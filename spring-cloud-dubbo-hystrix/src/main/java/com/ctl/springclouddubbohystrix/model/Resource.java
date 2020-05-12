package com.ctl.springclouddubbohystrix.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: Resource</p>
 * <p>Description:资源信息 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 * @author hanshow
 * @date 2019-04-25 14:04:25
 * @version 1.0
 */
public class Resource implements Serializable {

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
     * 资源名称
     */
    private String name;

    /**
     * 前端name国际化key值(不能重复)
     */
    private String nameInternationalization;

    /**
     * 资源路径(不能为空唯一)
     */
    private String url;

    /**
     * 跳转地址,前端路由或者app活动页面
     */
    private String skipPath;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 资源图标(css样式，图片地址，均可)
     */
    private String icon;

    /**
     * 1:图片2:css
     */
    private Byte iconType;

    /**
     * 父节点id
     */
    private String pid;

    /**
     * 顺序
     */
    private Integer seq;

    /**
     * 授权可见状态;1:可见；0不可见
     */
    private Byte state;

    /**
     * 资源类型；1菜单导航；2按钮 ;3其他;11APP菜单；12APP按钮
     */
    private Byte resourceType;

    /**
     * 是否是基础数据，基础数据不可以删除编辑1是2否
     */
    private Byte defaultData;

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

    public String getNameInternationalization() {
        return nameInternationalization;
    }

    public void setNameInternationalization(String nameInternationalization) {
        this.nameInternationalization = nameInternationalization == null ? null : nameInternationalization.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getSkipPath() {
        return skipPath;
    }

    public void setSkipPath(String skipPath) {
        this.skipPath = skipPath == null ? null : skipPath.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Byte getIconType() {
        return iconType;
    }

    public void setIconType(Byte iconType) {
        this.iconType = iconType;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getResourceType() {
        return resourceType;
    }

    public void setResourceType(Byte resourceType) {
        this.resourceType = resourceType;
    }

    public Byte getDefaultData() {
        return defaultData;
    }

    public void setDefaultData(Byte defaultData) {
        this.defaultData = defaultData;
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
        sb.append(", nameInternationalization=").append(nameInternationalization);
        sb.append(", url=").append(url);
        sb.append(", skipPath=").append(skipPath);
        sb.append(", description=").append(description);
        sb.append(", icon=").append(icon);
        sb.append(", iconType=").append(iconType);
        sb.append(", pid=").append(pid);
        sb.append(", seq=").append(seq);
        sb.append(", state=").append(state);
        sb.append(", resourceType=").append(resourceType);
        sb.append(", defaultData=").append(defaultData);
        sb.append(", remarks=").append(remarks);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


}