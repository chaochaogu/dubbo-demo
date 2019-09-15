package com.chaochaogu.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chaochao gu
 * @date 2019/9/15
 */
@Data
public class User implements Serializable {

    private Integer id;

    private String email;

    private String nickname;

    private String password;

    private String role;

    private String phone;
}
