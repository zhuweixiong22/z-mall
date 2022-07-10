package com.wyu.zmall.repository;

import com.wyu.zmall.model.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zwx
 * @date 2022-07-04 15:40
 */
public interface SpuRepository extends JpaRepository<Spu, Long> {
    Spu findOneById(Long id);

    Page<Spu> findByCategoryId(Long cid, Pageable request);

    // 根据根分类id查询商品
    Page<Spu> findByRootCategoryId(Long cid, Pageable request);
    // findByCategoryIdOrderByCreateTime
}
