package com.dm.controller;

import com.dm.consts.DMConst;
import com.dm.enums.ResponseEnum;
import com.dm.enums.RoleEnum;
import com.dm.form.LoginUserUpdateForm;
import com.dm.form.UserAddForm;
import com.dm.form.UserLoginForm;
import com.dm.form.UserUpdateForm;
import com.dm.log.annotation.Log;
import com.dm.pojo.User;
import com.dm.service.IUserService;
import com.dm.vo.ResponseVo;
import com.dm.vo.UserVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.nio.charset.StandardCharsets;

import static com.dm.consts.DMConst.CURRENT_USER;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "User用户模块")
//@CrossOrigin
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
    public ResponseVo<UserVo> login(@Valid @RequestBody UserLoginForm userLoginForm,
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

        ResponseVo<UserVo> userVoResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        ResponseVo<User> userResponseVo = userService.findById(userVoResponseVo.getData().getId());
        //设置Session
        session.setAttribute(CURRENT_USER, userResponseVo.getData());
        log.info("/user/login sessionId={}", session.getId());

        return userVoResponseVo;
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
    public ResponseVo<UserVo> userInfo(@ApiIgnore HttpSession session){
        log.info("/user/info sessionId={}", session.getId());
        User user = (User) session.getAttribute(CURRENT_USER);
        UserVo userVo = user2UserVo(user);
        //已经通过拦截器判断登录状态
        return ResponseVo.success(userVo);
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
    public ResponseVo logout(@ApiIgnore HttpSession session){
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
    @Log("添加用户")
    @ApiOperation(value = "add添加用户")
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=10,message="用户未登录，请先登录"),
            @ApiResponse(code=12,message="用户名已存在"),
            @ApiResponse(code=13,message="邮箱已存在")
    })
    @PostMapping("/add")
    public ResponseVo add(@Valid @RequestBody UserAddForm form,
                                @ApiIgnore HttpSession session){
        //防止username, password有空值
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



    /**
     * 修改其他用户信息
     * @param id
     * @param form
     * @return
     */
    @Log("修改其他用户信息")
    @ApiOperation(value = "update修改其他用户信息")
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=10,message="用户未登录，请先登录"),
            @ApiResponse(code=15,message="权限不足")
    })
    @PostMapping("/update/{id}")
    public ResponseVo update(@PathVariable Integer id,
                             @Valid @RequestBody UserUpdateForm form,
                             @ApiIgnore HttpSession session){
        //当前登录用户
        User user = (User) session.getAttribute(CURRENT_USER);
        //被修改用户之前的数据
        User userBefore = userService.findById(id).getData();
        //被修改用户之后的数据
        User userAfter = new User();
        BeanUtils.copyProperties(form, userAfter);
        userAfter.setId(id);
        //权限判断
        if (user.getRole() == 0){
            return ResponseVo.error(ResponseEnum.INSUFFICIENT_AUTHORITY);
        } else if (user.getRole() == 1 && userBefore.getRole() >0){
            return ResponseVo.error(ResponseEnum.INSUFFICIENT_AUTHORITY);
        }
//        log.info("username={}", form.getUsername());

        return userService.update(user);
    }


    @Log("修改登录用户信息")
    @ApiOperation(value = "update修改登录用户信息")
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=10,message="用户未登录，请先登录")
    })
    @PostMapping("/update")
    public ResponseVo updateLoginUser(@Valid @RequestBody LoginUserUpdateForm form,
                             @ApiIgnore HttpSession session){
        //将修改后所有信息放入user
        Integer id = ((User) session.getAttribute(CURRENT_USER)).getId();
        User user = new User();
        BeanUtils.copyProperties(form, user);
        user.setId(id);
        //MD5摘要算法
        user.setPassword(
                DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));

        ResponseVo responseVo = userService.update(user);
        if (!responseVo.getStatus().equals(ResponseVo.success().getStatus())){
            return responseVo;
        }
        //放到session
        session.setAttribute(CURRENT_USER, user);
        return ResponseVo.success();
    }







    private UserVo user2UserVo(User user){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setPassword("");
        if (user.getRole() == 2){
            userVo.setRoleStr(RoleEnum.TOP_ADMIN.getDesc());
        } else if (user.getRole() == 1){
            userVo.setRoleStr(RoleEnum.ADMIN.getDesc());
        } else {
            userVo.setRoleStr(RoleEnum.CUSTOMER.getDesc());
        }

        return userVo;
    }





}
