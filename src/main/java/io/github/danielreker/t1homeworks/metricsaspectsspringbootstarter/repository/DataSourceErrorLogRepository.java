package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.DataSourceErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSourceErrorLogRepository extends JpaRepository<DataSourceErrorLog, Long> {
}