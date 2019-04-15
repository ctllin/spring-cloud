package com.ctl.test.controller;

import com.whalin.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * com.ctl.test.controller
 * MemCachedController
 * ctl 2019/3/29 0:55
 */
@RestController
public class MemCachedController {
    //@Autowired(required = false)
    private MemCachedClient memCachedClient;
    //启动memcached
    //memcached -d -m 64M -u root -l 192.168.42.29 -p 11211 -c 256 -P /tmp/memcached.pid
    public void contextLoads() throws InterruptedException {
        // 放入缓存
        boolean flag = memCachedClient.set("a", 1);

        // 取出缓存
        Object a = memCachedClient.get("a");
        System.out.println(a);


        // 3s后过期
        memCachedClient.set("b", "2", new Date(3000));
        Object b = memCachedClient.get("b");
        System.out.println(b);

        Thread.sleep(3000);
        b = memCachedClient.get("b");
        System.out.println(b);
    }
}
