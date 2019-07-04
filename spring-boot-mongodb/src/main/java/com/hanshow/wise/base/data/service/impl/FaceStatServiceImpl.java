package com.hanshow.wise.base.data.service.impl;

import com.hanshow.wise.base.data.model.sensor.FaceStat;
import com.hanshow.wise.base.data.service.FaceStatRepository;
import com.hanshow.wise.base.data.service.FaceStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaceStatServiceImpl implements FaceStatService {

    private static Logger logger = LoggerFactory.getLogger(FaceStatServiceImpl.class);

    @Autowired
    private FaceStatRepository faceStatRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 根据设备编号获取人脸统计信息
     *
     * @param deviceId
     * @return
     */
    @Override
    public List<FaceStat> findByDeviceId(String deviceId) {
        return null;
    }

    /**
     * 根据sensorId获取人脸统计信息
     *
     * @param sensorId
     * @return
     */
    @Override
    public List<FaceStat> findBySensorId(String sensorId) {
        return null;
    }
}
