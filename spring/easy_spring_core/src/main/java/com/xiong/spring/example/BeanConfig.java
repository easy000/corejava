package com.xiong.spring.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class BeanConfig {
    @Bean("coreThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
        tp.setMaxPoolSize(30);
        tp.setCorePoolSize(10);
        tp.setQueueCapacity(20);
        tp.setThreadNamePrefix("coreTaskExecutorThread-");
        return tp;
    }

    // 注意 这个BeanName 必须是applicationEventMulticaster
    @Bean("applictionEventMulticaster")
    public SimpleApplicationEventMulticaster getSimpleApplicationEventMulticaster(
            @Qualifier("coreThreadPoolTaskExecutor") ThreadPoolTaskExecutor tp) {
        SimpleApplicationEventMulticaster sp = new SimpleApplicationEventMulticaster();
        // 设置异步执行的线程池
        sp.setTaskExecutor(tp);
        return sp;
    }
}
