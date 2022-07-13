package com.wyu.zmall.service.impl;

import com.wyu.zmall.model.User;
import com.wyu.zmall.repository.UserRepository;
import com.wyu.zmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zwx
 * @date 2022-07-13 16:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserById(Long id) {
        User user = this.userRepository.findFirstById(id);
        return user;
    }
}
