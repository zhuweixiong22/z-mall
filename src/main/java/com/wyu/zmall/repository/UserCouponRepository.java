package com.wyu.zmall.repository;

import com.wyu.zmall.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author zwx
 * @date 2022-07-13 17:51
 */
public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    Optional<UserCoupon> findByUserIdAndCouponId(Long userId, Long couponId);
}
