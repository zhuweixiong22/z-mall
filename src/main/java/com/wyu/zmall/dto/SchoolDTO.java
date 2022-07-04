package com.wyu.zmall.dto;

import org.hibernate.validator.constraints.Length;

/**
 * @author zwx
 * @date 2022-06-27 22:34
 */
public class SchoolDTO {
    @Length(min = 2)
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
