package com.xuan.forum.dto;

import lombok.Data;

import java.util.List;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/19
 * @描述： 标签 Dto类型
 */
@Data
public class TagDto {
    /** 标签id */
    private String tagId;
    /** 标签类型名称 */
    private String categoryName;
    /** 标签内容 */
    private List<String> tags;

    public TagDto( String tagId ,String categoryName, List<String> tags) {
        this.tagId = tagId;
        this.categoryName = categoryName;
        this.tags = tags;
    }
}
