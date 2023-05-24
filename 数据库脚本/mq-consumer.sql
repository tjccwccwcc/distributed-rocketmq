/*
Navicat MySQL Data Transfer

Source Server         : 192.168.113.204_3306
Source Server Version : 50722
Source Host           : 192.168.113.204:3306
Source Database       : mq-consumer

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2021-07-29 15:09:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_usable_integral
-- ----------------------------
DROP TABLE IF EXISTS `t_usable_integral`;
CREATE TABLE `t_usable_integral` (
  `user_id` bigint(20) NOT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `freezed_amount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_usable_integral
-- ----------------------------
INSERT INTO `t_usable_integral` VALUES ('13088889999', '2020-11-06 15:36:07', '2020-12-11 10:59:52', '500000', '0');
