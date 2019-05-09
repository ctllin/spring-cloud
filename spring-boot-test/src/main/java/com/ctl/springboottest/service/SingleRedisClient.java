package com.ctl.springboottest.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
public class SingleRedisClient implements RedisClient {


    /**
     * 设置字符串 保存时间为制定时长
     *
     * @param key
     * @param value
     * @param expire 保存时长单位秒
     * @return
     */
    @Override
    public boolean setString(String key, String value, int expire) {
        return false;
    }

    /**
     * 设置字符串 一直存在
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean setString(String key, String value) {
        return false;
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        return null;
    }

    /**
     * 根据key删除字符串
     *
     * @param key
     * @return 成功返回1失败返回0异常返回-1(没有该key删除返回0)
     */
    @Override
    public long delString(String key) {
        return 0;
    }

    /**
     * 根据key名称模糊获取key列表
     *
     * @param pattern 例如member*
     * @return
     */
    @Override
    public Set<String> getKeySet(String pattern) {
        return null;
    }

    /**
     * 存放Map<String, String>类型数据
     *
     * @param key
     * @param dataMap
     * @return
     */
    @Override
    public boolean setMapData(String key, Map<String, String> dataMap) {
        return false;
    }

    /**
     * 获取map类型数据（获取整个map）
     *
     * @param key
     * @return 没有获取到数据返回[], 异常返回null
     */
    @Override
    public Map<String, String> getMapDataAll(String key) {
        return null;
    }

    /**
     * 从map中获取数据
     *
     * @param key
     * @param field
     * @return 异常返回null, 正常返回返回集合长度等于filed长度，且返回的list是按顺序获取的，如果可以对应的value存在则该list中对应的值为null</br>
     * map={'a'='a1','b'='b1'}  获取 a c 则返回[a1,null]
     */
    @Override
    public List<String> getMapData(String key, String... field) {
        return null;
    }

    /**
     * 设置时长
     *
     * @param key
     * @param seconds
     * @return
     */
    @Override
    public Long expire(String key, int seconds) {
        return null;
    }

    /**
     * 获取redis List 集合
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<String> getList(String key, long start, long end) {
        return null;
    }

    /**
     * 设置 redis List 集合
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public long setList(String key, List<String> value) {
        return 0;
    }

    /**
     * 获取redis List 集合 长度
     *
     * @param key
     * @return
     */
    @Override
    public Long getListLength(String key) {
        return null;
    }
}
