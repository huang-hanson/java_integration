CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      password VARCHAR(255),
                      photo VARCHAR(255),
                      createTime DATETIME,
                      updateTime DATETIME,
                      status INT
);
