package com.dev.demo.service;

import com.dev.demo.model.sensor.LightCurtainData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LightCurtainRepository extends MongoRepository<LightCurtainData, String> {
}
