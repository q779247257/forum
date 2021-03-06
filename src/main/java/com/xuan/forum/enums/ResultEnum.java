package com.xuan.forum.enums;

import lombok.Getter;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/29
 */

@Getter
public enum  ResultEnum {
    /** 未登录请求评论 */
    NO_LOGIN(2002,"没有登录不可以进行评论,是否要进行登录"),
    /** 评论时 父级id为null */
    NO_SELECT_QUESTION_OR_COMMENT(2001,"没有选择问题或评论进行评论"),
    /** 评论类型错误 */
    COMMENT_TYPE_ERROR(2003,"评论类型错误或不存在"),
    /** 评论不存在 */
    PARENT_COMMENT_NULL(2004,"你评论的评论不存在或已删除"),
    /** 评论的问题不存在 */
    PARENT_QUESTION_NULL(2005,"你评论的问题不存在或已删除"),
    /** 输入问题不能为null */
    VALUE_NOT_CAN_NULL(2006,"输入内容不能为空"),
    /** 读到了不属于自己的通知  */
    NOTIFICATION_NOT_OWN(2007,"兄弟？你还想读别人的通知?做梦了?"),
    NOTIFICATION_IS_NU(2008,"鬼知道你要看的是什么通知?"),
    GITHUB_LOGIN_ERROR(2009,"Github连接失败，请稍后再试试吧"),
    ;

    /** 状态码 */
    private Integer code;
    /** 状态内容 */
    private String meeage;

    /**
     * @param code 状态码
     * @param meeage 状态内容
     */
    ResultEnum(Integer code, String meeage) {
        
        this.code = code;
        this.meeage = meeage;
    }


}
