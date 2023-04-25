package com.zdjizhi.tools.function;

import com.zdjizhi.common.Fileds;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple3;

public class KeyByFunction implements KeySelector<Tuple3<String, Fileds, String>, String> {
    @Override
    public String getKey(Tuple3<String, Fileds, String> value) throws Exception {

        return value.f0;
    }
}
