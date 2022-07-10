package com.wyu.zmall.vo;

import com.wyu.zmall.model.Category;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zwx
 * @date 2022-07-11 0:11
 */
@Data
public class CategoryAllVO {
    private List<CategoryVO> roots;

    private List<CategoryVO> subs;

    public CategoryAllVO(Map<String, List<Category>> map) {
        this.roots = map.get("root").stream()
                .map(category -> {
                    CategoryVO categoryVO = new CategoryVO();
                    BeanUtils.copyProperties(category, categoryVO);
                    return categoryVO;
                }).collect(Collectors.toList());

        this.subs = map.get("sub").stream()
                .map(category -> {
                    CategoryVO categoryVO = new CategoryVO();
                    BeanUtils.copyProperties(category, categoryVO);
                    return categoryVO;
                }).collect(Collectors.toList());
    }
}
