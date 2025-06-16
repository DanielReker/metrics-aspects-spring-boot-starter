package io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.kafka;

import io.github.danielreker.t1homeworks.metricsaspectsspringbootstarter.config.properties.MetricsAspectsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.utils.Utils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MetricsProducer<T> {
    private final MetricsAspectsProperties properties;

    private final KafkaTemplate<String, T> kafkaTemplate;


    public void sendMetricsError(T metricsErrorDto, String errorType) throws Exception {
        try {
            var header = new RecordHeader("errorType", Utils.utf8(errorType));
            ProducerRecord<String, T> record = new ProducerRecord<>(properties.getKafkaTopic(), metricsErrorDto);
            record.headers().add(header);
            kafkaTemplate.send(record).get();
        } finally {
            kafkaTemplate.flush();
        }
    }
}
