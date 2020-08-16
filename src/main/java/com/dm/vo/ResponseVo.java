package com.dm.vo;

import com.dm.enums.ResponseEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.Objects;

/**
 * 返回给前端的信息
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL) //只显示非空的数据
public class ResponseVo<T> {

    private Integer status;
    private String msg;
    private T data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    //成功状态返回
    public static <T> ResponseVo<T> success(){
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }
    public static <T> ResponseVo<T> success(T data){
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), data);
    }


    //错误。传入ResponseEnum可直接使用枚举中的东西
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum){
        return new ResponseVo<>(responseEnum.getCode(),
                responseEnum.getDesc());
    }
    //错误。使用BindingResult的信息作为msg
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, BindingResult bindingResult){
        return new ResponseVo<>(responseEnum.getCode(),
                //不能为空
                Objects.requireNonNull(bindingResult.getFieldError().getField()+" "+bindingResult.getFieldError().getDefaultMessage()));
    }
    //错误。使用自定义的信息作为msg
    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, String msg){
        return new ResponseVo<>(responseEnum.getCode(), msg);
    }



}
