package com.wyu.zmall.repository;

import com.wyu.zmall.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zwx
 * @date 2022-07-10 23:09
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByIsRoot(Boolean isRoot);
}
