package com.xuan.forum.mapper;

import com.xuan.forum.model.User;
import org.apache.ibatis.annotations.Insert;

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
}
