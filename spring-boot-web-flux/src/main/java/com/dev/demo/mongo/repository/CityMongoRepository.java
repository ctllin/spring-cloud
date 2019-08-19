package com.dev.demo.mongo.repository;

import com.dev.demo.bean.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: CityRepository</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-19 14:58
 */
@Repository
public interface CityMongoRepository extends ReactiveMongoRepository<City, Long> {
}
