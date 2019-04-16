package com.ctl.sharding.service.impl;

import com.ctl.sharding.entity.Member;
import com.ctl.sharding.service.DService;
import org.springframework.stereotype.Service;

/**
 * com.ctl.sharding.service
 * DubboServiceImpl
 * ctl 2019/3/23 0:49
 */
@Service(value = "dServiceImpl")// default: DServiceImpl of Type :: class com.ctl.sharding.service.impl.DServiceImpl
public class DServiceImpl implements DService {
    @Override
    public Object method1() {
        return "method1";
    }

    @Override
    public Object method2() {
        return new Member();
    }

    @Override
    public Object method3() {
        return System.currentTimeMillis();
    }
}
