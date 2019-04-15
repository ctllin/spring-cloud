package com.ctl.test.sprinz.service.impl;

import com.ctl.test.sprinz.service.Performance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * com.ctl.test.spring.aop.service.impl
 * GangQinPerformance 钢琴表演 未实现任何方法
 * ctl 2019/3/28 22:43
 */
@Component
@Qualifier("gangqin")
public class GangQinPerformance implements Performance {
}
