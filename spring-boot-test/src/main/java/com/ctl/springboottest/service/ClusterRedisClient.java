package com.ctl.springboottest.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ctl.springboottest.util.ConfigUtils;
import com.ctl.springboottest.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.params.SetParams;

import java.util.*;

/**
 * <p>
 * Title: RedisUtil
 * </p>
 * <p>
 * Description: redis集群工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Company: www.hanshow.com
 * </p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-05-17 19:57
 */
public class ClusterRedisClient implements RedisClient {
    static Logger logger = LoggerFactory.getLogger(ClusterRedisClient.class);
    //private static Jedis jedis=redisInstances();
    private static JedisCluster cluster = null;
    private static Map<String, String> setDataMap = new HashMap<>();
    private static final Integer SECURITY_KEY_EXPIRE_TIME = 24 * 60 * 60 * 31;

    static {
        poolInit();
    }

    private static void initializePool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxTotal个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(100);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(1000 * 50);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            //在将连接放回池中前，自动检验连接是否有效
            config.setTestOnReturn(true);
            //自动测试池中的空闲连接是否都是可用连接
            config.setTestWhileIdle(true);
            //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
            //config.setMaxWaitMillis(-1);
            String redisType = ConfigUtils.getType("redis.type");
            Set<HostAndPort> nodes = new LinkedHashSet<>();
            String redisClusterAddress = ConfigUtils.getType("redis.cluster.address");
            String[] redisAddress = redisClusterAddress.split(",");
            if (redisAddress != null) {
                for (int i = 0; i < redisAddress.length; i++) {
                    String redisAddressTemp = redisAddress[i];
                    String[] addressPort = redisAddressTemp.split(":");
                    if (addressPort != null && addressPort.length >= 2) {
                        nodes.add(new HostAndPort(addressPort[0], Integer.parseInt(addressPort[1])));//master
                    }
                }
            }
            //加入前面三个为master后面三个为从属当关闭前三个后 后三个会自动变master 然后从后三个获取数据
            cluster = new JedisCluster(nodes, 100000, 20000, 30, ConfigUtils.getType("redis.auth"), config);

