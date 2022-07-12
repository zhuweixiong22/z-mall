package com.wyu.zmall.repository;

import com.wyu.zmall.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zwx
 * @date 2022-07-12 21:11
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findByName(String name);
}
