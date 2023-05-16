package com.zdjizhi.common;

import com.alibaba.fastjson2.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class InterimSessionRecord {
    private long common_vsys_id;
    private String common_device_id;
    private String common_device_tag;
    private String common_protocol_label;
    private String common_app_full_path;

    private String device_group;
    private String data_center;

    private long common_flags;

    private long common_c2s_pkt_diff;
    private long common_s2c_pkt_diff;
    private long common_c2s_byte_diff;
    private long common_s2c_byte_diff;
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



    public long getCommon_flags() {
        return common_flags;
    }

    public void setCommon_flags(long common_flags) {
        this.common_flags = common_flags;
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

    public String getCommon_client_ip() {
        return common_client_ip;
    }

    public void setCommon_client_ip(String common_client_ip) {
        this.common_client_ip = common_client_ip;
    }

    public String getInterimSessionTags() {
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


    public Fields getInterimSessionFileds() {
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
        return new Fields(0, in_bytes, out_bytes, in_pkts, out_pkts, common_c2s_pkt_diff, common_s2c_pkt_diff, common_c2s_byte_diff, common_s2c_byte_diff, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, "");
    }


}

