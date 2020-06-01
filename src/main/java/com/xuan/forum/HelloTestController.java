package com.xuan.forum;

import com.xuan.forum.dto.PaginationDto;
import com.xuan.forum.dto.QuestionQueryDto;
import com.xuan.forum.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/3/8
 * @描述：
 */
@Controller
public class HelloTestController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "空的默认值") String name, Model model) {
        System.out.println("接受到的name：" + name);
        model.addAttribute("name", name);
        return "hello";
    }

    //默认跳转路径(首页)
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page, //当前页数
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "serach", required = false) String serach
    ) {
        PaginationDto pagination;
        if (StringUtils.isEmpty(serach)){
            pagination = questionService.list(page, size);
        }else {
            QuestionQueryDto questionQueryDto = new QuestionQueryDto(serach, page, size);
            pagination = questionService.list(questionQueryDto);
            model.addAttribute("serach", serach);
        }
        model.addAttribute("pagination", pagination);
        return "hello";
    }
}
