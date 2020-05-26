package com.xuan.forum.dto;

import lombok.Data;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/26
 * @描述：
 */
@Data
public class FileDto {
    /** 0 表示上传失败，1 表示上传成功 */
    private Integer success;
    /** 提示的信息，上传成功或上传失败及错误信息等。 */
    private String message;
    /** 图片地址 上传成功时才返回 */
    private String url;

    public FileDto(Integer success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }
}
