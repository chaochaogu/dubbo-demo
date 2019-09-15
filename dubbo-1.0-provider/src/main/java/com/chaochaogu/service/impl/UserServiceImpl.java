package com.chaochaogu.service.impl;

import com.chaochaogu.model.User;
import com.chaochaogu.server.DubboService;
import com.chaochaogu.service.UserService;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
@DubboService(UserService.class)
public class UserServiceImpl implements UserService {

    public String sayHello() {
        return "Hello Dubbo";
    }

    public User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setEmail("chao@163.com");
        user.setNickname("chao");
        user.setPassword("321123");
        user.setPhone("13812345678");
        user.setRole("Administrator");
        return user;
    }

    public int updateById(Integer id) {
        return 0;
    }
}
