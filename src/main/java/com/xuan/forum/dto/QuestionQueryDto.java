package com.xuan.forum.dto;

import lombok.Data;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/28
 * @描述： 文章搜索 Dto
 */
@Data
public class QuestionQueryDto {
    /** 搜索内容 */
    private String serach;
    /** 当前页 */
    private Integer page;
    /** 每页展示数量 */
    private Integer size;

    public QuestionQueryDto(String serach, Integer page, Integer size) {
        this.serach = serach;
        this.page = page;
        this.size = size;
    }
}
