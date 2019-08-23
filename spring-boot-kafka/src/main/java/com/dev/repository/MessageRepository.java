package com.dev.repository;

import com.dev.bean.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>Title: MessageRepository</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-23 09:33
 */
public interface MessageRepository extends MongoRepository<Message, Long> {
}
