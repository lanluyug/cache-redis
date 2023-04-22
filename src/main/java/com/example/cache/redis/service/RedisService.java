package com.example.cache.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void test(){
        redisTemplate.opsForValue().set("key1", "我是新信息");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        //插入单条数据（存在有效期）
        System.out.println("-----------------");
        //向redis里存入数据和设置缓存时间
        redisTemplate.opsForValue().set("key2", "这是一条会过期的信息", 1, TimeUnit.SECONDS);
        //检查key是否存在，返回boolean值
        System.out.println(redisTemplate.hasKey("key2"));
        System.out.println(redisTemplate.opsForValue().get("key2"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(redisTemplate.hasKey("key2"));//检查key是否存在，返回boolean值
        System.out.println(redisTemplate.opsForValue().get("key2"));
        System.out.println("-----------------");
    }
}
