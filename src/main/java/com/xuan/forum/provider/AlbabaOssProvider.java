package com.xuan.forum.provider;

import com.aliyun.oss.OSSClient;
import com.xuan.forum.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
    @Value("${alibaba.oss.endpoint}")
    private String endpoint;
    @Value("${alibaba.access_key_id}")
    private String accessKeyId;
    @Value("${alibaba.access.key_secret}")
    private String accessKeySecret;
    @Value("${alibaba.bucket.name}")
    private String bucketName;

    public  String picOSS(MultipartFile uploadFile)  {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //获取文件后缀
        String suffix = "."+uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".") + 1);
//      如果文件名一样，阿里云会直接覆盖，这里我们使用 UUID 等一些分布式id生成器 来作为文件名
        String filename =  UuidUtils.getUuid()+suffix;
        // 上传
        try {
            ossClient.putObject(bucketName, filename, new ByteArrayInputStream(uploadFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭client
        ossClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl(bucketName, filename, expiration).toString();
        return url;
    }


    /**
     * 校验图片是否合法
     * @param imgName 图片名称
     * @return ture 合法  false 不合法
     */
    public boolean checkSuffix(String imgName) {
        Boolean flag =false;
        //图片格式
        String[] FILETYPES = new String[]{
                ".jpg", ".bmp", ".jpeg", ".png", ".gif",
                ".JPG", ".BMP", ".JPEG", ".PNG", ".GIF"
        };
        if(!StringUtils.isEmpty(imgName)){
            for (int i = 0; i < FILETYPES.length; i++) {
                String fileType = FILETYPES[i];
                if (imgName.endsWith(fileType)) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }
}
