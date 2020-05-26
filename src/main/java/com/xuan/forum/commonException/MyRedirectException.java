package com.xuan.forum.commonException;

import com.xuan.forum.enums.ResultEnum;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/26
 * @描述：
 */
public class MyRedirectException  extends RuntimeException {
    private Integer code;
    private String message;

    public MyRedirectException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MyRedirectException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMeeage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
