package com.ctl.lucene.test1;

/**
 * com.ctl.lucene.test1
 * Searcher 根据索引搜索
 * ctl 2019/3/31 0:59
 */

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Searcher {

    public static void search(String indexDir, String q) throws Exception {
        // 得到读取索引文件的路径
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        // 通过dir得到的路径下的所有的文件
        IndexReader reader = DirectoryReader.open(dir);
        // 建立索引查询器
        IndexSearcher is = new IndexSearcher(reader);
        // 实例化分析器
        Analyzer analyzer = new SmartChineseAnalyzer(true);
        // 建立查询解析器
        /**
         * 第一个参数是要查询的字段； 第二个参数是分析器Analyzer
         */
        QueryParser parser = new QueryParser("contents", analyzer);
        // 根据传进来的p查找
        Query query = parser.parse(q);
        System.out.println("分词后得到：" + query.toString());
        // 计算索引开始时间
        long start = System.currentTimeMillis();
        // 开始查询
        /**
         * 第一个参数是通过传过来的参数来查找得到的query； 第二个参数是要出查询的行数
         */
        TopDocs hits = is.search(query, 10);
        // 计算索引结束时间
        long end = System.currentTimeMillis();
        System.out.println("匹配 " + q + " ，总共花费" + (end - start) + "毫秒" + "查询到" + hits.totalHits + "个记录");
        // 遍历hits.scoreDocs，得到scoreDoc
        /**
         * ScoreDoc:得分文档,即得到文档 scoreDocs:代表的是topDocs这个文档数组
         *
         * @throws Exception
         */
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            System.out.println(doc.get("fullPath") + "\t" + doc.get("contentStr"));
        }
        // 关闭reader


        //高亮显示start
        //算分
        QueryScorer scorer = new QueryScorer(query,"contents");
        //显示得分高的片段
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        //设置标签内部关键字的颜色
        //第一个参数：标签的前半部分；第二个参数：标签的后半部分。
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        //第一个参数是对查到的结果进行实例化；第二个是片段得分（显示得分高的片段，即摘要）
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        //设置片段
        highlighter.setTextFragmenter(fragmenter);
        //高亮显示end
        //遍历topDocs
        /**
         * ScoreDoc:是代表一个结果的相关度得分与文档编号等信息的对象。
         * scoreDocs:代表文件的数组
         * @throws Exception
         * */
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            //获取文档
            Document document = is.doc(scoreDoc.doc);
            //输出全路径
            System.out.println(document.get("contentStr"));
            String contents = document.get("contentStr");
            if (contents != null) {
                //把全部得分高的摘要给显示出来
                //第一个参数是对哪个参数进行设置；第二个是以流的方式读入
                TokenStream tokenStream = analyzer.tokenStream("contentStr", new StringReader(contents));
                //获取最高的片段
                System.out.println(highlighter.getBestFragment(tokenStream, contents));
            }

        }
        reader.close();
    }

    public static void main(String[] args) {
        String indexDir = "E:\\lucene\\dataindex";
        //我们要搜索的内容
        String q = "夏朝";
        q = "戏子";
        q = "家";
        q = "家家";
        q = "睡";
        q = "醒";
        q = "薄烟";
        q = "太阳的脸";
        q = "树上仿佛已经满是桃儿";
        q = "请不要相信我的美丽";
        //  q = "盼望着";
        try {
            search(indexDir, q);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}