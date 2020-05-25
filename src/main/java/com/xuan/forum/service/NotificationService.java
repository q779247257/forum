package com.xuan.forum.service;

import com.xuan.forum.commonException.MyCustomException;
import com.xuan.forum.dto.NotificationDto;
import com.xuan.forum.dto.PaginationDto;
import com.xuan.forum.enums.NotificationStatusEnum;
import com.xuan.forum.enums.NotificationTypeEnum;
import com.xuan.forum.enums.ResultEnum;
import com.xuan.forum.mapper.CommentMapper;
import com.xuan.forum.mapper.NotificationMapper;
import com.xuan.forum.mapper.QuestionMapper;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.Comment;
import com.xuan.forum.model.Notification;
import com.xuan.forum.model.Question;
import com.xuan.forum.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 展示未读通知
     * @param userId 用户id
     * @param page 当前页数
     * @param size 每页展示数量
     * @return
     */
    public PaginationDto<NotificationDto> list(Integer userId, Integer page, Integer size) {
        /** 获取所有的数量 */
        Integer totalCount = notificationMapper.countByReceiver(userId);

        PaginationDto<NotificationDto> paginationDto = new PaginationDto<NotificationDto>();
        //设置分页参数
        paginationDto.setPagination(totalCount,page,size);
        //获取数据库查询 offset 参数
        Integer offset = size * (paginationDto.getPage() - 1);
        //查询分页所展示的数据（根据接受通知的人）
        List<Notification> notifications = notificationMapper.pageListByReceiver(null,userId,offset,size);

        List<NotificationDto> notificationDtos = new ArrayList<>();
        //遍历分页查询到的数据
        for (Notification notification : notifications){
            NotificationDto notificationDto = new NotificationDto();
            //对象copy
            BeanUtils.copyProperties(notification,notificationDto);
            //获取接受评论的人
            User user = userMapper.selectByPrimaryKey(notification.getReceiver());
            notificationDto.setNotifier(user);

            String outerTitle = null;
            //todo 判断通知类型
            if (notificationDto.getType() == NotificationTypeEnum.REPLY_QUESTION.getType()){
                //通知类型 是问题
                Question question = questionMapper.selectByPrimaryKey(notification.getOuterId());
                outerTitle = question.getTitle();
                notificationDto.setQuestionId(question.getId());
            }else if (notificationDto.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()){
                //通知类型 是评论

                //获取评论
                Comment comment = commentMapper.selectByPrimaryKey(notification.getOuterId());
                //获取父评论
                Comment ParentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
                outerTitle = ParentComment.getContent();
                //父评论的id是文章id
                notificationDto.setQuestionId(ParentComment.getParentId());
            }else {
                outerTitle = "通知类型可能有一点问题";
            }

            //设置标题
            notificationDto.setOuterTitle(outerTitle);
            notificationDtos.add(notificationDto);
        }
        //设置页面承载元素
        paginationDto.setData(notificationDtos);
        paginationDto.setDataCount(totalCount);
        return paginationDto;
    }

    /**
     * 根据状态查询通知数量
     * @param status  0 未读 ； 1 已读
     * @return
     */
    public Integer coutOfStatus(Integer status) {
        return notificationMapper.countByStatus(status);
    }


    /**
     * 读取通知
     * @param notificationId 通知id
     * @param user 用户
     * @return
     */
    public NotificationDto read(Integer notificationId, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(notificationId);
        //校验通知是否属于自己
        if (notification.getReceiver() != user.getId()) throw new MyCustomException(ResultEnum.NOTIFICATION_NOT_OWN);
        NotificationDto notificationDto = new NotificationDto();
        BeanUtils.copyProperties(notification,notificationDto);

        String outerTitle = null;
        if (notificationDto.getType() == NotificationTypeEnum.REPLY_QUESTION.getType()){
            //通知类型 是问题
            Question question = questionMapper.selectByPrimaryKey(notification.getOuterId());
            outerTitle = question.getTitle();
            notificationDto.setQuestionId(question.getId());
        }else if (notificationDto.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()){
            //通知类型 是评论

            //获取评论
            Comment comment = commentMapper.selectByPrimaryKey(notification.getOuterId());
            //获取父评论
            Comment ParentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            outerTitle = ParentComment.getContent();
            //父评论的id是文章id
            notificationDto.setQuestionId(ParentComment.getParentId());
        }else {
            outerTitle = "通知类型可能有一点问题";
        }
        //todo 更新通知  已读状态

        return notificationDto;
    }
}
