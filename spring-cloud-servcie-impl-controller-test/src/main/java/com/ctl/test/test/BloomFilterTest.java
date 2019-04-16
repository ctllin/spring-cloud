package com.ctl.test.test;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.math.BigDecimal;
import java.util.*;

/**
 * Bloom Filter特点： 参考http://f.dataguru.cn/thread-184420-1-1.html
 * 不存在漏报（False Negative），即某个元素在某个集合中，肯定能报出来。
 * 可能存在误报（False Positive），即某个元素不在某个集合中，可能也被爆出来。
 */
public class BloomFilterTest {
    private static final int expectedInsertions = 1000000;
    private static BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), expectedInsertions, 0.001);
    private static List<String> stringList = new ArrayList<>(expectedInsertions);
    private static Set<String> stringSet = new HashSet<>(expectedInsertions);
    private static int right = 0;
    private static int error = 0;

    public static void main(String[] args) {
        for (int i = 0; i < expectedInsertions; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            bloomFilter.put(uuid);
            stringList.add(uuid);
            stringSet.add(uuid);
        }
        for (int i = 1; i <= 10000; i++) { //存在的去判断正确率100%
            String key = stringList.get(i * 100 - 1);
            if (bloomFilter.mightContain(key)) {
                if (stringSet.contains(key)) {
                    ++right;
                } else {
                    ++error;
                }
            }
        }
        System.out.println("right=" + right + "\terror=" + error);
        //right=10000	error=0 存在一定是100%
        //right=10000	error=0 存在一定是100%
        //right=10000	error=0 存在一定是100%

        error = 0;
        for (int i = 1; i <= 100000; i++) { //不存在的正确率就是fpp设定的参数上下微小浮动
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            if (bloomFilter.mightContain(uuid)) {
                System.out.print("误伤的数量" + (++error));
            } else {

            }
        }
        System.out.println("\nerror=" + new BigDecimal(error/100000.0d));
        //error=0.00103999999999999990889232304169809140148572623729705810546875
        //error=0.000790000000000000012108369862318113518995232880115509033203125
        //error=0.00093000000000000005405398351143730906187556684017181396484375
        //因为fpp设置为0.001所以会得到上面三个结果每次结果都不同但是基本都是在0.001左右

    }
}