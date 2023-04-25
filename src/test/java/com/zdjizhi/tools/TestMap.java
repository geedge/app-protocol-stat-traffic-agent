package com.zdjizhi.tools;

import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestMap {

    public static void main(String[] args) {
            Map<String, Object> tagsMap = new HashMap<String, Object>();
            tagsMap.put("session",1L);


        Object tags = tagsMap.remove("tags");
        System.out.println(tags);
        System.out.println(tagsMap);

    }
    }

