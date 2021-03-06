package com.ctl.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!搜索基本流程示例
 */
public class SearchBaseFlow  {
    private final static String TEXT = "盼望着，盼望着，东风来了，春天的脚步近了。\n" +
            "一切都像刚睡醒的样子，欣欣然张开了眼。山朗润起来了，水涨起来了，太阳的脸红起来了。\n" +
            "小草偷偷地从土里钻出来，嫩嫩的，绿绿的。园子里，田野里，瞧去，一大片一大片满是的。坐着，躺着，打两个滚，踢几脚球，赛几趟跑，捉几回迷藏。风轻悄悄的，草软绵绵的。\n" +
            "桃树、杏树、梨树，你不让我，我不让你，都开满了花赶趟儿。红的像火，粉的像霞，白的像雪。花里带着甜味儿；闭了眼，树上仿佛已经满是桃儿、杏儿、梨儿。花下成千成百的蜜蜂嗡嗡地闹着，大小的蝴蝶飞来飞去。野花遍地是：杂样儿，有名字的，没名字的，散在草丛里，像眼睛，像星星，还眨呀眨的。\n" +
            "“吹面不寒杨柳风”，不错的，像母亲的手抚摸着你。风里带来些新翻的泥土的气息，混着青草味儿，还有各种花的香，都在微微润湿的空气里酝酿。鸟儿将巢安在繁花嫩叶当中，高兴起来了，呼朋引伴地卖弄清脆的喉咙，唱出宛转的曲子，与轻风流水应和着。牛背上牧童的短笛，这时候也成天嘹亮地响着。\n" +
            "雨是最寻常的，一下就是三两天。可别恼。看，像牛毛，像花针，像细丝，密密地斜织着，人家屋顶上全笼着一层薄烟。树叶儿却绿得发亮，小草儿也青得逼你的眼。傍晚时候，上灯了，一点点黄晕的光，烘托出一片安静而和平的夜。在乡下，小路上，石桥边，有撑起伞慢慢走着的人，地里还有工作的农民，披着蓑戴着笠。他们的房屋，稀稀疏疏的在雨里静默着。\n" +
            "天上风筝渐渐多了，地上孩子也多了。城里乡下，家家户户，老老小小，也赶趟儿似的，一个个都出来了。舒活舒活筋骨，抖擞抖擞精神，各做各的一份事去。“一年之计在于春”，刚起头儿，有的是工夫，有的是希望。\n" +
            "春天像刚落地的娃娃，从头到脚都是新的，它生长着。\n" +
            "春天像小姑娘，花枝招展的，笑着，走着。\n" +
            "春天像健壮的青年，有铁一般的胳膊和腰脚，领着我们上前去";
    private final static String FIELD1 = "acticle";

    public static void main(String[] args) throws Exception {
        // 要搜索的字段
        String keyword="天上风筝";
        keyword="天上风筝";
        keyword="可别恼";
        keyword="盼望着";
        // 使用的分词器
        Analyzer analyzer = new SmartChineseAnalyzer(true);
        Path indexPath =  new File("E:"+File.separator+"lucene"+File.separator+"dataindex").toPath();
        indexPath=Files.createTempDirectory("tempIndex");
        // 索引存储目录
        Directory directory = FSDirectory.open(indexPath);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
        // String text = "This is the text to be indexed.";
        doc.add(new Field(FIELD1, TEXT, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();
        // 索引读取器
        IndexReader indexReader = DirectoryReader.open(directory);
        // 索引搜索器
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // 查询生成器（解析输入生成Query查询对象）
        QueryParser parser = new QueryParser(FIELD1, analyzer);
        // 通过parse解析输入（分词），生成query对象
        Query query = parser.parse(keyword);
        System.out.println("分词后得到：" + query.toString());
        // 搜索，得到TopN的结果（结果中有命中总数，topN的scoreDocs（评分文档（文档id，评分）））
        TopDocs topDocs = indexSearcher.search(query, 10); // 前10条

        // 获得总命中数
        System.out.println(topDocs.totalHits);
        // 遍历topN结果的scoreDocs,取出文档id对应的文档信息
        for (ScoreDoc sdoc : topDocs.scoreDocs) {
            // 根据文档id取存储的文档
            Document hitDoc = indexSearcher.doc(sdoc.doc);
            // 取文档的字段
            System.out.println(hitDoc.get(FIELD1));
        }

        // 使用完毕，关闭、释放资源
        indexReader.close();
        directory.close();
    }

}

