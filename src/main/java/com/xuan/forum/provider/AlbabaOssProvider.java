package com.xuan.forum.provider;

import com.aliyun.oss.OSSClient;
import com.xuan.forum.utils.UuidUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: 轩轩
 * @Date: 2020/3/10 11:48
 * @description: 阿里云OSS对象存储上传工具
 */
@Component
public class AlbabaOssProvider {
    public static String picOSS(MultipartFile uploadFile)  {
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com/";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
        // https://ram.console.aliyun.com 创建
        String accessKeyId = "LTAI4FkcLtwaSxLUcy7bfY9B";
        String accessKeySecret = "KoNNamQoL5rFdKHLQhYSrg2z5w18Vb";
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //获取文件后缀
        String suffix = "."+uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".") + 1);
//      如果文件名一样，阿里云会直接覆盖，这里我们使用 UUID 等一些分布式id生成器 来作为文件名
        String filename =  UuidUtils.getUuid()+suffix;
        // 上传
        try {
            ossClient.putObject("xuanandjava", filename, new ByteArrayInputStream(uploadFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭client
        ossClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl("xuanandjava", filename, expiration).toString();
        System.out.println("url:"+url);
        return url;
    }
}
