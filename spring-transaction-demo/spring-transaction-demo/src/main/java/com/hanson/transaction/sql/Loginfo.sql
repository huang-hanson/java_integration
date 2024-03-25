CREATE TABLE loginfo (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(250),
                         `desc` TEXT,
                         createtime DATETIME DEFAULT CURRENT_TIMESTAMP
);
