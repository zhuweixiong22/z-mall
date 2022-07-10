package com.wyu.zmall.service;

import com.wyu.zmall.model.Spu;
import org.springframework.data.domain.Page;

/**
 * @author zwx
 * @date 2022-07-04 15:39
 */
public interface SpuService {
    Spu getSpuById(Long id);

    Page<Spu> getLatestSpuList(Integer pageNum, Integer pageSize);

    Page<Spu> getSpuListByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer pageSize);
}
