package com.ctl.springboottest.controller;

import com.alibaba.fastjson.JSONObject;
import com.ctl.springboottest.po.Goods;
import com.ctl.springboottest.service.impl.GoodsRepository;
import com.google.gson.Gson;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * com.ctl.test.controller
 * AsyncController  使用该类需要先创建index
 * curl -XPOST 'localhost:9200/goods/store_goods/_bulk?pretty' -H 'Content-Type: application/json' --data-binary @goodsToES.json
 * ctl 2019/3/31 21:35
 */
@RestController
@RequestMapping("/es")
public class ElasticSearchController {

    @Autowired
    private GoodsRepository er;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //增加
    @RequestMapping("/add/{id}")
    @ResponseBody
    public Goods add(@PathVariable("id") String id) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(getName(32));
        goods.setSku(String.valueOf(System.currentTimeMillis()));
        er.save(goods);
        System.err.println("add a obj");
        return goods;
    }

    //删除
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Goods delete(@PathVariable("id") String id) {
        Goods goods = new Goods();
        goods.setId(id);
        er.delete(goods);
        return goods;
    }

    //删除
    @RequestMapping("/delete2")
    @ResponseBody
    public Goods delete2(@RequestBody Goods good) {
        er.delete(good);
        return good;
    }

    //删除
    @RequestMapping("/deleteByIndexTypeId/{index}/{type}/{id}")
    @ResponseBody
    public Object deleteByIndexTypeId(@PathVariable("index") String index,@PathVariable("type") String type,@PathVariable("id") String id) {
        String result = elasticsearchTemplate.delete(index, type, id);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("deleteByIndexTypeIdResult",result);
        return returnMap;
    }

    //删除根据条件删除数据
    @RequestMapping("/deleteByDeleteQuery/{index}/{type}")
    @ResponseBody
    public Object deleteByDeleteQuery(@PathVariable("index") String index,@PathVariable("type") String type,@RequestBody Goods good) {
        Map<String, Object> returnMap = new HashMap<>();
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setIndex(index);
        deleteQuery.setType(type);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (good.getName() != null) {
            //name=辣牛肉湯麵 匹配=[雙響泡哈燒鮮辣牛肉湯麵, 真麵堂紅燒牛肉湯麵104g/包, 味王-椒麻牛肉湯麵91g/包] name会进行分词,value包含分词即可
            //boolQueryBuilder.must(QueryBuilders.matchQuery("name", goods.getName()));
            //name=辣牛肉湯麵 匹配=[*辣牛肉湯麵*] name不会进行分词 value中需要包含这个短语
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("name", good.getName()));
            //name=辣牛肉湯麵 无法匹配到数据 ,term不分词，所以term查询的条件必须是text字段分词后的某一个,name=肉则可以
            //boolQueryBuilder.must(QueryBuilders.termQuery("name", goods.getName()));
        }
        if (good.getSku() != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("sku", good.getSku()));
        }
        if (good.getName() == null && good.getSku() == null) {
            System.out.println("name sku 不能同时为空");
            return returnMap;
        }
        deleteQuery.setQuery(boolQueryBuilder);
        elasticsearchTemplate.delete(deleteQuery, Goods.class);
        return returnMap;
    }

    //局部更新
    @RequestMapping("/update/{id}")
    @ResponseBody
    public Goods update(@PathVariable("id") String id, @RequestBody Goods good) {
        Goods goods = er.queryGoodsById(id);
        if (good.getName() == null) {

            goods.setName(getName(new Random().nextInt(32 + 1)));
        } else {
            goods.setName(good.getName());
        }
        if (good.getSku() == null) {

            goods.setSku(getENStr(new Random().nextInt(32 + 1)));
        } else {
            goods.setSku(good.getSku());
        }
        er.save(goods);
        System.err.println("update a obj");
        return goods;
    }


    //查询
    @RequestMapping("/query/{id}")
    @ResponseBody
    public Goods query(@PathVariable("id") String id) {
        Goods accountInfo = er.queryGoodsById(id);
        Optional<Goods> byId = er.findById(id);
        Goods goods = byId.get();
        System.out.println(JSONObject.toJSONString(byId));

        System.err.println(new Gson().toJson(accountInfo));
        return accountInfo;
    }
    //查询
    @RequestMapping("/queryByName/{name}")
    @ResponseBody
    public List<Goods> queryByName(@PathVariable("name") String name) {
        List<Goods> goodsList = er.queryGoodsByName(name);
        return goodsList;
    }

    //查询 模糊查询(单个字段)不进行分词(等同于like '%value%')
    @RequestMapping("/matchPhraseQuery")
    @ResponseBody
    public Iterable<Goods> matchPhraseQuery(@RequestBody Goods goods) {
        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("name", goods.getName());
        if (goods.getCurrentPage() == null || goods.getCurrentPage() <= 0) {
            goods.setCurrentPage(1);
        }
        if (goods.getPageSize() == null || goods.getPageSize() <= 0) {
            goods.setPageSize(100);
        }
        Pageable pageable = new QPageRequest(0, goods.getPageSize());
        Iterable<Goods> goodsIterable = er.search(queryBuilder, pageable);
        return goodsIterable;
    }

    //多个条件都满足查询,不进行分词
    //curl -XPOST 'localhost:10000/es/boolMustQueryBuilder' -H 'Content-Type: application/json;charset=utf-8' -d '{"name":"鮮辣牛肉湯麵","sku":"804699"}'
    @RequestMapping("/boolMustQueryBuilder")
    @ResponseBody
    public Iterable<Goods> boolMustQueryBuilder(@RequestBody Goods goods) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (goods.getName() != null) {
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("name", goods.getName()));
        }
        if (goods.getSku() != null) {
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("sku", goods.getSku()));
        }
        if (goods.getCurrentPage() == null || goods.getCurrentPage() <= 0) {
            goods.setCurrentPage(1);
        }
        if (goods.getPageSize() == null || goods.getPageSize() <= 0) {
            goods.setPageSize(100);
        }
        Pageable pageable = new QPageRequest(0, goods.getPageSize());
        Iterable<Goods> goodsIterable = er.search(boolQueryBuilder, pageable);
        return goodsIterable;
    }

    //curl -XPOST 'localhost:10000/es/searchQuery' -H 'Content-Type: application/json;charset=utf-8' -d '{}'
    @RequestMapping("/searchQuery")
    @ResponseBody
    public Object searchQuery(@RequestBody Goods goods) {
        //外层大条件（or）
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //条件1 (and and )
        BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
        if (goods.getName() != null) {
            //name=辣牛肉湯麵 匹配=[雙響泡哈燒鮮辣牛肉湯麵, 真麵堂紅燒牛肉湯麵104g/包, 味王-椒麻牛肉湯麵91g/包] name会进行分词,value包含分词即可
            //boolQueryBuilder.must(QueryBuilders.matchQuery("name", goods.getName()));
            //name=辣牛肉湯麵 匹配=[*辣牛肉湯麵*] name不会进行分词 value中需要包含这个短语
            boolQueryBuilder1.must(QueryBuilders.matchPhraseQuery("name", goods.getName()));
            //name=辣牛肉湯麵 无法匹配到数据 ,term不分词，所以term查询的条件必须是text字段分词后的某一个,name=肉则可以
            //boolQueryBuilder.must(QueryBuilders.termQuery("name", goods.getName()));
        }
        if (goods.getSku() != null) {
            boolQueryBuilder1.must(QueryBuilders.termQuery("sku", goods.getSku()));
        }
        if (goods.getCurrentPage() == null || goods.getCurrentPage() <= 0) {
            goods.setCurrentPage(1);
        }
        if (goods.getPageSize() == null || goods.getPageSize() <= 0) {
            goods.setPageSize(100);
        }

        //条件2 (and and )
        BoolQueryBuilder boolQueryBuilder2 = QueryBuilders.boolQuery();
        boolQueryBuilder2.must(QueryBuilders.termQuery("sku", "123456"));
        boolQueryBuilder2.must(QueryBuilders.matchPhraseQuery("name", "BND"));

        //最外层条件将两个条件or
        boolQueryBuilder.should(boolQueryBuilder1);
        boolQueryBuilder.should(boolQueryBuilder2);
        //Pageable pageable = new QPageRequest(goods.getCurrentPage()-1, goods.getPageSize());
        Pageable pageable = new QPageRequest(0, goods.getPageSize());
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withIndices("goods") //index
                .withTypes("store_goods") //type
                .withFields("sku", "id", "name") //只返回这三个字段
                .withPageable(pageable) //分页
                .build();

        List<Goods> goodsList = elasticsearchTemplate.queryForList(searchQuery, Goods.class);
        return goodsList;
    }


    //查询
    @RequestMapping("/searchSimilar")
    @ResponseBody
    public Page<Goods> searchSimilar(@RequestBody Goods goods) {
        if (goods.getCurrentPage() == null || goods.getCurrentPage() <= 0) {
            goods.setCurrentPage(1);
        }
        if (goods.getPageSize() == null || goods.getPageSize() <= 0) {
            goods.setPageSize(100);
        }
        Pageable pageable = new QPageRequest(0, goods.getPageSize());
        Page<Goods> page = er.searchSimilar(goods, new String[]{"sku","id","name","img_url"}, pageable);
        return page;
    }

    //统计条数
    @RequestMapping("/count")
    @ResponseBody
    public Object count() {
        long count = er.count();
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allCount", count);
        return returnMap;
    }
    @RequestMapping("/deleteIndex/{index}")
    @ResponseBody
    public Object deleteIndex(@PathVariable("index") String index) {
        boolean b = elasticsearchTemplate.deleteIndex(index);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("delIndexResult",b);
        return returnMap;
    }
    public static void main(String[] args) {
        for (int i = 65; i < 65 + 26; i++) {
            System.out.println(getENStr(32));
        }
    }

    public static String getName(int strLen) {
        Random random = new Random();
        if (strLen <= 0) {
            strLen = 32;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < strLen; i++) {

            int nextInt = random.nextInt(20901);
            str.append((char) (nextInt + 19968));
        }
        return str.toString();
    }

    public static String getENStr(int strLen) {
        Random random = new Random();
        if (strLen <= 0) {
            strLen = 32;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < strLen; i++) {

            int nextInt = random.nextInt(26);
            str.append((char) (nextInt + 65));
        }
        return str.toString();
    }
}
//matchPhraseQuery和matchQuery等的区别，在使用matchQuery等时，在执行查询时，搜索的词会被分词器分词，而使用matchPhraseQuery时，不会被分词器分词，
// 而是直接以一个短语的形式查询，而如果你在创建索引所使用的field的value中没有这么一个短语（顺序无差，且连接在一起），那么将查询不出任何结果。

//将字段设置成keyword的时候查询的时候已有的值不会被分词。



//------------------------------------------------------------------------------
//https://www.cnblogs.com/chenmz1995/p/10199147.html
//term查询keyword字段。
//term不会分词。而keyword字段也不分词。需要完全匹配才可。

//term查询text字段。
//因为text字段会分词，而term不分词，所以term查询的条件必须是text字段分词后的某一个。

//match查询keyword字段
//match会被分词，而keyword不会被分词，match的需要跟keyword的完全匹配可以。

//match查询text字段
//match分词，text也分词，只要match的分词结果和text的分词结果有相同的就匹配。

//match_phrase匹配keyword字段。
//必须跟keywork一致才可以。

//match_phrase匹配text字段。
//keywork包含该短语即可

//query_string查询key类型的字段，试过了，无法查询。

//query_string查询text类型的字段。
//和match_phrase区别的是，不需要连续，顺序还可以调换。
//------------------------------------------------------------------------------

//must --> and
//should --> or
//通过嵌套组合可以得到sql同样的and 和 or 查询