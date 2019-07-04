package com.hanshow.wise.base.data.model.query;



public class LightCurtainDataQuery {
    /**
     * 主键
     */
    private Long id;
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 传感器id
     */
    private String sensorId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }


}
