package com.chaochaogu.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author chaochao Gu
 * @date 2019/9/3
 */
@Configuration // 相当于一个spring的xml
@ComponentScan(basePackages = "com.chaochaogu.component")
@PropertySource("classpath:gcc-web.properties")
@ComponentScan(basePackages = "com.chaochaogu.controller")
@EnableDubbo(scanBasePackages = "com.chaochaogu.component")
@EnableWebMvc // 开启springmvc，相当于spring.xml中的<mvc:annotation-driven/>
public class DubboWebConfig implements WebMvcConfigurer {

    /**
     *  <!-- 采用默认的servlet处理静态资源 -->
     * 	<mvc:default-servlet-handler />
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     *  <!-- 配置视图解析器 -->
     * 	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     * 		<property name="prefix" value="/" />
     * 		<property name="suffix" value=".jsp" />
     * 	</bean>
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
