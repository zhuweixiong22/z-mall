package com.wyu.zmall.repository;

import com.wyu.zmall.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author zwx
 * @date 2022-07-12 21:11
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findByName(String name);

    // 这里也可以写jpql 因为在activity里有couponlist的导航属性，所以可以用匿名函数这么查，这个函数名字虽然很奇怪，但是与函数名字没有关系
    // 所以导航属性还可以帮我们简化查询
    Optional<Activity> findByCouponListId(Long couponId);

    /*
    // 因为优惠券表里多添加了一个activity_id 所以可以用一对多查询
    @Query("select a from Activity as a\n" +
            "left outer join Coupon as c\n" +
            "on a.id = c.activityId\n" +
            "where c.id = :couponId")*/

    @Query(nativeQuery = true, value = "select *\n" +
            "from activity as a\n" +
            "join activity_coupon as ac \n" +
            "on ac.activity_id = a.id\n" +
            "join coupon as c \n" +
            "on ac.coupon_id = c.id\n" +
            "where c.id = :couponId")
    Optional<Activity> findByCouponId(@Param("couponId") Long couponId);
}
