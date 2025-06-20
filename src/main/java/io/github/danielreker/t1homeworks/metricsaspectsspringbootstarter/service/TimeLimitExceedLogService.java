package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.service;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.kafka.MetricsProducer;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper.TimeLimitExceedLogMapper;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.dto.TimeLimitExceedLogDto;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository.TimeLimitExceedLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TimeLimitExceedLogService {
    private final TimeLimitExceedLogRepository repository;
    private final MetricsProducer<TimeLimitExceedLogDto> metricsProducer;
    private final TimeLimitExceedLogMapper mapper;

    @Async
    public void logTimeLimitExceed(TimeLimitExceedLogDto timeLimitExceedLogDto) {
        try {
            metricsProducer.sendMetricsError(timeLimitExceedLogDto, "METRICS");
        } catch (Exception ex) {
            log.warn("Failed to log time limit exceed to Kafka, logging to DB: {}", ex.getMessage());
            repository.save(mapper.toEntity(timeLimitExceedLogDto));
        }
    }
}
