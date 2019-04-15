package com.ctl.springboot.atomikos.mapperbus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctl.springboot.atomikos.entity.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created with Intellij IDEA.
 * @author ctl
 * @version 2019/03/14
 */
@Repository
public interface MemberBusMapper extends BaseMapper<Member> {

}
