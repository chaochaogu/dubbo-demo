package com.chaochaogu;

import com.chaochaogu.config.DubboServiceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @author chaochao Gu
 * @date 2019/9/3
 */
public class Test {

    public static void main(String[] args) throws IOException {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(DubboServiceConfig.class);
        System.in.read();
    }
}
