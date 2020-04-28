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
            //根据github账户查询用户
            User user = userMapper.findByName(question.getCreator());

            QuestionDto questionDto = new QuestionDto();
            //对象copy
            BeanUtils.copyProperties(question,questionDto);
            if (null != user){
                questionDto.setUser(user);
            }else {
                questionDto.setUser(new User());

            }

            questionDtoLit.add(questionDto);
        }
        //设置页面承载元素
        paginationDto.setQuestionDtoList(questionDtoLit);
        return paginationDto;

    }

    /**
     * 展示某个用户的问题
     * @param userName 用户账户
     * @param page 页数
     * @param size 每页展示数量
     */
    public PaginationDto list(String userName, Integer page, Integer size) {
        /** 获取所有的数量 */
        Integer totalCount = questionMapper.countByUserId(userName);

        PaginationDto paginationDto = new PaginationDto();
        //设置分页参数
        paginationDto.setPagination(totalCount,page,size);
        //获取数据库查询 offset 参数
        Integer offset = size * (paginationDto.getPage() - 1);
        //查询分页所展示的数据（根据github账号）
        List<Question> questionList = questionMapper.pageListByUserId(userName,offset,size);

        List<QuestionDto> questionDtoLit = new ArrayList<>();
        //遍历分页查询到的数据
        for (Question question : questionList){
            //根据git账户查询用户
            User user = userMapper.findByName(question.getCreator());
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

    /**
     * 根据id获取问题
     * @param id 问题id
     */
    public QuestionDto getById(Integer id) {
       Question question =  questionMapper.selectByPrimaryKey(id);

        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        //根据账户查询User
        User byName = userMapper.findByName(questionDto.getCreator());
        questionDto.setUser(byName);
        return questionDto;
    }

    /**
     * 问题新增或修改
     * @param question
     */
    public void createOrUpdate(Question question) {
        //当前时间戳
        long dataLong = System.currentTimeMillis();
        if (question.getId() == null){
            question.setGmtCreate(dataLong);
            question.setGmtModified(dataLong);
            //等于null  第一次发起问题 新增问题
            questionMapper.insertSelective(question);
        }else {
            question.setGmtModified(dataLong);
            //id 不是空 代表问题已经存在，更新问题
            questionMapper.updateByPrimaryKeySelective(question);
        }
    }

    /**
     * 问题阅读数+1
     * @param questionId 问题的id
     */
    public void incView(Integer questionId) {
            questionMapper.incView(questionId);
    }
}
