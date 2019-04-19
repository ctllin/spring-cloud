package com.ctl.springboottest.aspect;

/**
 * <p>Title: LogAcpect</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-04-19 16:36
 */
import java.lang.annotation.*;

/**
 * <p>Title: LogAcpect</p>
 * <p>Description: 在dubbo链路追踪使用</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-19 10:38
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAcpect {
    String module() default "";
}

