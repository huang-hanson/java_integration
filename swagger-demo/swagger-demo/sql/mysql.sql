-- 创建用户表
CREATE TABLE `user` (
   `id` int(11) NOT NULL COMMENT '主键id',
   `name` varchar(32) DEFAULT NULL COMMENT '姓名',
   `birthday` varchar(16) DEFAULT NULL COMMENT '生日(yyyy-MM-dd)',
   `gender` tinyint(2) DEFAULT NULL COMMENT '性别：0:女；1:男',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 添加数据
INSERT INTO `swagger_demo`.`user` (`id`, `name`, `birthday`, `gender`) VALUES (1, '黄汉升', '1999-05-18', 1);