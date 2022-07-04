package com.wyu.zmall.service;

import com.wyu.zmall.model.Banner;
import org.springframework.stereotype.Service;

/**
 * @author zwx
 * @date 2022-06-26 23:54
 */

public interface BannerService {
    Banner getByName(String name);
}
