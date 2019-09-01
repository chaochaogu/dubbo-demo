package com.chaochaogu.service.impl;

import com.chaochaogu.model.User;
import com.chaochaogu.service.UserService;

/**
 * @author chaochao gu
 * @date 2019/9/1
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(1L);
        user.setGender(1);
        user.setName("超");
        return user;
    }
}
