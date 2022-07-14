package com.wyu.zmall.vo;

import com.wyu.zmall.model.Coupon;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zwx
 * @date 2022-07-14 14:27
 */
@Data
public class CouponCategoryVO extends CouponVO{
    private List<CategoryVO> categoryVOList;

    /**
     * TODO 这里需要处理循环序列化的问题
     * 最好不要在VO对象里写业务
     */
    public CouponCategoryVO(Coupon coupon) {
        super(coupon);

        categoryVOList = new ArrayList<>();
        coupon.getCategoryList().forEach(category -> {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);
            categoryVOList.add(categoryVO);
        });
    }

    public CouponCategoryVO(){

    }
}
