package com.wyu.zmall.vo;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 自己封装Page对象
 * @author zwx
 * @date 2022-07-04 21:03
 */
public class PageVO<T> {
    private Long total;

    private Integer pageNum;

    private Integer pageSize;

    private Integer totalPageSize;

    private List<T> items;

    public PageVO(Page<T> page) {
        this.initPageParam(page);
        this.items = page.getContent();
    }

    /**
     * 抽出来复用
     * @param page
     */
    public void initPageParam(Page<T> page) {
        this.total = page.getTotalElements();
        this.pageSize = page.getSize();
        this.pageNum = page.getNumber();
        this.totalPageSize = page.getTotalPages();
    }

    public PageVO() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(Integer totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
