//package com.wyu.zmall.model.test;
//
//import javax.persistence.*;
//
///**
// * @author zwx
// * @date 2022-06-28 11:33
// */
////@Entity
//@Table(name = "test_banner_item")
//public class BannerItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String img;
//    private String keyword;
//    private Short type;
//    private String name;
//
//    private Long bannerId;
//
//    @JoinColumn(insertable = false, updatable = false, name = "bannerId") // 指明从表的外键 否是一对多还是会生成第三张中间表
//    @ManyToOne
//    private Banner banner;
//
//    public Banner getBanner() {
//        return banner;
//    }
//
//    public void setBanner(Banner banner) {
//        this.banner = banner;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//
//    public String getKeyword() {
//        return keyword;
//    }
//
//    public void setKeyword(String keyword) {
//        this.keyword = keyword;
//    }
//
//    public Short getType() {
//        return type;
//    }
//
//    public void setType(Short type) {
//        this.type = type;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getBannerId() {
//        return bannerId;
//    }
//
//    public void setBannerId(Long bannerId) {
//        this.bannerId = bannerId;
//    }
//}
