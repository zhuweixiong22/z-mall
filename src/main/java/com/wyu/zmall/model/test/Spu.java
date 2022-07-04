//package com.wyu.zmall.model.test;
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * @author zwx
// * @date 2022-06-28 23:16
// */
////@Entity
////@Table(name = "test_spu")
//public class Spu {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//
//    private String subtitle;
//
//    @ManyToMany(mappedBy = "spuList")
//    private List<Theme> themeList;
//
//    public List<Theme> getThemeList() {
//        return themeList;
//    }
//
//    public void setThemeList(List<Theme> themeList) {
//        this.themeList = themeList;
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
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getSubtitle() {
//        return subtitle;
//    }
//
//    public void setSubtitle(String subtitle) {
//        this.subtitle = subtitle;
//    }
//}
