package com.zdjizhi.common;

import java.util.Map;

public class Metrics {
    private final static  String name = AppProtocolMetricsConfig.METRICS_NAME;
    private  Map<String, Object> tags;
    private Fields fields;
    private long timestamp;

    public Metrics(Map<String, Object> tags, Fields fields, long timestamp) {
        this.tags = tags;
        this.fields = fields;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public  Map<String, Object> getTags() {
        return tags;
    }

    public void setTags( Map<String, Object> tags) {
        this.tags = tags;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
