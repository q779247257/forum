package com.xuan.forum.mapper;

import com.xuan.forum.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 * @描述：
 */
public interface UserMapper {
    /**
     * 根据用户插入
     * @param user
     */
    @Insert("insert into user " +
            "(name,account_id,token,gmt_create,gmt_modified)" +
            " values " +
            "(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
     void insert(User user);

    /**
     * 根据token查询用户
     * @param token 查询的token
     * @return
     */

    @Select("SELECT" +
            " id," +
            " account_id," +
            " name," +
            " token," +
            " gmt_create," +
            " gmt_modified" +
            " FROM user" +
            " WHERE token = #{token}")
    User findByToken(@Param("token") String token);
}
