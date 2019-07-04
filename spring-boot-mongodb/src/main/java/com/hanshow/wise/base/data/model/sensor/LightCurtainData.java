package com.hanshow.wise.base.data.model.sensor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sensorId",
        "position",
        "timestamp"
})
@Data
@AllArgsConstructor
@ToString
public class LightCurtainData implements Serializable {
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
    /**
     * 传感器出发点坐标
     */
    @JsonProperty("position")
    private List<Integer> position = new ArrayList<>();
    /**
     * 时间戳
     */
    @JsonProperty("timestamp")
    private String timestamp;

    public LightCurtainData() {
    }

    /**
     * @return The sensorId
     */
    @JsonProperty("sensorId")
    public String getSensorId() {
        return sensorId;
    }

    /**
     * @param sensorId The sensorId
     */
    @JsonProperty("sensorId")
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * @return The position
     */
    @JsonProperty("position")
    public List<Integer> getPosition() {
        return position;
    }

    /**
     * @param position The position
     */
    @JsonProperty("position")
    public void setPosition(List<Integer> position) {
        this.position = position;
    }

    /**
     * @return The timestamp
     */
    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp
     */
    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}