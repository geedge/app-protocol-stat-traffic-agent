package com.zdjizhi.tools.function;

import com.zdjizhi.common.Fields;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple3;

public class KeyByFunction implements KeySelector<Tuple3<String, Fields, String>, String> {
    @Override
    public String getKey(Tuple3<String, Fields, String> value) throws Exception {

        return value.f0;
    }
}
