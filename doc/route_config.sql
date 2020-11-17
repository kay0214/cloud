CREATE TABLE `ht_gateway_api_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `path` varchar(255) NOT NULL,
  `service_id` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `retryable` tinyint(1) DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `strip_prefix` int(11) DEFAULT '1',
  `secure_visit_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '安全访问控制标识，  0-无需登陆访问 1-需要登陆访问',
  `api_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `path_uq` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路由配置表';