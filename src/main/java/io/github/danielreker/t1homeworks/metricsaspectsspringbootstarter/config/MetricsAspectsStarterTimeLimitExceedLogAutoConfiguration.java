package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.aop.MetricAspect;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.properties.MetricsAspectsProperties;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.kafka.MetricsProducer;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.TimeLimitExceedLogMapper;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.TimeLimitExceedLogMapperImpl;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.dto.TimeLimitExceedLogDto;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository.TimeLimitExceedLogRepository;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.service.TimeLimitExceedLogService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@AutoConfigureAfter({
        MetricsAspectsStarterKafkaAutoConfiguration.class,
        MetricsAspectsStarterJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@ConditionalOnBean({ TimeLimitExceedLogRepository.class, MetricsProducer.class })
public class MetricsAspectsStarterTimeLimitExceedLogAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public TimeLimitExceedLogMapper metricsAspectsStarterTimeLimitExceedLogMapper() {
        return new TimeLimitExceedLogMapperImpl();
    }

    @Bean
    @ConditionalOnBean(TimeLimitExceedLogMapper.class)
    @ConditionalOnMissingBean
    public TimeLimitExceedLogService metricsAspectsStarterTimeLimitExceedLogService(
            TimeLimitExceedLogRepository repository,
            MetricsProducer<TimeLimitExceedLogDto> metricsProducer,
            TimeLimitExceedLogMapper mapper
    ) {
        return new TimeLimitExceedLogService(repository, metricsProducer, mapper);
    }

    @Bean
    @ConditionalOnBean(TimeLimitExceedLogService.class)
    @ConditionalOnMissingBean
    public MetricAspect metricsAspectsStarterMetricAspect(
            MetricsAspectsProperties properties,
            TimeLimitExceedLogService timeLimitExceedLogService
    ) {
        return new MetricAspect(properties, timeLimitExceedLogService);
    }
}
