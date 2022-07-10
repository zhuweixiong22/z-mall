package com.wyu.zmall.vo;

import com.wyu.zmall.model.Category;
import lombok.Data;

import java.util.List;

/**
 * @author zwx
 * @date 2022-07-10 23:48
 */
@Data
public class CategoryVO {

    private Long id;

    private String name;

    private Boolean isRoot;

    private Long parentId;

    private String img;

    private Long sortOrder;

}
