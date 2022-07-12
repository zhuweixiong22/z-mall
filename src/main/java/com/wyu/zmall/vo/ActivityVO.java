package com.wyu.zmall.vo;

import com.wyu.zmall.model.Activity;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * @author zwx
 * @date 2022-07-12 21:07
 */
@Data
public class ActivityVO {

    private Long id;

    private String title;

    private String entranceImg;

    private Boolean online;

    private String remark;

    private Date startTime;

    private Date endTime;

    public ActivityVO(Activity activity) {
        BeanUtils.copyProperties(activity,this);
    }

    public  ActivityVO() {

    }
}
