package com.wyu.zmall.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wyu.zmall.enums.ResultEnum;
import com.wyu.zmall.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author zwx
 * @date 2022-07-11 21:33
 */
@Component
public class TokenUtil {

    private static String jwtKey;

    private static Integer jwtExpiredTime;

    private final static Integer defaultScope = 8;

    @Value("${z-mall.security.jwt-key}")
    public void setJwtKey(String jwtKey) {
        TokenUtil.jwtKey = jwtKey;
    }

    @Value("${z-mall.security.jwt-expired-time}")
    public void setJwtKey(Integer jwtExpiredTime) {
        TokenUtil.jwtExpiredTime = jwtExpiredTime;
    }

    public static String generateToken(Long uid) {
        return generateToken(uid, defaultScope);
    }

    public static String generateToken(Long uid, Integer scope) {
        Algorithm algorithm = Algorithm.HMAC256(TokenUtil.jwtKey);

        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        calendar.setTime(new Date());
        // 当前时间加上过期时间就是过期的时间点
        calendar.add(Calendar.SECOND, jwtExpiredTime);

        return JWT.create()
                .withClaim("uid", uid)
                .withClaim("scope", scope)
                .withExpiresAt(calendar.getTime())
                .withIssuedAt(now)
                .sign(algorithm);
    }

    public static Map<String, Claim> verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TokenUtil.jwtKey);
        JWTVerifier verifier = JWT.require(algorithm).build(); // JWT验证类

        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (TokenExpiredException e) {
            throw new HttpException(ResultEnum.TOKEN_EXPIRED.getCode(), ResultEnum.TOKEN_EXPIRED.getDesc(), HttpStatus.BAD_REQUEST.value());
        } catch (JWTVerificationException e) {
            throw new HttpException(ResultEnum.TOKEN_ERROR.getCode(), ResultEnum.TOKEN_ERROR.getDesc(), HttpStatus.BAD_REQUEST.value());
        }
    }
}
