package com.myself.sbdiancan.utils;

import com.myself.sbdiancan.enums.CodeEnum;

/**
 * 枚举工具类
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:58 2018\6\4 0004
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if (code.compareTo(each.getCode()) == 0){
                return each;
            }
        }
        return null;
    }

}
