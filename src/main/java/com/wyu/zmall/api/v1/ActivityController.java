package com.wyu.zmall.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.wyu.zmall.model.Activity;
import com.wyu.zmall.service.ActivityService;
import com.wyu.zmall.vo.ActivityCouponVO;
import com.wyu.zmall.vo.ActivityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zwx
 * @date 2022-07-12 20:52
 */
@Api(tags = "Activity相关接口")
@RestController
@RequestMapping("/activity")
@Validated
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @ApiOperation("根据名字获取主页活动信息")
    @GetMapping("/name/{name}")
    public ActivityVO getHomeActivity(@PathVariable String name) {
        Activity activity = this.activityService.getByName(name);
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        return activityVO;
    }

    @ApiOperation("根据名字获取主页活动信息(包含Coupon信息)")
    @GetMapping("/name/{name}/with_coupon")
    public ActivityCouponVO getActivityWithCoupon(@PathVariable String name) {
        Activity activity = this.activityService.getByName(name);
        ActivityCouponVO activityCouponVO = new ActivityCouponVO(activity);
        return activityCouponVO;
    }
}
