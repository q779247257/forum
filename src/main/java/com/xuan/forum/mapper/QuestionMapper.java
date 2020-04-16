package com.xuan.forum.mapper;

import com.xuan.forum.model.Question;
import org.apache.ibatis.annotations.*;
/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/16
 * @描述：
 */
public interface QuestionMapper {


    @Insert("INSERT INTO question ( title, description, gmt_create, gmt_modified, creator, tag )VALUES(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator}, #{tag} )")
    void insert(Question question);
}
