package com.dev.demo.service;

import com.dev.demo.model.sensor.FaceStat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FaceStatRepository extends MongoRepository<FaceStat, String> {
}
