package com.xuan.forum.model;

import lombok.Data;

@Data
public class Comment {
    /** 评论id */
    private Integer id;

    /** 父类id （问题和回复） */
    private Integer parentId;

    /** 1：父类id为问题  2：父类id为回复 */
    private Integer type;

    /** 评论人的用户id ） */
    private Integer commentator;

    /** 创建时间 */
    private Long gmtCreate;

    /** 更新时间 */
    private Long gmtModified;

    /** 点赞数 */
    private Integer likeCount;

    /** 评论蕾蕾 */
    private String content;

}