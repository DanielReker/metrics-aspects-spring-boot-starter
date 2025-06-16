package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config;

import lombok.NonNull;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MetricsAspectsJpaRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(
            @NonNull AnnotationMetadata importingClassMetadata,
            @NonNull BeanDefinitionRegistry registry
    ) {
        AutoConfigurationPackages.register(registry, "io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.model");
        AutoConfigurationPackages.register(registry, "io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.repository");
    }
}
