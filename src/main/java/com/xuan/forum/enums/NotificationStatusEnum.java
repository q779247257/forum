package com.xuan.forum.enums;

import lombok.Getter;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/22
 * @描述： 通知状态枚举
 */
@Getter
public enum NotificationStatusEnum {
    /** 未读 */
    UNREAD(0),
    /**已读*/
    READ(1),

    ;
    private Integer status;

    NotificationStatusEnum(Integer status) {
        this.status = status;
    }
}
