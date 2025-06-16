package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("metrics-aspects")
public record MetricsAspectsProperties(
        String kafkaTopic,
        double timeLimitMs
) {
}
