package com.ctl.test.javaapi.common.util;

import com.ctl.test.model.Person;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * <p>Title: ConstructorUtilsTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-25 16:54
 */
public class ConstructorUtilsTest {
    public static void main(String[] args) throws Exception {
        Person ctl1 = ConstructorUtils.invokeConstructor(Person.class);//无参构造函数
        System.out.println(ctl1);
        Person ctl2 = ConstructorUtils.invokeConstructor(Person.class, new Object[]{1, "ctl"}, new Class[]{Integer.class, String.class});//构造函数 Integer id, String name
        System.out.println(ctl2);
        //Person ctl3 = ConstructorUtils.invokeConstructor(Person.class, 1, "ctl", new Date());//无此构造函数  会报错
        //System.out.println(ctl3); java.lang.NoSuchMethodException: No such accessible constructor on object: com.ctl.test.model.Person
        Person ctl4 = ConstructorUtils.invokeConstructor(Person.class, new Object[]{"ctl", 1}, new Class[]{String.class, Integer.class});//构造函数 String name, Integer id
        System.out.println(ctl4);
    }
}
