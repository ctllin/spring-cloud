package com.dev.demo.service.impl;

import com.dev.demo.model.sensor.FaceJsonRootBean;
import com.dev.demo.model.sensor.FaceStat;
import com.dev.demo.service.FaceStatRepository;
import com.dev.demo.service.FaceStatService;
import com.hanshow.wise.common.jo.BaseQUERY;
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
