package com.xuan.forum.commonException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/17
 * @描述： 全局异常处理
 */
@ControllerAdvice
public class MyExceptionHander {

    private static Logger logger = LoggerFactory.getLogger(MyExceptionHander.class);


    @ExceptionHandler(IllegalArgumentException.class)
    public String spelEvaluationException(Exception e , Model model){
        model.addAttribute("msg","您访问的文章不存在，或者已经删除!");
        logger.error("抛"+ e +"异常，用户查询不存在的文章");
        return "error";
    }
}
