package com.github.xie.collector.aspect;

import com.github.xie.collector.annotation.Log;
import com.github.xie.collector.annotation.Operator;
import com.github.xie.collector.annotation.OperatorData;
import com.github.xie.collector.handler.LogHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Aspect
public class CollectorAspect {
    private final Map<String, LogHandler> logHandlerMap;

    public CollectorAspect(Map<String, LogHandler> logHandlerMap) {
        this.logHandlerMap = logHandlerMap;
    }

    @Pointcut("@annotation(com.github.xie.collector.annotation.Log)")
    public void collectorPointcut() {
    }

    @Around("collectorPointcut()&&@annotation(log)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, Log log) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method          method          = methodSignature.getMethod();
        Object          proceed         = proceedingJoinPoint.proceed();
        Object          operator        = getOperator(method, proceedingJoinPoint.getArgs());
        List<Object>    operatorData    = getOperatorData(method, proceedingJoinPoint.getArgs());
        if (logHandlerMap != null && !logHandlerMap.isEmpty()) {
            for (LogHandler logHandler : logHandlerMap.values()) {
                logHandler.log(log.code(), log.desc(), operator, operatorData);
            }
        }
        return proceed;
    }

    private Object getOperator(Method method, Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            if (annotations == null || annotations.length == 0) {
                continue;
            }
            for (Annotation annotation : annotations) {
                if (annotation != null && annotation.annotationType().equals(Operator.class)) {
                    return args[i];
                }
            }
        }
        return null;
    }

    private List<Object> getOperatorData(Method method, Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        List<Object>   objects              = new ArrayList<>();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            if (annotations == null || annotations.length == 0) {
                continue;
            }
            for (Annotation annotation : annotations) {
                if (annotation != null && annotation.annotationType().equals(OperatorData.class)) {
                    objects.add(args[i]);
                }
            }
        }
        return objects;
    }
}
