package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author zwx
 * @date 2022-07-12 20:39
 */
@Entity
@Table(name = "user_coupon", schema = "z-mall", catalog = "")
@Data
public class UserCoupon {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long userId;

    private Long couponId;

    private Integer status;

    private Date createTime;

    private Long orderId;

    private Date updateTime;

}
