package com.wyu.zmall.api.v1;

import com.wyu.zmall.enums.ResponseCode;
import com.wyu.zmall.exception.http.NotFoundException;
import com.wyu.zmall.model.Theme;
import com.wyu.zmall.service.ThemeService;
import com.wyu.zmall.vo.ThemeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zwx
 * @date 2022-07-03 22:34
 */
@Api(tags = "Theme相关接口")
@RestController
@RequestMapping("/theme")
@Validated
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @ApiOperation("获取一组主题概要信息")
    @GetMapping("/by/names")
    public List<ThemeVO> getThemeGroupByNames(@RequestParam(name = "names") String names){
        List<String> nameList = Arrays.asList(names.split(","));
        List<Theme> themeList = this.themeService.getByNames(nameList);

        List<ThemeVO> themeVOList = new ArrayList<>();
        themeList.forEach(theme -> {
            ThemeVO themeVO = new ThemeVO();
            BeanUtils.copyProperties(theme,themeVO);
            themeVOList.add(themeVO);
        });

        return themeVOList;
    }

    @ApiOperation("获取主题详细信息包含Spu")
    @GetMapping("/name/{name}/with_spu")
    public Theme getThemeByNameWithSpu(@PathVariable(name = "name") String themeName){
        Optional<Theme> optionalTheme = this.themeService.getByName(themeName);
        return optionalTheme.orElseThrow(()-> new NotFoundException(ResponseCode.BANNER_NOT_FOUND.getCode()));
    }
}
