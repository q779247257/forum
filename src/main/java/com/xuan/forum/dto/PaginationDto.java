package com.xuan.forum.dto;

import lombok.Data;

import java.util.ArrayList;
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
    /** 数据的数量 */
    private Integer questionTotalCount;
    /** 当前页 */
    private Integer page;
    /** 最大页 */
    private Integer totalPage;
    /** 页码组 */
    private List<Integer> pages = new ArrayList<>();

    /**
     * 设置分页的相关参数
     * @param totalCount 总条数
     * @param page 当前页
     * @param size 每页展示数量
     */
    public void setPagination(Integer totalCount, Integer page, Integer size) {

        /** 总页数 */
        Integer totalPage;
        if (totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        this.totalPage = totalPage;
        //最小页数为1
        if (page < 1){
            page = 1;
        }
        //最大页数 为总页面
        if (page >  this.totalPage){
            page =  this.totalPage;
            //每页数据的时候 totalPage 会等于0
            if (page == 0) page = 1;
        }

        this.page = page;

        //当前页添加到页组
        pages.add(page);
        for (int i=1 ; i<=3 ; i++){
            if (page - i > 0 ){
                pages.add(0 ,page - i);
            }

            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if (page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }

        //是否展示下一页
        if (page == totalPage){
            showNext = false;
        }else {
            showNext = true;
        }

        //是否展示 第一页
        if (pages.contains(1)){
            showFirsPage = false;
        }else {
            showFirsPage = true;
        }

        //是否展示最后一页
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
