package com.hanshow.wise.base.data.service;

import com.hanshow.wise.base.data.model.sensor.FaceInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FaceInfoRepository extends MongoRepository<FaceInfo, String> {
}