            logger.info("redisType={},#1普通2集群", redisType);
        } catch (NumberFormatException e) {
            logger.error("初始化redis失败-cluster", e);

        }
    }

    /**
     * 多线程环境同步初始化（保证项目中有且仅有一个连接池）
     */
    private static synchronized void poolInit() {

        if (cluster == null) {
            initializePool();
        }
    }


    /**
     * 获取cluster
     *
     * @return
     */
    public static JedisCluster getCluster() {
        try {
            if (cluster == null) {
                poolInit();
            }
            if (cluster != null) {
                return cluster;
            } else {
                logger.info("获取Redis cluster失败");
                return null;
            }
        } catch (Exception e) {
            logger.error("获取redis cluster失败", e);
            return null;
        }
    }

    /**
     * 设置字符串 保存时间为指定时长
     *
     * @param key
     * @param value
     * @param expire 保存时长单位秒
     * @return
     */

    public boolean setString(String key, String value, int expire) {
        try {
            String returnStr = getCluster().set(key, value);
            long result = getCluster().expire(key, expire);
            return "OK".equals(returnStr) && result >= 1 ? true : false;
        } catch (Exception e) {
            logger.error("设置字符串失败", e);
            try {
                String returnStr = getCluster().set(key, value);
                long result = getCluster().expire(key, expire);
                return "OK".equals(returnStr) && result >= 1 ? true : false;
            } catch (Exception e1) {
                logger.error("第二次设置字符串失败", e);
                return false;
            }
        } finally {

        }
    }

    /**
     * 设置对象(不能为数组或list) 保存时间为指定时长
     *
     * @param key
     * @param obj
     * @param expire 保存时长单位秒
     * @return
     */
    @Override
    public boolean setObject(String key, Object obj, int expire) {
        if (StringUtil.isEmptyTrim(key)) {
            logger.info("key={} is null", key);
            return false;
        }
        if (obj == null) {
            logger.info("obj={} is null", obj);
            return false;
        }
        String value;
        try {
            value = JSON.toJSONString(obj);
            String returnStr = getCluster().set(key, value);
            long result = getCluster().expire(key, expire);
            return "OK".equals(returnStr) && result >= 1 ? true : false;
        } catch (Exception e) {
            logger.error("设置对象失败", e);
            try {
                value = JSON.toJSONString(obj);
                String returnStr = getCluster().set(key, value);
                long result = getCluster().expire(key, expire);
                return "OK".equals(returnStr) && result >= 1 ? true : false;
            } catch (Exception e1) {
                logger.error("第二次设置对象串失败", e);
                return false;
            }
        } finally {

        }


    }


    /**
     * 设置字符串 一直存在
     *
     * @param key
     * @param value
     * @return
     */

    public boolean setString(String key, String value) {
        try {
            String returnStr = getCluster().set(key, value);
            return "OK".equals(returnStr) ? true : false;
        } catch (Exception e) {
            logger.error("设置字符串失败失败", e);
            try {
                String returnStr = getCluster().set(key, value);
                return "OK".equals(returnStr) ? true : false;
            } catch (Exception e1) {
                logger.error("第二次设置字符串失败失败", e1);
                return false;
            }
        } finally {

        }
    }

    /**
     * 设置设置对象(不能为数组或list) 一直存在
     *
     * @param key
     * @param obj
     * @return
     */
    @Override
    public boolean setObject(String key, Object obj) {
        if (StringUtil.isEmptyTrim(key)) {
            logger.info("key={} is null", key);
            return false;
        }
        if (obj == null) {
            logger.info("obj={} is null", obj);
            return false;
        }
        String value;
        try {
            value = JSON.toJSONString(obj);
            String returnStr = getCluster().set(key, value);
            return "OK".equals(returnStr) ? true : false;
        } catch (Exception e) {
            logger.error("设置对象失败", e);
            try {
                value = JSON.toJSONString(obj);
                String returnStr = getCluster().set(key, value);
                return "OK".equals(returnStr) ? true : false;
            } catch (Exception e1) {
                logger.error("第二次设置对象串失败", e);
                return false;
            }
        } finally {

        }
    }

    /**
     * 设置字符串
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean setNXString(String key, String value) {
        try {
            long result = getCluster().setnx(key, value);
            return 1 == result ? true : false;
        } catch (Exception e) {
            logger.error("设置字符串失败失败nx", e);
            try {
                long result = getCluster().setnx(key, value);
                return 1 == result ? true : false;
            } catch (Exception e1) {
                logger.error("第二次设置字符串失败失败nx", e1);
                return false;
            }
        } finally {

        }
    }


    /**
     * 根据key删除字符串
     *
     * @param key
     * @return 成功返回1失败返回0异常返回-1(没有该key删除返回0)
     */
    public long delString(String key) {
        try {
            return getCluster().expire(key, 0);
        } catch (Exception e) {
            logger.error("根据key删除字符串失败", e);
            try {
                return getCluster().expire(key, 0);
            } catch (Exception e1) {
                logger.error("第二次根据key删除字符串失败", e1);
            }
            return -1;
        } finally {

        }
    }


    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        try {
            return getCluster().get(key);
        } catch (Exception e) {
            logger.error("获取字符串失败", e);
            try {
                return getCluster().get(key);
            } catch (Exception e1) {
                logger.error("第二次获取字符串失败", e);
                return null;
            }
        } finally {

        }
    }

    /**
     * 获取对象(不能为数组或list)
     *
     * @param key
     * @param clazz
     * @return
     */
    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        try {
            String value = getCluster().get(key);
            if (StringUtil.isEmptyTrim(value)) {
                return null;
            }
            T t = JSON.toJavaObject((JSON) JSON.parse(value), clazz);
            return t;
        } catch (Exception e) {
            logger.error("获取对象失败", e);
            try {
                String value = getCluster().get(key);
                if (StringUtil.isEmptyTrim(value)) {
                    return null;
                }
                T t = JSON.toJavaObject((JSON) JSON.parse(value), clazz);
                return t;
            } catch (Exception e1) {
                logger.error("获取对象失败", e);
                return null;
            }
        } finally {

        }
    }

    /**
     * 根据key名称模糊获取key列表
     *
     * @param pattern 例如member*
     * @return
     */
    public Set<String> getKeySet(String pattern) {
        try {
            logger.debug("start getting keys...");
            TreeSet<String> keys = new TreeSet<>();
            Map<String, JedisPool> clusterNodes = getCluster().getClusterNodes();
            for (String node : clusterNodes.keySet()) {
                logger.debug("getting keys from: {}", node);
                JedisPool jp = clusterNodes.get(node);
                Jedis connection = null;
                try {
                    connection = jp.getResource();
                    keys.addAll(connection.keys(pattern));
                } catch (Exception e) {
                    logger.error("getting keys error node: {}", node, e);
                } finally {
                    logger.debug("Connection closed.");
                    if (connection != null) {
                        connection.close();//用完一定要close这个链接！！！
                    }
                }
            }
            logger.debug("Keys gotten!");
            return keys;
            //return cluster.hkeys(pattern);
        } catch (Exception e) {
            logger.error("根据key名称模糊获取key列表失败", e);
            return null;
        } finally {

        }
    }

    /**
     * 存放Map<String, String>类型数据
     *
     * @param key
     * @param dataMap
     * @return
     */
    public boolean setMapData(String key, Map<String, String> dataMap) {
        try {
            String result = getCluster().hmset(key, dataMap);
            return "OK".equals(result) ? true : false;
        } catch (Exception e) {
            logger.error("集群模式设置map失败", e);
            try {
                String result = getCluster().hmset(key, dataMap);
                return "OK".equals(result) ? true : false;
            } catch (Exception e1) {
                logger.error("集群模式设置二次map失败", e);
            }
            return false;
        }
    }

    /**
     * 获取map类型数据（获取整个map）
     *
     * @param key
     * @return 没有获取到数据返回[], 异常返回null
     */
    public Map<String, String> getMapDataAll(String key) {
        try {
            return getCluster().hgetAll(key);
        } catch (Exception e) {
            logger.error("根据key获取map失败", e);
            try {
                return getCluster().hgetAll(key);
            } catch (Exception e1) {
                logger.error("集群模式第二次根据key获取map失败", e);
                return null;
            }
        }
    }

    /**
     * 从map中获取数据
     *
     * @param key
     * @param field
     * @return 异常返回null, 正常返回返回集合长度等于filed长度，且返回的list是按顺序获取的，如果可以对应的value存在则该list中对应的值为null</br>
     * map={'a'='a1','b'='b1'}  获取 a c 则返回[a1,null]
     */
    public List<String> getMapData(String key, String... field) {
        if (field == null || field.length == 0) {
            return new ArrayList<>();
        }
        try {
            List<String> hmget = getCluster().hmget(key, field);
            return hmget;
        } catch (Exception e) {
            logger.error("集群模式第一次获取map数据失败", e);
            try {
                List<String> hmget = getCluster().hmget(key, field);
                return hmget;
            } catch (Exception e1) {
                logger.error("集群模式第二次获取map数据失败", e);
                return new ArrayList<>();
            }
        }
    }


    /**
     * 设置时长
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        try {
            return getCluster().expire(key, seconds);
        } catch (Exception e) {
            logger.error("根据key名称设置expire失败", e);
            try {
                return getCluster().expire(key, seconds);
            } catch (Exception e1) {
                logger.error("第二次根据key名称设置expire失败", e);
                return null;
            }
        } finally {

        }
    }

    /**
     * 获取redis List 集合
     *
     * @param key
     * @return
     */
    public List<String> getList(String key, long start, long end) {
        try {
            if (start < 0 || end < start) {
                return null;
            }
            List<String> list = getCluster().lrange(key, start, end);
            return list;
        } catch (Exception e) {
            logger.error("集群模式第一次获取list数据失败", e);
            try {
                List<String> list = getCluster().lrange(key, start, end);
                return list;
            } catch (Exception e1) {
                logger.error("集群模式第二次获取list数据失败", e);
                return new ArrayList<>();
            }
        }
    }


    /**
     * 设置 redis List 集合
     *
     * @param key
     * @return
     */
    public long setList(String key, List<String> value) {
        try {
            if (value == null) {
                return 0;
            }
            long result = getCluster().lpush(key, value.toArray(new String[]{}));
            return result;
        } catch (Exception e) {
            logger.error("集群模式第一次获取list数据失败", e);
            try {
                long result = getCluster().lpush(key, value.toArray(new String[]{}));
                return result;
            } catch (Exception e1) {
                logger.error("集群模式第二次获取list数据失败", e);
                return 0;
            }
        }
    }


    /**
     * 获取redis List 集合 长度
     *
     * @param key
     * @return
     */

    public Long getListLength(String key) {
        try {
            if (key == null) {
                return null;
            }
            long result = getCluster().llen(key);
            return result;
        } catch (Exception e) {
            logger.error("集群模式第一次获取list数据失败", e);
            try {
                long result = getCluster().llen(key);
                return result;
            } catch (Exception e1) {
                logger.error("集群模式第二次获取list数据失败", e);
                return null;
            }
        }
    }

    /**
     * 设置list对象(如果list已经存在，再次放入会失败) 保存时间为指定时长
     *
     * @param key
     * @param list
     * @param expire 保存时长单位秒
     * @return
     */
    @Override
    public <T> boolean setListObj(String key, List<T> list, int expire) {
        if (StringUtil.isEmptyTrim(key) || list == null || list.size() == 0) {
            return false;
        }
        String value = JSONArray.toJSONString(list);
        SetParams params = new SetParams();
        params.ex(expire);
        params.nx();//不加,可以重复加锁(此行执行后才可以正常锁)
        try {
            String returnStr = getCluster().set(key, value, params);
            return "OK".equals(returnStr) ? true : false;
        } catch (Exception e) {
            logger.error("集群模式第一次设置list对象失败", e);
            try {
                String returnStr = getCluster().set(key, value, params);
                return "OK".equals(returnStr) ? true : false;
            } catch (Exception e1) {
                logger.error("集群模式第二次设置list对象失败", e);
                return false;
            }
        }
    }



    /**
     * 设置list对象
     *
     * @param key
     * @param list
     * @return
     */
    @Override
    public boolean setListObj(String key, List<Object> list) {
        if (StringUtil.isEmptyTrim(key) || list == null || list.size() == 0) {
            return false;
        }
        String value = JSONArray.toJSONString(list);
        try {
            String returnStr = getCluster().set(key, value);
            return "OK".equals(returnStr) ? true : false;
        } catch (Exception e) {
            logger.error("集群模式第一次设置list对象失败", e);
            try {
                String returnStr = getCluster().set(key, value);
                return "OK".equals(returnStr) ? true : false;
            } catch (Exception e1) {
                logger.error("集群模式第二次设置list对象失败", e);
                return false;
            }
        }
    }


    /**
     * 获取List
     *
     * @param key
     * @param clazz
     * @return
     */
    @Override
    public <T> List<T> getListObj(String key, Class<T> clazz) {
        if (StringUtil.isEmptyTrim(key) || clazz == null) {
            return null;
        }
        try {
            String value = getCluster().get(key);
            List<T> t = JSONArray.parseArray(value, clazz);
            return t;
        } catch (Exception e) {
            logger.error("集群模式第一次获取list对象失败", e);
            try {
                String value = getCluster().get(key);
                List<T> t = JSONArray.parseArray(value, clazz);
                return t;
            } catch (Exception e1) {
                logger.error("集群模式第二次获取list对象失败", e);
                return null;
            }
        }

    }


    public static void main(String[] args) {

    }

}
