package com.dev.demo.service;

import com.dev.demo.model.sensor.FaceJsonRootBean;
import com.dev.demo.model.sensor.FaceStat;
import com.hanshow.wise.common.jo.BaseQUERY;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaceInfoService {
    void save(BaseQUERY<FaceJsonRootBean> record);
    List<FaceStat> findByDeviceId(String deviceId);
    List<FaceStat> findByFaceId(String faceId);
}
