package com.xuan.forum.mapper;

import com.xuan.forum.model.User;
import org.apache.ibatis.annotations.*;

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
            "(name,account_id,token,gmt_create,gmt_modified,bio,avatar_url)" +
            " values " +
            "(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
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
            " gmt_modified, " +
            " bio " +
            " FROM user WHERE token = #{token}")
    @Results(id ="userMap" , value = {
                    @Result(id=true,column = "id",property = "id" ),
                    @Result(column = "name",property = "name"),
                    @Result(column = "account_id",property = "accountId"),
                    @Result(column = "token",property = "token"),
                    @Result(column = "gmt_create",property = "gmtCreate"),
                    @Result(column = "gmt_modified",property = "gmtModified"),
                    @Result(column = "bio",property = "bio"),
                    @Result(column = "avatar_url",property = "avatarUrl")

            })
    User findByToken(@Param("token") String token);

    @Select("SELECT" +
            " id," +
            " account_id," +
            " name," +
            " token," +
            " gmt_create," +
            " gmt_modified, " +
            " bio, " +
            "avatar_url " +
            " FROM user WHERE id = #{id}")
    @ResultMap("userMap")
    User findById(@Param("id")Integer id);
}
