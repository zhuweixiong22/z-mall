package com.wyu.zmall.api.v1;

import com.wyu.zmall.core.LocalUserThreadHolder;
import com.wyu.zmall.core.UnifyResponse;
import com.wyu.zmall.core.annotations.ScopeLevel;
import com.wyu.zmall.enums.CouponStatus;
import com.wyu.zmall.enums.ResultEnum;
import com.wyu.zmall.exception.http.HttpException;
import com.wyu.zmall.model.Coupon;
import com.wyu.zmall.service.CouponService;
import com.wyu.zmall.vo.CouponCategoryVO;
import com.wyu.zmall.vo.CouponVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zwx
 * @date 2022-07-13 14:25
 */
@Api(tags = "Coupon相关接口")
@RestController
@RequestMapping("/coupon")
@Validated
public class CouponController {

    @Autowired
    private CouponService couponService;

    @ApiOperation("根据分类获取优惠券")
    @GetMapping("/by/category/{cid}")
    public List<CouponVO> getCouponByCategoryId(@PathVariable Long cid) {
        List<Coupon> couponList = this.couponService.getByCategoryId(cid);
        List<CouponVO> couponVOList = new ArrayList<>();
        couponList.forEach(coupon -> {
            CouponVO couponVO = new CouponVO();
            BeanUtils.copyProperties(coupon, couponVO);
            couponVOList.add(couponVO);
        });
        return couponVOList;
    }

    @ApiOperation("获取所有全场券")
    @GetMapping("/whole_store")
    public List<CouponVO> getWholeStoreCoupons() {
        List<Coupon> couponList = this.couponService.getWholeStore();
        List<CouponVO> couponVOList = new ArrayList<>();
        couponList.forEach(coupon -> {
            CouponVO couponVO = new CouponVO();
            BeanUtils.copyProperties(coupon, couponVO);
            couponVOList.add(couponVO);
        });
        return couponVOList;
    }

    @ApiOperation("领取优惠券")
    @ScopeLevel()
    @PostMapping("/collect/{id}")
    public UnifyResponse collectCoupon(@PathVariable Long id) {
        // 用户uid通过token来获取，是不能通过前端显式传过来的，超权问题，防止用户操作其他用户的值
        Long uid = LocalUserThreadHolder.getLocalUserId();
        this.couponService.collectOneCoupon(uid, id);
        return UnifyResponse.success();
    }

    @ApiOperation("获取我的优惠券")
    @ScopeLevel()
    @GetMapping("/myself/by/status/{status}")
    public List<CouponVO> getMyCouponsByStatus(@PathVariable @Positive Integer status) {
        Long uid = LocalUserThreadHolder.getLocalUserId();
        // 因为引入了延迟订单支付 取消订单后如果要归还优惠券，用到了异步机制（很难保证百分之百执行成功 2->1） 所以这个status不一定是准确的
        // 触发机制
        List<Coupon> couponList;
        switch (CouponStatus.toType(status)) {
            case AVAILABLE:
                couponList = this.couponService.getMyAvailableCoupons(uid);
                break;
            case USED:
                couponList = this.couponService.getMyUsedCoupons(uid);
                break;
            case EXPIRED:
                couponList = this.couponService.getMyExpiredCoupons(uid);
                break;
            default:
                throw new HttpException(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getDesc(), HttpStatus.BAD_REQUEST.value());
        }
        List<CouponVO> couponVOList = new ArrayList<>();
        couponList.forEach(coupon -> {
            CouponVO couponVO = new CouponVO();
            BeanUtils.copyProperties(coupon, couponVO);
            couponVOList.add(couponVO);
        });
        return couponVOList;
    }

    @ApiOperation("结算页面获取用户可用优惠券(包含分类信息)")
    @ScopeLevel()
    @GetMapping("/myself/available/with_category")
    public List<CouponCategoryVO> getCouponWithCategory() {
        Long uid = LocalUserThreadHolder.getLocalUserId();
        List<Coupon> couponList = this.couponService.getMyAvailableCoupons(uid);
        // 两种流的形式，和上面那个接口
        return couponList.stream().map(coupon -> {return new CouponCategoryVO(coupon);}).collect(Collectors.toList());
    }
}
