package com.zdjizhi.tools.kafka;

import com.zdjizhi.common.AppProtocolMetricsConfig;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;



import java.util.Properties;

/**
 * @author wangchengcheng
 * @Package com.zdjizhi.utils.kafka
 * @Description:
 * @date 2023/04/19
 */
public class KafkaConsumer {


    //消费session-record配置
    private static Properties createConsumerConfig(String groupId) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", AppProtocolMetricsConfig.SOURCE_KAFKA_SERVERS);
        properties.put("group.id", groupId);
        properties.put("session.timeout.ms", AppProtocolMetricsConfig.SESSION_TIMEOUT_MS);
        properties.put("max.poll.records", AppProtocolMetricsConfig.MAX_POLL_RECORDS);
        properties.put("max.partition.fetch.bytes", AppProtocolMetricsConfig.MAX_PARTITION_FETCH_BYTES);
        properties.put("partition.discovery.interval.ms", "10000");
        CertUtils.chooseCert(AppProtocolMetricsConfig.SOURCE_KAFKA_SERVERS, properties);

        return properties;
    }


    public static FlinkKafkaConsumer<String> getKafkaConsumer(String topic,String groupId) {
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(topic,
                new SimpleStringSchema(), createConsumerConfig(groupId));


        //随着checkpoint提交，将offset提交到kafka
        kafkaConsumer.setCommitOffsetsOnCheckpoints(true);

        //从消费组当前的offset开始消费
        kafkaConsumer.setStartFromGroupOffsets();

        return kafkaConsumer;
    }


}
