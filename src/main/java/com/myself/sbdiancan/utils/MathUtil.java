package com.myself.sbdiancan.utils;



/**
 * 金额数据工具类
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 13:39 2018\6\4 0004
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较2个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2){
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE){
            return true;
        }else {
            return false;
        }
    }

}
