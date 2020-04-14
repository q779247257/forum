package com.xuan.forum.dto;

import lombok.Data;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 * @描述： github 回调access DTO类
 */
@Data
public class GithubAccessTokenDto {
    //您从GitHub收到的GitHub App的客户端ID。
    private String client_id;
    //您从GitHub收到的GitHub App的客户密码。
    private String client_secret;
    //收到的Github的 响应Code码
    private String code;
    //授权后将用户发送到应用程序中的URL。
    private String redirect_uri;
    //请求Github时提供的无法猜测的随机字符串。
    private String state;

}
