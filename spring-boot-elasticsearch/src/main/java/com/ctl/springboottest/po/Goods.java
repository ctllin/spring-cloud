package com.ctl.springboottest.po;

/**
 * com.ctl.springboottest.po
 * Goods
 * ctl 2019/4/14 15:50
 */

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.elasticsearch.annotations.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "merchant_id",
        "sku",
        "plu_code",
        "code",
        "name",
        "simple_name",
        "brand_code",
        "brand_name",
        "unit",
        "specification",
        "grade",
        "pack_size",
        "manufacturer",
        "manufacturer_address",
        "place_of_origin",
        "description",
        "img_url",
        "packing_flag",
        "process_flag",
        "raw_materials_flag",
        "type",
        "storage_mode",
        "suttle",
        "weight",
        "retail_above",
        "retail_below",
        "category_id",
        "category_code",
        "category_name",
        "route_category_code",
        "route_category_name",
        "cost_price",
        "market_price",
        "suggested_price",
        "group_buy_price",
        "proposal_purchase_price",
        "keep_days",
        "keep_hour",
        "state",
        "remove_flag",
        "del_flag",
        "gmt_create",
        "gmt_modified"
})


@Document(indexName = "goods",type = "store_goods", shards = 1,replicas = 0, refreshInterval = "-1")
public class Goods {

    @JsonProperty("id")
    private String id;
    @JsonProperty("merchant_id")
    private String merchantId;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("plu_code")
    private String pluCode;
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("simple_name")
    private String simpleName;
    @JsonProperty("brand_code")
    private String brandCode;
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("specification")
    private String specification;
    @JsonProperty("grade")
    private String grade;
    @JsonProperty("pack_size")
    private String packSize;
    @JsonProperty("manufacturer")
    private String manufacturer;
    @JsonProperty("manufacturer_address")
    private String manufacturerAddress;
    @JsonProperty("place_of_origin")
    private String placeOfOrigin;
    @JsonProperty("description")
    private String description;
    @JsonProperty("img_url")
    private String imgUrl;
    @JsonProperty("packing_flag")
    private String packingFlag;
    @JsonProperty("process_flag")
    private String processFlag;
    @JsonProperty("raw_materials_flag")
    private String rawMaterialsFlag;
    @JsonProperty("type")
    private String type;
    @JsonProperty("storage_mode")
    private String storageMode;
    @JsonProperty("suttle")
    private String suttle;
    @JsonProperty("weight")
    private String weight;
    @JsonProperty("retail_above")
    private String retailAbove;
    @JsonProperty("retail_below")
    private String retailBelow;
    @JsonProperty("category_id")
    private String categoryId;
    @JsonProperty("category_code")
    private String categoryCode;
    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("route_category_code")
    private String routeCategoryCode;
    @JsonProperty("route_category_name")
    private String routeCategoryName;
    @JsonProperty("cost_price")
    private String costPrice;
    @JsonProperty("market_price")
    private String marketPrice;
    @JsonProperty("suggested_price")
    private String suggestedPrice;
    @JsonProperty("group_buy_price")
    private String groupBuyPrice;
    @JsonProperty("proposal_purchase_price")
    private String proposalPurchasePrice;
    @JsonProperty("keep_days")
    private String keepDays;
    @JsonProperty("keep_hour")
    private String keepHour;
    @JsonProperty("state")
    private String state;
    @JsonProperty("remove_flag")
    private String removeFlag;
    @JsonProperty("del_flag")
    private String delFlag;
    @JsonProperty("gmt_create")
    private String gmtCreate;
    @JsonProperty("gmt_modified")
    private String gmtModified;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private Integer currentPage;
    private Integer pageSize;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The merchantId
     */
    @JsonProperty("merchant_id")
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId The merchant_id
     */
    @JsonProperty("merchant_id")
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return The sku
     */
    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    /**
     * @param sku The sku
     */
    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return The pluCode
     */
    @JsonProperty("plu_code")
    public String getPluCode() {
        return pluCode;
    }

