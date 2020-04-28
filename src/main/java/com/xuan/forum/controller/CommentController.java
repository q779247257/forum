package com.xuan.forum.controller;

import com.xuan.forum.dto.CommentDto;
import com.xuan.forum.mapper.CommentMapper;
import com.xuan.forum.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/28
 * @描述： 评论controller
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping("/increase")
    @ResponseBody
    public Object comment(@RequestBody CommentDto commentDto){
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());//设置父类id
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());//设置类型
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(1);//评论人的id
        comment.setLikeCount(0);//点赞数
        commentMapper.insertSelective(comment);
        return comment;
    }
}
