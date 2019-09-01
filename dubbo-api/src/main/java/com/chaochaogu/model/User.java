package com.chaochaogu.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chaochao gu
 * @date 2019/9/1
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer gender;
}
