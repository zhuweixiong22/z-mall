package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zwx
 * @date 2022-07-12 16:50
 */
@Entity
@Data
public class Coupon extends BaseEntry{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    private Date startTime;

    private Date endTime;

    private Integer validTime;

    private String description;

    private BigDecimal fullMoney;

    private BigDecimal minus;

    private BigDecimal rate;

    private Integer type;

    private Long activityId;

    private String remark;

    private Boolean wholeStore;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "couponList")
    private List<Category> categoryList;
}
