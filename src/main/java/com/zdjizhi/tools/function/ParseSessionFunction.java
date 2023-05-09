package com.zdjizhi.tools.function;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.zdjizhi.common.Fields;
import com.zdjizhi.common.SessionRecord;
import com.zdjizhi.utils.StringUtil;
import org.apache.flink.api.common.functions.MapFunction;

import org.apache.flink.api.java.tuple.Tuple3;

/**
 * @author wangchengcheng
 * @Package com.zdjizhi.utils.functions
 * @Description:
 * @date 2023/04/20
 */
public class ParseSessionFunction implements MapFunction<String, Tuple3<String, Fields, String>> {
    private static final Log logger = LogFactory.get();
    private SessionRecord sessionRecord;
    private String tags;
    private Fields sessionField;

    @Override
    public Tuple3<String, Fields, String> map(String message) {

        try {
            if (StringUtil.isNotBlank(message)) {
                sessionRecord = JSON.parseObject(message, SessionRecord.class);
                //过滤common_protocol_label不为空的数据
                if (StringUtil.isNotBlank(sessionRecord.getCommon_protocol_label())) {
                    tags = sessionRecord.getSessionTags();
                    sessionField = sessionRecord.getSessionFileds();
                    return new Tuple3<>(tags, sessionField, sessionRecord.getCommon_client_ip());
                }else {
                    return new Tuple3<>("", sessionField, "");
                }
            }else {
                return new Tuple3<>("", sessionField, "");
            }

        } catch (Exception e) {
            logger.error("An error occurred in the session-record parsing,error message is:" + e + "，The original log is" + message);
            return new Tuple3<>("", sessionField, "");
        }
    }
}
