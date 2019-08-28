package com.dev.config;

import com.dev.bean.A;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: AllOfA</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-26 10:06
 */
@Component
public class AllOfA {
    public AllOfA(List<A> allA){
        System.out.println(Arrays.deepToString(allA.toArray()));
    }
}
//[com.dev.config.CefA@2e17a321, com.dev.config.DefA@521bb1a4]