package com.xuan.forum.dto;

import com.xuan.forum.model.User;
import lombok.Data;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/25
 * @描述： 通知Dto类
 */
@Data
public class NotificationDto {
    /** 通知id */
    private Integer id;

    /** 新增时间 */
    private Long gmtCreate;

    /** 0 未读 ； 1 已读 */
    private Integer status;

    /** 回复的 评论id 或 问题id*/
    private Integer outerId;

    /** 问题id 用于评论的时候展示 */
    private Integer questionId;

    /** 通知的人 */
    private User notifier;

    /** 外部的名字 */
    private String outerTitle;

    /** 类型 1 回复问题 */
    private Integer type;
}
