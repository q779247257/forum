package com.xuan.forum.service;

import com.xuan.forum.commonException.MyCustomException;
import com.xuan.forum.dto.CommentCreateDto;
import com.xuan.forum.enums.CommentTypeEnum;
import com.xuan.forum.enums.ResultEnum;
import com.xuan.forum.mapper.CommentMapper;
import com.xuan.forum.mapper.QuestionMapper;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.Comment;
import com.xuan.forum.model.Question;
import com.xuan.forum.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private UserMapper userMapper;

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

    /**
     * 获取指定文章的评论
     * @param questionId 文章id
     */
    public List<CommentCreateDto> listByQuestionId(Integer questionId) {
        //查询一级评论
        List<Comment> commentList = commentMapper.findByQestionIdOrType(questionId,CommentTypeEnum.QUESTION.getType());

        //校验是否有评论
        if (commentList.isEmpty()) return new ArrayList<>();

        //创建返回的数据类
        List<CommentCreateDto> commentCreateDtos = new ArrayList<>();

        // todo 设置 comment数据
        commentList.stream().forEach(item -> {
            CommentCreateDto commentCreateDto = new CommentCreateDto();
            BeanUtils.copyProperties(item,commentCreateDto);
            commentCreateDtos.add(commentCreateDto);
        });
        //todo 设置 User数据
        commentCreateDtos.stream().forEach(item -> {
            User user = userMapper.selectByPrimaryKey(item.getCommentator());
            item.setUser(user);
        });
        return commentCreateDtos;
    }
}
