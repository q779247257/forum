package com.xuan.forum.controller;

import com.xuan.forum.dto.FileDto;
import com.xuan.forum.provider.AlbabaOssProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/26
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private AlbabaOssProvider ossProvider;

    //文件上传
    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
        String fileName = file.getOriginalFilename();
        if (ossProvider.checkSuffix(fileName)){
            String fileUrl = ossProvider.picOSS(file);
            return new FileDto(1,"上传成功",fileUrl);
        }else {
            return new FileDto(0,"可能不支持您上传的文件格式",null);
        }

    }
}
