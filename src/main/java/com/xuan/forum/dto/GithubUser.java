package com.xuan.forum.dto;

import com.xuan.forum.model.User;

import java.util.UUID;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 * @描述： git账户相关信息
 */
public class GithubUser {
    //github 登录的账号
    private String login;
    //github的昵称
    private String name;
    //github 的账号id
    private Long id;
    //github 的账号描述
    private String bio;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }

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
        user.setBio(this.getBio());
        return user;
    }
}
