package com.dm.controller;

import com.dm.consts.DMConst;
import com.dm.form.UserLoginForm;
import com.dm.pojo.User;
import com.dm.service.IUserService;
import com.dm.vo.ResponseVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "User模块类")
public class UserController {

    @Autowired
    private IUserService userService;



    @ApiOperation(value = "login登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", required = true),
            @ApiImplicitParam(name = "password", required = true),
            @ApiImplicitParam(name = "session", required = false)
    })
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=11,message="用户名或密码错误")
    })
    /**
     * 登录
     * @param userLoginForm
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  //BindingResult bindingResult,
                                  HttpSession session){
        //防止username, password有空值
//        if (bindingResult.hasErrors()){
//            //控制台上打印
//            log.error("登录提交的参数有误，{} {}",
//                    bindingResult.getFieldError().getField(), //对注解@NotBlank，显示错误的字段
//                    bindingResult.getFieldError().getDefaultMessage());//注解@NotBlank上面的信息，这里默认是“不能为空”
//            return ResponseVo.error(ResponseEnum.PARAM_ERROR,
//                    bindingResult);
//        }

        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        //设置Session
        session.setAttribute(DMConst.CURRENT_USER, userResponseVo.getData());
        log.info("/user/login sessionId={}", session.getId());

        return userResponseVo;
    }



    /**
     * 获取登录用户信息
     * @param session
     * @return
     */
    @GetMapping("/info")
    public ResponseVo<User> userInfo(HttpSession session){
        log.info("/user/info sessionId={}", session.getId());
        User user = (User) session.getAttribute(DMConst.CURRENT_USER);
        //已经通过拦截器判断登录状态
//        if (user == null) { //未登录情况下
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
//        }
        return ResponseVo.success(user);
    }


    /**
     * 退出登录
     * @param session
     * @return
     */
    @PostMapping("/logout")
    public ResponseVo<User> logout(HttpSession session){
        log.info("/user/logout sessionId={}", session.getId());
        //已经通过拦截器判断登录状态
//        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
//        if (user == null) { //未登录情况下
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
//        }

        //移除session，退出登录
        session.removeAttribute(DMConst.CURRENT_USER);

        return ResponseVo.success();

    }



}
