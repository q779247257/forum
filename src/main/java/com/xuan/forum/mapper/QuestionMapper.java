package com.xuan.forum.mapper;

import com.xuan.forum.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    Integer updateByPrimaryKey(Question record);

    Integer count();

    List<Question> pageList(@Param("offset") Integer offset, @Param("size")Integer size);

    Integer countByUserId(String githubUsername);

    List<Question> pageListByUserId(@Param("githubUsername") String githubUsername, @Param("offset") Integer offset, @Param("size") Integer size);
}