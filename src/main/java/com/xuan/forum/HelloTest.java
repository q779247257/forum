package com.xuan.forum;

import org.springframework.beans.factory.annotation.Value;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/3/8
 * @描述：
 */
@Controller
public class HelloTest {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name" ,required = false ,defaultValue = "空的默认值") String name, Model model){
        System.out.println("接受到的name："+name);
        model.addAttribute("name",name);
        return "hello";
    }

    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/echo")
    public String echo(){
        return "test";
    }

    //默认跳转路径(首页)
    @GetMapping("/")
    public String index(HttpServletRequest request){
        //获取全部cookie
        Cookie[] cookies = request.getCookies();

        if (cookies != null){
            //遍历全部Cookie
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    //获取token
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    //如果数据库中有token的话，代表已经登录
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                        System.out.println("用户处于登录状态，用户放入Session的信息为"+user);
                    }
                    break;
                }
            }
        }
        return "hello";
    }


}