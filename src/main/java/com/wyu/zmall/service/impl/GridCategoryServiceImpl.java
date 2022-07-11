package com.wyu.zmall.service.impl;

import com.wyu.zmall.model.GridCategory;
import com.wyu.zmall.repository.GridCategoryRepository;
import com.wyu.zmall.service.GridCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwx
 * @date 2022-07-11 14:01
 */
@Service
public class GridCategoryServiceImpl implements GridCategoryService {

    @Autowired
    private GridCategoryRepository gridCategoryRepository;

    @Override
    public List<GridCategory> getAll() {
        return this.gridCategoryRepository.findAll();
    }
}
