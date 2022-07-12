package com.wyu.zmall.service;

import com.wyu.zmall.model.Activity;
import com.wyu.zmall.vo.ActivityVO;

/**
 * @author zwx
 * @date 2022-07-12 21:09
 */
public interface ActivityService {

    Activity getByName(String name);
}
