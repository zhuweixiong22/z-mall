package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author zwx
 * @date 2022-07-11 13:54
 */
@Entity
@Data
public class GridCategory extends BaseEntry{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    private String img;

    private String name;

    private Long categoryId;

    private Long rootCategoryId;

}
