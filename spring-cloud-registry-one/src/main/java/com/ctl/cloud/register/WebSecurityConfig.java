package com.ctl.cloud.register;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>Title: WebSecurityConfig</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-04-10 19:01
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        super.configure(http);
    }
}
// Request execution failure with status code 403; retrying on another server if available 意思是执行请求失败，返回码403，如果实例可用，重新向另外一台服务器发送请求，下面异常的意思就是找不到任何一台服务器去执行这个请求。http 403 我们可以把问题定位到请求被禁止，原因是由于新版的Spring security 会默认开启防csrf攻击，所有的请求都必须携带crsf这个参数，但是从以上的信息来看显然是没有的。所以我们需要主动去关闭，在Eureka服务配置
