package com.hanshow.wise.base.data.service.impl;

import com.hanshow.wise.base.data.model.sensor.FaceInfo;
import com.hanshow.wise.base.data.service.FaceInfoRepository;
import com.hanshow.wise.base.data.service.FaceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FaceInfoServiceImpl implements FaceInfoService {

    private static Logger logger = LoggerFactory.getLogger(FaceInfoServiceImpl.class);

    @Autowired
    private FaceInfoRepository faceInfoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;



    /**
     * 根据设备编号获取人脸信息
     *
     * @param deviceId
     * @return
     */
    @Override
    public List<FaceInfo> findByDeviceId(String deviceId) {
        return null;
    }

    /**
     * 根据faceId获取人脸信息
     *
     * @param faceId
     * @return
     */
    @Override
    public List<FaceInfo> findByFaceId(String faceId) {
        return null;
    }

    /**
     * 根据sensorId获取人脸信息
     *
     * @param sensorId
     * @return
     */
    @Override
    public List<FaceInfo> findBySensorId(String sensorId) {
        return null;
    }
}
