package com.zdjizhi.tools.kafka;

import com.zdjizhi.common.AppProtocolMetricsConfig;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Optional;
import java.util.Properties;

/**
 * @author qidaijie
 * @Package com.zdjizhi.utils.kafka
 * @Description:
 * @date 2021/6/814:04
 */
public class KafkaProducer {

    private static Properties createProducerConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", AppProtocolMetricsConfig.SINK_KAFKA_SERVERS);
        properties.put("acks", AppProtocolMetricsConfig.PRODUCER_ACK);
        properties.put("retries", AppProtocolMetricsConfig.RETRIES);
        properties.put("linger.ms", AppProtocolMetricsConfig.LINGER_MS);
        properties.put("request.timeout.ms", AppProtocolMetricsConfig.REQUEST_TIMEOUT_MS);
        properties.put("batch.size", AppProtocolMetricsConfig.BATCH_SIZE);
        properties.put("buffer.memory", AppProtocolMetricsConfig.BUFFER_MEMORY);
        properties.put("max.request.size", AppProtocolMetricsConfig.MAX_REQUEST_SIZE);
        properties.put("compression.type", AppProtocolMetricsConfig.PRODUCER_KAFKA_COMPRESSION_TYPE);

        CertUtils.chooseCert(AppProtocolMetricsConfig.SINK_KAFKA_SERVERS, properties);

        return properties;
    }


    public static FlinkKafkaProducer<String> getKafkaProducer() {
        FlinkKafkaProducer<String> kafkaProducer = new FlinkKafkaProducer<String>(
               AppProtocolMetricsConfig.SINK_KAFKA_TOPIC,
                new SimpleStringSchema(),
                createProducerConfig(),
                //sink与所有分区建立连接，轮询写入；
                Optional.empty());

        //允许producer记录失败日志而不是捕获和抛出它们
        kafkaProducer.setLogFailuresOnly(true);

        return kafkaProducer;
    }

}
