package com.ctl.jdk8;

import com.ctl.Node;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>Title: PredicateTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-17 13:51
 */
public class PredicateTest {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-1, -2, 0, 4, 5);
        filter(numbers, n -> n > 0);
        numbers.parallelStream().filter(n -> n > 0).forEachOrdered(n -> System.out.println(n));//有序
        numbers.parallelStream().filter(n -> n > 0).forEach(n -> System.out.println(n));//无序
    }

    public static void filter(List<Integer> numbers, Predicate<Integer> condition) {
        for (Integer number : numbers) {
            if (condition.test(number)) {
                System.out.println("Eligible number: " + number);
            }
        }
    }
}
