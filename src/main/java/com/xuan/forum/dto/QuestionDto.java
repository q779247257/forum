package com.xuan.forum.dto;

import com.xuan.forum.model.User;
import lombok.Data;

import java.util.Date;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/17
 * @描述：
 */
@Data
public class QuestionDto {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 文章名称
     */
    private String title;
    /**
     * 文章内容
     */
    private String description;
    /**
     * 新增时间
     */
    private Long gmtCreate;
    /**更新时间*/
    private Long gmtModified;
    /**问题创建人的github账户*/
    private String creator;
    /**评论数*/
    private Integer commentCount;
    /**阅读数*/
    private Integer viewCout;
    /**点赞数*/
    private Integer likeCount;
    /**标签*/
    private String tag;
    /** 文章作者信息 */
    private User user;


    public Date getDateByLong(Long paramLong){
        return new Date(paramLong);
    }

}
