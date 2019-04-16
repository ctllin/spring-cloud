package com.ctl.service;

import org.springframework.web.bind.annotation.GetMapping;
/**
 * <p>Title: HelloService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-01-14 11:13
 */
public interface HelloService {
    @GetMapping("/sayhello")
    String sayhello();
}
