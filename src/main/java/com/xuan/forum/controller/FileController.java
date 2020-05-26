package com.xuan.forum.controller;

import com.xuan.forum.dto.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/26
 */
@Controller
@RequestMapping("/file")
public class FileController {
    //文件长传
    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(){
        return new FileDto(1,"上传成功","/img/recommend.jpg");
    }
}
