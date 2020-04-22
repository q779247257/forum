package com.xuan.forum.service;

import com.xuan.forum.dto.PaginationDto;
import com.xuan.forum.dto.QuestionDto;
import com.xuan.forum.mapper.QuestionMapper;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.Question;
import com.xuan.forum.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/17
 * @描述：
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> list() {
        List<Question> questionList = questionMapper.list();
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question question : questionList){
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            //对象copy
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
         }
        return questionDtos;

    }

    /**
     * 分页数据 service层
     * @param page 页数
     * @param size 每页展示数量
     * @return
     */
    public PaginationDto list(Integer page, Integer size) {

        /** 获取所有的数量 */
        Integer totalCount = questionMapper.count();

        PaginationDto paginationDto = new PaginationDto();
        //设置分页参数
        paginationDto.setPagination(totalCount,page,size);
        //获取数据库查询 offset
        Integer offset = size * (paginationDto.getPage() - 1);
        //查询分页所展示的数据
        List<Question> questionList = questionMapper.pageList(offset,size);
        List<QuestionDto> questionDtoLit = new ArrayList<>();
        for (Question question : questionList){
            //根据id查询用户
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            //对象copy
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoLit.add(questionDto);
        }
        //设置页面承载元素
        paginationDto.setQuestionDtoList(questionDtoLit);
        return paginationDto;

    }

    /**
     * 展示某个用户的问题
     * @param userId 用户id
     * @param page 页数
     * @param size 每页展示数量
     */
    public PaginationDto list(Integer userId, Integer page, Integer size) {
        /** 获取所有的数量 */
        Integer totalCount = questionMapper.countByUserId(userId);

        PaginationDto paginationDto = new PaginationDto();
        //设置分页参数
        paginationDto.setPagination(totalCount,page,size);
        //获取数据库查询 offset 参数
        Integer offset = size * (paginationDto.getPage() - 1);
        //查询分页所展示的数据
        List<Question> questionList = questionMapper.pageListByUserId(userId,offset,size);

        List<QuestionDto> questionDtoLit = new ArrayList<>();
        //遍历分页查询到的数据
        for (Question question : questionList){
            //根据id查询用户
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            //对象copy
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoLit.add(questionDto);
        }
        //设置页面承载元素
        paginationDto.setQuestionDtoList(questionDtoLit);
        paginationDto.setQuestionTotalCount(totalCount);
        return paginationDto;
    }
}
