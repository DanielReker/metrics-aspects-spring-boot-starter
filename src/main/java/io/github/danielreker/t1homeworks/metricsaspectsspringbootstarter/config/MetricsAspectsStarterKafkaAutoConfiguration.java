package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.annotation.ConditionalOnAspectsMetricsEnabled;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.properties.MetricsAspectsProperties;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.kafka.MetricsProducer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@AutoConfiguration
@ConditionalOnBean({ KafkaTemplate.class })
@ConditionalOnAspectsMetricsEnabled
public class MetricsAspectsStarterKafkaAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public <T> MetricsProducer<T> metricsAspectsStarterMetricsProducer(
            MetricsAspectsProperties properties,
            KafkaTemplate<String, T> kafkaTemplate
    ) {
        return new MetricsProducer<>(properties, kafkaTemplate);
    }
}
