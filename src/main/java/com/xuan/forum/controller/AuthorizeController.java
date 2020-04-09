package com.xuan.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 */
@Controller
public class AuthorizeController {

    /**
     * github登录回调地址
     * @param gitCode github的code
     * @param gitState github授权中的随机字符串
     */
    @GetMapping("/callback")
    public String callback(@RequestParam("code")String gitCode,@RequestParam("state") String gitState){

        return "hellow";
    }
}
