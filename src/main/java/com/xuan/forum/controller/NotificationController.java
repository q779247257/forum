package com.xuan.forum.controller;

import com.xuan.forum.dto.NotificationDto;
import com.xuan.forum.model.User;
import com.xuan.forum.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/25
 * @描述： 通知控制层
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /** 进入通知 */
    @GetMapping("/notification/{notificationId}")
    public String profile(@PathVariable("notificationId") Integer notificationId ,
                          HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/";

        //读取通知
        NotificationDto notificationDto = notificationService.read(notificationId,user);

        return "redirect:/question/"+notificationDto.getQuestionId();
    }
}
