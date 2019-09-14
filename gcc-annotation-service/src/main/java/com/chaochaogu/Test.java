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

        ApplicationContext context = new AnnotationConfigApplicationContext(DubboServiceConfig.class);

        // 启动了spring ioc容器后，不要退出，以便于其他服务可以调用
        System.in.read();

        //也ok
        //Thread.sleep(1000000000);
    }
}
