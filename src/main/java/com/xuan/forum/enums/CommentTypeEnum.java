package com.xuan.forum.enums;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/29
 * @描述：
 */
public enum CommentTypeEnum {
    /** 问题 */
    QUESTION(1),
    /** 评论 */
    COMMENT(2)
    ;
    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }



    public Integer getType() {
        return type;
    }
}
