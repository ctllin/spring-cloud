package com.ctl.springbootdubbozipkinapi.service.impl;

import com.ctl.springbootdubbozipkinapi.service.Person2Service;
import com.ctl.springbootdubbozipkinapi.service.Person3Service;
import com.ctl.springbootdubbozipkinapi.service.Person4Service;
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
public class Person3ServiceImpl implements Person3Service {
    @Autowired(required = false)
    private Person4Service person4Service;
    @Override
    public String say3(String id) {
        log.info("id={},say3\n",id);
        return person4Service.say4(id);
    }
}
