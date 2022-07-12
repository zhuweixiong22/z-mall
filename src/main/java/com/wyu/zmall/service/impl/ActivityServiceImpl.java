package com.wyu.zmall.service.impl;

import com.wyu.zmall.enums.ResultEnum;
import com.wyu.zmall.exception.http.NotFoundException;
import com.wyu.zmall.model.Activity;
import com.wyu.zmall.repository.ActivityRepository;
import com.wyu.zmall.service.ActivityService;
import com.wyu.zmall.vo.ActivityVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zwx
 * @date 2022-07-12 21:09
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity getByName(String name) {
        Activity activity =  this.activityRepository.findByName(name);
        if (activity == null) {
            throw new NotFoundException(ResultEnum.ERROR.getCode());
        }
        return activity;
    }
}
