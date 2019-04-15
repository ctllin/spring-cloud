package com.ctl.test.service;

import com.ctl.test.model.Person;

/**
 * <p>Title: PersonServcie</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-09 15:13
 */
public interface PersonService {
    /**
     * 根据pid获取person
     * @param pid
     * @return
     */
    Person getPersonByPid(int pid);

    boolean addPerson(Person person);

    boolean modifyPerson(Person person);

    Person delPerson(Integer pid);
}
