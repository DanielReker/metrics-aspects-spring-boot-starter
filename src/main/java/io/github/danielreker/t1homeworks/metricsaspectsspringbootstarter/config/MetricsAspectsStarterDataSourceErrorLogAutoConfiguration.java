package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.aop.DataSourceErrorLogger;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.kafka.MetricsProducer;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.DataSourceErrorLogMapper;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.DataSourceErrorLogMapperImpl;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.dto.DataSourceErrorLogDto;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository.DataSourceErrorLogRepository;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.service.DataSourceErrorLogService;
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
@ConditionalOnBean({ DataSourceErrorLogRepository.class, MetricsProducer.class })
public class MetricsAspectsStarterDataSourceErrorLogAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public DataSourceErrorLogMapper metricsAspectsStarterDataSourceErrorLogMapper() {
        return new DataSourceErrorLogMapperImpl();
    }

    @Bean
    @ConditionalOnBean(DataSourceErrorLogMapper.class)
    @ConditionalOnMissingBean
    public DataSourceErrorLogService metricsAspectsStarterDataSourceErrorLogService(
            DataSourceErrorLogRepository repository,
            MetricsProducer<DataSourceErrorLogDto> metricsProducer,
            DataSourceErrorLogMapper mapper
    ) {
        return new DataSourceErrorLogService(repository, metricsProducer, mapper);
    }

    @Bean
    @ConditionalOnBean(DataSourceErrorLogService.class)
    @ConditionalOnMissingBean
    public DataSourceErrorLogger metricsAspectsStarterDataSourceErrorLogger(
            DataSourceErrorLogService dataSourceErrorLogService
    ) {
        return new DataSourceErrorLogger(dataSourceErrorLogService);
    }
}
