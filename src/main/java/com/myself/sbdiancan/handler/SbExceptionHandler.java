package com.myself.sbdiancan.handler;

import com.myself.sbdiancan.config.ProjectUrlConfig;
import com.myself.sbdiancan.config.WechatAccountConfig;
import com.myself.sbdiancan.exception.ResponseBankException;
import com.myself.sbdiancan.exception.SbAuthorizeException;
import com.myself.sbdiancan.exception.SbException;
import com.myself.sbdiancan.utils.ResultVOUtil;
import com.myself.sbdiancan.vo.ResultVO;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常捕获
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 11:24 2018\6\6 0006
 */
@ControllerAdvice
public class SbExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SbAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sb/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSb())
                .concat("/sb/seller/login"));
    }

    //全局异常
    @ExceptionHandler(value = SbException.class)
    @ResponseBody
    public ResultVO handlerSbException(SbException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    // Http状态码异常返回
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)//403
    public void handleResponseBankException(){

    }

    //未知系统异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handlerException(Exception e){
        return ResultVOUtil.error(666,e.getMessage());
    }

}
