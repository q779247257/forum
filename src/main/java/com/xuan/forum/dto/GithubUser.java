package com.xuan.forum.dto;

import com.xuan.forum.model.User;
import lombok.Data;

import java.util.UUID;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 * @描述： git账户相关信息
 */
@Data
public class GithubUser {
    //github 登录的账号
    private String login;
    //github的昵称
    private String name;
    //github 的账号id
    private Long id;
    //github 的账号描述
    private String bio;
    //github 的头像
    private String avatar_url;


    /**
     * dto 转为model类 User gmtModified更新的时间戳为null
     */
    public User toUser(){
        User user = new User();
        //设置token
        user.setToken(UUID.randomUUID().toString());
        //设置名字
        user.setName(this.getLogin());
        //设置github账号的id
        user.setAccountId(String.valueOf(this.getId()));
        //设置新增时间为当前系统时间戳
        user.setGmtCreate(System.currentTimeMillis());
        //设置账号描述
        user.setBio(this.getBio());
        //设置头像地址
        user.setAvatarUrl(this.getAvatar_url());
        return user;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
