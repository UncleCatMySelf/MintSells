package com.myself.sbdiancan.exception;

import com.myself.sbdiancan.enums.ResultEnum;
import lombok.Getter;

/**
 * 系统全局异常
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 10:49 2018\6\3 0003
 */
@Getter
public class SbException extends RuntimeException {

    private Integer code;

    public SbException(ResultEnum resultEnum){
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SbException(Integer code, String message){
        super(message);

        this.code = code;
    }
}
