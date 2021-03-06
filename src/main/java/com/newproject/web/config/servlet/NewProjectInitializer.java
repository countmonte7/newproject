package com.newproject.web.config.servlet;

import com.newproject.web.config.spring.AppConfig;
import com.newproject.web.config.spring.MessageConverterConfig;
import com.newproject.web.config.spring.MvcConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class NewProjectInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);
        rootContext.register(MvcConfig.class);
        rootContext.register(MessageConverterConfig.class);

        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();

        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
