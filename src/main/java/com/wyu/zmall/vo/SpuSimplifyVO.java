package com.wyu.zmall.vo;

/**
 * @author zwx
 * @date 2022-07-04 17:09
 */
public class SpuSimplifyVO {
    private Long id;

    private String title;

    private String subtitle;

    private String price;

    private Long sketchSpecId;

    private String img;

    private String discountPrice;

    private String description;

    private String tags;

    private String forThemeImg;

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

    public String getForThemeImg() {
        return forThemeImg;
    }

    public void setForThemeImg(String forThemeImg) {
        this.forThemeImg = forThemeImg;
    }
}
