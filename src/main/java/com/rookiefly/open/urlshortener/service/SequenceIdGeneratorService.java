package com.rookiefly.open.urlshortener.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SequenceIdGeneratorService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String ID_KEY = "id:generator:shorturl";

    public Long generateSequenceId() {
        return stringRedisTemplate.opsForValue().increment(ID_KEY);
    }
}