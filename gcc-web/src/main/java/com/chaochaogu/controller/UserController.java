package com.chaochaogu.controller;

import com.chaochaogu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chaochao gu
 * @date 2019/9/2
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Object user(@RequestParam("id") Integer id){
        return userService.getUserById(id);
    }
}
