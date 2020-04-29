package com.xuan.forum.service;

import com.xuan.forum.commonException.MyCustomException;
import com.xuan.forum.commonException.MyExceptionHander;
import com.xuan.forum.dto.ResultDto;
import com.xuan.forum.enums.CommentTypeEnum;
import com.xuan.forum.enums.ResultEnum;
import com.xuan.forum.mapper.CommentMapper;
import com.xuan.forum.mapper.QuestionMapper;
import com.xuan.forum.model.Comment;
import com.xuan.forum.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/29
 * @描述：
 */
@Service
public class CommentService {

    private static Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 增加评论
     */
    @Transactional
    public void insert(Comment comment) {
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //todo 回复评论 id 2

            //获取父评论
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            //父评论 判断
            if (parentComment == null) throw new MyCustomException(ResultEnum.PARENT_COMMENT_NULL);

            //增加评论
            commentMapper.insert(comment);
        }else if (comment.getType() == CommentTypeEnum.QUESTION.getType()){
            //todo 回复问题 id 1


            //获取父类的问题
            Question parentQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
            //父类 问题判断
            if (parentQuestion == null) throw new MyCustomException(ResultEnum.PARENT_QUESTION_NULL);

            //增加评论
            commentMapper.insert(comment);
            //评论数加1
            questionMapper.incComment(comment.getParentId());
        }
    }
}
