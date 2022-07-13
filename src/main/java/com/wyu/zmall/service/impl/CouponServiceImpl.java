package com.wyu.zmall.service.impl;

import com.wyu.zmall.enums.CouponStatus;
import com.wyu.zmall.enums.ResultEnum;
import com.wyu.zmall.exception.http.HttpException;
import com.wyu.zmall.model.Activity;
import com.wyu.zmall.model.Coupon;
import com.wyu.zmall.model.UserCoupon;
import com.wyu.zmall.repository.ActivityRepository;
import com.wyu.zmall.repository.CouponRepository;
import com.wyu.zmall.repository.UserCouponRepository;
import com.wyu.zmall.service.CouponService;
import com.wyu.zmall.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

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

    @Override
    public void collectOneCoupon(Long uid, Long id) {
        // 1、只需要校验这个id对应的优惠券是否存在也不需要接收，uid因为不是从前端传过来的，不用校验
        this.couponRepository.findById(id)
                .orElseThrow(() -> new HttpException(ResultEnum.COUPON_NOT_FOUND.getCode(), ResultEnum.COUPON_NOT_FOUND.getDesc(), HttpStatus.NOT_FOUND.value()));
        // 2、该优惠券存在还要判断是否能领取，领取时间是由活动时间决定的（优惠券是基于活动的）
        // 查询活动
        Activity activity = this.activityRepository.findByCouponId(id)
                .orElseThrow(() -> new HttpException(ResultEnum.COUPON_ACTIVITY_NOT_FOUND.getCode(), ResultEnum.COUPON_ACTIVITY_NOT_FOUND.getDesc(), HttpStatus.NOT_FOUND.value()));
        Date now = new Date();
        boolean isValidTime = CommonUtil.isValidTime(now, activity.getStartTime(), activity.getEndTime());

        // 3、是否在合法领取时间
        if (!isValidTime) {
            throw new HttpException(ResultEnum.COUPON_COLLECT_TIME_ERROR.getCode(), ResultEnum.COUPON_COLLECT_TIME_ERROR.getDesc(), HttpStatus.BAD_REQUEST.value());
        }

        // 4、校验是否已经领取过优惠券 查询user_coupon中间表是否存在相同uid和id的记录
        this.userCouponRepository.findByUserIdAndCouponId(uid, id)
                .ifPresent(userCoupon -> {
                    throw new HttpException(ResultEnum.COUPON_COLLECTED.getCode(), ResultEnum.COUPON_COLLECTED.getDesc(), HttpStatus.BAD_REQUEST.value());
                });

        // 5、通过以上校验后，就可以领取优惠券
        // 领取时间我们自己填，如果交给mysql，可能会差几毫秒
        UserCoupon userCoupon = new UserCoupon(uid, id, CouponStatus.AVAILABLE.getCode(), now);
        this.userCouponRepository.save(userCoupon);
    }


    @Override
    public List<Coupon> getCouponsByStatus(Long uid, Integer status) {
        return null;
    }

    @Override
    public List<Coupon> getMyAvailableCoupons(Long uid) {
        return this.couponRepository.findMyAvailable(uid, new Date());
    }

    @Override
    public List<Coupon> getMyUsedCoupons(Long uid) {
        return this.couponRepository.findMyUsed(uid);
    }

    @Override
    public List<Coupon> getMyExpiredCoupons(Long uid) {
        return this.couponRepository.findMyExpired(uid, new Date());
    }
}
