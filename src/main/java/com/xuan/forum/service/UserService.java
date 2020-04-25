package com.xuan.forum.service;

import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/25
 */
@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public User createOrUpdate(User user) {
        //根据账户拿到user
       User dbUser =  userMapper.findByName(user.getName());
       if (dbUser == null){//不存在做插入操作
           //设置更新时间为新增时间
           user.setGmtModified(user.getGmtCreate());
           userMapper.insert(user);
           logger.info("首次登陆成功，用户放入的Session的信息："+user);
           return user;
       }else {
           //设置当前系统的时间戳
           dbUser.setGmtModified(System.currentTimeMillis());
           //更新头像
           dbUser.setAvatarUrl(user.getAvatarUrl());
           //更新token
            dbUser.setToken(user.getToken());
           //存在，做更新操作
           userMapper.updateById(dbUser);
           logger.info("用户登录成更新token："+dbUser.getToken());
           return dbUser;
       }

    }
}
