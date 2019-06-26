package com.dev.demo.model.sensor;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "deviceId",
        "lightCurtainData"
})
public class LightCurtainJsonRootBean implements Serializable{

    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("lightCurtainData")
    private LightCurtainData lightCurtainData;


    /**
     * @return The deviceId
     */
    @JsonProperty("deviceId")
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId The deviceId
     */
    @JsonProperty("deviceId")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return The lightCurtainData
     */
    @JsonProperty("lightCurtainData")
    public LightCurtainData getLightCurtainData() {
        return lightCurtainData;
    }

    /**
     * @param lightCurtainData The lightCurtainData
     */
    @JsonProperty("lightCurtainData")
    public void setLightCurtainData(LightCurtainData lightCurtainData) {
        this.lightCurtainData = lightCurtainData;
    }

    public static void main(String[] args) {
        String srcJson ="{\n" +
                "  \"deviceId\": \"200119090000008888\",\n" +
                "  \"lightCurtainData\": {\n" +
                "    \"sensorId\": \"04FF128106\",\n" +
                "    \"position\": [\n" +
                "      7,\n" +
                "      15\n" +
                "    ],\n" +
                "    \"timestamp\": \"1561192318389\"\n" +
                "  }\n" +
                "}";
        LightCurtainJsonRootBean rootBean = JSON.parseObject(srcJson.toString(), LightCurtainJsonRootBean.class);
        System.out.println(rootBean);
    }

}
