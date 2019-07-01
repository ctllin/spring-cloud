package com.dev.demo.model.sensor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 人脸统计数据
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "siteCount",
        "positiveCount",
        "newFaceCount",
        "oldFaceCount"
})
@Data
@AllArgsConstructor
@ToString
public class FaceStat implements Serializable{
    public FaceStat() {
    }

    /**
     * 设备id
     */
    @JsonProperty("deviceId")
    private String deviceId;
    /**
     * 人员侧脸经过数量
     */
    @JsonProperty("siteCount")
    private String siteCount;
    /**
     * 人员正脸的数量
     */
    @JsonProperty("positiveCount")
    private String positiveCount;
    /**
     * 正脸里新客户数量
     */
    @JsonProperty("newFaceCount")
    private String newFaceCount;
    /**
     * 正脸里老客户数量
     */
    @JsonProperty("oldFaceCount")
    private String oldFaceCount;

    /**
     * @return The siteCount
     */
    @JsonProperty("siteCount")
    public String getSiteCount() {
        return siteCount;
    }

    /**
     * @param siteCount The siteCount
     */
    @JsonProperty("siteCount")
    public void setSiteCount(String siteCount) {
        this.siteCount = siteCount;
    }

    /**
     * @return The positiveCount
     */
    @JsonProperty("positiveCount")
    public String getPositiveCount() {
        return positiveCount;
    }

    /**
     * @param positiveCount The positiveCount
     */
    @JsonProperty("positiveCount")
    public void setPositiveCount(String positiveCount) {
        this.positiveCount = positiveCount;
    }

    /**
     * @return The newFaceCount
     */
    @JsonProperty("newFaceCount")
    public String getNewFaceCount() {
        return newFaceCount;
    }

    /**
     * @param newFaceCount The newFaceCount
     */
    @JsonProperty("newFaceCount")
    public void setNewFaceCount(String newFaceCount) {
        this.newFaceCount = newFaceCount;
    }

    /**
     * @return The oldFaceCount
     */
    @JsonProperty("oldFaceCount")
    public String getOldFaceCount() {
        return oldFaceCount;
    }

    /**
     * @param oldFaceCount The oldFaceCount
     */
    @JsonProperty("oldFaceCount")
    public void setOldFaceCount(String oldFaceCount) {
        this.oldFaceCount = oldFaceCount;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}