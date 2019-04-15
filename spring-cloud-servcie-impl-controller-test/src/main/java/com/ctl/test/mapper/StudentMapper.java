package com.ctl.test.mapper;

import com.ctl.test.model.Student;
import com.ctl.test.model.StudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(StudentExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(StudentExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(Student record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Student record);

    /**
     *
     * @mbg.generated
     */
    List<Student> selectByExample(StudentExample example);

    /**
     *
     * @mbg.generated
     */
    Student selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Student record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Student record);
}