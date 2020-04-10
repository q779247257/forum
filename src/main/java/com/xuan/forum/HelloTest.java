package com.xuan.forum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/3/8
 * @描述：
 */
@Controller
public class HelloTest {
    @Value("${xuan.demo.value}")
    private String value ;
    @Value("${xuan.yml}")
    private String valueYml ;
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name" ,required = false ,defaultValue = "空的默认值") String name, Model model){
        System.out.println("接受到的name："+name);
        model.addAttribute("name",name);
        return "hello";
    }

    //默认跳转路径论坛的首页
    @GetMapping("/")
    public String index(){
        System.out.println("注入的是："+value);
        System.out.println("yml注入的是："+valueYml);
        return "hello";
    }
}
