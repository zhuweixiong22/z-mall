package com.wyu.zmall.service.impl;

import com.wyu.zmall.model.Category;
import com.wyu.zmall.repository.CategoryRepository;
import com.wyu.zmall.service.CategoryService;
import com.wyu.zmall.vo.CategoryAllVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zwx
 * @date 2022-07-10 23:39
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Map<String, List<Category>> getAll() {
        List<Category> roots = this.categoryRepository.findAllByIsRoot(true);
        List<Category> subs = this.categoryRepository.findAllByIsRoot(false);
        Map<String, List<Category>> map = new HashMap<>();
        map.put("root", roots);
        map.put("sub", subs);
        return map;
    }
}
