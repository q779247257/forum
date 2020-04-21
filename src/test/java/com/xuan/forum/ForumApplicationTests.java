package com.xuan.forum;

import com.xuan.forum.dto.PaginationDto;
import com.xuan.forum.mapper.QuestionMapper;
import com.xuan.forum.model.Question;
import com.xuan.forum.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class ForumApplicationTests {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;


    /** 页面数据展示*/
    @Test
    public void contextLoads() {
        AtomicInteger cout = new AtomicInteger();
        questionService.list().forEach(item -> {
            System.out.println(item);
            System.out.println(cout +"次输出数据");
            cout.addAndGet(1);
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

}
