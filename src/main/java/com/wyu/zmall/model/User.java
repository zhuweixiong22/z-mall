package com.wyu.zmall.model;

import com.wyu.zmall.util.MapAndJsonConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

/**
 * @author zwx
 * @date 2022-07-11 21:15
 */
@Entity
@Data
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String openid;

    private String nickname;

    private Long unifyUid;

    private String email;

    private String password;

    private String mobile;

    @Convert(converter = MapAndJsonConverter.class)
    private Map<String, Object> wxProfile;

}
