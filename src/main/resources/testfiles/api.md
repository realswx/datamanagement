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

