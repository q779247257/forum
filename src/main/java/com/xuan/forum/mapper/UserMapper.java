package com.xuan.forum.mapper;

import com.xuan.forum.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 * @描述：
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) " +
            "values " +
            "               (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
     void insert(User user);
}
