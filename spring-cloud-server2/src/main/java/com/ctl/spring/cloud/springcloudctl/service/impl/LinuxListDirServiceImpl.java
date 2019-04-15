package com.ctl.spring.cloud.springcloudctl.service.impl;

import com.ctl.spring.cloud.springcloudctl.service.ListDirService;

/**
 * <p>Title: LinuxListDirService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-02-25 14:45
 */
public class LinuxListDirServiceImpl implements ListDirService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
