package com.wyu.zmall.api.v1;

import com.wyu.zmall.core.annotations.ScopeLevel;
import com.wyu.zmall.model.Coupon;
import com.wyu.zmall.service.CouponService;
import com.wyu.zmall.vo.CouponVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<CouponVO> getWholeStoreCoupon() {
        List<Coupon> couponList = this.couponService.getWholeStore();
        List<CouponVO> couponVOList = new ArrayList<>();
        couponList.forEach(coupon -> {
            CouponVO couponVO = new CouponVO();
            BeanUtils.copyProperties(coupon, couponVO);
            couponVOList.add(couponVO);
        });
        return couponVOList;
    }

    @ScopeLevel()
    @PostMapping("/collect/{id}")
    public void collectCoupon(@PathVariable Long id) {
        // 用户uid通过token来获取，是不能通过前端显式传过来的，超权问题，防止用户操作其他用户的值

    }
}
