/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : test_sys

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2019-03-27 18:18:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` char(32) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `mobile` char(11) DEFAULT NULL,
  `desc` varchar(64) DEFAULT NULL,
  `version` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `flag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
