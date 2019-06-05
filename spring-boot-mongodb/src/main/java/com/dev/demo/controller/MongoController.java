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
import com.dev.demo.model.UserQuery;
import com.dev.demo.service.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @RequestMapping(value = "/find", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    @ResponseBody
    public Object find(HttpServletRequest request, @RequestBody UserQuery userQuery) {
        Map<String,Object> returnMap = new HashMap<>();
        Sort sort = Sort.by(Sort.Direction.ASC, "age");
        Pageable pageable = PageRequest.of(userQuery.getCurrentPage(), userQuery.getPageSize(), sort);


       User user = new User();
       BeanUtils.copyProperties(userQuery,user);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith()) //采用“包含匹配”的方式查询
                .withIgnorePaths("currentPage", "pageSize");  //忽略属性，不参与查询
        Example<User> example = Example.of(user, matcher);
        Page<User> userPage = userRepository.findAll(example, pageable);
        returnMap.put("total",userPage.getTotalElements());
        returnMap.put("totalPages",userPage.getTotalPages());
//        returnMap.put("context",userPage.getContent());


        List<User> all = userRepository.findAll(example, sort);
        returnMap.put("data",all);
//        returnMap.put("page",userPage);

        Criteria criteria = new Criteria() ;
        Query query = new Query();
        query.with(pageable);
        query.with(sort);
        criteria.and("age").gt(1);
        query.addCriteria(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        returnMap.put("users",users);

        return returnMap;
    }
}