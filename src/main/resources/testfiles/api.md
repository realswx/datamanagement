# 一、User用户模块

## 1、登录

  POST  /user/login

### request

Content-Type: application/json

```json
{
    "username": "admin",
    "password": "admin"
}

String username;
String password;
```

### response

success

```json
{
    "status": 0,
    "data": {
        "id": 1,
        "username": "admin",
        "password": "",
        "email": "admin1@dms.com",
        "roleStr": "最高级管理员",
        "birthday": "1997-08-08T16:00:00.000+0000"
    }
}
```

##### 注：

防止密码泄露，故意将密码设置为空

fail

```json
{
    "status": 11,
    "msg": "用户名或密码错误"
}
```



## 2、获取登录用户信息

GET  /user/info

### request

无参数

### response

success

```json
{
    "status": 0,
    "data": {
        "id": 1,
        "username": "admin",
        "password": "",
        "email": "admin1@dms.com",
        "roleStr": "最高级管理员",
        "birthday": "1997-08-08T16:00:00.000+0000"
    }
}
```

##### 注：

防止密码泄露，故意将密码设置为空

fail

```json
{
    "status": 10,
    "msg": "用户未登录，请先登录"
}
```

## 3、退出登录

POST  /user/logout

### request

无

### response

success

```json
{
    "status": 0,
    "msg": "成功"
}
```

fail

```json
{
    "status": -1,
    "msg": "服务端异常"
}
```

## 4、查询用户列表

##### 注：

显示除自己外的所有系统用户

GET  /user/list

### request

```json
Integer pageNum(defaultValue=1);
Integet pageSize(defaultValue=10);
HttpSession session;
前两个参数非必传
session不要传，不必理会
```

### response

success

