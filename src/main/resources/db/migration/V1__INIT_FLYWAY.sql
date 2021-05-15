use flyway;

CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `age` int DEFAULT NULL COMMENT '年龄',
  `name` varchar(16) COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

insert into user (age, name) values (12, '张三');
insert into user (age, name) values (14, '李四');