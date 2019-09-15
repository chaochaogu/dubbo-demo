package com.chaochaogu.service;

import com.chaochaogu.model.User;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
public interface UserService {

    String sayHello();

    User getUserById(Integer id);

    int updateById(Integer id);
}
