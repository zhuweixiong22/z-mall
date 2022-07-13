package com.wyu.zmall.repository;

import com.wyu.zmall.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author zwx
 * @date 2022-07-13 14:27
 */
public interface CouponRepository extends JpaRepository<Coupon, Long> {


/*    @Query(nativeQuery = true,
            value = "select *\n" +
            "from coupon\n" +
            "         inner join coupon_category\n" +
            "                    on coupon_category.coupon_id = coupon.id\n" +
            "         inner join category\n" +
                    "                    on coupon_category.category_id = category.id\n" +
            "where category.id = :cid")*/

    @Query("select c " +
            "from Coupon as c\n" +
            "inner join\n" +
            "c.categoryList as clist\n" +
            "inner join Activity as a\n" +
            "on a.id = c.activityId\n" +
            "where clist.id = :cid\n" +
            "and a.startTime < :now\n" +
            "and a.endTime > :now\n")
    List<Coupon> findByCategoryId(Long cid, Date now);

    @Query("select c from Coupon as c\n" +
            "inner join Activity as a\n" +
            "on a.id = c.activityId\n" +
            "where c.wholeStore = :isWholeStore\n" +
            "and a.startTime < :now\n" +
            "and a.endTime > :now")
    List<Coupon> findByWholeStore(Boolean isWholeStore, Date now);


    /**
     * 查询我的可用优惠券
     * 涉及三张表 user, user_coupon, coupon
     * 第一个条件肯定要status为 1
     * 但是我们又不能只依赖status，因为可能已过期了status还未被及时修改
     * 所以还要判断是否过期
     * 为了严谨 orderId 也必须为空
     * @param uid
     * @param now
     * @return
     */
    @Query("select c \n" +
            "from Coupon as c\n" +
            "inner join UserCoupon as uc\n" +
            "on c.id = uc.couponId\n" +
            "inner join User as u\n" +
            "on u.id = uc.userId\n" +
            "where u.id = :uid\n" +
            "and uc.status = 1\n" +
            "and c.startTime < :now\n" +
            "and :now < c.endTime\n" +
            "and uc.orderId is null\n")
    List<Coupon> findMyAvailable(Long uid, Date now);

    @Query("select c \n" +
            "from Coupon as c\n" +
            "inner join UserCoupon as uc\n" +
            "on c.id = uc.couponId\n" +
            "inner join User as u\n" +
            "on u.id = uc.userId\n" +
            "where u.id = :uid\n" +
            "and uc.status = 2\n" +
            "and uc.orderId is not null\n")
    List<Coupon> findMyUsed(Long uid);


    /**
     * 查看过期的优惠券不能依赖status=3
     * 首先过期的优惠券status肯定不等于2，因为等于2是已使用
     * 我们再根据时间判断
     * @param uid
     * @param now
     * @return
     */
    @Query("select c \n" +
            "from Coupon as c\n" +
            "inner join UserCoupon as uc\n" +
            "on c.id = uc.couponId\n" +
            "inner join User as u\n" +
            "on u.id = uc.userId\n" +
            "where u.id = :uid\n" +
            "and uc.status <> 2\n" +
            "and uc.orderId is null\n" +
            "and :now > c.endTime\n")
    List<Coupon> findMyExpired(Long uid, Date now);
}
