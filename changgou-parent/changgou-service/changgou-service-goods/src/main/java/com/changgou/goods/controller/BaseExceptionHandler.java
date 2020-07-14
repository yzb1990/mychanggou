package com.changgou.goods.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author : yuzebo <511729587@qq.com>
 * @Date : 2020/7/12 - 9:42 下午 - 星期日
 * @Package : com.changgou.goods.controller
 * @ProjectName : changgou
 */
@ControllerAdvice
public class BaseExceptionHandler {
    /***
     * 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
