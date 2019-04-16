package com.ctl.common.service;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>Title: GreetingService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-17 14:59
 */
public interface GreetingService {
    @GetMapping("/greeting")
    String greeting();
}
