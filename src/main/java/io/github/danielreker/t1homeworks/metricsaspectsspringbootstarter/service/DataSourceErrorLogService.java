package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.service;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.kafka.MetricsProducer;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.DataSourceErrorLogMapper;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.dto.DataSourceErrorLogDto;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository.DataSourceErrorLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DataSourceErrorLogService {
    private final DataSourceErrorLogRepository repository;
    private final MetricsProducer<DataSourceErrorLogDto> metricsProducer;
    private final DataSourceErrorLogMapper mapper;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logDataSourceError(DataSourceErrorLogDto dataSourceErrorLogDto) {
        try {
            metricsProducer.sendMetricsError(dataSourceErrorLogDto, "DATA_SOURCE");
        } catch (Exception ex) {
            log.warn("Failed to log data source error to Kafka, logging to DB: {}", ex.getMessage());
            repository.save(mapper.toEntity(dataSourceErrorLogDto));
        }
    }
}
