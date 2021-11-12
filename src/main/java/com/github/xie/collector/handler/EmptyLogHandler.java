package com.github.xie.collector.handler;

import java.util.List;

public class EmptyLogHandler implements LogHandler {
    @Override
    public void log(String operatorCode, String desc, Object operator, List<Object> operatorData) {

    }
}
