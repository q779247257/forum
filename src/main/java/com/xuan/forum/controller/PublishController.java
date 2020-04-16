package com.xuan.forum.controller;

import com.xuan.forum.mapper.QuestionMapper;
import com.xuan.forum.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/10
 * @描述： publish
 */
@Controller
public class PublishController {



    @Autowired
    private QuestionMapper questionMapper;

    //跳转到 发起的问题 页面
    @GetMapping("/publish")
    public String publish(){
        return "publish.html";
    }

    @PostMapping("/publish")
    public String doPublicsh(
      @RequestParam("title") String title,//文章标题
      @RequestParam("description") String description,//文章内容
      @RequestParam("tag") String tag,//文章标签（逗号隔开）
      @RequestParam(value = "userId",required = false) Integer userId, //用户的id
      Model model
    ){
        long paramDateLong = System.currentTimeMillis();
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);//设置标签
        question.setGmtCreate(paramDateLong);
        question.setGmtModified(paramDateLong);
        question.setCreator(userId);
        questionMapper.insert(question);

        if (userId == null){
            model.addAttribute("error","用户未登录");
        }
        return "publish.html";
    }
}
