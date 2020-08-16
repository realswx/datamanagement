package com.dm.interceptor;


import com.dm.consts.DMConst;
import com.dm.exception.UserLoginException;
import com.dm.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器，在执行其他操作之前调用拦截器，判断是否登录
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * 在请求之前拦截
     * true表示继续流程，false表示终端
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle......");
        User user = (User) request.getSession().getAttribute(DMConst.CURRENT_USER);
        if (user == null){
            log.info("user == null");
            throw new UserLoginException();
//            return false;
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
        }
        return true;
    }
}