    /**
     * @param pluCode The plu_code
     */
    @JsonProperty("plu_code")
    public void setPluCode(String pluCode) {
        this.pluCode = pluCode;
    }

    /**
     * @return The code
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The simpleName
     */
    @JsonProperty("simple_name")
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * @param simpleName The simple_name
     */
    @JsonProperty("simple_name")
    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    /**
     * @return The brandCode
     */
    @JsonProperty("brand_code")
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * @param brandCode The brand_code
     */
    @JsonProperty("brand_code")
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    /**
     * @return The brandName
     */
    @JsonProperty("brand_name")
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName The brand_name
     */
    @JsonProperty("brand_name")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return The unit
     */
    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit The unit
     */
    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return The specification
     */
    @JsonProperty("specification")
    public String getSpecification() {
        return specification;
    }

    /**
     * @param specification The specification
     */
    @JsonProperty("specification")
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /**
     * @return The grade
     */
    @JsonProperty("grade")
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade The grade
     */
    @JsonProperty("grade")
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return The packSize
     */
    @JsonProperty("pack_size")
    public String getPackSize() {
        return packSize;
    }

    /**
     * @param packSize The pack_size
     */
    @JsonProperty("pack_size")
    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    /**
     * @return The manufacturer
     */
    @JsonProperty("manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer The manufacturer
     */
    @JsonProperty("manufacturer")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return The manufacturerAddress
     */
    @JsonProperty("manufacturer_address")
    public String getManufacturerAddress() {
        return manufacturerAddress;
    }

    /**
     * @param manufacturerAddress The manufacturer_address
     */
    @JsonProperty("manufacturer_address")
    public void setManufacturerAddress(String manufacturerAddress) {
        this.manufacturerAddress = manufacturerAddress;
    }

    /**
     * @return The placeOfOrigin
     */
    @JsonProperty("place_of_origin")
    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    /**
     * @param placeOfOrigin The place_of_origin
     */
    @JsonProperty("place_of_origin")
    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    /**
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The imgUrl
     */
    @JsonProperty("img_url")
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl The img_url
     */
    @JsonProperty("img_url")
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return The packingFlag
     */
    @JsonProperty("packing_flag")
    public String getPackingFlag() {
        return packingFlag;
    }

    /**
     * @param packingFlag The packing_flag
     */
    @JsonProperty("packing_flag")
    public void setPackingFlag(String packingFlag) {
        this.packingFlag = packingFlag;
    }

    /**
     * @return The processFlag
     */
    @JsonProperty("process_flag")
    public String getProcessFlag() {
        return processFlag;
    }

    /**
     * @param processFlag The process_flag
     */
    @JsonProperty("process_flag")
    public void setProcessFlag(String processFlag) {
        this.processFlag = processFlag;
    }

    /**
     * @return The rawMaterialsFlag
     */
    @JsonProperty("raw_materials_flag")
    public String getRawMaterialsFlag() {
        return rawMaterialsFlag;
    }

    /**
     * @param rawMaterialsFlag The raw_materials_flag
     */
    @JsonProperty("raw_materials_flag")
    public void setRawMaterialsFlag(String rawMaterialsFlag) {
        this.rawMaterialsFlag = rawMaterialsFlag;
    }

    /**
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The storageMode
     */
    @JsonProperty("storage_mode")
    public String getStorageMode() {
        return storageMode;
    }

    /**
     * @param storageMode The storage_mode
     */
    @JsonProperty("storage_mode")
    public void setStorageMode(String storageMode) {
        this.storageMode = storageMode;
    }

    /**
     * @return The suttle
     */
    @JsonProperty("suttle")
    public String getSuttle() {
        return suttle;
    }

    /**
     * @param suttle The suttle
     */
    @JsonProperty("suttle")
    public void setSuttle(String suttle) {
        this.suttle = suttle;
    }

    /**
     * @return The weight
     */
    @JsonProperty("weight")
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight The weight
     */
    @JsonProperty("weight")
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return The retailAbove
     */
    @JsonProperty("retail_above")
    public String getRetailAbove() {
        return retailAbove;
    }

    /**
     * @param retailAbove The retail_above
     */
    @JsonProperty("retail_above")
    public void setRetailAbove(String retailAbove) {
        this.retailAbove = retailAbove;
    }

    /**
     * @return The retailBelow
     */
    @JsonProperty("retail_below")
    public String getRetailBelow() {
        return retailBelow;
    }

    /**
     * @param retailBelow The retail_below
     */
    @JsonProperty("retail_below")
    public void setRetailBelow(String retailBelow) {
        this.retailBelow = retailBelow;
    }

    /**
     * @return The categoryId
     */
    @JsonProperty("category_id")
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId The category_id
     */
    @JsonProperty("category_id")
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return The categoryCode
     */
    @JsonProperty("category_code")
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * @param categoryCode The category_code
     */
    @JsonProperty("category_code")
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * @return The categoryName
     */
    @JsonProperty("category_name")
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName The category_name
     */
    @JsonProperty("category_name")
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return The routeCategoryCode
     */
    @JsonProperty("route_category_code")
    public String getRouteCategoryCode() {
        return routeCategoryCode;
    }

    /**
     * @param routeCategoryCode The route_category_code
     */
    @JsonProperty("route_category_code")
    public void setRouteCategoryCode(String routeCategoryCode) {
        this.routeCategoryCode = routeCategoryCode;
    }

    /**
     * @return The routeCategoryName
     */
    @JsonProperty("route_category_name")
    public String getRouteCategoryName() {
        return routeCategoryName;
    }

    /**
     * @param routeCategoryName The route_category_name
     */
    @JsonProperty("route_category_name")
    public void setRouteCategoryName(String routeCategoryName) {
        this.routeCategoryName = routeCategoryName;
    }

    /**
     * @return The costPrice
     */
    @JsonProperty("cost_price")
    public String getCostPrice() {
        return costPrice;
    }

    /**
     * @param costPrice The cost_price
     */
    @JsonProperty("cost_price")
    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * @return The marketPrice
     */
    @JsonProperty("market_price")
    public String getMarketPrice() {
        return marketPrice;
    }

    /**
     * @param marketPrice The market_price
     */
    @JsonProperty("market_price")
    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * @return The suggestedPrice
     */
    @JsonProperty("suggested_price")
    public String getSuggestedPrice() {
        return suggestedPrice;
    }

    /**
     * @param suggestedPrice The suggested_price
     */
    @JsonProperty("suggested_price")
    public void setSuggestedPrice(String suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    /**
     * @return The groupBuyPrice
     */
    @JsonProperty("group_buy_price")
    public String getGroupBuyPrice() {
        return groupBuyPrice;
    }

    /**
     * @param groupBuyPrice The group_buy_price
     */
    @JsonProperty("group_buy_price")
    public void setGroupBuyPrice(String groupBuyPrice) {
        this.groupBuyPrice = groupBuyPrice;
    }

    /**
     * @return The proposalPurchasePrice
     */
    @JsonProperty("proposal_purchase_price")
    public String getProposalPurchasePrice() {
        return proposalPurchasePrice;
    }

    /**
     * @param proposalPurchasePrice The proposal_purchase_price
     */
    @JsonProperty("proposal_purchase_price")
    public void setProposalPurchasePrice(String proposalPurchasePrice) {
        this.proposalPurchasePrice = proposalPurchasePrice;
    }

    /**
     * @return The keepDays
     */
    @JsonProperty("keep_days")
    public String getKeepDays() {
        return keepDays;
    }

    /**
     * @param keepDays The keep_days
     */
    @JsonProperty("keep_days")
    public void setKeepDays(String keepDays) {
        this.keepDays = keepDays;
    }

    /**
     * @return The keepHour
     */
    @JsonProperty("keep_hour")
    public String getKeepHour() {
        return keepHour;
    }

    /**
     * @param keepHour The keep_hour
     */
    @JsonProperty("keep_hour")
    public void setKeepHour(String keepHour) {
        this.keepHour = keepHour;
    }

    /**
     * @return The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The removeFlag
     */
    @JsonProperty("remove_flag")
    public String getRemoveFlag() {
        return removeFlag;
    }

    /**
     * @param removeFlag The remove_flag
     */
    @JsonProperty("remove_flag")
    public void setRemoveFlag(String removeFlag) {
        this.removeFlag = removeFlag;
    }

    /**
     * @return The delFlag
     */
    @JsonProperty("del_flag")
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag The del_flag
     */
    @JsonProperty("del_flag")
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return The gmtCreate
     */
    @JsonProperty("gmt_create")
    public String getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate The gmt_create
     */
    @JsonProperty("gmt_create")
    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return The gmtModified
     */
    @JsonProperty("gmt_modified")
    public String getGmtModified() {
        return gmtModified;
    }

    /**
     * @param gmtModified The gmt_modified
     */
    @JsonProperty("gmt_modified")
    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(merchantId).append(sku).append(pluCode).append(code).append(name).append(simpleName).append(brandCode).append(brandName).append(unit).append(specification).append(grade).append(packSize).append(manufacturer).append(manufacturerAddress).append(placeOfOrigin).append(description).append(imgUrl).append(packingFlag).append(processFlag).append(rawMaterialsFlag).append(type).append(storageMode).append(suttle).append(weight).append(retailAbove).append(retailBelow).append(categoryId).append(categoryCode).append(categoryName).append(routeCategoryCode).append(routeCategoryName).append(costPrice).append(marketPrice).append(suggestedPrice).append(groupBuyPrice).append(proposalPurchasePrice).append(keepDays).append(keepHour).append(state).append(removeFlag).append(delFlag).append(gmtCreate).append(gmtModified).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Goods) == false) {
            return false;
        }
        Goods rhs = ((Goods) other);
        return new EqualsBuilder().append(id, rhs.id).append(merchantId, rhs.merchantId).append(sku, rhs.sku).append(pluCode, rhs.pluCode).append(code, rhs.code).append(name, rhs.name).append(simpleName, rhs.simpleName).append(brandCode, rhs.brandCode).append(brandName, rhs.brandName).append(unit, rhs.unit).append(specification, rhs.specification).append(grade, rhs.grade).append(packSize, rhs.packSize).append(manufacturer, rhs.manufacturer).append(manufacturerAddress, rhs.manufacturerAddress).append(placeOfOrigin, rhs.placeOfOrigin).append(description, rhs.description).append(imgUrl, rhs.imgUrl).append(packingFlag, rhs.packingFlag).append(processFlag, rhs.processFlag).append(rawMaterialsFlag, rhs.rawMaterialsFlag).append(type, rhs.type).append(storageMode, rhs.storageMode).append(suttle, rhs.suttle).append(weight, rhs.weight).append(retailAbove, rhs.retailAbove).append(retailBelow, rhs.retailBelow).append(categoryId, rhs.categoryId).append(categoryCode, rhs.categoryCode).append(categoryName, rhs.categoryName).append(routeCategoryCode, rhs.routeCategoryCode).append(routeCategoryName, rhs.routeCategoryName).append(costPrice, rhs.costPrice).append(marketPrice, rhs.marketPrice).append(suggestedPrice, rhs.suggestedPrice).append(groupBuyPrice, rhs.groupBuyPrice).append(proposalPurchasePrice, rhs.proposalPurchasePrice).append(keepDays, rhs.keepDays).append(keepHour, rhs.keepHour).append(state, rhs.state).append(removeFlag, rhs.removeFlag).append(delFlag, rhs.delFlag).append(gmtCreate, rhs.gmtCreate).append(gmtModified, rhs.gmtModified).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
