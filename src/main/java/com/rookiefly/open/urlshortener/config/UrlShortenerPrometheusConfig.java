package com.rookiefly.open.urlshortener.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlShortenerPrometheusConfig {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName, @Value("${spring.datasource.hikari.poolName}") String hikariPoolName) {
        return (registry) -> registry.config().commonTags("application", applicationName, "hikaricp", hikariPoolName);
    }

}