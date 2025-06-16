package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository.DataSourceErrorLogRepository;
import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository.TimeLimitExceedLogRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;

@AutoConfiguration
//@AutoConfigureAfter({
//        HibernateJpaAutoConfiguration.class,
//        JpaRepositoriesAutoConfiguration.class
//})
//@ConditionalOnClass(JpaRepository.class)
//@ConditionalOnBean(EntityManagerFactory.class)
@Import({ MetricsAspectsJpaRegistrar.class })
public class MetricsAspectsJpaAutoConfiguration {
}
