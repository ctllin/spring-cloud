package com.hanshow.wise.base.lience.model;

import java.io.Serializable;

/**
 * <p>Title: HanshowLicence</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-04-30 14:06
 */
public class HanshowLicence implements Serializable{
    /**
     * 门店数量
     */
    private Integer storeNum;
    /**设备数量
     *
     */
    private Integer deviceNum;

    public HanshowLicence() {
    }

    public HanshowLicence(Integer storeNum, Integer deviceNum) {
        this.storeNum = storeNum;
        this.deviceNum = deviceNum;
    }

    public Integer getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(Integer storeNum) {
        this.storeNum = storeNum;
    }

    public Integer getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }
}
