package com.github.xie.collector.configuration;

import com.github.xie.collector.aspect.CollectorAspect;
import com.github.xie.collector.handler.LogHandler;
import com.github.xie.collector.properties.CollectorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(CollectorProperties.class)
@ConditionalOnProperty(name = "collector.enabled", havingValue = "true")
public class CollectorConfiguration {


    @Bean
    public CollectorAspect collectorAspect(ApplicationContext applicationContext) {
        Map<String, LogHandler> logHandlerMap = applicationContext.getBeansOfType(LogHandler.class);
        return new CollectorAspect(logHandlerMap);
    }
}
