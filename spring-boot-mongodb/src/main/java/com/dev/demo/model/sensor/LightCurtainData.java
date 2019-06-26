package com.dev.demo.model.sensor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sensorId",
        "position",
        "timestamp"
})
public class LightCurtainData {

    @JsonProperty("sensorId")
    private String sensorId;
    @JsonProperty("position")
    private List<Integer> position = new ArrayList<Integer>();
    @JsonProperty("timestamp")
    private String timestamp;


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


}