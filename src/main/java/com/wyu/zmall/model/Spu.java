package com.wyu.zmall.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author zwx
 * @date 2022-07-04 15:29
 */
@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getSketchSpecId() {
        return sketchSpecId;
    }

    public void setSketchSpecId(Long sketchSpecId) {
        this.sketchSpecId = sketchSpecId;
    }

    public Long getDefaultSkuId() {
        return defaultSkuId;
    }

    public void setDefaultSkuId(Long defaultSkuId) {
        this.defaultSkuId = defaultSkuId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getForThemeImg() {
        return forThemeImg;
    }

    public void setForThemeImg(String forThemeImg) {
        this.forThemeImg = forThemeImg;
    }

    public List<SpuDetailImg> getSpuDetailImgList() {
        return spuDetailImgList;
    }

    public void setSpuDetailImgList(List<SpuDetailImg> spuDetailImgList) {
        this.spuDetailImgList = spuDetailImgList;
    }

    public List<SpuImg> getSpuImgList() {
        return spuImgList;
    }

    public void setSpuImgList(List<SpuImg> spuImgList) {
        this.spuImgList = spuImgList;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
