package com.chaochaogu;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author chaochao gu
 * @date 2019/9/2
 */
public class Test {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        System.in.read();
    }
}
