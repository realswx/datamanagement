/*Table structure for table `dms_user` */

DROP TABLE IF EXISTS `dms_user`;

CREATE TABLE `dms_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码，MD5加密',
  `email` varchar(50) NOT NULL,
  `role` tinyint(1) NOT NULL DEFAULT '1' COMMENT '角色1-管理员,0-普通用户',
  `birthday` date   DEFAULT NULL COMMENT '出生日期',
    PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `dms_user` */
insert  into `dms_user`(`id`,`username`,`password`,`email`,`role`,`birthday`) values 
(1,'admin','21232f297a57a5a743894a0e4a801fc3','admin@dms.com',1,'1993-03-15'),
(2,'admin2','21232f297a57a5a743894a0e4a801fc3','admin2@dms.com',1,'1990-06-15'),
(3,'张一','21232f297a57a5a743894a0e4a801fc3','zhang1@dms.com',0,'1997-03-24');



/*Table structure for table `dms_product` */

DROP TABLE IF EXISTS `dms_product`;

CREATE TABLE `dms_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `price` decimal(20,2) NOT NULL COMMENT '价格,单位-元保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存',
  `sale` int(11) NOT NULL COMMENT '销售量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='商品表';

/*Data for the table `mall_product` */

insert  into `dms_product`(`id`,`name`,`price`,`stock`,`sale`) values 
(26,'Apple iPhone 11手机',6999.00,988,12),
(27,'美的 535WKZM冰箱',3299.00,999,1),
(28,'华为 手机P9',1999.00,990,10),
(29,'海尔HJ100洗衣机',4299.00,910,90);

/*Table structure for table `dms_order` */

DROP TABLE IF EXISTS `dms_order`;

CREATE TABLE `dms_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` int(11) NOT NULL COMMENT '订单编号',
  `buyer_id` int(11) DEFAULT NULL COMMENT '买家id',
  `payment` decimal(20,2) NOT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，精确到日',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `mall_order` */

insert  into `dms_order`(`id`,`order_no`,`buyer_id`,`payment`,`create_time`) values 
(1,1239901840,1,6999.00,'2020-03-18 00:43:58'),
(2,1239901841,2,4299.00,'2020-06-28 00:43:58');


/*Table structure for table `dms_order_item` */

DROP TABLE IF EXISTS `dms_order_item`;

CREATE TABLE `dms_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
  `order_no` int(11) DEFAULT NULL COMMENT '订单编号',
  `buyer_id` int(11) DEFAULT NULL COMMENT '买家ID',
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
    PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_buyer_id_index` (`buyer_id`,`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='订单明细表';

/*Data for the table `mall_order_item` */

insert  into `dms_order_item`(`id`,`order_no`,`buyer_id`,`product_id`,`product_name`,`unit_price`,`quantity`,`total_price`) values 
(1,1239901840,1,26,'Apple iPhone 11手机',6999.00,1,6999.00),
(2,1239901841,2,29,'海尔HJ100洗衣机',4299.00,1,4299.00);

