package com.hanshow.wise.base.data.service;

import com.hanshow.wise.base.data.model.sensor.LightCurtainData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LightCurtainRepository extends MongoRepository<LightCurtainData, String> {
}
