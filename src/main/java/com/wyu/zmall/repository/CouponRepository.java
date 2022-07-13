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
            "where category.id = #{cid};")*/

    @Query("select c from Coupon as c\n" +
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
}
