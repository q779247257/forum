package com.xuan.forum;

import com.alibaba.fastjson.JSON;
import com.xuan.forum.dto.CommentDto;
import com.xuan.forum.dto.PaginationDto;
import com.xuan.forum.mapper.QuestionMapper;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.Question;
import com.xuan.forum.model.User;
import com.xuan.forum.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class ForumApplicationTests {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    /** 页面数据展示*/
    @Test
    public void contextLoads() {
        AtomicInteger cout = new AtomicInteger();
        questionService.list(1,10).getQuestionDtoList().forEach(item -> {
            System.out.println(item);
        });
    }

    /** 分页测试 */
    @Test
    public void testPageLimit(){

        System.out.println("第1页数据--------------------------------------------");
        PaginationDto pageInfo = questionService.list(1, 5);
        pageInfo.getQuestionDtoList().forEach(item -> {
            System.out.println(item);
        });
    }
    @Test
    public void testUser(){
        User user = userMapper.findByName("q779247257");
        System.out.println("查询到数据："+user);
    }

    @Test
    public void ObjectToJson(){
        User user = new User();
        user.setToken("123123213");
        user.setBio("这是描述");
        user.setName("xuanxuna");
        String json = JSON.toJSONString(user);
        System.out.println("转换之后的json:"+json);
    }


}
