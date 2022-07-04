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
////@Table(name = "test_theme")
//public class Theme {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//
//    private String name;
//
//    @ManyToMany
//    // 设置生成中间表的表名及外键名
//    @JoinTable(name = "test_theme_spu", joinColumns = @JoinColumn(name = "theme_id"), inverseJoinColumns = @JoinColumn(name = "spu_id"))
//    private List<Spu> spuList;
//
//    public List<Spu> getSpuList() {
//        return spuList;
//    }
//
//    public void setSpuList(List<Spu> spuList) {
//        this.spuList = spuList;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
