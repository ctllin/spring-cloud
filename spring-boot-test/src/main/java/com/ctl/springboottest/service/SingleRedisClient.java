package com.ctl.springboottest.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ctl.springboottest.util.ConfigUtils;
import com.ctl.springboottest.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.util.*;

/**
 * <p>
 * Title: RedisUtil
 * </p>
 * <p>
 * Description: redis工具类
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
public class SingleRedisClient implements RedisClient {
    static Logger logger = LoggerFactory.getLogger(RedisClient.class);
    //public  Jedis jedis=redisInstances();
    public static JedisPool pool = null;
    public static Map<String, String> setDataMap = new HashMap<>();
    public static final Integer SECURITY_KEY_EXPIRE_TIME = 24 * 60 * 60 * 31;

    static {
        getPool();
    }

    public static void initializePool() {
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
            pool = new JedisPool(config, ConfigUtils.getType("redis.host"), Integer.parseInt(ConfigUtils.getType("redis.port")), 10000, ConfigUtils.getType("redis.auth"));
            logger.info("redisType={},#1普通2集群", redisType);
        } catch (NumberFormatException e) {
            logger.error("初始化redis失败-single", e);
        }
    }

    /**
     * 多线程环境同步初始化（保证项目中有且仅有一个连接池）
     */
    public static synchronized void poolInit() {
        if (pool == null) {
            initializePool();
        }
    }

    /**
     * 构建redis连接池 使用过得redis连接直接调用close方法关闭(使用后必须关闭不可以使用pool.returnResource(redis))
     *
     * @return JedisPool
     */
    public static JedisPool getPool() {
        try {
            if (pool == null) {
                poolInit();
            }
            if (pool != null) {
                logger.info("redis连接池参数如下：numActive={},numIdle={},numWaiters={}", pool.getNumActive(), pool.getNumIdle(), pool.getNumWaiters());
            }
            return pool;
        } catch (Exception e) {
            logger.error("获取redis链接池失败", e);
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            String returnStr = redis.set(key, value);
            redis.expire(key, expire);
            return "OK".equals(returnStr) ? true : false;
        } catch (Exception e) {
            logger.error("设置字符串失败", e);
            return false;
        } finally {
            if (redis != null) {
                redis.close();
            }
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
        Jedis redis = null;
        try {
            String value = JSON.toJSONString(obj);
            redis = getPool().getResource();
            String returnStr = redis.set(key, value);
            redis.expire(key, expire);
            return "OK".equals(returnStr) ? true : false;
        } catch (Exception e) {
            logger.error("设置对象失败", e);
            return false;
        } finally {
            if (redis != null) {
                redis.close();
            }
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            String returnStr = redis.set(key, value);
            return "OK".equals(returnStr) ? true : false;
        } catch (Exception e) {
            logger.error("设置字符串失败失败", e);
            return false;
        } finally {
            if (redis != null) {
                redis.close();
            }
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
        {
            if (StringUtil.isEmptyTrim(key)) {
                logger.info("key={} is null", key);
                return false;
            }
            if (obj == null) {
                logger.info("obj={} is null", obj);
                return false;
            }
            Jedis redis = null;
            try {
                String value = JSON.toJSONString(obj);
                redis = getPool().getResource();
                String returnStr = redis.set(key, value);
                return "OK".equals(returnStr) ? true : false;
            } catch (Exception e) {
                logger.error("设置对象失败", e);
                return false;
            } finally {
                if (redis != null) {
                    redis.close();
                }
            }
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            long result = redis.setnx(key, value);
            return 1==result ? true : false;
        } catch (Exception e) {
            logger.error("设置字符串失败失败nx", e);
            return false;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    /**
     * 根据key删除字符串
     *
     * @param key
     * @return 成功返回1失败返回0异常返回-1(没有该key删除返回0)
     */

    public long delString(String key) {
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            return redis.expire(key, 0);
        } catch (Exception e) {
            logger.error("根据key删除字符串失败", e);
            return -1;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */

    public String getString(String key) {
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            return redis.get(key);
        } catch (Exception e) {
            logger.error("获取字符串失败", e);
            return null;
        } finally {
            if (redis != null) {
                redis.close();
            }
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            String value = redis.get(key);
            if(StringUtil.isEmptyTrim(value)){
                return null;
            }
            T t = JSON.toJavaObject((JSON) JSON.parse(value), clazz);
            return t;
        } catch (Exception e) {
            logger.error("获取字符串失败", e);
            return null;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    /**
     * 根据key名称模糊获取key列表
     *
     * @param pattern 例如member*
     * @return
     */
    public Set<String> getKeySet(String pattern) {
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            return redis.keys(pattern);
        } catch (Exception e) {
            logger.error("根据key名称模糊获取key列表失败", e);
            return null;
        } finally {
            if (redis != null) {
                redis.close();
            }
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            String result = redis.hmset(key, dataMap);
            return "OK".equals(result) ? true : false;
        } catch (Exception e) {
            logger.error("向指定map中增加数据失败", e);
            return false;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }


    /**
     * 获取map类型数据（获取整个map）
     *
     * @param key
     * @return 没有获取到数据返回[], 异常返回null
     */
    public Map<String, String> getMapDataAll(String key) {
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            return redis.hgetAll(key);
        } catch (Exception e) {
            logger.error("根据key获取map失败", e);
            return null;
        } finally {
            if (redis != null) {
                redis.close();
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            List<String> hmget = redis.hmget(key, field);
            return hmget;
        } catch (Exception e) {
            logger.error("向指定map中获取数据失败", e);
            return null;
        } finally {
            if (redis != null) {
                redis.close();
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            return redis.expire(key, seconds);
        } catch (Exception e) {
            logger.error("根据key名称设置expire失败", e);
            return -1L;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    /**
     * 获取redis List 集合
     *
     * @param key
     * @return
     */
    public List<String> getList(String key, long start, long end) {
        Jedis redis = null;
        try {
            if (start < 0 || end < start) {
                return null;
            }
            redis = getPool().getResource();
            return redis.lrange(key, start, end);
        } catch (Exception e) {
            logger.error("根据key获取list失败", e);
            return null;
        } finally {
            if (redis != null) {
                redis.close();
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
        Jedis redis = null;
        try {
            if (value == null || value.size() == 0) {
                logger.error("根据key获取list失败value={}", value);
                return 0;
            }
            redis = getPool().getResource();
            return redis.lpush(key, value.toArray(new String[]{}));
        } catch (Exception e) {
            logger.error("根据key获取list失败", e);
            return 0;
        } finally {
            if (redis != null) {
                redis.close();
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            return redis.llen(key);
        } catch (Exception e) {
            logger.error("根据key获取list失败", e);
            return null;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    /**
     * 设置list对象 保存时间为指定时长
     *
     * @param key
     * @param list
     * @param expire 保存时长单位秒
     * @return
     */
    @Override
    public <T> boolean setListObj(String key, List<T> list, int expire) {
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            if (StringUtil.isEmptyTrim(key) || list == null || list.size() == 0) {
                return false;
            }
            String value = JSONArray.toJSONString(list);
            SetParams params = new SetParams();
            params.ex(expire);
            params.nx();//不加,可以重复加锁(此行执行后才可以正常锁)
            String returnStr = redis.set(key, value, params);
            return "OK".equals(returnStr) ? true : false;
        } catch (Exception e) {
            logger.error("根据key获取list失败", e);
            return false;
        } finally {
            if (redis != null) {
                redis.close();
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            if (StringUtil.isEmptyTrim(key) || list == null || list.size() == 0) {
                return false;
            }
            String value = JSONArray.toJSONString(list);
            SetParams params = new SetParams();
            params.nx();//不加,可以重复加锁(此行执行后才可以正常锁)
            String returnStr = redis.set(key, value, params);
            return "OK".equals(returnStr) ? true : false;
        } catch (Exception e) {
            logger.error("根据key获取list失败", e);
            return false;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    /**
     * 获取List 对象
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
        Jedis redis = null;
        try {
            redis = getPool().getResource();
            String value = redis.get(key);
            List<T> t = JSONArray.parseArray(value, clazz);
            return t;
        } catch (Exception e) {
            return null;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    public static void main(String[] args) {

    }

}
