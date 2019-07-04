package com.hanshow.wise.base.data.model.query;

public class FaceInfoQuery extends PageQuery {
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 人脸编号
     */
    private String faceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }
}
