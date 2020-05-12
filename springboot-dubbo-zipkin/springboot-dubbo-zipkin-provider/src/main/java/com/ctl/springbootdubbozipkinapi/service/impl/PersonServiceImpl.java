package com.ctl.springbootdubbozipkinapi.service.impl;

import com.ctl.springbootdubbozipkinapi.service.Person2Service;
import com.ctl.springbootdubbozipkinapi.service.Person3Service;
import com.ctl.springbootdubbozipkinapi.service.PersonService;
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
public class PersonServiceImpl implements PersonService {
    @Autowired(required = false)
    private Person2Service   person2Service;

    @Override
    public String say1(String id) {
        log.info("id={},say1",id);
        return person2Service.say2(id);
    }
}
