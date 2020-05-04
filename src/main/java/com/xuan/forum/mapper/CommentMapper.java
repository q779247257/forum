package com.xuan.forum.mapper;

import com.xuan.forum.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 根据 文章id 和 类型 查询评论
     * @param questionId： 文章id
     * @param type：  父类类型 1：父类id为问题  2：父类id为回复
     * @return
     */
    List<Comment> findByQestionIdOrType(@Param("questionId") Integer questionId, @Param("type")Integer type);
}