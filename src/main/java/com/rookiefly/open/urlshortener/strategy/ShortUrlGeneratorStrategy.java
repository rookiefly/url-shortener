package com.rookiefly.open.urlshortener.strategy;

import com.rookiefly.open.urlshortener.service.ShortUrlGeneratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ShortUrlGeneratorStrategy {

    @Resource
    Map<String, ShortUrlGeneratorService> shortUrlGeneratorServiceMap = new ConcurrentHashMap<>(2);

    public ShortUrlGeneratorService getStrategy(String strategy) {
        ShortUrlGeneratorService shortUrlGeneratorService = shortUrlGeneratorServiceMap.get(strategy);
        if (shortUrlGeneratorService == null) {
            throw new RuntimeException("no strategy defined");
        }
        return shortUrlGeneratorService;
    }
}
