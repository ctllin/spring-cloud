package com.ctl.sharding.controller;

import com.ctl.sharding.entity.Member;
import com.ctl.sharding.service.MemberService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.ctl.test.controller
 * StaticAutowireController
 * ctl 2019/3/20 21:50
 */
@RestController
@RequestMapping(value = "/static")
public class StaticAutowireController implements InitializingBean {
    protected static final Logger logger = LoggerFactory.getLogger(StaticAutowireController.class);
    //spring静态注入
    private static MemberService memberService;
    static {
        logger.info(" static init 1执行");

    }
    @Autowired
    public void setMemberService(MemberService memberService) {
        logger.info(" Autowired 2执行,spring 不对静态变量进行注入,但是可以同过set方法注入");
        StaticAutowireController.memberService = memberService;
    }
    @PostConstruct
    public void init(){
        logger.info(" PostConstruct init 3执行");
    }

    @PreDestroy
    public void close(){
        logger.info(" PreDestroy close");
    }
    //@ModelAttribute注释的方法会在此controller每个方法执行前被执行，因此对于一个controller映射多个URL的用法来说，要谨慎使用。
    @ModelAttribute
    public void modelAttributeMethod(HttpServletRequest request , HttpServletResponse response){
        logger.info(" ModelAttribute call ...... ");
    }
    @ModelAttribute
    public Member modelAttributeMember(@PathVariable(value = "pid") String pid){
        logger.info(" modelAttributeMember call ...... 虽然有返回值,但是不影响getPerson接口返回数据 ");
        Member member = memberService.getById(pid);
        return member;
    }
    @RequestMapping(value = "/getMember/{pid}")
    @ResponseBody
    public Member getPerson(@PathVariable(value = "pid") String pid) {
        Member member = memberService.getById(pid);
        member.setName("林家小院");
        return member;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("执行afterPropertiesSet 4执行");
    }
}
