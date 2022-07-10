package com.wyu.zmall.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.wyu.zmall.bo.PageCounter;
import com.wyu.zmall.model.Spu;
import com.wyu.zmall.service.SpuService;
import com.wyu.zmall.util.CommonUtil;
import com.wyu.zmall.vo.PageDozerVO;
import com.wyu.zmall.vo.SpuSimplifyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.logging.SimpleFormatter;

/**
 * @author zwx
 * @date 2022-07-04 15:35
 */
@Api(tags = "Spu相关接口")
@RestController
@RequestMapping("/spu")
@Validated // 写在类上只是开启非java bean的校验 若要对请求体进行校验还要在方法参数上加上
public class SpuController {

    @Autowired
    private SpuService spuService;

    @ApiOperation("获取商品详情")
    @GetMapping("id/{id}/detail")
    public Spu getDetail(@PathVariable @Positive Long id) {
        return this.spuService.getSpuById(id);
    }

    @ApiOperation("获取最新商品概要信息列表")
    @GetMapping("/latest")
    public PageDozerVO<Spu, SpuSimplifyVO> getLatestSpuList(@RequestParam(required = false, defaultValue = "0") Integer start,
                                                            @RequestParam(required = false, defaultValue = "10") Integer count){
        // 序列化返回的时候会读取所有的公共字段

        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        PageCounter pageCounter = CommonUtil.PageParamConverter(start, count);
        Page<Spu> spuPage = this.spuService.getLatestSpuList(pageCounter.getPageNum(), pageCounter.getPageSize());

        return new PageDozerVO<Spu, SpuSimplifyVO>(spuPage, SpuSimplifyVO.class);
    }

    @ApiOperation("根据分类id查询商品概要信息列表")
    @GetMapping("/by/category/{id}")
    public PageDozerVO<Spu, SpuSimplifyVO> getSpuListByCategoryId(@PathVariable(name = "id") @Positive(message = "{id.positive}") Long cid,
                                                                  @RequestParam(name = "is_root", required = false, defaultValue = "false") Boolean isRoot,
                                                                  @RequestParam(required = false,defaultValue = "0") Integer start,
                                                                  @RequestParam(required = false, defaultValue = "10") Integer count){
        PageCounter pageCounter = CommonUtil.PageParamConverter(start, count);
        Page<Spu> spuPage = this.spuService.getSpuListByCategory(cid, isRoot, pageCounter.getPageNum(), pageCounter.getPageSize());

        return new PageDozerVO<Spu, SpuSimplifyVO>(spuPage, SpuSimplifyVO.class);
    }

    @GetMapping("/id/{id}/simplify")
    public SpuSimplifyVO getSimplifySpu(@PathVariable @Positive(message = "{id.positive}") Long id){
        Spu spu = this.spuService.getSpuById(id);
        SpuSimplifyVO spuSimplifyVO = new SpuSimplifyVO();
        BeanUtils.copyProperties(spu, spuSimplifyVO);
        return spuSimplifyVO;
    }
}
