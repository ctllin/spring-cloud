package com.ctl.springbootdubbozipkinapi.controller;

import com.ctl.springbootdubbozipkinapi.service.Person2Service;
import com.ctl.springbootdubbozipkinapi.service.Person3Service;
import com.ctl.springbootdubbozipkinapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: PersonController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.1
 * @date 2020-05-12 19:38
 */
@RestController
public class PersonController {
    @Autowired(required = false)
    private PersonService personService;
    @Autowired(required = false)
    private Person2Service person2Service;
    @Autowired(required = false)
    private Person3Service person3Service;

    @RequestMapping(value = "/test")
    public Object test(HttpServletRequest request, String id) {
        return personService.say1(id);
    }
}
