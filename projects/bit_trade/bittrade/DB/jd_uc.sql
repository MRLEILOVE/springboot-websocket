/*
 Navicat Premium Data Transfer

 Source Server         : 154.222.138.249
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 154.222.138.249:3306
 Source Schema         : jd_uc

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 30/08/2019 11:42:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '登录用户ID',
  `login_name` bigint(20) NOT NULL COMMENT '登录名称',
  `lgoin_type` tinyint(2) NULL DEFAULT NULL COMMENT '登录方式:缺省',
  `login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录IP',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '缺省状态',
  `extend1` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缺省1',
  `extend2` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缺省2',
  `exten3` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缺省3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `login_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `login_password` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `tele_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `recommend_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推荐码',
  `user_email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `phone_area_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机区域码',
  `is_tel_validate` tinyint(1) NULL DEFAULT 0 COMMENT '是否电话绑定:1绑定，0无效',
  `is_identity_validate` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否证件绑定:1绑定，0无效',
  `is_mail_validate` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否邮箱绑定:1绑定，0无效',
  `user_address` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `identity_type` tinyint(2) NULL DEFAULT NULL COMMENT '证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证',
  `identity_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `tel_validate_time` datetime(0) NULL DEFAULT NULL COMMENT '手机验证时间',
  `mail_validate_time` datetime(0) NULL DEFAULT NULL COMMENT '邮箱验证时间',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '账号类型:缺省',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '账号状态:1正常，0冻结',
  `user_first_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第一次登陆ip',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `extend1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `extend2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `extend3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `is_identity_validate`, `is_mail_validate`) USING BTREE,
  UNIQUE INDEX `UK_LOGIN_NAME`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '15387215009', '$2a$10$B/NqYtSbWIEBgkWR56dvCOXliCXvqc6fGm2amyTsH61K3MlTXXuii', '15387215009', '15387215009', '15387215009', NULL, NULL, NULL, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2019-08-29 17:13:01', NULL, NULL);
INSERT INTO `t_user` VALUES (2, 'admin', '$2a$10$oM31TL98yZi7rvw6uMHw6Oqzyhx.Ab8RCJLGLkan.Bfg4tD2wHZle', '15387215009', '15387215009', '15387215009', '', '', '', 0, 0, 0, '', NULL, '', NULL, NULL, NULL, 1, '', NULL, NULL, '2019-08-30 11:12:39', '', '');
INSERT INTO `t_user` VALUES (325436084, '15207101021', '$2a$10$Zl9adRZveXlMrWM7u.rS3OViyWPv0X3zKg.9cJ.QAxTqIkGwtTCyG', '15207101021', NULL, '15207101021', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:04', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:04', '2019-08-29 17:04:29', NULL, NULL);
INSERT INTO `t_user` VALUES (1047449832, '15207101019', '$2a$10$UrwaJGNmXKWECMarrF.P2u0EG1hghNRnJd2g6San88DhZsx3f8qi2', '15207101019', NULL, '15207101019', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:03', '2019-08-20 20:56:40', NULL, NULL);
INSERT INTO `t_user` VALUES (1399844668, '15207101009', '$2a$10$CtPQPEp7vcZUS4Yt.9UgmeiXKksCdIvtabW/2X8IjDV7Zh/XCZ.7G', '15207101009', NULL, '15207101009', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:19:45', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:19:45', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (1816444309, '13418967011', '$2a$10$XlfIMqgEpmKo3.yOf7LdNuCy3WnthQ..gIO0jnzoDorOzyC5F6L3u', NULL, NULL, '13418967011', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:23:19', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:23:19', '2019-08-20 21:56:53', NULL, NULL);
INSERT INTO `t_user` VALUES (1822869796, '13760229100', '$2a$10$o.u4EuIsvBO8ghcOd6SIF.GlZI9hUFxql82HyJtDie2RliSDdQsOu', NULL, NULL, '13760229100', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:42:26', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:42:26', '2019-08-20 23:44:38', NULL, NULL);
INSERT INTO `t_user` VALUES (2041302331, '18279987523', '$2a$10$U79YHH6mX3GE/aTTXHvvOerbkBgVyuzgpI4JTm15FuhqzB3nuFCIu', NULL, NULL, '18279987523', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-22 15:19:01', NULL, NULL, 1, NULL, NULL, '2019-08-22 15:19:01', '2019-08-22 15:20:47', NULL, NULL);
INSERT INTO `t_user` VALUES (2113858794, '15207101018', '$2a$10$gVeHThVhL.O1gFgwJBhPQeNLTvjox2j2rZCmCbGrMM02/HhqTP7Ni', '15207101018', NULL, '15207101018', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (2435745288, '13800000103', '$2a$10$owbuH622ZACCPVcEFixqE.upEOhqRagOeiFpLBBd7JUL/HTTx96qi', NULL, NULL, '13800000103', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-22 15:18:08', NULL, NULL, 1, NULL, NULL, '2019-08-22 15:18:08', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (2494629567, '15207101002', '$2a$10$/gI19ieag2khjizU2kFQpeCj9qDrT1wD9rMVTEeQ9aFpeao1usLlu', '15207101002', NULL, '15207101002', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:19:23', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:19:23', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (3064927466, '17671716711', '$2a$10$Vn4KAPWZViw6UFQBRHRB9eSB2.S5zRvYz.N/JAGeiu9xLnFd4mb1m', NULL, NULL, '17671716711', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-06-04 23:08:20', NULL, NULL, 1, NULL, NULL, '2019-06-04 23:08:20', '2019-08-20 19:50:42', NULL, NULL);
INSERT INTO `t_user` VALUES (3461589146, '15207101017', '$2a$10$w5N3Dyc5xM6.gBZcomr7eu8co1t3taCBiHX3MX3mjQP.GnsgXWI5e', '15207101017', NULL, '15207101017', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:03', '2019-08-20 20:57:10', NULL, NULL);
INSERT INTO `t_user` VALUES (3511129857, '15207108156', '$2a$10$siHtiObIdYrEWMagxp/HhexXGhKUwBImZmOSzGIQxe1PwCWrqXTXy', NULL, NULL, '15207108156', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 21:33:52', NULL, NULL, 1, NULL, NULL, '2019-08-20 21:33:52', '2019-08-29 14:38:40', NULL, NULL);
INSERT INTO `t_user` VALUES (3587721044, '18211424351', '$2a$10$Pcl3mzUVlfWTydX56/qPAudI/10kscOGRB.A2ADkwO6arQFUrQ9xa', NULL, NULL, '18211424351', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:19:35', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:19:35', '2019-08-22 15:07:07', NULL, NULL);
INSERT INTO `t_user` VALUES (4174984838, '15207101016', '$2a$10$PaIBjP3fpHyoqKMVmliyMuBXsddQCbEMZhNbywINULej9KE9.mOkG', '15207101016', NULL, '15207101016', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (4493035286, '15207101012', '$2a$10$fxONJxbu5tJlyGk.ZTof6.3qp5tgra3h93iDIcbLVqnvVQ0J5kD6i', '15207101012', NULL, '15207101012', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:02', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:02', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (4761225418, '18576459821', '$2a$10$025i8VZ6kL4pkzv/EcbbmensOvJqH.akJo4ATychUOG.bw6DLDJDS', NULL, NULL, '18576459821', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 17:03:29', NULL, NULL, 1, NULL, NULL, '2019-08-20 17:03:29', '2019-08-27 19:07:59', NULL, NULL);
INSERT INTO `t_user` VALUES (4997695310, '18279987520', '$2a$10$T8AXsgRtAd/ESed7P1468OMnPIjXz9mlrGQrFywdAcrZCygy.pHr6', NULL, NULL, '18279987520', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:10:33', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:10:33', '2019-08-22 10:53:14', NULL, NULL);
INSERT INTO `t_user` VALUES (5932197261, '18279987521', '$2a$10$hhtT2OT694Tz2Jf5h4xGuODnynqGlHirm9tEuCNMD0lKRqG/DCOEW', NULL, NULL, '18279987521', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 16:14:21', NULL, NULL, 1, NULL, NULL, '2019-08-20 16:14:21', '2019-08-21 19:43:58', NULL, NULL);
INSERT INTO `t_user` VALUES (6189707076, '13800000102', '$2a$10$a9X0aJcy8X1yG8K72KSxqeeF/sn5S5Pl6.fKtxZdLTDLXJumYuQQe', NULL, NULL, '13800000102', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-21 23:09:41', NULL, NULL, 1, NULL, NULL, '2019-08-21 23:09:41', '2019-08-21 23:09:50', NULL, NULL);
INSERT INTO `t_user` VALUES (6855037642, '13800000101', '$2a$10$2vxohWE.K2x9A4asSShV2O1ljig2fqUqygo203giMv.LnQ2b7YPLe', NULL, NULL, '13800000101', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-21 10:12:45', NULL, NULL, 1, NULL, NULL, '2019-08-21 10:12:45', '2019-08-22 14:11:54', NULL, NULL);
INSERT INTO `t_user` VALUES (7737990752, '13418967012', '$2a$10$YpQKrhNGvukTiuhvOiD5NOhW.gaCJafS2/gY9j0velS4MJvJ6jpla', NULL, NULL, '13418967012', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 20:53:09', NULL, NULL, 1, NULL, NULL, '2019-08-20 20:53:09', NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (7891782315, '18279987522', '$2a$10$hDjuykp6hTGL1Xm8NiSurOIKVbnCnxIzV5ZiJ8sLFu1EfKbcST5ju', NULL, NULL, '18279987522', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 16:46:36', NULL, NULL, 1, NULL, NULL, '2019-08-20 16:46:36', '2019-08-22 10:50:18', NULL, NULL);
INSERT INTO `t_user` VALUES (8489857857, '15207101014', '$2a$10$EKl9TyfPbp.xSEVDTwwrjexSSlUamWZNpY1wiHlPqoFd4SnWYbrom', '15207101014', NULL, '15207101014', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:03', '2019-08-20 20:58:58', NULL, NULL);
INSERT INTO `t_user` VALUES (8981546581, '15207101015', '$2a$10$9reVO9e/ytCnc59dN/MlpOhRcYUuYi/13XFp4XqDwKLfeJ.vdgSwG', '15207101015', NULL, '15207101015', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:03', '2019-08-20 20:59:22', NULL, NULL);
INSERT INTO `t_user` VALUES (8988918943, '15207101020', '$2a$10$NvNJC8ZSU/RUWFniDKGF9uorf8MiOesQoo5WROsdPv1MD8.AAVf6a', '15207101020', NULL, '15207101020', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:03', '2019-08-20 17:17:44', NULL, NULL);
INSERT INTO `t_user` VALUES (9470436648, '13600000401', '$2a$10$PT7oAAUSqWphVgSAH/4Ayebevmpihj56YFTJBOIAvlUyIDIwfpE52', NULL, NULL, '13600000401', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-21 22:23:30', NULL, NULL, 1, NULL, NULL, '2019-08-21 22:23:30', '2019-08-22 15:19:35', NULL, NULL);
INSERT INTO `t_user` VALUES (9696233980, '15207101013', '$2a$10$yQGWzN7SIYbkZVQXJVS4Pu/Sk9ZfPzMIn48hC8FUSItkN6QSQF/8m', '15207101013', NULL, '15207101013', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:03', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:03', '2019-08-20 23:11:08', NULL, NULL);
INSERT INTO `t_user` VALUES (9884952776, '13418967021', '$2a$10$Upd05nabWaaJIhGnjcglheaXJ2poGcb56qLqu0x81QH2ZXY6Bkz1i', NULL, NULL, '13418967021', NULL, NULL, '86', 1, 0, 0, NULL, NULL, NULL, '2019-08-20 15:32:22', NULL, NULL, 1, NULL, NULL, '2019-08-20 15:32:22', '2019-08-20 20:53:44', NULL, NULL);

-- ----------------------------
-- Table structure for t_user_authentication
-- ----------------------------
DROP TABLE IF EXISTS `t_user_authentication`;
CREATE TABLE `t_user_authentication`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户认证表主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `identity_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `identity_type` tinyint(2) NULL DEFAULT NULL COMMENT '证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证',
  `identity_front_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件正面照片',
  `identity_front_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件正面照片七牛key',
  `identity_front_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件正面照片七牛hash',
  `identity_back_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件背面照片',
  `identity_back_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件背面照片七牛key',
  `identity_back_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件背面照片七牛hash',
  `post_real_validate` tinyint(1) NULL DEFAULT 0 COMMENT '证件是否提交:1提交，0无效',
  `fhas_real_Validate` tinyint(1) NULL DEFAULT 0 COMMENT '证件审核：0未审核，1通过，2审核中',
  `bank_card_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `bank_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户行',
  `bank_card_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行卡正面',
  `bank_card_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bank_card_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `alipay_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付宝账号',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '缺省状态',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `auditor` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_id`(`user_id`) USING BTREE,
  UNIQUE INDEX `idx_identity_no`(`identity_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
