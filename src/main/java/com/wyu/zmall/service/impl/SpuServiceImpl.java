package com.wyu.zmall.service.impl;

import com.wyu.zmall.enums.ResultEnum;
import com.wyu.zmall.exception.http.NotFoundException;
import com.wyu.zmall.model.Spu;
import com.wyu.zmall.repository.SpuRepository;
import com.wyu.zmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        Spu spu = this.spuRepository.findOneById(id);
        if (spu == null) {
            throw new NotFoundException(ResultEnum.SPU_NOT_FOUND.getCode());
        }
        return spu;
    }

    @Override
    public Page<Spu> getLatestSpuList(Integer pageNum, Integer pageSize) {
        // Sort.by里的字段写的是模型里的字段名而不是数据库里的字段名 descending倒序
        Pageable request = PageRequest.of(pageNum, pageSize, Sort.by("createTime").descending());
        return this.spuRepository.findAll(request);
    }

    @Override
    public Page<Spu> getSpuListByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer pageSize) {
        Pageable request = PageRequest.of(pageNum, pageSize);
        if (isRoot) {
            return this.spuRepository.findByRootCategoryId(cid, request);
        } else {
            return this.spuRepository.findByCategoryId(cid, request);
        }
    }
}
