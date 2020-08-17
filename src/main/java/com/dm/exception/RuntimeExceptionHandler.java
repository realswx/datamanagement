package com.dm.exception;

import com.dm.enums.ResponseEnum;
import com.dm.vo.ResponseVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Objects;

/**
 * 捕获异常，统一异常返回的格式
 */
//Controller增强器,可对controller中被 @RequestMapping注解的方法加一些逻辑处理。最常用的就是异常处理
@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class) //捕获异常的类型
    @ResponseBody //返回json格式
//    @ResponseStatus(HttpStatus.FORBIDDEN) //更改返回的HTTP状态码
    public ResponseVo handle(RuntimeException e){
        return ResponseVo.error(ResponseEnum.ERROR, e.getMessage());
    }


//    @ExceptionHandler(IOException.class)
//    @ResponseBody
//    public ResponseVo iOExceptionHandle(IOException e){
//        return ResponseVo.error(ResponseEnum.ERROR, e.getMessage());
//    }


    @ExceptionHandler(UserLoginException.class) //捕获异常，异常在UserLoginInceptor中抛出
    @ResponseBody
    public ResponseVo userLoginHandle(){
        return ResponseVo.error(ResponseEnum.NEED_LOGIN); //异常处理
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVo notValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Objects.requireNonNull(bindingResult.getFieldError());
        return ResponseVo.error(ResponseEnum.PARAM_ERROR,
                bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage()
        );
    }


}
