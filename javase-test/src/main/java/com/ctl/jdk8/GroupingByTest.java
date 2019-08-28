package com.ctl.jdk8;


import org.assertj.core.util.Lists;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Title: GroupingByTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-26 11:11
 */

public class GroupingByTest {
    public static void main(String[] args) {
        List<Fruit> fruitList = Lists.newArrayList(new Fruit("apple", 6d), new Fruit("apple", 6d),
                new Fruit("banana", 7d), new Fruit("banana", 7d),
                new Fruit("banana", 7d), new Fruit("grape", 8d));

        Map<String, Long> map = fruitList.stream().collect(Collectors.groupingBy(Fruit::getName, Collectors.counting()));
        System.out.println(map);
        map.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).forEachOrdered(System.out::println);
        Map<String, DoubleSummaryStatistics> sumMap = fruitList.stream().collect(Collectors.groupingBy(Fruit::getName, Collectors.summarizingDouble(Fruit::getPrice)));
        System.out.println(sumMap);

        Map<String, Double> sumMap1 = fruitList.stream().collect(Collectors.groupingBy(Fruit::getName, Collectors.summingDouble(Fruit::getPrice)));
        System.out.println(sumMap1);

        Map<String, List<Fruit>> mapList = fruitList.stream().collect(Collectors.groupingBy(Fruit::getName));
        System.out.println(mapList);


    }
}
