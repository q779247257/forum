package com.xuan.forum.dto;

import com.xuan.forum.commonException.MyCustomException;
import com.xuan.forum.enums.ResultEnum;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.ORB;

import java.lang.ref.PhantomReference;

@Data
public class ResultDto<T> {
    /** 状态码 */
    private Integer code;
    /** 提示信息 */
    private String message;

    private T Data;

    public static ResultDto errorOf(Integer code , String message){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }


    public static ResultDto errorOf(ResultEnum resultEnum){
        return errorOf(resultEnum.getCode(),resultEnum.getMeeage());
    }

    public static ResultDto errorOf(MyCustomException exception){
        return errorOf(exception.getCode(),exception.getMessage());
    }

    public static ResultDto okOF(){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

    public static <T> ResultDto okOF(T data){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        resultDto.setData(data);
        return resultDto;
    }
}
