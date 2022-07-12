package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author zwx
 * @date 2022-07-11 14:12
 */
@Entity
@Data
public class Theme extends BaseEntry{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    private String description;

    private String name;

    private String tplName;

    private String entranceImg;

    private String extend;

    private String internalTopImg;

    private String titleImg;

    private Boolean online;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "theme_spu", joinColumns = @JoinColumn(name = "theme_id"), inverseJoinColumns = @JoinColumn(name = "spu_id"))
    // joinColumns是关系维护端（theme）的中间表外键
    private List<Spu> spuList;
}
