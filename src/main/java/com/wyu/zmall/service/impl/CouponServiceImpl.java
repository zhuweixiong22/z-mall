package com.wyu.zmall.service.impl;

import com.wyu.zmall.model.Coupon;
import com.wyu.zmall.repository.CouponRepository;
import com.wyu.zmall.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zwx
 * @date 2022-07-13 14:26
 */
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public List<Coupon> getByCategoryId(Long cid) {
        // TODO 不能查询出过期的优惠券
        Date now = new Date();
        // 多对多参数
        List<Coupon> couponList = this.couponRepository.findByCategoryId(cid, now);
        return couponList;
    }

    @Override
    public List<Coupon> getWholeStore() {
        Date now = new Date();
        List<Coupon> wholeStoreCoupon = this.couponRepository.findByWholeStore(true, now);
        return wholeStoreCoupon;
    }
}
