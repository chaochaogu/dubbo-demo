package com.chaochaogu.init;

import com.chaochaogu.config.DubboWebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author chaochao gu
 * @date 2019/9/6
 */
public class InitDispatcher implements WebApplicationInitializer {

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(DubboWebConfig.class);
        context.setServletContext(servletContext);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        //spring mvc的dispatcherServlet拦截所有
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }
}
