package com.ctl.test.zookeeper.lock;

/**
 * <p>Title: CuratorUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-22 15:28
 */
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CuratorUtil implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(CuratorUtil.class);

    //zookeeper.connection.url=192.168.42.29:2181,192.168.42.29:2182,192.168.42.29:2183
    //zookeeper.iread.lock.path=/home/soft/cluster-zookeeper-3.5.3-beta/lock

    @Value("${zookeeper.connection.url}")
    private String zookeeperConnectionString;
    @Value("${zookeeper.lockPath.prefix}")
    private String lockPathPrefix;
    private CuratorFramework client;

    @Override
    public void afterPropertiesSet() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        client.start();
    }

    /**
     * 获取锁。返回不为null表示成功获取到锁，用完之后需要调用releaseLock方法释放
     * @param relativePath 锁的相对路径，Not start with '/'
     * @param waitSeconds 等待秒数
     * @return 未获取到锁返回null
     */
    public InterProcessMutex getLock(String relativePath, int waitSeconds) {
        InterProcessMutex lock = new InterProcessMutex(client, lockPathPrefix + relativePath);
        try {
            if (lock.acquire(waitSeconds, TimeUnit.SECONDS)) {
                return lock;
            }
        } catch (Exception e) {
            logger.error("get lock error", e);
        }
        releaseLock(lock);
        return null;
    }

    /**
     * 释放锁
     */
    public void releaseLock(InterProcessMutex lock) {
        if (lock != null && lock.isAcquiredInThisProcess()) {
            try {
                lock.release();
            } catch (Exception e) {
                logger.warn("release lock error", e);
            }
        }
    }
}
