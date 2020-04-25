package com.xuan.forum.controller;

import com.xuan.forum.dto.QuestionDto;
import com.xuan.forum.mapper.QuestionMapper;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.Question;
import com.xuan.forum.model.User;
import com.xuan.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/10
 * @描述： publish
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserMapper userMapper;

    //跳转到 发起的问题 页面
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublicsh(
      @RequestParam("title") String title,//文章标题
      @RequestParam("description") String description,//文章内容
      @RequestParam("tag") String tag,//文章标签（逗号隔开）
      @RequestParam(value = "gitUser",required = false) String gitUser, //github的账户
      @RequestParam(value = "id",required = false) Integer id, //问题id
      Model model,
      HttpServletRequest request
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        //校验参数
        if (gitUser == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        if (title == null || title.equals("")) {
            model.addAttribute("error","标题不能为null");
            return "publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("error","文章内容不能为null");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error","文章标签不能为null");
            return "publish";
        }



        //获取全部cookie
        User user = (User) request.getSession().getAttribute("user");

        if (user == null ){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        //创建发布问题插入数据库
        Question question = new Question();
        //设置标题
        question.setTitle(title);
        //设置描述
        question.setDescription(description);
        question.setTag(tag);//设置标签
        question.setCreator(gitUser);
        question.setId(id);
        questionService.createOrUpdate(question);
        //回到首页
        return "redirect:/";
    }


    /**
     * 编辑问题 跳转会显页面
     * @param id 问题 id
     * @return 问题页面
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id , Model model){
        QuestionDto question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }
}
