package com.hanshow.wise.base.data.service.impl;

import com.hanshow.wise.base.data.model.User;
import com.hanshow.wise.base.data.service.UserRepository;
import com.hanshow.wise.base.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-06-05 14:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {

        userRepository.save(user);

    }

    @Override
    public User findByName(String name) {
        return this.findByName(name);
    }

}
