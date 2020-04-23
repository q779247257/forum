package com.xuan.forum.commonException;

import com.xuan.forum.controller.AuthorizeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(AuthorizeController.class);


    @ExceptionHandler(SpelEvaluationException.class)
    public String spelEvaluationException(SpelEvaluationException e){
        logger.error("抛SpelEvaluationException异常，重定向首页");
        return "redirect:/";
    }
}
