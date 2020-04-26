/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : master

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-04-26 15:33:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `password` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `username` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4', '25', 'bonatgy', null, 'slave1-zjw');
INSERT INTO `user` VALUES ('2', '18', 'Transactional', '1', 'Transactional');
INSERT INTO `user` VALUES ('3', '18', 'master', '1', 'master');
INSERT INTO `user` VALUES ('5', '25', 'bonatgy', null, 'slave1-zjw');
INSERT INTO `user` VALUES ('6', '25', 'bonatgy', null, 'slave1-zjw');
SET FOREIGN_KEY_CHECKS=1;
