package com.wyu.zmall.repository;

import com.wyu.zmall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author zwx
 * @date 2022-07-11 21:22
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByOpenid(String openId);

    User findFirstById(Long id);

    User findByUnifyUid(Long uid);
}
