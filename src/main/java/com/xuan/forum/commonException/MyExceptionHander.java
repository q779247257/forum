package com.xuan.forum.commonException;

import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/17
 * @描述： 全局异常处理
 */
@ControllerAdvice
public class MyExceptionHander {
    @ExceptionHandler(SpelEvaluationException.class)
    public String spelEvaluationException(SpelEvaluationException e){
        System.out.println("抛SpelEvaluationException异常，重定向首页");
        return "redirect:/";
    }
}
