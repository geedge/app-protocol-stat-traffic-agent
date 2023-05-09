package com.zdjizhi.tools.function;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.zdjizhi.common.Fields;
import com.zdjizhi.common.Metrics;

import com.zdjizhi.utils.StringUtil;
import org.apache.datasketches.hll.HllSketch;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;


import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CountAppProtocolWindowProcess extends ProcessWindowFunction<Tuple3<String, Fields, String>, String, String, TimeWindow> {
    private static final Log logger = LogFactory.get();

    private long sessions;
    private long in_bytes;
    private long out_bytes;
    private long in_pkts;
    private long out_pkts;
    private long c2s_pkts;
    private long s2c_pkts;
    private long c2s_bytes;
    private long s2c_bytes;
    private long c2s_fragments;
    private long s2c_fragments;
    private long c2s_tcp_lost_bytes;
    private long s2c_tcp_lost_bytes;
    private long c2s_tcp_ooorder_pkts;
    private long s2c_tcp_ooorder_pkts;
    private long c2s_tcp_retransmitted_pkts;
    private long s2c_tcp_retransmitted_pkts;
    private long c2s_tcp_retransmitted_bytes;
    private long s2c_tcp_retransmitted_bytes;
    private String device_group;
    private String data_center;
    private ArrayList<Object>  read;
    private String common_device_tag;


    private String dataCenterExpr = "$.tags[?(@.tag=='data_center')].value";
    private String dataGroupExpr = "$.tags[?(@.tag=='device_group')].value";

    private String client_ip_sketch;
    private Map<String, Object> cacheMap = new HashMap<>(20);

    @Override
    public void process(String key, Context context, Iterable<Tuple3<String, Fields, String>> iterable, Collector<String> collector) throws Exception {
        if (StringUtil.isNotBlank(key)) {
            try {
                HllSketch hllSketch = new HllSketch(12);
                for (Tuple3<String, Fields, String> record : iterable) {
                    sessions = sessions + record.f1.getSessions();

                    in_bytes = in_bytes + record.f1.getIn_bytes();
                    out_bytes = out_pkts + record.f1.getOut_bytes();

                    in_pkts = in_pkts + record.f1.getIn_pkts();
                    out_pkts = out_pkts + record.f1.getOut_pkts();

                    c2s_pkts = c2s_pkts + record.f1.getC2s_pkts();
                    s2c_pkts = s2c_pkts + record.f1.getS2c_pkts();

                    c2s_bytes = c2s_bytes + record.f1.getC2s_bytes();
                    s2c_bytes = s2c_bytes + record.f1.getS2c_bytes();

                    c2s_fragments = c2s_fragments + record.f1.getC2s_fragments();
                    s2c_fragments = s2c_fragments + record.f1.getS2c_fragments();

                    c2s_tcp_lost_bytes = c2s_tcp_lost_bytes + record.f1.getC2s_tcp_lost_bytes();
                    s2c_tcp_lost_bytes = s2c_tcp_lost_bytes + record.f1.getS2c_tcp_lost_bytes();

                    c2s_tcp_ooorder_pkts = c2s_tcp_ooorder_pkts + record.f1.getC2s_tcp_ooorder_pkts();
                    s2c_tcp_ooorder_pkts = s2c_tcp_ooorder_pkts + record.f1.getS2c_tcp_ooorder_pkts();

                    c2s_tcp_retransmitted_pkts = c2s_tcp_retransmitted_pkts + record.f1.getC2s_tcp_retransmitted_pkts();
                    s2c_tcp_retransmitted_pkts = s2c_tcp_retransmitted_pkts + record.f1.getS2c_tcp_retransmitted_pkts();

                    c2s_tcp_retransmitted_bytes = c2s_tcp_retransmitted_bytes + record.f1.getC2s_tcp_retransmitted_bytes();
                    s2c_tcp_retransmitted_bytes = s2c_tcp_retransmitted_bytes + record.f1.getS2c_tcp_retransmitted_bytes();

                    hllSketch.update(record.f2);
                }

                client_ip_sketch = Base64.getEncoder().encodeToString(hllSketch.toUpdatableByteArray());
                Fields field = new Fields(sessions, in_bytes, out_bytes, in_pkts, out_pkts, c2s_pkts, s2c_pkts, c2s_bytes, s2c_bytes, c2s_fragments, s2c_fragments, c2s_tcp_lost_bytes, s2c_tcp_lost_bytes, c2s_tcp_ooorder_pkts, s2c_tcp_ooorder_pkts, c2s_tcp_retransmitted_pkts, s2c_tcp_retransmitted_pkts, c2s_tcp_retransmitted_bytes, s2c_tcp_retransmitted_bytes, client_ip_sketch);



                //解析device_tag获得data_center、device_group
                Map keyMap = JSON.parseObject(key, Map.class);
                common_device_tag = (String) keyMap.remove("common_device_tag");

                if (common_device_tag != null) {
                    DocumentContext parse = JsonPath.parse(common_device_tag);
                    read = parse.read(dataCenterExpr);
                    if (read.size() >= 1) {
                        data_center = read.get(0).toString();
                    }
                    read = parse.read(dataGroupExpr);
                    if (read.size() >= 1) {
                        device_group = read.get(0).toString();
                    }
                }

                keyMap.put("device_group", device_group);
                keyMap.put("data_center", data_center);
                keyMap.remove("randomNum");

                collector.collect(JSON.toJSONString(new Metrics(keyMap, field, context.window().getEnd() / 1000)));
                sessions = 0;
                in_bytes = 0;
                out_bytes = 0;
                in_pkts = 0;
                out_pkts = 0;
                c2s_pkts = 0;
                s2c_pkts = 0;
                c2s_bytes = 0;
                s2c_bytes = 0;
                c2s_fragments = 0;
                s2c_fragments = 0;
                c2s_tcp_lost_bytes = 0;
                s2c_tcp_lost_bytes = 0;
                c2s_tcp_ooorder_pkts = 0;
                s2c_tcp_ooorder_pkts = 0;
                c2s_tcp_retransmitted_pkts = 0;
                s2c_tcp_retransmitted_pkts = 0;
                c2s_tcp_retransmitted_bytes = 0;
                s2c_tcp_retransmitted_bytes = 0;

            } catch (Exception e) {
                logger.error("windows count error,message:" + e);
            }
        }
    }
}
