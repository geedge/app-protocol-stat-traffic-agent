package com.zdjizhi.topology;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.zdjizhi.common.Fields;
import com.zdjizhi.common.AppProtocolMetricsConfig;
import com.zdjizhi.tools.function.CountAppProtocolWindowProcess;
import com.zdjizhi.tools.function.KeyByFunction;
import com.zdjizhi.tools.function.ParseInterimSessionFunction;
import com.zdjizhi.tools.function.ParseSessionFunction;
import com.zdjizhi.tools.kafka.KafkaConsumer;

import com.zdjizhi.tools.kafka.KafkaProducer;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;




/**
 * @author wangchengcheng
 * @Package com.zdjizhi.topology
 * @Description:
 * @date 2023/04/19
 */
public class LogAppProtocolMetricsTopology {
    private static final Log logger = LogFactory.get();

    public static void main(String[] args) {
        final StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setBufferTimeout(AppProtocolMetricsConfig.BUFFER_TIMEOUT);

        //消费、清洗会话日志
        SingleOutputStreamOperator<String> sessionSource = environment.addSource(KafkaConsumer.getKafkaConsumer(AppProtocolMetricsConfig.SESSION_SOURCE_KAFKA_TOPIC, AppProtocolMetricsConfig.SESSION_GROUP_ID))
                .setParallelism(AppProtocolMetricsConfig.SESSION_SOURCE_PARALLELISM).name(AppProtocolMetricsConfig.SESSION_SOURCE_KAFKA_TOPIC);

        SingleOutputStreamOperator<Tuple3<String, Fields, String>> sessionParseMap = sessionSource.map(new ParseSessionFunction())
                .setParallelism(AppProtocolMetricsConfig.SESSION_SOURCE_PARALLELISM).name("sessionParseMap");


        //消费、清洗过渡会话
        SingleOutputStreamOperator<String> interimSessionSource = environment.addSource(KafkaConsumer.getKafkaConsumer(AppProtocolMetricsConfig.INTERIM_SESSION_SOURCE_KAFKA_TOPIC, AppProtocolMetricsConfig.INTERIM_SESSION_GROUP_ID))
                .setParallelism(AppProtocolMetricsConfig.INTERIM_SESSION_SOURCE_PARALLELISM).name(AppProtocolMetricsConfig.INTERIM_SESSION_SOURCE_KAFKA_TOPIC);

        SingleOutputStreamOperator<Tuple3<String, Fields, String>> interimSessionParseMap = interimSessionSource.map(new ParseInterimSessionFunction())
                .setParallelism(AppProtocolMetricsConfig.INTERIM_SESSION_SOURCE_PARALLELISM).name("interimSessionParseMap");



        //将会话、过渡会话汇聚并按照key分组后进入滑动窗口
        WindowedStream<Tuple3<String, Fields, String>, String, TimeWindow> window = sessionParseMap.union(interimSessionParseMap).keyBy(new KeyByFunction())
                .window(TumblingProcessingTimeWindows.of(Time.seconds(AppProtocolMetricsConfig.COUNT_WINDOW_SECONDS)));

        SingleOutputStreamOperator<String> countWindow = window.process(new CountAppProtocolWindowProcess()).setParallelism(AppProtocolMetricsConfig.COUNT_WINDOW_PARALLELISM).name("countWindow");

        countWindow.addSink(KafkaProducer.getKafkaProducer()).name("LogSinkKafka")
                .setParallelism(AppProtocolMetricsConfig.SINK_KAFKA_PARALLELISM).name(AppProtocolMetricsConfig.SINK_KAFKA_TOPIC);

        try {
            environment.execute(args[0]);
        } catch (Exception e) {
            logger.error("This Flink task start ERROR! Exception information is :" + e);
            e.printStackTrace();
        }

    }


}
