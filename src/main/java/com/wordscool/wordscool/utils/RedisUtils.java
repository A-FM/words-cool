package com.wordscool.wordscool.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    private static RedisTemplate<Object,Object> redisTemplate = null;
    private static final long EXPIRE_TIME_IN_SEVEN_DAYS = 7;
    public static final String TOKEN_HEAD = "token";

    public RedisUtils(RedisTemplate<Object, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }


    public static void putObj(Object o1, Object o2, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(o1,o2,timeout,unit);
    }

    public static void putObj(Object o1, Object o2){
        redisTemplate.opsForValue().set(o1,o2,EXPIRE_TIME_IN_SEVEN_DAYS,TimeUnit.DAYS);
    }

    /**
     * 获取对象，并且在该对象小于一天时间过期的时候，将过期时间设置为一天。
     * @param o key
     * @return value
     */
    public static Object getObj(Object o){
        if (redisTemplate.getExpire(o)!=-1&&redisTemplate.getExpire(o)<TimeUnit.DAYS.toSeconds(1)){
            redisTemplate.expire(o,1,TimeUnit.DAYS);
        }
        return redisTemplate.opsForValue().get(o);
    }

    public static void removeObj(Object obj){
        redisTemplate.delete(obj);
    }

}


