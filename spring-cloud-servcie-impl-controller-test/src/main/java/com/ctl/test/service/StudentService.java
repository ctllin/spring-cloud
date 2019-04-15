package com.ctl.test.service;

import com.ctl.test.model.Student;

public interface StudentService {
    Student getStudentBySid(int sid);

    boolean addStudent(Student student);

    boolean modifyPerson(Student student);

    Student delPerson(Integer sid);
}
