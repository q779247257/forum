package com.xuan.forum.model;

import lombok.Data;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 * @描述：
 */
@Data
public class User {
    private Integer id;//id
    private String name;//名字
    private String accountId;//github 账户id
    private String token;
    private Long gmtCreate;//新增的时间戳
    private Long gmtModified;//最后一次更新的时间戳
    private String bio;// github 账号描述
    private String avatarUrl;

}
