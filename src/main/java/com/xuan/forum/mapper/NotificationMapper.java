package com.xuan.forum.mapper;

import com.xuan.forum.model.Notification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notification record);

    int insertSelective(Notification record);

    Notification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);

    /**
     * 根据接受通知的人，查询通知总数量
     * @param receiver 接受通知的人
     * @return
     */
    Integer countByReceiver(Integer receiver);

    /**
     * 分页查询通知
     * @param status 0 未读 ； 1 已读
     * @param receiver 接受通知的人
     * @param offset 当前页
     * @param size 每页展示数量
     * @return
     */
    List<Notification> pageListByReceiver(@Param("status") Integer status,@Param("receiver") Integer receiver, @Param("offset")Integer offset, @Param("size")Integer size);

    Integer countByStatus(@Param("status")Integer status);
}