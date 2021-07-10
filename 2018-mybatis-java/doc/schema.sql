CREATE TABLE `t_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `call_machine` varchar(32) NOT NULL,
  `call_user` varchar(32) NOT NULL,
  `name` varchar(50),
  `template_type` varchar(20) NOT NULL DEFAULT 'text',
  `template` varchar(2000) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
