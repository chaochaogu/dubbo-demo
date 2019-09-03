package com.chaochaogu.service.impl;

import com.chaochaogu.mapper.UserMapper;
import com.chaochaogu.model.User;
import com.chaochaogu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chaochao Gu
 * @date 2019/9/3
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
