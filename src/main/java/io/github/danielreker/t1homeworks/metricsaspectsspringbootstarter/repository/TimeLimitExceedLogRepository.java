package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.TimeLimitExceedLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeLimitExceedLogRepository extends JpaRepository<TimeLimitExceedLog, Long> {
}