CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL COMMENT '用户名称',
  `state` int(2) NOT NULL COMMENT '0:删除 1:可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';



INSERT INTO `role` (`id`, `role_name`, `state`)
VALUES
  (1,'admin',1),
  (2,'普通用户',1);