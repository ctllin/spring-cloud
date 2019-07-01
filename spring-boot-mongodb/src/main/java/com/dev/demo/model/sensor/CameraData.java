package com.dev.demo.model.sensor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "faceInfo",
        "faceStat",
        "sensorId"
})
public class CameraData implements Serializable {
    public CameraData() {
    }

    @JsonProperty("faceInfo")
    private List<FaceInfo> faceInfo = new ArrayList<>();
    @JsonProperty("faceStat")
    private FaceStat faceStat;
    @JsonProperty("sensorId")
    private String sensorId;

    /**
     * @return The faceInfo
     */
    @JsonProperty("faceInfo")
    public List<FaceInfo> getFaceInfo() {
        return faceInfo;
    }

    /**
     * @param faceInfo The faceInfo
     */
    @JsonProperty("faceInfo")
    public void setFaceInfo(List<FaceInfo> faceInfo) {
        this.faceInfo = faceInfo;
    }

    /**
     * @return The faceStat
     */
    @JsonProperty("faceStat")
    public FaceStat getFaceStat() {
        return faceStat;
    }

    /**
     * @param faceStat The faceStat
     */
    @JsonProperty("faceStat")
    public void setFaceStat(FaceStat faceStat) {
        this.faceStat = faceStat;
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

}