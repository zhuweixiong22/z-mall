package com.wyu.zmall.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wyu.zmall.enums.ResultEnum;
import com.wyu.zmall.exception.http.HttpException;
import com.wyu.zmall.model.User;
import com.wyu.zmall.repository.UserRepository;
import com.wyu.zmall.service.WxAuthenticationService;
import com.wyu.zmall.util.GenericAndJsonConverter;
import com.wyu.zmall.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Map;

/**
 * @author zwx
 * @date 2022-07-11 20:27
 */
@Service
public class WxAuthenticationServiceImpl implements WxAuthenticationService {
    @Value("${wx.code2session}")
    private String code2SessionUrl;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String code2Session(String code) {
        String url = MessageFormat.format(this.code2SessionUrl, this.appid, this.appsecret, code);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        Map<String, String> map = GenericAndJsonConverter.jsonToObject(response, new TypeReference<Map<String, String>>() {
        });

        return registerUser(map);
    }

    private String registerUser(Map<String, String> map) {
        if (map == null) {
            throw new HttpException();
        }
        String openId =  map.get("openid");
        if (openId == null) {
            throw new HttpException(ResultEnum.GET_WX_OPEN_ID_ERROR.getCode(), ResultEnum.GET_WX_OPEN_ID_ERROR.getDesc());
        }

        User dbUser = this.userRepository.findByOpenid(openId);
        if (dbUser == null) {
            User user = new User();
            user.setOpenid(openId);
            this.userRepository.save(user);
            // 加了@GeneratedValue 调用save方法后jpa会自动更新user
            return TokenUtil.generateToken(user.getId());
        }
        // 用户不是第一次登陆
        return TokenUtil.generateToken(dbUser.getId());

    }
}
