package com.xuan.forum.controller;

import com.xuan.forum.dto.GithubAccessTokenDto;
import com.xuan.forum.dto.GithubUser;
import com.xuan.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    /**
     * github登录回调地址
     * @param code github的code
     * @param state github授权中的随机字符串
     */
    @GetMapping("/callback")
    public String callback(String code,
                           String state){
        System.out.println("code:"+code);
        System.out.println("state:"+state);
        //创建获取access_token所需的参数对象
        GithubAccessTokenDto accessTokenDto = new GithubAccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8081/callback");
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id("0a3644bfe6f3a24b63fe");
        accessTokenDto.setClient_secret("9f03ac7d1534d60ada6ee17e53f2073e862395d7");
        //获取access_tpken
        String accessToken = githubProvider.getAccessToken(accessTokenDto);

        //根据access_token 获取github 的相关信息
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println("githubUser:"+githubUser);
        return "hello";
    }
}
