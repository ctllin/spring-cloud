package com.ctl.test.sprinz.service.impl;

import com.ctl.test.sprinz.service.Encoreable;
import org.springframework.stereotype.Component;

/**
 * com.ctl.test.spring.aop.service.impl
 * EncoreableImpl
 * ctl 2019/3/28 22:39
 */
@Component
public class EncoreableImpl implements Encoreable {
    @Override
    public void performEncore(Object obj){
        System.out.println("Encoreabl->EncoreableImpl.performEncore()\t"+obj);
    }
}