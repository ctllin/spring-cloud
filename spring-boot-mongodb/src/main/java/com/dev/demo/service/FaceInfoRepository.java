package com.dev.demo.service;

import com.dev.demo.model.sensor.FaceInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FaceInfoRepository extends MongoRepository<FaceInfo, String> {
}
