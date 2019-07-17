/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 17/07/2019 10:17:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (26, '李四');
INSERT INTO `t_user` VALUES (27, '李四');

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES (27, 17);

-- ----------------------------
-- Table structure for t_user_z
-- ----------------------------
DROP TABLE IF EXISTS `t_user_z`;
CREATE TABLE `t_user_z`  (
  `test_id` bigint(20) NOT NULL COMMENT '主键ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `test_type` int(11) NULL DEFAULT NULL COMMENT '测试下划线字段命名类型',
  `test_date` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `role` bigint(20) NULL DEFAULT NULL COMMENT '测试',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`test_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'abc用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_z
-- ----------------------------
INSERT INTO `t_user_z` VALUES (0, 1, '雷锋', 1, 1, '2017-01-01 01:01:01', 1, '10010');
INSERT INTO `t_user_z` VALUES (1, 1, '三毛', 2, 1, '2017-02-02 01:01:01', 1, '10086');
INSERT INTO `t_user_z` VALUES (2, 1, '小马', 1, 1, '2017-03-03 01:01:01', 1, '10000');
INSERT INTO `t_user_z` VALUES (3, 2, '麻花藤', 1, 1, '2017-03-03 01:01:01', 1, '10000');
INSERT INTO `t_user_z` VALUES (4, 2, '东狗', 2, 1, '2017-03-03 01:01:01', 1, '10086');
INSERT INTO `t_user_z` VALUES (5, 1, '王五', 2, 1, '2017-03-03 01:01:01', 1, '10010');

SET FOREIGN_KEY_CHECKS = 1;
