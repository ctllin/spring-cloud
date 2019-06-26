package com.dev.demo.model.query;


import com.dev.demo.model.constant.LightCurtainConstant;

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
    /**
     * 当前页数
     */
    private Integer currentPage;
    /**
     * 每页条数
     */
    private Integer pageSize;

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

    public Integer getCurrentPage() {
        return currentPage == null ? LightCurtainConstant.CURRENTPAGE : currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize == null ? LightCurtainConstant.PAGESIZE : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
