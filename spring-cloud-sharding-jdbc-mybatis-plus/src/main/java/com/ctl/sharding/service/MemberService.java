package com.ctl.sharding.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctl.sharding.entity.Member;
import com.ctl.sharding.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created with Intellij IDEA.
 * @author ctl
 * @version 2019/03/14
 */
@Service
public class MemberService implements IService<Member> {
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public boolean save(Member entity) {
        return memberMapper.insert(entity) == 1 ? true : false;
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
        return memberMapper.selectById(id);
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

//    @Transactional(rollbackFor = Exception.class)
//    public boolean save(Member member) {
//        return super.insertOrUpdate(member);
//    }
}