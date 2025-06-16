package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.mapper;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.DataSourceErrorLog;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model.dto.DataSourceErrorLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DataSourceErrorLogMapper {
    DataSourceErrorLog toEntity(DataSourceErrorLogDto dataSourceErrorLogDto);
}