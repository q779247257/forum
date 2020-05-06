package com.xuan.forum.controller;

import com.xuan.forum.dto.CommentDto;
import com.xuan.forum.dto.ResultDto;
import com.xuan.forum.enums.ResultEnum;
import com.xuan.forum.model.Comment;
import com.xuan.forum.model.User;
import com.xuan.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/28
 * @描述： 评论controller
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 新增评论
     * @param commentDto
     */
    @PostMapping("/increase")
    @ResponseBody
    public Object comment(@RequestBody CommentDto commentDto, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        //登录判断
        if (user == null ){
            return ResultDto.errorOf(ResultEnum.NO_LOGIN);
        }
        //父级id 判断 非空
        if (commentDto.getParentId() == null || commentDto.getParentId() == 0){
            return ResultDto.errorOf(ResultEnum.NO_SELECT_QUESTION_OR_COMMENT);
        }
        //评论类型 判断
        if (commentDto.getType() == null || (commentDto.getType() != 1 && commentDto.getType() != 2) ){
            return ResultDto.errorOf(ResultEnum.COMMENT_TYPE_ERROR);
        }
        if (commentDto.getContent() == null || StringUtils.isEmpty(commentDto.getContent())){
            return ResultDto.errorOf(ResultEnum.VALUE_NOT_CAN_NULL);
        }


        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());//设置父类id
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());//设置类型
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());//评论人的id
        comment.setLikeCount(0);//点赞数
        commentService.insert(comment);
        return ResultDto.okOF();
    }
}
