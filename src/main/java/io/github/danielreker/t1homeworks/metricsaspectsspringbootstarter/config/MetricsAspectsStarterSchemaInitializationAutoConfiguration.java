package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@AutoConfiguration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@ConditionalOnBean(DataSource.class)
@ConditionalOnProperty(prefix = "metrics-aspects", name = "initialize-db-schema", havingValue = "true", matchIfMissing = true)
public class MetricsAspectsStarterSchemaInitializationAutoConfiguration {
    @Bean
    public DataSourceScriptDatabaseInitializer metricsAspectsStarterScriptDatabaseInitializer(
            DataSource dataSource
    ) {
        DatabaseInitializationSettings settings = new DatabaseInitializationSettings();

        String schemaScriptLocation = "classpath:metrics-aspects-starter-db/schema.sql";
        settings.setSchemaLocations(Collections.singletonList(schemaScriptLocation));

        settings.setContinueOnError(false);
        settings.setEncoding(StandardCharsets.UTF_8);
        settings.setMode(DatabaseInitializationMode.ALWAYS);

        return new DataSourceScriptDatabaseInitializer(dataSource, settings);
    }
}