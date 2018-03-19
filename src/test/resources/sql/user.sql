CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名称',
  `mobile_phone` varchar(255) DEFAULT '' COMMENT '手机',
  `email` varchar(255) DEFAULT '' COMMENT '邮箱',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `sex` int(2) DEFAULT NULL COMMENT '性别:1男，2女',
  `id_card` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `images` varchar(500) DEFAULT NULL COMMENT '图片路径',
  `city_code` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `state` int(2) NOT NULL COMMENT '0:删除 1:可用',
  `gmt_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_update` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `valid` int(2) NOT NULL COMMENT '是否验证',
  PRIMARY KEY (`id`),
  KEY `IDX_MOBILE` (`mobile_phone`),
  KEY `IDX_EMAIL` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';