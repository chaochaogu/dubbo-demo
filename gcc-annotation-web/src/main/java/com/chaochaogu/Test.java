package com.chaochaogu;

import com.chaochaogu.component.MyComponent;
import com.chaochaogu.config.DubboWebConfig;
import com.chaochaogu.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author chaochao Gu
 * @date 2019/9/3
 */
public class Test {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DubboWebConfig.class);
        MyComponent myComponent = context.getBean(MyComponent.class);
        User user = myComponent.test(1);
        System.out.println(user);
    }
}
