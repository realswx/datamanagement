package com.dm.controller;

import com.dm.consts.DMConst;
import com.dm.enums.ResponseEnum;
import com.dm.form.UserAddForm;
import com.dm.form.UserLoginForm;
import com.dm.log.annotation.Log;
import com.dm.pojo.User;
import com.dm.service.IUserService;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.dm.consts.DMConst.CURRENT_USER;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "User模块类")
public class UserController {

    @Autowired
    private IUserService userService;



    @Log("登录")
    @ApiOperation(value = "login登录")
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
                                  @ApiIgnore HttpSession session){
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
        session.setAttribute(CURRENT_USER, userResponseVo.getData());
        log.info("/user/login sessionId={}", session.getId());

        return userResponseVo;
    }



    /**
     * 获取登录用户信息
     * @param session
     * @return
     */
    @Log("查看用户信息")
    @ApiOperation(value = "userInfo用户信息")
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=10,message="用户未登录，请先登录")
    })
    @GetMapping("/info")
    public ResponseVo<User> userInfo(@ApiIgnore HttpSession session){
        log.info("/user/info sessionId={}", session.getId());
        User user = (User) session.getAttribute(CURRENT_USER);
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
    @Log("登出")
    @ApiOperation(value = "logout退出登录")
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=10,message="用户未登录，请先登录")
    })
    @PostMapping("/logout")
    public ResponseVo<User> logout(@ApiIgnore HttpSession session){
        log.info("/user/logout sessionId={}", session.getId());
        //已经通过拦截器判断登录状态
//        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
//        if (user == null) { //未登录情况下
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
//        }

        //移除session，退出登录
        session.removeAttribute(CURRENT_USER);

        return ResponseVo.success();

    }


    /**
     * 查询除登录用户外的所有系统用户
     * @param pageNum
     * @param pageSize
     * @param session
     * @return
     */
    @Log("用户列表")
    @ApiOperation(value = "list查询用户信息")
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=10,message="用户未登录，请先登录")
    })
    @GetMapping("/list")
    public ResponseVo<PageInfo> list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     @ApiIgnore HttpSession session) {
        User user = (User) session.getAttribute(CURRENT_USER);
        return userService.list(user.getId(), pageNum, pageSize);
    }


    /**
     * 添加用户
     * @param form
     * @return
     */
    @ApiOperation(value = "add添加用户")
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=10,message="用户未登录，请先登录"),
            @ApiResponse(code=12,message="用户名已存在"),
            @ApiResponse(code=13,message="邮箱已存在")
    })
    @PostMapping("/add")
    public ResponseVo<User> add(@Valid @RequestBody UserAddForm form,
                                @ApiIgnore HttpSession session){
        //防止username, password, email有空值
        //编写好统一的拦截之后被简化
//        if(bindingResult.hasErrors()){
//            log.error("提交的数据有误,{} {}",
//                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),
//                    bindingResult.getFieldError().getDefaultMessage());
//            return ResponseVo.error(PARAM_ERROR, bindingResult);
//        }
        User current_user = (User) session.getAttribute(CURRENT_USER);
        if (current_user.getRole() < 1){
            return ResponseVo.error(ResponseEnum.INSUFFICIENT_AUTHORITY);
        }
        User user = new User();
        BeanUtils.copyProperties(form, user);
        return userService.add(user);
    }




}
