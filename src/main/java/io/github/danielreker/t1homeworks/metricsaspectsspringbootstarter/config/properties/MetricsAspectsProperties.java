package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("metrics-aspects")
@Getter
@Setter
public class MetricsAspectsProperties {
    /**
     * Kafka topic where metrics messages are sent by starter
     */
    String kafkaTopic;

    /**
     * If @Metric annotated method works more that specified time limit, exceed info is logged
     */
    double timeLimitMs = 5;

    /**
     * Enable DataSource error logging
     */
    boolean enableDataSourceErrorLogging = true;

    /**
     * Enable time limit exceed error logging
     */
    boolean enableTimeLimitExceedErrorLogging = true;

    /**
     * Create logging/metrics tables required by starter on application startup
     */
    boolean initializeDbSchema = true;
}
