package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.aop.DataSourceErrorLogger;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.aop.MetricAspect;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.properties.MetricsAspectsProperties;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.kafka.MetricsProducer;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.DataSourceErrorLogMapperImpl;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.TimeLimitExceedLogMapperImpl;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.service.DataSourceErrorLogService;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.service.TimeLimitExceedLogService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@EnableConfigurationProperties(MetricsAspectsProperties.class)
@Import({ DataSourceErrorLogger.class, MetricAspect.class, DataSourceErrorLogger.class, TimeLimitExceedLogMapperImpl.class,
        DataSourceErrorLogMapperImpl.class, DataSourceErrorLogService.class, TimeLimitExceedLogService.class,
        MetricsProducer.class })
public class MetricsAspectsAutoConfiguration {
}
