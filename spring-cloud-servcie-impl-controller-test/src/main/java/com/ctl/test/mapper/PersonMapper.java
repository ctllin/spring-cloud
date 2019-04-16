package com.ctl.test.mapper;

import com.ctl.test.model.Person;
import com.ctl.test.model.PersonExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(PersonExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(PersonExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(Person record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Person record);

    /**
     *
     * @mbg.generated
     */
    List<Person> selectByExample(PersonExample example);

    /**
     *
     * @mbg.generated
     */
    Person selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Person record, @Param("example") PersonExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Person record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Person record);
}