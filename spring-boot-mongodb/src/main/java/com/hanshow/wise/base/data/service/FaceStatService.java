package com.hanshow.wise.base.data.service;

import com.hanshow.wise.base.data.model.sensor.FaceStat;
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
