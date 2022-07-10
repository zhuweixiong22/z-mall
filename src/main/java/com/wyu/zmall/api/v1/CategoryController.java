package com.wyu.zmall.api.v1;

import com.wyu.zmall.model.Category;
import com.wyu.zmall.service.CategoryService;
import com.wyu.zmall.vo.CategoryAllVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zwx
 * @date 2022-07-10 22:57
 */
@Api("分类相关接口")
@RestController
@RequestMapping("/category")
@Validated // 写在类上只是开启非java bean的校验 若要对请求体进行校验还要在方法参数上加上
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取所有分类信息")
    @GetMapping("/all")
    public CategoryAllVO getAll(){
        return this.categoryService.getAll();
    }

}
