package com.wyu.zmall.service.impl;

import com.wyu.zmall.enums.ResponseCode;
import com.wyu.zmall.exception.http.NotFoundException;
import com.wyu.zmall.model.Spu;
import com.wyu.zmall.repository.SpuRepository;
import com.wyu.zmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwx
 * @date 2022-07-04 15:41
 */
@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuRepository spuRepository;
    @Override
    public Spu getSpuById(Long id) {
        Spu spu = spuRepository.findOneById(id);
        if (spu == null) {
            throw new NotFoundException(ResponseCode.SPU_NOT_FOUND.getCode());
        }
        return spu;
    }

    @Override
    public Page<Spu> getLatestPagingSpu(Integer pageNum, Integer pageSize) {
        // Sort.by里的字段写的是模型里的字段名而不是数据库里的字段名 descending倒序
        PageRequest request = PageRequest.of(pageNum, pageSize, Sort.by("createTime").descending());
        Page<Spu> spuPage = spuRepository.findAll(request);
        return spuPage;
    }
}
