package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.annotation.ConditionalOnAspectsMetricsEnabled;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;

@AutoConfiguration
@ConditionalOnClass(JpaRepository.class)
@ConditionalOnAspectsMetricsEnabled
@Import({ MetricsAspectsJpaRegistrar.class })
public class MetricsAspectsStarterJpaAutoConfiguration {
}
