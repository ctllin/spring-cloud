/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2019-03-10 01:12:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', 'ctl', '1', '2019-03-09 22:23:34');
INSERT INTO `person` VALUES ('2', 'lin', '1', '2019-03-09 22:23:34');
INSERT INTO `person` VALUES ('3', 'zxp', '1', '2019-03-09 22:23:34');
INSERT INTO `person` VALUES ('4', 'kym', '1', '2019-03-09 22:23:34');
INSERT INTO `person` VALUES ('5', 'xl', '1', '2019-03-09 22:23:34');
INSERT INTO `person` VALUES ('65', 'ctl-1552149835811-transactionTemplate', '1', '2019-03-09 16:43:56');
INSERT INTO `person` VALUES ('66', 'ctl-1552149916129-transactionTemplate', '1', '2019-03-09 16:45:16');
INSERT INTO `person` VALUES ('68', 'ctl-1552149917626-transactionTemplate', '1', '2019-03-09 16:45:18');
INSERT INTO `person` VALUES ('69', 'ctl-1552149917790-transactionTemplate', '1', '2019-03-09 16:45:18');
INSERT INTO `person` VALUES ('70', 'ctl-1552149918903-transactionTemplate', '1', '2019-03-09 16:45:19');
INSERT INTO `person` VALUES ('71', 'ctl-1552149919043-transactionTemplate', '1', '2019-03-09 16:45:19');
INSERT INTO `person` VALUES ('72', 'ctl-1552149920206-transactionTemplate', '1', '2019-03-09 16:45:20');
INSERT INTO `person` VALUES ('73', 'ctl-1552149920269-transactionTemplate', '1', '2019-03-09 16:45:20');
INSERT INTO `person` VALUES ('74', 'ctl-1552149931641-transactionTemplate', '1', '2019-03-09 16:45:32');
INSERT INTO `person` VALUES ('75', 'ctl-1552149931692-transactionTemplate', '1', '2019-03-09 16:45:32');
INSERT INTO `person` VALUES ('78', 'ctl-1552150103521-transactionTemplate', '1', '2019-03-09 16:48:24');
INSERT INTO `person` VALUES ('79', 'ctl-1552150103664-transactionTemplate', '1', '2019-03-09 16:48:24');
INSERT INTO `person` VALUES ('81', 'ctl-1552150148495-transactionTemplate', '1', '2019-03-09 16:49:08');
INSERT INTO `person` VALUES ('83', 'ctl-1552150149609-transactionTemplate', '1', '2019-03-09 16:49:10');
INSERT INTO `person` VALUES ('85', 'ctl-1552150150944-transactionTemplate', '1', '2019-03-09 16:49:11');
INSERT INTO `person` VALUES ('88', 'ctl-1552150182542-transactionTemplate', '1', '2019-03-09 16:49:43');
INSERT INTO `person` VALUES ('89', 'ctl-1552150182581-transactionTemplate', '1', '2019-03-09 16:49:43');
INSERT INTO `person` VALUES ('90', 'ctl-1552150198477-transactionTemplate', '1', '2019-03-09 16:49:58');
INSERT INTO `person` VALUES ('91', 'ctl-1552150198691-transactionTemplate', '1', '2019-03-09 16:49:59');
INSERT INTO `person` VALUES ('92', 'ctl-1552150355299-transactionTemplate', '1', '2019-03-09 16:52:35');
INSERT INTO `person` VALUES ('94', 'ctl-1552151140444-transactionTemplate', '1', '2019-03-09 17:05:40');
INSERT INTO `person` VALUES ('95', 'ctl-1552151141293-transactionTemplate', '1', '2019-03-09 17:05:41');
INSERT INTO `person` VALUES ('96', 'ctl-1552151141344-transactionTemplate', '1', '2019-03-09 17:05:41');
INSERT INTO `person` VALUES ('97', 'ctl-1552151149310-transactionTemplate', '1', '2019-03-09 17:05:49');
INSERT INTO `person` VALUES ('99', 'ctl-1552151158725-transactionTemplate', '1', '2019-03-09 17:05:59');
INSERT INTO `person` VALUES ('101', 'ctl-1552151208147-transactionTemplate', '1', '2019-03-09 17:06:49');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'ctl', '1', '2019-03-09 22:23:34');
INSERT INTO `student` VALUES ('2', 'lin', '1', '2019-03-09 22:23:34');
INSERT INTO `student` VALUES ('3', 'zxp', '1', '2019-03-09 22:23:34');
INSERT INTO `student` VALUES ('4', 'kym', '1', '2019-03-09 22:23:34');
INSERT INTO `student` VALUES ('5', 'xl', '1', '2019-03-09 22:23:34');
INSERT INTO `student` VALUES ('65', 'ctl-1552149835811-transactionTemplate', '1', '2019-03-09 16:43:56');
INSERT INTO `student` VALUES ('66', 'ctl-1552149916129-transactionTemplate', '1', '2019-03-09 16:45:16');
INSERT INTO `student` VALUES ('68', 'ctl-1552149917626-transactionTemplate', '1', '2019-03-09 16:45:18');
INSERT INTO `student` VALUES ('69', 'ctl-1552149917790-transactionTemplate', '1', '2019-03-09 16:45:18');
INSERT INTO `student` VALUES ('70', 'ctl-1552149918903-transactionTemplate', '1', '2019-03-09 16:45:19');
INSERT INTO `student` VALUES ('71', 'ctl-1552149919043-transactionTemplate', '1', '2019-03-09 16:45:19');
INSERT INTO `student` VALUES ('72', 'ctl-1552149920206-transactionTemplate', '1', '2019-03-09 16:45:20');
INSERT INTO `student` VALUES ('73', 'ctl-1552149920269-transactionTemplate', '1', '2019-03-09 16:45:20');
INSERT INTO `student` VALUES ('74', 'ctl-1552149931641-transactionTemplate', '1', '2019-03-09 16:45:32');
INSERT INTO `student` VALUES ('75', 'ctl-1552149931692-transactionTemplate', '1', '2019-03-09 16:45:32');
INSERT INTO `student` VALUES ('78', 'ctl-1552150103521-transactionTemplate', '1', '2019-03-09 16:48:24');
INSERT INTO `student` VALUES ('79', 'ctl-1552150103664-transactionTemplate', '1', '2019-03-09 16:48:24');
INSERT INTO `student` VALUES ('81', 'ctl-1552150148495-transactionTemplate', '1', '2019-03-09 16:49:08');
INSERT INTO `student` VALUES ('83', 'ctl-1552150149609-transactionTemplate', '1', '2019-03-09 16:49:10');
INSERT INTO `student` VALUES ('85', 'ctl-1552150150944-transactionTemplate', '1', '2019-03-09 16:49:11');
INSERT INTO `student` VALUES ('88', 'ctl-1552150182542-transactionTemplate', '1', '2019-03-09 16:49:43');
INSERT INTO `student` VALUES ('89', 'ctl-1552150182581-transactionTemplate', '1', '2019-03-09 16:49:43');
INSERT INTO `student` VALUES ('90', 'ctl-1552150198477-transactionTemplate', '1', '2019-03-09 16:49:58');
INSERT INTO `student` VALUES ('91', 'ctl-1552150198691-transactionTemplate', '1', '2019-03-09 16:49:59');
INSERT INTO `student` VALUES ('92', 'ctl-1552150355299-transactionTemplate', '1', '2019-03-09 16:52:35');
INSERT INTO `student` VALUES ('94', 'ctl-1552151140444-transactionTemplate', '1', '2019-03-09 17:05:40');
INSERT INTO `student` VALUES ('95', 'ctl-1552151141293-transactionTemplate', '1', '2019-03-09 17:05:41');
INSERT INTO `student` VALUES ('96', 'ctl-1552151141344-transactionTemplate', '1', '2019-03-09 17:05:41');
INSERT INTO `student` VALUES ('97', 'ctl-1552151149310-transactionTemplate', '1', '2019-03-09 17:05:49');
INSERT INTO `student` VALUES ('99', 'ctl-1552151158725-transactionTemplate', '1', '2019-03-09 17:05:59');
INSERT INTO `student` VALUES ('101', 'ctl-1552151208147-transactionTemplate', '1', '2019-03-09 17:06:49');
