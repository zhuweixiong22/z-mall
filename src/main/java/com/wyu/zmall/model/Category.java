package com.wyu.zmall.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author zwx
 * @date 2022-07-10 23:06
 */
@Entity
@Where(clause = "delete_time is null and online = 1")
@Data
public class Category extends BaseEntry{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String description;

    private Boolean isRoot;

    private Long parentId;

    private String img;

    private Long sortOrder;

    private Long online;

    private Long level;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coupon_category", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private List<Coupon> couponList;
}
