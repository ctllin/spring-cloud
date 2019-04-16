package com.ctl.sharding.controller;

import com.ctl.sharding.service.MemberService;
import com.ctl.sharding.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with Intellij IDEA.
 * @author ctl
 * @version 2019/03/14
 */
@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired(required = false)
    private MemberService memberService;

    @PostMapping("save")
    public Boolean save(@RequestBody Member member) {
        return memberService.save(member);
    }

    @GetMapping("selectById")
    public Member selectById(String id) {
        return memberService.getById(id);
    }
}
