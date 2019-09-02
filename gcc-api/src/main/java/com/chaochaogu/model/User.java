package com.chaochaogu.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private Integer id;

    private String email;

    private String nickname;

    private String password;

    private String role;

    private String phone;
}