package com.xuan.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/10
 * @描述： publish
 */
@Controller
public class PublishController {
    //跳转到 发起的问题 页面
    @GetMapping("/publish")
    public String publish(){
        return "publish.html";
    }
}
