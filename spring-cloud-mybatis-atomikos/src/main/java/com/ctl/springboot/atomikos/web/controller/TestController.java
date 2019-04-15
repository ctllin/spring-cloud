package com.ctl.springboot.atomikos.web.controller;

import com.ctl.springboot.atomikos.service.ManyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Random;

@Controller
@RequestMapping("/atomikos")
public class TestController {
	@Autowired
	private JdbcTemplate sysJdbcTemplate;
	@Autowired
	private JdbcTemplate busJdbcTemplate;
    @Autowired
    private ManyService manyService;

	@Transactional
	@RequestMapping("addMember")
    @ResponseBody
	public Object addMember() {
		System.out.println("begin.....");
		sysJdbcTemplate.execute("insert into member(id,name) values(replace(uuid(),'-',''),'sys')");
		busJdbcTemplate.execute("insert into member(id,name) values(replace(uuid(),'-',''),'bus')");
        int i = 1 / new Random().nextInt(2);// 赋值age为0故意引发事务
		System.out.println("end.....");
		return new Object();
	}
    @ResponseBody
    @RequestMapping("addMember2")
    public Object addMember2() {
        System.out.println("addMember2 begin.....");
        manyService.insertDb1AndDb2();
        System.out.println("addMember2 end.....");
        return new Object();
    }

    @ResponseBody
    @RequestMapping("getMember")
    public Object getMember(String id) {
        System.out.println("addMember2 begin.....");
        manyService.getById(id);
        System.out.println("addMember2 end.....");
        return new Object();
    }
}
