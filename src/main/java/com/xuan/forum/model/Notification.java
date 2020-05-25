package com.xuan.forum.model;

import lombok.Data;

@Data
public class Notification {

    /** 通知id */
    private Integer id;

    /** 发起通知的人 */
    private Integer notifier;

    /** 接受通知的人 */
    private Integer receiver;

    /** 回复的 评论id 或 问题id*/
    private Integer outerid;

    /** 类型：1回复 */
    private Integer type;

    /** 新增时间 */
    private Long gmtCreate;

    /** 0 未读 ； 1 已读 */
    private Integer status;


}