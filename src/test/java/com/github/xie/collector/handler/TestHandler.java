package com.github.xie.collector.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestHandler implements LogHandler {
    private static final Logger log = LoggerFactory.getLogger(TestHandler.class);

    @Override
    public void log(String operatorCode, String desc, Object operator, List<Object> operatorData) {
        log.info("code:{}.operator:{}.operator data:{}", operatorCode, operator, operatorData);
    }
}
