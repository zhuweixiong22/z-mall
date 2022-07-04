package com.wyu.zmall.bo;

/**
 * BO 对象：业务对象
 * 一般是业务层和控制层的传输，比较灵活，业务中需要的对象
 * @author zwx
 * @date 2022-07-04 20:11
 */
public class PageCounter {

    private Integer pageNum;

    private Integer pageSize;

    public PageCounter(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
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
}
