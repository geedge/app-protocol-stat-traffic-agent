package com.zdjizhi.common;

import com.alibaba.fastjson2.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SessionRecord {
    private long common_vsys_id;
    private String common_device_id;
    private String common_device_tag;
    private String common_protocol_label;
    private String common_app_full_path;

    private String device_group;
    private String data_center;

    private long common_sessions;
    private long common_flags;
    private String common_l7_protocol;


    private long common_c2s_pkt_diff;
    private long common_s2c_pkt_diff;
    private long common_c2s_byte_diff;
    private long common_s2c_byte_diff;
    private long common_c2s_pkt_num;
    private long common_s2c_pkt_num;
    private long common_c2s_byte_num;
    private long common_s2c_byte_num;
    private long common_c2s_ipfrag_num;
    private long common_s2c_ipfrag_num;
    private long common_c2s_tcp_lostlen;
    private long common_s2c_tcp_lostlen;
    private long common_c2s_tcp_unorder_num;
    private long common_s2c_tcp_unorder_num;
    private long common_c2s_pkt_retrans;
    private long common_s2c_pkt_retrans;
    private long common_c2s_byte_retrans;
    private long common_s2c_byte_retrans;
    private String common_client_ip;

    public long getCommon_vsys_id() {
        return common_vsys_id;
    }

    public void setCommon_vsys_id(long common_vsys_id) {

        this.common_vsys_id = common_vsys_id;
    }

    public String getCommon_device_id() {
        return common_device_id;
    }

    public void setCommon_device_id(String common_device_id) {
        this.common_device_id = common_device_id;
    }

    public String getCommon_device_tag() {
        return common_device_tag;
    }

    public void setCommon_device_tag(String common_device_tag) {
        this.common_device_tag = common_device_tag;
    }

    public String getCommon_protocol_label() {
        return common_protocol_label;
    }

    public void setCommon_protocol_label(String common_protocol_label) {
        this.common_protocol_label = common_protocol_label;
    }

    public String getCommon_app_full_path() {
        return common_app_full_path;
    }

    public void setCommon_app_full_path(String common_app_full_path) {
        this.common_app_full_path = common_app_full_path;
    }

    public long getCommon_sessions() {
        return common_sessions;
    }

    public void setCommon_sessions(long common_sessions) {
        this.common_sessions = common_sessions;
    }

    public long getCommon_flags() {
        return common_flags;
    }

    public void setCommon_flags(long common_flags) {
        this.common_flags = common_flags;
    }

    public long getCommon_c2s_pkt_num() {
        return common_c2s_pkt_num;
    }

    public void setCommon_c2s_pkt_num(long common_c2s_pkt_num) {
        this.common_c2s_pkt_num = common_c2s_pkt_num;
    }

    public long getCommon_s2c_pkt_num() {
        return common_s2c_pkt_num;
    }

    public void setCommon_s2c_pkt_num(long common_s2c_pkt_num) {
        this.common_s2c_pkt_num = common_s2c_pkt_num;
    }

    public long getCommon_c2s_byte_num() {
        return common_c2s_byte_num;
    }

    public void setCommon_c2s_byte_num(long common_c2s_byte_num) {
        this.common_c2s_byte_num = common_c2s_byte_num;
    }

    public long getCommon_s2c_byte_num() {
        return common_s2c_byte_num;
    }

    public void setCommon_s2c_byte_num(long common_s2c_byte_num) {
        this.common_s2c_byte_num = common_s2c_byte_num;
    }

    public long getCommon_c2s_ipfrag_num() {
        return common_c2s_ipfrag_num;
    }

    public void setCommon_c2s_ipfrag_num(long common_c2s_ipfrag_num) {
        this.common_c2s_ipfrag_num = common_c2s_ipfrag_num;
    }

    public long getCommon_s2c_ipfrag_num() {
        return common_s2c_ipfrag_num;
    }

    public void setCommon_s2c_ipfrag_num(long common_s2c_ipfrag_num) {
        this.common_s2c_ipfrag_num = common_s2c_ipfrag_num;
    }

    public long getCommon_c2s_tcp_lostlen() {
        return common_c2s_tcp_lostlen;
    }

    public void setCommon_c2s_tcp_lostlen(long common_c2s_tcp_lostlen) {
        this.common_c2s_tcp_lostlen = common_c2s_tcp_lostlen;
    }

    public long getCommon_s2c_tcp_lostlen() {
        return common_s2c_tcp_lostlen;
    }

    public void setCommon_s2c_tcp_lostlen(long common_s2c_tcp_lostlen) {
        this.common_s2c_tcp_lostlen = common_s2c_tcp_lostlen;
    }

    public long getCommon_c2s_tcp_unorder_num() {
        return common_c2s_tcp_unorder_num;
    }

    public void setCommon_c2s_tcp_unorder_num(long common_c2s_tcp_unorder_num) {
        this.common_c2s_tcp_unorder_num = common_c2s_tcp_unorder_num;
    }

    public long getCommon_s2c_tcp_unorder_num() {
        return common_s2c_tcp_unorder_num;
    }

    public void setCommon_s2c_tcp_unorder_num(long common_s2c_tcp_unorder_num) {
        this.common_s2c_tcp_unorder_num = common_s2c_tcp_unorder_num;
    }

    public long getCommon_c2s_pkt_retrans() {
        return common_c2s_pkt_retrans;
    }

    public void setCommon_c2s_pkt_retrans(long common_c2s_pkt_retrans) {
        this.common_c2s_pkt_retrans = common_c2s_pkt_retrans;
    }

    public long getCommon_s2c_pkt_retrans() {
        return common_s2c_pkt_retrans;
    }

    public void setCommon_s2c_pkt_retrans(long common_s2c_pkt_retrans) {
        this.common_s2c_pkt_retrans = common_s2c_pkt_retrans;
    }

    public long getCommon_c2s_byte_retrans() {
        return common_c2s_byte_retrans;
    }

    public void setCommon_c2s_byte_retrans(long common_c2s_byte_retrans) {
        this.common_c2s_byte_retrans = common_c2s_byte_retrans;
    }

    public long getCommon_s2c_byte_retrans() {
        return common_s2c_byte_retrans;
    }

    public void setCommon_s2c_byte_retrans(long common_s2c_byte_retrans) {
        this.common_s2c_byte_retrans = common_s2c_byte_retrans;
    }

    public String getCommon_client_ip() {
        return common_client_ip;
    }

    public void setCommon_client_ip(String common_client_ip) {
        this.common_client_ip = common_client_ip;
    }

    public String getDevice_group() {
        return device_group;
    }

    public void setDevice_group(String device_group) {
        this.device_group = device_group;
    }

    public String getData_center() {
        return data_center;
    }

    public void setData_center(String data_center) {
        this.data_center = data_center;
    }

    public long getCommon_c2s_pkt_diff() {
        return common_c2s_pkt_diff;
    }

    public void setCommon_c2s_pkt_diff(long common_c2s_pkt_diff) {
        this.common_c2s_pkt_diff = common_c2s_pkt_diff;
    }

    public long getCommon_s2c_pkt_diff() {
        return common_s2c_pkt_diff;
    }

    public void setCommon_s2c_pkt_diff(long common_s2c_pkt_diff) {
        this.common_s2c_pkt_diff = common_s2c_pkt_diff;
    }

    public long getCommon_c2s_byte_diff() {
        return common_c2s_byte_diff;
    }

    public void setCommon_c2s_byte_diff(long common_c2s_byte_diff) {
        this.common_c2s_byte_diff = common_c2s_byte_diff;
    }

    public long getCommon_s2c_byte_diff() {
        return common_s2c_byte_diff;
    }

    public void setCommon_s2c_byte_diff(long common_s2c_byte_diff) {
        this.common_s2c_byte_diff = common_s2c_byte_diff;
    }

    public String getCommon_l7_protocol() {
        return common_l7_protocol;
    }

    public void setCommon_l7_protocol(String common_l7_protocol) {
        this.common_l7_protocol = common_l7_protocol;
    }

    public String getSessionTags() {
        Map<String, Object> tagsMap = new HashMap<String, Object>();

        if (common_vsys_id == 0) {
            common_vsys_id = 1;
        }
        tagsMap.put("vsys_id", common_vsys_id);
        tagsMap.put("device_id", common_device_id);
        tagsMap.put("common_device_tag", common_device_tag);
        tagsMap.put("protocol_label", common_protocol_label);
        tagsMap.put("app_full_path", common_app_full_path);
        tagsMap.put("randomNum",ThreadLocalRandom.current().nextInt(AppProtocolMetricsConfig.RANDOM_RANGE_NUM));

        return JSON.toJSONString(tagsMap);
    }


    public Fields getSessionFileds() {
        long out_bytes;
        long in_bytes;
        long in_pkts;
        long out_pkts;
        Long clientIsLocal = 8L;
        if ((common_flags & clientIsLocal) == 8L) {
            in_bytes = common_s2c_byte_diff;
            out_bytes = common_c2s_byte_diff;
            in_pkts = common_s2c_pkt_diff;
            out_pkts = common_c2s_pkt_diff;
        } else {
            in_bytes = common_c2s_byte_diff;
            out_bytes = common_s2c_byte_diff;
            in_pkts = common_c2s_pkt_diff;
            out_pkts = common_s2c_pkt_diff;
        }
        return new Fields(common_sessions, in_bytes, out_bytes, in_pkts, out_pkts, common_c2s_pkt_diff, common_s2c_pkt_diff, common_c2s_byte_diff, common_s2c_byte_diff, common_c2s_ipfrag_num, common_s2c_ipfrag_num, common_c2s_tcp_lostlen, common_s2c_tcp_lostlen, common_c2s_tcp_unorder_num, common_s2c_tcp_unorder_num,
                common_c2s_pkt_retrans, common_s2c_pkt_retrans, common_c2s_byte_retrans, common_s2c_byte_retrans, "");
    }

}

