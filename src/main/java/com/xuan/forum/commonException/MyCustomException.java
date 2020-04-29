package com.xuan.forum.commonException;

import com.xuan.forum.enums.ResultEnum;

import lombok.Getter;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/29
 * @描述：
 */
@Getter
public class MyCustomException extends RuntimeException {
    private Integer code;
    private String message;

    public MyCustomException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MyCustomException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMeeage();
    }
}
