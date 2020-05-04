package com.akybenko.solutions.company.employee.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@RefreshScope
public class AsyncConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncConfig.class);

    @Value("${thread.core-pool-size:4}")
    private int corePoolSize;
    @Value("${thread.max-pool-size:4}")
    private int maxPoolSize;
    @Value("${thread.prefix-name:EmployeeServiceThread-}")
    private String prefix;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        LOG.debug("Creating async task executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.setThreadNamePrefix(prefix);
        executor.initialize();
        return executor;
    }
}
