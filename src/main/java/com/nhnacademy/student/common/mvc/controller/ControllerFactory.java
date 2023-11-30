package com.nhnacademy.student.common.mvc.controller;

import com.nhnacademy.student.common.mvc.annotation.RequestMapping;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ControllerFactory {
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c) {
        //todo beanMap에 key = method + servletPath, value = Controller instance를 저장합니다.
        for (Class<?> clazz: c) {
            RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
            if(Objects.nonNull(mapping)){
                String key =  mapping.method() + mapping.value();
                try {
                    beanMap.put(key, clazz.getDeclaredConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public Object getBean(String method, String path){
        //todo beanMap 에서 method+servletPath을 key로 이용하여 Controller instance를 구합니다.
        return beanMap.get(method + path);
    }

}
