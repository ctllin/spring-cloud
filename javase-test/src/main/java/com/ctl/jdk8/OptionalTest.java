package com.ctl.jdk8;

import java.util.Optional;

/**
 * <p>Title: OptionalTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-17 14:29
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer num = null;
        Optional<Integer> number = Optional.of(num).filter(num1->num1>0);
    }
}
