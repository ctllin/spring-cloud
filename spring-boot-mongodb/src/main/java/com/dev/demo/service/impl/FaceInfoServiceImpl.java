package com.dev.demo.service.impl;

import com.dev.demo.model.sensor.FaceJsonRootBean;
import com.dev.demo.model.sensor.FaceStat;
import com.dev.demo.service.FaceInfoRepository;
import com.dev.demo.service.FaceInfoService;
import com.hanshow.wise.common.jo.BaseQUERY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FaceInfoServiceImpl implements FaceInfoService{

    private static Logger logger = LoggerFactory.getLogger(FaceInfoServiceImpl.class);

    @Autowired
    private FaceInfoRepository faceInfoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(BaseQUERY<FaceJsonRootBean> record) {

    }

    @Override
    public List<FaceStat> findByDeviceId(String deviceId) {
        return null;
    }

    @Override
    public List<FaceStat> findByFaceId(String faceId) {
        return null;
    }
}
