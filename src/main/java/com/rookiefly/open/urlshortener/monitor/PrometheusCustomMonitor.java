package com.rookiefly.open.urlshortener.monitor;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PrometheusCustomMonitor {

    /**
     * 记录请求出错次数
     */
    private Counter requestErrorCount;

    /**
     * 生成短链接次数
     */
    private Counter generateShortUrlCount;

    private final MeterRegistry registry;

    @Autowired
    public PrometheusCustomMonitor(MeterRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    private void init() {
        requestErrorCount = registry.counter("requests_error_total", "status", "error");
        generateShortUrlCount = registry.counter("generateShortUrl_request_count", "url-shortener", "url-shortener-test");
    }

    public Counter getRequestErrorCount() {
        return requestErrorCount;
    }

    public Counter getGenerateShortUrlCount() {
        return generateShortUrlCount;
    }
}