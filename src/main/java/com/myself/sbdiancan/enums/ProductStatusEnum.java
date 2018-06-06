package com.myself.sbdiancan.enums;

import lombok.Getter;

/**
 * 商品状态
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 21:43 2018\6\1 0001
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0, "上架"),
    DOWN(1, "下架")
    ;
    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
