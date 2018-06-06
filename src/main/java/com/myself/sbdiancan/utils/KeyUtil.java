package com.myself.sbdiancan.utils;

import java.util.Random;

/**
 * 主键生成工具类
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 11:06 2018\6\3 0003
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * 在并发下任然可能重复，需要加synchronized 多线程
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        //生成6位随机数
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

}
