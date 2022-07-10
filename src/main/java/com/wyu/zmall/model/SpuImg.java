package com.wyu.zmall.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author zwx
 * @date 2022-07-04 16:26
 */
@Entity
@Data
public class SpuImg extends BaseEntry{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String img;

    private Long spuId;
}
