package com.myself.sbdiancan.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 基于Gson的json工具
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 10:22 2018\6\4 0004
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
