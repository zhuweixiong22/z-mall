package com.wyu.zmall.vo;

import com.wyu.zmall.model.Activity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwx
 * @date 2022-07-12 21:24
 */
@Data
public class ActivityCouponVO extends ActivityVO {
    private List<CouponVO> couponVOList;

    /**
     * TODO 这里需要处理循环序列化的问题 借助一个CouponVO对象 List<CouponVO>
     * 最好不要在VO对象里写业务
     */
    public ActivityCouponVO(Activity activity) {
        super(activity);

        couponVOList = new ArrayList<>();
        activity.getCouponList().forEach(coupon -> {
            CouponVO couponVO = new CouponVO();
            BeanUtils.copyProperties(coupon, couponVO);
            couponVOList.add(couponVO);
        });
    }

    public ActivityCouponVO() {

    }
}
