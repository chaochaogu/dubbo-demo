package com.chaochaogu.component;

import com.chaochaogu.model.User;
import com.chaochaogu.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author chaochao Gu
 * @date 2019/9/3
 */
@Component
public class MyComponent {

    // 引用远程dubbo服务
    @Reference
    private UserService userService;

    public User test(Integer id) {
        return userService.getUserById(id);
    }
}
