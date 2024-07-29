CREATE TABLE `mq_fail_message`
(
    `id`          BIGINT AUTO_INCREMENT PRIMARY KEY,
    `mq_name`     VARCHAR(255) NOT NULL,
    `queue_name`  VARCHAR(255) NOT NULL,
    `message`     TEXT         NOT NULL,
    `create_date` DATETIME     NOT NULL,
    `update_date` DATETIME     NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
