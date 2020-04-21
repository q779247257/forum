package com.xuan.forum.dto;

import lombok.Data;

import java.util.List;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/20
 * @描述： 分页DTO类
 */
@Data
public class PaginationDto {
    /** 页面承载的元素 */
    private List<QuestionDto> questionDtoList;
    /** 是否有 向前（上一页）按钮 */
    private boolean showPrevious;
    /** 是否有第一页按钮 */
    private boolean showFirsPage;
    /** 是否有下一页 */
    private boolean showNext;
    /** 是否有末页 */
    private boolean showEndPage;
    /** 当前页 */
    private Integer page;

    /** 页码组 */
    private List<Integer> pages;
}
