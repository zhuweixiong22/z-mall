package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author zwx
 * @date 2022-07-03 21:27
 */
@Entity
@Data
public class BannerItem extends BaseEntry {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String img;

    private String keyword;

    private Short type;

    private Long bannerId;

    private String name;
}
