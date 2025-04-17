package com.wordscool.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BaseUtils {
    public static String getUUIDTime(){
        return UUID.randomUUID().toString().replace("-","")+System.currentTimeMillis();
    }
}
