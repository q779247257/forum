package com.xuan.forum.commonException;

import com.xuan.forum.dto.ResultDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/17
 * @描述： 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHander {


    @ExceptionHandler(IllegalArgumentException.class)
    public String spelEvaluationException(Exception e , Model model){
        model.addAttribute("msg","您访问的文章不存在，或者已经删除!");
        log.error("抛"+ e +"异常，用户查询不存在的文章");
        return "error";
    }

    @ExceptionHandler(MyCustomException.class)
    @ResponseBody
    public Object MyCustomException(MyCustomException exception){
        return ResultDto.errorOf(exception);
    }
}
