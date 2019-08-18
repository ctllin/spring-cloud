package com.hanshow.wise.base.data.service;

import com.hanshow.wise.base.data.model.sensor.FaceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaceInfoService {
//    /**
//     * 保存人脸信息
//     * @param record
//     */
//    void save(BaseQUERY<FaceJsonRootBean> record);

    /**
     * 根据设备编号获取人脸信息
     * @param deviceId
     * @return
     */
    List<FaceInfo> findByDeviceId(String deviceId);
    /**
     * 根据faceId获取人脸信息
     * @param faceId
     * @return
     */
    List<FaceInfo> findByFaceId(String faceId);
    /**
     * 根据sensorId获取人脸信息
     * @param sensorId
     * @return
     */
    List<FaceInfo> findBySensorId(String sensorId);
}
