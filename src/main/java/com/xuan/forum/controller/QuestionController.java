package com.xuan.forum.controller;

import com.xuan.forum.dto.QuestionDto;
import com.xuan.forum.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/23
 * @描述： 文章controller
 */
@Controller
public class QuestionController {
    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(QuestionController.class);
    }

    @Autowired
    private QuestionService questionService;

    /**
     * 查看文章
     * @param id 文章id
     */
    @GetMapping("/question/{id}")
    public void question(@PathVariable("id") Integer id ,
                         Model model){

        //根据文章id查询出来
       QuestionDto questionDto = questionService.getById(id);
       if (questionDto == null){
           logger.warn("不存在id为 ["+ id +"]的问题");
       }
       model.addAttribute("question",questionDto);
    }

}
