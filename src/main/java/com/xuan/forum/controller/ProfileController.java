package com.xuan.forum.controller;

import com.xuan.forum.dto.PaginationDto;
import com.xuan.forum.enums.NotificationStatusEnum;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.Notification;
import com.xuan.forum.model.User;
import com.xuan.forum.service.NotificationService;
import com.xuan.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/22
 * @描述： 导航栏个人选项卡跳转
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationService notificationService;

    /**
     * 个人选项卡 我的问题跳转
     * @param action 选的选项卡内容
     * @param model
     * @return
     */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action , Model model , HttpServletRequest request,
                            @RequestParam(name = "page", defaultValue = "1") Integer page, //当前页数
                          @RequestParam(name = "size", defaultValue = "5") Integer size){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/";

        if ("questions".equals(action) ){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            //todo 查询问题
            PaginationDto pagination = questionService.list(user.getName(), page, size);
            model.addAttribute("pagination", pagination);
        }else
        //todo 最新回复
        if ("repiies".equals(action)){
            model.addAttribute("section","repiies");
            model.addAttribute("sectionName","最新回复");
            //todo 查询通知问题
            PaginationDto pagination = notificationService.list(user.getId(),page,size);
            model.addAttribute("pagination", pagination);
        }

        //未读数量
       Integer unreadCount =  notificationService.coutOfStatus(NotificationStatusEnum.UNREAD.getStatus(),user.getId());
        model.addAttribute("unreadCount",unreadCount);

        return "profile";
    }
}
