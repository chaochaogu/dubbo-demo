package com.chaochaogu.controller;

import com.chaochaogu.model.User;
import com.chaochaogu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chaochao Gu
 * @date 2019/9/3
 */
@RestController
public class UserController {

    // dubbo框架生成的一个代理对象
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Object user(@RequestParam("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user2")
    public String user2(Model model, @RequestParam("id") Integer id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }
}
