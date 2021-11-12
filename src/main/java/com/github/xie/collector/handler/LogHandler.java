package com.github.xie.collector.handler;

import java.util.List;

public interface LogHandler {
    void log(String operatorCode, String desc, Object operator, List<Object> operatorData);
}
