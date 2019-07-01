package com.dev.demo.service;

import com.dev.demo.model.sensor.FaceJsonRootBean;
import com.dev.demo.model.sensor.FaceStat;
import com.hanshow.wise.common.jo.BaseQUERY;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaceStatService {
//    /**
//     * 保存人脸统计信息
//     * @param record
//     */
//    void save(BaseQUERY<FaceJsonRootBean> record);

    /**
     * 根据设备编号获取人脸统计信息
     * @param deviceId
     * @return
     */
    List<FaceStat> findByDeviceId(String deviceId);
    /**
     * 根据sensorId获取人脸统计信息
     * @param sensorId
     * @return
     */
    List<FaceStat> findBySensorId(String sensorId);
}
