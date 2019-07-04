package com.hanshow.wise.base.data.service;

import com.hanshow.wise.base.data.model.sensor.FaceStat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FaceStatRepository extends MongoRepository<FaceStat, String> {
}
