package com.zdjizhi.tools;

import com.alibaba.fastjson2.JSON;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.zdjizhi.common.SessionRecord;

import java.util.ArrayList;
import java.util.Map;

public class TestJsonPath {
    public static void main(String[] args) {

        String data_center;
        String device_group;

        String  sessionReocrd = "{\"common_schema_type\":\"BASE\",\"common_sessions\":1,\"common_protocol_label\":\"ETHERNET.IPv4.TCP\",\"common_c2s_byte_diff\":2264,\"common_c2s_pkt_diff\":4,\"common_s2c_byte_diff\":0,\"common_s2c_pkt_diff\":0,\"common_c2s_ipfrag_num\":0,\"common_s2c_ipfrag_num\":0,\"common_c2s_tcp_unorder_num\":0,\"common_s2c_tcp_unorder_num\":0,\"common_c2s_tcp_lostlen\":0,\"common_s2c_tcp_lostlen\":0,\"common_c2s_pkt_retrans\":3,\"common_s2c_pkt_retrans\":0,\"common_c2s_byte_retrans\":1698,\"common_s2c_byte_retrans\":0,\"common_flags\":8201,\"common_flags_identify_info\":\"{\\\"Asymmetric\\\":4,\\\"Client is Local\\\":1,\\\"C2S\\\":1}\",\"common_direction\":69,\"common_app_full_path\":\"unknown\",\"common_app_label\":\"unknown\",\"common_tcp_client_isn\":3855187193,\"common_server_ip\":\"45.188.134.11\",\"common_client_ip\":\"5.32.144.55\",\"common_server_port\":8081,\"common_client_port\":5652,\"common_stream_dir\":1,\"common_address_type\":4,\"common_address_list\":\"5652-8081-5.32.144.55-45.188.134.11\",\"common_start_time\":1682403782,\"common_end_time\":1682403802,\"common_con_duration_ms\":20164,\"common_s2c_pkt_num\":0,\"common_s2c_byte_num\":0,\"common_c2s_pkt_num\":4,\"common_c2s_byte_num\":2264,\"common_client_location\":\"俄罗斯联邦.莫斯科州.沃斯克列先斯克\",\"common_server_location\":\"巴西.马托格罗索州.Unknown\",\"common_stream_trace_id\":\"869469798157990979\",\"common_l4_protocol\":\"IPv4_TCP\",\"common_sled_ip\":\"192.168.40.81\",\"common_device_id\":\"21426003\",\"common_data_center\":\"center-xxg-9140\",\"common_device_tag\":\"{\\\"tags\\\":[{\\\"tag\\\":\\\"device_group\\\",\\\"value\\\":\\\"group-xxg-9140\\\"},{\\\"tag\\\":\\\"data_center\\\",\\\"value\\\":\\\"center-xxg-9140\\\"}]}\",\"common_t_vsys_id\":1,\"common_vsys_id\":1}";

        SessionRecord sessionRecord = JSON.parseObject(sessionReocrd, SessionRecord.class);

        int i = 0;
        double begin = System.currentTimeMillis();

        System.out.println(sessionRecord.getCommon_device_tag());
//        //jsonpath
//        while (i < 6000000) {
//            String common_device_tag = sessionRecord.getCommon_device_tag();
//            String dataCenterExpr = "$.tags[?(@.tag=='data_center')].value";
//            String dataGroupExpr = "$.tags[?(@.tag=='device_group')].value";
//
//
//            if (common_device_tag != null) {
//
//                DocumentContext parse = JsonPath.parse(common_device_tag);
//
//
//
//                ArrayList<Object> read = parse.read(dataCenterExpr);
//                if (read.size() >= 1) {
//                    data_center = read.get(0).toString();
//
//                }
//
//                read = parse.read(dataGroupExpr);
//                if (read.size() >= 1) {
//                    device_group = read.get(0).toString();
//                }
//            }
//
//            i++;
//        }

        double end = System.currentTimeMillis(); // 程序结束时间，调用系统当前时间

        double time = end - begin;// 程序的运行时间

        System.out.println(time/1000 +"秒");

//        //fastjson2
//        while (i < 600000) {
//
//            String dataCenterExpr = "$.tags[?(@.tag=='data_center')][0].value";
//            String dataGroupExpr = "$.tags[?(@.tag=='device_group')][0].value";
//
//
//            if (common_device_tag != null) {
//                ArrayList<Object> read = JsonPath.parse(common_device_tag).read(dataCenterExpr);
//                if (read.size() >= 1) {
//                    data_center = read.get(0).toString();
//
//                }
//
//                read = JsonPath.parse(common_device_tag).read(dataGroupExpr);
//                if (read.size() >= 1) {
//                    device_group = read.get(0).toString();
//                }
//            }
//            i++;
//        }








    }
}
