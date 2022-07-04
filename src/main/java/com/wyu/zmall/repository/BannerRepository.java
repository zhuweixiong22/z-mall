package com.wyu.zmall.repository;

import com.wyu.zmall.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zwx
 * @date 2022-06-28 12:00
 */
//@Repository
// 继承了Repository接口的接口或者类，都相当于使用了@Repository注解，继承JpaRepository就可以间接继承实现了Repository
// 注解的作用不只是将类识别为Bean，同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。
public interface BannerRepository extends JpaRepository<Banner, Integer> {

    Banner findOneById(Long id);

    Banner findOneByName(String name);
}
