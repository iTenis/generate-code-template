package com.ymm.activity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author liujiejian
 */
public class CommonPageResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 结果集
     */
    private List<T> pageList;
    /**
     * 页大小
     */
    private Integer pageSize;
    /**
     * 起始页 从1开始
     */
    private Integer startPage;
    /**
     * 总记录的条数
     */
    private Long totalResults;
    /**
     * 总页数
     */
    private Integer totalPages;

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public CommonPageResponseDTO() {
    }

    public CommonPageResponseDTO(List<T> pageList, Integer pageSize, Integer startPage, Long totalResults) {
        super();
        this.pageList = pageList;
        this.pageSize = pageSize;
        this.startPage = startPage;
        this.totalResults = totalResults;
        Long l = this.totalResults / this.pageSize;
        this.totalPages = l.intValue();
        if (totalPages * pageSize < totalResults) {
            totalPages++;
        }
    }
}
