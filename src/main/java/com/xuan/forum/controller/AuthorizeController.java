package com.xuan.forum.controller;

import com.xuan.forum.dto.GithubAccessTokenDto;
import com.xuan.forum.dto.GithubUser;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.User;
import com.xuan.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 */
@Controller
public class AuthorizeController {
    @Autowired//git第三方集成
    private GithubProvider githubProvider;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUr;



    /**
     * github登录回调地址
     * @param code github的code
     * @param state github授权中的随机字符串
     */
    @GetMapping("/callback")
    public String callback(String code,
                           String state,
                           HttpServletRequest request, HttpServletResponse response){
        //创建获取access_token所需的参数对象
        GithubAccessTokenDto accessTokenDto = new GithubAccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUr);
        accessTokenDto.setState(state);
        //设置client_id
        accessTokenDto.setClient_id(clientId);
        //设置 client_secret
        accessTokenDto.setClient_secret(clientSecret);
        //获取access_tpken
        String accessToken = githubProvider.getAccessToken(accessTokenDto);

        //根据access_token 获取github 的相关信息
        GithubUser githubUser = githubProvider.getUserByAccessToken(accessToken);

        System.out.println("获取的githubUser:"+githubUser);

        //如果user不为null则登录成功
        if (githubUser != null){
            //dto 转为 model 类
            User user = githubUser.toUser();
            //设置更新时间为新增时间
            user.setGmtModified(user.getGmtCreate());
            //添加数据
            userMapper.insert(user);

             request.getSession().setAttribute("user", user);
              System.out.println("首次登录成功，用户放入Session的信息为" + user);

            //添加cookie并写入信息
            response.addCookie(new Cookie("token",user.getToken()));

            //重定向hello首页
            return "redirect:/";
        }else{
            //登录失败重新登录
            return "redirect:/";
        }
    }
}
