package com.ctl.test.controller;

import com.ctl.test.zookeeper.lock.CuratorUtil;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>Title: ZookeeperLockController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-22 16:40
 */
@RestController
@RequestMapping(value = "/zookeeper")
public class ZookeeperLockController {
    static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    /** 至少锁60秒 */
    private static final long LOCK_MIN_TIME = 3000;
    @Resource
    private Environment environment;
    @Autowired
    private CuratorUtil curatorUtil;
    @RequestMapping(value = "/send")
    @ResponseBody
    public Object hello() {
        Map<String,Object> returnMap = new HashMap<>();
        InterProcessMutex lock = curatorUtil.getLock(getClass().getSimpleName() + "/lock", 1);
        if (lock == null) {
            logger.info("can not get lock, exit job.");
            returnMap.put("result","can not get lock, exit job.");
            return returnMap;
        }

        long st = System.currentTimeMillis();
        logger.info("start job...");

        try {
           Thread.sleep(3000);
        } catch (Exception e) {
            logger.error("job error", e);
        } finally {
            long cost = System.currentTimeMillis() - st;
            logger.info("job finished, cost {} ms.", cost);

            if (cost < LOCK_MIN_TIME) {
                try {
                    Thread.sleep(LOCK_MIN_TIME - cost);
                } catch (InterruptedException e) {}
            }
            curatorUtil.releaseLock(lock);
        }
        Long key = System.currentTimeMillis();
        String value = UUID.randomUUID().toString();
        String value2 = UUID.randomUUID().toString();
        returnMap.put("key", key);
        returnMap.put("value", value);
        returnMap.put("value2", value2);
        return returnMap;
    }
}
