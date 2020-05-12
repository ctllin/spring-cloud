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
 * <p>Title: ZipkinBraveController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.1
 * @date 2020-05-12 17:15
 */
@Api("service的API接口")
@RestController
@RequestMapping("/service1")
public class ZipkinBraveController {
 
 
  @Autowired
  private OkHttpClient client;
  
          @ApiOperation("trace第一步")
  @RequestMapping("/test")
  public String service1() throws Exception {
    Thread.sleep(100);
    Request request = new Request.Builder().url("http://localhost:8082/service2/test").build();
    Response response = client.newCall(request).execute();
    return response.body().string();
  }
  
}