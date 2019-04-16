package com.ctl.springboot.atomikos.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctl.springboot.atomikos.entity.Member;
import com.ctl.springboot.atomikos.mapperbus.MemberBusMapper;
import com.ctl.springboot.atomikos.mappersys.MemberSysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

/**
 * <p>Title: ManyService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-27 14:52
 */
@Service
public class ManyService implements IService<Member> {
    @Autowired
    //@Qualifier(value ="sqlSessionFactoryBus" )
    private MemberBusMapper memberBusMapper;

    @Autowired
    //@Qualifier(value ="sqlSessionFactorySys" )
    private MemberSysMapper memberSysMapper;

    // 开启事务，由于使用jta+atomikos解决分布式事务，所以此处不必再指定事务
    @Transactional
    public int insert() {
        Member member = new Member();
        member.setName("1_" + System.currentTimeMillis());
        int insert = memberSysMapper.insert(member);
        int i = 1 / new Random().nextInt(2);// 赋值age为0故意引发事务
        return insert;
    }

    // 开启事务，由于使用jta+atomikos解决分布式事务，所以此处不必再指定事务
    @Transactional
    public Map<String, Member> insertDb1AndDb2() {
        Map<String, Member> returnMap = new HashMap<>();
        Member member = new Member();
        member.setId(""+System.currentTimeMillis()+new Random().nextInt(1000000));
        member.setName("1_" + System.currentTimeMillis());
        int insert = memberSysMapper.insert(member);
        returnMap.put("member1", member);
        Member member2 = new Member();
        member2.setId(""+System.currentTimeMillis()+new Random().nextInt(1000000));
        member2.setName("2_" + System.currentTimeMillis());
        int insert2 = memberBusMapper.insert(member2);
        returnMap.put("member2", member2);
        int i = 1 / new Random().nextInt(2);// 赋值age为0故意引发事务
        return returnMap;
    }

    @Override
    public boolean save(Member entity) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<Member> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Member> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<Member> queryWrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Override
    public boolean updateById(Member entity) {
        return false;
    }

    @Override
    public boolean update(Member entity, Wrapper<Member> updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Member> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Member entity) {
        return false;
    }

    @Override
    public Member getById(Serializable id) {
        return memberSysMapper.selectById(id);
    }

    @Override
    public Collection<Member> listByIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public Collection<Member> listByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public Member getOne(Wrapper<Member> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Member> queryWrapper) {
        return null;
    }

    @Override
    public int count(Wrapper<Member> queryWrapper) {
        return 0;
    }

    @Override
    public List<Member> list(Wrapper<Member> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Member> page(IPage<Member> page, Wrapper<Member> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<Member> queryWrapper) {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Wrapper<Member> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<Member> page, Wrapper<Member> queryWrapper) {
        return null;
    }

    @Override
    public BaseMapper<Member> getBaseMapper() {
        return null;
    }
}
