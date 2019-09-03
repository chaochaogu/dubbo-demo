package com.chaochaogu.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author chaochao Gu
 * @date 2019/9/3
 */
@Configuration
@PropertySource({"classpath:jdbc.properties", "classpath:gcc-service.properties"})
@ComponentScan(basePackages = "com.chaochaogu.service")
@MapperScan(basePackages = "com.chaochaogu.mapper")
@EnableDubbo(scanBasePackages = "com.chaochaogu.service")
public class DubboServiceConfig {

    @Value("${jdbc.username}")
    private String userName;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driverClassName}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
