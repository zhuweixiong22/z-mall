package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author zwx
 * @date 2022-07-12 16:50
 */
@Entity
@Data
public class Activity extends BaseEntry{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    private String description;

    private Date startTime;

    private Date endTime;

    private String remark;

    private Boolean online;

    private String entranceImg;

    private String internalTopImg;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId")
    private List<Coupon> couponList;

}
