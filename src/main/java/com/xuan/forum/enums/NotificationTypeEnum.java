package com.xuan.forum.enums;

import lombok.Getter;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/22
 * @描述： 通知类型
 */
@Getter
public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复问题 "),
    REPLY_COMMENT(1,"回复了评论")
    ;
    /** 状态 */
    private int type;
    /** 名称 */
    private String name;

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
