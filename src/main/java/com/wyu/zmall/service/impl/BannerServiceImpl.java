package com.wyu.zmall.service.impl;

import com.wyu.zmall.enums.ResponseCode;
import com.wyu.zmall.exception.http.NotFoundException;
import com.wyu.zmall.model.Banner;
import com.wyu.zmall.repository.BannerRepository;
import com.wyu.zmall.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zwx
 * @date 2022-06-26 23:54
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner getByName(String name) {
        Banner banner = bannerRepository.findOneByName(name);
        if (banner == null) {
            throw new NotFoundException(ResponseCode.BANNER_NOT_FOUND.getCode());
        }
        return banner;
    }
}
