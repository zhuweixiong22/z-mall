package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author zwx
 * @date 2022-07-03 21:09
 */
@Entity
@Data
public class Banner extends BaseEntry {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String description;

    private String title;

    private String img;

    @OneToMany
    @JoinColumn(name = "bannerId")
    private List<BannerItem> bannerItemList;
}
