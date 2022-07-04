//package com.wyu.zmall.model.test;
//
//import com.wyu.zmall.model.BannerItem;
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * @author zwx
// * @date 2022-06-28 11:31
// */
////@Entity
////@Table(name = "test_banner")
//public class Banner {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(length = 16)
//    private String name;
//
//    @Transient
//    private String description;
//
//    private String img;
//
//    private String title;
//
//    @OneToMany(mappedBy = "banner", fetch = FetchType.EAGER) // FetchType.EAGER开启急加载
//    //@JoinColumn(name = "bannerId") // 指明从表的外键 否是一对多还是会生成第三张中间表
//    private List<BannerItem> bannerItemList;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
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
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
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
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public List<BannerItem> getBannerItemList() {
//        return bannerItemList;
//    }
//
//    public void setBannerItemList(List<BannerItem> bannerItemList) {
//        this.bannerItemList = bannerItemList;
//    }
//}
