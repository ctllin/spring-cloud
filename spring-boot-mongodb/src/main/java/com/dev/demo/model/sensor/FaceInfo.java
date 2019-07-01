package com.dev.demo.model.sensor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "age",
        "gender",
        "faceId",
        "faceVec",
        "stayTiming",
        "firstIn",
        "count",
        "faceArea"
})
@Data
@AllArgsConstructor
@ToString
public class FaceInfo implements Serializable {
    public FaceInfo() {
    }

    /**
     * 设备id
     */
    @JsonProperty("deviceId")
    private String deviceId;
    /**
     * 传感器id
     */
    @JsonProperty("sensorId")
    private String sensorId;
    @JsonProperty("age")
    private String age;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("faceId")
    private String faceId;
    /**
     * 面部角度
     */
    @JsonProperty("faceVec")
    private List<Double> faceVec = new ArrayList<>();
    /**
     * 停留时间
     */
    @JsonProperty("stayTiming")
    private String stayTiming;
    /**
     * 第一次人脸出现的时间戳
     */
    @JsonProperty("firstIn")
    private String firstIn;
    /**
     * 未用
     */
    @JsonProperty("count")
    private String count;
    /**
     * 人脸面部大小
     */
    @JsonProperty("faceArea")
    private String faceArea;

    /**
     * @return The age
     */
    @JsonProperty("age")
    public String getAge() {
        return age;
    }

    /**
     * @param age The age
     */
    @JsonProperty("age")
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return The gender
     */
    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return The faceId
     */
    @JsonProperty("faceId")
    public String getFaceId() {
        return faceId;
    }

    /**
     * @param faceId The faceId
     */
    @JsonProperty("faceId")
    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    /**
     * @return The faceVec
     */
    @JsonProperty("faceVec")
    public List<Double> getFaceVec() {
        return faceVec;
    }

    /**
     * @param faceVec The faceVec
     */
    @JsonProperty("faceVec")
    public void setFaceVec(List<Double> faceVec) {
        this.faceVec = faceVec;
    }

    /**
     * @return The stayTiming
     */
    @JsonProperty("stayTiming")
    public String getStayTiming() {
        return stayTiming;
    }

    /**
     * @param stayTiming The stayTiming
     */
    @JsonProperty("stayTiming")
    public void setStayTiming(String stayTiming) {
        this.stayTiming = stayTiming;
    }

    /**
     * @return The firstIn
     */
    @JsonProperty("firstIn")
    public String getFirstIn() {
        return firstIn;
    }

    /**
     * @param firstIn The firstIn
     */
    @JsonProperty("firstIn")
    public void setFirstIn(String firstIn) {
        this.firstIn = firstIn;
    }

    /**
     * @return The count
     */
    @JsonProperty("count")
    public String getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    @JsonProperty("count")
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return The faceArea
     */
    @JsonProperty("faceArea")
    public String getFaceArea() {
        return faceArea;
    }

    /**
     * @param faceArea The faceArea
     */
    @JsonProperty("faceArea")
    public void setFaceArea(String faceArea) {
        this.faceArea = faceArea;
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