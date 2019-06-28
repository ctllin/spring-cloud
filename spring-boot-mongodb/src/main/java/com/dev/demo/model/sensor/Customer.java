package com.dev.demo.model.sensor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "faceInfo",
        "faceStat"
})
public class Customer {

    @JsonProperty("faceInfo")
    private List<FaceInfo> faceInfo = new ArrayList<>();
    @JsonProperty("faceStat")
    private FaceStat faceStat;


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


}