package com.wyu.zmall.service;

import com.wyu.zmall.model.Spu;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author zwx
 * @date 2022-07-04 15:39
 */
public interface SpuService {
    Spu getSpuById(Long id);

    Page<Spu> getLatestPagingSpu(Integer pageNum, Integer pageSize);
}
