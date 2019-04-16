package com.ctl.sharding.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ctl.sharding.entity.Member;
import com.ctl.sharding.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with Intellij IDEA.
 * @author ctl
 * @version 2019/03/14
 */
@Service
public class MemberService extends ServiceImpl<MemberMapper, Member> {

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Member member) {
        return super.insertOrUpdate(member);
    }
}