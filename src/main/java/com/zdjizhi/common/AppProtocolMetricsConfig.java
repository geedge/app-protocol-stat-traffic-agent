package com.zdjizhi.common;


import com.zdjizhi.tools.system.FlowWriteConfigurations;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author Administrator
 */
public class FlowWriteConfig {

    private static StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    static {
        encryptor.setPassword("galaxy");
    }
    /**
     * kafka common
     */
    public static final String KAFKA_SASL_JAAS_USER = encryptor.decrypt(FlowWriteConfigurations.getStringProperty(1, "kafka.user"));
    public static final String KAFKA_SASL_JAAS_PIN = encryptor.decrypt(FlowWriteConfigurations.getStringProperty(1, "kafka.pin"));


    /**
     * consumer session-record config
     */
    public static final String SESSION_SOURCE_KAFKA_TOPIC = FlowWriteConfigurations.getStringProperty(0, "session.source.kafka.topic");
    public static final String SESSION_GROUP_ID = FlowWriteConfigurations.getStringProperty(0, "session.group.id");
    public static final Integer SESSION_SOURCE_PARALLELISM = FlowWriteConfigurations.getIntProperty(0, "session.source.parallelism");


    /**
     * consumer interim-session-record config
     */
    public static final String INTERIM_SESSION_SOURCE_KAFKA_TOPIC = FlowWriteConfigurations.getStringProperty(0, "interim.session.source.kafka.topic");
    public static final String INTERIM_SESSION_GROUP_ID = FlowWriteConfigurations.getStringProperty(0, "interim.session.group.id");
    public static final Integer INTERIM_SESSION_SOURCE_PARALLELISM = FlowWriteConfigurations.getIntProperty(0, "interim.session.source.parallelism");







    /**
     * kafka source config
     */
    public static final String SESSION_TIMEOUT_MS = FlowWriteConfigurations.getStringProperty(1, "session.timeout.ms");
    public static final String MAX_POLL_RECORDS = FlowWriteConfigurations.getStringProperty(1, "max.poll.records");
    public static final String MAX_PARTITION_FETCH_BYTES = FlowWriteConfigurations.getStringProperty(1, "max.partition.fetch.bytes");

    /**
     * kafka sink config
     */

    public static final String PRODUCER_ACK = FlowWriteConfigurations.getStringProperty(1, "producer.ack");
    public static final String PRODUCER_KAFKA_COMPRESSION_TYPE = FlowWriteConfigurations.getStringProperty(0, "producer.kafka.compression.type");
    public static final String SINK_KAFKA_TOPIC = FlowWriteConfigurations.getStringProperty(0,"sink.kafka.topic");
    public static final Integer SINK_KAFKA_PARALLELISM = FlowWriteConfigurations.getIntProperty(0,"sink.kafka.parallelism");



    /**
     * connection kafka
     */
    public static final String RETRIES = FlowWriteConfigurations.getStringProperty(1, "retries");
    public static final String LINGER_MS = FlowWriteConfigurations.getStringProperty(1, "linger.ms");
    public static final Integer REQUEST_TIMEOUT_MS = FlowWriteConfigurations.getIntProperty(1, "request.timeout.ms");
    public static final Integer BATCH_SIZE = FlowWriteConfigurations.getIntProperty(1, "batch.size");
    public static final Integer BUFFER_MEMORY = FlowWriteConfigurations.getIntProperty(1, "buffer.memory");
    public static final Integer MAX_REQUEST_SIZE = FlowWriteConfigurations.getIntProperty(1, "max.request.size");

    /**
     * common config
     */
    public static final String SOURCE_KAFKA_SERVERS = FlowWriteConfigurations.getStringProperty(0, "source.kafka.servers");
    public static final String SINK_KAFKA_SERVERS = FlowWriteConfigurations.getStringProperty(0, "sink.kafka.servers");
    public static final String TOOLS_LIBRARY = FlowWriteConfigurations.getStringProperty(0, "tools.library");


    /**
     * window config
     */
    public static final Integer COUNT_WINDOW_SECONDS = FlowWriteConfigurations.getIntProperty(0, "count.window.seconds");
    public static final Integer COUNT_WINDOW_PARALLELISM = FlowWriteConfigurations.getIntProperty(0, "count.window.parallelism");

    /**
     * metrics config
     */

    public static final String METRICS_NAME = FlowWriteConfigurations.getStringProperty(0, "metrics.name");

    public static final Integer RANDOM_RANGE_NUM = FlowWriteConfigurations.getIntProperty(1, "random.range.num");




    public static final Integer BUFFER_TIMEOUT = FlowWriteConfigurations.getIntProperty(1, "buffer.timeout");

}