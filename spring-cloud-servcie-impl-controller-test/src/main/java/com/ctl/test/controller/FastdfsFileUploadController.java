package com.ctl.test.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.InputStream;
import java.util.*;

/**
 * 用户controller类
 * @ClassName : UserController
 * @Author : yuanzy
 * @Date : 2019/4/23-10:46
 */

@Controller
@RequestMapping("/common/common")
public class FastdfsFileUploadController {
    @Autowired(required = false)
    private FastFileStorageClient storageClient;
    private static Logger logger = LoggerFactory.getLogger(FastdfsFileUploadController.class);
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, MultipartHttpServletRequest request){
        logger.info("图片开始上传,filename={}",file.getOriginalFilename());
        // 文件的名字
        String fileName = file.getOriginalFilename();
        String extName = fileName.split("\\.")[1];
        // 保存
        try {
            InputStream in = file.getInputStream();
            StorePath storePath = storageClient.uploadFile(in,file.getSize(), extName,null);
            String filePath =  storePath.getFullPath();
            String httpHead = "http://lcoalhost";
            if (httpHead != null && !httpHead.endsWith("/")) {
                httpHead += "/";
            }
            logger.info("图片上传成功fileurl={}", httpHead + filePath);
            return  httpHead+filePath;
        } catch (Exception e) {
            logger.error("上传文件失败"+e.getMessage(),e);
            return null;
        }
    }
    @RequestMapping(value = "/uploadMutiFile", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadMutiFile(@RequestParam(value = "files", required = false) MultipartFile files[], MultipartHttpServletRequest request) {
        logger.info("图片开始上传,filename={}", Arrays.deepToString(files));
        // 文件的名字
        // 保存
        try {
            List<Map<String, String>> list = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String fileName = file.getOriginalFilename();
                String extName = fileName.split("\\.")[1];
                InputStream in = file.getInputStream();
                StorePath storePath = storageClient.uploadFile(in, file.getSize(), extName, null);
                String filePath = storePath.getFullPath();
                String httpHead = "http://lcoalhost";
                if (httpHead != null && !httpHead.endsWith("/")) {
                    httpHead += "/";
                }
                Map<String, String> fileMap = new HashMap<>();
                fileMap.put(fileName, httpHead + filePath);
                list.add(fileMap);
                logger.info("图片{}上传成功fileurl={}", fileName, httpHead + filePath);
            }
            return list;
        } catch (Exception e) {
            logger.error("上传文件失败" + e.getMessage(), e);
            return null;
        }
    }
}
