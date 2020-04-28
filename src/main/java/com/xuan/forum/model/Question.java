package com.xuan.forum.model;

import lombok.Data;


@Data
public class Question {
    /** 主键id */
    private Integer id;
    /** 标题 */
    private String title;
    /** 新增时间戳 */
    private Long gmtCreate;
    /** 最新更新时间 */
    private Long gmtModified;
    /** 问题创建人的github账户 */
    private String creator;
    /** 评论数 */
    private Integer commentCount;
    /** 阅读数 */
    private Integer viewCout;
    /** 点赞数 */
    private Integer likeCount;
    /** 标签 */
    private String tag;
    /** 问题描述 */
    private String description;

}