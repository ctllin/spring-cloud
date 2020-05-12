package com.ctl.springclouddubbohystrix.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: Role</p>
 * <p>Description:角色信息 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 * @author hanshow
 * @date 2019-04-25 14:04:25
 * @version 1.0
 */
public class Role implements Serializable {


    /**
     * 主键
     */
    private String id;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 使用类型；1:wiseB,wiseC
     */
    private String appId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 顺序
     */
    private Integer seq;

    /**
     * 是否是基础数据，基础数据不可以删除编辑1是2否
     */
    private Byte defaultData;

    /**
     * 使用状态；1：启用；2：禁用
     */
    private Byte useStatus;

    /**
     * 描述
     */
    private String depict;

    /**
     * 授权可见状态;1:可见；0不可见
     */
    private Byte state;

    /**
     * 
     */
    private String remarks;

    /**
     * 添加时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 删除标识 0:正常 1:删除 3:假删除
     */
    private Byte delFlag;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Byte getDefaultData() {
        return defaultData;
    }

    public void setDefaultData(Byte defaultData) {
        this.defaultData = defaultData;
    }

    public Byte getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Byte useStatus) {
        this.useStatus = useStatus;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict == null ? null : depict.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", appId=").append(appId);
        sb.append(", name=").append(name);
        sb.append(", seq=").append(seq);
        sb.append(", defaultData=").append(defaultData);
        sb.append(", useStatus=").append(useStatus);
        sb.append(", depict=").append(depict);
        sb.append(", state=").append(state);
        sb.append(", remarks=").append(remarks);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}