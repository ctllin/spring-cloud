package com.dev.demo.model.query;

import com.dev.demo.model.constant.CurtainConstant;

import java.io.Serializable;

public class PageQuery implements Serializable {
    /**
     * 当前页数
     */
    private Integer currentPage;
    /**
     * 每页条数
     */
    private Integer pageSize;

    public Integer getCurrentPage() {
        return currentPage == null ? CurtainConstant.CURRENTPAGE : currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize == null ? CurtainConstant.PAGESIZE : (pageSize >= CurtainConstant.MAX_PAGESIZE ? CurtainConstant.MAX_PAGESIZE : pageSize);
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
