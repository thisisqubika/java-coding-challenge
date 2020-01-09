package com.mooveit.cars.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadPoolTaskSchedulerConfiguration {

    @Value("${thread.cant}")
    private Integer cantThread;

    @Value("${thread.name}")
    private String threadName;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(cantThread);
        threadPoolTaskScheduler.setThreadNamePrefix(threadName);
        return threadPoolTaskScheduler;
    }
}


