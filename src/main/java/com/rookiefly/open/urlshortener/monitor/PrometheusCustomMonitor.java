package com.rookiefly.open.urlshortener.monitor;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
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
     * 订单发起次数
     */
    private Counter orderCount;

    /**
     * 生成短链接次数
     */
    private Counter generateShortUrlCount;

    /**
     * 金额统计
     */
    private DistributionSummary amountSum;

    private final MeterRegistry registry;

    @Autowired
    public PrometheusCustomMonitor(MeterRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    private void init() {
        requestErrorCount = registry.counter("requests_error_total", "status", "error");
        orderCount = registry.counter("order_request_count", "order", "order-test");
        amountSum = registry.summary("order_amount_sum", "orderAmount", "order-test");
        generateShortUrlCount = registry.counter("generateShortUrl_request_count", "url-shortener", "url-shortener-test");
    }

    public Counter getRequestErrorCount() {
        return requestErrorCount;
    }

    public Counter getOrderCount() {
        return orderCount;
    }

    public DistributionSummary getAmountSum() {
        return amountSum;
    }

    public Counter getGenerateShortUrlCount() {
        return generateShortUrlCount;
    }
}