package com.mooveit.cars.configurations;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaticApplicationContext implements ApplicationContextAware {

    static ApplicationContext applicationContext = null;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * Note that this is a static method which expose ApplicationContext
     **/
    public static ApplicationContext getContext() {
        return applicationContext;
    }

}