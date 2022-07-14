package com.wyu.zmall.vo;

import com.wyu.zmall.model.Category;
import com.wyu.zmall.model.Coupon;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zwx
 * @date 2022-07-12 22:24
 */
@Data
public class CouponVO {
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

    public CouponVO(Coupon coupon) {
        BeanUtils.copyProperties(coupon, this);
    }

    public CouponVO() {

    }

}
