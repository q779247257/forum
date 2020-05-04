package com.xuan.forum.mapper;

import com.xuan.forum.model.Comment;
import com.xuan.forum.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByToken(String token);

    User findByName(String name);

    /**
     * 批量查询集合id中所有的用户
     * @param userIds 批量查询的id
     */
    List<User> findByListId( @Param("userIds") List<Integer> userIds);

}