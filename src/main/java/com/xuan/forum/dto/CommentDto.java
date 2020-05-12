package com.xuan.forum.dto;

import lombok.Data;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/28
 * @描述： 评论dto 页面传递过来的
 */
@Data
public class CommentDto {
    /** 父类id */
    private Integer parentId;
    /** 评论内容 */
    private String content;
    /** 评论父类id类型 评论2 问题1 */
    private Integer type;
}
