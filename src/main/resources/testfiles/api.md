# 用户模块

## 1、登录

  POST  /user/login

### request

Content-Type: application/json

```json
{
    "username": "admin",
    "password": "admin"
}
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
        "email": "admin@dms.com",
        "role": 1,
        "birthday": "1993/03/15"
    }
}
```

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
        "email": "admin@dms.com",
        "role": 1,
        "birthday": "1993/03/15"
    }
}
```

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

