package com.hanshow.wise.base.data.service;

import com.hanshow.wise.base.data.model.sensor.LightCurtainData;
import com.hanshow.wise.base.data.model.sensor.LightCurtainJsonRootBean;
import com.hanshow.wise.common.jo.BaseQUERY;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LightCurtainService {
    void save(BaseQUERY<LightCurtainJsonRootBean> record);
    List<LightCurtainData> findByDeviceId(String deviceId);
    List<LightCurtainData> findBySensorId(String sensorId);
}
