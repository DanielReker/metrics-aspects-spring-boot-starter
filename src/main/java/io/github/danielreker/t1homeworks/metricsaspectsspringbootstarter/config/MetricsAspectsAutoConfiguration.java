package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.aop.DataSourceErrorLogger;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.aop.MetricAspect;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.properties.MetricsAspectsProperties;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.kafka.MetricsProducer;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.DataSourceErrorLogMapper;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.DataSourceErrorLogMapperImpl;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.TimeLimitExceedLogMapper;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.TimeLimitExceedLogMapperImpl;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.dto.DataSourceErrorLogDto;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.dto.TimeLimitExceedLogDto;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository.DataSourceErrorLogRepository;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository.TimeLimitExceedLogRepository;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.service.DataSourceErrorLogService;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.service.TimeLimitExceedLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@AutoConfiguration
@ConditionalOnBean({ DataSourceErrorLogRepository.class, TimeLimitExceedLogRepository.class })
@AutoConfigureAfter({ MetricsAspectsJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableConfigurationProperties(MetricsAspectsProperties.class)
public class MetricsAspectsAutoConfiguration {
    private final MetricsAspectsProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public MetricAspect metricsAspectsStarterMetricAspect(TimeLimitExceedLogService timeLimitExceedLogService) {
        return new MetricAspect(properties, timeLimitExceedLogService);
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceErrorLogger metricsAspectsStarterDataSourceErrorLogger(DataSourceErrorLogService dataSourceErrorLogService) {
        return new DataSourceErrorLogger(dataSourceErrorLogService);
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceErrorLogService metricsAspectsStarterDataSourceErrorLogService(
            DataSourceErrorLogRepository repository,
            MetricsProducer<DataSourceErrorLogDto> metricsProducer,
            DataSourceErrorLogMapper mapper
    ) {
        return new DataSourceErrorLogService(repository, metricsProducer, mapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public TimeLimitExceedLogService metricsAspectsStarterTimeLimitExceedLogService(
            TimeLimitExceedLogRepository repository,
            MetricsProducer<TimeLimitExceedLogDto> metricsProducer,
            TimeLimitExceedLogMapper mapper
    ) {
        return new TimeLimitExceedLogService(repository, metricsProducer, mapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public <T> MetricsProducer<T> metricsAspectsStarterMetricsProducer(KafkaTemplate<String, T> kafkaTemplate) {
        return new MetricsProducer<>(properties, kafkaTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public TimeLimitExceedLogMapper metricsAspectsStarterTimeLimitExceedLogMapper() {
        return new TimeLimitExceedLogMapperImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceErrorLogMapper metricsAspectsStarterDataSourceErrorLogMapper() {
        return new DataSourceErrorLogMapperImpl();
    }
}
