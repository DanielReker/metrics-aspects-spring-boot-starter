package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ConditionalOnExpression("""
    ${metrics-aspects.enable-data-source-error-logging} or
    ${metrics-aspects.enable-time-limit-exceed-error-logging}
""")
public @interface ConditionalOnAspectsMetricsEnabled {
}
