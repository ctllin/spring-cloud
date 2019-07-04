package com.hanshow.wise.base.data.service;

import com.hanshow.wise.base.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>Title: UserRepository</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-06-05 14:07
 */
public interface UserRepository extends MongoRepository<User, String> {


    User findByName(String name);

}