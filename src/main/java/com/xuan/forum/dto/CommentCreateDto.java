package com.xuan.forum.dto;


import com.xuan.forum.model.User;
import lombok.Data;

/**
 * @Author: 轩轩
 * @Date: 2020/5/4 16:12
 * @description: 返回的评论Dto对象
 */
@Data
public class CommentCreateDto {
    /** 评论id */
    private Integer id;

    /** 父类id （问题和回复） */
    private Integer parentId;

    /** 1：父类id为问题  2：父类id为回复 */
    private Integer type;

    /** 评论人id */
    private Integer commentator;

    /** 创建时间 */
    private Long gmtCreate;

    /** 更新时间 */
    private Long gmtModified;

    /** 点赞数 */
    private Integer likeCount;

    /** 评论内容  */
    private String content;

    private User user;

}
