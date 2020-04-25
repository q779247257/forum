package com.xuan.forum.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/16
 * @描述： 文章
 */
@Data
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 文章名称
     */
    private String title;

    /**
     * 文章内容
     */
    private String description;

    /**
     * 新增时间
     */
    private Long gmtCreate;

    /**更新时间*/
    private Long gmtModified;

    /**问题创建人的github账户*/
    private String creator;

    /**评论数*/
    private Integer commentCount;

    /**阅读数*/
    private Integer viewCout;

    /**点赞数*/
    private Integer likeCount;

    /**tag*/
    private String tag;


    public Question() {
    }

}

