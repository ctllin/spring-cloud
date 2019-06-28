package com.dev.demo.service.impl;

import com.dev.demo.model.sensor.Customer;
import com.dev.demo.model.sensor.FaceInfo;
import com.dev.demo.model.sensor.FaceJsonRootBean;
import com.dev.demo.model.sensor.FaceStat;
import com.dev.demo.service.FaceInfoRepository;
import com.dev.demo.service.FaceService;
import com.dev.demo.service.FaceStatRepository;
import com.hanshow.wise.common.jo.BaseQUERY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaceServiceImpl implements FaceService {
    private static Logger logger = LoggerFactory.getLogger(FaceServiceImpl.class);
    @Autowired
    private FaceInfoRepository faceInfoRepository;
    @Autowired
    private FaceStatRepository faceStatRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void save(BaseQUERY<FaceJsonRootBean> record) {
        if(record.getData()==null){
            logger.error("face data is null");
            return;
        }
        FaceJsonRootBean faceJsonRootBean = record.getData();
        Customer customer = faceJsonRootBean.getCustomer();
        FaceStat faceStat = customer.getFaceStat();
        faceStat.setDeviceId(faceJsonRootBean.getDeviceId());
        faceStatRepository.save(faceStat);
        List<FaceInfo> faceInfoList = customer.getFaceInfo();
        if(faceInfoList!=null){
            faceInfoList.parallelStream().forEach(faceInfo -> {
                faceInfo.setDeviceId(faceJsonRootBean.getDeviceId());
                faceInfoRepository.save(faceInfo);
            });
        }
    }

    @Override
    public List<FaceInfo> getFaceInfoByDeviceId(String deviceId) {
        return null;
    }

    @Override
    public List<FaceStat> getFaceStatByDeviceId(String deviceId) {
        return null;
    }
}