```json
{
    "status": 0,
    "data": {
        "total": 2,
        "list": [
            {
                "id": 2,
                "username": "admin2",
                "password": "",
                "email": "admin2@dms.com",
                "roleStr": "管理员",
                "birthday": "1990-06-14T15:00:00.000+0000"
            },
            {
                "id": 3,
                "username": "张一",
                "password": "",
                "email": "zhang1@dms.com",
                "roleStr": "普通用户",
                "birthday": "1997-03-23T16:00:00.000+0000"
            }
        ],
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 1,
        "endRow": 2,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

##### 注：

防止密码泄露，故意将密码设置为空

## 5、添加用户

##### 注：

普通用户不能添加用户，添加的用户为普通用户

POST  /user/add

request

```json
String username;
String password;
String email;
Date birthday;
```

##### 注：

**birthday的形式为yyyy-MM-dd...，不是yyyy/MM/dd...**

response

success

```json
{
    "status": 0,
    "msg": "成功"
}
```

fail

```json
{
    "status": 15,
    "msg": "权限不足"
}
```

```json
{
    "status": 12,
    "msg": "用户名已存在"
}
```

```json
{
    "status": 13,
    "msg": "邮箱已存在"
}
```



# 二、Product商品模块

## 1、导入文件

POST  /product/import

### request

file文件

### response

success

```json
{
    "status": 0,
    "msg": "成功"
}
```

fail

```json
{
    "status": 1,
    "msg": "系统未获取到文件"
}
```



## 2、查询列表

GET  /product/list

### request

form-data

```json
Integer pageNum(defaultValue=1);
Integet pageSize(defaultValue=10);
两个参数非必传
```

### response

success

```json
{
    "status": 0,
    "data": {
        "total": 4,
        "list": [
            {
                "id": 26,
                "name": "Apple iPhone 11手机",
                "price": 6999.00,
                "stock": 988,
                "sale": 12
            },
            {
                "id": 27,
                "name": "美的 535WKZM冰箱",
                "price": 3299.00,
                "stock": 999,
                "sale": 1
            },
            {
                "id": 28,
                "name": "华为 手机P9",
                "price": 1999.00,
                "stock": 990,
                "sale": 10
            },
            {
                "id": 29,
                "name": "海尔HJ100洗衣机",
                "price": 4299.00,
                "stock": 910,
                "sale": 90
            }
        ],
        "pageNum": 1,
        "pageSize": 10,
        "size": 4,
        "startRow": 1,
        "endRow": 4,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```





# 三、Order订单模块

## 1、导入文件

POST  /order/import

### request

file文件

### response

success

```json
{
    "status": 0,
    "msg": "成功"
}
```

fail

```json
{
    "status": 1,
    "msg": "系统未获取到文件"
}
```



## 2、查询列表

GET  /order/list

### request

form-data

```json
Integer pageNum(defaultValue=1);
Integet pageSize(defaultValue=10);
两个参数非必传
```

### response

success

注：前端页面不要显示id字段

```json
{
    "status": 0,
    "data": {
        "total": 4,
        "list": [
            {
                "id": 1,
                "orderNo": 1239901840,
                "buyerId": 1,
                "payment": 6999.00,
                "createTime": "2020-03-17T16:43:58.000+0000"
            },
            {
                "id": 2,
                "orderNo": 1239901841,
                "buyerId": 2,
                "payment": 4299.00,
                "createTime": "2020-06-27T16:43:58.000+0000"
            }
        ],
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 1,
        "endRow": 2,
        "pages": 2,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 2
    }
}
```



# 四、OrderItem订单明细模块

## 1、导入文件

GET  /orderItem/import

### request

file文件

### response

success

```json
{
    "status": 0,
    "msg": "成功"
}
```

fail

```json
{
    "status": 1,
    "msg": "系统未获取到文件"
}
```

## 2、查询列表

GET  /orderItem/list

### request

form-data

```json
Integer pageNum(defaultValue=1);
Integet pageSize(defaultValue=10);
两个参数非必传
```

### response

success

注：前端页面不要显示id字段

```json
{
    "status": 0,
    "data": {
        "total": 6,
        "list": [
            {
                "id": 3,
                "orderNo": 1239901842,
                "buyerId": 3,
                "productId": 27,
                "productName": "美的 535WKZM冰箱",
                "unitPrice": 3299.00,
                "quantity": 1,
                "totalPrice": 3299.00
            },
            {
                "id": 4,
                "orderNo": 1239901842,
                "buyerId": 3,
                "productId": 28,
                "productName": "华为 手机P9",
                "unitPrice": 1999.00,
                "quantity": 1,
                "totalPrice": 1999.00
            }
        ],
        "pageNum": 2,
        "pageSize": 2,
        "size": 2,
        "startRow": 3,
        "endRow": 4,
        "pages": 3,
        "prePage": 1,
        "nextPage": 3,
        "isFirstPage": false,
        "isLastPage": false,
        "hasPreviousPage": true,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2,
            3
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 3
    }
}
```



# 五、Log日志模块

## 1、查询正常日志

GET  /log/info

### request

form-data

```json
Integer pageNum(defaultValue=1);
Integet pageSize(defaultValue=10);
两个参数非必传
```

### response

success

```json
{
    "status": 0,
    "data": {
        "total": 11,
        "list": [
            {
                "logId": 2573,
                "description": "登录",
                "logType": "INFO",
                "method": "com.dm.controller.UserController.login()",
                "params": "{UserLoginForm(username=admin, password=admin) org.apache.catalina.session.StandardSessionFacade@5efb67cf  }",
                "requestIp": "0:0:0:0:0:0:0:1",
                "time": 748,
                "username": "admin",
                "address": "0:0:0:0:0:0:0:1",
                "browser": "Unknown",
                "createTime": "2020-08-18T16:03:10.000+0000",
                "exceptionDetail": null
            },
            {
                "logId": 2574,
                "description": "用户列表",
                "logType": "INFO",
                "method": "com.dm.controller.UserController.list()",
                "params": "{1 10 org.apache.catalina.session.StandardSessionFacade@5efb67cf  }",
                "requestIp": "0:0:0:0:0:0:0:1",
                "time": 181,
                "username": "admin",
                "address": "0:0:0:0:0:0:0:1",
                "browser": "Unknown",
                "createTime": "2020-08-18T16:04:24.000+0000",
                "exceptionDetail": null
            }
        ],
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 1,
        "endRow": 2,
        "pages": 6,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2,
            3,
            4,
            5,
            6
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 6
    }
}
```



## 2、查询异常日志

GET  /log/error

### request

form-data

```json
Integer pageNum(defaultValue=1);
Integet pageSize(defaultValue=10);
两个参数非必传
```

### response

```json
{
    "status": 0,
    "data": {
        "total": 1,
        "list": [
            {
                "logId": 2577,
                "description": "用户列表",
                "logType": "ERROR",
                "method": "com.dm.controller.UserController.list()",
                "params": "{1 10 org.apache.catalina.session.StandardSessionFacade@736b859e  }",
                "requestIp": "0:0:0:0:0:0:0:1",
                "time": 0,
                "username": "admin",
                "address": "0:0:0:0:0:0:0:1",
                "browser": "Unknown",
                "createTime": "2020-08-18T16:08:34.000+0000",
                "exceptionDetail": "java.lang.RuntimeException: 意外错误\r\n\tat com.dm.service.impl.UserServiceImpl.error(UserServiceImpl.java:146)\r\n\tat com.dm.service.impl.UserServiceImpl.list(UserServiceImpl.java:77)\r\n\tat com.dm.controller.UserController.list(UserController.java:140)\r\n\tat com.dm.controller.UserController$$FastClassBySpringCGLIB$$b84d1aa1.invoke(<generated>)\r\n\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:769)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:747)\r\n\tat org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n\tat com.dm.log.aspect.LogAspect.logAround(LogAspect.java:57)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n\tat org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n\tat org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:747)\r\n\tat org.springframework.aop.aspectj.AspectJAfterThrowingAdvice.invoke(AspectJAfterThrowingAdvice.java:62)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:175)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:747)\r\n\tat org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:747)\r\n\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:689)\r\n\tat com.dm.controller.UserController$$EnhancerBySpringCGLIB$$6de6c0c5.list(<generated>)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:888)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:793)\r\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)\r\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n\tat org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\r\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:526)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\r\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:408)\r\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:861)\r\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1579)\r\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\r\n\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.lang.Thread.run(Thread.java:748)\r\n"
            }
        ],
        "pageNum": 1,
        "pageSize": 1,
        "size": 1,
        "startRow": 1,
        "endRow": 1,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

