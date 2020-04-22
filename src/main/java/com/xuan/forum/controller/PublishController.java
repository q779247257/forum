package com.xuan.forum.controller;

import com.xuan.forum.mapper.QuestionMapper;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.Question;
import com.xuan.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/10
 * @描述： publish
 */
@Controller
public class PublishController {



    @Autowired
    private QuestionMapper questionMapper;
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
      @RequestParam(value = "userId",required = false) Integer userId, //用户的id
      Model model,
      HttpServletRequest request
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        //校验参数
        if (userId == null){
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
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null){
            //遍历全部Cookie
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    //获取token
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    //如果数据库中有token的话，代表已经登录
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        System.out.println("接受的userid为："+userId);
        if (user == null ){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        //创建发布问题插入数据库
        long paramDateLong = System.currentTimeMillis();
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);//设置标签
        question.setGmtCreate(paramDateLong);
        question.setGmtModified(paramDateLong);
        question.setCreator(userId);
        questionMapper.insert(question);
        //回到首页
        return "redirect:/";
    }


}
