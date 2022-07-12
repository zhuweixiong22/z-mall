package com.wyu.zmall.api.v1;

import com.auth0.jwt.interfaces.Claim;
import com.wyu.zmall.core.annotations.ScopeLevel;
import com.wyu.zmall.dto.TokenDTO;
import com.wyu.zmall.dto.UserDTO;
import com.wyu.zmall.enums.ResultEnum;
import com.wyu.zmall.exception.http.NotFoundException;
import com.wyu.zmall.service.WxAuthenticationService;
import com.wyu.zmall.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zwx
 * @date 2022-07-11 17:10
 */
@Api(tags = "token相关接口")
@RestController
@RequestMapping("/token")
public class UserController {

    @Autowired
    private WxAuthenticationService wxAuthenticationService;

    @ApiOperation("登录并获取token")
    @PostMapping("")
    public Map<String, String> getToken(@RequestBody @Validated UserDTO userDTO) {
            Map<String, String> map = new HashMap<>();
            String token = null;
            switch (userDTO.getLoginType()) {
                case USER_WX:
                    token = this.wxAuthenticationService.code2Session(userDTO.getAccount());
                    break;
                case USER_EMAIL:
                    break;
                default:
                    throw new NotFoundException(ResultEnum.LOGIN_NOT_FOUND.getCode());
            }
            map.put("token", token);
            return map;
    }

    @ApiOperation("验证token是否有效")
    @PostMapping("/verify")
    public Map<String, Boolean> verifyToken(@RequestBody @Validated TokenDTO tokenDTO) {
        Map<String, Boolean> map = new HashMap<>();
        Map<String, Claim> claims = TokenUtil.verifyToken(tokenDTO.getToken());
        if (claims == null) {
            map.put("is_valid", false);
        } else {
            map.put("is_valid", true);
        }
        return map;
    }
}
