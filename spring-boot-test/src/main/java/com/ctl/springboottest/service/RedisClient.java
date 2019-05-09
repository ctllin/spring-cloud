package com.ctl.springboottest.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis接口类
 *
 * @author wuqingsong
 */
public interface RedisClient {
    /**
     * 设置字符串 保存时间为制定时长
     *
     * @param key
     * @param value
     * @param expire 保存时长单位秒
     * @return
     */
    boolean setString(String key, String value, int expire);

    /**
     * 设置字符串 一直存在
     *
     * @param key
     * @param value
     * @return
     */
    boolean setString(String key, String value);

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 根据key删除字符串
     *
     * @param key
     * @return 成功返回1失败返回0异常返回-1(没有该key删除返回0)
     */
    long delString(String key);

    /**
     * 根据key名称模糊获取key列表
     *
     * @param pattern 例如member*
     * @return
     */
    Set<String> getKeySet(String pattern);

    /**
     * 存放Map<String, String>类型数据
     *
     * @param key
     * @param dataMap
     * @return
     */
    boolean setMapData(String key, Map<String, String> dataMap);

    /**
     * 获取map类型数据（获取整个map）
     *
     * @param key
     * @return 没有获取到数据返回[], 异常返回null
     */
    Map<String, String> getMapDataAll(String key);

    /**
     * 从map中获取数据
     *
     * @param key
     * @param field
     * @return 异常返回null, 正常返回返回集合长度等于filed长度，且返回的list是按顺序获取的，如果可以对应的value存在则该list中对应的值为null</br>
     * map={'a'='a1','b'='b1'}  获取 a c 则返回[a1,null]
     */
    List<String> getMapData(String key, String... field);

    /**
     * 设置时长
     *
     * @param key
     * @param seconds
     * @return
     */
    Long expire(String key, int seconds);

    /**
     * 获取redis List 集合
     *
     * @param key
     * @return
     */
    List<String> getList(String key, long start, long end);

    /**
     * 设置 redis List 集合
     *
     * @param key
     * @return
     */
    long setList(String key, List<String> value);

    /**
     * 获取redis List 集合 长度
     *
     * @param key
     * @return
     */

    Long getListLength(String key);
}
