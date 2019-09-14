package com.chaochaogu.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 配置类
 *
 * @author chaochao Gu
 * @date 2019/9/3
 */
@Configuration // 相当于一个spring的xml
@ComponentScan(basePackages = "com.chaochaogu.component")
//@PropertySource("classpath:gcc-web.properties") // 读取gcc-web.properties配置文件
@ComponentScan(basePackages = "com.chaochaogu.controller") // 扫描spring的@Component注解
@EnableDubbo(scanBasePackages = "com.chaochaogu.component") // 扫描dubbo的@Reference注解
@EnableWebMvc // 开启springmvc，相当于spring.xml中的<mvc:annotation-driven/>
public class DubboWebConfig implements WebMvcConfigurer {

    /**
     * <!-- 采用默认的servlet处理静态资源 -->
     * <mvc:default-servlet-handler />
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * <!-- 配置视图解析器 -->
     * <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     * <property name="prefix" value="/" />
     * <property name="suffix" value=".jsp" />
     * </bean>
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
