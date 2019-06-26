package com.dev.demo.service;

import com.dev.demo.model.sensor.LightCurtainData;
import com.dev.demo.model.sensor.LightCurtainJsonRootBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LightCurtainService {
    void save(LightCurtainJsonRootBean record);
    List<LightCurtainData> findByDeviceId(String deviceId);
    List<LightCurtainData> findBySensorId(String sensorId);
}
