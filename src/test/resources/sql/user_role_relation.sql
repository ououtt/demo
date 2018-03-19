CREATE TABLE `user_role_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户名称',
  `role_id` int(11) NOT NULL COMMENT '手机',
  PRIMARY KEY (`id`),
  KEY `IDX_USER_ID` (`user_id`),
  KEY `IDX_ROLE_ID` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色关联表';