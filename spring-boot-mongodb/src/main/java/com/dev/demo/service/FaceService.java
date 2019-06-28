package com.dev.demo.service;

import com.dev.demo.model.sensor.FaceInfo;
import com.dev.demo.model.sensor.FaceJsonRootBean;
import com.dev.demo.model.sensor.FaceStat;
import com.hanshow.wise.common.jo.BaseQUERY;

import java.util.List;

public interface FaceService {
    void save(BaseQUERY<FaceJsonRootBean> record);

    List<FaceInfo> getFaceInfoByDeviceId(String deviceId);

    List<FaceStat> getFaceStatByDeviceId(String deviceId);
}
