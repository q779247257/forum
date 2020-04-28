package com.xuan.forum.dto;

import lombok.Data;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/28
 * @描述： 评论dto
 */
@Data
public class CommentDto {
    /** 父类id */
    private Integer parentId;
    /** 评论内容 */
    private String content;
    /** 类型 */
    private Integer type;
}
