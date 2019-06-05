package com.dev.demo.controller;

/**
 * <p>Title: MongoController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-06-05 11:46
 */


import com.dev.demo.model.User;
import com.dev.demo.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;

    /**
     * 处理excel与数据库之间的差异数据
     * */
    @RequestMapping("/findByName")
    public Object findByName() throws Exception {
       return userRepository.findByName("1");
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object add(HttpServletRequest request, @RequestBody User user) {
        return userRepository.insert(user);
    }
}