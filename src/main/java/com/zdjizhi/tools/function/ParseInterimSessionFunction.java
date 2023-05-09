package com.zdjizhi.tools.function;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSON;
import com.zdjizhi.common.Fields;
import com.zdjizhi.common.InterimSessionRecord;
import com.zdjizhi.utils.StringUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;

/**
 * @author wangchengcheng
 * @Package com.zdjizhi.utils.functions
 * @Description:
 * @date 2023/04/20
 */
public class ParseInterimSessionFunction implements MapFunction<String, Tuple3<String, Fields, String>> {
    private static final Log logger = LogFactory.get();
    private InterimSessionRecord interimsessionRecord;
    private String tags="";
    private Fields sessionField;

    @Override
    public Tuple3<String, Fields, String> map(String message) throws Exception {

        try {
            if (StringUtil.isNotBlank(message)) {
                interimsessionRecord = JSON.parseObject(message, InterimSessionRecord.class);
                //过滤common_protocol_label不为空的数据
                if (StringUtil.isNotBlank(interimsessionRecord.getCommon_protocol_label())) {
                    tags = interimsessionRecord.getInterimSessionTags();
                    sessionField = interimsessionRecord.getInterimSessionFileds();
                    return new Tuple3<>(tags, sessionField, interimsessionRecord.getCommon_client_ip());
                }else {
                    return new Tuple3<>("", sessionField, "");
                }
            }else {
                return new Tuple3<>("", sessionField, "");
            }
        } catch (RuntimeException e) {
            logger.error("An error occurred in the interim-session-record parsing,error message is:" + e + "，The original log is" + message);
            return new Tuple3<>("", sessionField, "");
        }
    }
}
