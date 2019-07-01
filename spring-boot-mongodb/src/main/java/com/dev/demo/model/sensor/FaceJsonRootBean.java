package com.dev.demo.model.sensor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "deviceId",
        "customer"
})
public class FaceJsonRootBean implements Serializable {
    public FaceJsonRootBean() {
    }

    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("customer")
    private Customer customer;


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
     * @return The customer
     */
    @JsonProperty("customer")
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer The customer
     */
    @JsonProperty("customer")
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public static void main(String[] args) {
        String srcJson ="{\n" +
                "  \"deviceId\": \"200119090000008888\",\n" +
                "  \"customer\": {\n" +
                "    \"faceInfo\": [\n" +
                "      {\n" +
                "        \"age\": \"26 \",\n" +
                "        \"gender\": \"0\",\n" +
                "        \"faceId\": \"4.0\",\n" +
                "        \"faceVec\": \"\",\n" +
                "        \"stayTiming\": \"15121\",\n" +
                "        \"firstIn\": \"1561701671490\",\n" +
                "        \"count\": \"-1\",\n" +
                "        \"faceArea\": \"51884\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"faceStat\": {\n" +
                "      \"siteCount\": \"25\",\n" +
                "      \"positiveCount\": \"29\",\n" +
                "      \"newFaceCount\": \"16\",\n" +
                "      \"oldFaceCount\": \"23\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        FaceJsonRootBean rootBean = JSON.parseObject(srcJson.toString(), FaceJsonRootBean.class);
        System.out.println(rootBean);
    }
}