package com.hanshow.wise.base.data.service;

import com.hanshow.wise.base.data.model.TRoot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TRootRepository extends MongoRepository<TRoot, String> {
}
