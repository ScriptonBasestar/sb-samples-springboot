-- CREATE SCHEMA
CREATE DATABASE IF NOT EXISTS dev_api_member_spring_db CHARACTER SET UTF8mb4 COLLATE UTF8mb4_bin;
GRANT ALL PRIVILEGES ON dev_api_member_spring_db.* TO 'testuser'@'%' IDENTIFIED BY 'passw0rd';

USE dev_api_member_spring_db;
CREATE TABLE `t_commodity` (
  id bigint PRIMARY KEY,
  name varchar(100) NOT NULL,
  since datetime NOT NULL,
  code char(4) NOT NULL,
  base char(4) NOT NULL,
  quote char(4) NOT NULL
) ENGINE=InnoDB;
