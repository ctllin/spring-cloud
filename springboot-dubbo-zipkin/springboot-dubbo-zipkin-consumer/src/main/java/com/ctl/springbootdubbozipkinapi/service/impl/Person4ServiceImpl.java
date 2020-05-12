package com.ctl.springbootdubbozipkinapi.service.impl;

import com.ctl.springbootdubbozipkinapi.service.Person4Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>Title: Person4ServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.1
 * @date 2020-05-12 21:27
 */
@Service
@Slf4j
public class Person4ServiceImpl implements Person4Service {
    @Override
    public String say4(String id) {
        log.info("id={},say4\n",id);
        return System.currentTimeMillis()+id;    }
}
