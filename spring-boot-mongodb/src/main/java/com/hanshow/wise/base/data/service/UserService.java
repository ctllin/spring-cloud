package com.hanshow.wise.base.data.service;


import com.hanshow.wise.base.data.model.User;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-06-05 14:05
 */
@Repository
public interface UserService {
    void save(User user);

    User findByName(String name);
}
