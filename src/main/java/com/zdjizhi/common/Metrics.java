package com.zdjizhi.common;

import java.util.Map;

public class Metrics {
    private final static  String name = FlowWriteConfig.METRICS_NAME;
    private  Map<String, Object> tags;
    private Fileds fields;
    private long timestamp;

    public Metrics(  Map<String, Object> tags, Fileds fields, long timestamp) {
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

    public Fileds getFields() {
        return fields;
    }

    public void setFields(Fileds fields) {
        this.fields = fields;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
