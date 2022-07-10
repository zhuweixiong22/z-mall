package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author zwx
 * @date 2022-07-04 15:29
 */
@Entity
@Data
public class Spu extends BaseEntry{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    private String subtitle;

    private Long categoryId;

    private Long rootCategoryId;

    private Boolean online;

    private String price;

    private Long sketchSpecId;

    private Long defaultSkuId;

    private String img;

    private String discountPrice;

    private String description;

    private String tags;

    private Long sortOrder;

    //private Object spuThemeImg; 不需要这个字段
    private String forThemeImg;

    @OneToMany(fetch = FetchType.LAZY) // 默认是懒加载 不读取就不会查询数据库
    @JoinColumn(name = "spuId")
    private List<SpuDetailImg> spuDetailImgList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spuId")
    private List<SpuImg> spuImgList;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spuId")
    private List<Sku> skuList;

}
