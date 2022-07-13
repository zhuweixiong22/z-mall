package com.wyu.zmall.bo;

import com.wyu.zmall.model.User;
import lombok.Data;

/**
 * @author zwx
 * @date 2022-07-13 16:46
 */
@Data
public class LocalUser {

    private User user;

    private Integer scope;
}
