package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({ MetricsAspectsJpaRegistrar.class })
public class MetricsAspectsJpaAutoConfiguration {
}
