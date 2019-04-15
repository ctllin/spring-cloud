package com.ctl.springboottest;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * com.ctl.springboottest
 * Test
 * ctl 2019/4/12 21:57
 */
public class Test {
//数据批量导入
//curl -XPOST 'localhost:9200/goods/store_goods/_bulk?pretty' -H 'Content-Type: application/json' --data-binary @data.json
//------data.json 最后一行必需有换行start-----
//    {"index":{"_index":"goods","_type":"store_goods","_id":"0000cf5c01604792b94f72e623c56136"}}
//    {"id":"0000cf5c01604792b94f72e623c56136","merchant_id":"1","sku":"144100","plu_code":"1","code":"","name":"威猛先生地板清潔劑-芬多精 1","simple_name":"威猛先生地板清潔劑-芬多精 1","brand_code":"","brand_name":"2","unit":"件","specification":"件","grade":"合格","pack_size":"1","manufacturer":"","manufacturer_address":"","place_of_origin":"台灣","description":"","img_url":"","packing_flag":"0","process_flag":"0","raw_materials_flag":"0","type":"0","storage_mode":"0","suttle":"","weight":"2","retail_above":"","retail_below":"","category_id":"6610865fcfd64162bf64b0a15664beb9","category_code":"2_27_92_11","category_name":"地板清潔","route_category_code":"0@2@2_27@2_27_92","route_category_name":"All@大宗消費品採購處@家庭清潔紙製品課@家庭清潔","cost_price":"","market_price":"219","suggested_price":"","group_buy_price":"","proposal_purchase_price":"","keep_days":"","keep_hour":"","state":"0","remove_flag":"0","del_flag":"0","gmt_create":"2018-11-13 19:02:27","gmt_modified":"2019-03-14 18:04:30"}
//    {"index":{"_index":"goods","_type":"store_goods","_id":"0000d3409e554f45a22c5c00dc8af86f"}}
//    {"id":"0000d3409e554f45a22c5c00dc8af86f","merchant_id":"1","sku":"378276","plu_code":"","code":"","name":"紹興熟香腸50g","simple_name":"23782761","brand_code":"","brand_name":"","unit":"包","specification":"50g/支","grade":"","pack_size":"1","manufacturer":"","manufacturer_address":"","place_of_origin":"台灣","description":"","img_url":"","packing_flag":"0","process_flag":"0","raw_materials_flag":"0","type":"0","storage_mode":"0","suttle":"","weight":"","retail_above":"","retail_below":"","category_id":"038c1ee64eac4c67939acd4375390a44","category_code":"2_15_25_4","category_name":"中式香腸","route_category_code":"0@2@2_15@2_15_25","route_category_name":"All@大宗消費品採購處@冷藏課@加工肉製品","cost_price":"","market_price":"26","suggested_price":"","group_buy_price":"","proposal_purchase_price":"","keep_days":"","keep_hour":"","state":"0","remove_flag":"0","del_flag":"0","gmt_create":"2018-11-13 19:03:17","gmt_modified":"2019-02-18 20:32:24"}
//
//------data.json 最后一行必需有换行 end-----
    private static Logger logger = LoggerFactory.getLogger("Application_ERROR");

    public static void main(String[] args) throws IOException {
        File file = new File("E://elk//goods.json");

        List<String> strings = FileUtils.readLines(file, "utf-8");
        LinkedList<String> toWrite = new LinkedList<>();
        int size = strings.size();
        String str = "{\"index\":{\"_index\":\"goods\",\"_type\":\"store_goods\",\"_id\":\"##\"}}";
        int times = 0;
        int files = 0;
        for (int i = 0; i < size; i++) {
            if (strings.get(i) != null && !"".equals(strings.get(i))) {
                Map<String, String> parse = null;
                try {
                    parse = (Map<String, String>) JSONObject.parse(strings.get(i));
                } catch (Exception e) {
                    System.out.println(strings.get(i));
                    continue;
                }
                times++;
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                toWrite.add(str.replace("##", uuid));
                parse.put("id", uuid);
                toWrite.add(JSONObject.toJSONString(parse));
                if (times % 20000 == 0) {
                    files++;
                    File fileToWrite = new File("E://elk//goodsES" + files + ".json");
                    fileToWrite.createNewFile();
                    FileUtils.writeLines(fileToWrite, toWrite);
                    toWrite.clear();
                }
            }
        }
    }
}
//curl -XPOST 'localhost:9200/goods/store_goods/_bulk?pretty' -H 'Content-Type: application/json' --data-binary @goodsES1.json
//curl -XPOST 'localhost:9200/goods/store_goods/_bulk?pretty' -H 'Content-Type: application/json' --data-binary @goodsES2.json
//curl -XPOST 'localhost:9200/goods/store_goods/_bulk?pretty' -H 'Content-Type: application/json' --data-binary @goodsES3.json
//curl -XPOST 'localhost:9200/goods/store_goods/_bulk?pretty' -H 'Content-Type: application/json' --data-binary @goodsES4.json
//curl -XPOST 'localhost:9200/goods/store_goods/_bulk?pretty' -H 'Content-Type: application/json' --data-binary @goodsES5.json