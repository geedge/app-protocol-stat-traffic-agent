package com.zdjizhi.topology;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.zdjizhi.common.Fileds;
import com.zdjizhi.common.FlowWriteConfig;
import com.zdjizhi.tools.function.CountWindowProcess;
import com.zdjizhi.tools.function.KeyByFunction;
import com.zdjizhi.tools.function.ParseInterimSessionFunction;
import com.zdjizhi.tools.function.ParseSessionFunction;
import com.zdjizhi.tools.kafka.KafkaConsumer;

import com.zdjizhi.tools.kafka.KafkaProducer;
import org.apache.flink.api.java.functions.KeySelector;
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
public class LogFlowWriteTopology {
    private static final Log logger = LogFactory.get();

    public static void main(String[] args) {
        final StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setBufferTimeout(FlowWriteConfig.BUFFER_TIMEOUT);

        //消费、清洗会话日志
        SingleOutputStreamOperator<String> sessionSource = environment.addSource(KafkaConsumer.getKafkaConsumer(FlowWriteConfig.SESSION_SOURCE_KAFKA_TOPIC,FlowWriteConfig.SESSION_GROUP_ID))
                .setParallelism(FlowWriteConfig.SESSION_SOURCE_PARALLELISM).name(FlowWriteConfig.SESSION_SOURCE_KAFKA_TOPIC);

        SingleOutputStreamOperator<Tuple3<String, Fileds, String>> sessionParseMap = sessionSource.map(new ParseSessionFunction())
                .setParallelism(FlowWriteConfig.SESSION_SOURCE_PARALLELISM).name("sessionParseMap");


        //消费、清洗过渡会话
        SingleOutputStreamOperator<String> interimSessionSource = environment.addSource(KafkaConsumer.getKafkaConsumer(FlowWriteConfig.INTERIM_SESSION_SOURCE_KAFKA_TOPIC,FlowWriteConfig.INTERIM_SESSION_GROUP_ID))
                .setParallelism(FlowWriteConfig.INTERIM_SESSION_SOURCE_PARALLELISM).name(FlowWriteConfig.INTERIM_SESSION_SOURCE_KAFKA_TOPIC);

        SingleOutputStreamOperator<Tuple3<String, Fileds, String>> interimSessionParseMap = interimSessionSource.map(new ParseInterimSessionFunction())
                .setParallelism(FlowWriteConfig.INTERIM_SESSION_SOURCE_PARALLELISM).name("interimSessionParseMap");



        //将会话、过渡会话汇聚并按照key分组后进入滑动窗口
        WindowedStream<Tuple3<String, Fileds, String>, String, TimeWindow> window = sessionParseMap.union(interimSessionParseMap).keyBy(new KeyByFunction())
                .window(TumblingProcessingTimeWindows.of(Time.seconds(FlowWriteConfig.COUNT_WINDOW_SECONDS)));

        SingleOutputStreamOperator<String> countWindow = window.process(new CountWindowProcess()).setParallelism(FlowWriteConfig.COUNT_WINDOW_PARALLELISM).name("countWindow");

        countWindow.addSink(KafkaProducer.getKafkaProducer()).name("LogSinkKafka")
                .setParallelism(FlowWriteConfig.SINK_KAFKA_PARALLELISM).name(FlowWriteConfig.SINK_KAFKA_TOPIC);

        try {
            environment.execute(args[0]);
        } catch (Exception e) {
            logger.error("This Flink task start ERROR! Exception information is :" + e);
            e.printStackTrace();
        }

    }


}
