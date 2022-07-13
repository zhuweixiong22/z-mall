package com.wyu.zmall.service;

import com.wyu.zmall.model.Coupon;

import java.util.Date;
import java.util.List;

/**
 * @author zwx
 * @date 2022-07-13 14:26
 */
public interface CouponService {
    List<Coupon> getByCategoryId(Long cid);

    List<Coupon> getWholeStore();

    void collectOneCoupon(Long uid, Long id);

    List<Coupon> getCouponsByStatus(Long uid, Integer status);

    List<Coupon> getMyAvailableCoupons(Long uid);

    List<Coupon> getMyUsedCoupons(Long uid);

    List<Coupon> getMyExpiredCoupons(Long uid);
}
