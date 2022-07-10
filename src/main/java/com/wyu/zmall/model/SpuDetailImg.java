package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author zwx
 * @date 2022-07-04 16:25
 */
@Entity
@Data
public class SpuDetailImg extends BaseEntry{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String img;

    private Long spuId;

    private Long sortOrder;
}
