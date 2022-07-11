package com.wyu.zmall.vo;

import lombok.Data;

/**
 * @author zwx
 * @date 2022-07-11 14:38
 */
@Data
public class ThemeVO {
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
}
