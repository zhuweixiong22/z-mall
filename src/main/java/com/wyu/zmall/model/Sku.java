package com.wyu.zmall.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wyu.zmall.bo.Spec;
import com.wyu.zmall.util.GenericAndJsonConverter;
import com.wyu.zmall.util.ListAndJsonConverter;
import com.wyu.zmall.util.MapAndJsonConverter;
import lombok.Data;
import org.springframework.util.CollectionUtils;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zwx
 * @date 2022-07-04 16:30
 */
@Entity
@Data
public class Sku extends BaseEntry{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private BigDecimal price;

    private BigDecimal discountPrice;

    private Boolean online;

    private String img;

    private String title;

    private Long spuId;

    // @Convert(converter = ListAndJsonConverter.class) 用jpa这种， specs的类型就是List<Object>，List<Object>会更通用，但是也就失去了业务性，如果要求业务性，就要每个List<T>去写一个Converter
    private String specs;

    //@Convert(converter = MapAndJsonConverter.class) // 打上Convert注解后还是爆红是因为IDEA识别问题 用jpa这种， test的类型就是Object
    //private String test;

    private String code;

    private Long stock;

    private Long categoryId;

    private Long rootCategoryId;

/*    public Spec getTest() {
        return GenericAndJsonConverter.jsonToObject(this.test, Spec.class);
    }

    public void setTest(Spec test) {
        this.test = GenericAndJsonConverter.objectToJson(test);
    }
    */

    public List<Spec> getSpecs() {
        return GenericAndJsonConverter.jsonToObject(this.specs, new TypeReference<List<Spec>>() {
        });
    }

    public void setSpecs(List<Spec> specs) {
        if (CollectionUtils.isEmpty(specs)) {
            return;
        }
        this.specs = GenericAndJsonConverter.objectToJson(specs);
    }
}
