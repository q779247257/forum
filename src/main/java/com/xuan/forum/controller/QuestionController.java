package com.xuan.forum.controller;

import com.xuan.forum.dto.CommentCreateDto;
import com.xuan.forum.dto.CommentDto;
import com.xuan.forum.dto.QuestionDto;
import com.xuan.forum.model.User;
import com.xuan.forum.service.CommentService;
import com.xuan.forum.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private CommentService commentService;

    /**
     * 查看文章
     * @param id 文章id
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id , HttpServletRequest request,
                         Model model){

        //根据文章id查询出来
       QuestionDto questionDto = questionService.getById(id);
       if (questionDto != null){
           List<CommentCreateDto> commentList = commentService.listByQuestionId(id);
           logger.info("评论的列表为:"+commentList);
           model.addAttribute("commentList",commentList);
           //累加阅读数+1
           questionService.incView(id);
           questionDto.setViewCout(questionDto.getViewCout()+1);
       }else {
           logger.warn("不存在id为 ["+ id +"]的问题");
       }
       model.addAttribute("question",questionDto);

       return "question";
    }

}
