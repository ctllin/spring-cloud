package com.ctl.springbootdubbozipkinapi.service.impl;

import com.ctl.springbootdubbozipkinapi.service.Person2Service;
import com.ctl.springbootdubbozipkinapi.service.Person3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: Person2ServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.1
 * @date 2020-05-12 18:23
 */
@Service
@Slf4j
public class Person2ServiceImpl implements Person2Service {
    @Autowired(required = false)
    private Person3Service person3Service;
    @Override
    public String say2(String id) {
        log.info("id={},say2",id);
        return person3Service.say3(id);
    }
}
