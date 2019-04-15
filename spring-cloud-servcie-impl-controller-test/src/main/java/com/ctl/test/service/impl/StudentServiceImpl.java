package com.ctl.test.service.impl;

import com.ctl.test.mapper.StudentMapper;
import com.ctl.test.model.Student;
import com.ctl.test.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Random;

@Service
public class StudentServiceImpl implements StudentService {
    static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    @Autowired(required = false)
    private StudentMapper studentMapper;

    @Override
    public Student getStudentBySid(int sid) {
        return studentMapper.selectByPrimaryKey(sid);
    }

    @PostConstruct
    public void init() {
        logger.info("StudentServiceImpl初始化完毕,bean创建后执行PostConstruct");
    }

    @Override
    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }
        student.setId(null);
        student.setUpdatetime(new Date());
        logger.info("" + 2 / new Random().nextInt(2)); //此句子执行后失败，事务回滚
        return studentMapper.insertSelective(student) == 1 ? true : false;
    }

    @Override
    public boolean modifyPerson(Student student) {
        if (student == null || student.getId() == null) {
            return false;
        }
        return studentMapper.updateByPrimaryKeySelective(student) == 1 ? true : false;
    }

    @Override
    public Student delPerson(Integer sid) {
        Student student = studentMapper.selectByPrimaryKey(sid);
        studentMapper.deleteByPrimaryKey(sid);
        return student;
    }
}
