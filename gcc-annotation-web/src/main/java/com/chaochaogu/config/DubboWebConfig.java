package com.chaochaogu.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author chaochao Gu
 * @date 2019/9/3
 */
@Configuration
@ComponentScan(basePackages = "com.chaochaogu.component")
@PropertySource("gcc-web.properties")
@EnableDubbo(scanBasePackages = "com.chaochaogu.component")
public class DubboWebConfig {
}
