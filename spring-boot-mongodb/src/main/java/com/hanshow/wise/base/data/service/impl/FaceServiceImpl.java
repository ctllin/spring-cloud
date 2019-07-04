package com.hanshow.wise.base.data.service.impl;

import com.hanshow.wise.base.data.model.sensor.CameraData;
import com.hanshow.wise.base.data.model.sensor.FaceInfo;
import com.hanshow.wise.base.data.model.sensor.FaceJsonRootBean;
import com.hanshow.wise.base.data.model.sensor.FaceStat;
import com.dev.demo.service.*;
import com.hanshow.wise.base.data.service.FaceInfoRepository;
import com.hanshow.wise.base.data.service.FaceService;
import com.hanshow.wise.base.data.service.FaceStatRepository;
import com.hanshow.wise.common.jo.BaseQUERY;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 保存人脸相关信息
     *
     * @param record
     */
    @Override
    @Transactional
    public void save(BaseQUERY<FaceJsonRootBean> record) {
        if (record == null || record.getData() == null) {
            logger.error("face data is null");
            return;
        }
        FaceJsonRootBean faceJsonRootBean = record.getData();
        CameraData customer = faceJsonRootBean.getCameraData();
        FaceStat faceStat = customer.getFaceStat();
        faceStat.setDeviceId(faceJsonRootBean.getDeviceId());
        faceStat.setSensorId(customer.getSensorId());
        faceStatRepository.save(faceStat);
        if (true) {
            int i = 0 / 1;
        }
        List<FaceInfo> faceInfoList = customer.getFaceInfo();
        if (faceInfoList != null) {
            faceInfoList.parallelStream().forEach(faceInfo -> {
                faceInfo.setDeviceId(faceJsonRootBean.getDeviceId());
                faceInfo.setSensorId(customer.getSensorId());
                faceInfoRepository.save(faceInfo);
            });
        }
    }

    /**
     * 根据设备编号获取人脸信息
     *
     * @param deviceId
     * @return
     */
    @Override
    public List<FaceInfo> getFaceInfoByDeviceId(String deviceId) {
        return null;
    }

    /**
     * 根据设备编号获取统计信息
     *
     * @param deviceId
     * @return
     */
    @Override
    public List<FaceStat> getFaceStatByDeviceId(String deviceId) {
        return null;
    }


}
