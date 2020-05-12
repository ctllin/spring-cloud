package com.ctl.springclouddubbohystrix.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: ZipkinBrave2Controller</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.1
 * @date 2020-05-12 17:20
 */
@Api("service的API接口")
@RestController
@RequestMapping("/service3")
public class ZipkinBrave3Controller {
@ApiOperation("trace第三步")
  @RequestMapping("/test")
  public String service1() throws Exception {
    Thread.sleep(300);
    return "service3";
  }
 
}
