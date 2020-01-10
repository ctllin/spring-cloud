package com.ctl.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;

/**
 * <p>Title: AjaxDownloadController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2020-01-10 10:50
 */
@RestController
@Slf4j
public class AjaxDownloadController {

    @Autowired
    private Environment environment;
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportResourcePlayByDay(HttpServletResponse response, HttpServletRequest request) {

        File file = new File(environment.getProperty("java.io.tmpdir") + File.separator + System.currentTimeMillis() + ".txt");
        try {
            String str="export success,file is comming";
            response.setHeader("filename", file.getName());
            response.setHeader("firstContentLength", String.valueOf(str.getBytes().length));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i <1000000 ; i++) {
                stringBuilder.append("测试1234567890测试数据测试数据测试数据测试数据"+ UUID.randomUUID().toString()+System.currentTimeMillis()).append("\n");
            }
            FileUtils.writeByteArrayToFile(file, stringBuilder.toString().getBytes("utf-8"));
            byte[] bytes = FileUtils.readFileToByteArray(file);
            response.setHeader("secondFileLength", String.valueOf(bytes.length));
            response.getOutputStream().write(str.getBytes());
            response.getOutputStream().flush();
            //response.setStatus(HttpStatus.CONTINUE.value());
//
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
            response.setStatus(200);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

