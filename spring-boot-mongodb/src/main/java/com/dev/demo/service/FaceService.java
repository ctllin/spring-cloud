package com.dev.demo.service;

import com.dev.demo.model.sensor.FaceInfo;
import com.dev.demo.model.sensor.FaceJsonRootBean;
import com.dev.demo.model.sensor.FaceStat;
import com.hanshow.wise.common.jo.BaseQUERY;

import java.util.List;

public interface FaceService {
    /**
     * 保存人脸相关信息
     *
     * @param record
     */
    void save(BaseQUERY<FaceJsonRootBean> record);

    /**
     * 根据设备编号获取人脸信息
     *
     * @param deviceId
     * @return
     */
    List<FaceInfo> getFaceInfoByDeviceId(String deviceId);

    /**
     * 根据设备编号获取统计信息
     *
     * @param deviceId
     * @return
     */
    List<FaceStat> getFaceStatByDeviceId(String deviceId);
}
