package com.chaochaogu.service.impl;

import com.chaochaogu.mapper.UserMapper;
import com.chaochaogu.model.User;
import com.chaochaogu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

/**
 * @author chaochao Gu
 * @date 2019/9/3
 */
@Service("userService")
@org.apache.dubbo.config.annotation.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional(transactionManager = "transactionManager", readOnly = false,
            isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED,
            noRollbackFor = FileNotFoundException.class, rollbackFor = Exception.class, timeout = -1)
    @Override
    public int updateById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setEmail("123@163.com");
        user.setNickname("超超");
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
