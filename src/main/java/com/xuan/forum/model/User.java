package com.xuan.forum.model;

import lombok.Data;

@Data
public class User {
    /** 主键id */
    private Integer id;

    /** github 账户id*/
    private String accountId;

    /**  名字对应github login账户*/
    private String name;

    /** 使用uuid作为token */
    private String token;

    /** 对应long类型 存储新增数据的时间戳*/
    private Long gmtCreate;

    /** 对应long类型 存储修改数据数据的时间戳 */
    private Long gmtModified;

    /** 对应github的 个人描述 */
    private String bio;

    /** github头像地址 */
    private String avatarUrl;


}