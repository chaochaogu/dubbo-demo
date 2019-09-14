package com.chaochaogu.service;

import com.chaochaogu.model.User;

/**
 * @author chaochao gu
 * @date 2019/9/2
 */
public interface UserService {

    User getUserById(Integer id);

    int updateById(Integer id);
}
