package com.xuan.forum.mapper;

import com.xuan.forum.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/16
 * @描述：
 */
public interface QuestionMapper {

    @Insert("INSERT INTO question ( title, description, gmt_create, gmt_modified, creator, tag )VALUES(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator}, #{tag} )")
    void insert(Question question);

    /** 查询全部的文章 问题*/
    @Select("select * from question ORDER BY gmt_modified DESC ")
    @Results(id ="questionMap" , value = {
            @Result(id=true,column = "id",property = "id" ),
            @Result(column = "title",property = "title"),
            @Result(column = "description",property = "description"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "comment_count",property = "commentCount"),
            @Result(column = "view_cout",property = "viewCout"),
            @Result(column = "like_count",property = "likeCount"),
            @Result(column = "tag",property = "tag"),

    })
    List<Question> list();


    @Select("SELECT * FROM question  ORDER BY gmt_modified DESC LIMIT #{offset} , #{size}")
    @ResultMap("questionMap")
    List<Question> pageList(@Param("offset") Integer offset, @Param("size") Integer size);


    /** 查询总数 */
    @Select("select count(1) from question")
    Integer count();
}
