package com.wyu.zmall.vo;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO PageDozerVO 封装用到泛型
 *
 * @author zwx
 * @date 2022-07-04 21:12
 */
public class PageDozerVO<T, K> extends PageVO{
    /**
     * 可以在JavaBean里适当的写简单的业务逻辑
     * 抽象出来 集合类型的VO转换
     * 由于Java的泛型是一种伪泛型，通过 PageDozerVO<T, K>中的 K去获取类型是非常难的，所以还要传入一个类型参数
     * 如果是其他语言其实是可以直接从 K 中获取到类型的
     * @param page
     * @param classK
     */
    @SuppressWarnings("unchecked")
    public PageDozerVO(Page<T> page, Class<K> classK) {
        this.initPageParam(page);
        List<T> sourceItems = page.getContent();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<K> targetItems = new ArrayList<>();

        sourceItems.forEach(source ->{
            // 传入源对象和目标对象的类型 --> 得到目标对象
            K target = mapper.map(source, classK);
            targetItems.add(target);
        });
        this.setItems(targetItems);
    }
}
