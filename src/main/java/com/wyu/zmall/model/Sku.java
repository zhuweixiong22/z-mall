package com.wyu.zmall.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wyu.zmall.bo.Spec;
import com.wyu.zmall.util.GenericAndJsonConverter;
import com.wyu.zmall.util.ListAndJsonConverter;
import com.wyu.zmall.util.MapAndJsonConverter;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getRootCategoryId() {
        return rootCategoryId;
    }

    public void setRootCategoryId(Long rootCategoryId) {
        this.rootCategoryId = rootCategoryId;
    }
}
