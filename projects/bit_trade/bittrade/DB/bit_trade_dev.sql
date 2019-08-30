/*
 Navicat Premium Data Transfer

 Source Server         : 154.222.138.249
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 154.222.138.249:3306
 Source Schema         : bit_trade_dev

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 30/08/2019 10:46:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '用户管理-账号初始密码', 'sys.user.initPassword', 'admin123', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '部门状态（1正常 0停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '删除标志（0代表删除 1代表正常）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 208 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', 'Bitrade', 0, 'admin', '15888888888', 'Futrade@qq.com', '1', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (201, 100, '', '研发部门', 3, 'admin', '18565741311', 'jd@qq.com', '1', '1', 'admin', '2018-11-13 16:05:52', 'admin', '2018-12-10 16:28:25');
INSERT INTO `sys_dept` VALUES (202, 100, '', '运营部', 2, 'Jackson', '15888888888', '', '1', '1', 'admin', '2019-04-12 15:45:37', '', NULL);
INSERT INTO `sys_dept` VALUES (203, 205, '', '商学院', 11, 'yslin', '17712345678', '', '1', '1', 'admin', '2019-05-20 09:30:33', 'admin', NULL);
INSERT INTO `sys_dept` VALUES (204, 100, '', '销售与服务', 9, 'zying', '17712345678', '', '1', '1', 'admin', '2019-05-20 09:32:13', '', NULL);
INSERT INTO `sys_dept` VALUES (205, 100, '', '市场部', 3, 'ysl', '17712345678', '', '1', '1', 'admin', '2019-05-20 09:33:56', '', NULL);
INSERT INTO `sys_dept` VALUES (206, 205, '', '商务部', 13, '老于', '17712345678', '', '1', '1', 'admin', '2019-05-22 21:17:50', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态（1正常 停0用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 392 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '2', 'sys_user_sex', '', '', 'Y', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '0', 'sys_user_sex', '', '', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '1', 'sys_show_hide', '', 'primary', 'Y', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '0', 'sys_show_hide', '', 'danger', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '1', 'sys_normal_disable', '', 'primary', 'Y', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '0', 'sys_normal_disable', '', 'danger', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-06-12 14:47:36', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-06-12 14:47:30', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (11, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (12, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (13, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (14, 1, '正常', '1', 'sys_notice_status', '', 'primary', 'Y', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (15, 2, '关闭', '0', 'sys_notice_status', '', 'danger', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (16, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (17, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (18, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (19, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (20, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (21, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (22, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (23, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (24, 8, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-06-12 17:07:55', '清空操作！');
INSERT INTO `sys_dict_data` VALUES (25, 1, '成功', '1', 'sys_common_status', '', 'primary', 'N', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-07-19 11:17:32', '正常状态');
INSERT INTO `sys_dict_data` VALUES (26, 2, '失败', '0', 'sys_common_status', '', 'danger', 'N', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-07-19 11:17:37', '停用状态！');
INSERT INTO `sys_dict_data` VALUES (102, 1, '买跌', '1', 'sys_contract_direction', '', 'danger', 'N', '1', 'admin', '2018-11-22 10:29:53', 'admin', '2018-12-04 15:50:02', '方向（涨/跌）：1-买跌，2-买涨');
INSERT INTO `sys_dict_data` VALUES (103, 2, '买涨', '2', 'sys_contract_direction', '', 'info', 'N', '1', 'admin', '2018-11-22 10:30:09', 'admin', '2018-12-04 15:49:54', '方向（涨/跌）：1-买跌，2-买涨');
INSERT INTO `sys_dict_data` VALUES (104, 1, '已拒绝', '0', 'audit_authentication_status', '', 'warning', 'Y', '1', 'admin', '2018-11-23 11:23:26', 'admin', '2018-11-30 14:46:08', '');
INSERT INTO `sys_dict_data` VALUES (105, 2, '已审核通过', '1', 'audit_authentication_status', '', 'success', 'Y', '1', 'admin', '2018-11-23 11:23:51', 'admin', '2018-11-30 14:44:35', '');
INSERT INTO `sys_dict_data` VALUES (106, 3, '待审核', '2', 'audit_authentication_status', '', 'danger', 'Y', '1', 'admin', '2018-11-23 11:24:09', 'admin', '2018-11-30 14:46:02', '');
INSERT INTO `sys_dict_data` VALUES (107, 1, '用户充值', '1', 'audit_recharge_type', '', 'primary', 'Y', '1', 'admin', '2018-11-23 17:48:44', 'admin', '2018-11-26 11:52:44', '');
INSERT INTO `sys_dict_data` VALUES (108, 2, '人工充值', '2', 'audit_recharge_type', '', 'info', 'Y', '1', 'admin', '2018-11-23 17:49:04', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (109, 2, '未付款', '2', 'audit_recharge_status', '', 'primary', 'Y', '1', 'admin', '2018-11-23 17:50:38', 'admin', '2018-12-12 10:31:28', '');
INSERT INTO `sys_dict_data` VALUES (110, 1, '待审核', '1', 'audit_recharge_status', '', 'danger', 'Y', '1', 'admin', '2018-11-23 17:50:56', 'admin', '2018-11-27 11:50:54', '');
INSERT INTO `sys_dict_data` VALUES (111, 3, '已通过', '3', 'audit_recharge_status', '', 'success', 'Y', '1', 'admin', '2018-11-23 17:51:12', 'admin', '2018-11-26 11:51:08', '');
INSERT INTO `sys_dict_data` VALUES (112, 4, '已拒绝', '4', 'audit_recharge_status', '', 'warning', 'Y', '1', 'admin', '2018-11-23 17:51:29', 'admin', '2018-11-26 11:51:03', '');
INSERT INTO `sys_dict_data` VALUES (113, 5, '已关闭', '5', 'audit_recharge_status', '', 'info', 'Y', '1', 'admin', '2018-11-23 17:52:01', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (114, 1, '用户提现', '1', 'audit_withdraw_type', '', 'primary', 'Y', '1', 'admin', '2018-11-26 11:34:36', 'admin', '2018-11-26 11:48:36', '');
INSERT INTO `sys_dict_data` VALUES (115, 2, '人工提现', '2', 'audit_withdraw_type', '', 'info', 'Y', '1', 'admin', '2018-11-26 11:34:55', 'admin', '2018-11-26 11:48:30', '');
INSERT INTO `sys_dict_data` VALUES (116, 1, '审核不通过', '0', 'audit_withdraw_status', '', 'warning', 'Y', '1', 'admin', '2018-11-26 11:35:56', 'admin', '2018-11-26 11:50:20', '');
INSERT INTO `sys_dict_data` VALUES (117, 2, '待审核', '2', 'audit_withdraw_status', '', 'danger', 'Y', '1', 'admin', '2018-11-26 11:36:28', 'admin', '2018-11-27 11:03:43', '');
INSERT INTO `sys_dict_data` VALUES (118, 3, '已通过', '1', 'audit_withdraw_status', '', 'success', 'Y', '1', 'admin', '2018-11-26 11:36:45', 'admin', '2018-11-27 11:03:38', '');
INSERT INTO `sys_dict_data` VALUES (119, 1, '一级代理', '1', 'user_agnt', '', 'primary', 'N', '1', 'admin', '2018-11-28 17:17:22', 'admin', '2018-12-10 14:17:14', '代理类型：1一级代理，2二级代理，3三级代理，0非代理');
INSERT INTO `sys_dict_data` VALUES (120, 2, '二级代理', '2', 'user_agnt', '', 'success', 'N', '1', 'admin', '2018-11-28 17:17:37', 'admin', '2018-12-10 14:17:24', '代理类型：1一级代理，2二级代理，3三级代理，0非代理');
INSERT INTO `sys_dict_data` VALUES (121, 3, '三级代理', '3', 'user_agnt', '', 'info', 'N', '1', 'admin', '2018-11-28 17:17:50', 'admin', '2018-12-10 14:17:30', '代理类型：1一级代理，2二级代理，3三级代理，0非代理');
INSERT INTO `sys_dict_data` VALUES (122, 4, '非代理', '-1', 'user_agnt', '', 'warning', 'N', '1', 'admin', '2018-11-28 17:18:08', 'admin', '2018-12-10 14:17:36', '代理类型：1一级代理，2二级代理，3三级代理，0非代理');
INSERT INTO `sys_dict_data` VALUES (123, 23, '审核中', '2', 'user_authentication', '', 'warning', 'N', '1', 'admin', '2018-11-28 17:18:46', 'admin', '2018-12-07 15:42:59', '证件审核：未提交，1通过，2审核中，3审核拒绝');
INSERT INTO `sys_dict_data` VALUES (124, 4, '已拒绝', '3', 'user_authentication', '', 'default', 'N', '1', 'admin', '2018-11-28 17:19:05', 'admin', '2018-12-07 15:42:56', '证件审核：未提交，1通过，2审核中，3审核拒绝');
INSERT INTO `sys_dict_data` VALUES (125, 1, '已认证', '1', 'user_authentication', '', 'success', 'N', '1', 'admin', '2018-11-28 17:19:54', 'admin', '2018-12-07 15:42:03', '证件审核：未提交，1通过，2审核中，3审核拒绝');
INSERT INTO `sys_dict_data` VALUES (126, 1, '盈', '1', 'contract_profitAndLoss', '', 'success', 'N', '1', 'admin', '2018-12-05 11:21:24', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (127, 2, '亏', '2', 'contract_profitAndLoss', '', 'danger', 'N', '1', 'admin', '2018-12-05 11:21:34', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (128, 3, '爆仓', '3', 'contract_status', '', 'danger', 'N', '1', 'admin', '2018-12-05 11:22:39', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (129, 2, '平仓', '2', 'contract_status', '', 'info', 'N', '1', 'admin', '2018-12-05 11:22:54', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (130, 1, '未开始', '1', 'article_status', '', 'warning', 'N', '1', 'admin', '2018-12-06 11:09:23', 'admin', '2018-12-06 11:09:52', '1=未开始 2=进行中 3=已过期');
INSERT INTO `sys_dict_data` VALUES (131, 2, '进行中', '2', 'article_status', '', 'success', 'N', '1', 'admin', '2018-12-06 11:09:47', '', NULL, '1=未开始 2=进行中 3=已过期');
INSERT INTO `sys_dict_data` VALUES (132, 3, '已过期', '3', 'article_status', '', 'danger', 'N', '1', 'admin', '2018-12-06 11:10:07', '', NULL, '1=未开始 2=进行中 3=已过期');
INSERT INTO `sys_dict_data` VALUES (133, 1, '未提交', '0', 'user_authentication', '', '', 'N', '1', 'admin', '2018-12-07 15:43:17', '', NULL, '0未提交，1通过，2审核中，3审核拒绝');
INSERT INTO `sys_dict_data` VALUES (134, 1, '一级代理', '1', 'user_agnt2', '', 'primary', 'Y', '1', 'admin', '2018-12-11 14:52:57', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (135, 2, '二级代理', '2', 'user_agnt2', '', 'success', 'Y', '1', 'admin', '2018-12-11 14:53:10', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (136, 3, '三级代理', '3', 'user_agnt2', '', 'info', 'Y', '1', 'admin', '2018-12-11 14:53:22', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (137, 1, '正常订单', '1', 'contract_token_order', '', 'success', 'N', '1', 'admin', '2018-12-18 11:00:39', '', NULL, '1=正常订单，2=体验金订单');
INSERT INTO `sys_dict_data` VALUES (138, 2, '体验金订单', '2', 'contract_token_order', '', 'warning', 'N', '1', 'admin', '2018-12-18 11:00:53', '', NULL, '1=正常订单，2=体验金订单');
INSERT INTO `sys_dict_data` VALUES (139, 1, 'USDT', '1', 'recharge_currency', '', 'primary', 'Y', '1', 'admin', '2018-12-18 11:28:41', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (140, 2, 'USDT（体验金）', '2', 'recharge_currency', '', 'success', 'Y', '1', 'admin', '2018-12-18 11:29:06', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (141, 3, '人工充值（体验金）', '3', 'audit_recharge_type', '', 'success', 'Y', '1', 'admin', '2018-12-18 13:57:58', 'admin', '2018-12-18 13:58:11', '');
INSERT INTO `sys_dict_data` VALUES (142, 1, 'IOS', '1', 'app_type', '', 'primary', 'Y', '1', 'admin', '2018-12-21 17:13:23', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (143, 2, 'Android', '2', 'app_type', '', 'success', 'Y', '1', 'admin', '2018-12-21 17:13:48', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (144, 3, 'H5', '3', 'app_type', '', 'info', 'Y', '1', 'admin', '2018-12-21 17:14:04', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (145, 1, '否', '0', 'app_status', '', 'default', 'Y', '1', 'admin', '2018-12-21 18:34:38', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (146, 2, '强制升级', '1', 'app_status', '', 'default', 'Y', '1', 'admin', '2018-12-21 18:34:53', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (147, 1, '否', '1', 'user_internal_account', '', 'default', 'Y', '1', 'admin', '2019-01-07 16:43:40', '', NULL, '否');
INSERT INTO `sys_dict_data` VALUES (148, 2, '是', '2', 'user_internal_account', '', 'primary', 'Y', '1', 'admin', '2019-01-07 16:43:59', '', NULL, '是');
INSERT INTO `sys_dict_data` VALUES (149, 4, '奖励金转入', '4', 'audit_recharge_type', '', 'default', 'Y', '1', 'admin', '2019-01-08 12:00:11', 'admin', '2019-01-08 12:00:32', '');
INSERT INTO `sys_dict_data` VALUES (150, 1, '禁用', '0', 'common_status', '', 'danger', 'Y', '1', 'admin', '2019-01-08 19:59:27', '', NULL, '禁用');
INSERT INTO `sys_dict_data` VALUES (151, 2, '正常', '1', 'common_status', '', 'primary', 'Y', '1', 'admin', '2019-01-08 19:59:46', '', NULL, '正常');
INSERT INTO `sys_dict_data` VALUES (152, 1, '手机网页端 - M', 'M', 'contract_client_type', '', 'info', 'N', '1', 'admin', '2019-01-10 17:28:28', 'admin', '2019-01-10 17:29:07', '交易订单号增加平台区分（M、Android、iOS）');
INSERT INTO `sys_dict_data` VALUES (153, 2, '安卓端 - A', 'A', 'contract_client_type', '', 'info', 'N', '1', 'admin', '2019-01-10 17:29:01', '', NULL, '交易订单号增加平台区分（M、Android、iOS）');
INSERT INTO `sys_dict_data` VALUES (154, 3, '苹果端 - S', 'S', 'contract_client_type', '', 'info', 'N', '1', 'admin', '2019-01-10 17:29:42', '', NULL, '交易订单号增加平台区分（M、Android、iOS）');
INSERT INTO `sys_dict_data` VALUES (155, 1, '已取消', '0', 'orderUser_orderStauts', '', 'default', 'N', '1', 'admin', '2019-03-19 14:31:02', 'admin', '2019-03-19 14:34:34', '（0=已取消，1=已完成，2=待支付，3=待放行，4=拒绝放行）');
INSERT INTO `sys_dict_data` VALUES (156, 2, '已完成', '1', 'orderUser_orderStauts', '', 'success', 'N', '1', 'admin', '2019-03-19 14:31:27', 'admin', '2019-03-19 14:35:05', '（0=已取消，1=已完成，2=待支付，3=待放行，4=拒绝放行）');
INSERT INTO `sys_dict_data` VALUES (157, 2, '待支付', '2', 'orderUser_orderStauts', '', 'info', 'N', '1', 'admin', '2019-03-19 14:32:36', 'admin', '2019-03-19 14:34:57', '（0=已取消，1=已完成，2=待支付，3=待放行，4=拒绝放行）');
INSERT INTO `sys_dict_data` VALUES (158, 4, '待放行', '3', 'orderUser_orderStauts', '', 'warning', 'N', '1', 'admin', '2019-03-19 14:33:12', 'admin', '2019-03-19 14:35:18', '（0=已取消，1=已完成，2=待支付，3=待放行，4=拒绝放行）');
INSERT INTO `sys_dict_data` VALUES (159, 4, '客诉中', '4', 'orderUser_orderStauts', '', 'warning', 'N', '1', 'admin', '2019-03-19 14:33:58', 'admin', '2019-04-04 14:22:31', '（0=已取消，1=已完成，2=待支付，3=待放行，4=拒绝放行）');
INSERT INTO `sys_dict_data` VALUES (160, 1, 'CNY/USDT', '0', 'orderUser_productType', '', 'primary', 'N', '1', 'admin', '2019-03-19 14:39:12', 'admin', '2019-08-02 23:35:57', '交易对类型（0=CNY/USDT，1=CNY/BI）');
INSERT INTO `sys_dict_data` VALUES (161, 2, 'CNY/BI', '1', 'orderUser_productType', '', 'success', 'N', '1', 'admin', '2019-03-19 14:39:55', 'admin', '2019-08-02 23:35:45', '交易对类型（0=CNY/USDT，1=CNY/BI）');
INSERT INTO `sys_dict_data` VALUES (162, 1, '出售订单', '1', 'orderUser_type', '', 'primary', 'N', '1', 'admin', '2019-03-19 15:00:10', '', NULL, '订单类型（1=出售订单，2=购买订单）');
INSERT INTO `sys_dict_data` VALUES (163, 2, '购买订单', '2', 'orderUser_type', '', 'success', 'N', '1', 'admin', '2019-03-19 15:00:36', '', NULL, '订单类型（1=出售订单，2=购买订单）');
INSERT INTO `sys_dict_data` VALUES (164, 0, '未知', '0', 'orderUser_orderStautsType', '', 'default', 'Y', '1', 'admin', '2019-03-19 15:46:03', '', NULL, '订单状态（0=未知，1=客户取消，2=客服取消，3=超时取消）');
INSERT INTO `sys_dict_data` VALUES (165, 1, '客户取消', '1', 'orderUser_orderStautsType', '', 'warning', 'N', '1', 'admin', '2019-03-19 15:46:29', '', NULL, '订单状态（0=未知，1=客户取消，2=客服取消，3=超时取消）');
INSERT INTO `sys_dict_data` VALUES (166, 2, '客服取消', '2', 'orderUser_orderStautsType', '', 'warning', 'N', '1', 'admin', '2019-03-19 15:46:51', '', NULL, '订单状态（0=未知，1=客户取消，2=客服取消，3=超时取消）');
INSERT INTO `sys_dict_data` VALUES (167, 3, '超时取消', '3', 'orderUser_orderStautsType', '', 'warning', 'N', '1', 'admin', '2019-03-19 15:47:29', '', NULL, '订单状态（0=未知，1=客户取消，2=客服取消，3=超时取消）');
INSERT INTO `sys_dict_data` VALUES (168, 1, '已认证', '1', 'rderEntrust_isAttestation', '', 'success', 'N', '1', 'admin', '2019-03-20 14:16:57', 'admin', '2019-03-20 14:36:07', '是否认证（1=已认证，2=未认证）');
INSERT INTO `sys_dict_data` VALUES (169, 2, '未认证', '2', 'rderEntrust_isAttestation', '', 'warning', 'N', '1', 'admin', '2019-03-20 14:17:31', 'admin', '2019-03-20 14:35:58', '是否认证（1=已认证，2=未认证）');
INSERT INTO `sys_dict_data` VALUES (170, 2, '认证成功', '1', 'personalCard_auth_Stauts', '', 'success', 'Y', '1', 'admin', '2019-03-26 12:14:51', 'admin', '2019-07-15 11:07:57', '1=认证成功，2=待审核，3=审核不成功,4=黑名单');
INSERT INTO `sys_dict_data` VALUES (171, 1, '待审核', '2', 'personalCard_auth_Stauts', '', 'info', 'N', '1', 'admin', '2019-03-26 12:15:18', 'admin', '2019-07-15 11:07:49', '1=认证成功，2=待审核，3=审核不成功,4=黑名单');
INSERT INTO `sys_dict_data` VALUES (172, 3, '审核不成功', '3', 'personalCard_auth_Stauts', '', 'warning', 'N', '1', 'admin', '2019-03-26 14:18:46', '', NULL, '1=认证成功，2=待审核，3=审核不成功,4=黑名单');
INSERT INTO `sys_dict_data` VALUES (173, 4, '黑名单', '4', 'personalCard_auth_Stauts', '', 'danger', 'N', '1', 'admin', '2019-03-26 14:19:26', '', NULL, '1=认证成功，2=待审核，3=审核不成功,4=黑名单');
INSERT INTO `sys_dict_data` VALUES (174, 1, '梦想合伙人', '1', 'personalPartner_type', '', 'primary', 'N', '1', 'admin', '2019-04-09 14:32:46', '', NULL, '（1=梦想合伙人，2=节点合伙人）');
INSERT INTO `sys_dict_data` VALUES (175, 2, '节点合伙人', '2', 'personalPartner_type', '', 'info', 'N', '1', 'admin', '2019-04-09 14:34:09', 'admin', '2019-04-09 15:11:52', '（1=梦想合伙人，2=节点合伙人）');
INSERT INTO `sys_dict_data` VALUES (176, 1, '未溢价', '1', 'premium_Stauts', '', 'primary', 'N', '1', 'admin', '2019-04-10 15:56:42', '', NULL, '1=未溢价，2=已溢价');
INSERT INTO `sys_dict_data` VALUES (177, 2, '已溢价', '2', 'premium_Stauts', '', 'success', 'N', '1', 'admin', '2019-04-10 15:57:32', '', NULL, '1=未溢价，2=已溢价');
INSERT INTO `sys_dict_data` VALUES (178, 1, '未激活', '0', 'beta_activate', '', 'primary', 'N', '1', 'admin', '2019-04-10 16:01:26', '', NULL, '是否激活（0=未激活，1=已激活）----何为激活，意思就是此狗已绑定创世用户，可以预约出售了');
INSERT INTO `sys_dict_data` VALUES (179, 2, '已激活', '1', 'beta_activate', '', 'success', 'N', '1', 'admin', '2019-04-10 16:01:55', '', NULL, '是否激活（0=未激活，1=已激活）----何为激活，意思就是此狗已绑定创世用户，可以预约出售了');
INSERT INTO `sys_dict_data` VALUES (200, 1, '隐藏', '-1', 'beta_order_stauts', '', 'default', 'Y', '1', 'admin', '2019-04-23 02:27:23', '', NULL, '订单状态（-1=隐藏，0=取消，1=完成，2=待转让，3=待支付，4=待放狗，5=客诉）');
INSERT INTO `sys_dict_data` VALUES (201, 1, '取消', '0', 'beta_order_stauts', '', 'primary', 'N', '1', 'admin', '2019-04-23 02:27:49', '', NULL, '订单状态（-1=隐藏，0=取消，1=完成，2=待转让，3=待支付，4=待放狗，5=客诉）');
INSERT INTO `sys_dict_data` VALUES (202, 1, '完成', '1', 'beta_order_stauts', '', 'success', 'N', '1', 'admin', '2019-04-23 02:28:21', '', NULL, '订单状态（-1=隐藏，0=取消，1=完成，2=待转让，3=待支付，4=待放狗，5=客诉）');
INSERT INTO `sys_dict_data` VALUES (203, 2, '待转让', '2', 'beta_order_stauts', '', 'info', 'Y', '1', 'admin', '2019-04-23 02:28:40', '', NULL, '订单状态（-1=隐藏，0=取消，1=完成，2=待转让，3=待支付，4=待放狗，5=客诉）');
INSERT INTO `sys_dict_data` VALUES (204, 3, '待支付', '3', 'beta_order_stauts', '', 'info', 'N', '1', 'admin', '2019-04-23 02:29:03', '', NULL, '订单状态（-1=隐藏，0=取消，1=完成，2=待转让，3=待支付，4=待放狗，5=客诉）');
INSERT INTO `sys_dict_data` VALUES (205, 4, '待放狗', '4', 'beta_order_stauts', '', 'warning', 'N', '1', 'admin', '2019-04-23 02:29:29', '', NULL, '订单状态（-1=隐藏，0=取消，1=完成，2=待转让，3=待支付，4=待放狗，5=客诉）');
INSERT INTO `sys_dict_data` VALUES (206, 5, '客诉', '5', 'beta_order_stauts', '', 'danger', 'N', '1', 'admin', '2019-04-23 02:29:48', '', NULL, '订单状态（-1=隐藏，0=取消，1=完成，2=待转让，3=待支付，4=待放狗，5=客诉）');
INSERT INTO `sys_dict_data` VALUES (207, 1, '等待', '0', 'beta_reservStauts', '', 'success', 'N', '1', 'admin', '2019-04-23 03:51:20', '', NULL, '预约状态（0=等待，1=成功，2=失败）');
INSERT INTO `sys_dict_data` VALUES (208, 2, '成功', '1', 'beta_reservStauts', '', 'info', 'N', '1', 'admin', '2019-04-23 03:51:39', '', NULL, '预约状态（0=等待，1=成功，2=失败）');
INSERT INTO `sys_dict_data` VALUES (209, 3, '失败', '2', 'beta_reservStauts', '', 'warning', 'N', '1', 'admin', '2019-04-23 03:52:14', '', NULL, '预约状态（0=等待，1=成功，2=失败）');
INSERT INTO `sys_dict_data` VALUES (210, 1, '用户', '0', 'beta_platform_and_user', '', 'success', 'N', '1', 'admin', '2019-04-23 21:58:43', 'admin', '2019-06-03 22:52:15', '平台创建还是用户创建（0.未知 1.平台 2.用户团队收益3.用户推广收益4.订单拆分）');
INSERT INTO `sys_dict_data` VALUES (211, 1, '平台', '1', 'beta_platform_and_user', '', 'info', 'N', '1', 'admin', '2019-04-23 21:59:04', 'admin', '2019-06-03 22:54:10', '平台创建还是用户创建（0.未知 1.平台 2.用户团队收益3.用户推广收益4.订单拆分）');
INSERT INTO `sys_dict_data` VALUES (212, 1, '放狗', '1', 'beta_processeda_order_stauts', '', 'success', 'N', '1', 'admin', '2019-04-24 20:42:43', '', NULL, '处理订单状态（1.放狗 2拒绝放狗）');
INSERT INTO `sys_dict_data` VALUES (213, 2, '拒绝放狗', '2', 'beta_processeda_order_stauts', '', 'warning', 'Y', '1', 'admin', '2019-04-24 20:43:05', '', NULL, '处理订单状态（1.放狗 2拒绝放狗）');
INSERT INTO `sys_dict_data` VALUES (214, 1, '未出售', '0', 'beta_sell_Status', '', 'primary', 'N', '1', 'admin', '2019-05-05 20:41:54', '', NULL, '出售状态（0=未出售，1=已出售）');
INSERT INTO `sys_dict_data` VALUES (215, 2, '已出售', '1', 'beta_sell_Status', '', 'success', 'N', '1', 'admin', '2019-05-05 20:42:11', '', NULL, '出售状态（0=未出售，1=已出售）');
INSERT INTO `sys_dict_data` VALUES (216, 1, '普通用户', '1', 'personalCard_currentIdentity', '', 'success', 'N', '1', 'admin', '2019-05-13 23:30:03', 'admin', '2019-05-13 23:32:17', '1=普通用户，2=推广大使，3=服务商，4=合伙人，5=钻石合伙人，6=皇冠合伙人');
INSERT INTO `sys_dict_data` VALUES (217, 2, '推广大使', '2', 'personalCard_currentIdentity', '', 'success', 'N', '1', 'admin', '2019-05-13 23:30:30', 'admin', '2019-05-13 23:32:07', '1=普通用户，2=推广大使，3=服务商，4=合伙人，5=钻石合伙人，6=皇冠合伙人');
INSERT INTO `sys_dict_data` VALUES (218, 3, '服务商', '3', 'personalCard_currentIdentity', '', 'success', 'N', '1', 'admin', '2019-05-13 23:30:43', 'admin', '2019-05-13 23:32:12', '1=普通用户，2=推广大使，3=服务商，4=合伙人，5=钻石合伙人，6=皇冠合伙人');
INSERT INTO `sys_dict_data` VALUES (219, 4, '合伙人', '4', 'personalCard_currentIdentity', '', 'success', 'N', '1', 'admin', '2019-05-13 23:30:54', 'admin', '2019-05-13 23:32:01', '1=普通用户，2=推广大使，3=服务商，4=合伙人，5=钻石合伙人，6=皇冠合伙人');
INSERT INTO `sys_dict_data` VALUES (220, 5, '钻石合伙人', '5', 'personalCard_currentIdentity', '', 'success', 'N', '1', 'admin', '2019-05-13 23:31:06', 'admin', '2019-05-13 23:31:55', '1=普通用户，2=推广大使，3=服务商，4=合伙人，5=钻石合伙人，6=皇冠合伙人');
INSERT INTO `sys_dict_data` VALUES (221, 6, '皇冠合伙人', '6', 'personalCard_currentIdentity', '', 'success', 'N', '1', 'admin', '2019-05-13 23:31:30', '', NULL, '1=普通用户，2=推广大使，3=服务商，4=合伙人，5=钻石合伙人，6=皇冠合伙人');
INSERT INTO `sys_dict_data` VALUES (222, 1, 'USDT', '0', 'beta_productType', '', 'primary', 'Y', '1', 'admin', '2019-05-16 12:55:44', '', NULL, '钱包类型（0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5累计收益,6团队收益）');
INSERT INTO `sys_dict_data` VALUES (223, 2, 'BITT', '1', 'beta_productType', '', 'primary', 'Y', '1', 'admin', '2019-05-16 12:56:07', 'admin', '2019-05-16 12:56:22', '钱包类型（0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5累计收益,6团队收益）');
INSERT INTO `sys_dict_data` VALUES (224, 3, '时间链', '2', 'beta_productType', '', 'primary', 'Y', '1', 'admin', '2019-05-16 12:56:45', '', NULL, '钱包类型（0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5累计收益,6团队收益）');
INSERT INTO `sys_dict_data` VALUES (225, 4, 'β通证', '3', 'beta_productType', '', 'primary', 'Y', '1', 'admin', '2019-05-16 12:57:00', '', NULL, '钱包类型（0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5累计收益,6团队收益）');
INSERT INTO `sys_dict_data` VALUES (226, 5, '推广收益', '4', 'beta_productType', '', 'primary', 'Y', '1', 'admin', '2019-05-16 12:57:30', '', NULL, '钱包类型（0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5累计收益,6团队收益）');
INSERT INTO `sys_dict_data` VALUES (227, 6, '累计收益', '5', 'beta_productType', '', 'primary', 'Y', '1', 'admin', '2019-05-16 12:58:03', '', NULL, '钱包类型（0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5累计收益,6团队收益）');
INSERT INTO `sys_dict_data` VALUES (228, 7, '团队收益', '6', 'beta_productType', '', 'primary', 'Y', '1', 'admin', '2019-05-16 12:58:35', '', NULL, '钱包类型（0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5累计收益,6团队收益）');
INSERT INTO `sys_dict_data` VALUES (229, 1, '转让人短信', '1', 'record_sms_log_type', '', 'success', 'Y', '1', 'admin', '2019-05-16 18:39:25', 'admin', '2019-05-17 10:12:34', '短信发送类型（1转让人 2领养人）');
INSERT INTO `sys_dict_data` VALUES (230, 2, '领养人短信', '2', 'record_sms_log_type', '', 'primary', 'Y', '1', 'admin', '2019-05-16 18:39:36', 'admin', '2019-05-17 10:10:57', '短信发送类型（1转让人 2领养人）');
INSERT INTO `sys_dict_data` VALUES (231, 1, '已启用', '1', 'country_code_use', '', 'primary', 'Y', '1', 'admin', '2019-05-20 11:01:27', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (232, 2, '未启用', '2', 'country_code_use', '', 'info', 'Y', '1', 'admin', '2019-05-20 11:01:47', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (233, 1, '可以', '1', 'coin_type', '', 'primary', 'Y', '1', 'admin', '2019-05-20 18:56:00', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (234, 2, '不可以', '2', 'coin_type', '', 'success', 'Y', '1', 'admin', '2019-05-20 18:56:10', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (235, 3, '团队', '2', 'beta_platform_and_user', '', 'info', 'Y', '1', 'admin', '2019-06-03 22:53:00', 'admin', '2019-06-03 22:58:42', '平台创建还是用户创建（0.未知 1.平台 2.用户团队收益3.用户推广收益4.订单拆分）');
INSERT INTO `sys_dict_data` VALUES (236, 4, '推广', '3', 'beta_platform_and_user', '', 'info', 'N', '1', 'admin', '2019-06-03 22:53:48', 'admin', '2019-06-03 22:58:32', '平台创建还是用户创建（0.未知 1.平台 2.用户团队收益3.用户推广收益4.订单拆分）');
INSERT INTO `sys_dict_data` VALUES (237, 5, '拆分', '4', 'beta_platform_and_user', '', 'info', 'N', '1', 'admin', '2019-06-03 22:55:13', 'admin', '2019-06-03 22:58:25', '平台创建还是用户创建（0.未知 1.平台 2.用户团队收益3.用户推广收益4.订单拆分）');
INSERT INTO `sys_dict_data` VALUES (238, 1, '收付款二维码', '1', 'beta_coverage_Type', '', 'primary', 'N', '1', 'admin', '2019-06-08 18:17:01', '', NULL, '覆盖的类型1收付款二维码2.付款图片');
INSERT INTO `sys_dict_data` VALUES (239, 2, '付款图片', '2', 'beta_coverage_Type', '', 'success', 'N', '1', 'admin', '2019-06-08 18:17:15', '', NULL, '覆盖的类型1收付款二维码2.付款图片');
INSERT INTO `sys_dict_data` VALUES (240, 9, '充值', '10', 'sys_oper_type', '', 'primary', 'Y', '1', 'admin', '2019-06-10 18:05:17', 'admin', '2019-06-10 18:07:30', '充值');
INSERT INTO `sys_dict_data` VALUES (241, 11, '放币', '11', 'sys_oper_type', '', 'info', 'N', '1', 'admin', '2019-06-11 09:34:53', '', NULL, '放币');
INSERT INTO `sys_dict_data` VALUES (242, 1, '成功', '1', 'import_stauts', '', 'success', 'N', '1', 'admin', '2019-06-11 09:35:21', 'admin', '2019-06-11 16:37:58', '导入状态（1=成功，2=失败）');
INSERT INTO `sys_dict_data` VALUES (243, 2, '失败', '2', 'import_stauts', '', 'warning', 'N', '1', 'admin', '2019-06-11 09:35:39', '', NULL, '导入状态（1=成功，2=失败）');
INSERT INTO `sys_dict_data` VALUES (244, 12, '取消订单', '12', 'sys_oper_type', '', 'success', 'N', '1', 'admin', '2019-06-11 09:37:05', '', NULL, '取消订单');
INSERT INTO `sys_dict_data` VALUES (249, 1, '衣服', '1', 'house_product_status', '', 'primary', 'N', '1', 'admin', '2019-06-14 14:12:58', 'admin', '2019-06-25 21:26:51', '商品类型');
INSERT INTO `sys_dict_data` VALUES (250, 2, '背景', '2', 'house_product_status', '', 'primary', 'Y', '1', 'admin', '2019-06-14 14:13:18', 'admin', '2019-06-25 21:27:02', '商品类型');
INSERT INTO `sys_dict_data` VALUES (251, 3, '狗舍', '3', 'house_product_status', '', 'primary', 'N', '1', 'admin', '2019-06-14 14:13:36', 'admin', '2019-06-25 21:27:18', '商品类型');
INSERT INTO `sys_dict_data` VALUES (252, 4, '道具', '4', 'house_product_status', '', 'primary', 'N', '1', 'admin', '2019-06-14 14:13:52', 'admin', '2019-06-25 21:27:30', '商品类型');
INSERT INTO `sys_dict_data` VALUES (253, 2, '未公开', '0', 'beta_HouseProduct_status', '', 'info', 'N', '1', 'admin', '2019-06-14 14:21:39', 'admin', '2019-06-27 22:59:37', '状态(0:未公开 1:出售 2:停售 3:不显示)');
INSERT INTO `sys_dict_data` VALUES (254, 1, '出售', '1', 'beta_HouseProduct_status', '', 'success', 'N', '1', 'admin', '2019-06-14 14:22:08', 'admin', '2019-06-27 22:59:30', '状态(0:未公开 1:出售 2:停售 3:不显示)');
INSERT INTO `sys_dict_data` VALUES (255, 2, '停售', '2', 'beta_HouseProduct_status', '', 'info', 'N', '1', 'admin', '2019-06-14 14:22:26', 'admin', '2019-06-14 19:44:39', '状态(0:未公开 1:出售 2:停售 3:不显示)');
INSERT INTO `sys_dict_data` VALUES (256, 3, '不显示', '3', 'beta_HouseProduct_status', '', 'default', 'N', '1', 'admin', '2019-06-14 14:22:51', 'admin', '2019-06-14 19:44:32', '状态(0:未公开 1:出售 2:停售 3:不显示)');
INSERT INTO `sys_dict_data` VALUES (257, 1, '母狗', '1', 'beta_HouseProduct_beSuitable', '', 'success', 'N', '1', 'admin', '2019-06-14 14:48:33', 'admin', '2019-06-14 19:44:04', '适用 1.母狗 2.公狗');
INSERT INTO `sys_dict_data` VALUES (258, 2, '公狗', '2', 'beta_HouseProduct_beSuitable', '', 'primary', 'N', '1', 'admin', '2019-06-14 14:48:49', '', NULL, '适用 1.母狗 2.公狗');
INSERT INTO `sys_dict_data` VALUES (259, 1, '未出生', '-1', 'beta_pup_state', '', 'default', 'Y', '1', 'admin', '2019-06-18 17:33:20', 'admin', '2019-06-20 20:59:13', '状态 (-1:未出生 0:小狗 1:大狗 2:已转让)');
INSERT INTO `sys_dict_data` VALUES (260, 2, '小狗', '0', 'beta_pup_state', '', 'primary', 'N', '1', 'admin', '2019-06-18 17:33:37', 'admin', '2019-06-20 20:59:08', '状态 (-1:未出生 0:小狗 1:大狗 2:已转让)');
INSERT INTO `sys_dict_data` VALUES (261, 3, '大狗', '1', 'beta_pup_state', '', 'success', 'N', '1', 'admin', '2019-06-18 17:33:52', 'admin', '2019-06-20 20:59:02', '状态 (-1:未出生 0:小狗 1:大狗 2:已转让)');
INSERT INTO `sys_dict_data` VALUES (262, 4, '已转让', '2', 'beta_pup_state', '', 'info', 'N', '1', 'admin', '2019-06-18 17:34:11', 'admin', '2019-06-20 20:58:56', '状态 (-1:未出生 0:小狗 1:大狗 2:已转让)');
INSERT INTO `sys_dict_data` VALUES (263, 1, '公狗', '0', 'beta_pup_gender', '', 'primary', 'N', '1', 'admin', '2019-06-18 20:53:25', '', NULL, '性别 (0:公狗 1:母狗)');
INSERT INTO `sys_dict_data` VALUES (264, 2, '母狗', '1', 'beta_pup_gender', '', 'info', 'N', '1', 'admin', '2019-06-18 20:53:45', '', NULL, '性别 (0:公狗 1:母狗)');
INSERT INTO `sys_dict_data` VALUES (265, 6, '小狗', '5', 'beta_platform_and_user', '', 'info', 'N', '1', 'admin', '2019-06-19 16:18:32', '', NULL, '平台创建还是用户创建（0.未知 1.平台 2.用户团队收益3.用户推广收益4.订单拆分5.小狗）');
INSERT INTO `sys_dict_data` VALUES (266, 4, '衣服', '0', 'house_product_showPosition', '', 'success', 'N', '1', 'admin', '2019-06-19 17:29:06', 'admin', '2019-07-01 17:26:15', '商品展示位置 0 衣服1 宅基地 2 狗舍 3 道具');
INSERT INTO `sys_dict_data` VALUES (267, 2, '宅基地', '1', 'house_product_showPosition', '', 'info', 'N', '1', 'admin', '2019-06-19 17:29:19', 'admin', '2019-06-22 18:28:03', '商品展示位置 0 衣服1 宅基地 2 狗舍 3 道具');
INSERT INTO `sys_dict_data` VALUES (268, 3, '狗舍', '2', 'house_product_showPosition', '', 'warning', 'N', '1', 'admin', '2019-06-19 17:29:39', 'admin', '2019-06-22 18:27:56', '商品展示位置 0 衣服1 宅基地 2 狗舍 3 道具');
INSERT INTO `sys_dict_data` VALUES (269, 1, '1次性消耗', '1', 'house_product_expendType', '', 'info', 'N', '1', 'admin', '2019-06-19 17:30:47', '', NULL, '消耗类别1次性消耗，2永久商品');
INSERT INTO `sys_dict_data` VALUES (270, 2, '永久商品', '2', 'house_product_expendType', '', 'info', 'N', '1', 'admin', '2019-06-19 17:31:01', '', NULL, '消耗类别1次性消耗，2永久商品');
INSERT INTO `sys_dict_data` VALUES (271, 1, '不孕不育期', '-1', 'beta_betaFemina_status', '', 'default', 'Y', '1', 'admin', '2019-06-20 16:07:55', '', NULL, '母狗状态(-1=不孕不育期,1=正常期，2=怀孕期，3=月子期)');
INSERT INTO `sys_dict_data` VALUES (272, 2, '正常期', '1', 'beta_betaFemina_status', '', 'primary', 'N', '1', 'admin', '2019-06-20 16:08:15', '', NULL, '母狗状态(-1=不孕不育期,1=正常期，2=怀孕期，3=月子期)');
INSERT INTO `sys_dict_data` VALUES (273, 3, '怀孕期', '2', 'beta_betaFemina_status', '1', 'info', 'N', '1', 'admin', '2019-06-20 16:08:31', '', NULL, '母狗状态(-1=不孕不育期,1=正常期，2=怀孕期，3=月子期)');
INSERT INTO `sys_dict_data` VALUES (274, 3, '月子期', '3', 'beta_betaFemina_status', '', 'info', 'N', '1', 'admin', '2019-06-20 16:08:48', '', NULL, '母狗状态(-1=不孕不育期,1=正常期，2=怀孕期，3=月子期)');
INSERT INTO `sys_dict_data` VALUES (275, 1, '道具', '3', 'house_product_showPosition', '', 'info', 'N', '1', 'admin', '2019-06-20 20:54:33', 'admin', '2019-07-01 17:26:08', '商品展示位置 0 衣服1 宅基地 2 狗舍 3 道具');
INSERT INTO `sys_dict_data` VALUES (276, 5, '母狗狗粮', '5', 'house_product_status', '', 'info', 'N', '1', 'admin', '2019-06-20 20:55:56', 'admin', '2019-06-26 17:12:17', '商品类型');
INSERT INTO `sys_dict_data` VALUES (278, 1, '未读', '0', 'beta_house_history', '', 'primary', 'N', '1', 'admin', '2019-06-20 22:42:29', 'admin', '2019-06-20 23:06:35', '状态 (0:未读 1：已读)');
INSERT INTO `sys_dict_data` VALUES (279, 2, '已读', '1', 'beta_house_history', '', 'success', 'N', '1', 'admin', '2019-06-20 22:42:41', '', NULL, '状态 (0:未读 1：已读)');
INSERT INTO `sys_dict_data` VALUES (280, 1, '未收取', '0', 'beta_house_status', '', 'primary', 'N', '1', 'admin', '2019-06-21 09:59:29', '', NULL, '状态 (0:未收取 1：已收取)');
INSERT INTO `sys_dict_data` VALUES (281, 2, '已收取', '1', 'beta_house_status', '', 'success', 'N', '1', 'admin', '2019-06-21 09:59:42', '', NULL, '状态 (0:未收取 1：已收取)');
INSERT INTO `sys_dict_data` VALUES (287, 1, '时', '1', 'beta_product_time', '', 'default', 'N', '1', 'admin', '2019-06-21 19:23:23', '', NULL, '狗窝商品时间(1=时2=分)');
INSERT INTO `sys_dict_data` VALUES (288, 2, '分', '2', 'beta_product_time', '', 'primary', 'N', '1', 'admin', '2019-06-21 19:23:44', '', NULL, '狗窝商品时间(1=时2=分)');
INSERT INTO `sys_dict_data` VALUES (289, 1, '正常', '0', 'price_risk', '', 'success', 'Y', '1', 'admin', '2019-06-22 18:11:12', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (290, 2, '警告', '1', 'price_risk', '', 'danger', 'Y', '1', 'admin', '2019-06-22 18:11:32', 'admin', '2019-06-22 18:14:52', '');
INSERT INTO `sys_dict_data` VALUES (291, 1, '正常', '0', 'saturated_risk', '', 'success', 'Y', '1', 'admin', '2019-06-22 18:12:03', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (292, 2, '警告', '1', 'saturated_risk', '', 'danger', 'Y', '1', 'admin', '2019-06-22 18:12:16', 'admin', '2019-06-22 18:14:46', '');
INSERT INTO `sys_dict_data` VALUES (296, 7, '狗盆', '7', 'house_product_status', '', 'info', 'N', '1', 'admin', '2019-06-26 11:17:47', '', NULL, '商品类型');
INSERT INTO `sys_dict_data` VALUES (297, 2, '小狗狗粮', '6', 'house_puppyProduct_status', '', 'primary', 'N', '1', 'admin', '2019-06-26 17:11:26', 'admin', '2019-07-08 15:48:15', '小狗商品类型 6:小狗狗粮,9加速卡');
INSERT INTO `sys_dict_data` VALUES (298, 1, '加速卡', '9', 'house_puppyProduct_status', '', 'primary', 'N', '1', 'admin', '2019-06-26 17:55:41', 'admin', '2019-07-08 15:46:49', '加速卡');
INSERT INTO `sys_dict_data` VALUES (299, 5, '删除', '-1', 'beta_HouseProduct_status', '', 'default', 'N', '1', 'admin', '2019-06-27 10:24:04', '', NULL, '状态(-1:删除，0:未公开 1:出售 2:停售 3:不显示)');
INSERT INTO `sys_dict_data` VALUES (300, 8, '母狗属性增值卡', '8', 'house_product_status', '', 'primary', 'N', '1', 'admin', '2019-06-27 10:48:46', 'admin', '2019-06-28 15:06:09', '商品类别1:衣服 2：背景 3.狗舍 4.道具 5.大狗狗粮 6.小狗狗粮 7狗盆8加速卡');
INSERT INTO `sys_dict_data` VALUES (301, 10, '孕期卡', '10', 'house_product_status', '', 'info', 'N', '1', 'admin', '2019-06-28 16:16:11', 'admin', '2019-07-02 11:28:07', '商品类型');
INSERT INTO `sys_dict_data` VALUES (302, 1, '使用中', '0', 'beta_house_backpack_use_status', '', 'primary', 'N', '1', 'admin', '2019-06-28 17:02:44', '', NULL, '状态 (0:使用中 1：过期)');
INSERT INTO `sys_dict_data` VALUES (303, 2, '过期', '1', 'beta_house_backpack_use_status', '', 'warning', 'N', '1', 'admin', '2019-06-28 17:03:09', '', NULL, '状态 (0:使用中 1：过期)');
INSERT INTO `sys_dict_data` VALUES (304, 11, '月子卡', '11', 'house_product_status', '', 'info', 'N', '1', 'admin', '2019-07-02 11:28:43', '', NULL, '商品类型');
INSERT INTO `sys_dict_data` VALUES (305, 1, '启用', '1', 'house_house_account_set_status', '', 'primary', 'Y', '1', 'admin', '2019-07-03 14:35:48', '', NULL, '挖矿配置状态 1启用 2关闭');
INSERT INTO `sys_dict_data` VALUES (306, 2, '关闭', '2', 'house_house_account_set_status', '', 'warning', 'N', '1', 'admin', '2019-07-03 14:36:11', '', NULL, '挖矿配置状态 1启用 2关闭');
INSERT INTO `sys_dict_data` VALUES (307, 1, '是', '1', 'house_house_account_set_isCharm', '', 'primary', 'N', '1', 'admin', '2019-07-03 14:38:52', '', NULL, '关联智力 1是 0否');
INSERT INTO `sys_dict_data` VALUES (308, 2, '否', '0', 'house_house_account_set_isCharm', '', 'warning', 'N', '1', 'admin', '2019-07-03 14:39:05', '', NULL, '关联智力 1是 0否');
INSERT INTO `sys_dict_data` VALUES (309, 1, '抢狗成功概率范围最小值', '10', 'dog_robbing', '', '', 'Y', '1', 'admin', '2019-07-04 17:44:13', 'admin', '2019-07-04 17:46:13', '抢狗成功概率范围最小值');
INSERT INTO `sys_dict_data` VALUES (310, 2, '抢狗成功概率范围最大值', '80', 'dog_robbing', '', '', 'Y', '1', 'admin', '2019-07-04 17:44:29', 'admin', '2019-07-04 17:45:47', '抢狗成功概率范围最大值');
INSERT INTO `sys_dict_data` VALUES (311, 3, '抢狗成功概率基数', '10000', 'dog_robbing', '', '', 'Y', '1', 'admin', '2019-07-04 17:44:41', 'admin', '2019-07-10 14:39:45', '抢狗成功概率基数');
INSERT INTO `sys_dict_data` VALUES (314, 12, '改名卡', '12', 'house_product_status', '', 'info', 'N', '1', 'admin', '2019-07-14 14:50:42', '', NULL, '商品类型');
INSERT INTO `sys_dict_data` VALUES (315, 1, '认证成功', '1', 'personalCard_auth_stautsNew', '', 'success', 'Y', '1', 'admin', '2019-07-15 16:50:28', '', NULL, '1.认证成功 3.审核不成功4.黑名单');
INSERT INTO `sys_dict_data` VALUES (316, 2, '审核不成功', '3', 'personalCard_auth_stautsNew', '', 'info', 'N', '1', 'admin', '2019-07-15 16:51:04', '', NULL, '1.认证成功 3.审核不成功4.黑名单');
INSERT INTO `sys_dict_data` VALUES (317, 3, '黑名单', '4', 'personalCard_auth_stautsNew', '', 'warning', 'N', '1', 'admin', '2019-07-15 16:51:26', '', NULL, '1.认证成功 3.审核不成功4.黑名单');
INSERT INTO `sys_dict_data` VALUES (320, 2, '用户注册', '3', 'record_sms_log_typeNew', '', 'info', 'N', '1', 'admin', '2019-07-16 18:16:30', '', NULL, '1,发送短信通知转让方 2.送短信通知领养方 3.用户注册 4.修改支付密码 5.修改收付款二维码 6.行情检查）');
INSERT INTO `sys_dict_data` VALUES (321, 4, '修改支付密码', '4', 'record_sms_log_typeNew', '', 'info', 'N', '1', 'admin', '2019-07-16 18:17:09', 'admin', '2019-07-16 19:51:35', '1,发送短信通知转让方 2.送短信通知领养方 3.用户注册 4.修改支付密码 5.修改收付款二维码 6.行情检查）');
INSERT INTO `sys_dict_data` VALUES (322, 5, '修改收付款二维码', '5', 'record_sms_log_typeNew', '', 'info', 'N', '1', 'admin', '2019-07-16 18:17:43', 'admin', '2019-07-16 19:35:13', '1,发送短信通知转让方 2.送短信通知领养方 3.用户注册 4.修改支付密码 5.修改收付款二维码 6.行情检查）');
INSERT INTO `sys_dict_data` VALUES (323, 6, '行情检查', '6', 'record_sms_log_typeNew', '', 'info', 'N', '1', 'admin', '2019-07-16 18:18:09', '', NULL, '1,发送短信通知转让方 2.送短信通知领养方 3.用户注册 4.修改支付密码 5.修改收付款二维码 6.行情检查）');
INSERT INTO `sys_dict_data` VALUES (324, 1, '未审核', '1', 't_customer_type', '', 'info', 'N', '1', 'admin', '2019-07-19 14:59:32', 'admin', '2019-07-19 15:00:09', '1未审核，2客服已审核，3客服已审核，4拒绝');
INSERT INTO `sys_dict_data` VALUES (325, 2, '客服已审核', '2', 't_customer_type', '', 'success', 'N', '1', 'admin', '2019-07-19 14:59:45', '', NULL, '1未审核，2客服已审核，3客服已审核，4拒绝');
INSERT INTO `sys_dict_data` VALUES (326, 3, '客服已审核', '3', 't_customer_type', '', 'success', 'N', '1', 'admin', '2019-07-19 15:00:02', '', NULL, '1未审核，2客服已审核，3客服已审核，4拒绝');
INSERT INTO `sys_dict_data` VALUES (327, 4, '财务拒绝', '4', 't_customer_type', '', 'warning', 'N', '1', 'admin', '2019-07-19 15:00:26', 'admin', '2019-08-02 16:06:51', '1是未审核，2是客服已审核，3是财务已审核，4是财务拒绝，5是成功，6客服已拒绝');
INSERT INTO `sys_dict_data` VALUES (328, 3, '拒放已处理', '3', 'beta_processeda_order_stauts', '', 'info', 'N', '1', 'admin', '2019-07-21 23:44:13', 'admin', '2019-07-21 23:46:22', '拒绝放狗已处理');
INSERT INTO `sys_dict_data` VALUES (329, 4, '放狗已处理', '4', 'beta_processeda_order_stauts', '', 'info', 'N', '1', 'admin', '2019-07-21 23:46:42', '', NULL, '放狗已处理');
INSERT INTO `sys_dict_data` VALUES (330, 1, '是', '1', 'give_currency_status', '', 'info', 'N', '1', 'admin', '2019-07-22 19:20:49', '', NULL, '是否送币');
INSERT INTO `sys_dict_data` VALUES (331, 2, '否', '0', 'give_currency_status', '', 'success', 'N', '1', 'admin', '2019-07-22 19:21:11', 'admin', '2019-07-23 11:37:08', '是否送币');
INSERT INTO `sys_dict_data` VALUES (332, 1, '充值', '1', 'wallet_fund_account_type', '', 'info', 'N', '1', 'admin', '2019-07-23 22:22:10', '', NULL, '1-充值');
INSERT INTO `sys_dict_data` VALUES (333, 2, '提现', '2', 'wallet_fund_account_type', '', 'info', 'N', '1', 'admin', '2019-07-23 22:22:29', '', NULL, '2-提现');
INSERT INTO `sys_dict_data` VALUES (334, 3, '资金转法币', '3', 'wallet_fund_account_type', '', 'info', 'Y', '1', 'admin', '2019-07-23 22:23:18', 'admin', '2019-07-30 00:14:07', '3-划转：资金账户--&gt;法币钱包');
INSERT INTO `sys_dict_data` VALUES (335, 4, '法币转资金', '4', 'wallet_fund_account_type', '', 'info', 'N', '1', 'admin', '2019-07-23 22:23:49', 'admin', '2019-07-30 00:13:55', '4-划转：法币钱包--&gt;资金账户');
INSERT INTO `sys_dict_data` VALUES (336, 5, '资金转beta', '5', 'wallet_fund_account_type', '', 'info', 'Y', '1', 'admin', '2019-07-23 22:24:15', 'admin', '2019-07-30 00:13:35', '5-划转：资金账户--&gt;beta账户');
INSERT INTO `sys_dict_data` VALUES (337, 6, 'beta转资金', '6', 'wallet_fund_account_type', '', 'info', 'N', '1', 'admin', '2019-07-23 22:24:46', 'admin', '2019-07-30 00:13:20', '6-划转：beta账户--&gt;资金账户');
INSERT INTO `sys_dict_data` VALUES (338, 1, '正常', '1', 'currencyConfig_status', '', 'success', 'N', '1', 'admin', '2019-07-25 16:58:07', '', NULL, '状态 1：正常 2：禁用');
INSERT INTO `sys_dict_data` VALUES (339, 2, '禁用', '2', 'currencyConfig_status', '', 'warning', 'N', '1', 'admin', '2019-07-25 16:58:23', 'admin', '2019-07-25 16:58:27', '状态 1：正常 2：禁用');
INSERT INTO `sys_dict_data` VALUES (340, 1, '初始化', '0', 'withdraw_walletBill_transferFlag', '', 'default', 'N', '1', 'admin', '2019-08-01 10:36:42', '', NULL, '转移状态：0：初始化，1：待归集，2已转移，3不用处理');
INSERT INTO `sys_dict_data` VALUES (341, 2, '待归集', '1', 'withdraw_walletBill_transferFlag', '', 'info', 'N', '1', 'admin', '2019-08-01 10:37:03', '', NULL, '转移状态：0：初始化，1：待归集，2已转移，3不用处理');
INSERT INTO `sys_dict_data` VALUES (342, 3, '已转移', '2', 'withdraw_walletBill_transferFlag', '', 'success', 'N', '1', 'admin', '2019-08-01 10:37:19', '', NULL, '转移状态：0：初始化，1：待归集，2已转移，3不用处理');
INSERT INTO `sys_dict_data` VALUES (343, 5, '不用处理', '3', 'withdraw_walletBill_transferFlag', '', 'warning', 'N', '1', 'admin', '2019-08-01 10:37:38', 'admin', '2019-08-01 10:37:46', '转移状态：0：初始化，1：待归集，2已转移，3不用处理');
INSERT INTO `sys_dict_data` VALUES (344, 7, '找回密码', '7', 'record_sms_log_typeNew', '', 'info', 'Y', '1', 'admin', '2019-08-01 17:44:27', 'admin', '2019-08-01 17:45:01', '短信发送类型');
INSERT INTO `sys_dict_data` VALUES (345, 8, '法币交易出售', '8', 'record_sms_log_typeNew', '', 'info', 'Y', '1', 'admin', '2019-08-01 17:47:07', '', NULL, '短信发送类型');
INSERT INTO `sys_dict_data` VALUES (346, 9, '法币交易打款', '9', 'record_sms_log_typeNew', '', 'info', 'Y', '1', 'admin', '2019-08-01 17:53:58', '', NULL, '短信发送类型');
INSERT INTO `sys_dict_data` VALUES (347, 10, '法币交易完成', '10', 'record_sms_log_typeNew', '', 'info', 'Y', '1', 'admin', '2019-08-01 17:54:20', '', NULL, '短信发送类型');
INSERT INTO `sys_dict_data` VALUES (348, 1, '发送短信通知转让方', '1', 'record_sms_log_typeNew', '', 'info', 'Y', '1', 'admin', '2019-08-01 22:18:36', '', NULL, '短信发送类型');
INSERT INTO `sys_dict_data` VALUES (349, 2, '送短信通知领养方', '2', 'record_sms_log_typeNew', '', 'info', 'Y', '1', 'admin', '2019-08-01 22:19:00', '', NULL, '短信发送类型');
INSERT INTO `sys_dict_data` VALUES (350, 5, '成功', '5', 't_customer_type', '', 'success', 'N', '1', 'admin', '2019-08-02 16:07:11', '', NULL, '1是未审核，2是客服已审核，3是财务已审核，4是财务拒绝，5是成功，6客服已拒绝');
INSERT INTO `sys_dict_data` VALUES (351, 6, '客服已拒绝', '-1', 't_customer_type', '', 'info', 'N', '1', 'admin', '2019-08-02 16:07:23', 'admin', '2019-08-02 16:18:25', '-1客服已拒绝，1是未审核，2是客服已审核，3是财务已审核，4是财务拒绝，5是成功，');
INSERT INTO `sys_dict_data` VALUES (352, 1, '初始化', '0', 'inandout_orader_flag', '', 'primary', 'N', '1', 'admin', '2019-08-02 17:04:37', '', NULL, '0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理');
INSERT INTO `sys_dict_data` VALUES (353, 2, '未到账户', '1', 'inandout_orader_flag', '', 'info', 'N', '1', 'admin', '2019-08-02 17:05:21', '', NULL, '0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理');
INSERT INTO `sys_dict_data` VALUES (354, 3, '已到账户', '2', 'inandout_orader_flag', '', 'success', 'N', '1', 'admin', '2019-08-02 17:05:39', '', NULL, '0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理');
INSERT INTO `sys_dict_data` VALUES (355, 4, '不用处理', '3', 'inandout_orader_flag', '', 'warning', 'N', '1', 'admin', '2019-08-02 17:05:54', '', NULL, '0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理');
INSERT INTO `sys_dict_data` VALUES (356, 1, '无效', 'D', 'walletbiz_status', '', 'info', 'N', '1', 'admin', '2019-08-03 14:29:27', '', NULL, '是否有效：D无效E有效');
INSERT INTO `sys_dict_data` VALUES (357, 2, '有效', 'E', 'walletbiz_status', '', 'success', 'N', '1', 'admin', '2019-08-03 14:29:44', '', NULL, '是否有效：D无效E有效');
INSERT INTO `sys_dict_data` VALUES (358, 1, '提币地址', 'withdraw', 'walletbiz_type', '', 'warning', 'N', '1', 'admin', '2019-08-03 15:01:01', 'admin', '2019-08-05 17:19:35', '钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]');
INSERT INTO `sys_dict_data` VALUES (359, 2, '手续费地址', 'fee', 'walletbiz_type', '', 'info', 'N', '1', 'admin', '2019-08-03 15:01:27', '', NULL, '钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]');
INSERT INTO `sys_dict_data` VALUES (360, 3, '归集', 'collect', 'walletbiz_type', '', 'primary', 'N', '1', 'admin', '2019-08-03 15:09:10', '', NULL, '钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]');
INSERT INTO `sys_dict_data` VALUES (361, 11, '充值成功', '11', 'record_sms_log_typeNew', '', 'info', 'Y', '1', 'admin', '2019-08-03 16:54:42', 'admin', '2019-08-03 18:05:00', '短信发送类型');
INSERT INTO `sys_dict_data` VALUES (362, 12, '提币成功', '12', 'record_sms_log_typeNew', '', 'info', 'Y', '1', 'admin', '2019-08-03 16:59:24', 'admin', '2019-08-03 17:53:47', '短信发送成功');
INSERT INTO `sys_dict_data` VALUES (363, 1, '总入', '1', 't_currency_typeCount', '', 'success', 'N', '1', 'admin', '2019-08-05 15:13:32', '', NULL, '类型 1;总入 2总出');
INSERT INTO `sys_dict_data` VALUES (364, 2, '总出', '2', 't_currency_typeCount', '', 'warning', 'N', '1', 'admin', '2019-08-05 15:13:55', '', NULL, '类型 1;总入 2总出');
INSERT INTO `sys_dict_data` VALUES (365, 11, '完成冻结', '11', 'beta_order_stauts', '', 'warning', 'Y', '1', 'admin', '2019-08-07 00:37:58', 'admin', '2019-08-07 00:41:54', '');
INSERT INTO `sys_dict_data` VALUES (366, 12, '待转让冻结', '12', 'beta_order_stauts', '', 'warning', 'Y', '1', 'admin', '2019-08-07 00:44:23', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (367, 13, '待支付冻结', '13', 'beta_order_stauts', '', 'warning', 'Y', '1', 'admin', '2019-08-07 00:45:18', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (368, 14, '待放狗冻结', '14', 'beta_order_stauts', '', 'warning', 'Y', '1', 'admin', '2019-08-07 00:45:32', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (369, 15, '客诉冻结', '15', 'beta_order_stauts', '', 'warning', 'Y', '1', 'admin', '2019-08-07 00:45:46', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (370, 3, '未出售冻结', '10', 'beta_sell_Status', '', 'warning', 'Y', '1', 'admin', '2019-08-07 19:56:42', 'admin', '2019-08-07 19:57:07', '');
INSERT INTO `sys_dict_data` VALUES (371, 4, '已出售冻结', '11', 'beta_sell_Status', '', 'warning', 'Y', '1', 'admin', '2019-08-07 19:57:03', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (373, 1, '冻结额度', '1', 'beta_freeze_recordType', '', 'info', 'N', '1', 'admin', '2019-08-08 14:57:25', '', NULL, '冻结流水类型1=冻结额度2=可售额度');
INSERT INTO `sys_dict_data` VALUES (374, 2, '可售额度', '2', 'beta_freeze_recordType', '', 'primary', 'N', '1', 'admin', '2019-08-08 14:57:46', '', NULL, '冻结流水类型1=冻结额度2=可售额度');
INSERT INTO `sys_dict_data` VALUES (375, 2, '余额将要不足', '2', 'wallet_fee_type', '', 'primary', 'N', '1', 'admin', '2019-08-08 16:48:59', 'admin', '2019-08-08 17:01:51', '预警');
INSERT INTO `sys_dict_data` VALUES (376, 1, '正常', '1', 'wallet_fee_type', '', 'primary', 'N', '1', 'admin', '2019-08-08 16:49:41', 'admin', '2019-08-08 17:01:46', '正常');
INSERT INTO `sys_dict_data` VALUES (377, 1, '新用户', '0', 'personalCard_user_oldFlags', '', 'primary', 'N', '1', 'admin', '2019-08-08 21:20:56', '', NULL, '老用户标识0=新用户1=老用户');
INSERT INTO `sys_dict_data` VALUES (378, 1, '老用户', '1', 'personalCard_user_oldFlags', '', 'success', 'N', '1', 'admin', '2019-08-08 21:21:10', '', NULL, '老用户标识0=新用户1=老用户');
INSERT INTO `sys_dict_data` VALUES (379, 0, '执行正常', '0', 'sys_job_log_status', '', 'success', 'Y', '1', 'admin', '2019-08-09 15:20:54', 'admin', '2019-08-09 15:48:57', '状态（0正常 1失败）');
INSERT INTO `sys_dict_data` VALUES (380, 1, '执行失败', '1', 'sys_job_log_status', '', 'danger', 'Y', '1', 'admin', '2019-08-09 15:21:15', 'admin', '2019-08-09 15:48:43', '状态（0正常 1失败）');
INSERT INTO `sys_dict_data` VALUES (381, 8, 'BTC', '11', 'beta_productType', '', 'primary', 'Y', '1', 'admin', '2019-08-13 17:42:36', 'admin', '2019-08-13 17:48:36', '钱包类型');
INSERT INTO `sys_dict_data` VALUES (383, 21, '审核中', '21', 'beta_order_stauts', '', 'success', 'N', '1', 'admin', '2019-08-14 11:19:01', '', NULL, '订单状态');
INSERT INTO `sys_dict_data` VALUES (384, 22, '审核失败', '22', 'beta_order_stauts', '', 'info', 'N', '1', 'admin', '2019-08-14 11:19:27', '', NULL, '订单状态');
INSERT INTO `sys_dict_data` VALUES (385, 5, '审核失败', '22', 'beta_sell_Status', '', 'primary', 'N', '1', 'admin', '2019-08-14 18:02:24', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (386, 1, '普通用户', '0', 'important_flag', '', 'info', 'N', '1', 'admin', '2019-08-15 16:22:09', '', NULL, '重点用户标识');
INSERT INTO `sys_dict_data` VALUES (387, 2, '重点用户', '1', 'important_flag', '', 'warning', 'N', '1', 'admin', '2019-08-15 16:22:32', '', NULL, '重点用户标识');
INSERT INTO `sys_dict_data` VALUES (388, 8, '可出售', '7', 'beta_productType', '', 'primary', 'N', '1', 'admin', '2019-08-16 15:56:28', '', NULL, '钱包类型');
INSERT INTO `sys_dict_data` VALUES (389, 31, '完成锁仓', '31', 'beta_order_stauts', '', 'info', 'Y', '1', 'admin', '2019-08-16 23:33:15', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (390, 32, '待转让锁仓', '32', 'beta_order_stauts', '', 'info', 'Y', '1', 'admin', '2019-08-16 23:33:34', '', NULL, '');
INSERT INTO `sys_dict_data` VALUES (391, 30, '锁仓中', '30', 'beta_sell_Status', '', 'info', 'Y', '1', 'admin', '2019-08-17 17:47:21', '', NULL, '');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态（1正常 0停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 190 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-08-30 02:59:40', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-06-05 22:02:16', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '1', 'admin', '2018-03-16 11:33:00', 'ourblue', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '1', 'admin', '2018-03-16 11:33:00', 'ourblue', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '系统是否', 'sys_yes_no', '1', 'admin', '2018-03-16 11:33:00', 'ourblue', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (6, '通知类型', 'sys_notice_type', '1', 'admin', '2018-03-16 11:33:00', 'ourblue', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知状态', 'sys_notice_status', '1', 'admin', '2018-03-16 11:33:00', 'ourblue', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (8, '操作类型', 'sys_oper_type', '1', 'admin', '2018-03-16 11:33:00', 'ourblue', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (9, '系统状态', 'sys_common_status', '1', 'admin', '2018-03-16 11:33:00', 'ourblue', '2018-03-16 11:33:00', '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (101, '方向（涨/跌）', 'sys_contract_direction', '1', 'admin', '2018-11-22 10:28:26', '', NULL, '方向（涨/跌）：1-买跌，2-买涨');
INSERT INTO `sys_dict_type` VALUES (102, '审核状态', 'audit_authentication_status', '1', 'admin', '2018-11-23 11:02:14', 'admin', '2018-11-23 11:02:41', '0未审核，1已审核，2待审核');
INSERT INTO `sys_dict_type` VALUES (103, '充值类型', 'audit_recharge_type', '1', 'admin', '2018-11-23 17:48:01', 'admin', '2019-01-09 15:41:52', '类型：1用户充值，2人工充值，3人工充值（体验金），4奖励金转入');
INSERT INTO `sys_dict_type` VALUES (104, '充值状态', 'audit_recharge_status', '1', 'admin', '2018-11-23 17:49:58', 'admin', '2018-12-12 10:31:42', '状态：1待审核，2未付款，3审核通过，4审核不通过，5已关闭');
INSERT INTO `sys_dict_type` VALUES (105, '提现类型', 'audit_withdraw_type', '1', 'admin', '2018-11-26 11:20:15', '', NULL, '类型：1用户提现，2手动提现');
INSERT INTO `sys_dict_type` VALUES (106, '提现状态', 'audit_withdraw_status', '1', 'admin', '2018-11-26 11:21:20', 'admin', '2018-11-27 11:03:56', '状态：2待审核，1审核通过，0审核不通过');
INSERT INTO `sys_dict_type` VALUES (107, '身份认证', 'user_authentication', '1', 'admin', '2018-11-28 17:08:28', 'admin', '2018-12-07 15:41:44', '证件审核：0未提交，1通过，2审核中，3审核拒绝');
INSERT INTO `sys_dict_type` VALUES (108, '代理', 'user_agnt', '1', 'admin', '2018-11-28 17:09:58', 'admin', '2018-11-28 17:15:47', '代理类型：1一级代理，2二级代理，3三级代理，0非代理');
INSERT INTO `sys_dict_type` VALUES (109, '盈亏', 'contract_profitAndLoss', '1', 'admin', '2018-12-05 11:20:54', '', NULL, '盈亏 1盈 2亏');
INSERT INTO `sys_dict_type` VALUES (110, '平仓类型', 'contract_status', '1', 'admin', '2018-12-05 11:22:09', '', NULL, '2-平仓 3爆仓');
INSERT INTO `sys_dict_type` VALUES (111, '文章状态', 'article_status', '1', 'admin', '2018-12-06 11:08:58', '', NULL, '1=未开始 2=进行中 3=已过期');
INSERT INTO `sys_dict_type` VALUES (112, '通用状态', 'common_status', '1', 'admin', '2018-12-07 20:37:28', '', NULL, '0禁用/不正常，1正常');
INSERT INTO `sys_dict_type` VALUES (113, '代理级别', 'user_agnt2', '1', 'admin', '2018-12-11 14:52:36', '', NULL, '代理级别：1一级代理，2二级代理，3三级代理');
INSERT INTO `sys_dict_type` VALUES (114, '订单类型', 'contract_token_order', '1', 'admin', '2018-12-18 11:00:14', '', NULL, '1=正常订单，2=体验金订单');
INSERT INTO `sys_dict_type` VALUES (115, '充值币种', 'recharge_currency', '1', 'admin', '2018-12-18 11:27:53', '', NULL, '充值币种：1USDT，2USDT（体验金）');
INSERT INTO `sys_dict_type` VALUES (116, 'app类型', 'app_type', '1', 'admin', '2018-12-21 17:12:57', '', NULL, 'app类型:1、IOS，2、Android，3、H5');
INSERT INTO `sys_dict_type` VALUES (118, 'app是否强制升级', 'app_status', '1', 'admin', '2018-12-21 18:33:39', '', NULL, '0、否，1、强制升级');
INSERT INTO `sys_dict_type` VALUES (119, '内部账号', 'user_internal_account', '1', 'admin', '2019-01-07 16:38:40', '', NULL, '1、否，2、是');
INSERT INTO `sys_dict_type` VALUES (120, '订单平台区分', 'contract_client_type', '1', 'admin', '2019-01-10 17:27:19', '', NULL, '交易订单号增加平台区分（M、Android、iOS）');
INSERT INTO `sys_dict_type` VALUES (121, '订单状态', 'orderUser_orderStauts', '1', 'admin', '2019-03-19 14:25:46', '', NULL, '（0=已取消，1=已完成，2=待支付，3=待放行，4=拒绝放行）');
INSERT INTO `sys_dict_type` VALUES (122, '交易对类型', 'orderUser_productType', '1', 'admin', '2019-03-19 14:37:15', '', NULL, '（1=CNY/USDT，2=CNY/BI）');
INSERT INTO `sys_dict_type` VALUES (123, '购买出售订单类型', 'orderUser_type', '1', 'admin', '2019-03-19 14:59:17', '', NULL, '订单类型（1=出售订单，2=购买订单）');
INSERT INTO `sys_dict_type` VALUES (124, '取消订单状态', 'orderUser_orderStautsType', '1', 'admin', '2019-03-19 15:45:25', '', NULL, '订单状态（0=未知，1=客户取消，2=客服取消，3=超时取消）');
INSERT INTO `sys_dict_type` VALUES (125, '委托单是否认证', 'rderEntrust_isAttestation', '1', 'admin', '2019-03-20 14:16:03', 'admin', '2019-03-20 14:35:11', '是否认证');
INSERT INTO `sys_dict_type` VALUES (126, '认证状态', 'personalCard_auth_Stauts', '1', 'admin', '2019-03-26 12:14:07', '', NULL, '1=认证成功，2=待审核，3=审核不成功,4=黑名单');
INSERT INTO `sys_dict_type` VALUES (127, '合伙人类型', 'personalPartner_type', '1', 'admin', '2019-04-09 14:32:04', 'admin', '2019-04-09 14:35:21', '（1=梦想合伙人，2=节点合伙人）');
INSERT INTO `sys_dict_type` VALUES (128, '是否溢价', 'premium_Stauts', '1', 'admin', '2019-04-10 15:56:15', '', NULL, '1=未溢价，2=已溢价');
INSERT INTO `sys_dict_type` VALUES (129, '是否激活', 'beta_activate', '1', 'admin', '2019-04-10 16:01:00', '', NULL, '是否激活（0=未激活，1=已激活）----何为激活，意思就是此狗已绑定创世用户，可以预约出售了');
INSERT INTO `sys_dict_type` VALUES (135, '订单状态', 'beta_order_stauts', '1', 'admin', '2019-04-23 02:27:00', '', NULL, '订单状态（-1=隐藏，0=取消，1=完成，2=待转让，3=待支付，4=待放狗，5=客诉）');
INSERT INTO `sys_dict_type` VALUES (136, '预约状态', 'beta_reservStauts', '1', 'admin', '2019-04-23 03:50:53', '', NULL, '预约状态（0=等待，1=成功，2=失败）');
INSERT INTO `sys_dict_type` VALUES (137, '订单创建类型', 'beta_platform_and_user', '1', 'admin', '2019-04-23 21:56:25', 'admin', '2019-06-03 23:03:35', '平台创建还是用户创建（0.未知 1.平台 2.用户团队收益3.用户推广收益4.订单拆分）');
INSERT INTO `sys_dict_type` VALUES (138, '处理订单状态', 'beta_processeda_order_stauts', '1', 'admin', '2019-04-24 20:42:14', '', NULL, '处理订单状态（1.放狗 2拒绝放狗）');
INSERT INTO `sys_dict_type` VALUES (139, '出售状态', 'beta_sell_Status', '1', 'admin', '2019-05-05 20:41:26', '', NULL, '出售状态（0=未出售，1=已出售）');
INSERT INTO `sys_dict_type` VALUES (141, '当前身份类型', 'personalCard_currentIdentity', '1', 'admin', '2019-05-13 22:39:47', 'admin', '2019-05-16 12:53:56', '1=普通用户，2=推广大使，3=服务商，4=合伙人，5=钻石合伙人，6=皇冠合伙人');
INSERT INTO `sys_dict_type` VALUES (142, '钱包类型', 'beta_productType', '1', 'admin', '2019-05-16 12:54:44', 'admin', '2019-05-16 12:55:06', '钱包类型（0=USDT,1=BITT，2=时间链，3=β通证，4=推广收益,5累计收益,6团队收益）');
INSERT INTO `sys_dict_type` VALUES (143, '短信类型', 'record_sms_log_type', '1', 'admin', '2019-05-16 18:38:59', 'admin', '2019-05-17 10:10:22', '短信发送类型（1转让人 2领养人）');
INSERT INTO `sys_dict_type` VALUES (144, '区域码是否启用', 'country_code_use', '1', 'admin', '2019-05-20 10:41:30', '', NULL, '是否启用(1=是2=否)');
INSERT INTO `sys_dict_type` VALUES (145, '是否可以充币/提币', 'coin_type', '1', 'admin', '2019-05-20 18:55:41', 'admin', '2019-06-03 11:52:10', '是否可以充币(1=可以2=不可以)');
INSERT INTO `sys_dict_type` VALUES (147, '覆盖的类型（修改的类型）', 'beta_coverage_Type', '1', 'admin', '2019-06-08 18:16:22', '', NULL, '覆盖的类型 1收付款二维码2.付款图片');
INSERT INTO `sys_dict_type` VALUES (148, '导入状态', 'import_stauts', '1', 'admin', '2019-06-11 09:32:36', 'admin', '2019-06-11 10:59:34', '导入状态（1=成功，2=失败）');
INSERT INTO `sys_dict_type` VALUES (151, '母狗状态', 'beta_betaFemina_status', '1', 'admin', '2019-06-13 11:54:49', 'admin', '2019-06-20 16:07:00', '母狗状态(-1=不孕不育期,1=正常期，2=怀孕期，3=月子期)');
INSERT INTO `sys_dict_type` VALUES (152, '母狗商品类别', 'house_product_status', '1', 'admin', '2019-06-14 14:10:29', 'admin', '2019-07-02 11:27:38', '商品类别');
INSERT INTO `sys_dict_type` VALUES (153, '商品出售状态', 'beta_HouseProduct_status', '1', 'admin', '2019-06-14 14:19:25', 'admin', '2019-06-14 14:24:36', '状态(0:未公开 1:出售 2:停售 3:不显示)');
INSERT INTO `sys_dict_type` VALUES (154, '商品适用', 'beta_HouseProduct_beSuitable', '1', 'admin', '2019-06-14 14:48:06', '', NULL, '适用 1.母狗 2.公狗');
INSERT INTO `sys_dict_type` VALUES (156, '小狗状态', 'beta_pup_state', '1', 'admin', '2019-06-18 17:32:51', 'admin', '2019-06-20 20:58:42', '状态 (-1:未出生 0:小狗 1:大狗 2:已转让)');
INSERT INTO `sys_dict_type` VALUES (157, '小狗性别', 'beta_pup_gender', '1', 'admin', '2019-06-18 20:52:59', '', NULL, '性别 (0:公狗 1:母狗)');
INSERT INTO `sys_dict_type` VALUES (158, '商品展示位置', 'house_product_showPosition', '1', 'admin', '2019-06-19 17:28:41', 'admin', '2019-06-22 18:26:51', '商品展示位置 0 衣服1 宅基地 2 狗舍 3 道具');
INSERT INTO `sys_dict_type` VALUES (159, '消耗类别', 'house_product_expendType', '1', 'admin', '2019-06-19 17:30:21', '', NULL, '消耗类别1次性消耗，2永久商品');
INSERT INTO `sys_dict_type` VALUES (160, '狗窝动态阅读类型', 'beta_house_history', '1', 'admin', '2019-06-20 22:42:09', '', NULL, '状态 (0:未读 1：已读)');
INSERT INTO `sys_dict_type` VALUES (161, '挖矿收取状态', 'beta_house_status', '1', 'admin', '2019-06-21 09:58:34', '', NULL, '状态 (0:未收取 1：已收取)');
INSERT INTO `sys_dict_type` VALUES (163, '狗窝商品时间', 'beta_product_time', '1', 'admin', '2019-06-21 19:22:53', 'admin', '2019-06-21 19:23:03', '狗窝商品时间(1=时2=分)');
INSERT INTO `sys_dict_type` VALUES (164, '饱和度告警', 'saturated_risk', '1', 'admin', '2019-06-22 18:10:07', '', NULL, '0=正常1=警告');
INSERT INTO `sys_dict_type` VALUES (165, '价格范围报警', 'price_risk', '1', 'admin', '2019-06-22 18:10:39', '', NULL, '0=正常1=警告');
INSERT INTO `sys_dict_type` VALUES (167, '小狗商品类别', 'house_puppyProduct_status', '1', 'admin', '2019-06-26 17:09:19', 'admin', '2019-06-27 10:46:45', '小狗商品类别');
INSERT INTO `sys_dict_type` VALUES (168, '背包物品使用状态', 'beta_house_backpack_use_status', '1', 'admin', '2019-06-28 17:02:26', 'admin', '2019-06-28 17:09:59', '背包物品状态 (0:使用中 1：过期)');
INSERT INTO `sys_dict_type` VALUES (169, '挖矿状态配置', 'house_house_account_set_status', '1', 'admin', '2019-07-03 14:35:29', '', NULL, '挖矿配置状态 1启用 2关闭');
INSERT INTO `sys_dict_type` VALUES (170, '挖矿配置关联智力', 'house_house_account_set_isCharm', '1', 'admin', '2019-07-03 14:38:17', '', NULL, '挖矿配置关联智力 1是 0否');
INSERT INTO `sys_dict_type` VALUES (171, '抢狗概率', 'dog_robbing', '1', 'admin', '2019-07-04 17:43:23', '', NULL, '抢狗概率相关');
INSERT INTO `sys_dict_type` VALUES (173, '认证状态新', 'personalCard_auth_stautsNew', '1', 'admin', '2019-07-15 16:49:45', '', NULL, '1.认证成功 3.审核不成功4.黑名单');
INSERT INTO `sys_dict_type` VALUES (174, '短信发送类型（新）', 'record_sms_log_typeNew', '1', 'admin', '2019-07-16 18:15:15', 'admin', '2019-07-18 15:17:05', '短信发送类型（1,发送短信通知转让方 2.送短信通知领养方 3.用户注册 4.修改支付密码 5.修改收付款二维码 6.行情检查7.找回密码）');
INSERT INTO `sys_dict_type` VALUES (175, '审核状态', 't_customer_type', '1', 'admin', '2019-07-19 14:58:56', 'admin', '2019-07-19 14:59:07', '1未审核，2客服已审核，3客服已审核，4拒绝');
INSERT INTO `sys_dict_type` VALUES (176, '是否送币', 'give_currency_status', '1', 'admin', '2019-07-22 19:20:10', '', NULL, '是否送币 1是 0否');
INSERT INTO `sys_dict_type` VALUES (177, '成交类型', 'wallet_fund_account_type', '1', 'admin', '2019-07-23 22:21:45', '', NULL, '成交类型');
INSERT INTO `sys_dict_type` VALUES (178, '币种配置状态', 'currencyConfig_status', '1', 'admin', '2019-07-25 16:57:50', '', NULL, '币种配置状态');
INSERT INTO `sys_dict_type` VALUES (179, '归集状态(转移状态）', 'withdraw_walletBill_transferFlag', '1', 'admin', '2019-08-01 10:36:17', '', NULL, '转移状态：0：初始化，1：待归集，2已转移，3不用处理');
INSERT INTO `sys_dict_type` VALUES (180, '充提订单状态', 'inandout_orader_flag', '1', 'admin', '2019-08-02 17:04:03', '', NULL, '0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理');
INSERT INTO `sys_dict_type` VALUES (181, '钱包状态', 'walletbiz_status', '1', 'admin', '2019-08-03 14:28:46', 'admin', '2019-08-03 14:57:29', '是否有效：D无效E有效');
INSERT INTO `sys_dict_type` VALUES (182, '钱包类型', 'walletbiz_type', '1', 'admin', '2019-08-03 14:56:56', '', NULL, '钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]');
INSERT INTO `sys_dict_type` VALUES (183, '币种类型', 't_currency_typeCount', '1', 'admin', '2019-08-05 15:12:58', '', NULL, '类型 1;总入 2总出');
INSERT INTO `sys_dict_type` VALUES (184, '冻结的流水类型', 'beta_freeze_recordType', '1', 'admin', '2019-08-08 14:56:50', '', NULL, '冻结流水类型1=冻结额度2=可售额度');
INSERT INTO `sys_dict_type` VALUES (185, '钱包手续费预警类型', 'wallet_fee_type', '1', 'admin', '2019-08-08 16:41:26', '', NULL, '1是正常，2是余额将要不足');
INSERT INTO `sys_dict_type` VALUES (186, '老用户标识', 'personalCard_user_oldFlags', '1', 'admin', '2019-08-08 21:20:18', '', NULL, '老用户标识0=新用户1=老用户');
INSERT INTO `sys_dict_type` VALUES (187, '定时任务执行日志状态', 'sys_job_log_status', '1', 'admin', '2019-08-09 15:19:54', 'admin', '2019-08-09 15:49:32', '状态（0执行正常 1执行失败）');
INSERT INTO `sys_dict_type` VALUES (189, '重点用户标识', 'important_flag', '1', 'admin', '2019-08-15 16:21:12', '', NULL, '重点用户标识0=普通用户1=重点用户');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务组名',
  `method_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '任务方法',
  `method_params` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法参数',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE,
  UNIQUE INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (100, 'betaTask', '委托单定时处理', 'entrustStauts', '', '0 0/1 * * * ?', '2', '1', '0', 'admin', '2019-06-12 15:58:13', 'admin', '2019-06-14 23:53:49', '委托单定时处理');
INSERT INTO `sys_job` VALUES (101, 'betaTask', '法币订单定时处理', 'orderStauts', '', '0 0/1 * * * ?', '1', '1', '0', 'admin', '2019-06-12 15:59:11', 'admin', '2019-06-14 23:53:46', '法币订单定时处理');
INSERT INTO `sys_job` VALUES (102, 'betaTask', '贝塔钱包补全系统', 'betaAccountCompletion', '', '0 0/5 * * * ?', '1', '1', '0', 'admin', '2019-06-12 16:00:06', 'admin', '2019-06-14 23:53:42', '贝塔钱包补全系统');
INSERT INTO `sys_job` VALUES (103, 'betaTask', '贝塔狗收益完成定时放狗', 'betaMaleTransfer', '', '0 0 0 1/1 * ?', '1', '1', '0', 'admin', '2019-06-12 16:02:03', 'admin', '2019-08-18 01:35:00', '贝塔狗收益完成定时放狗');
INSERT INTO `sys_job` VALUES (104, 'betaTask', '贝塔狗订单超时两小时待转让的订单进入客诉状态', 'betaMalePass', '', '0 0/1 * * * ?', '1', '1', '0', 'admin', '2019-06-12 16:03:57', 'admin', '2019-08-13 17:31:11', '贝塔狗订单超时两小时待转让的订单进入客诉状态');
INSERT INTO `sys_job` VALUES (105, 'betaTask', '贝塔狗订单超时两小时未支付的订单进入客诉状态', 'betaMaleCancel', '', '0 0/1 * * * ?', '1', '1', '0', 'admin', '2019-06-12 16:04:56', 'admin', '2019-08-13 18:55:59', '贝塔狗订单超时两小时未支付的订单进入客诉状态');
INSERT INTO `sys_job` VALUES (106, 'betaTask', '推广收益结算', 'shareProfit', '', '0 0 0/1 * * ?', '1', '1', '0', 'admin', '2019-06-12 16:05:57', 'admin', '2019-06-14 23:53:28', '推广收益结算，给用户添加累计收益，给直推人获得推广收益');
INSERT INTO `sys_job` VALUES (107, 'betaTask', '贝塔狗预约资金退还', 'betaReservationRefund', NULL, '0 0 0 1/1 * ?', '1', '1', '0', 'admin', '2019-06-12 16:06:41', '', '2019-06-14 23:53:24', '退还昨天的预约资金给用户');
INSERT INTO `sys_job` VALUES (108, 'betaTask', '团队收益结算', 'generalizeProfit', '', '0 0 0/1 * * ?', '1', '1', '0', 'admin', '2019-06-12 16:07:24', 'admin', '2019-06-14 23:54:58', '团队收益结算给用户');
INSERT INTO `sys_job` VALUES (109, 'betaTask', '用户推广身份升级', 'userUpgradePush', '', '0 0 0/2 * * ?', '1', '1', '0', 'admin', '2019-06-12 16:08:24', 'admin', '2019-06-14 23:53:17', '给推广条件达成的用户升级身份');
INSERT INTO `sys_job` VALUES (111, 'houseTask', '母狗繁殖', 'breed', '', '0 0 0/1 * * ?', '2', '1', '0', 'admin', '2019-06-13 23:24:37', 'admin', '2019-06-18 17:02:24', '');
INSERT INTO `sys_job` VALUES (112, 'houseTask', '小狗成长', 'babyGrowUp', '', '0 0 0 * * ?', '3', '1', '0', 'admin', '2019-06-16 15:54:13', 'admin', '2019-06-18 11:47:56', '');
INSERT INTO `sys_job` VALUES (113, 'houseTask', '挖矿', 'mining', '', '0 0 0/1 * * ?', '3', '1', '0', 'admin', '2019-06-16 15:55:51', 'admin', '2019-06-18 11:46:54', '');
INSERT INTO `sys_job` VALUES (114, 'houseTask', '商品过期处理', 'productExpire', NULL, '0 */1 * * * ?', '1', '1', '0', 'admin', '2019-06-28 01:05:53', '', '2019-06-28 15:02:11', '');
INSERT INTO `sys_job` VALUES (115, 'betaTask', '缓存用户抢公狗概率', 'cacheUserMaleDogTotalWorth', '', '0 25,55 10,12,14,15,17,18,19,20 * * ? *', '1', '1', '1', 'admin', '2019-07-03 20:10:13', 'admin', '2019-07-29 14:23:25', '缓存用户抢公狗概率');
INSERT INTO `sys_job` VALUES (116, 'houseTask', '返回小狗吃剩余的狗粮', 'rerunSurplusFood', '', '0 0 0 * * ?', '1', '1', '0', 'admin', '2019-07-03 22:03:33', 'admin', '2019-07-12 01:48:51', '');
INSERT INTO `sys_job` VALUES (117, 'betaTask', '统计每天的注册人数，实名人数', 'statisticsNumber', '', '0 0 0 * * ?', '2', '1', '0', 'admin', '2019-07-08 18:11:17', 'admin', '2019-07-26 17:19:06', '收集用户一天注册实名数');
INSERT INTO `sys_job` VALUES (118, 'betaTask', '统计每天抢狗卖狗数', 'countDayDog', '', '0 0 0 1/1 * ?', '2', '1', '0', 'admin', '2019-07-10 11:35:24', 'admin', '2019-07-12 01:48:44', '统计每天抢狗卖狗数每天首次抢狗数');
INSERT INTO `sys_job` VALUES (119, 'betaTask', '定时统计剩余公狗数量', 'betaCountRemain', '0', '0/30 0/1 10-20 * * ?', '1', '1', '1', 'admin', '2019-07-28 21:43:25', 'admin', '2019-08-14 19:52:22', '每半分钟统计剩余公狗数量');
INSERT INTO `sys_job` VALUES (120, 'betaTask', '查询用户是否有重复的订单', 'selectDistinctOrder', NULL, '0 0/5 10-20 * * ?', '1', '1', '0', 'admin', '2019-07-30 20:58:08', '', '2019-07-30 20:58:29', '查询用户是否有两只狗');
INSERT INTO `sys_job` VALUES (121, 'betaTask', '法币钱包补全系统', 'personalAccountCompletion', '', '0 0/5 * * * ?', '1', '1', '0', 'admin', '2019-07-31 19:17:40', 'admin', '2019-07-31 22:41:21', '法币钱包补全系统');
INSERT INTO `sys_job` VALUES (122, 'betaTask', '每天统计点击人数和抢狗人数', 'statisticReserve', NULL, '0 0 0 1/1 * ?', '1', '1', '0', 'admin', '2019-08-01 19:06:47', '', '2019-08-02 16:17:00', '');
INSERT INTO `sys_job` VALUES (123, 'betaTask', '处理发送失败的短信', 'updateSendSmsStatus', '', '0 */5 * * * ?', '1', '1', '0', 'admin', '2019-08-01 21:59:34', 'admin', '2019-08-03 02:05:17', '处理发送失败的短信');
INSERT INTO `sys_job` VALUES (125, 'houseTask', '狗窝人数统计', 'statisticsHouseNumber', NULL, '0 0 1 * * ?', '1', '1', '0', 'admin', '2019-08-07 16:49:42', '', '2019-08-07 16:49:46', '狗窝人数统计');
INSERT INTO `sys_job` VALUES (126, 'betaTask', '自动解除冻结订单', 'priorityAutoMeetSell', '', '0 0/5 * * * ?', '1', '1', '1', 'admin', '2019-08-08 16:45:16', 'admin', '2019-08-16 16:43:39', '');
INSERT INTO `sys_job` VALUES (127, 'betaTask', '更新老用户冻结金额', 'statisticsOldUserFreezeAccount', '', '0 0 1 * * ?', '1', '1', '0', 'admin', '2019-08-09 21:51:50', 'admin', '2019-08-10 00:55:08', '更新老用户冻结金额');
INSERT INTO `sys_job` VALUES (128, 'betaTask', '给上级增加可售额度', 'addParentSellBalance', NULL, '0 0 1 * * ?', '1', '1', '1', 'admin', '2019-08-11 01:23:57', '', NULL, '');
INSERT INTO `sys_job` VALUES (129, 'betaTask', '定时统计冻结金额', 'statisticsFreezeAccountStat', '', '0 0 1 * * ?', '1', '1', '0', 'admin', '2019-08-12 10:34:52', 'admin', '2019-08-14 01:01:01', '');
INSERT INTO `sys_job` VALUES (130, 'betaTask', '提取用户的人群画像数据', 'extractUserPortraitData', NULL, '0 0 4 * * ?', '1', '1', '1', 'admin', '2019-08-12 19:41:59', '', '2019-08-13 22:41:22', '提取用户的人群画像数据');
INSERT INTO `sys_job` VALUES (131, 'betaTask', '标记今天抢狗的老用户为重点用户', 'focusOnOldUsers', '', '0 */1 * * * ?', '1', '1', '0', 'admin', '2019-08-15 15:39:26', 'admin', '2019-08-16 23:19:45', '标记今天抢狗的老用户为重点用户');
INSERT INTO `sys_job` VALUES (132, 'betaTask', '锁仓指定套餐的用户', 'lockComboUser', '11,10,7,9', '0 0 0/1 * * ?', '1', '1', '1', 'admin', '2019-08-16 20:02:50', 'admin', '2019-08-17 01:54:33', '');
INSERT INTO `sys_job` VALUES (133, 'betaTask', '更新新用户的公狗优先级', 'newUserMalePriority', '', '0 0/5 * * * ?', '1', '1', '1', 'admin', '2019-08-16 20:08:58', 'admin', '2019-08-16 22:10:52', '');
INSERT INTO `sys_job` VALUES (134, 'betaTask', '初始化系统设置数量', 'clearBetaReserve', '100', '0 0 0 1/1 * ?', '1', '1', '1', 'admin', '2019-08-21 15:47:16', 'admin', '2019-08-21 19:58:45', '');
INSERT INTO `sys_job` VALUES (135, 'betaTask', '更新系统剩余公狗数量', 'betaRemainSystemSub', '200', '0/10 * * * * ?', '1', '1', '1', 'admin', '2019-08-21 21:33:23', 'admin', '2019-08-26 00:04:34', '');
INSERT INTO `sys_job` VALUES (136, 'betaTask', '用户活跃度减一', 'reduceTv', '', '0 0 1 * * ?', '1', '1', '0', 'admin', '2019-08-26 00:04:08', 'admin', '2019-08-26 01:50:35', '用户活跃度减一');
INSERT INTO `sys_job` VALUES (137, 'betaTask', '给推荐人增加优先权', 'firstDogAddParentFirst', '', '0 30 23 * * ?', '1', '1', '1', 'admin', '2019-08-26 01:56:43', 'admin', '2019-08-26 02:02:55', '');
INSERT INTO `sys_job` VALUES (138, 'betaTask', '清除使用优先权', 'clearFirst', '', '0 30 23 * * ?', '1', '1', '1', 'admin', '2019-08-26 01:59:29', 'admin', '2019-08-26 02:02:51', '');
INSERT INTO `sys_job` VALUES (139, 'betaTask', '定时刷新预约数据', 'refreshBetaReservation', NULL, '0 0 0 1/1 * ?', '1', '1', '0', 'admin', '2019-08-26 13:30:27', '', '2019-08-26 13:30:34', '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `method_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务方法',
  `method_params` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法参数',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 524121 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '登录状态（1成功 0失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (1, 'a', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-27 15:12:03');
INSERT INTO `sys_logininfor` VALUES (2, 'a', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-27 15:12:11');
INSERT INTO `sys_logininfor` VALUES (3, 'a', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '用户不存在/密码错误', '2019-08-27 15:13:44');
INSERT INTO `sys_logininfor` VALUES (4, 'a', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '用户不存在/密码错误', '2019-08-27 15:14:13');
INSERT INTO `sys_logininfor` VALUES (5, 'a', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-27 15:14:21');
INSERT INTO `sys_logininfor` VALUES (6, 'a', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-27 15:14:31');
INSERT INTO `sys_logininfor` VALUES (7, 'a', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '用户不存在/密码错误', '2019-08-27 15:14:46');
INSERT INTO `sys_logininfor` VALUES (8, 'asdfljl', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-27 15:14:55');
INSERT INTO `sys_logininfor` VALUES (9, 'asdfljl', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '用户不存在/密码错误', '2019-08-27 15:15:23');
INSERT INTO `sys_logininfor` VALUES (10, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-27 15:15:34');
INSERT INTO `sys_logininfor` VALUES (11, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-27 15:17:11');
INSERT INTO `sys_logininfor` VALUES (12, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '密码输入错误15次，帐户锁定10分钟', '2019-08-27 15:17:38');
INSERT INTO `sys_logininfor` VALUES (13, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '密码输入错误15次，帐户锁定10分钟', '2019-08-27 15:17:54');
INSERT INTO `sys_logininfor` VALUES (14, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-27 15:18:06');
INSERT INTO `sys_logininfor` VALUES (15, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-27 15:19:56');
INSERT INTO `sys_logininfor` VALUES (16, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '密码输入错误15次，帐户锁定10分钟', '2019-08-27 15:21:01');
INSERT INTO `sys_logininfor` VALUES (17, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '密码输入错误15次，帐户锁定10分钟', '2019-08-27 15:21:08');
INSERT INTO `sys_logininfor` VALUES (18, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '密码输入错误15次，帐户锁定10分钟', '2019-08-27 15:22:07');
INSERT INTO `sys_logininfor` VALUES (19, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-27 15:22:22');
INSERT INTO `sys_logininfor` VALUES (20, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '密码输入错误15次，帐户锁定10分钟', '2019-08-27 15:22:29');
INSERT INTO `sys_logininfor` VALUES (21, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-27 15:22:34');
INSERT INTO `sys_logininfor` VALUES (22, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-27 15:38:18');
INSERT INTO `sys_logininfor` VALUES (23, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-27 15:41:45');
INSERT INTO `sys_logininfor` VALUES (24, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-27 16:16:53');
INSERT INTO `sys_logininfor` VALUES (25, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-27 16:16:56');
INSERT INTO `sys_logininfor` VALUES (26, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-27 16:37:34');
INSERT INTO `sys_logininfor` VALUES (27, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-28 00:13:36');
INSERT INTO `sys_logininfor` VALUES (28, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-28 00:13:50');
INSERT INTO `sys_logininfor` VALUES (29, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '密码输入错误15次，帐户锁定10分钟', '2019-08-28 00:13:53');
INSERT INTO `sys_logininfor` VALUES (30, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 00:13:57');
INSERT INTO `sys_logininfor` VALUES (31, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 01:07:32');
INSERT INTO `sys_logininfor` VALUES (32, 'admin', '192.168.1.12', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 11:06:11');
INSERT INTO `sys_logininfor` VALUES (33, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 14:27:14');
INSERT INTO `sys_logininfor` VALUES (34, 'admin', '192.168.1.12', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 16:54:54');
INSERT INTO `sys_logininfor` VALUES (35, 'admin', '192.168.1.12', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 16:59:55');
INSERT INTO `sys_logininfor` VALUES (36, 'admin', '192.168.1.12', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 17:00:41');
INSERT INTO `sys_logininfor` VALUES (37, 'admin', '192.168.1.12', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 17:00:57');
INSERT INTO `sys_logininfor` VALUES (38, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 17:38:21');
INSERT INTO `sys_logininfor` VALUES (39, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 17:38:35');
INSERT INTO `sys_logininfor` VALUES (40, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 18:00:48');
INSERT INTO `sys_logininfor` VALUES (41, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-28 18:12:10');
INSERT INTO `sys_logininfor` VALUES (42, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 10:31:59');
INSERT INTO `sys_logininfor` VALUES (43, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 18:04:19');
INSERT INTO `sys_logininfor` VALUES (44, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 18:04:28');
INSERT INTO `sys_logininfor` VALUES (45, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 18:05:02');
INSERT INTO `sys_logininfor` VALUES (46, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 18:05:15');
INSERT INTO `sys_logininfor` VALUES (47, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 23:24:10');
INSERT INTO `sys_logininfor` VALUES (48, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 23:28:33');
INSERT INTO `sys_logininfor` VALUES (49, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 23:28:41');
INSERT INTO `sys_logininfor` VALUES (50, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 23:35:14');
INSERT INTO `sys_logininfor` VALUES (51, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-29 23:42:32');
INSERT INTO `sys_logininfor` VALUES (52, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 23:42:35');
INSERT INTO `sys_logininfor` VALUES (53, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 23:49:22');
INSERT INTO `sys_logininfor` VALUES (54, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 23:57:38');
INSERT INTO `sys_logininfor` VALUES (55, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-29 23:59:20');
INSERT INTO `sys_logininfor` VALUES (56, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-30 00:00:33');
INSERT INTO `sys_logininfor` VALUES (57, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-30 00:04:51');
INSERT INTO `sys_logininfor` VALUES (58, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '验证码错误', '2019-08-30 00:11:29');
INSERT INTO `sys_logininfor` VALUES (59, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-30 00:11:32');
INSERT INTO `sys_logininfor` VALUES (60, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-30 01:03:05');
INSERT INTO `sys_logininfor` VALUES (61, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-30 01:11:36');
INSERT INTO `sys_logininfor` VALUES (62, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-30 01:15:54');
INSERT INTO `sys_logininfor` VALUES (63, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '登录成功', '2019-08-30 02:58:20');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（1显示 0隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2389 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统配置', 0, 1, '#', 'M', '1', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-19 14:11:51', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '#', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 99, '#', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-19 14:19:24', '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '版本管理', 1, 3, '/system/user', 'C', '1', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2019-05-16 17:31:36', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', 'C', '1', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', 'C', '1', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 1, '/system/dept', 'C', '1', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2019-05-16 17:31:17', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', 'C', '1', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', 'C', '1', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES (112, '表单构建', 3, 1, '/tool/build', 'C', '1', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 2002, 1, '/monitor/operlog', 'C', '1', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-13 15:09:17', '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 2002, 2, '/monitor/logininfor', 'C', '1', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-11-13 15:09:34', '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', 'F', '1', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', 'F', '1', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', 'F', '1', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', 'F', '1', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', 'F', '1', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1005, '重置密码', 100, 5, '#', 'F', '1', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1006, '角色查询', 101, 1, '#', 'F', '1', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1007, '角色新增', 101, 2, '#', 'F', '1', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1008, '角色修改', 101, 3, '#', 'F', '1', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1009, '角色删除', 101, 4, '#', 'F', '1', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1010, '角色导出', 101, 4, '#', 'F', '1', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1011, '菜单查询', 102, 1, '#', 'F', '1', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1012, '菜单新增', 102, 2, '#', 'F', '1', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单修改', 102, 3, '#', 'F', '1', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单删除', 102, 4, '#', 'F', '1', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1015, '部门查询', 103, 1, '#', 'F', '1', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1016, '部门新增', 103, 2, '#', 'F', '1', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1017, '部门修改', 103, 3, '#', 'F', '1', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1018, '部门删除', 103, 4, '#', 'F', '1', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1024, '字典查询', 105, 1, '#', 'F', '1', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1025, '字典新增', 105, 2, '#', 'F', '1', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1026, '字典修改', 105, 3, '#', 'F', '1', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1027, '字典删除', 105, 4, '#', 'F', '1', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1028, '字典导出', 105, 4, '#', 'F', '1', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1038, '操作查询', 500, 1, '#', 'F', '1', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1039, '操作删除', 500, 2, '#', 'F', '1', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1040, '详细信息', 500, 3, '#', 'F', '1', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 3, '#', 'F', '1', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', 'F', '1', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', 'F', '1', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 2, '#', 'F', '1', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1045, '在线查询', 109, 1, '#', 'F', '1', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1046, '批量强退', 109, 2, '#', 'F', '1', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1047, '单条强退', 109, 3, '#', 'F', '1', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (2002, '日志管理', 0, 3, '#', 'M', '0', '', 'fa fa-folder-open-o', 'admin', '2018-11-13 15:01:09', 'admin', '2018-11-19 14:19:34', '');
INSERT INTO `sys_menu` VALUES (2004, '代理商管理', 0, 98, '#', 'M', '0', '', 'fa fa-user', 'admin', '2018-11-19 14:18:25', 'admin', '2019-07-24 21:00:12', '');
INSERT INTO `sys_menu` VALUES (2005, '商品管理', 0, 4, '#', 'M', '0', '', 'fa fa-circle', 'admin', '2018-11-19 14:20:43', 'admin', '2019-07-24 20:59:25', '');
INSERT INTO `sys_menu` VALUES (2006, '订单管理', 0, 5, '#', 'M', '0', '', 'fa fa-diamond', 'admin', '2018-11-19 14:21:45', 'admin', '2019-07-24 20:59:40', '');
INSERT INTO `sys_menu` VALUES (2007, 'KYC身份', 0, 6, '#', 'M', '1', '', 'fa fa-hand-stop-o', 'admin', '2018-11-19 14:24:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2008, '平台钱包', 0, 7, '#', 'M', '1', '', 'fa fa-thermometer', 'admin', '2018-11-19 14:25:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2010, '运营管理', 0, 9, '#', 'M', '1', '', 'fa fa-navicon', 'admin', '2018-11-19 14:29:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2011, '微合约持仓单', 2006, 1, '/contract/micro', 'C', '1', 'contract:micro:view', '#', 'admin', '2018-11-21 17:31:24', 'admin', '2018-12-03 17:55:32', '');
INSERT INTO `sys_menu` VALUES (2012, 'KYC身份认证', 2007, 1, '/audit/authentication', 'C', '1', 'audit:authentication:view', '#', 'admin', '2018-11-21 19:59:21', 'admin', '2018-11-22 15:52:40', '');
INSERT INTO `sys_menu` VALUES (2013, '版本管理', 2010, 1, '/content/banner', 'C', '1', 'content:banner:view', '#', 'admin', '2018-11-22 14:21:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2014, '微合约历史单', 2006, 2, '/contract/microhistory', 'C', '1', 'contract:microhistory:view', '#', 'admin', '2018-11-22 15:35:38', 'admin', '2018-12-03 17:55:43', '');
INSERT INTO `sys_menu` VALUES (2015, '永续合约持仓单', 2006, 3, '/contract/perpetual', 'C', '1', 'contract:perpetual:view', '#', 'admin', '2018-11-22 17:53:28', 'admin', '2018-12-03 17:55:55', '');
INSERT INTO `sys_menu` VALUES (2016, '充值审核', 2007, 2, '/audit/recharge', 'M', '0', 'audit:recharge:view', '#', 'admin', '2018-11-23 15:19:12', 'admin', '2019-07-17 10:05:08', '');
INSERT INTO `sys_menu` VALUES (2017, '审核通过', 2016, 1, '#', 'F', '1', 'audit:recharge:auditPass', '#', 'admin', '2018-11-24 13:53:32', 'admin', '2018-11-24 15:02:02', '');
INSERT INTO `sys_menu` VALUES (2018, '永续合约历史单', 2006, 4, '/contract/perpetualhistory', 'C', '1', 'contract:perpetualhistory:view', '#', 'admin', '2018-11-24 14:27:59', 'admin', '2018-12-03 17:56:12', '');
INSERT INTO `sys_menu` VALUES (2019, '审核拒绝', 2016, 2, '#', 'F', '1', 'audit:recharge:auditRefuse', '#', 'admin', '2018-11-24 17:33:15', 'admin', '2018-11-24 18:51:55', '');
INSERT INTO `sys_menu` VALUES (2020, '提现审核', 2007, 3, '/audit/recharge', 'C', '0', 'audit:withdraw:view', '#', 'admin', '2018-11-26 09:27:57', 'admin', '2018-11-26 09:29:24', '');
INSERT INTO `sys_menu` VALUES (2021, '详情', 2020, 1, '#', 'F', '1', 'audit:withdraw:edit', '#', 'admin', '2018-11-26 11:31:29', 'admin', '2018-11-30 14:59:59', '');
INSERT INTO `sys_menu` VALUES (2022, '微合约管理', 2005, 1, '/product/micro', 'C', '1', 'product:micro:view', '#', 'admin', '2018-11-27 09:54:06', 'admin', '2018-12-03 17:54:06', '');
INSERT INTO `sys_menu` VALUES (2023, '永续合约管理', 2005, 2, '/product/perpetual', 'C', '1', 'product:perpetual:view', '#', 'admin', '2018-11-27 09:54:56', 'admin', '2018-12-03 17:54:30', '');
INSERT INTO `sys_menu` VALUES (2024, '编辑', 2016, 3, '#', 'F', '1', 'audit:recharge:edit', '#', 'admin', '2018-11-27 20:13:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2025, '冷钱包地址', 2008, 1, '/wallet/view', 'C', '1', 'wallet:view', '#', 'admin', '2018-11-28 15:03:00', 'admin', '2019-08-02 15:25:26', '');
INSERT INTO `sys_menu` VALUES (2028, '热钱包地址', 2008, 2, '/finance/withdrawRecord', 'C', '1', 'finance:withdrawRecord:view', '#', 'admin', '2018-11-28 18:27:24', 'admin', '2019-08-02 15:25:44', '');
INSERT INTO `sys_menu` VALUES (2029, '提现记录查询', 2028, 1, '#', 'F', '1', 'finance:withdrawRecord:list', '#', 'admin', '2018-11-28 18:28:43', 'admin', '2018-11-28 18:29:18', '');
INSERT INTO `sys_menu` VALUES (2030, '提现导出', 2028, 2, '#', 'F', '1', 'finance:withdrawRecord:export', '#', 'admin', '2018-11-28 18:29:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2031, '用户信息列表', 2009, 1, 'user', 'C', '1', 'user:view', '#', 'admin', '2018-11-28 18:37:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2032, '平台钱包资产', 2008, 5, '/finance/setting/rateSetting', 'C', '1', 'finance:rateSetting:view', '#', 'admin', '2018-11-29 09:55:34', 'admin', '2019-07-31 11:02:26', '');
INSERT INTO `sys_menu` VALUES (2033, '汇率编辑', 2032, 1, '#', 'F', '1', 'finance:rateSetting:edit', '#', 'admin', '2018-11-29 09:57:41', 'admin', '2018-11-29 09:59:52', '');
INSERT INTO `sys_menu` VALUES (2034, '出入金限制设置', 2008, 6, '/finance/setting/amountSetting', 'C', '0', 'finance:amountSetting:view', '#', 'admin', '2018-11-29 10:01:15', 'admin', '2019-07-31 11:02:32', '');
INSERT INTO `sys_menu` VALUES (2035, '出入金限制编辑', 2034, 1, '#', 'F', '1', 'finance:amountSetting:edit', '#', 'admin', '2018-11-29 10:02:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2036, '人工充值申请', 2008, 3, '/finance/manualOperation/rechargeApply', 'C', '0', 'finance:rechargeApply:view', '#', 'admin', '2018-11-29 18:25:25', 'admin', '2019-07-31 11:02:37', '');
INSERT INTO `sys_menu` VALUES (2037, '人工提现申请', 2008, 4, '/finance/manualOperation/withdrawApply', 'C', '0', 'finance:withdrawApply:view', '#', 'admin', '2018-11-29 18:28:26', 'admin', '2019-07-31 11:02:41', '');
INSERT INTO `sys_menu` VALUES (2038, '充值申请', 2036, 1, '#', 'F', '1', 'finance:rechargeApply:apply', '#', 'admin', '2018-11-29 18:31:32', 'admin', '2018-11-29 19:51:43', '');
INSERT INTO `sys_menu` VALUES (2039, '提现申请', 2037, 1, '#', 'F', '1', 'finance:withdrawApply:apply', '#', 'admin', '2018-11-29 18:32:19', 'admin', '2018-11-29 19:52:05', '');
INSERT INTO `sys_menu` VALUES (2040, '代理商账号管理', 2004, 1, '/agnt/user', 'C', '1', 'agnt:user:view', '#', 'admin', '2018-11-30 14:16:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2041, '操作查询', 2040, 1, '#', 'F', '1', 'agnt:user:list', '#', 'admin', '2018-11-30 14:18:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2042, '操作新增', 2040, 2, '#', 'F', '1', 'agnt:user:add', '#', 'admin', '2018-11-30 14:18:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2043, '身份认证详情', 2012, 1, '#', 'F', '1', 'audit:authentication:edit', '#', 'admin', '2018-11-30 14:34:10', 'admin', '2018-11-30 14:34:38', '');
INSERT INTO `sys_menu` VALUES (2044, '身份认证审核', 2012, 2, '#', 'F', '1', 'audit:authentication:audit', '#', 'admin', '2018-11-30 14:59:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2045, '提现审核', 2020, 2, '#', 'F', '1', 'audit:withdraw:audit', '#', 'admin', '2018-11-30 15:00:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2046, '操作编辑', 2040, 3, '#', 'F', '1', 'agnt:user:edit', '#', 'admin', '2018-11-30 16:22:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2047, '参数设置管理', 0, 10, '#', 'M', '0', '', 'fa fa-qrcode', 'admin', '2018-12-01 10:30:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2048, '参数设置', 2047, 1, '/config/parameterConfig', 'C', '1', 'config:parameterConfig:view', '#', 'admin', '2018-12-01 10:30:54', 'admin', '2018-12-01 10:34:15', '');
INSERT INTO `sys_menu` VALUES (2049, '参数查询', 2048, 1, '#', 'F', '1', 'config:parameterConfig:list', '#', 'admin', '2018-12-01 10:35:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2050, '参数新增', 2048, 2, '#', 'F', '1', 'config:parameterConfig:add', '#', 'admin', '2018-12-01 10:35:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2051, '参数修改', 2048, 3, '#', 'F', '1', 'config:parameterConfig:edit', '#', 'admin', '2018-12-01 10:35:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2052, '参数删除', 2048, 4, '#', 'F', '1', 'config:parameterConfig:del', '#', 'admin', '2018-12-01 10:36:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2053, '保存或更新', 2048, 5, '#', 'F', '1', 'config:parameterConfig:saveOrUpdate', '#', 'admin', '2018-12-01 18:07:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2055, '公告管理', 2010, 3, '/content/article', 'C', '1', 'content:article:view', '#', 'admin', '2018-12-06 11:19:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2056, '帮助中心管理', 2010, 4, '/content/notice', 'C', '1', 'content:notice:view', '#', 'admin', '2018-12-06 13:19:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2057, 'banner查询', 2013, 1, '#', 'F', '1', 'content:banner:list', '#', 'admin', '2018-12-08 11:59:18', 'admin', '2018-12-08 11:59:40', '');
INSERT INTO `sys_menu` VALUES (2058, 'banner新增', 2013, 2, '#', 'F', '1', 'content:banner:add', '#', 'admin', '2018-12-08 12:00:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2059, 'banner编辑', 2013, 3, '#', 'F', '1', 'content:banner:edit', '#', 'admin', '2018-12-08 12:01:00', 'admin', '2018-12-08 14:24:22', '');
INSERT INTO `sys_menu` VALUES (2060, 'banner保存或更新', 2013, 4, '#', 'F', '1', 'content:banner:saveOrUpdate', '#', 'admin', '2018-12-08 12:01:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2061, '身份认证审核列表', 2012, 3, '#', 'F', '1', 'audit:authentication:list', '#', 'admin', '2018-12-08 12:05:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2062, '充值审核列表', 2016, 4, '#', 'F', '1', 'audit:recharge:list', '#', 'admin', '2018-12-08 15:30:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2063, '提现审核列表', 2020, 3, '#', 'F', '1', 'audit:withdraw:list', '#', 'admin', '2018-12-08 15:30:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2064, '操作查询', 2022, 1, '#', 'F', '1', 'product:micro:list', '#', 'admin', '2018-12-08 15:33:50', 'admin', '2018-12-08 15:34:21', '');
INSERT INTO `sys_menu` VALUES (2065, '微合约编辑', 2022, 2, '#', 'F', '1', 'product:micro:edit', '#', 'admin', '2018-12-08 15:37:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2066, '操作查询', 2023, 1, '#', 'F', '1', 'product:perpetual:list', '#', 'admin', '2018-12-08 15:38:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2067, '永续合约编辑', 2023, 2, '#', 'F', '1', 'product:perpetual:edit', '#', 'admin', '2018-12-08 15:39:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2068, '操作查询', 2011, 1, '#', 'F', '1', 'contract:micro:list', '#', 'admin', '2018-12-08 15:41:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2069, '列表导出', 2011, 2, '#', 'F', '1', 'contract:micro:export', '#', 'admin', '2018-12-08 15:42:05', 'admin', '2018-12-08 15:45:22', '');
INSERT INTO `sys_menu` VALUES (2070, '操作查询', 2014, 1, '#', 'F', '1', 'contract:microhistory:list', '#', 'admin', '2018-12-08 15:43:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2071, '列表导出', 2014, 2, '#', 'F', '1', 'contract:microhistory:export', '#', 'admin', '2018-12-08 15:45:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2072, '操作查询', 2015, 1, '#', 'F', '1', 'contract:perpetual:list', '#', 'admin', '2018-12-08 15:46:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2073, '列表导出', 2015, 2, '#', 'F', '1', 'contract:perpetual:export', '#', 'admin', '2018-12-08 15:46:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2074, '强制平仓', 2015, 3, '#', 'F', '1', 'contract:perpetual:sell', '#', 'admin', '2018-12-08 15:47:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2075, '操作查询', 2018, 1, '#', 'F', '1', 'contract:perpetualhistory:list', '#', 'admin', '2018-12-08 15:48:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2076, '列表导出', 2018, 2, '#', 'F', '1', 'contract:perpetualhistory:export', '#', 'admin', '2018-12-08 15:48:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2077, '操作查询', 2031, 1, '#', 'F', '1', 'user:list', '#', 'admin', '2018-12-08 15:50:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2078, '列表导出', 2031, 2, '#', 'F', '1', 'user:export', '#', 'admin', '2018-12-08 15:51:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2079, '用户详情', 2031, 3, '#', 'F', '1', 'user:detail', '#', 'admin', '2018-12-08 15:51:43', 'admin', '2018-12-08 15:52:11', '');
INSERT INTO `sys_menu` VALUES (2080, '操作查询', 2055, 1, '#', 'F', '1', 'content:article:list', '#', 'admin', '2018-12-08 15:54:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2081, '活动编辑', 2055, 2, '#', 'F', '1', 'content:article:edit', '#', 'admin', '2018-12-08 15:55:20', 'admin', '2018-12-08 15:57:11', '');
INSERT INTO `sys_menu` VALUES (2082, '活动新增', 2055, 3, '#', 'F', '1', 'content:article:add', '#', 'admin', '2018-12-08 15:56:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2083, '活动显示隐藏', 2055, 5, '#', 'F', '1', 'content:article:published', '#', 'admin', '2018-12-08 15:57:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2084, '活动删除', 2055, 4, '#', 'F', '1', 'content:article:deleteById', '#', 'admin', '2018-12-08 15:57:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2085, '操作查询', 2056, 1, '#', 'F', '1', 'content:notice:list', '#', 'admin', '2018-12-08 15:58:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2086, '公告帮助编辑', 2056, 2, '#', 'F', '1', 'content:notice:edit', '#', 'admin', '2018-12-08 16:01:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2087, '公告帮助新增', 2056, 3, '#', 'F', '1', 'content:notice:add', '#', 'admin', '2018-12-08 16:02:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2088, '公告帮助删除', 2056, 4, '#', 'F', '1', 'content:notice:deleteById', '#', 'admin', '2018-12-08 16:02:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2089, '显示隐藏', 2056, 5, '#', 'F', '1', 'content:notice:published', '#', 'admin', '2018-12-08 16:05:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2090, '代理商列表', 2004, 2, '/agnt/list', 'C', '1', 'agnt:list:view', '#', 'admin', '2018-12-10 10:45:56', 'admin', '2018-12-10 10:46:32', '');
INSERT INTO `sys_menu` VALUES (2091, '代理商列表查询', 2090, 1, '#', 'F', '1', 'agnt:list:list', '#', 'admin', '2018-12-10 10:47:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2092, '代理商列表导出', 2090, 2, '#', 'F', '1', 'agnt:list:export', '#', 'admin', '2018-12-10 10:47:59', 'admin', '2018-12-10 15:20:08', '');
INSERT INTO `sys_menu` VALUES (2093, '代理客户', 2004, 3, '/agnt/customer', 'C', '1', 'agnt:customer:view', '#', 'admin', '2018-12-10 15:21:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2094, '代理客户查询', 2093, 1, '#', 'F', '1', 'agnt:customer:list', '#', 'admin', '2018-12-10 15:21:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2095, '代理客户导出', 2093, 2, '#', 'F', '1', 'agnt:customer:export', '#', 'admin', '2018-12-10 15:22:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2096, '体验金管理', 2005, 3, '/product/token', 'C', '1', 'product:tokenSetting:view', '#', 'admin', '2018-12-18 20:12:10', 'admin', '2018-12-18 20:12:28', '');
INSERT INTO `sys_menu` VALUES (2097, '体验金设置编辑', 2096, 1, '#', 'F', '1', 'product:tokenSetting:edit', '#', 'admin', '2018-12-18 20:18:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2098, '版本管理', 2047, 2, '/config/appVersion', 'C', '1', 'config:appversion:view', '#', 'admin', '2018-12-21 16:40:04', 'admin', '2018-12-21 16:40:37', '');
INSERT INTO `sys_menu` VALUES (2099, '版本列表', 2098, 1, '#', 'F', '1', 'config:appversion:list', '#', 'admin', '2018-12-21 16:41:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2100, '版本添加', 2098, 2, '#', 'F', '1', 'config:appversion:add', '#', 'admin', '2018-12-21 16:42:16', 'admin', '2018-12-21 18:40:11', '');
INSERT INTO `sys_menu` VALUES (2101, '版本编辑', 2098, 3, '#', 'F', '1', 'config:appversion:edit', '#', 'admin', '2018-12-21 16:42:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2102, '保存或更新', 2098, 4, '#', 'F', '1', 'config:appversion:saveOrUpdate', '#', 'admin', '2018-12-21 18:40:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2103, '个人邀请', 0, 11, '#', 'M', '0', '', 'fa fa-handshake-o', 'admin', '2019-01-07 18:19:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2104, '个人邀请信息', 2103, 1, 'labour', 'C', '1', 'labour:view', '#', 'admin', '2019-01-07 18:20:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2105, '自动充值审核', 2016, 5, '#', 'F', '1', 'audit:recharge:rechargeConfig', '#', 'admin', '2019-01-08 17:21:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2106, '承兑商管理', 2005, 4, '/product/acceptDealer', 'C', '1', 'product:acceptDealer:view', '#', 'admin', '2019-01-08 19:47:53', 'admin', '2019-01-08 19:48:15', '');
INSERT INTO `sys_menu` VALUES (2107, '承兑商列表', 2106, 1, '#', 'F', '1', 'product:acceptDealer:list', '#', 'admin', '2019-01-08 19:48:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2108, '承兑商新增或编辑', 2106, 2, '#', 'F', '1', 'product:acceptDealer:saveOrUpdate', '#', 'admin', '2019-01-08 19:49:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2109, '承兑商新增', 2106, 3, '#', 'F', '1', 'product:acceptDealer:add', '#', 'admin', '2019-01-08 19:49:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2110, '承兑商编辑', 2106, 4, '#', 'F', '1', 'product:acceptDealer:edit', '#', 'admin', '2019-01-08 19:50:16', 'admin', '2019-01-08 19:51:02', '');
INSERT INTO `sys_menu` VALUES (2111, '承兑商删除', 2106, 5, '#', 'F', '1', 'product:acceptDealer:del', '#', 'admin', '2019-01-08 19:50:44', 'admin', '2019-01-08 19:51:16', '');
INSERT INTO `sys_menu` VALUES (2112, '个人邀请管理', 2005, 5, '/product/inviteReward', 'C', '1', 'product:inviteReward:view', '#', 'admin', '2019-01-09 10:16:25', 'admin', '2019-01-09 10:18:14', '');
INSERT INTO `sys_menu` VALUES (2113, '奖励编辑', 2112, 1, '#', 'F', '1', 'product:inviteReward:edit', '#', 'admin', '2019-01-09 10:17:56', 'admin', '2019-01-09 14:16:43', '');
INSERT INTO `sys_menu` VALUES (2114, '操作删除', 2040, 4, '#', 'F', '1', 'agnt:user:del', '#', 'admin', '2019-01-10 16:39:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2115, '身份认证审核拒绝', 2012, 4, '#', 'F', '1', 'audit:authentication:auditRefuse', '#', 'admin', '2019-01-10 19:52:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2116, '代理客户编辑', 2093, 3, '#', 'F', '1', 'agnt:customer:edit', '#', 'admin', '2019-01-11 18:11:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2119, '法币', 0, 1, '#', 'M', '1', '', 'fa fa-user-circle-o', 'admin', '2019-03-19 10:57:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2120, '出售订单', 2119, 3, 'c2c/orderUser', 'C', '1', 'orderUser:view', '#', 'admin', '2019-03-19 11:00:15', 'admin', '2019-03-29 15:19:52', '');
INSERT INTO `sys_menu` VALUES (2122, '广告', 2119, 1, '/c2c/orderEntrust', 'C', '1', 'orderEntrust:view', '#', 'admin', '2019-03-20 11:43:37', 'admin', '2019-03-29 15:20:18', '');
INSERT INTO `sys_menu` VALUES (2123, '购买订单', 2119, 2, 'personal/personalCard', 'C', '1', 'personal:personalCard:view', 'fa fa-address-card-o', 'admin', '2019-03-26 11:34:53', 'admin', '2019-05-14 15:22:57', '');
INSERT INTO `sys_menu` VALUES (2124, '币币', 0, 2, '#', 'M', '1', '', 'fa fa-cogs', 'admin', '2019-04-07 18:35:28', 'admin', '2019-04-09 20:07:55', '');
INSERT INTO `sys_menu` VALUES (2126, '币币交易当前委托单', 2124, 1, 'beta/comboGroup', 'C', '1', 'comboGroup:view', '#', 'admin', '2019-04-07 18:37:31', 'admin', '2019-05-16 14:33:25', '');
INSERT INTO `sys_menu` VALUES (2127, '法币币种配置', 2119, 4, 'node/personalPartner', 'C', '1', 'personalPartner:view', 'fa fa-group', 'admin', '2019-04-09 12:36:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2128, '币币交易历史订单', 2124, 1, 'beta/betaMale', 'C', '1', 'betaMale:view', '#', 'admin', '2019-04-10 15:32:43', 'admin', '2019-05-16 14:50:58', '');
INSERT INTO `sys_menu` VALUES (2129, '币币币种配置', 2124, 1, 'beta/betaHeader', 'C', '1', 'betaHeader:view', '#', 'admin', '2019-04-11 20:25:17', 'admin', '2019-04-24 16:00:23', '');
INSERT INTO `sys_menu` VALUES (2130, '参数配置', 2124, 1, 'beta/betaFemina', 'C', '1', 'betaFemina:view', '#', 'admin', '2019-04-11 23:07:06', 'admin', '2019-04-24 16:00:32', '');
INSERT INTO `sys_menu` VALUES (2131, '参数配置', 2119, 5, 'personalAccount', 'C', '1', 'personalAccount:view', 'fa fa-address-book-o', 'admin', '2019-04-12 11:13:03', 'admin', '2019-05-19 16:14:51', '');
INSERT INTO `sys_menu` VALUES (2132, '资产', 2124, 5, 'beta/betaAccount', 'C', '0', 'betaAccount:view', '#', 'admin', '2019-04-14 15:42:49', 'admin', '2019-04-24 16:01:33', '');
INSERT INTO `sys_menu` VALUES (2134, '预约', 2124, 6, 'beta/betaReservation', 'C', '0', 'betaReservation:view', '#', 'admin', '2019-04-15 10:55:15', 'admin', '2019-05-16 17:22:54', '');
INSERT INTO `sys_menu` VALUES (2135, '订单', 2124, 1, 'beta/betaOrder', 'C', '0', 'betaOrderInfo:view', '#', 'admin', '2019-04-20 17:54:58', 'admin', '2019-04-24 19:05:46', '');
INSERT INTO `sys_menu` VALUES (2136, '已处理订单', 2124, 1, 'beta/betaProcessedaOrder', 'C', '0', 'betaProcessedaOrder:info', '#', 'admin', '2019-04-24 21:56:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2139, '推广团队配置', 2124, 10, 'beta/betaTeamConfig', 'C', '0', 'betaTeamConfig:view', '#', 'admin', '2019-05-09 21:42:06', 'admin', '2019-05-16 15:57:25', '');
INSERT INTO `sys_menu` VALUES (2147, '用户抢狗分析', 2, 2, '/beta/betaAdoption', 'C', '1', 'betaAdoption:view', '#', 'admin', '2019-05-16 10:05:09', 'admin', '2019-05-16 12:10:42', '');
INSERT INTO `sys_menu` VALUES (2148, '新增', 2139, 1, '#', 'F', '1', 'betaTeamConfig:add', '#', 'admin', '2019-05-16 12:14:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2149, '导出', 2122, 1, '#', 'F', '1', 'orderUser:export', '#', 'admin', '2019-05-16 12:15:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2150, '删除', 2139, 2, '#', 'F', '1', 'betaTeamConfig:delete', '#', 'admin', '2019-05-16 12:15:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2151, '列表', 2139, 3, '#', 'F', '1', 'betaTeamConfig:list', '#', 'admin', '2019-05-16 12:15:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2152, '编辑', 2139, 4, '#', 'F', '1', 'betaTeamConfig:edit', '#', 'admin', '2019-05-16 12:16:19', 'admin', '2019-05-16 16:06:37', '');
INSERT INTO `sys_menu` VALUES (2153, '订单详情', 2122, 2, '#', 'F', '1', 'orderEntrust:orderUserDetail', '#', 'admin', '2019-05-16 12:16:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2154, '列表', 2136, 1, '#', 'F', '1', 'betaProcessedaOrder:list', '#', 'admin', '2019-05-16 12:17:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2155, '详情', 2136, 2, '#', 'F', '1', 'betaProcessedaOrder:edit', '#', 'admin', '2019-05-16 12:17:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2156, '删除', 2136, 3, '#', 'F', '1', 'betaProcessedaOrderdelete:delete', '#', 'admin', '2019-05-16 12:18:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2157, '修改身份', 2123, 1, '#', 'F', '1', 'personal:personalCard:updateCurrentIdentity', '#', 'admin', '2019-05-16 12:18:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2158, '列表', 2135, 1, '#', 'F', '1', 'betaOrderListPage:list', '#', 'admin', '2019-05-16 12:18:39', 'admin', '2019-05-16 18:47:03', '');
INSERT INTO `sys_menu` VALUES (2159, '详情', 2123, 2, '#', 'F', '1', 'personal:personalCard:detail', '#', 'admin', '2019-05-16 12:18:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2160, '详情', 2135, 3, '#', 'F', '1', 'orderListDetails:edit', '#', 'admin', '2019-05-16 12:19:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2161, '导出', 2135, 2, '#', 'F', '1', 'betaOrder:export', '#', 'admin', '2019-05-16 12:19:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2162, '审核', 2123, 3, '#', 'F', '1', 'personal:personalCard:audit', '#', 'admin', '2019-05-16 12:19:50', 'admin', '2019-05-19 15:31:01', '');
INSERT INTO `sys_menu` VALUES (2163, '放狗', 2135, 4, '#', 'F', '1', 'orderList:audit', '#', 'admin', '2019-05-16 12:20:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2164, '拒绝放狗', 2135, 5, '#', 'F', '1', 'orderList:auditRefuseDogo', '#', 'admin', '2019-05-16 12:20:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2165, '列表', 2134, 1, '#', 'F', '1', 'betaReservation:list', '#', 'admin', '2019-05-16 12:50:50', 'admin', '2019-05-16 16:29:20', '');
INSERT INTO `sys_menu` VALUES (2166, '详情', 2134, 2, '#', 'F', '1', 'betaMaleList:edit', '#', 'admin', '2019-05-16 12:51:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2167, '新增', 2134, 3, '#', 'F', '1', 'betaHeader:saveOrUpdate', '#', 'admin', '2019-05-16 12:51:45', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2168, '删除', 2134, 4, '#', 'F', '1', 'betaReservation:delete', '#', 'admin', '2019-05-16 12:52:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2169, '列表', 2132, 1, '#', 'F', '1', 'betaAccount:list', '#', 'admin', '2019-05-16 12:54:32', 'admin', '2019-05-16 16:58:38', '');
INSERT INTO `sys_menu` VALUES (2170, '流水', 2132, 2, '#', 'F', '1', 'betaAccountRecordListInfo:view', '#', 'admin', '2019-05-16 12:54:54', 'admin', '2019-05-16 18:06:09', '');
INSERT INTO `sys_menu` VALUES (2171, '列表', 2130, 1, '#', 'F', '1', 'betaFemina:list', '#', 'admin', '2019-05-16 12:55:47', 'admin', '2019-05-16 19:59:36', '');
INSERT INTO `sys_menu` VALUES (2172, '详情', 2130, 2, '#', 'F', '1', 'betaFemina:edit', '#', 'admin', '2019-05-16 12:56:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2173, '增加修改', 2130, 3, '#', 'F', '1', 'betaFemina:saveOrUpdate', '#', 'admin', '2019-05-16 12:57:09', 'admin', '2019-05-16 20:03:22', '');
INSERT INTO `sys_menu` VALUES (2174, '列表', 2129, 1, '#', 'F', '1', 'betaHeader:list', '#', 'admin', '2019-05-16 12:57:49', 'admin', '2019-05-16 20:16:24', '');
INSERT INTO `sys_menu` VALUES (2175, '删除', 2129, 2, '#', 'F', '1', 'betaHeader:delete', '#', 'admin', '2019-05-16 12:58:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2176, '详情', 2129, 3, '#', 'F', '1', 'betaHeader:edit', '#', 'admin', '2019-05-16 12:58:35', 'admin', '2019-05-16 20:07:46', '');
INSERT INTO `sys_menu` VALUES (2177, '修改', 2129, 4, '#', 'F', '1', 'betaHeader:saveOrUpdate', '#', 'admin', '2019-05-16 12:59:21', 'admin', '2019-05-16 20:08:05', '');
INSERT INTO `sys_menu` VALUES (2178, '列表', 2128, 1, '#', 'F', '1', 'betaMaleList:list', '#', 'admin', '2019-05-16 13:00:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2179, '保存beta狗', 2128, 2, '#', 'F', '1', 'betaMaleList:saveOrUpdate', '#', 'admin', '2019-05-16 13:01:01', 'admin', '2019-05-16 20:51:15', '');
INSERT INTO `sys_menu` VALUES (2180, '详情', 2128, 3, '#', 'F', '1', 'betaMaleList:edit', '#', 'admin', '2019-05-16 13:01:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2181, '修改出售时间', 2128, 4, '#', 'F', '1', 'betaMaleList:updatePresellTime', '#', 'admin', '2019-05-16 13:01:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2182, '订单列表', 2128, 4, '#', 'F', '1', 'orderList:list', '#', 'admin', '2019-05-16 13:02:52', 'admin', '2019-05-16 15:23:22', '');
INSERT INTO `sys_menu` VALUES (2184, '列表', 2126, 1, '#', 'F', '1', 'comboGroup:list', '#', 'admin', '2019-05-16 13:04:11', 'admin', '2019-05-16 14:08:20', '');
INSERT INTO `sys_menu` VALUES (2185, '删除', 2126, 2, '#', 'F', '1', 'comboGroup:delete', '#', 'admin', '2019-05-16 14:08:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2186, '编辑', 2126, 3, '#', 'F', '1', 'comboGroup:edit', '#', 'admin', '2019-05-16 14:09:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2187, '新增', 2126, 4, '#', 'F', '1', 'comboGroup:add', '#', 'admin', '2019-05-16 14:48:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2188, '导出', 2128, 6, '#', 'F', '1', 'orderList:importExcel', '#', 'admin', '2019-05-16 15:25:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2189, '抢狗短信 ', 2, 3, '/recordSmsLog', 'C', '1', 'recordSmsLog:view', '#', 'admin', '2019-05-16 18:08:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2190, '团队配置保存', 2139, 1, '#', 'F', '1', 'betaTeamConfig:saveOrUpdate', '#', 'admin', '2019-05-16 18:11:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2191, '资产流水列表', 2132, 3, '#', 'F', '1', 'betaAccountRecordInfo:view', '#', 'admin', '2019-05-16 18:34:57', 'admin', '2019-05-16 18:36:12', '');
INSERT INTO `sys_menu` VALUES (2192, '导出表格', 2120, 1, '#', 'F', '1', 'orderUser:export', '#', 'admin', '2019-05-16 18:50:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2193, '订单详情', 2120, 2, '#', 'F', '1', 'orderUser:detail', '#', 'admin', '2019-05-16 18:51:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2194, '放币', 2193, 1, '#', 'F', '1', 'orderUser:updateOrderUserCoinRelease', '#', 'admin', '2019-05-16 18:52:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2195, '取消订单', 2193, 2, '#', 'F', '1', 'orderUser:updateOrderUserCoinCancel', '#', 'admin', '2019-05-16 18:53:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2196, '查看凭证', 2193, 3, '#', 'F', '1', 'orderUser:selectCredentials', '#', 'admin', '2019-05-16 18:53:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2197, '删除', 2127, 1, '#', 'F', '1', 'personalPartner:delete', '#', 'admin', '2019-05-16 18:54:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2198, '导出', 2153, 1, '#', 'F', '1', 'orderUser:export', '#', 'admin', '2019-05-16 18:56:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2199, '订单详情列表', 2153, 1, '#', 'F', '1', 'orderEntrust:detail', '#', 'admin', '2019-05-16 18:57:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2200, '出售订单详情', 2153, 3, '#', 'F', '1', 'orderUser:detail', '#', 'admin', '2019-05-16 18:58:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2202, '追踪链', 2135, 6, '#', 'F', '1', 'betaOrderListInfo:list', '#', 'admin', '2019-05-16 18:59:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2203, '增加头像', 2129, 5, '#', 'F', '1', 'betaHeader:add', '#', 'admin', '2019-05-16 20:13:45', 'admin', '2019-05-16 20:28:21', '');
INSERT INTO `sys_menu` VALUES (2204, '保存头像', 2129, 7, '#', 'F', '1', 'betaHeader:saveOrUpdate', '#', 'admin', '2019-05-16 20:30:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2205, '新增', 2128, 7, '#', 'F', '1', 'betaMale:add', '#', 'admin', '2019-05-16 20:49:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2206, '保存套餐', 2126, 5, '#', 'F', '1', 'comboGroup:saveOrUpdate', '#', 'admin', '2019-05-16 20:57:55', 'admin', '2019-05-16 21:01:19', '');
INSERT INTO `sys_menu` VALUES (2207, '删除', 2128, 8, '#', 'F', '1', 'orderList:delete', '#', 'admin', '2019-05-16 21:17:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2208, '列表', 2123, 4, '#', 'F', '1', 'personal:personalCard:list', '#', 'admin', '2019-05-19 15:20:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2209, '列表', 2122, 1, '#', 'F', '1', 'orderEntrust:list', '#', 'admin', '2019-05-19 15:39:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2210, '列表', 2120, 3, '#', 'F', '1', 'orderUser:list', '#', 'admin', '2019-05-19 15:44:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2211, '列表', 2127, 1, '#', 'F', '1', 'personalPartner:list', '#', 'admin', '2019-05-19 15:49:36', 'admin', '2019-05-19 16:13:27', '');
INSERT INTO `sys_menu` VALUES (2212, '列表', 2131, 1, '#', 'F', '1', 'personalAccount:list', '#', 'admin', '2019-05-19 16:15:13', 'admin', '2019-05-19 18:05:36', '');
INSERT INTO `sys_menu` VALUES (2213, '国家区域码管理', 2047, 3, '/config/countryCode', 'C', '1', 'config:countryCode:view', '#', 'admin', '2019-05-20 09:53:14', 'admin', '2019-05-20 18:42:49', '');
INSERT INTO `sys_menu` VALUES (2214, '区域码添加', 2213, 1, '#', 'F', '1', 'config:countryCode:add', '#', 'admin', '2019-05-20 09:56:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2215, '区域码修改', 2213, 2, '#', 'F', '1', 'config:countryCode:edit', '#', 'admin', '2019-05-20 09:56:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2216, '新增或修改', 2213, 3, '#', 'F', '1', 'config:countryCode:saveOrUpdate', '#', 'admin', '2019-05-20 09:57:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2217, '删除区域码', 2213, 3, '#', 'F', '1', 'config:countryCode:remove', '#', 'admin', '2019-05-20 09:57:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2218, '钱包币种配置', 2047, 4, '/config/accountConfig', 'C', '1', 'config:accountConfig:view', '#', 'admin', '2019-05-20 18:42:37', 'admin', '2019-05-20 18:43:02', '');
INSERT INTO `sys_menu` VALUES (2219, '新增钱包币种', 2218, 1, '#', 'F', '1', 'config:accountConfig:add', '#', 'admin', '2019-05-20 18:43:43', 'admin', '2019-05-20 18:44:51', '');
INSERT INTO `sys_menu` VALUES (2220, '编辑钱包币种', 2218, 2, '#', 'F', '1', 'config:accountConfig:edit', '#', 'admin', '2019-05-20 18:44:07', 'admin', '2019-05-20 18:44:43', '');
INSERT INTO `sys_menu` VALUES (2221, '删除钱包币种', 2218, 3, '#', 'F', '1', 'config:accountConfig:remove', '#', 'admin', '2019-05-20 18:44:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2229, '校验人数', 2123, 5, '#', 'F', '1', 'personal:personalCard:checkNumber', '#', 'admin', '2019-05-26 15:10:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2230, '列表', 2147, 1, '#', 'F', '1', 'betaAdoption:list', '#', 'admin', '2019-05-26 15:16:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2231, '列表', 2189, 1, '#', 'F', '1', 'recordSmsLog:list', '#', 'admin', '2019-05-26 15:17:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2232, '个人邀请列表', 2103, 1, '#', 'F', '1', 'labour:list', '#', 'admin', '2019-05-26 16:27:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2233, '充值保存', 2132, 4, '#', 'F', '1', 'betaAccount:saveOrUpdate', '#', 'admin', '2019-05-27 11:59:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2234, '钱包币种配置列表', 2218, 4, '#', 'F', '1', 'config:accountConfig:list', '#', 'admin', '2019-05-27 12:02:24', 'admin', '2019-05-27 12:03:40', '');
INSERT INTO `sys_menu` VALUES (2235, '充币', 2132, 5, '#', 'F', '1', 'betaAccount:edit', '#', 'admin', '2019-05-27 12:08:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2238, '用户监控', 2237, 1, '#', 'F', '1', 'index:userMonitor', '#', 'admin', '2019-05-27 20:50:25', 'admin', '2019-05-27 21:18:01', '');
INSERT INTO `sys_menu` VALUES (2240, '抢狗监控', 2237, 2, '#', 'F', '1', 'index:betaMonitor', '#', 'admin', '2019-05-27 20:50:38', 'admin', '2019-05-27 21:18:15', '');
INSERT INTO `sys_menu` VALUES (2241, '实名监控', 2237, 3, '#', 'F', '1', 'index:cardMonitor', '#', 'admin', '2019-05-27 20:50:55', 'admin', '2019-05-27 21:18:28', '');
INSERT INTO `sys_menu` VALUES (2242, '持仓监控', 2237, 4, '#', 'F', '1', 'index:accountMonitor', '#', 'admin', '2019-05-27 20:51:09', 'admin', '2019-05-27 21:18:39', '');
INSERT INTO `sys_menu` VALUES (2244, '人工充币记录', 2124, 11, 'beta/accountUpRecord', 'C', '0', 'accountUpRecord:view', '#', 'admin', '2019-05-31 23:37:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2245, '列表', 2244, 1, 'beta/accountUpRecord/list', 'F', '1', 'accountUpRecord:list', '#', 'admin', '2019-05-31 23:37:43', 'admin', '2019-05-31 23:39:03', '');
INSERT INTO `sys_menu` VALUES (2246, '用户统计', 2009, 2, 'user/userStatistic', 'C', '1', '#', '#', 'admin', '2019-06-01 14:42:04', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2247, '图片日志', 2, 4, 'beta/imgLog', 'C', '1', 'imgLogInfo:view', '#', 'admin', '2019-06-03 11:15:46', 'admin', '2019-06-10 16:29:23', '');
INSERT INTO `sys_menu` VALUES (2248, '数据库监控', 2, 5, '/druid/datasource.html', 'C', '1', 'druid:view', '#', 'admin', '2019-06-06 16:12:56', 'admin', '2019-06-06 16:13:37', '');
INSERT INTO `sys_menu` VALUES (2249, '手动刷新套餐缓存', 2126, 6, '#', 'F', '1', 'comboGroup:refreshRedis', '#', 'admin', '2019-06-08 13:43:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2250, '手动刷新预约缓存', 2134, 5, '#', 'F', '1', 'betaReservation:refreshRedis', '#', 'admin', '2019-06-08 14:43:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2251, '手动刷新公狗缓存', 2128, 9, '#', 'F', '1', 'betaMale:refreshRedis', '#', 'admin', '2019-06-08 18:27:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2252, '刷新实名缓存', 2123, 6, '#', 'F', '1', 'personal:personalCard:refreshRedis', '#', 'admin', '2019-06-10 04:13:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2253, '列表', 2247, 1, '#', 'F', '1', 'imgLog:list', '#', 'admin', '2019-06-10 16:20:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2254, '详情', 2189, 2, '#', 'F', '1', 'imgLog:edit', '#', 'admin', '2019-06-10 16:21:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2255, '详情', 2247, 2, '#', 'F', '1', 'imgLog:edit', '#', 'admin', '2019-06-10 16:24:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2256, '删除', 2247, 3, '#', 'F', '1', 'imgLog:remove', '#', 'admin', '2019-06-10 16:24:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2257, '导入列表', 2128, 10, '#', 'F', '1', 'betaMaleImportRecord:list', '#', 'admin', '2019-06-10 22:13:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2258, '定时任务', 2, 7, '/sysJob', 'C', '1', 'sysJob:job:view', '#', 'admin', '2019-06-11 22:25:31', 'admin', '2019-06-12 20:14:29', '');
INSERT INTO `sys_menu` VALUES (2259, '持仓监控列表', 2242, 1, 'selectCountUserBeta:list', 'F', '1', 'selectCountUserBeta:list', '#', 'admin', '2019-06-13 21:02:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2260, '持仓监控页面', 2242, 2, '#', 'F', '1', 'selectCountUserBeta:view', '#', 'admin', '2019-06-13 21:03:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2262, '资产账户', 0, 3, '#', 'M', '1', '', 'fa fa-home', 'admin', '2019-06-14 14:28:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2263, '用户资产', 2262, 1, '#', 'C', '1', '#', '#', 'admin', '2019-06-14 14:30:31', 'admin', '2019-07-01 18:16:16', '');
INSERT INTO `sys_menu` VALUES (2268, '母狗商品', 2263, 1, 'betaHouse/houseProduct', 'C', '1', 'houseProductInfo:view', '#', 'admin', '2019-06-17 10:58:31', 'admin', '2019-06-17 10:59:39', '');
INSERT INTO `sys_menu` VALUES (2269, '母狗商品', 2268, 1, '#', 'F', '1', '#', '#', 'admin', '2019-06-17 11:00:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2270, '小狗商品', 2263, 2, 'betaHouse/houseProduct/housePuppyInfo', 'C', '1', 'housePuppyInfo:view', '#', 'admin', '2019-06-17 12:08:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2271, '资产流水', 2262, 2, 'betaHouse/housePuppy', 'C', '1', 'housePuppy:view', '#', 'admin', '2019-06-17 22:00:23', 'admin', '2019-06-22 14:13:20', '');
INSERT INTO `sys_menu` VALUES (2272, '充币记录', 2262, 3, 'betaHouse/houseComboSet', 'C', '1', 'comboSetInfo:view', '#', 'admin', '2019-06-19 10:41:59', 'admin', '2019-07-03 14:27:51', '');
INSERT INTO `sys_menu` VALUES (2273, '提币记录', 2262, 4, 'betaHouse/house', 'C', '1', 'house:view', '#', 'admin', '2019-06-20 14:08:29', 'admin', '2019-06-20 16:12:43', '');
INSERT INTO `sys_menu` VALUES (2274, '提币审核', 2262, 5, 'betaHouse/houseOrder', 'C', '1', 'houseOrder:view', '#', 'admin', '2019-06-22 00:06:58', 'admin', '2019-06-22 00:15:20', '');
INSERT INTO `sys_menu` VALUES (2275, '拆狗设置', 2047, 5, '/beta/comboGroupSet', 'C', '1', 'comboGroupSet:view', '#', 'admin', '2019-06-22 15:19:47', 'admin', '2019-06-22 15:20:31', '');
INSERT INTO `sys_menu` VALUES (2276, '套餐列表', 2275, 1, '#', 'F', '1', 'comboGroupSet:list', '#', 'admin', '2019-06-22 15:21:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2277, '母狗商品列表', 2268, 1, '#', 'F', '1', 'houseProduct:list', '#', 'admin', '2019-06-23 11:45:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2278, '增加母狗商品', 2268, 2, '#', 'F', '1', 'houseProduct:saveHouseProduct', '#', 'admin', '2019-06-23 11:47:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2279, '保存母狗商品', 2268, 3, '#', 'F', '1', 'houseProduct:updateHouseProduct', '#', 'admin', '2019-06-23 11:48:12', 'admin', '2019-06-23 12:00:00', '');
INSERT INTO `sys_menu` VALUES (2280, '编辑', 2268, 4, '#', 'F', '1', 'houseProduct:edit', '#', 'admin', '2019-06-23 11:59:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2281, '小狗商品详情', 2270, 1, '#', 'F', '1', 'houseProductPuppy:editFull', '#', 'admin', '2019-06-23 12:03:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2282, '保存小狗商品', 2270, 2, '#', 'F', '1', 'houseProduct:updatePuppy', '#', 'admin', '2019-06-23 12:05:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2283, '小狗列表', 2271, 1, '#', 'F', '1', 'housePuppy:list', '#', 'admin', '2019-06-23 12:06:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2284, '狗窝参数列表', 2272, 1, '#', 'F', '1', 'houseComboSet:list', '#', 'admin', '2019-06-23 12:09:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2285, '保存狗窝参数', 2272, 2, '#', 'F', '1', 'houseComboSet:saveHouseComboSet', '#', 'admin', '2019-06-23 12:10:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2286, '狗窝详情', 2272, 3, '#', 'F', '1', 'houseComboSet:edit', '#', 'admin', '2019-06-23 12:10:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2287, '保存狗窝参数设置', 2272, 4, '#', 'F', '1', 'houseComboSet:updateHouseProduct', '#', 'admin', '2019-06-23 12:11:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2288, '删除狗窝的参数', 2272, 5, '#', 'F', '1', 'houseComboSet:delete', '#', 'admin', '2019-06-23 12:11:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2289, '狗窝列表', 2273, 1, '#', 'F', '1', 'house:list', '#', 'admin', '2019-06-23 12:13:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2290, '狗窝背包', 2273, 1, '#', 'F', '1', 'house:backpackInfo', '#', 'admin', '2019-06-23 14:18:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2291, '背包装备', 2273, 3, '#', 'F', '1', 'house:getBackpackList', '#', 'admin', '2019-06-23 14:20:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2292, '母狗列表', 2273, 3, '#', 'F', '1', 'house:feminaList', '#', 'admin', '2019-06-23 14:21:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2293, '商品订单列表', 2274, 1, '#', 'F', '1', 'houseOrder:list', '#', 'admin', '2019-06-23 14:29:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2296, '保存编辑', 2275, 2, '#', 'F', '1', 'comboGroupSet:saveOrUpdate', '#', 'admin', '2019-06-27 22:05:28', 'admin', '2019-06-27 22:06:22', '');
INSERT INTO `sys_menu` VALUES (2297, '打开编辑页面', 2275, 3, '#', 'F', '1', 'comboGroupSet:edit', '#', 'admin', '2019-06-27 22:06:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2298, '商品删除', 2268, 5, '#', 'F', '1', 'deleteHouseProduct', '#', 'admin', '2019-07-01 18:05:09', 'admin', '2019-07-01 18:05:27', '');
INSERT INTO `sys_menu` VALUES (2299, '资金账户币种配置', 2262, 6, 'betaHouse/houseAccountSet', 'C', '1', 'houseAccountSet:view', '#', 'admin', '2019-07-03 11:31:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2300, '挖矿设置列表', 2299, 2, '#', 'F', '1', 'houseAccountSet:list', '#', 'admin', '2019-07-03 11:32:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2301, '提现客服审核', 2007, 4, '/audit/customer', 'C', '0', 'newWithDrawInfo:view', '#', 'admin', '2019-07-11 09:32:21', 'admin', '2019-07-19 16:04:48', '');
INSERT INTO `sys_menu` VALUES (2302, '提现财务审核', 2008, 5, '/audit/finance', 'C', '0', 'financeInfo:view', '#', 'admin', '2019-07-11 09:34:50', 'admin', '2019-08-02 14:26:40', '');
INSERT INTO `sys_menu` VALUES (2304, '已发送短信', 2, 3, 'recordSmsLog/noAdoptSMSList', 'C', '1', 'noAdoptSMSList:list', '#', 'admin', '2019-07-16 17:46:42', 'admin', '2019-07-18 14:46:34', '');
INSERT INTO `sys_menu` VALUES (2305, '资金账户管理', 0, 16, '#', 'M', '0', '', 'fa fa-circle', 'admin', '2019-07-17 16:01:34', 'admin', '2019-07-29 23:41:04', '');
INSERT INTO `sys_menu` VALUES (2306, '资金池统计', 2305, 1, '/funds/capitalPool', 'C', '1', '', '#', 'admin', '2019-07-17 16:53:14', 'admin', '2019-07-17 18:07:30', '');
INSERT INTO `sys_menu` VALUES (2307, '热钱包地址管理', 2008, 2, '/funds/hotAddress', 'C', '0', '', '#', 'admin', '2019-07-17 16:55:10', 'admin', '2019-08-02 15:24:21', '');
INSERT INTO `sys_menu` VALUES (2309, '提现客服列表', 2301, 1, '#', 'F', '1', 'customer:list', '#', 'admin', '2019-07-19 16:09:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2310, '审核通过', 2301, 2, '#', 'F', '1', 'customer:audit', '#', 'admin', '2019-07-19 16:10:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2311, '客服审核拒绝', 2301, 3, '#', 'F', '1', 'customer:refuse', '#', 'admin', '2019-07-19 16:11:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2312, '客服审核通过', 2301, 4, '#', 'F', '1', 'customer:auditPass', '#', 'admin', '2019-07-19 16:12:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2313, '批量拒绝', 2301, 6, '#', 'F', '1', 'customer:refuseAll', '#', 'admin', '2019-07-19 16:12:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2314, '审核通过', 2302, 1, '#', 'F', '1', 'finance:audit', '#', 'admin', '2019-07-19 16:21:51', 'admin', '2019-07-19 16:31:36', '');
INSERT INTO `sys_menu` VALUES (2315, '财务审核拒绝', 2302, 2, '#', 'F', '1', 'customer:refuse', '#', 'admin', '2019-07-19 16:30:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2316, '批量财务审核', 2302, 3, '#', 'F', '1', 'customer:auditPass', '#', 'admin', '2019-07-19 16:32:04', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2317, '批量拒绝', 2302, 1, '#', 'F', '1', 'customer:refuseAll', '#', 'admin', '2019-07-19 16:32:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2318, '冷钱包地址管理', 2008, 3, '/funds/coldAddress', 'C', '0', '', '#', 'admin', '2019-07-20 14:46:07', 'admin', '2019-08-02 15:24:41', '');
INSERT INTO `sys_menu` VALUES (2319, '统计', 0, 9, '#', 'M', '0', '', 'fa fa-bar-chart-o', 'admin', '2019-07-20 17:12:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2320, '实名注册统计', 2319, 1, 'beta/betaStatisticsUser/registeredInfo', 'C', '1', 'registeredInfo:view', '#', 'admin', '2019-07-20 17:21:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2321, '统计列表', 2320, 2, '#', 'F', '1', 'getRegistereList:list', '#', 'admin', '2019-07-20 17:22:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2322, '用户资产管理', 2305, 5, 'userWallet/walletFundAccount', 'C', '1', 'walletFundAccountInfo:view', '#', 'admin', '2019-07-22 10:47:33', 'admin', '2019-08-01 17:53:45', '');
INSERT INTO `sys_menu` VALUES (2323, '用户钱包', 0, 4, '#', '-', '0', '', '#', 'admin', '2019-07-22 18:12:45', 'admin', '2019-07-22 19:59:50', '');
INSERT INTO `sys_menu` VALUES (2324, '充值记录', 2323, 1, '/userWallet/recharge1', 'C', '1', '', '#', 'admin', '2019-07-22 18:13:49', 'admin', '2019-07-22 20:00:21', '');
INSERT INTO `sys_menu` VALUES (2325, '提币记录', 2323, 2, '/userWallet/withdraw', 'C', '1', '', '#', 'admin', '2019-07-22 18:14:24', 'admin', '2019-07-22 20:00:35', '');
INSERT INTO `sys_menu` VALUES (2329, '客户投诉处理', 2119, 6, 'currencyConfig/accountManage', 'C', '1', 'accountManageInfo:view', '#', 'admin', '2019-07-25 14:46:45', 'admin', '2019-07-25 16:00:32', '');
INSERT INTO `sys_menu` VALUES (2330, '划转配置', 2119, 8, 'currencyConfig/transferDirection', 'C', '0', 'transferDirection:view', '#', 'admin', '2019-07-25 14:47:10', 'admin', '2019-07-25 21:14:22', '');
INSERT INTO `sys_menu` VALUES (2331, 'beta账户', 2124, 12, 'currencyConfig/accountManage/betaAccountManageInfo', 'C', '0', 'betaAccountManageInfo:view', '#', 'admin', '2019-07-25 14:48:12', 'admin', '2019-07-25 23:10:21', '');
INSERT INTO `sys_menu` VALUES (2332, '币种配置', 0, 2, '#', 'M', '0', '', 'fa fa-reorder', 'admin', '2019-07-25 15:10:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2333, '单币种提币配置', 2332, 5, '/config/withdrawCurrencyConfig', 'C', '1', 'config:withdrawCurrencyConfig:view', '#', 'admin', '2019-07-25 15:11:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2334, '等级提币限额配置', 2332, 6, '/config/gradedWithdrawalLimit', 'C', '1', 'config:gradedWithdrawalLimit:view', '#', 'admin', '2019-07-25 15:12:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2335, '列表', 2333, 1, '#', 'F', '1', 'config:withdrawCurrencyConfig:list', '#', 'admin', '2019-07-25 15:13:53', 'admin', '2019-07-25 15:16:40', '');
INSERT INTO `sys_menu` VALUES (2336, '添加', 2333, 2, '#', 'F', '1', 'config:withdrawCurrencyConfig:add', '#', 'admin', '2019-07-25 15:14:19', 'admin', '2019-07-25 15:16:49', '');
INSERT INTO `sys_menu` VALUES (2337, '修改', 2333, 3, '#', 'F', '1', 'config:withdrawCurrencyConfig:edit', '#', 'admin', '2019-07-25 15:14:53', 'admin', '2019-07-25 15:16:59', '');
INSERT INTO `sys_menu` VALUES (2338, '删除', 2333, 4, '#', 'F', '1', 'config:withdrawCurrencyConfig:remove', '#', 'admin', '2019-07-25 15:16:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2339, '列表', 2334, 1, '#', 'F', '1', 'config:gradedWithdrawalLimit:list', '#', 'admin', '2019-07-25 15:18:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2340, '增加', 2334, 2, '#', 'F', '1', 'config:gradedWithdrawalLimit:add', '#', 'admin', '2019-07-25 17:45:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2341, '修改', 2334, 3, '#', 'F', '1', 'config:gradedWithdrawalLimit:edit', '#', 'admin', '2019-07-25 17:46:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2342, '删除', 2334, 4, '#', 'F', '1', 'config:gradedWithdrawalLimit:remove', '#', 'admin', '2019-07-25 17:47:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2343, 'beta划转配置', 2124, 13, 'currencyConfig/transferDirection/betaTransferDirection', 'C', '0', 'betaTransferDirection:view', '#', 'admin', '2019-07-25 23:38:49', 'admin', '2019-07-25 23:59:48', '');
INSERT INTO `sys_menu` VALUES (2344, '用户充值记录', 2305, 6, 'userWallet/recharge1', 'C', '1', '', '#', 'admin', '2019-07-26 15:08:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2345, '用户提币记录', 2305, 7, '/userWallet/withdraw', 'C', '1', '', '#', 'admin', '2019-07-26 15:10:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2346, '交易对列表', 2047, 6, 'config/tradingOnConfig', 'C', '1', 'config:tradingOn:view', '#', 'admin', '2019-07-26 17:51:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2347, '资金账户', 2305, 8, 'currencyConfig/accountManage/capitalAccountManageInfo', 'C', '1', 'capitalAccountManageInfo:view', '#', 'admin', '2019-07-26 22:08:08', 'admin', '2019-07-26 22:20:58', '');
INSERT INTO `sys_menu` VALUES (2349, '资金划转配置', 2305, 9, 'currencyConfig/transferDirection/capitalTransferDirection', 'C', '1', 'capitalTransferDirection:view', '#', 'admin', '2019-07-26 22:35:42', 'admin', '2019-07-26 22:40:34', '');
INSERT INTO `sys_menu` VALUES (2350, '母狗设置', 2124, 14, '/beta/feminaImg/edit', 'C', '0', 'beta:feminaImg:edit', '#', 'admin', '2019-07-29 16:23:39', 'admin', '2019-07-29 22:22:10', '');
INSERT INTO `sys_menu` VALUES (2351, '放狗撤回权限', 2136, 5, '#', 'F', '1', 'processedaOrder:updateputTheDog', '#', 'admin', '2019-07-30 00:29:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2352, '拒绝放狗撤回权限', 2136, 6, '#', 'F', '1', 'processedaOrder:updateRecall', '#', 'admin', '2019-07-30 00:30:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2353, '手动刷新团队人数', 2031, 5, '#', 'F', '1', 'user:refreshTeamCount', '#', 'admin', '2019-07-30 00:42:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2354, '区域码列表', 2213, 5, '#', 'F', '1', 'config:countryCode:list', '#', 'admin', '2019-07-31 15:24:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2355, '资金出金记录', 2008, 2, 'finance/withdrawWalletBill', 'C', '0', 'finance:withdrawWalletBill:view', '#', 'admin', '2019-07-31 16:22:02', 'admin', '2019-08-01 17:54:41', '');
INSERT INTO `sys_menu` VALUES (2356, '资金入金记录', 2008, 3, 'finance/withdrawWalletBill/withdrawRechargeListInfo', 'C', '0', 'finance:withdrawRecharge:view', '#', 'admin', '2019-08-01 11:13:12', 'admin', '2019-08-01 17:54:46', '');
INSERT INTO `sys_menu` VALUES (2357, '预约抢狗分析', 2319, 1, 'statistic/reserve/view', 'C', '1', 'statistic:reserve:view', '#', 'admin', '2019-08-01 15:01:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2358, '用户资产管理', 2008, 1, 'userWallet/walletFundAccount', 'C', '0', 'walletFundAccountInfo:view', '#', 'admin', '2019-08-01 17:54:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2361, '资金总额', 2008, 8, 'audit/finance/totalInfo', 'C', '0', 'totalNew:view', '#', 'admin', '2019-08-05 14:34:16', 'admin', '2019-08-05 16:56:04', '');
INSERT INTO `sys_menu` VALUES (2362, '狗窝开通统计', 2319, 2, 'beta/betaStatisticsUser/houseCountInfo', 'C', '1', 'houseCountInfo:view', '#', 'admin', '2019-08-07 14:18:05', 'admin', '2019-08-07 16:32:17', '');
INSERT INTO `sys_menu` VALUES (2363, '冻结资产', 2124, 5, '/beta/betaFreezeAccount', 'C', '0', 'betaFreezeAccount:view', '#', 'admin', '2019-08-08 04:50:04', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2364, '列表', 2363, 1, '#', 'F', '1', 'betaFreezeAccount:list', '#', 'admin', '2019-08-08 04:51:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2365, '手续费管理', 2008, 9, '/audit/finance/feeInfo', 'C', '0', '', '#', 'admin', '2019-08-08 16:01:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2366, '修改用户身份', 2123, 7, '#', 'F', '1', 'personal:updateOldFlag', '#', 'admin', '2019-08-08 22:04:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2367, '禁用交易对', 2346, 1, '#', 'F', '1', 'updateTradingOn:update', '#', 'admin', '2019-08-13 11:47:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2368, '初始化交易对编辑', 2346, 2, '#', 'F', '1', 'config:parameterConfig:editInfo', '#', 'admin', '2019-08-13 11:48:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2369, '保存编辑的交易对', 2346, 3, '#', 'F', '1', 'config:parameterConfig:updateTradingOns', '#', 'admin', '2019-08-13 11:49:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2370, '交易对管理列表', 2346, 5, '#', 'F', '1', 'config:tradingOn:list', '#', 'admin', '2019-08-13 11:55:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2371, '导出', 2123, 8, '#', 'F', '1', 'personal:personalCard:export', '#', 'admin', '2019-08-13 14:07:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2372, '冻结资产分析', 2319, 3, 'statistic/freeze/view', 'C', '1', 'statistic:freeze:view', '#', 'admin', '2019-08-13 16:33:43', 'admin', '2019-08-13 16:34:17', '');
INSERT INTO `sys_menu` VALUES (2373, '导出', 2132, 6, '#', 'F', '1', 'betaAccount:export', '#', 'admin', '2019-08-13 16:35:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2374, '导出', 2363, 2, '#', 'F', '1', 'betaFreezeAccount:export', '#', 'admin', '2019-08-13 16:36:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2375, '打包出售审核', 2124, 15, 'beta/betaOrder/auditListView', 'C', '0', 'betaOrder:auditList', '#', 'admin', '2019-08-14 10:11:40', 'admin', '2019-08-14 15:06:02', '');
INSERT INTO `sys_menu` VALUES (2376, '列表', 2375, 1, '#', 'F', '1', 'betaOrder:auditList', '#', 'admin', '2019-08-14 10:13:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2377, '打包出售审核', 2375, 2, '#', 'F', '1', 'betaOrder:packAudit', '#', 'admin', '2019-08-14 10:15:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2378, '人群画像分析', 2319, 10, '/portrait/view', 'C', '1', 'portrait:view', '#', 'admin', '2019-08-14 15:15:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2379, '导入', 2132, 7, '#', 'F', '1', 'betaAccount:importExcel', '#', 'admin', '2019-08-15 21:16:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2380, '数字资产', 2124, 6, 'beta/betaLockedAccount', 'C', '0', 'betaLockedAccountInfo:view', '#', 'admin', '2019-08-16 16:56:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2381, '公狗优先级导入', 2128, 11, '#', 'F', '1', 'betaMale:importExcelWaitSum', '#', 'admin', '2019-08-17 23:33:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2382, '重置优先级', 2128, 12, '#', 'F', '1', 'betaMale:clearWaitSum', '#', 'admin', '2019-08-18 01:31:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2383, '资产划转设置', 2262, 7, 'betaHouse/houseAccountSet', 'C', '1', 'houseAccountSet:view', '#', 'admin', '2019-07-03 11:31:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2384, '划转记录', 2262, 8, 'betaHouse/houseAccountSet', 'C', '1', 'houseAccountSet:view', '#', 'admin', '2019-07-03 11:31:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2385, '参数配置', 2262, 9, 'betaHouse/houseAccountSet', 'C', '1', 'houseAccountSet:view', '#', 'admin', '2019-07-03 11:31:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2386, '公告管理', 1, 3, '/system/user', 'C', '1', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2019-05-16 17:31:36', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (2387, '运营管理', 1, 3, '/system/user', 'C', '1', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2019-05-16 17:31:36', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (2388, 'Banner管理', 2010, 2, '/content/banner', 'C', '1', 'content:banner:view', '#', 'admin', '2018-11-22 14:21:24', '', NULL, '');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `status` int(1) NULL DEFAULT 1 COMMENT '操作状态（1正常 0异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` tinyint(1) NULL DEFAULT 1 COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1:正常,0停用',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '1:存在 0：无效',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, 1, 1, 1, 'admin', '2018-03-16 11:33:00', 'lyu', '2019-06-15 04:38:25', '管理员');
INSERT INTO `sys_role` VALUES (11, '产品经理', '8638add7-6871-4f62-b2d5-223cfc2cd779', 1, 1, 1, 1, 'admin', '2018-12-11 09:55:24', 'admin', '2019-07-30 15:05:03', NULL);
INSERT INTO `sys_role` VALUES (12, '审核经理', '36998699-6df8-41fa-bbb9-2481f6a81e08', 1, 1, 1, 1, 'admin', '2018-12-12 09:19:44', 'admin', '2019-07-30 15:02:43', NULL);
INSERT INTO `sys_role` VALUES (13, '计划经理', '2c610601-e53a-4506-af3d-2bf9ff430702', 1, 1, 1, 1, 'admin', '2019-04-12 15:47:27', 'admin', '2019-07-30 14:49:59', NULL);
INSERT INTO `sys_role` VALUES (14, '客服文员', '46e7538b-3d56-4fec-a81f-e61095f61262', 1, 1, 1, 1, 'admin', '2019-05-20 20:58:12', 'admin', '2019-07-30 15:03:11', NULL);
INSERT INTO `sys_role` VALUES (15, '实审文员', 'ccdff5ad-e209-4402-a269-7463987c04b6', 1, 1, 1, 1, 'admin', '2019-05-22 18:47:27', 'admin', '2019-07-22 11:34:45', NULL);
INSERT INTO `sys_role` VALUES (16, '客诉文员', 'cfc0fc50-cc8c-4537-8a7b-194860b85434', 1, 1, 1, 1, 'admin', '2019-06-09 00:53:30', 'admin', '2019-07-30 00:28:02', NULL);
INSERT INTO `sys_role` VALUES (17, '运营经理', 'a64ffe39-3851-41a2-96bd-2783a250529f', 1, 1, 1, 1, 'admin', '2019-07-25 13:19:30', 'admin', '2019-07-30 15:04:30', NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dept_id` int(11) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 109);
INSERT INTO `sys_role_menu` VALUES (1, 112);
INSERT INTO `sys_role_menu` VALUES (1, 500);
INSERT INTO `sys_role_menu` VALUES (1, 501);
INSERT INTO `sys_role_menu` VALUES (1, 1038);
INSERT INTO `sys_role_menu` VALUES (1, 1039);
INSERT INTO `sys_role_menu` VALUES (1, 1040);
INSERT INTO `sys_role_menu` VALUES (1, 1041);
INSERT INTO `sys_role_menu` VALUES (1, 1042);
INSERT INTO `sys_role_menu` VALUES (1, 1043);
INSERT INTO `sys_role_menu` VALUES (1, 1044);
INSERT INTO `sys_role_menu` VALUES (1, 1045);
INSERT INTO `sys_role_menu` VALUES (1, 1046);
INSERT INTO `sys_role_menu` VALUES (1, 1047);
INSERT INTO `sys_role_menu` VALUES (1, 2002);
INSERT INTO `sys_role_menu` VALUES (1, 2004);
INSERT INTO `sys_role_menu` VALUES (1, 2005);
INSERT INTO `sys_role_menu` VALUES (1, 2006);
INSERT INTO `sys_role_menu` VALUES (1, 2007);
INSERT INTO `sys_role_menu` VALUES (1, 2008);
INSERT INTO `sys_role_menu` VALUES (1, 2010);
INSERT INTO `sys_role_menu` VALUES (1, 2011);
INSERT INTO `sys_role_menu` VALUES (1, 2012);
INSERT INTO `sys_role_menu` VALUES (1, 2013);
INSERT INTO `sys_role_menu` VALUES (1, 2014);
INSERT INTO `sys_role_menu` VALUES (1, 2015);
INSERT INTO `sys_role_menu` VALUES (1, 2016);
INSERT INTO `sys_role_menu` VALUES (1, 2017);
INSERT INTO `sys_role_menu` VALUES (1, 2018);
INSERT INTO `sys_role_menu` VALUES (1, 2019);
INSERT INTO `sys_role_menu` VALUES (1, 2020);
INSERT INTO `sys_role_menu` VALUES (1, 2021);
INSERT INTO `sys_role_menu` VALUES (1, 2022);
INSERT INTO `sys_role_menu` VALUES (1, 2023);
INSERT INTO `sys_role_menu` VALUES (1, 2024);
INSERT INTO `sys_role_menu` VALUES (1, 2025);
INSERT INTO `sys_role_menu` VALUES (1, 2028);
INSERT INTO `sys_role_menu` VALUES (1, 2029);
INSERT INTO `sys_role_menu` VALUES (1, 2030);
INSERT INTO `sys_role_menu` VALUES (1, 2031);
INSERT INTO `sys_role_menu` VALUES (1, 2032);
INSERT INTO `sys_role_menu` VALUES (1, 2033);
INSERT INTO `sys_role_menu` VALUES (1, 2034);
INSERT INTO `sys_role_menu` VALUES (1, 2035);
INSERT INTO `sys_role_menu` VALUES (1, 2036);
INSERT INTO `sys_role_menu` VALUES (1, 2037);
INSERT INTO `sys_role_menu` VALUES (1, 2038);
INSERT INTO `sys_role_menu` VALUES (1, 2039);
INSERT INTO `sys_role_menu` VALUES (1, 2040);
INSERT INTO `sys_role_menu` VALUES (1, 2041);
INSERT INTO `sys_role_menu` VALUES (1, 2042);
INSERT INTO `sys_role_menu` VALUES (1, 2043);
INSERT INTO `sys_role_menu` VALUES (1, 2044);
INSERT INTO `sys_role_menu` VALUES (1, 2045);
INSERT INTO `sys_role_menu` VALUES (1, 2046);
INSERT INTO `sys_role_menu` VALUES (1, 2047);
INSERT INTO `sys_role_menu` VALUES (1, 2048);
INSERT INTO `sys_role_menu` VALUES (1, 2049);
INSERT INTO `sys_role_menu` VALUES (1, 2050);
INSERT INTO `sys_role_menu` VALUES (1, 2051);
INSERT INTO `sys_role_menu` VALUES (1, 2052);
INSERT INTO `sys_role_menu` VALUES (1, 2053);
INSERT INTO `sys_role_menu` VALUES (1, 2055);
INSERT INTO `sys_role_menu` VALUES (1, 2056);
INSERT INTO `sys_role_menu` VALUES (1, 2057);
INSERT INTO `sys_role_menu` VALUES (1, 2058);
INSERT INTO `sys_role_menu` VALUES (1, 2059);
INSERT INTO `sys_role_menu` VALUES (1, 2060);
INSERT INTO `sys_role_menu` VALUES (1, 2061);
INSERT INTO `sys_role_menu` VALUES (1, 2062);
INSERT INTO `sys_role_menu` VALUES (1, 2063);
INSERT INTO `sys_role_menu` VALUES (1, 2064);
INSERT INTO `sys_role_menu` VALUES (1, 2065);
INSERT INTO `sys_role_menu` VALUES (1, 2066);
INSERT INTO `sys_role_menu` VALUES (1, 2067);
INSERT INTO `sys_role_menu` VALUES (1, 2068);
INSERT INTO `sys_role_menu` VALUES (1, 2069);
INSERT INTO `sys_role_menu` VALUES (1, 2070);
INSERT INTO `sys_role_menu` VALUES (1, 2071);
INSERT INTO `sys_role_menu` VALUES (1, 2072);
INSERT INTO `sys_role_menu` VALUES (1, 2073);
INSERT INTO `sys_role_menu` VALUES (1, 2074);
INSERT INTO `sys_role_menu` VALUES (1, 2075);
INSERT INTO `sys_role_menu` VALUES (1, 2076);
INSERT INTO `sys_role_menu` VALUES (1, 2077);
INSERT INTO `sys_role_menu` VALUES (1, 2078);
INSERT INTO `sys_role_menu` VALUES (1, 2079);
INSERT INTO `sys_role_menu` VALUES (1, 2080);
INSERT INTO `sys_role_menu` VALUES (1, 2081);
INSERT INTO `sys_role_menu` VALUES (1, 2082);
INSERT INTO `sys_role_menu` VALUES (1, 2083);
INSERT INTO `sys_role_menu` VALUES (1, 2084);
INSERT INTO `sys_role_menu` VALUES (1, 2085);
INSERT INTO `sys_role_menu` VALUES (1, 2086);
INSERT INTO `sys_role_menu` VALUES (1, 2087);
INSERT INTO `sys_role_menu` VALUES (1, 2088);
INSERT INTO `sys_role_menu` VALUES (1, 2089);
INSERT INTO `sys_role_menu` VALUES (1, 2090);
INSERT INTO `sys_role_menu` VALUES (1, 2091);
INSERT INTO `sys_role_menu` VALUES (1, 2092);
INSERT INTO `sys_role_menu` VALUES (1, 2093);
INSERT INTO `sys_role_menu` VALUES (1, 2094);
INSERT INTO `sys_role_menu` VALUES (1, 2095);
INSERT INTO `sys_role_menu` VALUES (1, 2096);
INSERT INTO `sys_role_menu` VALUES (1, 2097);
INSERT INTO `sys_role_menu` VALUES (1, 2098);
INSERT INTO `sys_role_menu` VALUES (1, 2099);
INSERT INTO `sys_role_menu` VALUES (1, 2100);
INSERT INTO `sys_role_menu` VALUES (1, 2101);
INSERT INTO `sys_role_menu` VALUES (1, 2102);
INSERT INTO `sys_role_menu` VALUES (1, 2103);
INSERT INTO `sys_role_menu` VALUES (1, 2104);
INSERT INTO `sys_role_menu` VALUES (1, 2105);
INSERT INTO `sys_role_menu` VALUES (1, 2106);
INSERT INTO `sys_role_menu` VALUES (1, 2107);
INSERT INTO `sys_role_menu` VALUES (1, 2108);
INSERT INTO `sys_role_menu` VALUES (1, 2109);
INSERT INTO `sys_role_menu` VALUES (1, 2110);
INSERT INTO `sys_role_menu` VALUES (1, 2111);
INSERT INTO `sys_role_menu` VALUES (1, 2112);
INSERT INTO `sys_role_menu` VALUES (1, 2113);
INSERT INTO `sys_role_menu` VALUES (1, 2114);
INSERT INTO `sys_role_menu` VALUES (1, 2115);
INSERT INTO `sys_role_menu` VALUES (1, 2119);
INSERT INTO `sys_role_menu` VALUES (1, 2120);
INSERT INTO `sys_role_menu` VALUES (1, 2122);
INSERT INTO `sys_role_menu` VALUES (1, 2123);
INSERT INTO `sys_role_menu` VALUES (1, 2124);
INSERT INTO `sys_role_menu` VALUES (1, 2127);
INSERT INTO `sys_role_menu` VALUES (1, 2128);
INSERT INTO `sys_role_menu` VALUES (1, 2131);
INSERT INTO `sys_role_menu` VALUES (1, 2132);
INSERT INTO `sys_role_menu` VALUES (1, 2135);
INSERT INTO `sys_role_menu` VALUES (1, 2136);
INSERT INTO `sys_role_menu` VALUES (1, 2139);
INSERT INTO `sys_role_menu` VALUES (1, 2147);
INSERT INTO `sys_role_menu` VALUES (1, 2148);
INSERT INTO `sys_role_menu` VALUES (1, 2149);
INSERT INTO `sys_role_menu` VALUES (1, 2150);
INSERT INTO `sys_role_menu` VALUES (1, 2151);
INSERT INTO `sys_role_menu` VALUES (1, 2152);
INSERT INTO `sys_role_menu` VALUES (1, 2153);
INSERT INTO `sys_role_menu` VALUES (1, 2154);
INSERT INTO `sys_role_menu` VALUES (1, 2155);
INSERT INTO `sys_role_menu` VALUES (1, 2156);
INSERT INTO `sys_role_menu` VALUES (1, 2157);
INSERT INTO `sys_role_menu` VALUES (1, 2158);
INSERT INTO `sys_role_menu` VALUES (1, 2159);
INSERT INTO `sys_role_menu` VALUES (1, 2160);
INSERT INTO `sys_role_menu` VALUES (1, 2161);
INSERT INTO `sys_role_menu` VALUES (1, 2162);
INSERT INTO `sys_role_menu` VALUES (1, 2163);
INSERT INTO `sys_role_menu` VALUES (1, 2164);
INSERT INTO `sys_role_menu` VALUES (1, 2169);
INSERT INTO `sys_role_menu` VALUES (1, 2170);
INSERT INTO `sys_role_menu` VALUES (1, 2178);
INSERT INTO `sys_role_menu` VALUES (1, 2179);
INSERT INTO `sys_role_menu` VALUES (1, 2180);
INSERT INTO `sys_role_menu` VALUES (1, 2181);
INSERT INTO `sys_role_menu` VALUES (1, 2182);
INSERT INTO `sys_role_menu` VALUES (1, 2188);
INSERT INTO `sys_role_menu` VALUES (1, 2189);
INSERT INTO `sys_role_menu` VALUES (1, 2190);
INSERT INTO `sys_role_menu` VALUES (1, 2191);
INSERT INTO `sys_role_menu` VALUES (1, 2192);
INSERT INTO `sys_role_menu` VALUES (1, 2193);
INSERT INTO `sys_role_menu` VALUES (1, 2194);
INSERT INTO `sys_role_menu` VALUES (1, 2195);
INSERT INTO `sys_role_menu` VALUES (1, 2196);
INSERT INTO `sys_role_menu` VALUES (1, 2197);
INSERT INTO `sys_role_menu` VALUES (1, 2198);
INSERT INTO `sys_role_menu` VALUES (1, 2199);
INSERT INTO `sys_role_menu` VALUES (1, 2200);
INSERT INTO `sys_role_menu` VALUES (1, 2202);
INSERT INTO `sys_role_menu` VALUES (1, 2205);
INSERT INTO `sys_role_menu` VALUES (1, 2207);
INSERT INTO `sys_role_menu` VALUES (1, 2208);
INSERT INTO `sys_role_menu` VALUES (1, 2209);
INSERT INTO `sys_role_menu` VALUES (1, 2210);
INSERT INTO `sys_role_menu` VALUES (1, 2211);
INSERT INTO `sys_role_menu` VALUES (1, 2212);
INSERT INTO `sys_role_menu` VALUES (1, 2229);
INSERT INTO `sys_role_menu` VALUES (1, 2230);
INSERT INTO `sys_role_menu` VALUES (1, 2231);
INSERT INTO `sys_role_menu` VALUES (1, 2232);
INSERT INTO `sys_role_menu` VALUES (1, 2233);
INSERT INTO `sys_role_menu` VALUES (1, 2235);
INSERT INTO `sys_role_menu` VALUES (1, 2238);
INSERT INTO `sys_role_menu` VALUES (1, 2240);
INSERT INTO `sys_role_menu` VALUES (1, 2241);
INSERT INTO `sys_role_menu` VALUES (1, 2242);
INSERT INTO `sys_role_menu` VALUES (1, 2247);
INSERT INTO `sys_role_menu` VALUES (1, 2248);
INSERT INTO `sys_role_menu` VALUES (1, 2251);
INSERT INTO `sys_role_menu` VALUES (1, 2252);
INSERT INTO `sys_role_menu` VALUES (1, 2253);
INSERT INTO `sys_role_menu` VALUES (1, 2254);
INSERT INTO `sys_role_menu` VALUES (1, 2255);
INSERT INTO `sys_role_menu` VALUES (1, 2256);
INSERT INTO `sys_role_menu` VALUES (1, 2257);
INSERT INTO `sys_role_menu` VALUES (1, 2258);
INSERT INTO `sys_role_menu` VALUES (1, 2259);
INSERT INTO `sys_role_menu` VALUES (1, 2260);
INSERT INTO `sys_role_menu` VALUES (1, 2304);
INSERT INTO `sys_role_menu` VALUES (5, 1);
INSERT INTO `sys_role_menu` VALUES (5, 100);
INSERT INTO `sys_role_menu` VALUES (5, 1000);
INSERT INTO `sys_role_menu` VALUES (5, 1001);
INSERT INTO `sys_role_menu` VALUES (5, 1002);
INSERT INTO `sys_role_menu` VALUES (5, 2007);
INSERT INTO `sys_role_menu` VALUES (5, 2012);
INSERT INTO `sys_role_menu` VALUES (5, 2016);
INSERT INTO `sys_role_menu` VALUES (5, 2017);
INSERT INTO `sys_role_menu` VALUES (5, 2019);
INSERT INTO `sys_role_menu` VALUES (5, 2020);
INSERT INTO `sys_role_menu` VALUES (5, 2021);
INSERT INTO `sys_role_menu` VALUES (5, 2024);
INSERT INTO `sys_role_menu` VALUES (5, 2043);
INSERT INTO `sys_role_menu` VALUES (5, 2044);
INSERT INTO `sys_role_menu` VALUES (5, 2045);
INSERT INTO `sys_role_menu` VALUES (6, 2007);
INSERT INTO `sys_role_menu` VALUES (6, 2012);
INSERT INTO `sys_role_menu` VALUES (6, 2016);
INSERT INTO `sys_role_menu` VALUES (6, 2017);
INSERT INTO `sys_role_menu` VALUES (6, 2019);
INSERT INTO `sys_role_menu` VALUES (6, 2020);
INSERT INTO `sys_role_menu` VALUES (6, 2021);
INSERT INTO `sys_role_menu` VALUES (6, 2024);
INSERT INTO `sys_role_menu` VALUES (6, 2043);
INSERT INTO `sys_role_menu` VALUES (6, 2044);
INSERT INTO `sys_role_menu` VALUES (6, 2045);
INSERT INTO `sys_role_menu` VALUES (7, 1);
INSERT INTO `sys_role_menu` VALUES (7, 2);
INSERT INTO `sys_role_menu` VALUES (7, 100);
INSERT INTO `sys_role_menu` VALUES (7, 101);
INSERT INTO `sys_role_menu` VALUES (7, 109);
INSERT INTO `sys_role_menu` VALUES (7, 500);
INSERT INTO `sys_role_menu` VALUES (7, 501);
INSERT INTO `sys_role_menu` VALUES (7, 1000);
INSERT INTO `sys_role_menu` VALUES (7, 1001);
INSERT INTO `sys_role_menu` VALUES (7, 1002);
INSERT INTO `sys_role_menu` VALUES (7, 1003);
INSERT INTO `sys_role_menu` VALUES (7, 1005);
INSERT INTO `sys_role_menu` VALUES (7, 1006);
INSERT INTO `sys_role_menu` VALUES (7, 1007);
INSERT INTO `sys_role_menu` VALUES (7, 1008);
INSERT INTO `sys_role_menu` VALUES (7, 1009);
INSERT INTO `sys_role_menu` VALUES (7, 1038);
INSERT INTO `sys_role_menu` VALUES (7, 1039);
INSERT INTO `sys_role_menu` VALUES (7, 1040);
INSERT INTO `sys_role_menu` VALUES (7, 1041);
INSERT INTO `sys_role_menu` VALUES (7, 1042);
INSERT INTO `sys_role_menu` VALUES (7, 1043);
INSERT INTO `sys_role_menu` VALUES (7, 1044);
INSERT INTO `sys_role_menu` VALUES (7, 1045);
INSERT INTO `sys_role_menu` VALUES (7, 1046);
INSERT INTO `sys_role_menu` VALUES (7, 1047);
INSERT INTO `sys_role_menu` VALUES (7, 2002);
INSERT INTO `sys_role_menu` VALUES (7, 2005);
INSERT INTO `sys_role_menu` VALUES (7, 2006);
INSERT INTO `sys_role_menu` VALUES (7, 2007);
INSERT INTO `sys_role_menu` VALUES (7, 2008);
INSERT INTO `sys_role_menu` VALUES (7, 2009);
INSERT INTO `sys_role_menu` VALUES (7, 2010);
INSERT INTO `sys_role_menu` VALUES (7, 2011);
INSERT INTO `sys_role_menu` VALUES (7, 2012);
INSERT INTO `sys_role_menu` VALUES (7, 2013);
INSERT INTO `sys_role_menu` VALUES (7, 2014);
INSERT INTO `sys_role_menu` VALUES (7, 2015);
INSERT INTO `sys_role_menu` VALUES (7, 2016);
INSERT INTO `sys_role_menu` VALUES (7, 2017);
INSERT INTO `sys_role_menu` VALUES (7, 2018);
INSERT INTO `sys_role_menu` VALUES (7, 2019);
INSERT INTO `sys_role_menu` VALUES (7, 2020);
INSERT INTO `sys_role_menu` VALUES (7, 2021);
INSERT INTO `sys_role_menu` VALUES (7, 2022);
INSERT INTO `sys_role_menu` VALUES (7, 2023);
INSERT INTO `sys_role_menu` VALUES (7, 2024);
INSERT INTO `sys_role_menu` VALUES (7, 2025);
INSERT INTO `sys_role_menu` VALUES (7, 2026);
INSERT INTO `sys_role_menu` VALUES (7, 2027);
INSERT INTO `sys_role_menu` VALUES (7, 2028);
INSERT INTO `sys_role_menu` VALUES (7, 2029);
INSERT INTO `sys_role_menu` VALUES (7, 2030);
INSERT INTO `sys_role_menu` VALUES (7, 2031);
INSERT INTO `sys_role_menu` VALUES (7, 2032);
INSERT INTO `sys_role_menu` VALUES (7, 2033);
INSERT INTO `sys_role_menu` VALUES (7, 2034);
INSERT INTO `sys_role_menu` VALUES (7, 2035);
INSERT INTO `sys_role_menu` VALUES (7, 2036);
INSERT INTO `sys_role_menu` VALUES (7, 2037);
INSERT INTO `sys_role_menu` VALUES (7, 2038);
INSERT INTO `sys_role_menu` VALUES (7, 2039);
INSERT INTO `sys_role_menu` VALUES (7, 2043);
INSERT INTO `sys_role_menu` VALUES (7, 2044);
INSERT INTO `sys_role_menu` VALUES (7, 2045);
INSERT INTO `sys_role_menu` VALUES (7, 2047);
INSERT INTO `sys_role_menu` VALUES (7, 2048);
INSERT INTO `sys_role_menu` VALUES (7, 2049);
INSERT INTO `sys_role_menu` VALUES (7, 2050);
INSERT INTO `sys_role_menu` VALUES (7, 2051);
INSERT INTO `sys_role_menu` VALUES (7, 2052);
INSERT INTO `sys_role_menu` VALUES (7, 2053);
INSERT INTO `sys_role_menu` VALUES (10, 1);
INSERT INTO `sys_role_menu` VALUES (10, 100);
INSERT INTO `sys_role_menu` VALUES (10, 101);
INSERT INTO `sys_role_menu` VALUES (10, 102);
INSERT INTO `sys_role_menu` VALUES (10, 103);
INSERT INTO `sys_role_menu` VALUES (10, 105);
INSERT INTO `sys_role_menu` VALUES (10, 1000);
INSERT INTO `sys_role_menu` VALUES (10, 1001);
INSERT INTO `sys_role_menu` VALUES (10, 1002);
INSERT INTO `sys_role_menu` VALUES (10, 1003);
INSERT INTO `sys_role_menu` VALUES (10, 1004);
INSERT INTO `sys_role_menu` VALUES (10, 1005);
INSERT INTO `sys_role_menu` VALUES (10, 1006);
INSERT INTO `sys_role_menu` VALUES (10, 1007);
INSERT INTO `sys_role_menu` VALUES (10, 1008);
INSERT INTO `sys_role_menu` VALUES (10, 1009);
INSERT INTO `sys_role_menu` VALUES (10, 1010);
INSERT INTO `sys_role_menu` VALUES (10, 1011);
INSERT INTO `sys_role_menu` VALUES (10, 1012);
INSERT INTO `sys_role_menu` VALUES (10, 1013);
INSERT INTO `sys_role_menu` VALUES (10, 1014);
INSERT INTO `sys_role_menu` VALUES (10, 1015);
INSERT INTO `sys_role_menu` VALUES (10, 1016);
INSERT INTO `sys_role_menu` VALUES (10, 1017);
INSERT INTO `sys_role_menu` VALUES (10, 1018);
INSERT INTO `sys_role_menu` VALUES (10, 1024);
INSERT INTO `sys_role_menu` VALUES (10, 1025);
INSERT INTO `sys_role_menu` VALUES (10, 1026);
INSERT INTO `sys_role_menu` VALUES (10, 1027);
INSERT INTO `sys_role_menu` VALUES (10, 1028);
INSERT INTO `sys_role_menu` VALUES (11, 1);
INSERT INTO `sys_role_menu` VALUES (11, 2);
INSERT INTO `sys_role_menu` VALUES (11, 3);
INSERT INTO `sys_role_menu` VALUES (11, 100);
INSERT INTO `sys_role_menu` VALUES (11, 101);
INSERT INTO `sys_role_menu` VALUES (11, 102);
INSERT INTO `sys_role_menu` VALUES (11, 103);
INSERT INTO `sys_role_menu` VALUES (11, 105);
INSERT INTO `sys_role_menu` VALUES (11, 109);
INSERT INTO `sys_role_menu` VALUES (11, 112);
INSERT INTO `sys_role_menu` VALUES (11, 500);
INSERT INTO `sys_role_menu` VALUES (11, 501);
INSERT INTO `sys_role_menu` VALUES (11, 1000);
INSERT INTO `sys_role_menu` VALUES (11, 1001);
INSERT INTO `sys_role_menu` VALUES (11, 1002);
INSERT INTO `sys_role_menu` VALUES (11, 1003);
INSERT INTO `sys_role_menu` VALUES (11, 1004);
INSERT INTO `sys_role_menu` VALUES (11, 1005);
INSERT INTO `sys_role_menu` VALUES (11, 1006);
INSERT INTO `sys_role_menu` VALUES (11, 1007);
INSERT INTO `sys_role_menu` VALUES (11, 1008);
INSERT INTO `sys_role_menu` VALUES (11, 1009);
INSERT INTO `sys_role_menu` VALUES (11, 1010);
INSERT INTO `sys_role_menu` VALUES (11, 1011);
INSERT INTO `sys_role_menu` VALUES (11, 1012);
INSERT INTO `sys_role_menu` VALUES (11, 1013);
INSERT INTO `sys_role_menu` VALUES (11, 1014);
INSERT INTO `sys_role_menu` VALUES (11, 1015);
INSERT INTO `sys_role_menu` VALUES (11, 1016);
INSERT INTO `sys_role_menu` VALUES (11, 1017);
INSERT INTO `sys_role_menu` VALUES (11, 1018);
INSERT INTO `sys_role_menu` VALUES (11, 1024);
INSERT INTO `sys_role_menu` VALUES (11, 1025);
INSERT INTO `sys_role_menu` VALUES (11, 1026);
INSERT INTO `sys_role_menu` VALUES (11, 1027);
INSERT INTO `sys_role_menu` VALUES (11, 1028);
INSERT INTO `sys_role_menu` VALUES (11, 1038);
INSERT INTO `sys_role_menu` VALUES (11, 1039);
INSERT INTO `sys_role_menu` VALUES (11, 1040);
INSERT INTO `sys_role_menu` VALUES (11, 1041);
INSERT INTO `sys_role_menu` VALUES (11, 1042);
INSERT INTO `sys_role_menu` VALUES (11, 1043);
INSERT INTO `sys_role_menu` VALUES (11, 1044);
INSERT INTO `sys_role_menu` VALUES (11, 1045);
INSERT INTO `sys_role_menu` VALUES (11, 1046);
INSERT INTO `sys_role_menu` VALUES (11, 1047);
INSERT INTO `sys_role_menu` VALUES (11, 2002);
INSERT INTO `sys_role_menu` VALUES (11, 2009);
INSERT INTO `sys_role_menu` VALUES (11, 2010);
INSERT INTO `sys_role_menu` VALUES (11, 2013);
INSERT INTO `sys_role_menu` VALUES (11, 2031);
INSERT INTO `sys_role_menu` VALUES (11, 2047);
INSERT INTO `sys_role_menu` VALUES (11, 2048);
INSERT INTO `sys_role_menu` VALUES (11, 2049);
INSERT INTO `sys_role_menu` VALUES (11, 2050);
INSERT INTO `sys_role_menu` VALUES (11, 2051);
INSERT INTO `sys_role_menu` VALUES (11, 2052);
INSERT INTO `sys_role_menu` VALUES (11, 2053);
INSERT INTO `sys_role_menu` VALUES (11, 2055);
INSERT INTO `sys_role_menu` VALUES (11, 2056);
INSERT INTO `sys_role_menu` VALUES (11, 2057);
INSERT INTO `sys_role_menu` VALUES (11, 2058);
INSERT INTO `sys_role_menu` VALUES (11, 2059);
INSERT INTO `sys_role_menu` VALUES (11, 2060);
INSERT INTO `sys_role_menu` VALUES (11, 2077);
INSERT INTO `sys_role_menu` VALUES (11, 2078);
INSERT INTO `sys_role_menu` VALUES (11, 2079);
INSERT INTO `sys_role_menu` VALUES (11, 2080);
INSERT INTO `sys_role_menu` VALUES (11, 2081);
INSERT INTO `sys_role_menu` VALUES (11, 2082);
INSERT INTO `sys_role_menu` VALUES (11, 2083);
INSERT INTO `sys_role_menu` VALUES (11, 2084);
INSERT INTO `sys_role_menu` VALUES (11, 2085);
INSERT INTO `sys_role_menu` VALUES (11, 2086);
INSERT INTO `sys_role_menu` VALUES (11, 2087);
INSERT INTO `sys_role_menu` VALUES (11, 2088);
INSERT INTO `sys_role_menu` VALUES (11, 2089);
INSERT INTO `sys_role_menu` VALUES (11, 2098);
INSERT INTO `sys_role_menu` VALUES (11, 2099);
INSERT INTO `sys_role_menu` VALUES (11, 2100);
INSERT INTO `sys_role_menu` VALUES (11, 2101);
INSERT INTO `sys_role_menu` VALUES (11, 2102);
INSERT INTO `sys_role_menu` VALUES (11, 2103);
INSERT INTO `sys_role_menu` VALUES (11, 2104);
INSERT INTO `sys_role_menu` VALUES (11, 2119);
INSERT INTO `sys_role_menu` VALUES (11, 2120);
INSERT INTO `sys_role_menu` VALUES (11, 2122);
INSERT INTO `sys_role_menu` VALUES (11, 2123);
INSERT INTO `sys_role_menu` VALUES (11, 2124);
INSERT INTO `sys_role_menu` VALUES (11, 2126);
INSERT INTO `sys_role_menu` VALUES (11, 2127);
INSERT INTO `sys_role_menu` VALUES (11, 2128);
INSERT INTO `sys_role_menu` VALUES (11, 2129);
INSERT INTO `sys_role_menu` VALUES (11, 2130);
INSERT INTO `sys_role_menu` VALUES (11, 2131);
INSERT INTO `sys_role_menu` VALUES (11, 2132);
INSERT INTO `sys_role_menu` VALUES (11, 2134);
INSERT INTO `sys_role_menu` VALUES (11, 2135);
INSERT INTO `sys_role_menu` VALUES (11, 2136);
INSERT INTO `sys_role_menu` VALUES (11, 2139);
INSERT INTO `sys_role_menu` VALUES (11, 2147);
INSERT INTO `sys_role_menu` VALUES (11, 2148);
INSERT INTO `sys_role_menu` VALUES (11, 2149);
INSERT INTO `sys_role_menu` VALUES (11, 2150);
INSERT INTO `sys_role_menu` VALUES (11, 2151);
INSERT INTO `sys_role_menu` VALUES (11, 2152);
INSERT INTO `sys_role_menu` VALUES (11, 2153);
INSERT INTO `sys_role_menu` VALUES (11, 2154);
INSERT INTO `sys_role_menu` VALUES (11, 2155);
INSERT INTO `sys_role_menu` VALUES (11, 2158);
INSERT INTO `sys_role_menu` VALUES (11, 2159);
INSERT INTO `sys_role_menu` VALUES (11, 2160);
INSERT INTO `sys_role_menu` VALUES (11, 2161);
INSERT INTO `sys_role_menu` VALUES (11, 2162);
INSERT INTO `sys_role_menu` VALUES (11, 2163);
INSERT INTO `sys_role_menu` VALUES (11, 2164);
INSERT INTO `sys_role_menu` VALUES (11, 2165);
INSERT INTO `sys_role_menu` VALUES (11, 2166);
INSERT INTO `sys_role_menu` VALUES (11, 2167);
INSERT INTO `sys_role_menu` VALUES (11, 2168);
INSERT INTO `sys_role_menu` VALUES (11, 2169);
INSERT INTO `sys_role_menu` VALUES (11, 2170);
INSERT INTO `sys_role_menu` VALUES (11, 2171);
INSERT INTO `sys_role_menu` VALUES (11, 2172);
INSERT INTO `sys_role_menu` VALUES (11, 2173);
INSERT INTO `sys_role_menu` VALUES (11, 2174);
INSERT INTO `sys_role_menu` VALUES (11, 2175);
INSERT INTO `sys_role_menu` VALUES (11, 2176);
INSERT INTO `sys_role_menu` VALUES (11, 2177);
INSERT INTO `sys_role_menu` VALUES (11, 2178);
INSERT INTO `sys_role_menu` VALUES (11, 2179);
INSERT INTO `sys_role_menu` VALUES (11, 2180);
INSERT INTO `sys_role_menu` VALUES (11, 2181);
INSERT INTO `sys_role_menu` VALUES (11, 2182);
INSERT INTO `sys_role_menu` VALUES (11, 2184);
INSERT INTO `sys_role_menu` VALUES (11, 2185);
INSERT INTO `sys_role_menu` VALUES (11, 2186);
INSERT INTO `sys_role_menu` VALUES (11, 2187);
INSERT INTO `sys_role_menu` VALUES (11, 2188);
INSERT INTO `sys_role_menu` VALUES (11, 2189);
INSERT INTO `sys_role_menu` VALUES (11, 2190);
INSERT INTO `sys_role_menu` VALUES (11, 2191);
INSERT INTO `sys_role_menu` VALUES (11, 2192);
INSERT INTO `sys_role_menu` VALUES (11, 2193);
INSERT INTO `sys_role_menu` VALUES (11, 2194);
INSERT INTO `sys_role_menu` VALUES (11, 2195);
INSERT INTO `sys_role_menu` VALUES (11, 2196);
INSERT INTO `sys_role_menu` VALUES (11, 2197);
INSERT INTO `sys_role_menu` VALUES (11, 2198);
INSERT INTO `sys_role_menu` VALUES (11, 2199);
INSERT INTO `sys_role_menu` VALUES (11, 2200);
INSERT INTO `sys_role_menu` VALUES (11, 2202);
INSERT INTO `sys_role_menu` VALUES (11, 2203);
INSERT INTO `sys_role_menu` VALUES (11, 2204);
INSERT INTO `sys_role_menu` VALUES (11, 2205);
INSERT INTO `sys_role_menu` VALUES (11, 2206);
INSERT INTO `sys_role_menu` VALUES (11, 2207);
INSERT INTO `sys_role_menu` VALUES (11, 2208);
INSERT INTO `sys_role_menu` VALUES (11, 2209);
INSERT INTO `sys_role_menu` VALUES (11, 2210);
INSERT INTO `sys_role_menu` VALUES (11, 2211);
INSERT INTO `sys_role_menu` VALUES (11, 2212);
INSERT INTO `sys_role_menu` VALUES (11, 2213);
INSERT INTO `sys_role_menu` VALUES (11, 2214);
INSERT INTO `sys_role_menu` VALUES (11, 2215);
INSERT INTO `sys_role_menu` VALUES (11, 2216);
INSERT INTO `sys_role_menu` VALUES (11, 2217);
INSERT INTO `sys_role_menu` VALUES (11, 2218);
INSERT INTO `sys_role_menu` VALUES (11, 2219);
INSERT INTO `sys_role_menu` VALUES (11, 2220);
INSERT INTO `sys_role_menu` VALUES (11, 2221);
INSERT INTO `sys_role_menu` VALUES (11, 2229);
INSERT INTO `sys_role_menu` VALUES (11, 2230);
INSERT INTO `sys_role_menu` VALUES (11, 2232);
INSERT INTO `sys_role_menu` VALUES (11, 2237);
INSERT INTO `sys_role_menu` VALUES (11, 2238);
INSERT INTO `sys_role_menu` VALUES (11, 2240);
INSERT INTO `sys_role_menu` VALUES (11, 2241);
INSERT INTO `sys_role_menu` VALUES (11, 2242);
INSERT INTO `sys_role_menu` VALUES (11, 2262);
INSERT INTO `sys_role_menu` VALUES (11, 2263);
INSERT INTO `sys_role_menu` VALUES (11, 2268);
INSERT INTO `sys_role_menu` VALUES (11, 2269);
INSERT INTO `sys_role_menu` VALUES (11, 2270);
INSERT INTO `sys_role_menu` VALUES (11, 2271);
INSERT INTO `sys_role_menu` VALUES (11, 2272);
INSERT INTO `sys_role_menu` VALUES (11, 2273);
INSERT INTO `sys_role_menu` VALUES (11, 2274);
INSERT INTO `sys_role_menu` VALUES (11, 2277);
INSERT INTO `sys_role_menu` VALUES (11, 2278);
INSERT INTO `sys_role_menu` VALUES (11, 2279);
INSERT INTO `sys_role_menu` VALUES (11, 2280);
INSERT INTO `sys_role_menu` VALUES (11, 2281);
INSERT INTO `sys_role_menu` VALUES (11, 2282);
INSERT INTO `sys_role_menu` VALUES (11, 2283);
INSERT INTO `sys_role_menu` VALUES (11, 2284);
INSERT INTO `sys_role_menu` VALUES (11, 2285);
INSERT INTO `sys_role_menu` VALUES (11, 2286);
INSERT INTO `sys_role_menu` VALUES (11, 2287);
INSERT INTO `sys_role_menu` VALUES (11, 2288);
INSERT INTO `sys_role_menu` VALUES (11, 2289);
INSERT INTO `sys_role_menu` VALUES (11, 2290);
INSERT INTO `sys_role_menu` VALUES (11, 2291);
INSERT INTO `sys_role_menu` VALUES (11, 2292);
INSERT INTO `sys_role_menu` VALUES (11, 2293);
INSERT INTO `sys_role_menu` VALUES (11, 2298);
INSERT INTO `sys_role_menu` VALUES (11, 2299);
INSERT INTO `sys_role_menu` VALUES (11, 2300);
INSERT INTO `sys_role_menu` VALUES (11, 2301);
INSERT INTO `sys_role_menu` VALUES (12, 2119);
INSERT INTO `sys_role_menu` VALUES (12, 2123);
INSERT INTO `sys_role_menu` VALUES (12, 2124);
INSERT INTO `sys_role_menu` VALUES (12, 2134);
INSERT INTO `sys_role_menu` VALUES (12, 2135);
INSERT INTO `sys_role_menu` VALUES (12, 2136);
INSERT INTO `sys_role_menu` VALUES (12, 2154);
INSERT INTO `sys_role_menu` VALUES (12, 2158);
INSERT INTO `sys_role_menu` VALUES (12, 2159);
INSERT INTO `sys_role_menu` VALUES (12, 2160);
INSERT INTO `sys_role_menu` VALUES (12, 2161);
INSERT INTO `sys_role_menu` VALUES (12, 2162);
INSERT INTO `sys_role_menu` VALUES (12, 2163);
INSERT INTO `sys_role_menu` VALUES (12, 2164);
INSERT INTO `sys_role_menu` VALUES (12, 2165);
INSERT INTO `sys_role_menu` VALUES (12, 2166);
INSERT INTO `sys_role_menu` VALUES (12, 2167);
INSERT INTO `sys_role_menu` VALUES (12, 2168);
INSERT INTO `sys_role_menu` VALUES (12, 2202);
INSERT INTO `sys_role_menu` VALUES (12, 2208);
INSERT INTO `sys_role_menu` VALUES (12, 2229);
INSERT INTO `sys_role_menu` VALUES (12, 2237);
INSERT INTO `sys_role_menu` VALUES (12, 2241);
INSERT INTO `sys_role_menu` VALUES (12, 2250);
INSERT INTO `sys_role_menu` VALUES (12, 2252);
INSERT INTO `sys_role_menu` VALUES (13, 2);
INSERT INTO `sys_role_menu` VALUES (13, 109);
INSERT INTO `sys_role_menu` VALUES (13, 1045);
INSERT INTO `sys_role_menu` VALUES (13, 1046);
INSERT INTO `sys_role_menu` VALUES (13, 1047);
INSERT INTO `sys_role_menu` VALUES (13, 2009);
INSERT INTO `sys_role_menu` VALUES (13, 2031);
INSERT INTO `sys_role_menu` VALUES (13, 2047);
INSERT INTO `sys_role_menu` VALUES (13, 2048);
INSERT INTO `sys_role_menu` VALUES (13, 2049);
INSERT INTO `sys_role_menu` VALUES (13, 2077);
INSERT INTO `sys_role_menu` VALUES (13, 2078);
INSERT INTO `sys_role_menu` VALUES (13, 2079);
INSERT INTO `sys_role_menu` VALUES (13, 2098);
INSERT INTO `sys_role_menu` VALUES (13, 2099);
INSERT INTO `sys_role_menu` VALUES (13, 2103);
INSERT INTO `sys_role_menu` VALUES (13, 2104);
INSERT INTO `sys_role_menu` VALUES (13, 2119);
INSERT INTO `sys_role_menu` VALUES (13, 2120);
INSERT INTO `sys_role_menu` VALUES (13, 2122);
INSERT INTO `sys_role_menu` VALUES (13, 2123);
INSERT INTO `sys_role_menu` VALUES (13, 2124);
INSERT INTO `sys_role_menu` VALUES (13, 2126);
INSERT INTO `sys_role_menu` VALUES (13, 2127);
INSERT INTO `sys_role_menu` VALUES (13, 2128);
INSERT INTO `sys_role_menu` VALUES (13, 2129);
INSERT INTO `sys_role_menu` VALUES (13, 2130);
INSERT INTO `sys_role_menu` VALUES (13, 2131);
INSERT INTO `sys_role_menu` VALUES (13, 2132);
INSERT INTO `sys_role_menu` VALUES (13, 2134);
INSERT INTO `sys_role_menu` VALUES (13, 2135);
INSERT INTO `sys_role_menu` VALUES (13, 2136);
INSERT INTO `sys_role_menu` VALUES (13, 2147);
INSERT INTO `sys_role_menu` VALUES (13, 2149);
INSERT INTO `sys_role_menu` VALUES (13, 2153);
INSERT INTO `sys_role_menu` VALUES (13, 2154);
INSERT INTO `sys_role_menu` VALUES (13, 2158);
INSERT INTO `sys_role_menu` VALUES (13, 2159);
INSERT INTO `sys_role_menu` VALUES (13, 2160);
INSERT INTO `sys_role_menu` VALUES (13, 2161);
INSERT INTO `sys_role_menu` VALUES (13, 2162);
INSERT INTO `sys_role_menu` VALUES (13, 2163);
INSERT INTO `sys_role_menu` VALUES (13, 2164);
INSERT INTO `sys_role_menu` VALUES (13, 2165);
INSERT INTO `sys_role_menu` VALUES (13, 2166);
INSERT INTO `sys_role_menu` VALUES (13, 2167);
INSERT INTO `sys_role_menu` VALUES (13, 2168);
INSERT INTO `sys_role_menu` VALUES (13, 2169);
INSERT INTO `sys_role_menu` VALUES (13, 2170);
INSERT INTO `sys_role_menu` VALUES (13, 2171);
INSERT INTO `sys_role_menu` VALUES (13, 2172);
INSERT INTO `sys_role_menu` VALUES (13, 2173);
INSERT INTO `sys_role_menu` VALUES (13, 2174);
INSERT INTO `sys_role_menu` VALUES (13, 2176);
INSERT INTO `sys_role_menu` VALUES (13, 2178);
INSERT INTO `sys_role_menu` VALUES (13, 2179);
INSERT INTO `sys_role_menu` VALUES (13, 2180);
INSERT INTO `sys_role_menu` VALUES (13, 2181);
INSERT INTO `sys_role_menu` VALUES (13, 2182);
INSERT INTO `sys_role_menu` VALUES (13, 2184);
INSERT INTO `sys_role_menu` VALUES (13, 2188);
INSERT INTO `sys_role_menu` VALUES (13, 2189);
INSERT INTO `sys_role_menu` VALUES (13, 2191);
INSERT INTO `sys_role_menu` VALUES (13, 2192);
INSERT INTO `sys_role_menu` VALUES (13, 2193);
INSERT INTO `sys_role_menu` VALUES (13, 2194);
INSERT INTO `sys_role_menu` VALUES (13, 2195);
INSERT INTO `sys_role_menu` VALUES (13, 2196);
INSERT INTO `sys_role_menu` VALUES (13, 2197);
INSERT INTO `sys_role_menu` VALUES (13, 2198);
INSERT INTO `sys_role_menu` VALUES (13, 2199);
INSERT INTO `sys_role_menu` VALUES (13, 2200);
INSERT INTO `sys_role_menu` VALUES (13, 2202);
INSERT INTO `sys_role_menu` VALUES (13, 2205);
INSERT INTO `sys_role_menu` VALUES (13, 2207);
INSERT INTO `sys_role_menu` VALUES (13, 2208);
INSERT INTO `sys_role_menu` VALUES (13, 2209);
INSERT INTO `sys_role_menu` VALUES (13, 2210);
INSERT INTO `sys_role_menu` VALUES (13, 2211);
INSERT INTO `sys_role_menu` VALUES (13, 2212);
INSERT INTO `sys_role_menu` VALUES (13, 2213);
INSERT INTO `sys_role_menu` VALUES (13, 2214);
INSERT INTO `sys_role_menu` VALUES (13, 2215);
INSERT INTO `sys_role_menu` VALUES (13, 2216);
INSERT INTO `sys_role_menu` VALUES (13, 2217);
INSERT INTO `sys_role_menu` VALUES (13, 2229);
INSERT INTO `sys_role_menu` VALUES (13, 2230);
INSERT INTO `sys_role_menu` VALUES (13, 2231);
INSERT INTO `sys_role_menu` VALUES (13, 2232);
INSERT INTO `sys_role_menu` VALUES (13, 2237);
INSERT INTO `sys_role_menu` VALUES (13, 2238);
INSERT INTO `sys_role_menu` VALUES (13, 2240);
INSERT INTO `sys_role_menu` VALUES (13, 2241);
INSERT INTO `sys_role_menu` VALUES (13, 2242);
INSERT INTO `sys_role_menu` VALUES (13, 2246);
INSERT INTO `sys_role_menu` VALUES (13, 2247);
INSERT INTO `sys_role_menu` VALUES (13, 2249);
INSERT INTO `sys_role_menu` VALUES (13, 2251);
INSERT INTO `sys_role_menu` VALUES (13, 2252);
INSERT INTO `sys_role_menu` VALUES (13, 2253);
INSERT INTO `sys_role_menu` VALUES (13, 2254);
INSERT INTO `sys_role_menu` VALUES (13, 2256);
INSERT INTO `sys_role_menu` VALUES (13, 2258);
INSERT INTO `sys_role_menu` VALUES (13, 2259);
INSERT INTO `sys_role_menu` VALUES (13, 2260);
INSERT INTO `sys_role_menu` VALUES (13, 2262);
INSERT INTO `sys_role_menu` VALUES (13, 2263);
INSERT INTO `sys_role_menu` VALUES (13, 2268);
INSERT INTO `sys_role_menu` VALUES (13, 2269);
INSERT INTO `sys_role_menu` VALUES (13, 2270);
INSERT INTO `sys_role_menu` VALUES (13, 2271);
INSERT INTO `sys_role_menu` VALUES (13, 2272);
INSERT INTO `sys_role_menu` VALUES (13, 2273);
INSERT INTO `sys_role_menu` VALUES (13, 2274);
INSERT INTO `sys_role_menu` VALUES (13, 2275);
INSERT INTO `sys_role_menu` VALUES (13, 2276);
INSERT INTO `sys_role_menu` VALUES (13, 2277);
INSERT INTO `sys_role_menu` VALUES (13, 2278);
INSERT INTO `sys_role_menu` VALUES (13, 2279);
INSERT INTO `sys_role_menu` VALUES (13, 2280);
INSERT INTO `sys_role_menu` VALUES (13, 2281);
INSERT INTO `sys_role_menu` VALUES (13, 2282);
INSERT INTO `sys_role_menu` VALUES (13, 2283);
INSERT INTO `sys_role_menu` VALUES (13, 2284);
INSERT INTO `sys_role_menu` VALUES (13, 2285);
INSERT INTO `sys_role_menu` VALUES (13, 2286);
INSERT INTO `sys_role_menu` VALUES (13, 2287);
INSERT INTO `sys_role_menu` VALUES (13, 2288);
INSERT INTO `sys_role_menu` VALUES (13, 2289);
INSERT INTO `sys_role_menu` VALUES (13, 2290);
INSERT INTO `sys_role_menu` VALUES (13, 2291);
INSERT INTO `sys_role_menu` VALUES (13, 2292);
INSERT INTO `sys_role_menu` VALUES (13, 2293);
INSERT INTO `sys_role_menu` VALUES (13, 2296);
INSERT INTO `sys_role_menu` VALUES (13, 2297);
INSERT INTO `sys_role_menu` VALUES (13, 2298);
INSERT INTO `sys_role_menu` VALUES (13, 2299);
INSERT INTO `sys_role_menu` VALUES (13, 2300);
INSERT INTO `sys_role_menu` VALUES (13, 2301);
INSERT INTO `sys_role_menu` VALUES (13, 2353);
INSERT INTO `sys_role_menu` VALUES (14, 2103);
INSERT INTO `sys_role_menu` VALUES (14, 2104);
INSERT INTO `sys_role_menu` VALUES (14, 2119);
INSERT INTO `sys_role_menu` VALUES (14, 2123);
INSERT INTO `sys_role_menu` VALUES (14, 2124);
INSERT INTO `sys_role_menu` VALUES (14, 2135);
INSERT INTO `sys_role_menu` VALUES (14, 2136);
INSERT INTO `sys_role_menu` VALUES (14, 2154);
INSERT INTO `sys_role_menu` VALUES (14, 2158);
INSERT INTO `sys_role_menu` VALUES (14, 2159);
INSERT INTO `sys_role_menu` VALUES (14, 2160);
INSERT INTO `sys_role_menu` VALUES (14, 2161);
INSERT INTO `sys_role_menu` VALUES (14, 2162);
INSERT INTO `sys_role_menu` VALUES (14, 2163);
INSERT INTO `sys_role_menu` VALUES (14, 2164);
INSERT INTO `sys_role_menu` VALUES (14, 2202);
INSERT INTO `sys_role_menu` VALUES (14, 2208);
INSERT INTO `sys_role_menu` VALUES (14, 2229);
INSERT INTO `sys_role_menu` VALUES (14, 2232);
INSERT INTO `sys_role_menu` VALUES (14, 2237);
INSERT INTO `sys_role_menu` VALUES (14, 2241);
INSERT INTO `sys_role_menu` VALUES (14, 2252);
INSERT INTO `sys_role_menu` VALUES (15, 2119);
INSERT INTO `sys_role_menu` VALUES (15, 2123);
INSERT INTO `sys_role_menu` VALUES (15, 2159);
INSERT INTO `sys_role_menu` VALUES (15, 2162);
INSERT INTO `sys_role_menu` VALUES (15, 2208);
INSERT INTO `sys_role_menu` VALUES (15, 2229);
INSERT INTO `sys_role_menu` VALUES (15, 2237);
INSERT INTO `sys_role_menu` VALUES (15, 2241);
INSERT INTO `sys_role_menu` VALUES (15, 2252);
INSERT INTO `sys_role_menu` VALUES (16, 2124);
INSERT INTO `sys_role_menu` VALUES (16, 2135);
INSERT INTO `sys_role_menu` VALUES (16, 2136);
INSERT INTO `sys_role_menu` VALUES (16, 2154);
INSERT INTO `sys_role_menu` VALUES (16, 2158);
INSERT INTO `sys_role_menu` VALUES (16, 2160);
INSERT INTO `sys_role_menu` VALUES (16, 2161);
INSERT INTO `sys_role_menu` VALUES (16, 2163);
INSERT INTO `sys_role_menu` VALUES (16, 2164);
INSERT INTO `sys_role_menu` VALUES (16, 2202);
INSERT INTO `sys_role_menu` VALUES (17, 2);
INSERT INTO `sys_role_menu` VALUES (17, 109);
INSERT INTO `sys_role_menu` VALUES (17, 500);
INSERT INTO `sys_role_menu` VALUES (17, 501);
INSERT INTO `sys_role_menu` VALUES (17, 1038);
INSERT INTO `sys_role_menu` VALUES (17, 1039);
INSERT INTO `sys_role_menu` VALUES (17, 1040);
INSERT INTO `sys_role_menu` VALUES (17, 1041);
INSERT INTO `sys_role_menu` VALUES (17, 1042);
INSERT INTO `sys_role_menu` VALUES (17, 1043);
INSERT INTO `sys_role_menu` VALUES (17, 1044);
INSERT INTO `sys_role_menu` VALUES (17, 1045);
INSERT INTO `sys_role_menu` VALUES (17, 1046);
INSERT INTO `sys_role_menu` VALUES (17, 1047);
INSERT INTO `sys_role_menu` VALUES (17, 2002);
INSERT INTO `sys_role_menu` VALUES (17, 2009);
INSERT INTO `sys_role_menu` VALUES (17, 2010);
INSERT INTO `sys_role_menu` VALUES (17, 2013);
INSERT INTO `sys_role_menu` VALUES (17, 2031);
INSERT INTO `sys_role_menu` VALUES (17, 2047);
INSERT INTO `sys_role_menu` VALUES (17, 2055);
INSERT INTO `sys_role_menu` VALUES (17, 2056);
INSERT INTO `sys_role_menu` VALUES (17, 2057);
INSERT INTO `sys_role_menu` VALUES (17, 2058);
INSERT INTO `sys_role_menu` VALUES (17, 2059);
INSERT INTO `sys_role_menu` VALUES (17, 2060);
INSERT INTO `sys_role_menu` VALUES (17, 2077);
INSERT INTO `sys_role_menu` VALUES (17, 2078);
INSERT INTO `sys_role_menu` VALUES (17, 2079);
INSERT INTO `sys_role_menu` VALUES (17, 2080);
INSERT INTO `sys_role_menu` VALUES (17, 2081);
INSERT INTO `sys_role_menu` VALUES (17, 2082);
INSERT INTO `sys_role_menu` VALUES (17, 2083);
INSERT INTO `sys_role_menu` VALUES (17, 2084);
INSERT INTO `sys_role_menu` VALUES (17, 2085);
INSERT INTO `sys_role_menu` VALUES (17, 2086);
INSERT INTO `sys_role_menu` VALUES (17, 2087);
INSERT INTO `sys_role_menu` VALUES (17, 2088);
INSERT INTO `sys_role_menu` VALUES (17, 2089);
INSERT INTO `sys_role_menu` VALUES (17, 2103);
INSERT INTO `sys_role_menu` VALUES (17, 2104);
INSERT INTO `sys_role_menu` VALUES (17, 2119);
INSERT INTO `sys_role_menu` VALUES (17, 2120);
INSERT INTO `sys_role_menu` VALUES (17, 2122);
INSERT INTO `sys_role_menu` VALUES (17, 2123);
INSERT INTO `sys_role_menu` VALUES (17, 2124);
INSERT INTO `sys_role_menu` VALUES (17, 2126);
INSERT INTO `sys_role_menu` VALUES (17, 2128);
INSERT INTO `sys_role_menu` VALUES (17, 2129);
INSERT INTO `sys_role_menu` VALUES (17, 2130);
INSERT INTO `sys_role_menu` VALUES (17, 2131);
INSERT INTO `sys_role_menu` VALUES (17, 2132);
INSERT INTO `sys_role_menu` VALUES (17, 2134);
INSERT INTO `sys_role_menu` VALUES (17, 2135);
INSERT INTO `sys_role_menu` VALUES (17, 2136);
INSERT INTO `sys_role_menu` VALUES (17, 2139);
INSERT INTO `sys_role_menu` VALUES (17, 2147);
INSERT INTO `sys_role_menu` VALUES (17, 2148);
INSERT INTO `sys_role_menu` VALUES (17, 2149);
INSERT INTO `sys_role_menu` VALUES (17, 2150);
INSERT INTO `sys_role_menu` VALUES (17, 2151);
INSERT INTO `sys_role_menu` VALUES (17, 2152);
INSERT INTO `sys_role_menu` VALUES (17, 2153);
INSERT INTO `sys_role_menu` VALUES (17, 2154);
INSERT INTO `sys_role_menu` VALUES (17, 2155);
INSERT INTO `sys_role_menu` VALUES (17, 2156);
INSERT INTO `sys_role_menu` VALUES (17, 2158);
INSERT INTO `sys_role_menu` VALUES (17, 2159);
INSERT INTO `sys_role_menu` VALUES (17, 2160);
INSERT INTO `sys_role_menu` VALUES (17, 2161);
INSERT INTO `sys_role_menu` VALUES (17, 2162);
INSERT INTO `sys_role_menu` VALUES (17, 2163);
INSERT INTO `sys_role_menu` VALUES (17, 2164);
INSERT INTO `sys_role_menu` VALUES (17, 2165);
INSERT INTO `sys_role_menu` VALUES (17, 2166);
INSERT INTO `sys_role_menu` VALUES (17, 2167);
INSERT INTO `sys_role_menu` VALUES (17, 2168);
INSERT INTO `sys_role_menu` VALUES (17, 2169);
INSERT INTO `sys_role_menu` VALUES (17, 2170);
INSERT INTO `sys_role_menu` VALUES (17, 2171);
INSERT INTO `sys_role_menu` VALUES (17, 2172);
INSERT INTO `sys_role_menu` VALUES (17, 2173);
INSERT INTO `sys_role_menu` VALUES (17, 2174);
INSERT INTO `sys_role_menu` VALUES (17, 2175);
INSERT INTO `sys_role_menu` VALUES (17, 2176);
INSERT INTO `sys_role_menu` VALUES (17, 2177);
INSERT INTO `sys_role_menu` VALUES (17, 2178);
INSERT INTO `sys_role_menu` VALUES (17, 2179);
INSERT INTO `sys_role_menu` VALUES (17, 2180);
INSERT INTO `sys_role_menu` VALUES (17, 2181);
INSERT INTO `sys_role_menu` VALUES (17, 2182);
INSERT INTO `sys_role_menu` VALUES (17, 2184);
INSERT INTO `sys_role_menu` VALUES (17, 2188);
INSERT INTO `sys_role_menu` VALUES (17, 2189);
INSERT INTO `sys_role_menu` VALUES (17, 2190);
INSERT INTO `sys_role_menu` VALUES (17, 2191);
INSERT INTO `sys_role_menu` VALUES (17, 2192);
INSERT INTO `sys_role_menu` VALUES (17, 2193);
INSERT INTO `sys_role_menu` VALUES (17, 2194);
INSERT INTO `sys_role_menu` VALUES (17, 2195);
INSERT INTO `sys_role_menu` VALUES (17, 2196);
INSERT INTO `sys_role_menu` VALUES (17, 2198);
INSERT INTO `sys_role_menu` VALUES (17, 2199);
INSERT INTO `sys_role_menu` VALUES (17, 2200);
INSERT INTO `sys_role_menu` VALUES (17, 2202);
INSERT INTO `sys_role_menu` VALUES (17, 2203);
INSERT INTO `sys_role_menu` VALUES (17, 2204);
INSERT INTO `sys_role_menu` VALUES (17, 2205);
INSERT INTO `sys_role_menu` VALUES (17, 2206);
INSERT INTO `sys_role_menu` VALUES (17, 2207);
INSERT INTO `sys_role_menu` VALUES (17, 2208);
INSERT INTO `sys_role_menu` VALUES (17, 2209);
INSERT INTO `sys_role_menu` VALUES (17, 2210);
INSERT INTO `sys_role_menu` VALUES (17, 2212);
INSERT INTO `sys_role_menu` VALUES (17, 2213);
INSERT INTO `sys_role_menu` VALUES (17, 2214);
INSERT INTO `sys_role_menu` VALUES (17, 2215);
INSERT INTO `sys_role_menu` VALUES (17, 2216);
INSERT INTO `sys_role_menu` VALUES (17, 2217);
INSERT INTO `sys_role_menu` VALUES (17, 2229);
INSERT INTO `sys_role_menu` VALUES (17, 2230);
INSERT INTO `sys_role_menu` VALUES (17, 2231);
INSERT INTO `sys_role_menu` VALUES (17, 2232);
INSERT INTO `sys_role_menu` VALUES (17, 2233);
INSERT INTO `sys_role_menu` VALUES (17, 2235);
INSERT INTO `sys_role_menu` VALUES (17, 2237);
INSERT INTO `sys_role_menu` VALUES (17, 2238);
INSERT INTO `sys_role_menu` VALUES (17, 2240);
INSERT INTO `sys_role_menu` VALUES (17, 2241);
INSERT INTO `sys_role_menu` VALUES (17, 2242);
INSERT INTO `sys_role_menu` VALUES (17, 2244);
INSERT INTO `sys_role_menu` VALUES (17, 2245);
INSERT INTO `sys_role_menu` VALUES (17, 2246);
INSERT INTO `sys_role_menu` VALUES (17, 2247);
INSERT INTO `sys_role_menu` VALUES (17, 2248);
INSERT INTO `sys_role_menu` VALUES (17, 2249);
INSERT INTO `sys_role_menu` VALUES (17, 2250);
INSERT INTO `sys_role_menu` VALUES (17, 2251);
INSERT INTO `sys_role_menu` VALUES (17, 2252);
INSERT INTO `sys_role_menu` VALUES (17, 2253);
INSERT INTO `sys_role_menu` VALUES (17, 2254);
INSERT INTO `sys_role_menu` VALUES (17, 2255);
INSERT INTO `sys_role_menu` VALUES (17, 2256);
INSERT INTO `sys_role_menu` VALUES (17, 2257);
INSERT INTO `sys_role_menu` VALUES (17, 2259);
INSERT INTO `sys_role_menu` VALUES (17, 2260);
INSERT INTO `sys_role_menu` VALUES (17, 2262);
INSERT INTO `sys_role_menu` VALUES (17, 2263);
INSERT INTO `sys_role_menu` VALUES (17, 2268);
INSERT INTO `sys_role_menu` VALUES (17, 2269);
INSERT INTO `sys_role_menu` VALUES (17, 2270);
INSERT INTO `sys_role_menu` VALUES (17, 2271);
INSERT INTO `sys_role_menu` VALUES (17, 2272);
INSERT INTO `sys_role_menu` VALUES (17, 2273);
INSERT INTO `sys_role_menu` VALUES (17, 2274);
INSERT INTO `sys_role_menu` VALUES (17, 2275);
INSERT INTO `sys_role_menu` VALUES (17, 2276);
INSERT INTO `sys_role_menu` VALUES (17, 2277);
INSERT INTO `sys_role_menu` VALUES (17, 2278);
INSERT INTO `sys_role_menu` VALUES (17, 2279);
INSERT INTO `sys_role_menu` VALUES (17, 2280);
INSERT INTO `sys_role_menu` VALUES (17, 2281);
INSERT INTO `sys_role_menu` VALUES (17, 2282);
INSERT INTO `sys_role_menu` VALUES (17, 2283);
INSERT INTO `sys_role_menu` VALUES (17, 2284);
INSERT INTO `sys_role_menu` VALUES (17, 2285);
INSERT INTO `sys_role_menu` VALUES (17, 2286);
INSERT INTO `sys_role_menu` VALUES (17, 2287);
INSERT INTO `sys_role_menu` VALUES (17, 2288);
INSERT INTO `sys_role_menu` VALUES (17, 2289);
INSERT INTO `sys_role_menu` VALUES (17, 2290);
INSERT INTO `sys_role_menu` VALUES (17, 2291);
INSERT INTO `sys_role_menu` VALUES (17, 2292);
INSERT INTO `sys_role_menu` VALUES (17, 2293);
INSERT INTO `sys_role_menu` VALUES (17, 2296);
INSERT INTO `sys_role_menu` VALUES (17, 2297);
INSERT INTO `sys_role_menu` VALUES (17, 2298);
INSERT INTO `sys_role_menu` VALUES (17, 2299);
INSERT INTO `sys_role_menu` VALUES (17, 2300);
INSERT INTO `sys_role_menu` VALUES (17, 2351);
INSERT INTO `sys_role_menu` VALUES (17, 2352);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0位置 1女 2男）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '帐号状态（0停用 1正常）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '删除标志（0代表删除 1标识存在）',
  `login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`, `avatar`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 201, 'admin', 'admin', '00', '2569031924@qq.com', '13824308678', '1', '70aa3500f0782f2634a77f68fd1eaedb.jpg', '8b61bcfefa433c019dcd869312f9644b', '940953', '1', '1', '127.0.0.1', '2019-08-30 02:58:20', 'admin', '2018-03-16 11:33:00', 'admin', '2019-08-30 02:59:15', '管理员');
INSERT INTO `sys_user` VALUES (121, 201, 'tim', 'tim', '00', '', '15999999999', '0', '', '43344c892425f4b1a633e36e0c242316', '4b592f', '1', '0', '103.100.211.243', '2018-12-12 14:15:55', 'admin', '2018-12-11 09:56:06', '', NULL, '');
INSERT INTO `sys_user` VALUES (122, 201, 'ceyx', 'ceyx', '00', '', '18810100001', '0', '', '9045fd5e5d38c94ed710cbcda6cea3e8', '7f18b6', '1', '0', '113.91.41.237', '2018-12-11 15:09:54', 'tim', '2018-12-11 10:26:22', '', NULL, '');
INSERT INTO `sys_user` VALUES (123, 201, 'qiang', 'admin', '00', 'awdfawe', '15999999998', '0', '', 'bb7b3b8d300c068e2ee859c1b893b4b0', '72fd17', '1', '0', '113.91.41.237', '2018-12-11 11:58:53', 'admin', '2018-12-11 10:40:07', 'admin', '2018-12-11 10:45:08', '');
INSERT INTO `sys_user` VALUES (124, 201, 'yongheng', '永恒', '00', '开发', '13680666666', '0', '', '405e0e00dca2defaef5562662fa8a611', '280806', '1', '0', '', NULL, 'admin', '2018-12-11 11:53:54', '', NULL, '');
INSERT INTO `sys_user` VALUES (125, 201, 'yongheng', '永恒', '00', '开发', '13680666666', '0', '', '10ad46d2a0ed1ed33a895386528af053', '7e2a5e', '1', '0', '', NULL, 'admin', '2018-12-11 11:53:54', '', NULL, '');
INSERT INTO `sys_user` VALUES (126, 201, '222', '啦啦啦', '00', '222', '13680666668', '0', '', 'bcb594168223eb95ba3117c23d8c6ef1', '3de97f', '1', '0', '', NULL, 'admin', '2018-12-11 11:55:29', '', NULL, '');
INSERT INTO `sys_user` VALUES (127, 201, '222', '啦啦啦', '00', '222', '13680666668', '0', '', '4800e69608052c510226315de1d758e5', 'aaa631', '1', '0', '', NULL, 'admin', '2018-12-11 11:55:30', '', NULL, '');
INSERT INTO `sys_user` VALUES (128, 201, 'yongheng2', '永恒', '00', '开发', '13680666688', '0', '', 'b058bbbf358fe0d0bb9b834f7504057c', '88fcab', '1', '0', '127.0.0.1', '2018-12-13 17:03:05', 'admin', '2018-12-11 11:57:09', '', NULL, '');
INSERT INTO `sys_user` VALUES (129, 201, 'red1', 'red1', '00', '人事', '13724294611', '0', '', 'a8d9848eee3fd101725977b7e5e3c8a7', 'f38997', '1', '0', '113.91.41.237', '2018-12-12 15:09:50', 'admin', '2018-12-12 09:21:22', '', NULL, '');
INSERT INTO `sys_user` VALUES (130, 201, 'huguansheng', '胡', '00', '', '18888888888', '0', '', '84a1d9f524e2359dec2c0912d9bbb7e7', 'fcf460', '1', '0', '113.91.41.237', '2018-12-12 13:44:46', 'red1', '2018-12-12 09:43:21', '', NULL, '');
INSERT INTO `sys_user` VALUES (131, 201, 'long', 'long', '00', '', '16666666666', '0', '', '279ddaba1c34fd5d7852c927c4b22f29', '8b2087', '1', '0', '113.91.41.237', '2018-12-12 14:12:20', 'red1', '2018-12-12 09:44:06', 'huang', '2018-12-12 14:11:56', '');
INSERT INTO `sys_user` VALUES (132, 201, 'huang', 'huang', '00', '', '16688888888', '0', '', '8c9e3fd7897b30f84699ef92de1fe510', 'fafce1', '1', '0', '127.0.0.1', '2019-02-15 18:25:06', 'admin', '2018-12-12 10:03:46', '', NULL, '');
INSERT INTO `sys_user` VALUES (134, 201, '123', 'xty', '00', 'xxx', '18888888880', '0', '', '375a0e35f75ed1985f473827c771f680', '085a39', '1', '0', '', NULL, 'huang', '2018-12-12 17:20:13', '', NULL, '');
INSERT INTO `sys_user` VALUES (135, 201, 'mmp', 'mmp', '00', 'ss', '13424234332', '0', '', '2361be850933e03ad9f6e2dae61228b1', 'f1123f', '1', '0', '', NULL, 'huang', '2018-12-12 17:32:10', 'huang', '2018-12-12 17:37:49', '');
INSERT INTO `sys_user` VALUES (136, 201, 'asd', 'asd', '00', 'ddd', '13424234111', '0', '', 'e940457b54fc5f34c728180ca3dc39fe', '0aa81a', '1', '0', '127.0.0.1', '2018-12-12 17:36:12', 'huang', '2018-12-12 17:38:13', 'huang', '2018-12-12 17:38:18', '');
INSERT INTO `sys_user` VALUES (137, 201, '123456', 'asdasdasd', '00', 'xxx', '18888888881', '0', '', '323cfc7eb16d0be6daeee121a19dd05d', '91005c', '1', '0', '', NULL, 'huang', '2018-12-12 17:45:18', 'huang', '2018-12-12 17:46:53', '');
INSERT INTO `sys_user` VALUES (138, 201, '789', '789', '00', '', '18888888882', '0', '', '7204580f20935ef69543775ef6dc649b', '55418a', '1', '0', '', NULL, 'huang', '2018-12-12 17:47:29', 'huang', '2018-12-12 17:48:07', '');
INSERT INTO `sys_user` VALUES (140, 201, 'yongheng3', '永恒', '00', '', '13680666667', '0', '', 'c534a029fbe8bca132a3dd443f81e614', '6f10e1', '1', '0', '127.0.0.1', '2018-12-21 13:41:23', 'admin', '2018-12-21 11:54:03', 'admin', '2018-12-21 13:43:28', '');
INSERT INTO `sys_user` VALUES (141, 202, 'bitrade', '兔子', '00', '计划经理', '15888888888', '0', '9f784dfeb80931c5a2f1b196b0364e48.jpg', '7a898881e7b75cd112aa0e0b9d738b39', '92fa93', '1', '1', '219.134.218.31', '2019-05-22 18:38:23', 'admin', '2019-04-12 15:46:43', 'admin', '2019-07-03 17:37:51', '');
INSERT INTO `sys_user` VALUES (142, 202, 'betagou', '小小', '00', '实审文员', '13412345678', '0', '', '35d9efc6060d17cd76cf10ff8c474ced', '9986d7', '1', '1', '219.134.218.31', '2019-05-22 18:41:51', 'admin', '2019-05-20 21:23:17', 'admin', '2019-07-03 17:38:47', '');
INSERT INTO `sys_user` VALUES (143, 202, 'smrz', '待定', '00', '实名文员', '18812345678', '0', '', '2e9d3a34f3eba1d69cc95f6c3a49d3be', '58377a', '1', '1', '163.125.180.35', '2019-06-20 00:02:48', 'admin', '2019-05-22 18:51:47', '', NULL, '');
INSERT INTO `sys_user` VALUES (144, 201, 'alin', '阿林', '00', '超级管理员', '13612345678', '0', '', '2262fe87031e220cd65d5b1fc5c9491d', 'd6b9f5', '1', '1', '27.38.28.219', '2019-07-30 00:41:47', 'admin', '2019-05-22 20:28:20', 'admin', '2019-07-30 00:40:59', '');
INSERT INTO `sys_user` VALUES (145, 202, 'jiner', '金儿', '00', '运营经理', '13453190551', '0', '', 'cabcd770a26615cb30dc47ba96a571f0', '839024', '1', '1', '14.28.0.129', '2019-08-19 20:43:48', 'alin', '2019-05-22 20:36:31', 'admin', '2019-07-30 00:34:52', '');
INSERT INTO `sys_user` VALUES (146, 201, 'bjian', '保健', '00', '管理员', '18688968196', '0', '', '2ffa46320e07777d3a65428fef259cc8', 'b34577', '1', '1', '163.125.244.186', '2019-07-25 13:22:16', 'admin', '2019-05-22 21:02:37', 'admin', '2019-07-30 00:31:53', '');
INSERT INTO `sys_user` VALUES (147, 202, 'tuzi', '兔子', '00', '运营经理', '13612345666', '0', '', '166f2008b5ee8f39dee8108e5df6b0ec', 'a99266', '1', '1', '14.30.46.118', '2019-07-28 17:07:15', 'admin', '2019-05-28 02:09:58', 'admin', '2019-07-28 17:00:48', '');
INSERT INTO `sys_user` VALUES (148, 202, 'jinersh', '小小(审核)', '00', '审核经理', '13412345679', '0', '', '6510c6204e920ef31ad53513e6e3350f', 'ce00b2', '1', '1', '183.39.173.58', '2019-06-09 00:45:04', 'admin', '2019-06-09 00:14:29', '', NULL, '');
INSERT INTO `sys_user` VALUES (149, 204, 'yinger', '盈儿', '00', '客诉文员', '15012345678', '0', '', 'ce086da3e871e981848eb5d69e962e12', 'a497cf', '1', '0', '183.39.173.58', '2019-06-09 00:56:25', 'admin', '2019-06-09 00:56:09', 'admin', '2019-06-09 01:11:30', '');
INSERT INTO `sys_user` VALUES (150, 202, 'tangtang', '唐唐', '00', '客服文员', '18612345679', '0', '', 'ad425600387a5899c9821497062e7082', '682e54', '1', '1', '219.134.216.174', '2019-08-19 20:14:11', 'admin', '2019-06-13 01:11:43', 'admin', '2019-06-27 16:33:55', '');
INSERT INTO `sys_user` VALUES (151, 202, 'zq327', 'zqin', '00', '客服文员', '17712345679', '0', '', 'f7782f60d253285603b2d7767bc52d6e', '465471', '1', '1', '116.77.73.250', '2019-07-21 11:45:29', 'admin', '2019-06-13 02:35:21', 'admin', '2019-07-01 13:45:44', '');
INSERT INTO `sys_user` VALUES (152, 202, 'xb330', '宝儿', '00', '客服文员', '13512345678', '0', '', 'bfe0ffd9544916a5d67a3b960e22769d', 'd36fb2', '1', '1', '183.12.50.104', '2019-08-16 16:50:10', 'admin', '2019-06-13 14:33:46', 'admin', '2019-07-03 17:36:01', '');
INSERT INTO `sys_user` VALUES (153, 203, 'lyu', '何森', '00', '管理员', '18612341234', '0', '', '2fe5c385749a02a7bf8fda64e049ee27', 'a93b70', '1', '1', '113.110.172.150', '2019-06-15 04:38:10', 'admin', '2019-06-15 04:34:19', '', NULL, '');
INSERT INTO `sys_user` VALUES (154, 202, 'huaner', '欢儿', '00', '客服文员', '17712345678', '0', '', '84d12be62c5d817984f895c60ae5ea81', '5620c0', '1', '1', '219.134.216.77', '2019-08-06 22:26:42', 'admin', '2019-06-19 19:35:43', '', NULL, '');
INSERT INTO `sys_user` VALUES (155, 202, 'xiongmao', '熊猫', '00', '计划经理', '15312345678', '0', '', '3f088719a63d3de8dbe18b422f598f43', '06bead', '1', '1', '113.118.184.189', '2019-08-18 00:16:14', 'admin', '2019-06-20 00:07:30', 'admin', '2019-07-30 00:38:32', '');
INSERT INTO `sys_user` VALUES (156, 202, 'hyan', 'why', '00', '实名文员', '13412345678', '0', '', 'f90ee3a8a4026c6fa06836161161f7c0', '5d713a', '1', '1', '223.10.150.156', '2019-08-19 19:26:06', 'admin', '2019-08-02 12:07:36', '', NULL, '');
INSERT INTO `sys_user` VALUES (157, 202, 'xbao', 'lxb', '00', '实名文员', '13422345678', '0', '', '934c53d9e9fc260c2ce221e6a88ed086', 'a7c293', '1', '1', '183.251.186.251', '2019-08-02 12:18:50', 'admin', '2019-08-02 12:18:09', '', NULL, '');

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) NULL DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) NULL DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) NULL DEFAULT 0 COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '在线用户记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (121, 11);
INSERT INTO `sys_user_role` VALUES (122, 1);
INSERT INTO `sys_user_role` VALUES (124, 1);
INSERT INTO `sys_user_role` VALUES (125, 1);
INSERT INTO `sys_user_role` VALUES (126, 1);
INSERT INTO `sys_user_role` VALUES (127, 1);
INSERT INTO `sys_user_role` VALUES (128, 1);
INSERT INTO `sys_user_role` VALUES (129, 12);
INSERT INTO `sys_user_role` VALUES (130, 1);
INSERT INTO `sys_user_role` VALUES (131, 12);
INSERT INTO `sys_user_role` VALUES (132, 1);
INSERT INTO `sys_user_role` VALUES (135, 1);
INSERT INTO `sys_user_role` VALUES (136, 1);
INSERT INTO `sys_user_role` VALUES (137, 1);
INSERT INTO `sys_user_role` VALUES (138, 12);
INSERT INTO `sys_user_role` VALUES (140, 12);
INSERT INTO `sys_user_role` VALUES (141, 13);
INSERT INTO `sys_user_role` VALUES (142, 15);
INSERT INTO `sys_user_role` VALUES (143, 15);
INSERT INTO `sys_user_role` VALUES (144, 13);
INSERT INTO `sys_user_role` VALUES (145, 17);
INSERT INTO `sys_user_role` VALUES (146, 1);
INSERT INTO `sys_user_role` VALUES (147, 17);
INSERT INTO `sys_user_role` VALUES (148, 12);
INSERT INTO `sys_user_role` VALUES (149, 16);
INSERT INTO `sys_user_role` VALUES (150, 14);
INSERT INTO `sys_user_role` VALUES (151, 14);
INSERT INTO `sys_user_role` VALUES (152, 14);
INSERT INTO `sys_user_role` VALUES (153, 1);
INSERT INTO `sys_user_role` VALUES (154, 14);
INSERT INTO `sys_user_role` VALUES (155, 13);
INSERT INTO `sys_user_role` VALUES (156, 15);
INSERT INTO `sys_user_role` VALUES (157, 15);

-- ----------------------------
-- Table structure for t_accept_dealer
-- ----------------------------
DROP TABLE IF EXISTS `t_accept_dealer`;
CREATE TABLE `t_accept_dealer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '承兑商表id',
  `accept_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '承兑商名称',
  `accept_businessId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '承兑商业务id',
  `accept_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '承兑商key（蓝鲸MD5key）',
  `accept_recharge_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '承兑商充值url',
  `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标url',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态：0禁用，1启用',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '（备用字段）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_account_manage
-- ----------------------------
DROP TABLE IF EXISTS `t_account_manage`;
CREATE TABLE `t_account_manage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户名称',
  `key_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关键词',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '值',
  `locality` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '位置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账户配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_account_manage
-- ----------------------------
INSERT INTO `t_account_manage` VALUES (1, '2019-08-21 11:32:01', '2019-08-21 11:32:01', '幣幣賬戶', 'bibi', '币币账户', '交易所');
INSERT INTO `t_account_manage` VALUES (2, '2019-08-21 11:32:39', '2019-08-21 11:32:39', '法幣賬戶', 'c2c', 'c2c账户', '交易所');
INSERT INTO `t_account_manage` VALUES (3, '2019-08-21 11:33:11', '2019-08-21 11:33:11', '資金賬戶', 'zijin', '资金账户', '交易所');

-- ----------------------------
-- Table structure for t_advert_info
-- ----------------------------
DROP TABLE IF EXISTS `t_advert_info`;
CREATE TABLE `t_advert_info`  (
  `id` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(64) UNSIGNED NOT NULL COMMENT '用户id',
  `coin_id` bigint(64) UNSIGNED NOT NULL COMMENT '法币id',
  `type` tinyint(1) UNSIGNED NOT NULL COMMENT '类型(1:出售 2:购买)',
  `pricing_mode` tinyint(1) UNSIGNED NOT NULL COMMENT '定价方式 1：固定价格 2：浮动价格',
  `floating_ratio` decimal(3, 2) NULL DEFAULT NULL COMMENT '浮动比例 (小数 0.01 = 1%)',
  `price` decimal(20, 2) UNSIGNED NULL DEFAULT NULL COMMENT '单价（CNY）',
  `hide_price` decimal(20, 2) UNSIGNED NULL DEFAULT NULL COMMENT '隐藏价格',
  `min_limit` decimal(20, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '最小限额（CNY）',
  `max_limit` decimal(20, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '最大限额（CNY）',
  `already_transaction_amount` decimal(20, 8) UNSIGNED NOT NULL DEFAULT 0.00000000 COMMENT '广告已交易数量',
  `balance_amount` decimal(20, 8) UNSIGNED NOT NULL DEFAULT 0.00000000 COMMENT '广告剩余数量',
  `freeze_amount` decimal(20, 8) UNSIGNED NOT NULL DEFAULT 0.00000000 COMMENT '广告进行中冻结数量',
  `payment_method_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收款方式id，出售单为收款方式, 购买单为付款方式,多个以逗号分隔',
  `status` tinyint(1) NOT NULL COMMENT '状态：1，进行中；2，已下架(暂停)；3，已撤销；',
  `open_opponent_limit` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否开启对手限制 (0 禁用 1 启用)',
  `certification_level` tinyint(1) NULL DEFAULT NULL COMMENT '对手限制-认证等级',
  `registered_time` datetime(0) NULL DEFAULT NULL COMMENT '对手限制-注册时间',
  `payment_time` int(11) NULL DEFAULT NULL COMMENT '对手限制-付款时间',
  `message` varchar(140) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易说明（留言）',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '版本号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uk_user_id_and_coin_id`(`user_id`, `coin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_advert_info
-- ----------------------------
INSERT INTO `t_advert_info` VALUES (23, 1, 1, 1, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 196.00000000, 12.00000000, '1', 1, 1, 1, '2018-11-11 12:12:12', 10, '请买家尽快付款，谢谢', 4, '2019-08-22 15:03:06', '2019-08-22 19:52:29');
INSERT INTO `t_advert_info` VALUES (24, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 6.00000000, 4.00000000, '1', 1, 1, 1, '2018-11-11 12:12:12', NULL, '这个是购买广告', 2, '2019-08-22 21:56:18', '2019-08-22 21:56:18');
INSERT INTO `t_advert_info` VALUES (25, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, '1', 1, 1, 1, '2018-11-11 12:12:12', NULL, '这个是购买广告', 1, '2019-08-23 00:46:15', '2019-08-23 00:46:15');
INSERT INTO `t_advert_info` VALUES (26, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, '1', 1, 1, 1, '2018-11-11 12:12:12', NULL, '这个是购买广告', 1, '2019-08-23 00:46:26', '2019-08-23 00:46:26');
INSERT INTO `t_advert_info` VALUES (27, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, '1', 1, 1, 1, '2018-11-11 12:12:12', NULL, '这个是购买广告', 1, '2019-08-23 00:46:37', '2019-08-23 00:46:37');
INSERT INTO `t_advert_info` VALUES (28, 1, 1, 1, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, '1,2,3', 1, 1, NULL, '2018-11-11 12:12:12', 10, '请买家尽快付款，谢谢', 1, '2019-08-29 09:36:53', '2019-08-29 09:39:14');
INSERT INTO `t_advert_info` VALUES (29, 1, 1, 1, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, '1,2,3', 1, 1, NULL, '2018-11-11 12:12:12', 10, '请买家尽快付款，谢谢', 1, '2019-08-29 09:38:44', '2019-08-29 09:38:44');
INSERT INTO `t_advert_info` VALUES (30, 1, 1, 1, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, '1,2,3', 1, 1, 1, '2018-11-11 12:12:12', 10, '请买家尽快付款，谢谢', 1, '2019-08-29 09:58:51', '2019-08-29 09:58:51');
INSERT INTO `t_advert_info` VALUES (31, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, '1,2,3', 1, 1, 1, '2018-11-11 12:12:12', NULL, '请买家尽快付款，谢谢', 1, '2019-08-29 09:59:35', '2019-08-29 16:20:33');
INSERT INTO `t_advert_info` VALUES (32, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 2.00000000, 8.00000000, '1,2,3', 1, 1, 1, '2018-11-11 12:12:12', NULL, '请买家尽快付款，谢谢', 13, '2019-08-29 14:04:54', '2019-08-29 15:21:06');
INSERT INTO `t_advert_info` VALUES (33, 1, 1, 1, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, '1,2,3', 3, 1, 1, '2018-11-11 12:12:12', 10, '请买家尽快付款，谢谢', 2, '2019-08-29 14:05:27', '2019-08-29 14:05:27');
INSERT INTO `t_advert_info` VALUES (34, 1, 1, 1, 1, NULL, 6.00, NULL, 20.00, 1000.00, 8.00000000, 2.00000000, 0.00000000, '1,2,3', 1, 1, 1, '2018-11-11 12:12:12', 10, '请买家尽快付款，谢谢', 3, '2019-08-29 15:33:25', '2019-08-29 15:52:20');

-- ----------------------------
-- Table structure for t_advert_order
-- ----------------------------
DROP TABLE IF EXISTS `t_advert_order`;
CREATE TABLE `t_advert_order`  (
  `id` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `advert_id` bigint(64) UNSIGNED NOT NULL COMMENT '广告信息id',
  `coin_id` bigint(64) UNSIGNED NOT NULL COMMENT '法币币种id',
  `order_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `payment_legal_currency` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '付款法币(CNY)',
  `advert_type` tinyint(1) NOT NULL COMMENT '广告类别 1出售 2购买',
  `advert_message` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告留言',
  `buyer_id` bigint(64) UNSIGNED NOT NULL COMMENT '购买者ID',
  `seller_id` bigint(64) UNSIGNED NOT NULL COMMENT '出售者ID',
  `publisher_id` bigint(64) UNSIGNED NOT NULL COMMENT '发布者ID',
  `canceller_id` bigint(64) NULL DEFAULT NULL COMMENT '取消者ID,系统超时取消填0（仅取消状态需填）',
  `transaction_amout` decimal(20, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '交易金额(CNY)',
  `transaction_num` decimal(20, 8) UNSIGNED NOT NULL DEFAULT 0.00000000 COMMENT '交易数量',
  `transaction_price` decimal(20, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '交易价格(CNY)',
  `rate` decimal(3, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '续费率(小数 0.01 = 1%)',
  `charge` decimal(20, 8) UNSIGNED NOT NULL DEFAULT 0.00000000 COMMENT '手续费',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态（0,未操作; 1，已拍下；2，已付款；3，已收款；5，已完成；6，已取消，7，超时关闭）',
  `cancel_order_deadline` datetime(0) NOT NULL COMMENT '取消订单截止时间（默认为 点击 购买/出售 后 3 分钟）',
  `arbit_status` tinyint(1) NULL DEFAULT NULL COMMENT '仲裁状态：0，未仲裁；1，已仲裁；',
  `arbit_result` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仲裁结果',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `grant_coin_time` datetime(0) NULL DEFAULT NULL COMMENT '放币时间',
  `overdue_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uk_advert_id_and_coin_id`(`advert_id`, `coin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_advert_order
-- ----------------------------
INSERT INTO `t_advert_order` VALUES (1, 23, 1, 'C2C361933910411710464', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-22 15:20:24', 0, NULL, NULL, NULL, '2019-08-22 15:27:27', NULL, '2019-08-22 15:18:29', '2019-08-22 15:18:29');
INSERT INTO `t_advert_order` VALUES (3, 23, 1, 'C2C362002942800302080', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-22 19:54:33', 0, NULL, NULL, NULL, '2019-08-22 20:01:33', NULL, '2019-08-22 19:51:19', '2019-08-22 19:51:19');
INSERT INTO `t_advert_order` VALUES (4, 23, 1, 'C2C362003285416218624', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-22 19:55:55', 0, NULL, NULL, NULL, '2019-08-22 20:02:55', NULL, '2019-08-22 19:52:41', '2019-08-22 19:52:41');
INSERT INTO `t_advert_order` VALUES (5, 24, 1, 'C2C362035928547594240', 'CNY', 2, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-22 22:05:37', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-22 22:02:23', '2019-08-22 22:02:23');
INSERT INTO `t_advert_order` VALUES (6, 32, 1, 'C2C364454914635730944', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-29 14:17:49', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:14:34', '2019-08-29 14:14:34');
INSERT INTO `t_advert_order` VALUES (7, 32, 1, 'C2C364456697617584128', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-29 14:24:54', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:21:39', '2019-08-29 14:21:39');
INSERT INTO `t_advert_order` VALUES (8, 32, 1, 'C2C364456719012728832', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-29 14:24:59', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:21:44', '2019-08-29 14:21:44');
INSERT INTO `t_advert_order` VALUES (9, 32, 1, 'C2C364456792345939968', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-29 14:25:16', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:22:01', '2019-08-29 14:22:01');
INSERT INTO `t_advert_order` VALUES (10, 32, 1, 'C2C364458374013784064', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-29 14:31:33', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:28:19', '2019-08-29 14:28:19');
INSERT INTO `t_advert_order` VALUES (11, 32, 1, 'C2C364462915153170432', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 14:49:36', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:46:21', '2019-08-29 14:46:21');
INSERT INTO `t_advert_order` VALUES (12, 32, 1, 'C2C364463111886999552', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 14:50:23', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:47:08', '2019-08-29 14:47:08');
INSERT INTO `t_advert_order` VALUES (13, 32, 1, 'C2C364464376519987200', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 14:55:24', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:52:09', '2019-08-29 14:52:18');
INSERT INTO `t_advert_order` VALUES (14, 32, 1, 'C2C364464916385632256', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 14:57:33', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:54:18', '2019-08-29 14:54:23');
INSERT INTO `t_advert_order` VALUES (15, 32, 1, 'C2C364465006026297344', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 14:57:55', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:54:40', '2019-08-29 14:54:44');
INSERT INTO `t_advert_order` VALUES (16, 32, 1, 'C2C364465451125837824', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 14:59:41', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 14:56:26', '2019-08-29 14:56:30');
INSERT INTO `t_advert_order` VALUES (17, 32, 1, 'C2C364471660507697152', 'CNY', 2, NULL, 1, 1, 1, NULL, 6.00, 1.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-29 15:24:21', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 15:21:06', '2019-08-29 15:21:06');
INSERT INTO `t_advert_order` VALUES (18, 34, 1, 'C2C364476361416511488', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 15:43:02', 0, NULL, NULL, NULL, '2019-08-29 15:50:02', NULL, '2019-08-29 15:39:47', '2019-08-29 15:41:19');
INSERT INTO `t_advert_order` VALUES (19, 34, 1, 'C2C364476883829657600', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 15:45:06', 0, NULL, NULL, NULL, '2019-08-29 15:52:06', NULL, '2019-08-29 15:41:51', '2019-08-29 15:41:58');
INSERT INTO `t_advert_order` VALUES (20, 34, 1, 'C2C364476946576445440', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 15:45:21', 0, NULL, NULL, NULL, '2019-08-29 15:52:21', NULL, '2019-08-29 15:42:06', '2019-08-29 15:42:41');
INSERT INTO `t_advert_order` VALUES (21, 34, 1, 'C2C364477817578196992', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 5, '2019-08-29 15:48:49', 0, NULL, NULL, NULL, '2019-08-29 15:55:49', NULL, '2019-08-29 15:45:34', '2019-08-29 15:45:42');
INSERT INTO `t_advert_order` VALUES (22, 34, 1, 'C2C364479521799409664', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 2, '2019-08-29 15:55:35', 0, NULL, NULL, NULL, '2019-08-29 16:02:35', NULL, '2019-08-29 15:52:20', '2019-08-29 15:54:34');
INSERT INTO `t_advert_order` VALUES (23, 31, 1, 'C2C364486517386776576', 'CNY', 2, NULL, 1, 1, 1, 1, 18.00, 3.00000000, 6.00, 0.00, 0.00000000, 6, '2019-08-29 16:23:23', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 16:20:08', '2019-08-29 16:20:32');

-- ----------------------------
-- Table structure for t_chat_record_log
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record_log`;
CREATE TABLE `t_chat_record_log`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sender_id` bigint(64) NOT NULL COMMENT '发送者ID',
  `receiver_id` bigint(64) NOT NULL COMMENT '接受者ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发送的内容',
  `message_type` tinyint(1) NOT NULL COMMENT '发送的类型:0,文本,1,图片',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_sender_id_receiver_id`(`sender_id`, `receiver_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1164024118792601603 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat_record_log
-- ----------------------------
INSERT INTO `t_chat_record_log` VALUES (1164024118792601602, 2, 1, '测试成功', 1, '2019-08-21 11:58:59', '2019-08-21 11:58:59');

-- ----------------------------
-- Table structure for t_currency
-- ----------------------------
DROP TABLE IF EXISTS `t_currency`;
CREATE TABLE `t_currency`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `short_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简称',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态:0禁用,1正常',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '法币:0否,1是',
  `desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '虚拟货币类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_currency
-- ----------------------------
INSERT INTO `t_currency` VALUES (1, 'BTC', '比特幣', 1, 0, '比特币（Bitcoin，简称BTC）是目前使用最为广泛的一种数字货币，它诞生于2009年1月3日，是一种点对点（P2P）传输的数字加密货币，总量2100万枚。比特币网络每10分钟释放出一定数量币，预计在2140年达到极限。比特币被投资者称为“数字黄金”。比特币依据特定算法，通过大量的计算产生，不依靠特定货币机构发行，其使用整个P2P网络中众多节点构成的分布式数据库来确认并记录所有的交易行为，并使用密码学设计确保货币流通各个环节安全性，可确保无法通过大量制造比特币来人为操控币值。基于密码学的设计可以使比特币只能被真实拥有者转移、支付及兑现。同样确保了货币所有权与流通交易的匿名性。', '2019-06-26 15:43:19');
INSERT INTO `t_currency` VALUES (2, 'ETH', '以太坊', 1, 0, '以太坊（Ethereum）是下一代密码学账本，可以支持众多的高级功能，包括用户发行货币，智能协议，去中心化的交易和设立去中心化自治组织(DAOs)或去中心化自治公司（DACs）。以太坊并不是把每一单个类型的功能作为特性来特别支持，相反，以太坊包括一个内置的图灵完备的脚本语言，允许通过被称为“合同”的机制来为自己想实现的特性写代码。一个合同就像一个自动的代理，每当接收到一笔交易，合同就会运行特定的一段代码，这段代码能修改合同内部的数据存储或者发送交易。', '2019-06-26 15:44:40');
INSERT INTO `t_currency` VALUES (3, 'USDT', '泰達幣', 1, 1, 'USDT是由Tether公司发行的一种虚拟货币，实际上和其他虚拟货币并没有什么本质的区别。但他牛就牛在宣布严格遵守1：1的准备金保证，即每发行1枚USDT代币，其银行帐户都会有1美元的资金保障。这样一来，USDT就和美元等价了，俨然成为了虚拟货币和现实货币的桥梁。特别是随着全球监管越来越严格，更多的人都通过USDT来进行虚拟货币的投资，地位自然也就水涨船高', '2019-07-05 16:21:38');
INSERT INTO `t_currency` VALUES (4, 'LTC', '萊特幣', 1, 0, '莱特币诞生于2011年11月9日，被称为是“数字白银”。莱特币在技术上和比特币具有相同的实现原理。它是第一个基于Scrypt算法的网络数字货币，与比特币相比，莱特币拥有更快的交易确认时间，更高的网络交易容量和效率。莱特币现在拥有完整的产业链，充分的流动性，足以证明其是成熟、安全、稳定的商用金融系统。', '2019-07-19 11:32:27');
INSERT INTO `t_currency` VALUES (5, 'EOS', '柚子幣', 1, 0, 'EOS (Enterprise Operation System)是由 Block.one公司主导开发的一种全新的基于区块链智能合约平台，旨在为高性能分布式应用提供底层区块链平台服务。EOS 项目的目标是实现一个类似操作系统的支撑分布式应用程序的区块链架构。该架构可以提供账户，身份认证，数据库，异步通信以及可在数以万计的 CPU/GPU群集上进行程序调度和并行运算。EOS最终可以支持每秒执行数百万个交易，同时普通用户执行智能合约无需支付使用费用。', '2019-07-19 11:32:30');
INSERT INTO `t_currency` VALUES (6, 'XRP', '瑞波幣', 1, 0, 'Ripple 是一个去中心化的资产传输网络,用于解决金融机构以及用户间的资产转换和信任问题。XRP 是这个网络上面的基础资产,目前已经成为市值排名前几位的区块链资产。', '2019-07-19 11:32:32');
INSERT INTO `t_currency` VALUES (7, 'ETC', '以太經典', 1, 0, '以太经典（Ethereum Classic，简称ETC）是原始以太坊Ethereum区块链的延续， 是以太坊在1,920,000个区块后硬分叉出的分叉币种，功能和以太坊极为类似。应用程序运行完全按照程序设定，没有任何停机、审查、欺诈或第三方干扰的可能性，免受外部干扰和主观篡改交易。', '2019-08-03 17:24:12');
INSERT INTO `t_currency` VALUES (8, 'BCH', '比特幣現金', 1, 0, '比特币现金（Bitcoin Cash (BCH)）是比特币的硬分叉币，通过升级协议修复链上容量，不包含SegWit功能。比特币现金（BCH）于2017年8月1日发布，作为原始比特币核心软件的升级版本，支持大区块（将区块大小提升至8M），有效提高支付处理速度，实现更快、更便宜的交易以及更流畅的用户体验', '2019-08-03 17:26:36');
INSERT INTO `t_currency` VALUES (9, 'TRX', 'TRX', 1, 0, 'TRON的流通市值为5,965,500,000美元（Coinmarketcap.com）,每日成交量为257,473,000美元。 现在,TRX在全球排名第10,全球用户近200万。 TRON-TRX的官方代币已在近70个交易所上市,包括BitMEX,Binance,Bittrex,Bitfinex,Upbit,Bithumb,Huobi,OKEx等。', '2019-08-03 17:30:23');
INSERT INTO `t_currency` VALUES (10, 'BSV', '比特幣SV', 1, 0, '应比特币现金（BCH）矿业巨头CoinGeek及其他矿工的要求，我们创建了比特币SV（Bitcoin Cash SV [IOU]），旨在为矿工提供明确的比特币现金（BCH）实现选择，并允许企业在其稳固可靠的基础上构建应用程序和网站。 比特币SV创建全球区块链的路线图以四大基本支柱为基础：安全性、稳定性、可扩容性、安全即时交易（亦称为零确认）。', '2019-08-03 17:32:45');

-- ----------------------------
-- Table structure for t_currency_address
-- ----------------------------
DROP TABLE IF EXISTS `t_currency_address`;
CREATE TABLE `t_currency_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) NULL DEFAULT NULL COMMENT '货币id，t_currency表中的id',
  `adderess` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '钱包地址',
  `address_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `adderess`(`adderess`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户钱包地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_currency_optional
-- ----------------------------
DROP TABLE IF EXISTS `t_currency_optional`;
CREATE TABLE `t_currency_optional`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `currency_trade_id` int(11) NOT NULL COMMENT '交易对id,t_currency_trade中的id',
  `status` tinyint(1) NOT NULL COMMENT '状态：0删除，1启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户的交易对自选表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_currency_optional
-- ----------------------------
INSERT INTO `t_currency_optional` VALUES (1, 735, 1, 1, '2019-07-06 16:48:56', '2019-07-19 17:16:05');
INSERT INTO `t_currency_optional` VALUES (2, 735, 2, 0, '2019-07-06 17:46:39', '2019-07-19 17:15:52');
INSERT INTO `t_currency_optional` VALUES (3, 735, 4, 0, '2019-07-12 11:00:01', '2019-07-19 17:06:00');
INSERT INTO `t_currency_optional` VALUES (4, 735, 3, 0, '2019-07-19 11:11:24', '2019-07-19 17:15:52');
INSERT INTO `t_currency_optional` VALUES (5, 735, 5, 0, '2019-07-19 11:11:40', '2019-07-19 16:58:00');
INSERT INTO `t_currency_optional` VALUES (6, 735, 7, 1, '2019-07-19 17:00:46', '2019-07-19 17:12:04');
INSERT INTO `t_currency_optional` VALUES (7, 735, 8, 0, '2019-07-19 17:00:47', '2019-07-19 17:15:55');
INSERT INTO `t_currency_optional` VALUES (8, 735, 9, 1, '2019-07-19 17:12:05', '2019-07-19 17:12:42');

-- ----------------------------
-- Table structure for t_currency_trade
-- ----------------------------
DROP TABLE IF EXISTS `t_currency_trade`;
CREATE TABLE `t_currency_trade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `symbol` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易对,如BTC/USDT',
  `currency_id1` int(11) NULL DEFAULT NULL COMMENT '货币id，t_currency表中的id',
  `currency_id2` int(11) NULL DEFAULT NULL COMMENT '法币id，t_currency表中的id',
  `price_decimal_digits` int(11) NULL DEFAULT NULL COMMENT '单价小数位',
  `count_decimal_digits` int(11) NULL DEFAULT NULL COMMENT '数量小数位',
  `min_price` decimal(20, 8) NULL DEFAULT 0.00000000 COMMENT '最小挂单单价',
  `min_count` decimal(20, 8) NULL DEFAULT 0.00000000 COMMENT '最小挂单数量',
  `min_amount` decimal(20, 8) NULL DEFAULT 0.00000000 COMMENT '最小挂单金额',
  `max_price` decimal(20, 8) NULL DEFAULT 0.00000000 COMMENT '最大可买单价',
  `max_count` decimal(20, 8) NULL DEFAULT 0.00000000 COMMENT '最大可买数量',
  `max_amount` decimal(20, 8) NULL DEFAULT 0.00000000 COMMENT '最大可买金额',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态：0禁用，1启用',
  `sort` int(4) NULL DEFAULT 1 COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `symbol`(`symbol`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '法币类型匹配表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_currency_trade
-- ----------------------------
INSERT INTO `t_currency_trade` VALUES (1, 'ETH-USDT', 2, 3, 2, 6, 0.00000001, 0.00000001, 0.00000001, 100.00000000, 100000.00000000, 100000000.00000000, 1, 1, '2019-06-26 15:48:49', '2019-06-26 15:48:51');
INSERT INTO `t_currency_trade` VALUES (2, 'LTC-USDT', 4, 3, 2, 6, 0.00000001, 0.00000001, 0.00000001, 100.00000000, 100000.00000000, 100000000.00000000, 1, 3, '2019-07-19 11:02:26', '2019-07-19 11:02:29');
INSERT INTO `t_currency_trade` VALUES (3, 'EOS-USDT', 5, 3, 3, 4, 0.00000001, 0.00000001, 0.00000001, 100.00000000, 100000.00000000, 100000000.00000000, 1, 4, '2019-07-19 11:04:08', '2019-07-19 11:04:10');
INSERT INTO `t_currency_trade` VALUES (4, 'XRP-USDT', 6, 3, 4, 3, 0.00000001, 0.00000001, 0.00000001, 100.00000000, 100000.00000000, 100000000.00000000, 1, 5, '2019-07-19 11:04:43', '2019-07-19 11:04:47');
INSERT INTO `t_currency_trade` VALUES (5, 'BTC-USDT', 1, 3, 1, 8, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 1, 6, '2019-08-03 17:20:06', '2019-08-03 17:20:08');
INSERT INTO `t_currency_trade` VALUES (6, 'ETC-USDT', 7, 3, 3, 5, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 1, 7, '2019-08-03 17:25:15', '2019-08-03 17:25:17');
INSERT INTO `t_currency_trade` VALUES (7, 'BCH-USDT', 8, 3, 2, 4, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 1, 8, '2019-08-03 17:27:31', '2019-08-03 17:27:33');
INSERT INTO `t_currency_trade` VALUES (8, 'TRX-USDT', 9, 3, 5, 2, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 1, 9, '2019-08-03 17:31:24', '2019-08-03 17:31:27');
INSERT INTO `t_currency_trade` VALUES (9, 'BSV-USDT', 10, 3, 2, 4, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 0.00000000, 1, 1, '2019-08-03 17:33:48', '2019-08-03 17:33:50');

-- ----------------------------
-- Table structure for t_entrust
-- ----------------------------
DROP TABLE IF EXISTS `t_entrust`;
CREATE TABLE `t_entrust`  (
  `id` bigint(64) NOT NULL COMMENT '委托表ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `currency_trade_id` int(11) NULL DEFAULT NULL COMMENT '交易对id,t_currency_trade中的id',
  `price` decimal(20, 10) NULL DEFAULT NULL COMMENT '价格',
  `amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '金额',
  `success_amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '成功撮合金额',
  `count` decimal(20, 8) NULL DEFAULT NULL COMMENT '委托数量',
  `left_count` decimal(20, 8) NULL DEFAULT NULL COMMENT '未成交数量',
  `fees` decimal(20, 8) NULL DEFAULT NULL COMMENT '总手续费',
  `left_fees` decimal(20, 8) NULL DEFAULT NULL COMMENT '剩余的手续费(未成交完全时)',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态:1未完成,2部分成交,3完全成交,4用户撤销',
  `entrust_type` int(11) NULL DEFAULT NULL COMMENT '委托类型:0市价交易,1限价交易',
  `entrust_direction` int(11) NULL DEFAULT NULL COMMENT '委托方向:0买入,1卖出',
  `version` int(11) NULL DEFAULT 1 COMMENT '版本号，每更新一次数据加1',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id_status`(`user_id`, `status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '委托交易表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_entrust_record
-- ----------------------------
DROP TABLE IF EXISTS `t_entrust_record`;
CREATE TABLE `t_entrust_record`  (
  `id` bigint(64) NOT NULL COMMENT '委托明细表id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `rival_user_id` bigint(20) NULL DEFAULT NULL COMMENT '对手方用户id',
  `entrust_id` bigint(64) NULL DEFAULT NULL COMMENT '委托单id,t_fentrust.id',
  `rival_entrust_id` bigint(64) NULL DEFAULT NULL COMMENT '对手委托单id,t_fentrust.id',
  `amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '总金额',
  `price` decimal(20, 10) NULL DEFAULT NULL COMMENT '价格',
  `count` decimal(20, 8) NULL DEFAULT NULL COMMENT '数量',
  `rate` decimal(20, 6) NULL DEFAULT NULL COMMENT '费率',
  `fees` decimal(20, 8) NULL DEFAULT NULL COMMENT '费用',
  `currency_trade_id` int(11) NULL DEFAULT NULL COMMENT '交易对id,t_currency_trade中的id',
  `is_active` tinyint(1) NULL DEFAULT NULL COMMENT '是否主动:1-主买主卖，0-被买被卖',
  `entrust_direction` int(11) NULL DEFAULT NULL COMMENT '交易类型:0-买，1-卖',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_create_time`(`create_time`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE,
  INDEX `index_currency_trade_id`(`currency_trade_id`) USING BTREE,
  INDEX `index_entrust_direction`(`entrust_direction`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '委托交易日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_kline
-- ----------------------------
DROP TABLE IF EXISTS `t_kline`;
CREATE TABLE `t_kline`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `symbol` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '如:btc_usdt',
  `low` decimal(20, 10) NULL DEFAULT NULL COMMENT '最低价格',
  `high` decimal(20, 10) NULL DEFAULT NULL COMMENT '最高价格',
  `open` decimal(20, 10) NULL DEFAULT NULL COMMENT '开盘价格',
  `close` decimal(20, 10) NULL DEFAULT NULL COMMENT '收盘价格',
  `volume` decimal(30, 10) NULL DEFAULT NULL COMMENT '交易量',
  `granularity` int(1) NULL DEFAULT NULL COMMENT '时间粒度，以秒为单位，如[60/180/300/900/1800/3600/7200/14400/21600/43200/86400/604800]',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_symbol_granularity_time`(`symbol`, `granularity`, `time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'k线数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_legal_currency_account
-- ----------------------------
DROP TABLE IF EXISTS `t_legal_currency_account`;
CREATE TABLE `t_legal_currency_account`  (
  `id` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(64) NOT NULL COMMENT '用户id',
  `coin_id` int(11) NOT NULL COMMENT '法币币种id',
  `balance_amount` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '可用余额',
  `freeze_amount` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '冻结金额',
  `c2c_already_deal_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'c2c已成交数量',
  `c2c_total_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'c2c总成交数量',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号，每更新一次数据加1',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_user_coin_id`(`user_id`, `coin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1164376152467324931 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '法币账户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_legal_currency_account
-- ----------------------------
INSERT INTO `t_legal_currency_account` VALUES (1, 1, 1, 920.00000000, 80.00000000, 4, 4, 0, '2019-08-22 10:08:53', '2019-08-22 10:08:53');
INSERT INTO `t_legal_currency_account` VALUES (2, 1, 2, 2000.00000000, 0.00000000, 0, 0, 0, '2019-08-22 10:09:04', '2019-08-22 10:09:04');
INSERT INTO `t_legal_currency_account` VALUES (1164376152307941377, 2, 1, 0.00000000, 0.00000000, 0, 0, 0, '2019-08-22 11:18:02', '2019-08-22 11:18:02');
INSERT INTO `t_legal_currency_account` VALUES (1164376152467324930, 2, 2, 0.00000000, 0.00000000, 0, 0, 0, '2019-08-22 11:18:02', '2019-08-22 11:18:02');

-- ----------------------------
-- Table structure for t_legal_currency_coin
-- ----------------------------
DROP TABLE IF EXISTS `t_legal_currency_coin`;
CREATE TABLE `t_legal_currency_coin`  (
  `id` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称（英文）',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题（中文）',
  `img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(0 禁用 1 启用)',
  `min_quota` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '发布广告最小数量',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '法币币种表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_legal_currency_coin
-- ----------------------------
INSERT INTO `t_legal_currency_coin` VALUES (1, 'USDT', '泰達幣', NULL, 1, 10.00000000, '2019-08-17 17:14:53', '2019-08-17 17:14:53');
INSERT INTO `t_legal_currency_coin` VALUES (2, 'BTC', '比特幣', NULL, 1, 2.00000000, '2019-08-17 17:25:54', '2019-08-17 17:25:54');

-- ----------------------------
-- Table structure for t_legal_currency_record
-- ----------------------------
DROP TABLE IF EXISTS `t_legal_currency_record`;
CREATE TABLE `t_legal_currency_record`  (
  `id` bigint(64) NOT NULL COMMENT '主键id',
  `user_id` bigint(64) NOT NULL COMMENT '用户id',
  `coin_id` int(11) NOT NULL COMMENT '法币币种id',
  `before_amount` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '成交前金额',
  `change_amount` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '变动金额 (流入 +，流出 -)',
  `after_amount` decimal(20, 8) NOT NULL DEFAULT 0.00000000 COMMENT '成交前金额',
  `type` int(1) NOT NULL COMMENT '流水类型, 1:  法币买入 2：法币出售，3:法币钱包-->币币钱包 4：法币钱包-->资金钱包  5：资金钱包 --> 法币钱包  6：币币钱包 -->法币钱包',
  `biz_order_id` tinyint(64) NULL DEFAULT NULL COMMENT '业务订单id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_coin_id`(`user_id`, `coin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '法币流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_legal_currency_record
-- ----------------------------
INSERT INTO `t_legal_currency_record` VALUES (361228616866209792, 1, 1, 10000.00000000, 1.00000000, 10001.00000000, 6, NULL, '2019-08-20 16:34:39');
INSERT INTO `t_legal_currency_record` VALUES (361230241722802176, 1, 1, 10001.00000000, -1.00000000, 10000.00000000, 3, NULL, '2019-08-20 16:41:07');

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
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL COMMENT '提币用户',
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单id',
  `fee` decimal(10, 0) NULL DEFAULT NULL COMMENT '手续费',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '具体币种',
  `amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '提币数量',
  `receiver_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接收地址',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '审核状态',
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, NULL, '345987706037342208', NULL, NULL, NULL, NULL, '未审核', NULL, '2019-07-09 15:12:44', '2019-07-09 15:12:44');

-- ----------------------------
-- Table structure for t_param_config
-- ----------------------------
DROP TABLE IF EXISTS `t_param_config`;
CREATE TABLE `t_param_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `param_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数键',
  `param_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `param_status` int(1) NULL DEFAULT NULL COMMENT '启动状态,1：开启, 0:不开启',
  `param_remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数备注',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_param_config
-- ----------------------------
INSERT INTO `t_param_config` VALUES (1, 'okexUsdToCnyRateKey', 'https://www.okex.me/api/swap/v3/rate', 1, '获取okex法币汇率URL', '2019-07-04 15:00:29', 'sys', NULL, NULL);
INSERT INTO `t_param_config` VALUES (2, 'okexKlineHistorySwitchKey', 'off', 1, '获取K线历史数据开关', '2019-07-05 10:10:13', 'sys', NULL, NULL);
INSERT INTO `t_param_config` VALUES (3, 'okexKlineUrlKey', 'https://www.okex.me/api/spot/v3/instruments/{0}/candles?granularity={1}', 1, 'okex-K线地址', '2019-07-05 10:23:33', 'sys', NULL, NULL);
INSERT INTO `t_param_config` VALUES (4, 'okexSymbolKlineHistoryDataKey', 'BTC-USDT,ETH-USDT,LTC-USDT,EOS-USDT', 1, '拉取指定okex交易对历史数据', '2019-07-05 10:35:07', 'sys', NULL, NULL);
INSERT INTO `t_param_config` VALUES (5, 'okexGranularitysKey', '60,180,300,900,1800,3600,7200,14400,21600,43200,86400,604800', 1, 'okex-获取K线数据的时间粒度', '2019-07-05 10:46:31', 'sys', NULL, NULL);
INSERT INTO `t_param_config` VALUES (6, 'okexWebsocketUrlKey', 'wss://real.okex.com:10442/ws/v3', 1, 'okex-WebSocket地址', '2019-07-06 16:10:33', 'sys', NULL, NULL);
INSERT INTO `t_param_config` VALUES (7, 'okexTickerHttpUrlKey', 'https://www.okex.me/api/spot/v3/instruments/{0}/ticker', 1, 'okex-Ticker地址', '2019-07-08 11:00:14', 'sys', NULL, NULL);
INSERT INTO `t_param_config` VALUES (8, 'usdtMaxFee', '10', 1, 'usdt提币最大费率', NULL, NULL, NULL, NULL);
INSERT INTO `t_param_config` VALUES (9, 'usdtMinFee', '4', 1, 'usdt最小提币费率', NULL, NULL, NULL, NULL);
INSERT INTO `t_param_config` VALUES (10, 'btcMaxFee', '0.001', 1, 'btc最大提币费率', NULL, NULL, NULL, NULL);
INSERT INTO `t_param_config` VALUES (11, 'btcMinFee', '0.0005', 1, 'btc最小提币费率', NULL, NULL, NULL, NULL);
INSERT INTO `t_param_config` VALUES (12, 'btcMin', '0.01', 1, '比特币最小提币数量', '2019-08-27 12:45:23', NULL, '2019-08-27 12:45:30', NULL);
INSERT INTO `t_param_config` VALUES (13, 'btcMax', '2', 1, '比特币大提币额度', NULL, NULL, NULL, NULL);
INSERT INTO `t_param_config` VALUES (14, 'usdtMin', '10', 1, 'usdt最小提币数量', NULL, NULL, NULL, NULL);
INSERT INTO `t_param_config` VALUES (15, 'usdtMax', '20000', 1, 'usdt最大提币数量', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_payment_mode
-- ----------------------------
DROP TABLE IF EXISTS `t_payment_mode`;
CREATE TABLE `t_payment_mode`  (
  `id` bigint(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(64) UNSIGNED NOT NULL COMMENT '用户id',
  `type` tinyint(1) NOT NULL COMMENT '类型 1；银行卡 2：支付宝 3：微信',
  `true_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名或昵称',
  `card` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '帐号',
  `bank_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `bank_branch` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户支行',
  `qr_code` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态：0，禁用；1，启用；',
  `display_setting` tinyint(1) NOT NULL COMMENT '展示设置 1：用于收款 2:用于付款 3：用于收付款',
  `belong_currency` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属币种 如：人民币CNY',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_record_log
-- ----------------------------
DROP TABLE IF EXISTS `t_record_log`;
CREATE TABLE `t_record_log`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `equipment_type` tinyint(1) NOT NULL COMMENT '设备类型：',
  `business_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务类型',
  `equipment_mark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发送的地址',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '信息发送日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_record_log
-- ----------------------------
INSERT INTO `t_record_log` VALUES (1, 1, 'RECEIVING_TEMPLATE', '8616675368716', '【Bitrade】尊敬的用户您好,您的验证码是:494044,验证码有效时间10分钟！', '2019-08-29 17:55:18');
INSERT INTO `t_record_log` VALUES (2, 1, 'RECEIVING_TEMPLATE', '8616675368716', '【Bitrade】尊敬的用户您好,您的验证码是:494044,验证码有效时间10分钟！', '2019-08-29 18:00:18');
INSERT INTO `t_record_log` VALUES (3, 1, 'RECEIVING_TEMPLATE', '8617687041818', '【Bitrade】尊敬的用户您好,您的验证码是:219326,验证码有效时间10分钟！', '2019-08-29 18:02:40');
INSERT INTO `t_record_log` VALUES (4, 1, 'RECEIVING_TEMPLATE', '8617687041818', '【Bitrade】尊敬的用户您好,您的验证码是:269813,验证码有效时间10分钟！', '2019-08-29 18:23:39');
INSERT INTO `t_record_log` VALUES (5, 1, 'RECEIVING_TEMPLATE', 'xiao_zichao@live.com', '【Bitrade】尊敬的用户您好,您的验证码是:486534,验证码有效时间10分钟！', '2019-08-29 20:38:39');

-- ----------------------------
-- Table structure for t_ticker
-- ----------------------------
DROP TABLE IF EXISTS `t_ticker`;
CREATE TABLE `t_ticker`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `symbol` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '币对名称',
  `last` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最新成交价[收盘价]',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '行情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_transfer_direction
-- ----------------------------
DROP TABLE IF EXISTS `t_transfer_direction`;
CREATE TABLE `t_transfer_direction`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `main_account_id` int(11) NOT NULL COMMENT '主账户',
  `target_account_id` int(11) NOT NULL COMMENT '目标账户',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态：0禁用，1启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '划转配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_transfer_direction
-- ----------------------------
INSERT INTO `t_transfer_direction` VALUES (1, '2019-08-21 18:00:00', '2019-08-21 18:00:00', 1, 2, 0);
INSERT INTO `t_transfer_direction` VALUES (2, '2019-08-21 18:00:09', '2019-08-21 18:00:09', 1, 3, 1);
INSERT INTO `t_transfer_direction` VALUES (3, '2019-08-21 18:00:16', '2019-08-21 18:00:16', 2, 1, 1);
INSERT INTO `t_transfer_direction` VALUES (4, '2019-08-21 18:00:23', '2019-08-21 18:00:23', 2, 3, 1);
INSERT INTO `t_transfer_direction` VALUES (5, '2019-08-21 18:00:29', '2019-08-21 18:00:29', 3, 1, 1);
INSERT INTO `t_transfer_direction` VALUES (6, '2019-08-21 18:00:36', '2019-08-21 18:00:36', 3, 2, 1);

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
-- Table structure for t_user_account
-- ----------------------------
DROP TABLE IF EXISTS `t_user_account`;
CREATE TABLE `t_user_account`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `login_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `fhas_real_Validate` tinyint(1) NOT NULL COMMENT '证件审核：0未提交，1通过，2审核中，3审核拒绝\'',
  `balance` decimal(15, 4) NOT NULL DEFAULT 0.0000 COMMENT '账户余额',
  `balance_token` decimal(15, 4) NOT NULL DEFAULT 0.0000 COMMENT '体验金余额',
  `frozen_amount` decimal(15, 4) NOT NULL DEFAULT 0.0000 COMMENT '冻结金额(提现)',
  `total_deposits` decimal(15, 4) NOT NULL DEFAULT 0.0000 COMMENT '总入金(充值)',
  `total_recharge_token` decimal(15, 4) NOT NULL DEFAULT 0.0000 COMMENT '体验金总充值',
  `total_withdrawals` decimal(15, 4) NOT NULL DEFAULT 0.0000 COMMENT '总出金(提现)',
  `used_margin` decimal(15, 4) NOT NULL DEFAULT 0.0000 COMMENT '占用保证金(下单冻结)',
  `used_token` decimal(15, 4) NOT NULL DEFAULT 0.0000 COMMENT '体验金下单冻结',
  `token_number` int(11) NOT NULL DEFAULT 0 COMMENT '体验金剩余使用次数',
  `internal_account` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否内部账户 1=正常账户、2=内部账户',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号，每更新一次数据加1',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_account
-- ----------------------------
INSERT INTO `t_user_account` VALUES (1, 10086, 'hrd', 'hrd', 1, 10000.0000, 10000.0000, 0.0000, 100.0000, 600.0000, 10000.0000, 0.0000, 0.0000, 0, 1, 0, NULL, NULL);

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

-- ----------------------------
-- Table structure for t_user_capital_account_record
-- ----------------------------
DROP TABLE IF EXISTS `t_user_capital_account_record`;
CREATE TABLE `t_user_capital_account_record`  (
  `id` bigint(64) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) NULL DEFAULT NULL COMMENT '货币id，t_currency表中的id',
  `count` decimal(20, 8) NULL DEFAULT NULL COMMENT '数量',
  `adderess` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `fees` decimal(20, 8) NULL DEFAULT NULL COMMENT '手续费',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型:1:充币,2提币,3:撤销提现',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态:0:失败,1:成功',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户资金账户记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_wallet
-- ----------------------------
DROP TABLE IF EXISTS `t_wallet`;
CREATE TABLE `t_wallet`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) NULL DEFAULT NULL COMMENT '货币id(t_currency.id)',
  `total` decimal(20, 8) NULL DEFAULT NULL COMMENT '可用总数量',
  `trade_frozen` decimal(20, 8) NULL DEFAULT NULL COMMENT '交易冻结数量',
  `transfer_frozen` decimal(20, 8) NULL DEFAULT NULL COMMENT '划转冻结数量',
  `version` int(11) NULL DEFAULT 1 COMMENT '版本号，每更新一次数据加1',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_user_currency_id`(`user_id`, `currency_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6170827 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '虚拟币钱包表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wallet
-- ----------------------------
INSERT INTO `t_wallet` VALUES (36239, 841, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-24 10:05:48', '2019-07-25 20:04:30');
INSERT INTO `t_wallet` VALUES (36240, 841, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-24 10:05:48', '2019-07-25 20:04:30');
INSERT INTO `t_wallet` VALUES (36247, 105, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-24 10:05:58', '2019-07-25 19:53:50');
INSERT INTO `t_wallet` VALUES (36248, 105, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-24 10:05:58', '2019-07-25 19:53:50');
INSERT INTO `t_wallet` VALUES (74963, 259, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-24 10:29:35', '2019-07-25 20:01:40');
INSERT INTO `t_wallet` VALUES (74964, 259, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-24 10:29:35', '2019-07-25 20:01:40');
INSERT INTO `t_wallet` VALUES (74965, 126, 3, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-24 10:29:35', '2019-07-25 19:53:26');
INSERT INTO `t_wallet` VALUES (74966, 126, 2, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-24 10:29:35', '2019-07-25 19:53:26');
INSERT INTO `t_wallet` VALUES (74967, 713, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-24 10:29:35', '2019-07-25 19:58:53');
INSERT INTO `t_wallet` VALUES (74968, 713, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-24 10:29:35', '2019-07-25 19:58:54');
INSERT INTO `t_wallet` VALUES (74969, 523, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-24 10:29:35', '2019-07-25 19:51:20');
INSERT INTO `t_wallet` VALUES (74970, 523, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-24 10:29:35', '2019-07-25 19:51:20');
INSERT INTO `t_wallet` VALUES (74971, 332, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-24 10:29:35', '2019-07-25 19:58:28');
INSERT INTO `t_wallet` VALUES (74972, 332, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-24 10:29:35', '2019-07-25 19:58:29');
INSERT INTO `t_wallet` VALUES (74973, 348, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-24 10:29:35', '2019-07-25 19:51:15');
INSERT INTO `t_wallet` VALUES (74974, 348, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-24 10:29:35', '2019-07-25 19:51:15');
INSERT INTO `t_wallet` VALUES (74975, 998, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-24 10:29:35', '2019-07-25 20:06:22');
INSERT INTO `t_wallet` VALUES (74976, 998, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-24 10:29:35', '2019-07-25 20:06:38');
INSERT INTO `t_wallet` VALUES (74977, 68, 2, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-24 10:29:36', '2019-07-25 19:54:08');
INSERT INTO `t_wallet` VALUES (74978, 68, 3, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-24 10:29:36', '2019-07-25 19:54:08');
INSERT INTO `t_wallet` VALUES (74979, 181, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-24 10:29:36', '2019-07-25 19:54:52');
INSERT INTO `t_wallet` VALUES (74980, 181, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-24 10:29:36', '2019-07-25 19:54:52');
INSERT INTO `t_wallet` VALUES (1671617, 177, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 07:31:14', '2019-07-25 19:51:07');
INSERT INTO `t_wallet` VALUES (1671618, 177, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 07:31:14', '2019-07-25 19:51:07');
INSERT INTO `t_wallet` VALUES (1671619, 847, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 07:31:15', '2019-07-25 20:01:13');
INSERT INTO `t_wallet` VALUES (1671620, 847, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 07:31:15', '2019-07-25 20:01:13');
INSERT INTO `t_wallet` VALUES (1671721, 340, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 07:31:18', '2019-07-25 19:59:25');
INSERT INTO `t_wallet` VALUES (1671722, 340, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 07:31:18', '2019-07-25 19:59:26');
INSERT INTO `t_wallet` VALUES (1671723, 773, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 07:31:18', '2019-07-25 20:02:58');
INSERT INTO `t_wallet` VALUES (1671724, 773, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 07:31:18', '2019-07-25 20:02:58');
INSERT INTO `t_wallet` VALUES (1672033, 793, 2, 0.00000000, 0.00000000, 0.00000000, 25, '2019-07-25 07:31:29', '2019-07-25 19:43:47');
INSERT INTO `t_wallet` VALUES (1672034, 793, 3, 0.00000000, 0.00000000, 0.00000000, 25, '2019-07-25 07:31:29', '2019-07-25 19:43:47');
INSERT INTO `t_wallet` VALUES (1672035, 777, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 07:31:29', '2019-07-25 19:56:21');
INSERT INTO `t_wallet` VALUES (1672036, 777, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 07:31:29', '2019-07-25 19:56:21');
INSERT INTO `t_wallet` VALUES (1672037, 691, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 07:31:29', '2019-07-25 19:55:11');
INSERT INTO `t_wallet` VALUES (1672038, 691, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 07:31:29', '2019-07-25 19:55:11');
INSERT INTO `t_wallet` VALUES (1672039, 283, 3, 0.00000000, 0.00000000, 0.00000000, 112, '2019-07-25 07:31:29', '2019-07-25 19:34:54');
INSERT INTO `t_wallet` VALUES (1672040, 283, 2, 0.00000000, 0.00000000, 0.00000000, 112, '2019-07-25 07:31:29', '2019-07-25 19:34:54');
INSERT INTO `t_wallet` VALUES (1672377, 423, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 07:31:42', '2019-07-25 19:35:20');
INSERT INTO `t_wallet` VALUES (1672378, 423, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 07:31:42', '2019-07-25 19:35:20');
INSERT INTO `t_wallet` VALUES (1672379, 589, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 07:31:42', '2019-07-25 19:55:42');
INSERT INTO `t_wallet` VALUES (1672380, 589, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 07:31:42', '2019-07-25 19:55:42');
INSERT INTO `t_wallet` VALUES (1672833, 3, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 07:31:58', '2019-07-25 19:34:18');
INSERT INTO `t_wallet` VALUES (1672834, 3, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 07:31:58', '2019-07-25 19:34:18');
INSERT INTO `t_wallet` VALUES (1672835, 507, 3, 0.00000000, 0.00000000, 0.00000000, 85, '2019-07-25 07:31:58', '2019-08-02 10:58:29');
INSERT INTO `t_wallet` VALUES (1672836, 507, 2, 0.00000000, 0.00000000, 0.00000000, 85, '2019-07-25 07:31:58', '2019-08-02 10:58:29');
INSERT INTO `t_wallet` VALUES (1672895, 14, 2, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 07:32:01', '2019-07-25 19:51:00');
INSERT INTO `t_wallet` VALUES (1672896, 14, 3, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 07:32:01', '2019-07-25 19:51:00');
INSERT INTO `t_wallet` VALUES (1672897, 517, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 07:32:01', '2019-07-25 19:53:12');
INSERT INTO `t_wallet` VALUES (1672898, 517, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 07:32:01', '2019-07-25 19:53:12');
INSERT INTO `t_wallet` VALUES (1673139, 785, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 07:32:12', '2019-07-25 20:03:55');
INSERT INTO `t_wallet` VALUES (1673140, 785, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 07:32:12', '2019-07-25 20:03:55');
INSERT INTO `t_wallet` VALUES (1673141, 371, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 07:32:12', '2019-07-25 20:01:38');
INSERT INTO `t_wallet` VALUES (1673142, 371, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 07:32:12', '2019-07-25 20:01:38');
INSERT INTO `t_wallet` VALUES (1673341, 301, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 07:32:19', '2019-07-25 20:11:07');
INSERT INTO `t_wallet` VALUES (1673342, 301, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 07:32:19', '2019-07-25 20:11:07');
INSERT INTO `t_wallet` VALUES (1673343, 490, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 07:32:19', '2019-07-25 20:04:58');
INSERT INTO `t_wallet` VALUES (1673344, 490, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 07:32:19', '2019-07-25 20:04:58');
INSERT INTO `t_wallet` VALUES (1674233, 372, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 16:57:25', '2019-07-25 19:55:58');
INSERT INTO `t_wallet` VALUES (1674234, 372, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 16:57:25', '2019-07-25 19:55:58');
INSERT INTO `t_wallet` VALUES (1674235, 962, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 16:57:25', '2019-07-31 16:03:57');
INSERT INTO `t_wallet` VALUES (1674236, 962, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 16:57:25', '2019-07-31 16:03:57');
INSERT INTO `t_wallet` VALUES (1674433, 422, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 16:58:02', '2019-07-25 19:29:50');
INSERT INTO `t_wallet` VALUES (1674434, 422, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 16:58:02', '2019-07-25 19:29:50');
INSERT INTO `t_wallet` VALUES (1674435, 904, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:58:03', '2019-07-25 20:06:53');
INSERT INTO `t_wallet` VALUES (1674436, 904, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:58:03', '2019-07-25 20:06:53');
INSERT INTO `t_wallet` VALUES (1674591, 808, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 16:58:40', '2019-07-25 19:58:25');
INSERT INTO `t_wallet` VALUES (1674592, 808, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 16:58:40', '2019-07-25 19:58:25');
INSERT INTO `t_wallet` VALUES (1674593, 200, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 16:58:42', '2019-07-25 19:42:48');
INSERT INTO `t_wallet` VALUES (1674594, 200, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 16:58:42', '2019-07-25 19:42:48');
INSERT INTO `t_wallet` VALUES (1674621, 52, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 16:58:47', '2019-07-25 20:04:25');
INSERT INTO `t_wallet` VALUES (1674622, 52, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 16:58:47', '2019-07-25 20:04:25');
INSERT INTO `t_wallet` VALUES (1674623, 870, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:58:47', '2019-07-25 19:58:56');
INSERT INTO `t_wallet` VALUES (1674624, 870, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:58:47', '2019-07-25 19:58:56');
INSERT INTO `t_wallet` VALUES (1674677, 666, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:59:01', '2019-07-25 19:59:27');
INSERT INTO `t_wallet` VALUES (1674678, 666, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:59:01', '2019-07-25 19:59:28');
INSERT INTO `t_wallet` VALUES (1674679, 388, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 16:59:01', '2019-07-25 19:57:22');
INSERT INTO `t_wallet` VALUES (1674680, 388, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 16:59:01', '2019-07-25 19:57:22');
INSERT INTO `t_wallet` VALUES (1674691, 220, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 16:59:05', '2019-07-25 19:54:15');
INSERT INTO `t_wallet` VALUES (1674692, 220, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 16:59:05', '2019-07-25 19:54:15');
INSERT INTO `t_wallet` VALUES (1674721, 94, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:59:12', '2019-07-25 20:01:31');
INSERT INTO `t_wallet` VALUES (1674722, 94, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:59:13', '2019-07-25 20:01:31');
INSERT INTO `t_wallet` VALUES (1674723, 806, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 16:59:13', '2019-08-02 11:00:26');
INSERT INTO `t_wallet` VALUES (1674724, 806, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 16:59:13', '2019-08-02 11:00:26');
INSERT INTO `t_wallet` VALUES (1674755, 561, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:59:19', '2019-07-25 19:43:56');
INSERT INTO `t_wallet` VALUES (1674756, 561, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:59:19', '2019-07-25 19:43:56');
INSERT INTO `t_wallet` VALUES (1674757, 642, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 16:59:19', '2019-07-25 20:01:30');
INSERT INTO `t_wallet` VALUES (1674758, 642, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 16:59:19', '2019-07-25 20:01:30');
INSERT INTO `t_wallet` VALUES (1674785, 874, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 16:59:24', '2019-07-25 19:34:55');
INSERT INTO `t_wallet` VALUES (1674786, 874, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 16:59:24', '2019-07-25 19:34:55');
INSERT INTO `t_wallet` VALUES (1674787, 886, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:59:24', '2019-07-25 19:47:41');
INSERT INTO `t_wallet` VALUES (1674788, 886, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 16:59:24', '2019-07-25 19:47:41');
INSERT INTO `t_wallet` VALUES (1674823, 392, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 16:59:28', '2019-07-25 20:04:53');
INSERT INTO `t_wallet` VALUES (1674824, 392, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 16:59:28', '2019-07-25 20:04:53');
INSERT INTO `t_wallet` VALUES (1674825, 582, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 16:59:29', '2019-07-25 19:52:34');
INSERT INTO `t_wallet` VALUES (1674826, 582, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 16:59:29', '2019-07-25 19:52:34');
INSERT INTO `t_wallet` VALUES (1675397, 293, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:00:23', '2019-07-25 19:46:25');
INSERT INTO `t_wallet` VALUES (1675398, 293, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:00:23', '2019-07-25 19:46:25');
INSERT INTO `t_wallet` VALUES (1675399, 335, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:00:23', '2019-07-25 19:36:56');
INSERT INTO `t_wallet` VALUES (1675400, 335, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:00:23', '2019-07-25 19:36:56');
INSERT INTO `t_wallet` VALUES (1675413, 877, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:00:24', '2019-07-25 19:59:17');
INSERT INTO `t_wallet` VALUES (1675414, 877, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:00:24', '2019-07-25 19:59:17');
INSERT INTO `t_wallet` VALUES (1675415, 889, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:00:24', '2019-07-25 19:47:31');
INSERT INTO `t_wallet` VALUES (1675416, 889, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:00:24', '2019-07-25 19:47:31');
INSERT INTO `t_wallet` VALUES (1675793, 991, 2, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:00:54', '2019-07-25 20:01:17');
INSERT INTO `t_wallet` VALUES (1675794, 991, 3, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:00:54', '2019-07-25 20:01:17');
INSERT INTO `t_wallet` VALUES (1675795, 878, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:00:54', '2019-08-02 11:00:11');
INSERT INTO `t_wallet` VALUES (1675796, 878, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:00:54', '2019-08-02 11:00:11');
INSERT INTO `t_wallet` VALUES (1675925, 440, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:01:04', '2019-07-25 19:39:58');
INSERT INTO `t_wallet` VALUES (1675926, 440, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:01:04', '2019-07-25 19:39:58');
INSERT INTO `t_wallet` VALUES (1675927, 154, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:01:05', '2019-07-25 19:49:53');
INSERT INTO `t_wallet` VALUES (1675928, 154, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:01:05', '2019-07-25 19:49:53');
INSERT INTO `t_wallet` VALUES (1676035, 317, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:01:13', '2019-07-25 19:51:17');
INSERT INTO `t_wallet` VALUES (1676036, 317, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:01:13', '2019-07-25 19:51:17');
INSERT INTO `t_wallet` VALUES (1676037, 663, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:01:13', '2019-07-25 19:45:06');
INSERT INTO `t_wallet` VALUES (1676038, 663, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:01:13', '2019-07-25 19:45:06');
INSERT INTO `t_wallet` VALUES (1676071, 258, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:01:16', '2019-07-25 19:49:37');
INSERT INTO `t_wallet` VALUES (1676072, 258, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:01:16', '2019-07-25 19:49:37');
INSERT INTO `t_wallet` VALUES (1676073, 591, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:01:16', '2019-07-25 19:47:45');
INSERT INTO `t_wallet` VALUES (1676074, 591, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:01:16', '2019-07-25 19:47:45');
INSERT INTO `t_wallet` VALUES (1676177, 818, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:01:24', '2019-07-25 19:47:12');
INSERT INTO `t_wallet` VALUES (1676178, 818, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:01:24', '2019-07-25 19:47:12');
INSERT INTO `t_wallet` VALUES (1676179, 369, 3, 0.00000000, 0.00000000, 0.00000000, 55, '2019-07-25 17:01:24', '2019-07-25 19:55:57');
INSERT INTO `t_wallet` VALUES (1676180, 369, 2, 0.00000000, 0.00000000, 0.00000000, 55, '2019-07-25 17:01:24', '2019-07-25 19:55:57');
INSERT INTO `t_wallet` VALUES (1676213, 313, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:01:27', '2019-07-25 19:48:51');
INSERT INTO `t_wallet` VALUES (1676214, 313, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:01:27', '2019-07-25 19:48:51');
INSERT INTO `t_wallet` VALUES (1676215, 390, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:01:27', '2019-07-25 19:59:40');
INSERT INTO `t_wallet` VALUES (1676216, 390, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:01:27', '2019-07-25 19:59:40');
INSERT INTO `t_wallet` VALUES (1676891, 618, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:02:38', '2019-07-25 19:31:49');
INSERT INTO `t_wallet` VALUES (1676892, 618, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:02:38', '2019-07-25 19:31:49');
INSERT INTO `t_wallet` VALUES (1676893, 804, 3, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:02:38', '2019-07-25 19:49:42');
INSERT INTO `t_wallet` VALUES (1676894, 804, 2, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:02:38', '2019-07-25 19:49:42');
INSERT INTO `t_wallet` VALUES (1677275, 935, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:03:08', '2019-07-25 19:33:33');
INSERT INTO `t_wallet` VALUES (1677276, 935, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:03:08', '2019-07-25 19:33:33');
INSERT INTO `t_wallet` VALUES (1677277, 588, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:03:08', '2019-07-25 19:53:46');
INSERT INTO `t_wallet` VALUES (1677278, 588, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:03:08', '2019-07-25 19:53:46');
INSERT INTO `t_wallet` VALUES (1677459, 277, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:03:24', '2019-07-25 19:49:36');
INSERT INTO `t_wallet` VALUES (1677460, 277, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:03:24', '2019-07-25 19:49:36');
INSERT INTO `t_wallet` VALUES (1677461, 246, 3, 0.00000000, 0.00000000, 0.00000000, 28, '2019-07-25 17:03:24', '2019-07-25 19:52:59');
INSERT INTO `t_wallet` VALUES (1677462, 246, 2, 0.00000000, 0.00000000, 0.00000000, 28, '2019-07-25 17:03:24', '2019-07-25 19:52:59');
INSERT INTO `t_wallet` VALUES (1678065, 166, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:04:10', '2019-07-25 19:59:52');
INSERT INTO `t_wallet` VALUES (1678066, 166, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:04:10', '2019-07-25 19:59:52');
INSERT INTO `t_wallet` VALUES (1678067, 712, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:04:10', '2019-07-25 20:04:18');
INSERT INTO `t_wallet` VALUES (1678068, 712, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:04:10', '2019-07-25 20:04:18');
INSERT INTO `t_wallet` VALUES (1678561, 925, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:04:51', '2019-07-25 20:03:45');
INSERT INTO `t_wallet` VALUES (1678562, 925, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:04:51', '2019-07-25 20:03:45');
INSERT INTO `t_wallet` VALUES (1678563, 346, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:04:51', '2019-08-02 10:49:35');
INSERT INTO `t_wallet` VALUES (1678564, 346, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:04:51', '2019-08-02 10:49:35');
INSERT INTO `t_wallet` VALUES (1690205, 95, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:21:57', '2019-07-25 19:50:13');
INSERT INTO `t_wallet` VALUES (1690206, 95, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:21:57', '2019-07-25 19:50:13');
INSERT INTO `t_wallet` VALUES (1690207, 896, 3, 0.00000000, 0.00000000, 0.00000000, 47, '2019-07-25 17:21:57', '2019-07-25 20:04:36');
INSERT INTO `t_wallet` VALUES (1690208, 896, 2, 0.00000000, 0.00000000, 0.00000000, 47, '2019-07-25 17:21:58', '2019-07-25 20:04:36');
INSERT INTO `t_wallet` VALUES (1690215, 379, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:21:58', '2019-07-25 20:00:01');
INSERT INTO `t_wallet` VALUES (1690216, 379, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:21:58', '2019-07-25 20:00:01');
INSERT INTO `t_wallet` VALUES (1690227, 413, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:00', '2019-07-25 19:42:55');
INSERT INTO `t_wallet` VALUES (1690228, 413, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:00', '2019-07-25 19:42:55');
INSERT INTO `t_wallet` VALUES (1690231, 624, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:00', '2019-07-25 19:47:16');
INSERT INTO `t_wallet` VALUES (1690232, 624, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:00', '2019-07-25 19:47:16');
INSERT INTO `t_wallet` VALUES (1690233, 160, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:01', '2019-07-25 19:48:31');
INSERT INTO `t_wallet` VALUES (1690234, 160, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:01', '2019-07-25 19:48:31');
INSERT INTO `t_wallet` VALUES (1690235, 64, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:01', '2019-07-25 20:07:09');
INSERT INTO `t_wallet` VALUES (1690236, 64, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:01', '2019-07-25 20:07:09');
INSERT INTO `t_wallet` VALUES (1690237, 670, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:22:02', '2019-07-25 19:51:34');
INSERT INTO `t_wallet` VALUES (1690238, 670, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:22:02', '2019-07-25 19:51:34');
INSERT INTO `t_wallet` VALUES (1690239, 511, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:02', '2019-07-25 19:36:26');
INSERT INTO `t_wallet` VALUES (1690240, 511, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:02', '2019-07-25 19:36:26');
INSERT INTO `t_wallet` VALUES (1690259, 709, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:04', '2019-07-25 20:00:36');
INSERT INTO `t_wallet` VALUES (1690260, 709, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:04', '2019-07-25 20:00:36');
INSERT INTO `t_wallet` VALUES (1690263, 312, 2, 0.00000000, 0.00000000, 0.00000000, 98, '2019-07-25 17:22:04', '2019-07-25 20:04:21');
INSERT INTO `t_wallet` VALUES (1690264, 312, 3, 0.00000000, 0.00000000, 0.00000000, 98, '2019-07-25 17:22:04', '2019-07-25 20:04:21');
INSERT INTO `t_wallet` VALUES (1690267, 84, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:22:05', '2019-07-25 19:47:54');
INSERT INTO `t_wallet` VALUES (1690268, 84, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:22:05', '2019-07-25 19:47:54');
INSERT INTO `t_wallet` VALUES (1690293, 212, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:07', '2019-07-25 19:50:30');
INSERT INTO `t_wallet` VALUES (1690294, 212, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:07', '2019-07-25 19:50:30');
INSERT INTO `t_wallet` VALUES (1690295, 603, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:22:07', '2019-07-25 19:23:54');
INSERT INTO `t_wallet` VALUES (1690296, 603, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:22:07', '2019-07-25 19:23:54');
INSERT INTO `t_wallet` VALUES (1690299, 600, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:08', '2019-07-25 19:54:58');
INSERT INTO `t_wallet` VALUES (1690300, 600, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:08', '2019-07-25 19:54:58');
INSERT INTO `t_wallet` VALUES (1690303, 992, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:08', '2019-07-25 20:01:40');
INSERT INTO `t_wallet` VALUES (1690304, 992, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:08', '2019-07-25 20:01:40');
INSERT INTO `t_wallet` VALUES (1690315, 36, 2, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:22:09', '2019-07-25 19:51:01');
INSERT INTO `t_wallet` VALUES (1690316, 36, 3, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:22:09', '2019-07-25 19:51:01');
INSERT INTO `t_wallet` VALUES (1690317, 710, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:22:09', '2019-07-25 19:20:49');
INSERT INTO `t_wallet` VALUES (1690318, 710, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:22:09', '2019-07-25 19:20:49');
INSERT INTO `t_wallet` VALUES (1690321, 28, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:22:10', '2019-07-25 19:56:21');
INSERT INTO `t_wallet` VALUES (1690322, 28, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:22:10', '2019-07-25 19:56:21');
INSERT INTO `t_wallet` VALUES (1690371, 89, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:14', '2019-07-25 19:57:01');
INSERT INTO `t_wallet` VALUES (1690372, 89, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:15', '2019-07-25 19:57:01');
INSERT INTO `t_wallet` VALUES (1690373, 375, 3, 0.00000000, 0.00000000, 0.00000000, 31, '2019-07-25 17:22:15', '2019-07-25 19:28:34');
INSERT INTO `t_wallet` VALUES (1690374, 375, 2, 0.00000000, 0.00000000, 0.00000000, 31, '2019-07-25 17:22:15', '2019-07-25 19:28:34');
INSERT INTO `t_wallet` VALUES (1690383, 780, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:22:16', '2019-07-25 20:01:30');
INSERT INTO `t_wallet` VALUES (1690384, 780, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:22:16', '2019-07-25 20:01:30');
INSERT INTO `t_wallet` VALUES (1690389, 44, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:22:17', '2019-07-25 19:21:18');
INSERT INTO `t_wallet` VALUES (1690390, 44, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:22:17', '2019-07-25 19:21:18');
INSERT INTO `t_wallet` VALUES (1690395, 364, 2, 0.00000000, 0.00000000, 0.00000000, 51, '2019-07-25 17:22:18', '2019-07-25 20:02:02');
INSERT INTO `t_wallet` VALUES (1690396, 364, 3, 0.00000000, 0.00000000, 0.00000000, 51, '2019-07-25 17:22:18', '2019-07-25 20:02:02');
INSERT INTO `t_wallet` VALUES (1690407, 263, 2, 0.00000000, 0.00000001, 0.00000000, 10, '2019-07-25 17:22:19', '2019-07-25 19:31:09');
INSERT INTO `t_wallet` VALUES (1690408, 263, 3, -0.00000098, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:19', '2019-07-25 19:31:09');
INSERT INTO `t_wallet` VALUES (1690411, 784, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:19', '2019-07-25 19:39:39');
INSERT INTO `t_wallet` VALUES (1690412, 784, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:19', '2019-07-25 19:39:39');
INSERT INTO `t_wallet` VALUES (1690413, 674, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:22:20', '2019-07-25 20:00:14');
INSERT INTO `t_wallet` VALUES (1690414, 674, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:22:20', '2019-07-25 20:00:14');
INSERT INTO `t_wallet` VALUES (1690419, 156, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:22:20', '2019-07-25 20:04:52');
INSERT INTO `t_wallet` VALUES (1690420, 156, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:22:20', '2019-07-25 20:04:52');
INSERT INTO `t_wallet` VALUES (1690423, 550, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:21', '2019-07-25 20:01:55');
INSERT INTO `t_wallet` VALUES (1690424, 550, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:21', '2019-07-25 20:01:55');
INSERT INTO `t_wallet` VALUES (1690425, 297, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:22:21', '2019-07-25 19:22:52');
INSERT INTO `t_wallet` VALUES (1690426, 297, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:22:21', '2019-07-25 19:22:52');
INSERT INTO `t_wallet` VALUES (1690429, 833, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:22', '2019-07-25 19:55:03');
INSERT INTO `t_wallet` VALUES (1690430, 833, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:22', '2019-07-25 19:55:04');
INSERT INTO `t_wallet` VALUES (1690433, 629, 2, 0.00000000, 0.00000000, 0.00000000, 444, '2019-07-25 17:22:22', '2019-07-25 20:02:57');
INSERT INTO `t_wallet` VALUES (1690434, 629, 3, 0.00000000, 0.00000000, 0.00000000, 444, '2019-07-25 17:22:22', '2019-07-25 20:02:58');
INSERT INTO `t_wallet` VALUES (1690435, 669, 2, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:22:23', '2019-07-25 20:00:57');
INSERT INTO `t_wallet` VALUES (1690436, 669, 3, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:22:23', '2019-07-25 20:00:57');
INSERT INTO `t_wallet` VALUES (1690437, 120, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:22:23', '2019-07-25 19:50:41');
INSERT INTO `t_wallet` VALUES (1690438, 120, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:22:23', '2019-07-25 19:50:41');
INSERT INTO `t_wallet` VALUES (1690445, 171, 2, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:22:24', '2019-07-25 20:06:41');
INSERT INTO `t_wallet` VALUES (1690446, 171, 3, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:22:24', '2019-07-25 20:06:42');
INSERT INTO `t_wallet` VALUES (1690455, 223, 2, -0.00000001, 0.00000000, 0.00000000, 21, '2019-07-25 17:22:25', '2019-07-25 19:40:08');
INSERT INTO `t_wallet` VALUES (1690456, 223, 3, 0.00000000, 0.00000098, 0.00000000, 21, '2019-07-25 17:22:25', '2019-07-25 19:40:08');
INSERT INTO `t_wallet` VALUES (1690465, 58, 2, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:22:26', '2019-08-02 10:52:42');
INSERT INTO `t_wallet` VALUES (1690466, 58, 3, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:22:26', '2019-08-02 10:52:42');
INSERT INTO `t_wallet` VALUES (1690497, 79, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:22:28', '2019-07-25 19:48:17');
INSERT INTO `t_wallet` VALUES (1690498, 79, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:22:28', '2019-07-25 19:48:17');
INSERT INTO `t_wallet` VALUES (1690503, 426, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:22:29', '2019-07-25 19:55:28');
INSERT INTO `t_wallet` VALUES (1690504, 426, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:22:29', '2019-07-25 19:55:28');
INSERT INTO `t_wallet` VALUES (1690511, 630, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:30', '2019-07-25 19:33:18');
INSERT INTO `t_wallet` VALUES (1690512, 630, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:30', '2019-07-25 19:33:18');
INSERT INTO `t_wallet` VALUES (1690513, 174, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:30', '2019-07-25 19:54:53');
INSERT INTO `t_wallet` VALUES (1690514, 174, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:30', '2019-07-25 19:54:53');
INSERT INTO `t_wallet` VALUES (1690515, 798, 2, 0.00000000, 0.00000000, 0.00000000, 34, '2019-07-25 17:22:30', '2019-07-25 20:03:36');
INSERT INTO `t_wallet` VALUES (1690516, 798, 3, 0.00000000, 0.00000000, 0.00000000, 34, '2019-07-25 17:22:30', '2019-07-25 20:03:36');
INSERT INTO `t_wallet` VALUES (1690519, 891, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:22:31', '2019-08-02 11:00:26');
INSERT INTO `t_wallet` VALUES (1690520, 891, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:22:31', '2019-08-02 11:00:26');
INSERT INTO `t_wallet` VALUES (1690525, 435, 2, 0.00000000, 0.00000000, 0.00000000, 25, '2019-07-25 17:22:32', '2019-07-25 19:48:03');
INSERT INTO `t_wallet` VALUES (1690526, 435, 3, 0.00000000, 0.00000000, 0.00000000, 25, '2019-07-25 17:22:32', '2019-07-25 19:48:03');
INSERT INTO `t_wallet` VALUES (1690527, 873, 3, 0.00000000, 0.00000000, 0.00000000, 25, '2019-07-25 17:22:32', '2019-07-25 19:56:12');
INSERT INTO `t_wallet` VALUES (1690528, 873, 2, 0.00000000, 0.00000000, 0.00000000, 25, '2019-07-25 17:22:32', '2019-07-25 19:56:12');
INSERT INTO `t_wallet` VALUES (1690537, 494, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:22:33', '2019-07-25 20:04:43');
INSERT INTO `t_wallet` VALUES (1690538, 494, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:22:33', '2019-07-25 20:04:43');
INSERT INTO `t_wallet` VALUES (1690543, 760, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:22:34', '2019-07-25 19:38:35');
INSERT INTO `t_wallet` VALUES (1690544, 760, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:22:34', '2019-07-25 19:38:35');
INSERT INTO `t_wallet` VALUES (1690549, 234, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:35', '2019-07-25 19:41:53');
INSERT INTO `t_wallet` VALUES (1690550, 234, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:35', '2019-07-25 19:41:53');
INSERT INTO `t_wallet` VALUES (1690559, 481, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:22:37', '2019-07-25 19:54:49');
INSERT INTO `t_wallet` VALUES (1690560, 481, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:22:37', '2019-07-25 19:54:50');
INSERT INTO `t_wallet` VALUES (1690579, 617, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:22:38', '2019-07-25 19:36:55');
INSERT INTO `t_wallet` VALUES (1690580, 617, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:22:38', '2019-07-25 19:36:55');
INSERT INTO `t_wallet` VALUES (1690585, 545, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:39', '2019-07-25 20:08:39');
INSERT INTO `t_wallet` VALUES (1690586, 545, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:39', '2019-07-25 20:08:39');
INSERT INTO `t_wallet` VALUES (1690589, 884, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:22:39', '2019-07-25 19:59:35');
INSERT INTO `t_wallet` VALUES (1690590, 884, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:22:39', '2019-07-25 19:59:35');
INSERT INTO `t_wallet` VALUES (1690591, 196, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:40', '2019-07-25 19:52:49');
INSERT INTO `t_wallet` VALUES (1690592, 196, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:40', '2019-07-25 19:52:49');
INSERT INTO `t_wallet` VALUES (1690595, 27, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:40', '2019-07-25 20:00:22');
INSERT INTO `t_wallet` VALUES (1690596, 27, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:40', '2019-07-25 20:00:22');
INSERT INTO `t_wallet` VALUES (1690605, 169, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:41', '2019-07-25 19:58:40');
INSERT INTO `t_wallet` VALUES (1690606, 169, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:41', '2019-07-25 19:58:40');
INSERT INTO `t_wallet` VALUES (1690617, 745, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:22:42', '2019-07-25 19:57:13');
INSERT INTO `t_wallet` VALUES (1690618, 745, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:22:42', '2019-07-25 19:57:13');
INSERT INTO `t_wallet` VALUES (1690619, 934, 2, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:22:43', '2019-07-25 19:59:16');
INSERT INTO `t_wallet` VALUES (1690620, 934, 3, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:22:43', '2019-07-25 19:59:16');
INSERT INTO `t_wallet` VALUES (1690621, 288, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:43', '2019-07-25 20:07:11');
INSERT INTO `t_wallet` VALUES (1690622, 288, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:43', '2019-07-25 20:07:09');
INSERT INTO `t_wallet` VALUES (1690635, 964, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:44', '2019-07-25 20:01:13');
INSERT INTO `t_wallet` VALUES (1690636, 964, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:44', '2019-07-25 20:01:13');
INSERT INTO `t_wallet` VALUES (1690637, 194, 3, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:22:44', '2019-07-25 19:56:34');
INSERT INTO `t_wallet` VALUES (1690638, 194, 2, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:22:44', '2019-07-25 19:56:33');
INSERT INTO `t_wallet` VALUES (1690641, 653, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:22:45', '2019-07-25 20:03:22');
INSERT INTO `t_wallet` VALUES (1690642, 653, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:22:45', '2019-07-25 20:03:22');
INSERT INTO `t_wallet` VALUES (1690647, 698, 2, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:22:45', '2019-07-25 19:50:45');
INSERT INTO `t_wallet` VALUES (1690648, 698, 3, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:22:46', '2019-07-25 19:50:45');
INSERT INTO `t_wallet` VALUES (1690663, 83, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:47', '2019-08-02 10:56:06');
INSERT INTO `t_wallet` VALUES (1690664, 83, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:22:47', '2019-08-02 10:56:06');
INSERT INTO `t_wallet` VALUES (1690677, 813, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:22:48', '2019-07-25 19:53:13');
INSERT INTO `t_wallet` VALUES (1690678, 813, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:22:48', '2019-07-25 19:53:14');
INSERT INTO `t_wallet` VALUES (1690691, 40, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:22:50', '2019-07-25 19:55:29');
INSERT INTO `t_wallet` VALUES (1690692, 40, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:22:50', '2019-07-25 19:55:29');
INSERT INTO `t_wallet` VALUES (1690699, 306, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:22:51', '2019-07-25 19:41:43');
INSERT INTO `t_wallet` VALUES (1690700, 306, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:22:51', '2019-07-25 19:41:43');
INSERT INTO `t_wallet` VALUES (1690705, 48, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:51', '2019-07-25 20:00:08');
INSERT INTO `t_wallet` VALUES (1690706, 48, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:51', '2019-07-25 20:00:08');
INSERT INTO `t_wallet` VALUES (1690707, 848, 2, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:22:52', '2019-07-25 19:48:15');
INSERT INTO `t_wallet` VALUES (1690708, 848, 3, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:22:52', '2019-07-25 19:48:15');
INSERT INTO `t_wallet` VALUES (1690715, 429, 2, 0.00000000, 0.00000000, 0.00000000, 28, '2019-07-25 17:22:52', '2019-07-25 19:55:17');
INSERT INTO `t_wallet` VALUES (1690716, 429, 3, 0.00000000, 0.00000000, 0.00000000, 28, '2019-07-25 17:22:52', '2019-07-25 19:55:17');
INSERT INTO `t_wallet` VALUES (1690735, 417, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:55', '2019-07-25 19:52:38');
INSERT INTO `t_wallet` VALUES (1690736, 417, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:55', '2019-07-25 19:52:38');
INSERT INTO `t_wallet` VALUES (1690739, 956, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:55', '2019-07-31 16:03:57');
INSERT INTO `t_wallet` VALUES (1690740, 956, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:22:55', '2019-07-31 16:03:57');
INSERT INTO `t_wallet` VALUES (1690765, 402, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:57', '2019-07-25 20:04:56');
INSERT INTO `t_wallet` VALUES (1690766, 402, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:58', '2019-07-25 20:04:56');
INSERT INTO `t_wallet` VALUES (1690767, 109, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:58', '2019-08-02 10:46:58');
INSERT INTO `t_wallet` VALUES (1690768, 109, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:22:58', '2019-08-02 10:46:58');
INSERT INTO `t_wallet` VALUES (1690769, 342, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:22:58', '2019-07-25 19:57:02');
INSERT INTO `t_wallet` VALUES (1690770, 342, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:22:58', '2019-07-25 19:57:02');
INSERT INTO `t_wallet` VALUES (1690771, 699, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:22:58', '2019-08-02 10:53:02');
INSERT INTO `t_wallet` VALUES (1690772, 699, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:22:58', '2019-08-02 10:53:02');
INSERT INTO `t_wallet` VALUES (1690773, 382, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:59', '2019-07-25 19:56:12');
INSERT INTO `t_wallet` VALUES (1690774, 382, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:22:59', '2019-07-25 19:56:12');
INSERT INTO `t_wallet` VALUES (1690783, 128, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:00', '2019-07-25 20:02:10');
INSERT INTO `t_wallet` VALUES (1690784, 128, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:00', '2019-07-25 20:02:09');
INSERT INTO `t_wallet` VALUES (1690793, 147, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:01', '2019-07-25 19:49:10');
INSERT INTO `t_wallet` VALUES (1690794, 147, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:01', '2019-07-25 19:49:10');
INSERT INTO `t_wallet` VALUES (1690801, 816, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:23:02', '2019-07-25 19:41:07');
INSERT INTO `t_wallet` VALUES (1690802, 816, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:23:02', '2019-07-25 19:41:07');
INSERT INTO `t_wallet` VALUES (1690803, 229, 3, 0.00000000, 0.00000000, 0.00000000, 77, '2019-07-25 17:23:02', '2019-07-25 19:53:41');
INSERT INTO `t_wallet` VALUES (1690804, 229, 2, 0.00000000, 0.00000000, 0.00000000, 77, '2019-07-25 17:23:02', '2019-07-25 19:53:41');
INSERT INTO `t_wallet` VALUES (1690807, 626, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:23:03', '2019-07-25 19:38:35');
INSERT INTO `t_wallet` VALUES (1690808, 626, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:23:03', '2019-07-25 19:38:35');
INSERT INTO `t_wallet` VALUES (1690817, 614, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:23:04', '2019-07-25 20:03:29');
INSERT INTO `t_wallet` VALUES (1690818, 614, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:23:04', '2019-07-25 20:03:29');
INSERT INTO `t_wallet` VALUES (1690825, 499, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:04', '2019-07-25 20:01:51');
INSERT INTO `t_wallet` VALUES (1690826, 499, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:04', '2019-07-25 20:01:51');
INSERT INTO `t_wallet` VALUES (1690833, 718, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:23:06', '2019-07-25 19:58:19');
INSERT INTO `t_wallet` VALUES (1690834, 718, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:23:06', '2019-07-25 19:58:19');
INSERT INTO `t_wallet` VALUES (1690841, 913, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:06', '2019-07-25 19:45:19');
INSERT INTO `t_wallet` VALUES (1690842, 913, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:06', '2019-07-25 19:45:19');
INSERT INTO `t_wallet` VALUES (1690843, 978, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:07', '2019-07-25 20:03:28');
INSERT INTO `t_wallet` VALUES (1690844, 978, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:07', '2019-07-25 20:03:28');
INSERT INTO `t_wallet` VALUES (1690855, 581, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:08', '2019-07-25 19:52:16');
INSERT INTO `t_wallet` VALUES (1690856, 581, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:08', '2019-07-25 19:52:17');
INSERT INTO `t_wallet` VALUES (1690857, 489, 3, 0.00000000, 0.00000000, 0.00000000, 97, '2019-07-25 17:23:08', '2019-07-25 19:50:47');
INSERT INTO `t_wallet` VALUES (1690858, 489, 2, 0.00000000, 0.00000000, 0.00000000, 97, '2019-07-25 17:23:08', '2019-07-25 19:50:47');
INSERT INTO `t_wallet` VALUES (1690861, 304, 2, 0.00000000, 0.00000001, 0.00000000, 10, '2019-07-25 17:23:08', '2019-07-25 20:00:11');
INSERT INTO `t_wallet` VALUES (1690862, 304, 3, -0.00000098, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:08', '2019-07-25 20:00:11');
INSERT INTO `t_wallet` VALUES (1690869, 621, 2, 0.00000000, 0.00000000, 0.00000000, 142, '2019-07-25 17:23:09', '2019-07-25 19:56:30');
INSERT INTO `t_wallet` VALUES (1690870, 621, 3, 0.00000000, 0.00000000, 0.00000000, 142, '2019-07-25 17:23:09', '2019-07-25 19:56:30');
INSERT INTO `t_wallet` VALUES (1690873, 252, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:23:10', '2019-07-25 20:01:33');
INSERT INTO `t_wallet` VALUES (1690874, 252, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:23:10', '2019-07-25 20:01:33');
INSERT INTO `t_wallet` VALUES (1690885, 202, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:23:11', '2019-07-25 19:55:07');
INSERT INTO `t_wallet` VALUES (1690886, 202, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:23:11', '2019-07-25 19:55:07');
INSERT INTO `t_wallet` VALUES (1690897, 540, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:13', '2019-07-25 19:53:36');
INSERT INTO `t_wallet` VALUES (1690898, 540, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:13', '2019-07-25 19:53:36');
INSERT INTO `t_wallet` VALUES (1690905, 729, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:14', '2019-07-25 19:42:12');
INSERT INTO `t_wallet` VALUES (1690906, 729, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:14', '2019-07-25 19:42:12');
INSERT INTO `t_wallet` VALUES (1690913, 73, 2, 0.00000000, 0.00000000, 0.00000000, 280, '2019-07-25 17:23:14', '2019-07-25 19:48:19');
INSERT INTO `t_wallet` VALUES (1690914, 73, 3, 0.00000000, 0.00000000, 0.00000000, 280, '2019-07-25 17:23:14', '2019-07-25 19:48:19');
INSERT INTO `t_wallet` VALUES (1690919, 140, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:23:15', '2019-07-25 19:49:28');
INSERT INTO `t_wallet` VALUES (1690920, 140, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:23:15', '2019-07-25 19:49:28');
INSERT INTO `t_wallet` VALUES (1690929, 570, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:23:16', '2019-07-25 20:01:18');
INSERT INTO `t_wallet` VALUES (1690930, 570, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:23:16', '2019-07-25 20:01:18');
INSERT INTO `t_wallet` VALUES (1690935, 966, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:17', '2019-07-25 19:57:43');
INSERT INTO `t_wallet` VALUES (1690936, 966, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:17', '2019-07-25 19:57:43');
INSERT INTO `t_wallet` VALUES (1690941, 462, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:23:18', '2019-07-25 19:41:01');
INSERT INTO `t_wallet` VALUES (1690942, 462, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:23:18', '2019-07-25 19:41:01');
INSERT INTO `t_wallet` VALUES (1690949, 477, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:23:19', '2019-07-25 19:58:40');
INSERT INTO `t_wallet` VALUES (1690950, 477, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:23:19', '2019-07-25 19:58:40');
INSERT INTO `t_wallet` VALUES (1690955, 851, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:19', '2019-07-25 19:53:02');
INSERT INTO `t_wallet` VALUES (1690956, 851, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:19', '2019-07-25 19:53:02');
INSERT INTO `t_wallet` VALUES (1690957, 268, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:23:20', '2019-07-25 20:02:59');
INSERT INTO `t_wallet` VALUES (1690958, 268, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:23:20', '2019-07-25 20:02:59');
INSERT INTO `t_wallet` VALUES (1690973, 45, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:22', '2019-07-25 20:04:42');
INSERT INTO `t_wallet` VALUES (1690974, 45, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:22', '2019-07-25 20:04:42');
INSERT INTO `t_wallet` VALUES (1690989, 602, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:23:23', '2019-07-25 19:04:48');
INSERT INTO `t_wallet` VALUES (1690990, 602, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:23:23', '2019-07-25 19:04:49');
INSERT INTO `t_wallet` VALUES (1691005, 906, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:23:24', '2019-08-02 10:47:09');
INSERT INTO `t_wallet` VALUES (1691006, 906, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:23:24', '2019-08-02 10:47:09');
INSERT INTO `t_wallet` VALUES (1691011, 595, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:25', '2019-07-25 20:00:14');
INSERT INTO `t_wallet` VALUES (1691012, 595, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:25', '2019-07-25 20:00:14');
INSERT INTO `t_wallet` VALUES (1691023, 206, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:27', '2019-07-25 19:54:17');
INSERT INTO `t_wallet` VALUES (1691024, 206, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:27', '2019-07-25 19:54:17');
INSERT INTO `t_wallet` VALUES (1691035, 308, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:23:29', '2019-07-25 19:23:54');
INSERT INTO `t_wallet` VALUES (1691036, 308, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:23:29', '2019-07-25 19:23:54');
INSERT INTO `t_wallet` VALUES (1691043, 123, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:23:29', '2019-07-25 20:00:02');
INSERT INTO `t_wallet` VALUES (1691044, 123, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:23:29', '2019-07-25 20:00:02');
INSERT INTO `t_wallet` VALUES (1691045, 971, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:23:30', '2019-07-25 20:05:53');
INSERT INTO `t_wallet` VALUES (1691046, 971, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:23:30', '2019-07-25 20:05:53');
INSERT INTO `t_wallet` VALUES (1691049, 559, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:30', '2019-08-02 10:46:42');
INSERT INTO `t_wallet` VALUES (1691050, 559, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:30', '2019-08-02 10:46:42');
INSERT INTO `t_wallet` VALUES (1691061, 922, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:23:31', '2019-07-25 19:49:02');
INSERT INTO `t_wallet` VALUES (1691062, 922, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:23:31', '2019-07-25 19:49:02');
INSERT INTO `t_wallet` VALUES (1691067, 387, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:23:32', '2019-07-25 19:59:42');
INSERT INTO `t_wallet` VALUES (1691068, 387, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:23:32', '2019-07-25 19:59:42');
INSERT INTO `t_wallet` VALUES (1691093, 611, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:23:36', '2019-07-25 19:23:42');
INSERT INTO `t_wallet` VALUES (1691094, 611, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:23:36', '2019-07-25 19:23:42');
INSERT INTO `t_wallet` VALUES (1691099, 55, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:23:37', '2019-07-25 19:46:57');
INSERT INTO `t_wallet` VALUES (1691100, 55, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:23:37', '2019-07-25 19:46:58');
INSERT INTO `t_wallet` VALUES (1691101, 656, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:23:37', '2019-07-25 19:58:52');
INSERT INTO `t_wallet` VALUES (1691102, 656, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:23:37', '2019-07-25 19:58:52');
INSERT INTO `t_wallet` VALUES (1691103, 881, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:23:38', '2019-07-25 19:53:43');
INSERT INTO `t_wallet` VALUES (1691104, 881, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:23:38', '2019-07-25 19:53:43');
INSERT INTO `t_wallet` VALUES (1691113, 383, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:39', '2019-07-25 19:50:31');
INSERT INTO `t_wallet` VALUES (1691114, 383, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:39', '2019-07-25 19:50:31');
INSERT INTO `t_wallet` VALUES (1691125, 547, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:23:41', '2019-07-25 19:42:54');
INSERT INTO `t_wallet` VALUES (1691126, 547, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:23:41', '2019-07-25 19:42:55');
INSERT INTO `t_wallet` VALUES (1691129, 975, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:41', '2019-07-25 19:43:05');
INSERT INTO `t_wallet` VALUES (1691130, 975, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:41', '2019-07-25 19:43:05');
INSERT INTO `t_wallet` VALUES (1691143, 352, 2, -0.00000001, 0.00000000, 0.00000000, 267, '2019-07-25 17:23:44', '2019-07-25 19:48:04');
INSERT INTO `t_wallet` VALUES (1691144, 352, 3, 0.00000000, 0.00000098, 0.00000000, 267, '2019-07-25 17:23:44', '2019-07-25 19:48:04');
INSERT INTO `t_wallet` VALUES (1691145, 501, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:23:44', '2019-07-25 19:49:00');
INSERT INTO `t_wallet` VALUES (1691146, 501, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:23:44', '2019-07-25 19:49:01');
INSERT INTO `t_wallet` VALUES (1691149, 676, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:23:45', '2019-07-25 20:00:50');
INSERT INTO `t_wallet` VALUES (1691150, 676, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:23:45', '2019-07-25 20:00:50');
INSERT INTO `t_wallet` VALUES (1691155, 767, 2, 0.00000000, 0.00000000, 0.00000000, 47, '2019-07-25 17:23:46', '2019-07-25 20:04:30');
INSERT INTO `t_wallet` VALUES (1691156, 767, 3, 0.00000000, 0.00000000, 0.00000000, 47, '2019-07-25 17:23:46', '2019-07-25 20:04:30');
INSERT INTO `t_wallet` VALUES (1691177, 831, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:23:49', '2019-07-25 19:37:54');
INSERT INTO `t_wallet` VALUES (1691178, 831, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:23:49', '2019-07-25 19:37:54');
INSERT INTO `t_wallet` VALUES (1691179, 165, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:23:49', '2019-07-25 19:56:28');
INSERT INTO `t_wallet` VALUES (1691180, 165, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:23:49', '2019-07-25 19:56:28');
INSERT INTO `t_wallet` VALUES (1691185, 987, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:50', '2019-07-25 20:03:19');
INSERT INTO `t_wallet` VALUES (1691186, 987, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:50', '2019-07-25 20:03:19');
INSERT INTO `t_wallet` VALUES (1691195, 609, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:23:51', '2019-07-25 20:08:47');
INSERT INTO `t_wallet` VALUES (1691196, 609, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:23:51', '2019-07-25 20:08:47');
INSERT INTO `t_wallet` VALUES (1691201, 463, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:52', '2019-07-25 19:52:21');
INSERT INTO `t_wallet` VALUES (1691202, 463, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:23:52', '2019-07-25 19:52:21');
INSERT INTO `t_wallet` VALUES (1691203, 834, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:52', '2019-07-25 20:01:53');
INSERT INTO `t_wallet` VALUES (1691204, 834, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:23:52', '2019-07-25 20:01:53');
INSERT INTO `t_wallet` VALUES (1691207, 778, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:23:53', '2019-07-25 20:03:45');
INSERT INTO `t_wallet` VALUES (1691208, 778, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:23:53', '2019-07-25 20:03:45');
INSERT INTO `t_wallet` VALUES (1691213, 916, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:23:53', '2019-07-25 19:47:37');
INSERT INTO `t_wallet` VALUES (1691214, 916, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:23:53', '2019-07-25 19:47:37');
INSERT INTO `t_wallet` VALUES (1691219, 505, 2, 0.00000000, 0.00000000, 0.00000000, 50, '2019-07-25 17:23:54', '2019-07-25 19:46:00');
INSERT INTO `t_wallet` VALUES (1691220, 505, 3, 0.00000000, 0.00000000, 0.00000000, 50, '2019-07-25 17:23:54', '2019-07-25 19:46:00');
INSERT INTO `t_wallet` VALUES (1691227, 857, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:23:55', '2019-07-25 19:41:38');
INSERT INTO `t_wallet` VALUES (1691228, 857, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:23:55', '2019-07-25 19:41:38');
INSERT INTO `t_wallet` VALUES (1691235, 509, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:23:56', '2019-07-25 19:48:44');
INSERT INTO `t_wallet` VALUES (1691236, 509, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:23:56', '2019-07-25 19:48:44');
INSERT INTO `t_wallet` VALUES (1691243, 85, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:57', '2019-07-25 19:59:29');
INSERT INTO `t_wallet` VALUES (1691244, 85, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:23:57', '2019-07-25 19:59:29');
INSERT INTO `t_wallet` VALUES (1691277, 796, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:01', '2019-07-25 19:30:59');
INSERT INTO `t_wallet` VALUES (1691278, 796, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:01', '2019-07-25 19:30:59');
INSERT INTO `t_wallet` VALUES (1691281, 218, 2, 0.00000000, 0.00000000, 0.00000000, 58, '2019-07-25 17:24:02', '2019-07-25 19:25:07');
INSERT INTO `t_wallet` VALUES (1691282, 218, 3, 0.00000000, 0.00000000, 0.00000000, 58, '2019-07-25 17:24:02', '2019-07-25 19:25:07');
INSERT INTO `t_wallet` VALUES (1691289, 657, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:24:03', '2019-07-25 19:59:46');
INSERT INTO `t_wallet` VALUES (1691290, 657, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:24:03', '2019-07-25 19:59:46');
INSERT INTO `t_wallet` VALUES (1691293, 57, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:24:04', '2019-07-25 19:46:27');
INSERT INTO `t_wallet` VALUES (1691294, 57, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:24:04', '2019-07-25 19:46:27');
INSERT INTO `t_wallet` VALUES (1691323, 86, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:24:07', '2019-07-25 19:56:37');
INSERT INTO `t_wallet` VALUES (1691324, 86, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:24:07', '2019-07-25 19:56:37');
INSERT INTO `t_wallet` VALUES (1691331, 256, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:24:08', '2019-07-25 20:01:36');
INSERT INTO `t_wallet` VALUES (1691332, 256, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:24:08', '2019-07-25 20:01:36');
INSERT INTO `t_wallet` VALUES (1691359, 637, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:24:11', '2019-07-25 20:06:01');
INSERT INTO `t_wallet` VALUES (1691360, 637, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:24:11', '2019-07-25 20:06:01');
INSERT INTO `t_wallet` VALUES (1691385, 859, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:24:14', '2019-07-25 19:59:03');
INSERT INTO `t_wallet` VALUES (1691386, 859, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:24:14', '2019-07-25 19:59:03');
INSERT INTO `t_wallet` VALUES (1691411, 587, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:24:17', '2019-07-25 20:04:42');
INSERT INTO `t_wallet` VALUES (1691412, 587, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:24:17', '2019-07-25 20:04:42');
INSERT INTO `t_wallet` VALUES (1691417, 815, 2, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:24:17', '2019-07-25 19:43:08');
INSERT INTO `t_wallet` VALUES (1691418, 815, 3, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:24:17', '2019-07-25 19:43:08');
INSERT INTO `t_wallet` VALUES (1691425, 533, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:24:18', '2019-07-25 19:59:48');
INSERT INTO `t_wallet` VALUES (1691426, 533, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:24:18', '2019-07-25 19:59:48');
INSERT INTO `t_wallet` VALUES (1691433, 988, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:19', '2019-07-25 19:54:51');
INSERT INTO `t_wallet` VALUES (1691434, 988, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:19', '2019-07-25 19:54:52');
INSERT INTO `t_wallet` VALUES (1691477, 926, 2, 0.00000000, 0.00000000, 0.00000000, 39, '2019-07-25 17:24:23', '2019-07-25 20:04:39');
INSERT INTO `t_wallet` VALUES (1691478, 926, 3, 0.00000000, 0.00000000, 0.00000000, 39, '2019-07-25 17:24:23', '2019-07-25 20:04:39');
INSERT INTO `t_wallet` VALUES (1691483, 22, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:24:25', '2019-07-25 19:39:54');
INSERT INTO `t_wallet` VALUES (1691484, 22, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:24:25', '2019-07-25 19:39:54');
INSERT INTO `t_wallet` VALUES (1691491, 320, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:26', '2019-07-25 19:46:32');
INSERT INTO `t_wallet` VALUES (1691492, 320, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:26', '2019-07-25 19:46:32');
INSERT INTO `t_wallet` VALUES (1691497, 394, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:26', '2019-07-25 19:55:33');
INSERT INTO `t_wallet` VALUES (1691498, 394, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:26', '2019-07-25 19:55:33');
INSERT INTO `t_wallet` VALUES (1691527, 380, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:33', '2019-07-25 19:46:22');
INSERT INTO `t_wallet` VALUES (1691528, 380, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:33', '2019-07-25 19:46:22');
INSERT INTO `t_wallet` VALUES (1691537, 575, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:37', '2019-08-02 10:50:33');
INSERT INTO `t_wallet` VALUES (1691538, 575, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:24:38', '2019-08-02 10:50:33');
INSERT INTO `t_wallet` VALUES (1691539, 989, 3, 0.00000000, 0.00000000, 0.00000000, 40, '2019-07-25 17:24:38', '2019-07-25 20:02:18');
INSERT INTO `t_wallet` VALUES (1691540, 989, 2, 0.00000000, 0.00000000, 0.00000000, 40, '2019-07-25 17:24:38', '2019-07-25 20:02:18');
INSERT INTO `t_wallet` VALUES (1691541, 737, 2, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:24:39', '2019-07-25 20:08:48');
INSERT INTO `t_wallet` VALUES (1691542, 737, 3, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:24:39', '2019-07-25 20:08:48');
INSERT INTO `t_wallet` VALUES (1691547, 486, 2, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:24:41', '2019-07-25 20:04:39');
INSERT INTO `t_wallet` VALUES (1691548, 486, 3, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:24:41', '2019-07-25 20:04:39');
INSERT INTO `t_wallet` VALUES (1691553, 280, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:41', '2019-07-25 19:33:16');
INSERT INTO `t_wallet` VALUES (1691554, 280, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:41', '2019-07-25 19:33:16');
INSERT INTO `t_wallet` VALUES (1691561, 66, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:42', '2019-07-25 19:45:22');
INSERT INTO `t_wallet` VALUES (1691562, 66, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:42', '2019-07-25 19:45:23');
INSERT INTO `t_wallet` VALUES (1691583, 34, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:24:45', '2019-07-25 19:58:52');
INSERT INTO `t_wallet` VALUES (1691584, 34, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:24:45', '2019-07-25 19:58:52');
INSERT INTO `t_wallet` VALUES (1691589, 13, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:24:45', '2019-07-25 19:41:17');
INSERT INTO `t_wallet` VALUES (1691590, 13, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:24:45', '2019-07-25 19:41:17');
INSERT INTO `t_wallet` VALUES (1691607, 495, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:47', '2019-07-25 19:53:28');
INSERT INTO `t_wallet` VALUES (1691608, 495, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:47', '2019-07-25 19:53:28');
INSERT INTO `t_wallet` VALUES (1691621, 491, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:24:48', '2019-07-25 20:01:28');
INSERT INTO `t_wallet` VALUES (1691622, 491, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:24:48', '2019-07-25 20:01:28');
INSERT INTO `t_wallet` VALUES (1691625, 153, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:24:49', '2019-07-25 19:57:33');
INSERT INTO `t_wallet` VALUES (1691626, 153, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:24:49', '2019-07-25 19:57:33');
INSERT INTO `t_wallet` VALUES (1691631, 127, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:24:50', '2019-08-02 10:52:14');
INSERT INTO `t_wallet` VALUES (1691632, 127, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:24:50', '2019-08-02 10:52:14');
INSERT INTO `t_wallet` VALUES (1691635, 122, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:24:50', '2019-07-25 19:54:11');
INSERT INTO `t_wallet` VALUES (1691636, 122, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:24:50', '2019-07-25 19:54:11');
INSERT INTO `t_wallet` VALUES (1691641, 447, 2, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:24:51', '2019-07-25 20:01:43');
INSERT INTO `t_wallet` VALUES (1691642, 447, 3, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:24:51', '2019-07-25 20:01:43');
INSERT INTO `t_wallet` VALUES (1691649, 129, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:24:52', '2019-07-25 19:49:03');
INSERT INTO `t_wallet` VALUES (1691650, 129, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:24:52', '2019-07-25 19:49:03');
INSERT INTO `t_wallet` VALUES (1691657, 768, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:24:53', '2019-07-25 19:57:53');
INSERT INTO `t_wallet` VALUES (1691658, 768, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:24:53', '2019-07-25 19:57:53');
INSERT INTO `t_wallet` VALUES (1691659, 957, 2, 0.00000000, 0.00000000, 0.00000000, 46, '2019-07-25 17:24:54', '2019-08-02 11:48:53');
INSERT INTO `t_wallet` VALUES (1691660, 957, 3, 0.00000000, 0.00000000, 0.00000000, 46, '2019-07-25 17:24:54', '2019-08-02 11:48:53');
INSERT INTO `t_wallet` VALUES (1691667, 981, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:24:55', '2019-07-25 20:00:29');
INSERT INTO `t_wallet` VALUES (1691668, 981, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:24:55', '2019-07-25 20:00:29');
INSERT INTO `t_wallet` VALUES (1691675, 396, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:24:55', '2019-07-25 19:42:08');
INSERT INTO `t_wallet` VALUES (1691676, 396, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:24:55', '2019-07-25 19:42:08');
INSERT INTO `t_wallet` VALUES (1691679, 1000, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:24:56', '2019-07-25 20:04:44');
INSERT INTO `t_wallet` VALUES (1691680, 1000, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:24:56', '2019-07-25 20:04:44');
INSERT INTO `t_wallet` VALUES (1691689, 607, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:57', '2019-07-25 19:42:57');
INSERT INTO `t_wallet` VALUES (1691690, 607, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:24:57', '2019-07-25 19:42:57');
INSERT INTO `t_wallet` VALUES (1691717, 714, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:00', '2019-07-25 19:59:17');
INSERT INTO `t_wallet` VALUES (1691718, 714, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:00', '2019-07-25 19:59:17');
INSERT INTO `t_wallet` VALUES (1691729, 267, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:25:01', '2019-07-25 19:59:57');
INSERT INTO `t_wallet` VALUES (1691730, 267, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:25:01', '2019-07-25 19:59:57');
INSERT INTO `t_wallet` VALUES (1691737, 330, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:25:02', '2019-07-25 20:00:41');
INSERT INTO `t_wallet` VALUES (1691738, 330, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:25:02', '2019-07-25 20:00:41');
INSERT INTO `t_wallet` VALUES (1691739, 840, 2, 0.00000000, 0.00000000, 0.00000000, 25, '2019-07-25 17:25:03', '2019-07-25 19:39:19');
INSERT INTO `t_wallet` VALUES (1691740, 840, 3, 0.00000000, 0.00000000, 0.00000000, 25, '2019-07-25 17:25:03', '2019-07-25 19:39:19');
INSERT INTO `t_wallet` VALUES (1691753, 209, 2, 0.00000000, 0.00000000, 0.00000000, 54, '2019-07-25 17:25:04', '2019-07-25 19:59:51');
INSERT INTO `t_wallet` VALUES (1691754, 209, 3, 0.00000000, 0.00000000, 0.00000000, 54, '2019-07-25 17:25:04', '2019-07-25 19:59:51');
INSERT INTO `t_wallet` VALUES (1691755, 409, 2, 0.00000000, 0.00000000, 0.00000000, 53, '2019-07-25 17:25:05', '2019-07-25 19:57:35');
INSERT INTO `t_wallet` VALUES (1691756, 409, 3, 0.00000000, 0.00000000, 0.00000000, 53, '2019-07-25 17:25:05', '2019-07-25 19:57:35');
INSERT INTO `t_wallet` VALUES (1691777, 428, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:07', '2019-07-25 20:02:59');
INSERT INTO `t_wallet` VALUES (1691778, 428, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:07', '2019-07-25 20:02:59');
INSERT INTO `t_wallet` VALUES (1691787, 638, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:09', '2019-07-25 20:01:51');
INSERT INTO `t_wallet` VALUES (1691788, 638, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:09', '2019-07-25 20:01:51');
INSERT INTO `t_wallet` VALUES (1691789, 167, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:25:09', '2019-07-25 19:40:15');
INSERT INTO `t_wallet` VALUES (1691790, 167, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:25:09', '2019-07-25 19:40:15');
INSERT INTO `t_wallet` VALUES (1691791, 672, 2, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:25:09', '2019-07-25 19:45:03');
INSERT INTO `t_wallet` VALUES (1691792, 672, 3, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:25:09', '2019-07-25 19:45:03');
INSERT INTO `t_wallet` VALUES (1691795, 23, 2, 0.00000000, 0.00000000, 0.00000000, 84, '2019-07-25 17:25:10', '2019-07-25 19:55:26');
INSERT INTO `t_wallet` VALUES (1691796, 23, 3, 0.00000000, 0.00000000, 0.00000000, 84, '2019-07-25 17:25:10', '2019-07-25 19:55:26');
INSERT INTO `t_wallet` VALUES (1691797, 75, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:10', '2019-07-25 19:45:04');
INSERT INTO `t_wallet` VALUES (1691798, 75, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:10', '2019-07-25 19:45:04');
INSERT INTO `t_wallet` VALUES (1691803, 803, 2, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:25:11', '2019-07-25 19:41:49');
INSERT INTO `t_wallet` VALUES (1691804, 803, 3, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:25:11', '2019-07-25 19:41:50');
INSERT INTO `t_wallet` VALUES (1691819, 732, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:13', '2019-07-25 19:54:30');
INSERT INTO `t_wallet` VALUES (1691820, 732, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:13', '2019-07-25 19:54:30');
INSERT INTO `t_wallet` VALUES (1691823, 224, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:14', '2019-07-25 19:41:48');
INSERT INTO `t_wallet` VALUES (1691824, 224, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:14', '2019-07-25 19:41:49');
INSERT INTO `t_wallet` VALUES (1691827, 454, 2, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 17:25:15', '2019-08-02 10:49:44');
INSERT INTO `t_wallet` VALUES (1691828, 454, 3, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 17:25:15', '2019-08-02 10:49:44');
INSERT INTO `t_wallet` VALUES (1691835, 584, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:25:16', '2019-07-25 19:57:12');
INSERT INTO `t_wallet` VALUES (1691836, 584, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:25:16', '2019-07-25 19:57:12');
INSERT INTO `t_wallet` VALUES (1691841, 362, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:16', '2019-07-25 19:57:34');
INSERT INTO `t_wallet` VALUES (1691842, 362, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:16', '2019-07-25 19:57:34');
INSERT INTO `t_wallet` VALUES (1691849, 18, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:25:17', '2019-07-25 19:54:05');
INSERT INTO `t_wallet` VALUES (1691850, 18, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:25:17', '2019-07-25 19:54:05');
INSERT INTO `t_wallet` VALUES (1691865, 303, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:20', '2019-07-25 19:53:48');
INSERT INTO `t_wallet` VALUES (1691866, 303, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:20', '2019-07-25 19:53:48');
INSERT INTO `t_wallet` VALUES (1691869, 427, 2, 0.00000000, 0.00000000, 0.00000000, 55, '2019-07-25 17:25:22', '2019-07-25 19:55:33');
INSERT INTO `t_wallet` VALUES (1691870, 427, 3, 0.00000000, 0.00000000, 0.00000000, 55, '2019-07-25 17:25:22', '2019-07-25 19:55:33');
INSERT INTO `t_wallet` VALUES (1691879, 700, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:25:22', '2019-07-25 19:54:09');
INSERT INTO `t_wallet` VALUES (1691880, 700, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:25:22', '2019-07-25 19:54:10');
INSERT INTO `t_wallet` VALUES (1691897, 662, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:25:25', '2019-07-25 19:56:18');
INSERT INTO `t_wallet` VALUES (1691898, 662, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:25:25', '2019-07-25 19:56:18');
INSERT INTO `t_wallet` VALUES (1691899, 521, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:25', '2019-07-25 19:37:04');
INSERT INTO `t_wallet` VALUES (1691900, 521, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:25', '2019-07-25 19:37:04');
INSERT INTO `t_wallet` VALUES (1691901, 261, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:25:26', '2019-07-25 19:40:26');
INSERT INTO `t_wallet` VALUES (1691902, 261, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:25:26', '2019-07-25 19:40:26');
INSERT INTO `t_wallet` VALUES (1691913, 143, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:28', '2019-07-25 19:50:06');
INSERT INTO `t_wallet` VALUES (1691914, 143, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:28', '2019-07-25 19:50:06');
INSERT INTO `t_wallet` VALUES (1691915, 821, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:25:28', '2019-07-25 19:58:09');
INSERT INTO `t_wallet` VALUES (1691916, 821, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:25:28', '2019-07-25 19:58:09');
INSERT INTO `t_wallet` VALUES (1691925, 215, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:25:30', '2019-07-25 19:53:34');
INSERT INTO `t_wallet` VALUES (1691926, 215, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:25:30', '2019-07-25 19:53:34');
INSERT INTO `t_wallet` VALUES (1691929, 199, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:25:31', '2019-07-25 20:00:12');
INSERT INTO `t_wallet` VALUES (1691930, 199, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:25:31', '2019-07-25 20:00:12');
INSERT INTO `t_wallet` VALUES (1691939, 302, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:25:35', '2019-07-25 20:08:45');
INSERT INTO `t_wallet` VALUES (1691940, 302, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:25:35', '2019-07-25 20:08:45');
INSERT INTO `t_wallet` VALUES (1691951, 636, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:38', '2019-07-25 20:04:57');
INSERT INTO `t_wallet` VALUES (1691952, 636, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:38', '2019-07-25 20:04:57');
INSERT INTO `t_wallet` VALUES (1691955, 515, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:39', '2019-07-25 19:31:53');
INSERT INTO `t_wallet` VALUES (1691956, 515, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:25:39', '2019-07-25 19:31:53');
INSERT INTO `t_wallet` VALUES (1691957, 980, 2, 0.00000000, 0.00000000, 0.00000000, 29, '2019-07-25 17:25:39', '2019-07-25 19:54:33');
INSERT INTO `t_wallet` VALUES (1691958, 980, 3, 0.00000000, 0.00000000, 0.00000000, 29, '2019-07-25 17:25:39', '2019-07-25 19:54:33');
INSERT INTO `t_wallet` VALUES (1691965, 347, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:40', '2019-07-25 19:59:43');
INSERT INTO `t_wallet` VALUES (1691966, 347, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:25:40', '2019-07-25 19:59:43');
INSERT INTO `t_wallet` VALUES (1691971, 568, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:41', '2019-07-25 20:01:48');
INSERT INTO `t_wallet` VALUES (1691972, 568, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:41', '2019-07-25 20:01:48');
INSERT INTO `t_wallet` VALUES (1691987, 274, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:25:43', '2019-07-25 19:48:14');
INSERT INTO `t_wallet` VALUES (1691988, 274, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:25:43', '2019-07-25 19:48:14');
INSERT INTO `t_wallet` VALUES (1692001, 493, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:48', '2019-07-25 19:55:50');
INSERT INTO `t_wallet` VALUES (1692002, 493, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:48', '2019-07-25 19:55:50');
INSERT INTO `t_wallet` VALUES (1692021, 299, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:57', '2019-07-25 20:03:59');
INSERT INTO `t_wallet` VALUES (1692022, 299, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:25:57', '2019-07-25 20:03:59');
INSERT INTO `t_wallet` VALUES (1692027, 475, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:25:59', '2019-07-25 19:58:49');
INSERT INTO `t_wallet` VALUES (1692028, 475, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:25:59', '2019-07-25 19:58:49');
INSERT INTO `t_wallet` VALUES (1692037, 739, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:26:02', '2019-07-25 19:43:06');
INSERT INTO `t_wallet` VALUES (1692038, 739, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:26:02', '2019-07-25 19:43:06');
INSERT INTO `t_wallet` VALUES (1692049, 800, 2, 0.00000000, 0.00000000, 0.00000000, 72, '2019-07-25 17:26:04', '2019-07-25 19:49:00');
INSERT INTO `t_wallet` VALUES (1692050, 800, 3, 0.00000000, 0.00000000, 0.00000000, 72, '2019-07-25 17:26:04', '2019-07-25 19:49:00');
INSERT INTO `t_wallet` VALUES (1692055, 567, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:26:06', '2019-07-25 19:57:32');
INSERT INTO `t_wallet` VALUES (1692056, 567, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:26:06', '2019-07-25 19:57:32');
INSERT INTO `t_wallet` VALUES (1692073, 227, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:26:09', '2019-07-25 19:53:24');
INSERT INTO `t_wallet` VALUES (1692074, 227, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:26:10', '2019-07-25 19:53:24');
INSERT INTO `t_wallet` VALUES (1692079, 314, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:26:10', '2019-07-25 19:48:04');
INSERT INTO `t_wallet` VALUES (1692080, 314, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:26:10', '2019-07-25 19:48:04');
INSERT INTO `t_wallet` VALUES (1692081, 419, 3, 0.00000000, 0.00000000, 0.00000000, 26, '2019-07-25 17:26:10', '2019-07-25 19:45:35');
INSERT INTO `t_wallet` VALUES (1692082, 419, 2, 0.00000000, 0.00000000, 0.00000000, 26, '2019-07-25 17:26:11', '2019-07-25 19:45:35');
INSERT INTO `t_wallet` VALUES (1692085, 468, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:26:11', '2019-07-25 20:04:53');
INSERT INTO `t_wallet` VALUES (1692086, 468, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:26:11', '2019-07-25 20:04:53');
INSERT INTO `t_wallet` VALUES (1692105, 942, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:26:14', '2019-07-25 19:55:19');
INSERT INTO `t_wallet` VALUES (1692106, 942, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:26:14', '2019-07-25 19:55:19');
INSERT INTO `t_wallet` VALUES (1692121, 615, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:26:17', '2019-07-25 19:31:06');
INSERT INTO `t_wallet` VALUES (1692122, 615, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:26:17', '2019-07-25 19:31:06');
INSERT INTO `t_wallet` VALUES (1692127, 549, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:26:18', '2019-07-25 19:57:42');
INSERT INTO `t_wallet` VALUES (1692128, 549, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:26:18', '2019-07-25 19:57:42');
INSERT INTO `t_wallet` VALUES (1692135, 276, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:26:19', '2019-07-25 20:01:21');
INSERT INTO `t_wallet` VALUES (1692136, 276, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:26:19', '2019-07-25 20:01:21');
INSERT INTO `t_wallet` VALUES (1692145, 20, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:26:20', '2019-07-25 19:54:05');
INSERT INTO `t_wallet` VALUES (1692146, 20, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:26:20', '2019-07-25 19:54:05');
INSERT INTO `t_wallet` VALUES (1692161, 892, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:26:22', '2019-07-25 19:47:26');
INSERT INTO `t_wallet` VALUES (1692162, 892, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:26:22', '2019-07-25 19:47:26');
INSERT INTO `t_wallet` VALUES (1692163, 231, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:26:22', '2019-07-25 19:49:52');
INSERT INTO `t_wallet` VALUES (1692164, 231, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:26:22', '2019-07-25 19:49:52');
INSERT INTO `t_wallet` VALUES (1692165, 986, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:26:23', '2019-07-25 19:40:39');
INSERT INTO `t_wallet` VALUES (1692166, 986, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:26:23', '2019-07-25 19:40:40');
INSERT INTO `t_wallet` VALUES (1692175, 29, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:26:25', '2019-07-25 19:58:24');
INSERT INTO `t_wallet` VALUES (1692176, 29, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:26:25', '2019-07-25 19:58:24');
INSERT INTO `t_wallet` VALUES (1692207, 232, 3, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:26:29', '2019-07-25 19:59:54');
INSERT INTO `t_wallet` VALUES (1692208, 232, 2, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:26:29', '2019-07-25 19:59:54');
INSERT INTO `t_wallet` VALUES (1692253, 675, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:26:35', '2019-07-25 19:50:38');
INSERT INTO `t_wallet` VALUES (1692254, 675, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:26:35', '2019-07-25 19:50:38');
INSERT INTO `t_wallet` VALUES (1692255, 882, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:26:35', '2019-07-25 19:51:25');
INSERT INTO `t_wallet` VALUES (1692256, 882, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:26:35', '2019-07-25 19:51:25');
INSERT INTO `t_wallet` VALUES (1692277, 108, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:26:38', '2019-07-25 19:35:35');
INSERT INTO `t_wallet` VALUES (1692278, 108, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:26:38', '2019-07-25 19:35:35');
INSERT INTO `t_wallet` VALUES (1692293, 245, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:26:40', '2019-07-25 19:37:05');
INSERT INTO `t_wallet` VALUES (1692294, 245, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:26:40', '2019-07-25 19:37:06');
INSERT INTO `t_wallet` VALUES (1692295, 970, 3, 0.00000000, 0.00000179, 0.00000000, 80, '2019-07-25 17:26:40', '2019-07-25 19:53:01');
INSERT INTO `t_wallet` VALUES (1692296, 970, 2, -0.00000001, 0.00000000, 0.00000000, 80, '2019-07-25 17:26:40', '2019-07-25 19:53:01');
INSERT INTO `t_wallet` VALUES (1692297, 608, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:26:40', '2019-07-25 19:42:49');
INSERT INTO `t_wallet` VALUES (1692298, 608, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:26:40', '2019-07-25 19:42:49');
INSERT INTO `t_wallet` VALUES (1692323, 282, 2, 0.00000000, 0.00000001, 0.00000000, 10, '2019-07-25 17:26:45', '2019-07-25 19:40:37');
INSERT INTO `t_wallet` VALUES (1692324, 282, 3, -0.00000179, 0.00000000, 0.00000000, 10, '2019-07-25 17:26:45', '2019-07-25 19:40:38');
INSERT INTO `t_wallet` VALUES (1692337, 358, 3, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:26:47', '2019-07-25 19:51:16');
INSERT INTO `t_wallet` VALUES (1692338, 358, 2, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:26:47', '2019-07-25 19:51:16');
INSERT INTO `t_wallet` VALUES (1692371, 33, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:26:51', '2019-08-02 10:53:03');
INSERT INTO `t_wallet` VALUES (1692372, 33, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:26:51', '2019-08-02 10:53:03');
INSERT INTO `t_wallet` VALUES (1692397, 238, 2, 0.00000000, 0.00000000, 0.00000000, 147, '2019-07-25 17:26:54', '2019-07-25 19:53:44');
INSERT INTO `t_wallet` VALUES (1692398, 238, 3, 0.00000000, 0.00000000, 0.00000000, 147, '2019-07-25 17:26:54', '2019-07-25 19:53:44');
INSERT INTO `t_wallet` VALUES (1692413, 279, 2, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:26:56', '2019-07-25 20:08:51');
INSERT INTO `t_wallet` VALUES (1692414, 279, 3, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:26:57', '2019-07-25 20:08:51');
INSERT INTO `t_wallet` VALUES (1692435, 791, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:26:58', '2019-07-25 19:36:52');
INSERT INTO `t_wallet` VALUES (1692436, 791, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:26:59', '2019-07-25 19:36:52');
INSERT INTO `t_wallet` VALUES (1692441, 794, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:26:59', '2019-07-25 19:37:00');
INSERT INTO `t_wallet` VALUES (1692442, 794, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:26:59', '2019-07-25 19:37:00');
INSERT INTO `t_wallet` VALUES (1692455, 932, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:27:01', '2019-07-25 19:36:10');
INSERT INTO `t_wallet` VALUES (1692456, 932, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:27:01', '2019-07-25 19:36:10');
INSERT INTO `t_wallet` VALUES (1692467, 444, 2, 0.00000000, 0.00000000, 0.00000000, 60, '2019-07-25 17:27:03', '2019-07-25 20:04:55');
INSERT INTO `t_wallet` VALUES (1692468, 444, 3, 0.00000000, 0.00000000, 0.00000000, 60, '2019-07-25 17:27:03', '2019-07-25 20:04:55');
INSERT INTO `t_wallet` VALUES (1692481, 244, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:05', '2019-07-25 19:22:41');
INSERT INTO `t_wallet` VALUES (1692482, 244, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:05', '2019-07-25 19:22:41');
INSERT INTO `t_wallet` VALUES (1692485, 979, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:27:06', '2019-07-25 19:59:29');
INSERT INTO `t_wallet` VALUES (1692486, 979, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:27:06', '2019-07-25 19:59:30');
INSERT INTO `t_wallet` VALUES (1692493, 56, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:06', '2019-07-25 19:40:47');
INSERT INTO `t_wallet` VALUES (1692494, 56, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:07', '2019-07-25 19:40:47');
INSERT INTO `t_wallet` VALUES (1692499, 807, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:27:08', '2019-07-25 20:00:49');
INSERT INTO `t_wallet` VALUES (1692500, 807, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:27:08', '2019-07-25 20:00:49');
INSERT INTO `t_wallet` VALUES (1692505, 627, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:27:09', '2019-07-25 19:59:56');
INSERT INTO `t_wallet` VALUES (1692506, 627, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:27:09', '2019-07-25 19:59:56');
INSERT INTO `t_wallet` VALUES (1692507, 544, 3, 0.00000000, 0.00000000, 0.00000000, 65, '2019-07-25 17:27:09', '2019-07-25 19:47:42');
INSERT INTO `t_wallet` VALUES (1692508, 544, 2, 0.00000000, 0.00000000, 0.00000000, 65, '2019-07-25 17:27:09', '2019-07-25 19:47:42');
INSERT INTO `t_wallet` VALUES (1692517, 524, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:27:11', '2019-07-25 19:48:22');
INSERT INTO `t_wallet` VALUES (1692518, 524, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:27:11', '2019-07-25 19:48:22');
INSERT INTO `t_wallet` VALUES (1692519, 917, 2, 0.00000000, 0.00000000, 0.00000000, 45, '2019-07-25 17:27:11', '2019-07-25 19:45:25');
INSERT INTO `t_wallet` VALUES (1692520, 917, 3, 0.00000000, 0.00000000, 0.00000000, 45, '2019-07-25 17:27:12', '2019-07-25 19:45:24');
INSERT INTO `t_wallet` VALUES (1692521, 646, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:12', '2019-07-25 19:38:26');
INSERT INTO `t_wallet` VALUES (1692522, 646, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:12', '2019-07-25 19:38:26');
INSERT INTO `t_wallet` VALUES (1692523, 168, 2, 0.00000000, 0.00000000, 0.00000000, 65, '2019-07-25 17:27:12', '2019-07-25 19:36:02');
INSERT INTO `t_wallet` VALUES (1692524, 168, 3, 0.00000000, 0.00000000, 0.00000000, 65, '2019-07-25 17:27:12', '2019-07-25 19:36:02');
INSERT INTO `t_wallet` VALUES (1692525, 525, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:13', '2019-07-25 20:04:43');
INSERT INTO `t_wallet` VALUES (1692526, 525, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:13', '2019-07-25 20:04:43');
INSERT INTO `t_wallet` VALUES (1692543, 723, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:27:15', '2019-07-25 19:34:17');
INSERT INTO `t_wallet` VALUES (1692544, 723, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:27:15', '2019-07-25 19:34:18');
INSERT INTO `t_wallet` VALUES (1692549, 911, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:27:16', '2019-07-25 20:06:34');
INSERT INTO `t_wallet` VALUES (1692550, 911, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:27:16', '2019-07-25 20:06:34');
INSERT INTO `t_wallet` VALUES (1692553, 707, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:18', '2019-07-25 19:38:07');
INSERT INTO `t_wallet` VALUES (1692554, 707, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:18', '2019-07-25 19:38:07');
INSERT INTO `t_wallet` VALUES (1692555, 474, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:27:18', '2019-07-25 19:24:30');
INSERT INTO `t_wallet` VALUES (1692556, 474, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:27:18', '2019-07-25 19:24:30');
INSERT INTO `t_wallet` VALUES (1692573, 846, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:27:21', '2019-07-25 20:02:01');
INSERT INTO `t_wallet` VALUES (1692574, 846, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:27:21', '2019-07-25 20:02:01');
INSERT INTO `t_wallet` VALUES (1692581, 503, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:27:21', '2019-07-25 19:58:59');
INSERT INTO `t_wallet` VALUES (1692582, 503, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:27:21', '2019-07-25 19:58:59');
INSERT INTO `t_wallet` VALUES (1692589, 573, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:27:22', '2019-07-25 19:54:29');
INSERT INTO `t_wallet` VALUES (1692590, 573, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:27:22', '2019-07-25 19:54:29');
INSERT INTO `t_wallet` VALUES (1692615, 466, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:25', '2019-07-25 20:00:13');
INSERT INTO `t_wallet` VALUES (1692616, 466, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:25', '2019-07-25 20:00:13');
INSERT INTO `t_wallet` VALUES (1692623, 658, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:27', '2019-07-25 19:38:34');
INSERT INTO `t_wallet` VALUES (1692624, 658, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:27', '2019-07-25 19:38:34');
INSERT INTO `t_wallet` VALUES (1692633, 741, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:29', '2019-07-25 19:32:35');
INSERT INTO `t_wallet` VALUES (1692634, 741, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:29', '2019-07-25 19:32:35');
INSERT INTO `t_wallet` VALUES (1692637, 144, 2, 0.00000000, 0.00000000, 0.00000000, 99, '2019-07-25 17:27:29', '2019-07-25 20:06:02');
INSERT INTO `t_wallet` VALUES (1692638, 144, 3, 0.00000000, 0.00000000, 0.00000000, 99, '2019-07-25 17:27:29', '2019-07-25 20:06:02');
INSERT INTO `t_wallet` VALUES (1692641, 26, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:30', '2019-08-02 11:00:10');
INSERT INTO `t_wallet` VALUES (1692642, 26, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:27:30', '2019-08-02 11:00:10');
INSERT INTO `t_wallet` VALUES (1692643, 599, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:27:30', '2019-07-25 19:58:09');
INSERT INTO `t_wallet` VALUES (1692644, 599, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:27:30', '2019-07-25 19:58:09');
INSERT INTO `t_wallet` VALUES (1692645, 648, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:27:30', '2019-07-25 19:33:34');
INSERT INTO `t_wallet` VALUES (1692646, 648, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:27:30', '2019-07-25 19:33:35');
INSERT INTO `t_wallet` VALUES (1692655, 226, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:27:32', '2019-07-25 19:42:36');
INSERT INTO `t_wallet` VALUES (1692656, 226, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:27:32', '2019-07-25 19:42:36');
INSERT INTO `t_wallet` VALUES (1692677, 19, 2, 0.00000000, 0.00000000, 0.00000000, 38, '2019-07-25 17:27:34', '2019-07-25 19:31:23');
INSERT INTO `t_wallet` VALUES (1692678, 19, 3, 0.00000000, 0.00000000, 0.00000000, 38, '2019-07-25 17:27:34', '2019-07-25 19:31:23');
INSERT INTO `t_wallet` VALUES (1692679, 100, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:27:35', '2019-07-25 19:25:44');
INSERT INTO `t_wallet` VALUES (1692680, 100, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:27:35', '2019-07-25 19:25:44');
INSERT INTO `t_wallet` VALUES (1692709, 138, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:27:37', '2019-07-25 19:51:31');
INSERT INTO `t_wallet` VALUES (1692710, 138, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:27:37', '2019-07-25 19:51:31');
INSERT INTO `t_wallet` VALUES (1692717, 543, 2, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:27:38', '2019-07-25 20:04:37');
INSERT INTO `t_wallet` VALUES (1692718, 543, 3, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:27:38', '2019-07-25 20:04:37');
INSERT INTO `t_wallet` VALUES (1692723, 328, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:39', '2019-07-25 19:59:55');
INSERT INTO `t_wallet` VALUES (1692724, 328, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:39', '2019-07-25 19:59:55');
INSERT INTO `t_wallet` VALUES (1692725, 903, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:27:40', '2019-07-25 19:49:11');
INSERT INTO `t_wallet` VALUES (1692726, 903, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:27:40', '2019-07-25 19:49:11');
INSERT INTO `t_wallet` VALUES (1692745, 562, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:27:42', '2019-07-25 19:40:51');
INSERT INTO `t_wallet` VALUES (1692746, 562, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:27:42', '2019-07-25 19:40:51');
INSERT INTO `t_wallet` VALUES (1692753, 6, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:43', '2019-07-25 20:04:20');
INSERT INTO `t_wallet` VALUES (1692754, 6, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:43', '2019-07-25 20:04:21');
INSERT INTO `t_wallet` VALUES (1692765, 557, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:44', '2019-07-25 20:01:11');
INSERT INTO `t_wallet` VALUES (1692766, 557, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:44', '2019-07-25 20:01:11');
INSERT INTO `t_wallet` VALUES (1692769, 469, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:27:44', '2019-07-25 19:53:21');
INSERT INTO `t_wallet` VALUES (1692770, 469, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:27:44', '2019-07-25 19:53:21');
INSERT INTO `t_wallet` VALUES (1692775, 141, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:27:45', '2019-07-25 19:52:15');
INSERT INTO `t_wallet` VALUES (1692776, 141, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:27:45', '2019-07-25 19:52:15');
INSERT INTO `t_wallet` VALUES (1692779, 580, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:45', '2019-07-25 19:33:51');
INSERT INTO `t_wallet` VALUES (1692780, 580, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:45', '2019-07-25 19:33:51');
INSERT INTO `t_wallet` VALUES (1692793, 262, 2, 0.00000000, 0.00000001, 0.00000000, 11, '2019-07-25 17:27:48', '2019-07-25 19:44:53');
INSERT INTO `t_wallet` VALUES (1692794, 262, 3, -0.00000098, 0.00000000, 0.00000000, 11, '2019-07-25 17:27:48', '2019-07-25 19:44:53');
INSERT INTO `t_wallet` VALUES (1692809, 554, 3, 0.00000000, 0.00000000, 0.00000000, 49, '2019-07-25 17:27:52', '2019-07-25 19:59:18');
INSERT INTO `t_wallet` VALUES (1692810, 554, 2, 0.00000000, 0.00000000, 0.00000000, 49, '2019-07-25 17:27:52', '2019-07-25 19:59:18');
INSERT INTO `t_wallet` VALUES (1692813, 719, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:53', '2019-07-25 20:00:51');
INSERT INTO `t_wallet` VALUES (1692814, 719, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:27:53', '2019-07-25 20:00:51');
INSERT INTO `t_wallet` VALUES (1692827, 456, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:57', '2019-07-25 20:01:11');
INSERT INTO `t_wallet` VALUES (1692828, 456, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:27:57', '2019-07-25 20:01:11');
INSERT INTO `t_wallet` VALUES (1692829, 708, 3, 0.00000000, 0.00000098, 0.00000000, 47, '2019-07-25 17:27:58', '2019-07-25 20:01:19');
INSERT INTO `t_wallet` VALUES (1692830, 708, 2, -0.00000001, 0.00000000, 0.00000000, 47, '2019-07-25 17:27:58', '2019-07-25 20:01:19');
INSERT INTO `t_wallet` VALUES (1692849, 972, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:28:01', '2019-07-25 19:35:51');
INSERT INTO `t_wallet` VALUES (1692850, 972, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:28:01', '2019-07-25 19:35:51');
INSERT INTO `t_wallet` VALUES (1692851, 65, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:28:02', '2019-07-25 19:59:41');
INSERT INTO `t_wallet` VALUES (1692852, 65, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:28:02', '2019-07-25 19:59:41');
INSERT INTO `t_wallet` VALUES (1692861, 721, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:28:06', '2019-07-25 19:59:38');
INSERT INTO `t_wallet` VALUES (1692862, 721, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:28:06', '2019-07-25 19:59:38');
INSERT INTO `t_wallet` VALUES (1692865, 717, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:28:09', '2019-07-25 19:33:50');
INSERT INTO `t_wallet` VALUES (1692866, 717, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:28:09', '2019-07-25 19:33:50');
INSERT INTO `t_wallet` VALUES (1692881, 210, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:28:11', '2019-07-25 20:01:37');
INSERT INTO `t_wallet` VALUES (1692882, 210, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:28:11', '2019-07-25 20:01:37');
INSERT INTO `t_wallet` VALUES (1692883, 91, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:28:12', '2019-07-25 19:37:56');
INSERT INTO `t_wallet` VALUES (1692884, 91, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:28:12', '2019-07-25 19:37:56');
INSERT INTO `t_wallet` VALUES (1692889, 300, 2, 0.00000000, 0.00000001, 0.00000000, 10, '2019-07-25 17:28:14', '2019-07-25 19:45:43');
INSERT INTO `t_wallet` VALUES (1692890, 300, 3, -0.00000098, 0.00000000, 0.00000000, 10, '2019-07-25 17:28:14', '2019-07-25 19:45:43');
INSERT INTO `t_wallet` VALUES (1692891, 130, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:28:14', '2019-07-25 19:59:03');
INSERT INTO `t_wallet` VALUES (1692892, 130, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:28:14', '2019-07-25 19:59:03');
INSERT INTO `t_wallet` VALUES (1692893, 214, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:28:16', '2019-07-25 19:45:55');
INSERT INTO `t_wallet` VALUES (1692894, 214, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:28:16', '2019-07-25 19:45:55');
INSERT INTO `t_wallet` VALUES (1692913, 546, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:28:21', '2019-07-25 19:47:50');
INSERT INTO `t_wallet` VALUES (1692914, 546, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:28:21', '2019-07-25 19:47:50');
INSERT INTO `t_wallet` VALUES (1692921, 345, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:28:23', '2019-07-25 19:55:52');
INSERT INTO `t_wallet` VALUES (1692922, 345, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:28:23', '2019-07-25 19:55:52');
INSERT INTO `t_wallet` VALUES (1692935, 178, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:28:24', '2019-07-25 19:48:23');
INSERT INTO `t_wallet` VALUES (1692936, 178, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:28:24', '2019-07-25 19:48:23');
INSERT INTO `t_wallet` VALUES (1692943, 612, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:28:26', '2019-07-25 19:52:42');
INSERT INTO `t_wallet` VALUES (1692944, 612, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:28:26', '2019-07-25 19:52:42');
INSERT INTO `t_wallet` VALUES (1692947, 801, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:28:26', '2019-07-25 19:30:18');
INSERT INTO `t_wallet` VALUES (1692948, 801, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:28:26', '2019-07-25 19:30:18');
INSERT INTO `t_wallet` VALUES (1692949, 639, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:28:27', '2019-07-25 20:03:56');
INSERT INTO `t_wallet` VALUES (1692950, 639, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:28:27', '2019-07-25 20:03:56');
INSERT INTO `t_wallet` VALUES (1692955, 697, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:28:29', '2019-07-25 19:53:56');
INSERT INTO `t_wallet` VALUES (1692956, 697, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:28:29', '2019-07-25 19:53:56');
INSERT INTO `t_wallet` VALUES (1692959, 558, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:28:30', '2019-07-25 20:03:21');
INSERT INTO `t_wallet` VALUES (1692960, 558, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:28:30', '2019-07-25 20:03:21');
INSERT INTO `t_wallet` VALUES (1692961, 11, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:28:30', '2019-07-25 19:32:43');
INSERT INTO `t_wallet` VALUES (1692962, 11, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:28:30', '2019-07-25 19:32:43');
INSERT INTO `t_wallet` VALUES (1692983, 63, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 17:28:34', '2019-07-25 19:12:42');
INSERT INTO `t_wallet` VALUES (1692984, 63, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 17:28:34', '2019-07-25 19:12:42');
INSERT INTO `t_wallet` VALUES (1692989, 53, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:28:35', '2019-07-25 20:00:33');
INSERT INTO `t_wallet` VALUES (1692990, 53, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:28:35', '2019-07-25 20:00:33');
INSERT INTO `t_wallet` VALUES (1693003, 738, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:28:38', '2019-07-25 20:00:14');
INSERT INTO `t_wallet` VALUES (1693004, 738, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:28:38', '2019-07-25 20:00:14');
INSERT INTO `t_wallet` VALUES (1693013, 766, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:28:40', '2019-07-25 20:00:18');
INSERT INTO `t_wallet` VALUES (1693014, 766, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:28:40', '2019-07-25 20:00:18');
INSERT INTO `t_wallet` VALUES (1693019, 71, 2, 0.00000000, 0.00000001, 0.00000000, 10, '2019-07-25 17:28:40', '2019-07-25 19:38:17');
INSERT INTO `t_wallet` VALUES (1693020, 71, 3, -0.00000098, 0.00000000, 0.00000000, 10, '2019-07-25 17:28:41', '2019-07-25 19:38:17');
INSERT INTO `t_wallet` VALUES (1693025, 414, 2, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:28:41', '2019-07-25 19:53:29');
INSERT INTO `t_wallet` VALUES (1693026, 414, 3, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:28:41', '2019-07-25 19:53:29');
INSERT INTO `t_wallet` VALUES (1693043, 907, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:28:43', '2019-07-25 19:48:27');
INSERT INTO `t_wallet` VALUES (1693044, 907, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:28:43', '2019-07-25 19:48:27');
INSERT INTO `t_wallet` VALUES (1693047, 908, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:28:44', '2019-07-25 19:53:54');
INSERT INTO `t_wallet` VALUES (1693048, 908, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:28:44', '2019-07-25 19:53:54');
INSERT INTO `t_wallet` VALUES (1693055, 112, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:28:46', '2019-07-25 19:54:02');
INSERT INTO `t_wallet` VALUES (1693056, 112, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:28:46', '2019-07-25 19:54:02');
INSERT INTO `t_wallet` VALUES (1693097, 420, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:28:51', '2019-07-25 19:53:40');
INSERT INTO `t_wallet` VALUES (1693098, 420, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:28:51', '2019-07-25 19:53:40');
INSERT INTO `t_wallet` VALUES (1693099, 683, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:28:51', '2019-07-25 19:59:24');
INSERT INTO `t_wallet` VALUES (1693100, 683, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:28:51', '2019-07-25 19:59:24');
INSERT INTO `t_wallet` VALUES (1693101, 671, 2, 0.00000000, 0.00000000, 0.00000000, 46, '2019-07-25 17:28:51', '2019-07-25 19:56:38');
INSERT INTO `t_wallet` VALUES (1693102, 671, 3, 0.00000000, 0.00000000, 0.00000000, 46, '2019-07-25 17:28:51', '2019-07-25 19:56:38');
INSERT INTO `t_wallet` VALUES (1693111, 139, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:28:53', '2019-07-25 19:38:41');
INSERT INTO `t_wallet` VALUES (1693112, 139, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:28:53', '2019-07-25 19:38:41');
INSERT INTO `t_wallet` VALUES (1698225, 792, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:39:07', '2019-07-25 19:30:04');
INSERT INTO `t_wallet` VALUES (1698226, 792, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:39:07', '2019-07-25 19:30:04');
INSERT INTO `t_wallet` VALUES (1698257, 115, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:39:11', '2019-08-02 11:00:50');
INSERT INTO `t_wallet` VALUES (1698258, 115, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:39:11', '2019-08-02 11:00:50');
INSERT INTO `t_wallet` VALUES (1698269, 742, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:14', '2019-07-25 19:38:31');
INSERT INTO `t_wallet` VALUES (1698270, 742, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:14', '2019-07-25 19:38:31');
INSERT INTO `t_wallet` VALUES (1698271, 812, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:39:15', '2019-07-25 19:51:25');
INSERT INTO `t_wallet` VALUES (1698272, 812, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:39:15', '2019-07-25 19:51:25');
INSERT INTO `t_wallet` VALUES (1698273, 43, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:15', '2019-07-25 19:55:43');
INSERT INTO `t_wallet` VALUES (1698274, 43, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:15', '2019-07-25 19:55:43');
INSERT INTO `t_wallet` VALUES (1698277, 693, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:39:16', '2019-07-25 19:57:38');
INSERT INTO `t_wallet` VALUES (1698278, 693, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:39:16', '2019-07-25 19:57:38');
INSERT INTO `t_wallet` VALUES (1698279, 769, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:16', '2019-07-25 19:59:45');
INSERT INTO `t_wallet` VALUES (1698280, 769, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:16', '2019-07-25 19:59:45');
INSERT INTO `t_wallet` VALUES (1698283, 119, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:17', '2019-07-25 19:56:49');
INSERT INTO `t_wallet` VALUES (1698284, 119, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:17', '2019-07-25 19:56:49');
INSERT INTO `t_wallet` VALUES (1698287, 488, 2, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:39:18', '2019-07-25 19:47:00');
INSERT INTO `t_wallet` VALUES (1698288, 488, 3, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:39:18', '2019-07-25 19:47:00');
INSERT INTO `t_wallet` VALUES (1698293, 940, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:39:19', '2019-07-25 20:02:02');
INSERT INTO `t_wallet` VALUES (1698294, 940, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:39:19', '2019-07-25 20:02:02');
INSERT INTO `t_wallet` VALUES (1698303, 397, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:39:20', '2019-07-25 19:50:24');
INSERT INTO `t_wallet` VALUES (1698304, 397, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:39:20', '2019-07-25 19:50:24');
INSERT INTO `t_wallet` VALUES (1698315, 254, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:39:22', '2019-08-02 10:46:42');
INSERT INTO `t_wallet` VALUES (1698316, 254, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:39:22', '2019-08-02 10:46:42');
INSERT INTO `t_wallet` VALUES (1698319, 278, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:39:22', '2019-07-25 20:04:11');
INSERT INTO `t_wallet` VALUES (1698320, 278, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:39:22', '2019-07-25 20:04:11');
INSERT INTO `t_wallet` VALUES (1698327, 500, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:39:23', '2019-07-25 19:47:33');
INSERT INTO `t_wallet` VALUES (1698328, 500, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:39:23', '2019-07-25 19:47:33');
INSERT INTO `t_wallet` VALUES (1698419, 819, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:34', '2019-07-25 20:08:51');
INSERT INTO `t_wallet` VALUES (1698420, 819, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:34', '2019-07-25 20:08:51');
INSERT INTO `t_wallet` VALUES (1698423, 820, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:36', '2019-07-25 19:45:45');
INSERT INTO `t_wallet` VALUES (1698424, 820, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:36', '2019-07-25 19:45:45');
INSERT INTO `t_wallet` VALUES (1698465, 265, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:39:42', '2019-07-25 19:51:36');
INSERT INTO `t_wallet` VALUES (1698466, 265, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:39:42', '2019-07-25 19:51:36');
INSERT INTO `t_wallet` VALUES (1698467, 771, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:39:42', '2019-07-25 20:01:59');
INSERT INTO `t_wallet` VALUES (1698468, 771, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:39:42', '2019-07-25 20:02:00');
INSERT INTO `t_wallet` VALUES (1698495, 136, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:39:46', '2019-07-25 19:57:52');
INSERT INTO `t_wallet` VALUES (1698496, 136, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:39:46', '2019-07-25 19:57:52');
INSERT INTO `t_wallet` VALUES (1698497, 585, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:46', '2019-07-25 19:37:31');
INSERT INTO `t_wallet` VALUES (1698498, 585, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:46', '2019-07-25 19:37:31');
INSERT INTO `t_wallet` VALUES (1698503, 101, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:39:46', '2019-07-25 19:57:37');
INSERT INTO `t_wallet` VALUES (1698504, 101, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:39:46', '2019-07-25 19:57:37');
INSERT INTO `t_wallet` VALUES (1698519, 361, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:39:50', '2019-07-25 20:00:17');
INSERT INTO `t_wallet` VALUES (1698520, 361, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:39:50', '2019-07-25 20:00:17');
INSERT INTO `t_wallet` VALUES (1698531, 566, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:52', '2019-07-25 19:54:44');
INSERT INTO `t_wallet` VALUES (1698532, 566, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:39:52', '2019-07-25 19:54:44');
INSERT INTO `t_wallet` VALUES (1698533, 4, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:39:52', '2019-07-25 20:04:10');
INSERT INTO `t_wallet` VALUES (1698534, 4, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:39:52', '2019-07-25 20:04:09');
INSERT INTO `t_wallet` VALUES (1698613, 60, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:00', '2019-07-25 20:03:59');
INSERT INTO `t_wallet` VALUES (1698614, 60, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:00', '2019-07-25 20:03:59');
INSERT INTO `t_wallet` VALUES (1698623, 298, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:40:02', '2019-07-25 19:50:36');
INSERT INTO `t_wallet` VALUES (1698624, 298, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:40:02', '2019-07-25 19:50:36');
INSERT INTO `t_wallet` VALUES (1698625, 855, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:02', '2019-07-25 20:04:49');
INSERT INTO `t_wallet` VALUES (1698626, 855, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:02', '2019-07-25 20:04:49');
INSERT INTO `t_wallet` VALUES (1698631, 205, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:40:03', '2019-07-25 19:56:53');
INSERT INTO `t_wallet` VALUES (1698632, 205, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:40:03', '2019-07-25 19:56:53');
INSERT INTO `t_wallet` VALUES (1698649, 483, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:40:05', '2019-07-25 20:05:59');
INSERT INTO `t_wallet` VALUES (1698650, 483, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:40:05', '2019-07-25 20:05:59');
INSERT INTO `t_wallet` VALUES (1698651, 765, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:40:05', '2019-07-25 20:00:33');
INSERT INTO `t_wallet` VALUES (1698652, 765, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:40:05', '2019-07-25 20:00:33');
INSERT INTO `t_wallet` VALUES (1698689, 613, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:08', '2019-07-25 20:04:38');
INSERT INTO `t_wallet` VALUES (1698690, 613, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:08', '2019-07-25 20:04:38');
INSERT INTO `t_wallet` VALUES (1698695, 404, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:40:10', '2019-07-25 20:08:46');
INSERT INTO `t_wallet` VALUES (1698696, 404, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:40:10', '2019-07-25 20:08:46');
INSERT INTO `t_wallet` VALUES (1698699, 705, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:10', '2019-07-25 19:59:30');
INSERT INTO `t_wallet` VALUES (1698700, 705, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:10', '2019-07-25 19:59:30');
INSERT INTO `t_wallet` VALUES (1698725, 640, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:14', '2019-07-25 19:47:04');
INSERT INTO `t_wallet` VALUES (1698726, 640, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:14', '2019-07-25 19:47:04');
INSERT INTO `t_wallet` VALUES (1698741, 96, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:40:16', '2019-07-25 19:55:56');
INSERT INTO `t_wallet` VALUES (1698742, 96, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:40:16', '2019-07-25 19:55:57');
INSERT INTO `t_wallet` VALUES (1698743, 296, 3, 0.00000000, 0.00000000, 0.00000000, 28, '2019-07-25 17:40:16', '2019-07-25 20:09:32');
INSERT INTO `t_wallet` VALUES (1698744, 296, 2, 0.00000000, 0.00000000, 0.00000000, 28, '2019-07-25 17:40:16', '2019-07-25 20:09:32');
INSERT INTO `t_wallet` VALUES (1698763, 248, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:19', '2019-07-25 20:00:45');
INSERT INTO `t_wallet` VALUES (1698764, 248, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:19', '2019-07-25 20:00:45');
INSERT INTO `t_wallet` VALUES (1698769, 336, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:40:20', '2019-07-25 20:01:58');
INSERT INTO `t_wallet` VALUES (1698770, 336, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:40:20', '2019-07-25 20:01:58');
INSERT INTO `t_wallet` VALUES (1698801, 333, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:40:24', '2019-07-25 19:52:39');
INSERT INTO `t_wallet` VALUES (1698802, 333, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:40:24', '2019-07-25 19:52:39');
INSERT INTO `t_wallet` VALUES (1698815, 70, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:40:26', '2019-07-25 19:46:14');
INSERT INTO `t_wallet` VALUES (1698816, 70, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:40:26', '2019-07-25 19:46:14');
INSERT INTO `t_wallet` VALUES (1698823, 381, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:40:27', '2019-07-25 20:08:38');
INSERT INTO `t_wallet` VALUES (1698824, 381, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:40:27', '2019-07-25 20:08:38');
INSERT INTO `t_wallet` VALUES (1698845, 548, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:30', '2019-07-25 19:53:30');
INSERT INTO `t_wallet` VALUES (1698846, 548, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:30', '2019-07-25 19:53:30');
INSERT INTO `t_wallet` VALUES (1698879, 249, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:40:34', '2019-07-25 20:04:50');
INSERT INTO `t_wallet` VALUES (1698880, 249, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:40:34', '2019-07-25 20:04:50');
INSERT INTO `t_wallet` VALUES (1698923, 251, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:39', '2019-07-25 20:03:16');
INSERT INTO `t_wallet` VALUES (1698924, 251, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:39', '2019-07-25 20:03:16');
INSERT INTO `t_wallet` VALUES (1698931, 211, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:40', '2019-07-25 19:46:40');
INSERT INTO `t_wallet` VALUES (1698932, 211, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:40', '2019-07-25 19:46:40');
INSERT INTO `t_wallet` VALUES (1698937, 98, 2, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:40:41', '2019-07-25 20:04:36');
INSERT INTO `t_wallet` VALUES (1698938, 98, 3, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:40:41', '2019-07-25 20:04:36');
INSERT INTO `t_wallet` VALUES (1698947, 323, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:43', '2019-07-25 19:59:31');
INSERT INTO `t_wallet` VALUES (1698948, 323, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:43', '2019-07-25 19:59:31');
INSERT INTO `t_wallet` VALUES (1698973, 325, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:40:47', '2019-07-25 20:01:17');
INSERT INTO `t_wallet` VALUES (1698974, 325, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:40:47', '2019-07-25 20:01:17');
INSERT INTO `t_wallet` VALUES (1699029, 355, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:53', '2019-07-25 19:55:51');
INSERT INTO `t_wallet` VALUES (1699030, 355, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:53', '2019-07-25 19:55:51');
INSERT INTO `t_wallet` VALUES (1699057, 641, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:58', '2019-07-25 19:38:36');
INSERT INTO `t_wallet` VALUES (1699058, 641, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:40:58', '2019-07-25 19:38:36');
INSERT INTO `t_wallet` VALUES (1699059, 8, 2, 0.00000000, 0.00000000, 0.00000000, 38, '2019-07-25 17:40:58', '2019-08-02 11:00:50');
INSERT INTO `t_wallet` VALUES (1699060, 8, 3, 0.00000000, 0.00000000, 0.00000000, 38, '2019-07-25 17:40:58', '2019-08-02 11:00:50');
INSERT INTO `t_wallet` VALUES (1699061, 260, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:59', '2019-07-25 19:53:38');
INSERT INTO `t_wallet` VALUES (1699062, 260, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:40:59', '2019-07-25 19:53:38');
INSERT INTO `t_wallet` VALUES (1699063, 275, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:40:59', '2019-07-25 19:48:02');
INSERT INTO `t_wallet` VALUES (1699064, 275, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:40:59', '2019-07-25 19:48:02');
INSERT INTO `t_wallet` VALUES (1699079, 673, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:41:01', '2019-07-25 20:02:00');
INSERT INTO `t_wallet` VALUES (1699080, 673, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:41:01', '2019-07-25 20:02:00');
INSERT INTO `t_wallet` VALUES (1699081, 271, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:41:02', '2019-07-25 19:48:08');
INSERT INTO `t_wallet` VALUES (1699082, 271, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:41:02', '2019-07-25 19:48:08');
INSERT INTO `t_wallet` VALUES (1699091, 175, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:03', '2019-07-25 20:03:23');
INSERT INTO `t_wallet` VALUES (1699092, 175, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:03', '2019-07-25 20:03:23');
INSERT INTO `t_wallet` VALUES (1699093, 633, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:41:03', '2019-07-25 19:48:18');
INSERT INTO `t_wallet` VALUES (1699094, 633, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:41:03', '2019-07-25 19:48:18');
INSERT INTO `t_wallet` VALUES (1699101, 539, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:04', '2019-07-25 20:01:54');
INSERT INTO `t_wallet` VALUES (1699102, 539, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:04', '2019-07-25 20:01:54');
INSERT INTO `t_wallet` VALUES (1699103, 353, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:41:05', '2019-07-25 20:01:58');
INSERT INTO `t_wallet` VALUES (1699104, 353, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:41:05', '2019-07-25 20:01:58');
INSERT INTO `t_wallet` VALUES (1699115, 810, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:41:07', '2019-07-25 19:55:25');
INSERT INTO `t_wallet` VALUES (1699116, 810, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:41:07', '2019-07-25 19:55:25');
INSERT INTO `t_wallet` VALUES (1699117, 221, 2, 0.00000000, 0.00000000, 0.00000000, 27, '2019-07-25 17:41:08', '2019-07-25 19:48:50');
INSERT INTO `t_wallet` VALUES (1699118, 221, 3, 0.00000000, 0.00000000, 0.00000000, 27, '2019-07-25 17:41:08', '2019-07-25 19:48:51');
INSERT INTO `t_wallet` VALUES (1699123, 508, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:41:09', '2019-07-25 20:00:06');
INSERT INTO `t_wallet` VALUES (1699124, 508, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:41:09', '2019-07-25 20:00:06');
INSERT INTO `t_wallet` VALUES (1699131, 398, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:41:10', '2019-07-25 19:53:55');
INSERT INTO `t_wallet` VALUES (1699132, 398, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:41:10', '2019-07-25 19:53:55');
INSERT INTO `t_wallet` VALUES (1699141, 476, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:41:12', '2019-07-25 19:32:11');
INSERT INTO `t_wallet` VALUES (1699142, 476, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:41:12', '2019-07-25 19:32:11');
INSERT INTO `t_wallet` VALUES (1699151, 464, 2, 0.00000000, 0.00000000, 0.00000000, 2, '2019-07-25 17:41:13', '2019-07-25 19:31:47');
INSERT INTO `t_wallet` VALUES (1699152, 464, 3, 0.00000000, 0.00000000, 0.00000000, 2, '2019-07-25 17:41:13', '2019-07-25 19:31:47');
INSERT INTO `t_wallet` VALUES (1699157, 187, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:41:14', '2019-07-25 19:53:54');
INSERT INTO `t_wallet` VALUES (1699158, 187, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:41:14', '2019-07-25 19:53:54');
INSERT INTO `t_wallet` VALUES (1699163, 594, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:41:16', '2019-07-25 20:00:52');
INSERT INTO `t_wallet` VALUES (1699164, 594, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:41:16', '2019-07-25 20:00:52');
INSERT INTO `t_wallet` VALUES (1699169, 400, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:17', '2019-07-25 19:57:18');
INSERT INTO `t_wallet` VALUES (1699170, 400, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:17', '2019-07-25 19:57:18');
INSERT INTO `t_wallet` VALUES (1699185, 190, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:41:19', '2019-07-25 19:16:52');
INSERT INTO `t_wallet` VALUES (1699186, 190, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:41:19', '2019-07-25 19:16:52');
INSERT INTO `t_wallet` VALUES (1699199, 783, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:41:21', '2019-07-25 19:57:22');
INSERT INTO `t_wallet` VALUES (1699200, 783, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:41:21', '2019-07-25 19:57:22');
INSERT INTO `t_wallet` VALUES (1699209, 373, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:23', '2019-07-25 20:04:51');
INSERT INTO `t_wallet` VALUES (1699210, 373, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:23', '2019-07-25 20:04:51');
INSERT INTO `t_wallet` VALUES (1699219, 814, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:41:25', '2019-07-25 20:02:21');
INSERT INTO `t_wallet` VALUES (1699220, 814, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:41:25', '2019-07-25 20:02:21');
INSERT INTO `t_wallet` VALUES (1699231, 918, 2, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:41:26', '2019-07-25 19:50:05');
INSERT INTO `t_wallet` VALUES (1699232, 918, 3, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:41:26', '2019-07-25 19:50:05');
INSERT INTO `t_wallet` VALUES (1699239, 593, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:41:29', '2019-07-25 19:48:57');
INSERT INTO `t_wallet` VALUES (1699240, 593, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:41:29', '2019-07-25 19:48:57');
INSERT INTO `t_wallet` VALUES (1699241, 9, 2, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 17:41:29', '2019-07-25 19:48:34');
INSERT INTO `t_wallet` VALUES (1699242, 9, 3, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 17:41:29', '2019-07-25 19:48:34');
INSERT INTO `t_wallet` VALUES (1699243, 461, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:30', '2019-07-25 19:56:00');
INSERT INTO `t_wallet` VALUES (1699244, 461, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:30', '2019-07-25 19:56:01');
INSERT INTO `t_wallet` VALUES (1699247, 410, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:41:31', '2019-07-25 19:39:18');
INSERT INTO `t_wallet` VALUES (1699248, 410, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:41:31', '2019-07-25 19:39:18');
INSERT INTO `t_wallet` VALUES (1699249, 952, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:41:32', '2019-07-25 19:53:33');
INSERT INTO `t_wallet` VALUES (1699250, 952, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:41:32', '2019-07-25 19:53:34');
INSERT INTO `t_wallet` VALUES (1699253, 620, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:34', '2019-07-25 19:53:27');
INSERT INTO `t_wallet` VALUES (1699254, 620, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:34', '2019-07-25 19:53:27');
INSERT INTO `t_wallet` VALUES (1699257, 188, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:41:35', '2019-07-25 20:05:59');
INSERT INTO `t_wallet` VALUES (1699258, 188, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:41:35', '2019-07-25 20:05:59');
INSERT INTO `t_wallet` VALUES (1699259, 480, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:41:35', '2019-07-25 19:46:06');
INSERT INTO `t_wallet` VALUES (1699260, 480, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:41:35', '2019-07-25 19:46:06');
INSERT INTO `t_wallet` VALUES (1699267, 295, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:41:36', '2019-07-25 19:25:06');
INSERT INTO `t_wallet` VALUES (1699268, 295, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:41:36', '2019-07-25 19:25:06');
INSERT INTO `t_wallet` VALUES (1699273, 255, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:41:37', '2019-07-25 19:59:36');
INSERT INTO `t_wallet` VALUES (1699274, 255, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:41:37', '2019-07-25 19:59:36');
INSERT INTO `t_wallet` VALUES (1699279, 860, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:40', '2019-07-25 20:04:11');
INSERT INTO `t_wallet` VALUES (1699280, 860, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:40', '2019-07-25 20:04:11');
INSERT INTO `t_wallet` VALUES (1699285, 455, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:41', '2019-07-25 19:48:37');
INSERT INTO `t_wallet` VALUES (1699286, 455, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:41', '2019-07-25 19:48:37');
INSERT INTO `t_wallet` VALUES (1699299, 572, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:41:44', '2019-07-25 20:06:01');
INSERT INTO `t_wallet` VALUES (1699300, 572, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:41:44', '2019-07-25 20:06:02');
INSERT INTO `t_wallet` VALUES (1699303, 541, 2, 0.00000000, 0.00000000, 0.00000000, 84, '2019-07-25 17:41:45', '2019-07-25 20:01:35');
INSERT INTO `t_wallet` VALUES (1699304, 541, 3, 0.00000000, 0.00000000, 0.00000000, 84, '2019-07-25 17:41:45', '2019-07-25 20:01:35');
INSERT INTO `t_wallet` VALUES (1699307, 598, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:45', '2019-07-25 19:50:30');
INSERT INTO `t_wallet` VALUES (1699308, 598, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:45', '2019-07-25 19:50:30');
INSERT INTO `t_wallet` VALUES (1699319, 631, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:49', '2019-07-25 19:51:12');
INSERT INTO `t_wallet` VALUES (1699320, 631, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:49', '2019-07-25 19:51:12');
INSERT INTO `t_wallet` VALUES (1699327, 201, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:50', '2019-07-25 20:00:56');
INSERT INTO `t_wallet` VALUES (1699328, 201, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:41:50', '2019-07-25 20:00:56');
INSERT INTO `t_wallet` VALUES (1699335, 272, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:41:52', '2019-07-25 20:06:21');
INSERT INTO `t_wallet` VALUES (1699336, 272, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:41:52', '2019-07-25 20:06:22');
INSERT INTO `t_wallet` VALUES (1699355, 655, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:41:54', '2019-07-25 20:04:36');
INSERT INTO `t_wallet` VALUES (1699356, 655, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:41:54', '2019-07-25 20:04:36');
INSERT INTO `t_wallet` VALUES (1699377, 552, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:58', '2019-07-25 19:28:11');
INSERT INTO `t_wallet` VALUES (1699378, 552, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:41:58', '2019-07-25 19:28:12');
INSERT INTO `t_wallet` VALUES (1699391, 416, 2, 0.00000000, 0.00000000, 0.00000000, 1001, '2019-07-25 17:42:00', '2019-07-25 20:01:27');
INSERT INTO `t_wallet` VALUES (1699392, 416, 3, 0.00000000, 0.00000000, 0.00000000, 1001, '2019-07-25 17:42:00', '2019-07-25 20:01:27');
INSERT INTO `t_wallet` VALUES (1699393, 433, 3, 0.00000000, 0.00000000, 0.00000000, 45, '2019-07-25 17:42:00', '2019-07-25 20:00:42');
INSERT INTO `t_wallet` VALUES (1699394, 433, 2, 0.00000000, 0.00000000, 0.00000000, 45, '2019-07-25 17:42:00', '2019-07-25 20:00:42');
INSERT INTO `t_wallet` VALUES (1699419, 69, 2, 0.00000000, 0.00000000, 0.00000000, 46, '2019-07-25 17:42:04', '2019-07-25 19:41:59');
INSERT INTO `t_wallet` VALUES (1699420, 69, 3, 0.00000000, 0.00000000, 0.00000000, 46, '2019-07-25 17:42:04', '2019-07-25 19:41:59');
INSERT INTO `t_wallet` VALUES (1699441, 776, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:42:07', '2019-07-25 19:44:58');
INSERT INTO `t_wallet` VALUES (1699442, 776, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:42:07', '2019-07-25 19:44:58');
INSERT INTO `t_wallet` VALUES (1699451, 754, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:42:09', '2019-07-25 19:42:42');
INSERT INTO `t_wallet` VALUES (1699452, 754, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:42:09', '2019-07-25 19:42:42');
INSERT INTO `t_wallet` VALUES (1699457, 152, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:10', '2019-07-25 19:48:30');
INSERT INTO `t_wallet` VALUES (1699458, 152, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:10', '2019-07-25 19:48:31');
INSERT INTO `t_wallet` VALUES (1699469, 679, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:42:14', '2019-07-25 19:59:53');
INSERT INTO `t_wallet` VALUES (1699470, 679, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:42:14', '2019-07-25 19:59:53');
INSERT INTO `t_wallet` VALUES (1699499, 867, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:42:18', '2019-07-25 19:45:11');
INSERT INTO `t_wallet` VALUES (1699500, 867, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:42:18', '2019-07-25 19:45:11');
INSERT INTO `t_wallet` VALUES (1699507, 789, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:42:20', '2019-07-25 19:59:37');
INSERT INTO `t_wallet` VALUES (1699508, 789, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:42:20', '2019-07-25 19:59:38');
INSERT INTO `t_wallet` VALUES (1699527, 368, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:42:24', '2019-07-25 19:59:11');
INSERT INTO `t_wallet` VALUES (1699528, 368, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:42:24', '2019-07-25 19:59:12');
INSERT INTO `t_wallet` VALUES (1699539, 118, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:42:25', '2019-07-25 20:03:36');
INSERT INTO `t_wallet` VALUES (1699540, 118, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:42:25', '2019-07-25 20:03:36');
INSERT INTO `t_wallet` VALUES (1699545, 51, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:42:26', '2019-07-25 20:01:22');
INSERT INTO `t_wallet` VALUES (1699546, 51, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:42:26', '2019-07-25 20:01:22');
INSERT INTO `t_wallet` VALUES (1699579, 198, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:42:31', '2019-07-25 19:29:26');
INSERT INTO `t_wallet` VALUES (1699580, 198, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:42:31', '2019-07-25 19:29:27');
INSERT INTO `t_wallet` VALUES (1699581, 294, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:42:31', '2019-07-25 19:59:27');
INSERT INTO `t_wallet` VALUES (1699582, 294, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:42:31', '2019-07-25 19:59:27');
INSERT INTO `t_wallet` VALUES (1699599, 173, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:42:34', '2019-07-25 20:00:10');
INSERT INTO `t_wallet` VALUES (1699600, 173, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:42:34', '2019-07-25 20:00:10');
INSERT INTO `t_wallet` VALUES (1699627, 835, 3, 0.00000000, 0.00000000, 0.00000000, 144, '2019-07-25 17:42:38', '2019-07-25 19:49:59');
INSERT INTO `t_wallet` VALUES (1699628, 835, 2, 0.00000000, 0.00000000, 0.00000000, 144, '2019-07-25 17:42:38', '2019-07-25 19:49:59');
INSERT INTO `t_wallet` VALUES (1699635, 110, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:42:39', '2019-07-25 19:59:18');
INSERT INTO `t_wallet` VALUES (1699636, 110, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:42:39', '2019-07-25 19:59:18');
INSERT INTO `t_wallet` VALUES (1699637, 795, 3, 0.00000000, 0.00000294, 0.00000000, 1051, '2019-07-25 17:42:39', '2019-07-25 19:58:23');
INSERT INTO `t_wallet` VALUES (1699638, 795, 2, -0.00000003, 0.00000000, 0.00000000, 1051, '2019-07-25 17:42:39', '2019-07-25 19:58:23');
INSERT INTO `t_wallet` VALUES (1699639, 92, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:42:40', '2019-07-25 19:56:15');
INSERT INTO `t_wallet` VALUES (1699640, 92, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:42:40', '2019-07-25 19:56:15');
INSERT INTO `t_wallet` VALUES (1699647, 802, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:42:41', '2019-07-25 19:41:45');
INSERT INTO `t_wallet` VALUES (1699648, 802, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:42:41', '2019-07-25 19:41:45');
INSERT INTO `t_wallet` VALUES (1699649, 628, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:41', '2019-07-25 19:54:34');
INSERT INTO `t_wallet` VALUES (1699650, 628, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:41', '2019-07-25 19:54:34');
INSERT INTO `t_wallet` VALUES (1699659, 542, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:42:43', '2019-07-25 19:54:55');
INSERT INTO `t_wallet` VALUES (1699660, 542, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:42:43', '2019-07-25 19:54:55');
INSERT INTO `t_wallet` VALUES (1699661, 367, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:42:44', '2019-07-25 20:05:52');
INSERT INTO `t_wallet` VALUES (1699662, 367, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:42:44', '2019-07-25 20:05:52');
INSERT INTO `t_wallet` VALUES (1699665, 225, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:42:45', '2019-07-25 20:01:36');
INSERT INTO `t_wallet` VALUES (1699666, 225, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:42:45', '2019-07-25 20:01:36');
INSERT INTO `t_wallet` VALUES (1699669, 264, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:42:45', '2019-07-25 20:00:43');
INSERT INTO `t_wallet` VALUES (1699670, 264, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:42:45', '2019-07-25 20:00:43');
INSERT INTO `t_wallet` VALUES (1699673, 856, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:42:48', '2019-07-25 19:36:46');
INSERT INTO `t_wallet` VALUES (1699674, 856, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:42:48', '2019-07-25 19:36:46');
INSERT INTO `t_wallet` VALUES (1699675, 103, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:42:49', '2019-07-25 20:01:43');
INSERT INTO `t_wallet` VALUES (1699676, 103, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:42:49', '2019-07-25 20:01:43');
INSERT INTO `t_wallet` VALUES (1699677, 506, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:50', '2019-07-25 19:23:48');
INSERT INTO `t_wallet` VALUES (1699678, 506, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:50', '2019-07-25 19:23:48');
INSERT INTO `t_wallet` VALUES (1699679, 528, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:50', '2019-07-25 19:24:00');
INSERT INTO `t_wallet` VALUES (1699680, 528, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:50', '2019-07-25 19:24:00');
INSERT INTO `t_wallet` VALUES (1699681, 31, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:42:51', '2019-07-25 19:38:37');
INSERT INTO `t_wallet` VALUES (1699682, 31, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:42:51', '2019-07-25 19:38:37');
INSERT INTO `t_wallet` VALUES (1699683, 322, 2, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 17:42:51', '2019-07-25 19:55:45');
INSERT INTO `t_wallet` VALUES (1699684, 322, 3, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 17:42:51', '2019-07-25 19:55:45');
INSERT INTO `t_wallet` VALUES (1699685, 497, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:42:53', '2019-07-25 19:44:54');
INSERT INTO `t_wallet` VALUES (1699686, 497, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:42:53', '2019-07-25 19:44:54');
INSERT INTO `t_wallet` VALUES (1699693, 999, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:54', '2019-07-25 19:56:26');
INSERT INTO `t_wallet` VALUES (1699694, 999, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:54', '2019-07-25 19:56:26');
INSERT INTO `t_wallet` VALUES (1699699, 389, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:42:55', '2019-07-25 20:00:21');
INSERT INTO `t_wallet` VALUES (1699700, 389, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:42:55', '2019-07-25 20:00:21');
INSERT INTO `t_wallet` VALUES (1699701, 242, 2, 0.00000000, 0.00000000, 0.00000000, 26, '2019-07-25 17:42:56', '2019-07-25 19:41:49');
INSERT INTO `t_wallet` VALUES (1699702, 242, 3, 0.00000000, 0.00000000, 0.00000000, 26, '2019-07-25 17:42:56', '2019-07-25 19:41:49');
INSERT INTO `t_wallet` VALUES (1699703, 59, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:57', '2019-07-25 19:41:57');
INSERT INTO `t_wallet` VALUES (1699704, 59, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:42:57', '2019-07-25 19:41:57');
INSERT INTO `t_wallet` VALUES (1699705, 736, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:42:58', '2019-07-25 19:57:42');
INSERT INTO `t_wallet` VALUES (1699706, 736, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:42:58', '2019-07-25 19:57:42');
INSERT INTO `t_wallet` VALUES (1699715, 949, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:00', '2019-07-25 19:53:17');
INSERT INTO `t_wallet` VALUES (1699716, 949, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:00', '2019-07-25 19:53:17');
INSERT INTO `t_wallet` VALUES (1699721, 950, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:43:01', '2019-07-25 19:34:40');
INSERT INTO `t_wallet` VALUES (1699722, 950, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:43:01', '2019-07-25 19:34:40');
INSERT INTO `t_wallet` VALUES (1699733, 459, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:43:05', '2019-07-25 20:06:00');
INSERT INTO `t_wallet` VALUES (1699734, 459, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:43:05', '2019-07-25 20:06:00');
INSERT INTO `t_wallet` VALUES (1699737, 193, 2, 0.00000000, 0.00000000, 0.00000000, 38, '2019-07-25 17:43:06', '2019-07-25 19:48:44');
INSERT INTO `t_wallet` VALUES (1699738, 193, 3, 0.00000000, 0.00000000, 0.00000000, 38, '2019-07-25 17:43:06', '2019-07-25 19:48:44');
INSERT INTO `t_wallet` VALUES (1699745, 341, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:43:07', '2019-08-02 10:58:42');
INSERT INTO `t_wallet` VALUES (1699746, 341, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:43:07', '2019-08-02 10:58:42');
INSERT INTO `t_wallet` VALUES (1699759, 408, 2, 0.00000000, 0.00000000, 0.00000000, 135, '2019-07-25 17:43:09', '2019-08-02 10:58:13');
INSERT INTO `t_wallet` VALUES (1699760, 408, 3, 0.00000000, 0.00000000, 0.00000000, 135, '2019-07-25 17:43:09', '2019-08-02 10:58:13');
INSERT INTO `t_wallet` VALUES (1699761, 354, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:10', '2019-07-25 19:57:51');
INSERT INTO `t_wallet` VALUES (1699762, 354, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:10', '2019-07-25 19:57:51');
INSERT INTO `t_wallet` VALUES (1699763, 307, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:10', '2019-07-25 19:45:01');
INSERT INTO `t_wallet` VALUES (1699764, 307, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:10', '2019-07-25 19:45:01');
INSERT INTO `t_wallet` VALUES (1699765, 647, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:43:11', '2019-07-25 19:56:33');
INSERT INTO `t_wallet` VALUES (1699766, 647, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:43:11', '2019-07-25 19:56:33');
INSERT INTO `t_wallet` VALUES (1699769, 590, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:13', '2019-07-25 20:00:45');
INSERT INTO `t_wallet` VALUES (1699770, 590, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:13', '2019-07-25 20:00:45');
INSERT INTO `t_wallet` VALUES (1699775, 253, 2, 0.00000000, 0.00000000, 0.00000000, 29, '2019-07-25 17:43:15', '2019-07-25 19:55:45');
INSERT INTO `t_wallet` VALUES (1699776, 253, 3, 0.00000000, 0.00000000, 0.00000000, 29, '2019-07-25 17:43:15', '2019-07-25 19:55:45');
INSERT INTO `t_wallet` VALUES (1699777, 424, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:43:16', '2019-07-25 19:52:43');
INSERT INTO `t_wallet` VALUES (1699778, 424, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:43:16', '2019-07-25 19:52:43');
INSERT INTO `t_wallet` VALUES (1699785, 616, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:43:19', '2019-08-02 10:58:13');
INSERT INTO `t_wallet` VALUES (1699786, 616, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:43:19', '2019-08-02 10:58:13');
INSERT INTO `t_wallet` VALUES (1699787, 411, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:19', '2019-07-25 19:56:37');
INSERT INTO `t_wallet` VALUES (1699788, 411, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:19', '2019-07-25 19:56:37');
INSERT INTO `t_wallet` VALUES (1699791, 157, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:22', '2019-07-25 19:48:59');
INSERT INTO `t_wallet` VALUES (1699792, 157, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:22', '2019-07-25 19:48:59');
INSERT INTO `t_wallet` VALUES (1699803, 50, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:43:24', '2019-07-25 20:04:45');
INSERT INTO `t_wallet` VALUES (1699804, 50, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:43:24', '2019-07-25 20:04:45');
INSERT INTO `t_wallet` VALUES (1699805, 412, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:26', '2019-07-25 19:38:48');
INSERT INTO `t_wallet` VALUES (1699806, 412, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:26', '2019-07-25 19:38:48');
INSERT INTO `t_wallet` VALUES (1699807, 779, 2, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:43:26', '2019-07-25 20:00:23');
INSERT INTO `t_wallet` VALUES (1699808, 779, 3, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:43:26', '2019-07-25 20:00:23');
INSERT INTO `t_wallet` VALUES (1699811, 811, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:43:27', '2019-07-25 20:03:24');
INSERT INTO `t_wallet` VALUES (1699812, 811, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:43:27', '2019-07-25 20:03:24');
INSERT INTO `t_wallet` VALUES (1699815, 944, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:28', '2019-07-25 20:01:28');
INSERT INTO `t_wallet` VALUES (1699816, 944, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:28', '2019-07-25 20:01:28');
INSERT INTO `t_wallet` VALUES (1699819, 114, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:43:28', '2019-07-25 20:01:56');
INSERT INTO `t_wallet` VALUES (1699820, 114, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:43:28', '2019-07-25 20:01:56');
INSERT INTO `t_wallet` VALUES (1699827, 504, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:29', '2019-07-25 20:03:33');
INSERT INTO `t_wallet` VALUES (1699828, 504, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:29', '2019-07-25 20:03:33');
INSERT INTO `t_wallet` VALUES (1699829, 269, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:30', '2019-07-25 19:41:06');
INSERT INTO `t_wallet` VALUES (1699830, 269, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:30', '2019-07-25 19:41:06');
INSERT INTO `t_wallet` VALUES (1699833, 711, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:31', '2019-07-25 19:56:13');
INSERT INTO `t_wallet` VALUES (1699834, 711, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:31', '2019-07-25 19:56:13');
INSERT INTO `t_wallet` VALUES (1699837, 605, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:32', '2019-07-25 19:47:22');
INSERT INTO `t_wallet` VALUES (1699838, 605, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:32', '2019-07-25 19:47:22');
INSERT INTO `t_wallet` VALUES (1699839, 339, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:32', '2019-07-25 19:58:58');
INSERT INTO `t_wallet` VALUES (1699840, 339, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:32', '2019-07-25 19:58:58');
INSERT INTO `t_wallet` VALUES (1699845, 955, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:43:34', '2019-07-25 19:51:12');
INSERT INTO `t_wallet` VALUES (1699846, 955, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:43:34', '2019-07-25 19:51:12');
INSERT INTO `t_wallet` VALUES (1699851, 337, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:43:36', '2019-07-25 20:00:29');
INSERT INTO `t_wallet` VALUES (1699852, 337, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:43:36', '2019-07-25 20:00:29');
INSERT INTO `t_wallet` VALUES (1699861, 281, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:43:37', '2019-07-25 19:56:56');
INSERT INTO `t_wallet` VALUES (1699862, 281, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:43:37', '2019-07-25 19:56:56');
INSERT INTO `t_wallet` VALUES (1699863, 764, 2, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:43:38', '2019-07-25 20:01:49');
INSERT INTO `t_wallet` VALUES (1699864, 764, 3, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:43:38', '2019-07-25 20:01:49');
INSERT INTO `t_wallet` VALUES (1699867, 49, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:39', '2019-07-25 20:08:48');
INSERT INTO `t_wallet` VALUES (1699868, 49, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:39', '2019-07-25 20:08:48');
INSERT INTO `t_wallet` VALUES (1699883, 914, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:43:42', '2019-07-25 19:29:29');
INSERT INTO `t_wallet` VALUES (1699884, 914, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:43:42', '2019-07-25 19:29:29');
INSERT INTO `t_wallet` VALUES (1699905, 973, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:49', '2019-07-25 19:31:24');
INSERT INTO `t_wallet` VALUES (1699906, 973, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:43:49', '2019-07-25 19:31:24');
INSERT INTO `t_wallet` VALUES (1699909, 960, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:43:50', '2019-07-25 19:58:26');
INSERT INTO `t_wallet` VALUES (1699910, 960, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:43:50', '2019-07-25 19:58:26');
INSERT INTO `t_wallet` VALUES (1699911, 41, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:51', '2019-08-02 10:47:24');
INSERT INTO `t_wallet` VALUES (1699912, 41, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:51', '2019-08-02 10:47:24');
INSERT INTO `t_wallet` VALUES (1699919, 689, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:43:52', '2019-07-25 19:54:16');
INSERT INTO `t_wallet` VALUES (1699920, 689, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:43:52', '2019-07-25 19:54:16');
INSERT INTO `t_wallet` VALUES (1699937, 531, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:43:56', '2019-08-02 10:49:35');
INSERT INTO `t_wallet` VALUES (1699938, 531, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:43:56', '2019-08-02 10:49:35');
INSERT INTO `t_wallet` VALUES (1699943, 762, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:43:57', '2019-07-25 20:00:50');
INSERT INTO `t_wallet` VALUES (1699944, 762, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:43:57', '2019-07-25 20:00:50');
INSERT INTO `t_wallet` VALUES (1699945, 452, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:57', '2019-07-25 19:41:23');
INSERT INTO `t_wallet` VALUES (1699946, 452, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:43:57', '2019-07-25 19:41:23');
INSERT INTO `t_wallet` VALUES (1699949, 148, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:58', '2019-07-25 19:56:32');
INSERT INTO `t_wallet` VALUES (1699950, 148, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:43:58', '2019-07-25 19:56:32');
INSERT INTO `t_wallet` VALUES (1699953, 131, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:43:59', '2019-07-25 19:51:38');
INSERT INTO `t_wallet` VALUES (1699954, 131, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:43:59', '2019-07-25 19:51:38');
INSERT INTO `t_wallet` VALUES (1699963, 365, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:44:01', '2019-07-25 19:53:19');
INSERT INTO `t_wallet` VALUES (1699964, 365, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:44:01', '2019-07-25 19:53:19');
INSERT INTO `t_wallet` VALUES (1699975, 310, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:44:04', '2019-07-25 19:48:29');
INSERT INTO `t_wallet` VALUES (1699976, 310, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:44:04', '2019-07-25 19:48:29');
INSERT INTO `t_wallet` VALUES (1699977, 677, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:44:04', '2019-07-25 19:37:22');
INSERT INTO `t_wallet` VALUES (1699978, 677, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:44:04', '2019-07-25 19:37:23');
INSERT INTO `t_wallet` VALUES (1699991, 619, 2, 0.00000000, 0.00000000, 0.00000000, 45, '2019-07-25 17:44:10', '2019-07-25 20:01:39');
INSERT INTO `t_wallet` VALUES (1699992, 619, 3, 0.00000000, 0.00000000, 0.00000000, 45, '2019-07-25 17:44:10', '2019-07-25 20:01:39');
INSERT INTO `t_wallet` VALUES (1699995, 151, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:44:11', '2019-07-25 20:01:53');
INSERT INTO `t_wallet` VALUES (1699996, 151, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:44:11', '2019-07-25 20:01:53');
INSERT INTO `t_wallet` VALUES (1700011, 291, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:16', '2019-07-25 19:26:14');
INSERT INTO `t_wallet` VALUES (1700012, 291, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:16', '2019-07-25 19:26:14');
INSERT INTO `t_wallet` VALUES (1700019, 876, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:17', '2019-07-25 19:46:33');
INSERT INTO `t_wallet` VALUES (1700020, 876, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:17', '2019-07-25 19:46:34');
INSERT INTO `t_wallet` VALUES (1700023, 529, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:44:19', '2019-07-25 20:03:39');
INSERT INTO `t_wallet` VALUES (1700024, 529, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:44:19', '2019-07-25 20:03:39');
INSERT INTO `t_wallet` VALUES (1700027, 756, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:44:20', '2019-08-02 11:00:31');
INSERT INTO `t_wallet` VALUES (1700028, 756, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:44:20', '2019-08-02 11:00:31');
INSERT INTO `t_wallet` VALUES (1700031, 634, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:44:21', '2019-07-25 20:04:11');
INSERT INTO `t_wallet` VALUES (1700032, 634, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:44:21', '2019-07-25 20:04:11');
INSERT INTO `t_wallet` VALUES (1700033, 385, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:44:21', '2019-08-02 10:51:13');
INSERT INTO `t_wallet` VALUES (1700034, 385, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:44:21', '2019-08-02 10:51:13');
INSERT INTO `t_wallet` VALUES (1700035, 228, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:44:22', '2019-07-25 19:46:14');
INSERT INTO `t_wallet` VALUES (1700036, 228, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:44:22', '2019-07-25 19:46:14');
INSERT INTO `t_wallet` VALUES (1700037, 996, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:44:24', '2019-07-25 19:59:07');
INSERT INTO `t_wallet` VALUES (1700038, 996, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:44:24', '2019-07-25 19:59:07');
INSERT INTO `t_wallet` VALUES (1700039, 150, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:44:24', '2019-07-25 19:59:23');
INSERT INTO `t_wallet` VALUES (1700040, 150, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:44:24', '2019-07-25 19:59:23');
INSERT INTO `t_wallet` VALUES (1700041, 852, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:25', '2019-07-25 20:01:49');
INSERT INTO `t_wallet` VALUES (1700042, 852, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:25', '2019-07-25 20:01:49');
INSERT INTO `t_wallet` VALUES (1700043, 180, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:44:27', '2019-07-25 19:35:18');
INSERT INTO `t_wallet` VALUES (1700044, 180, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:44:27', '2019-07-25 19:35:18');
INSERT INTO `t_wallet` VALUES (1700049, 836, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:28', '2019-07-25 19:56:52');
INSERT INTO `t_wallet` VALUES (1700050, 836, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:28', '2019-07-25 19:56:52');
INSERT INTO `t_wallet` VALUES (1700051, 563, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:44:29', '2019-07-25 19:18:26');
INSERT INTO `t_wallet` VALUES (1700052, 563, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:44:30', '2019-07-25 19:18:26');
INSERT INTO `t_wallet` VALUES (1700053, 430, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:44:30', '2019-07-25 19:25:49');
INSERT INTO `t_wallet` VALUES (1700054, 430, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:44:30', '2019-07-25 19:25:49');
INSERT INTO `t_wallet` VALUES (1700055, 102, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:44:32', '2019-07-25 19:56:20');
INSERT INTO `t_wallet` VALUES (1700056, 102, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:44:32', '2019-07-25 19:56:20');
INSERT INTO `t_wallet` VALUES (1700059, 17, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:33', '2019-07-25 19:54:06');
INSERT INTO `t_wallet` VALUES (1700060, 17, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:33', '2019-07-25 19:54:06');
INSERT INTO `t_wallet` VALUES (1700061, 378, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:34', '2019-07-25 19:54:04');
INSERT INTO `t_wallet` VALUES (1700062, 378, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:34', '2019-07-25 19:54:04');
INSERT INTO `t_wallet` VALUES (1700069, 951, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:44:36', '2019-07-25 19:59:50');
INSERT INTO `t_wallet` VALUES (1700070, 951, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:44:36', '2019-07-25 19:59:50');
INSERT INTO `t_wallet` VALUES (1700077, 21, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:37', '2019-07-25 19:52:52');
INSERT INTO `t_wallet` VALUES (1700078, 21, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:37', '2019-07-25 19:52:53');
INSERT INTO `t_wallet` VALUES (1700087, 659, 2, 0.00000000, 0.00000000, 0.00000000, 35, '2019-07-25 17:44:39', '2019-07-25 19:44:02');
INSERT INTO `t_wallet` VALUES (1700088, 659, 3, 0.00000000, 0.00000000, 0.00000000, 35, '2019-07-25 17:44:39', '2019-07-25 19:44:01');
INSERT INTO `t_wallet` VALUES (1700091, 133, 2, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:44:41', '2019-07-25 19:53:48');
INSERT INTO `t_wallet` VALUES (1700092, 133, 3, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:44:41', '2019-07-25 19:53:48');
INSERT INTO `t_wallet` VALUES (1700093, 752, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:44:42', '2019-07-25 19:58:42');
INSERT INTO `t_wallet` VALUES (1700094, 752, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:44:42', '2019-07-25 19:58:42');
INSERT INTO `t_wallet` VALUES (1700097, 898, 2, 0.00000000, 0.00000000, 0.00000000, 79, '2019-07-25 17:44:43', '2019-07-25 19:54:31');
INSERT INTO `t_wallet` VALUES (1700098, 898, 3, 0.00000000, 0.00000000, 0.00000000, 79, '2019-07-25 17:44:43', '2019-07-25 19:54:31');
INSERT INTO `t_wallet` VALUES (1700101, 692, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:44', '2019-07-25 19:58:47');
INSERT INTO `t_wallet` VALUES (1700102, 692, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:44', '2019-07-25 19:58:47');
INSERT INTO `t_wallet` VALUES (1700105, 684, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:46', '2019-07-25 19:49:19');
INSERT INTO `t_wallet` VALUES (1700106, 684, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:44:47', '2019-07-25 19:49:19');
INSERT INTO `t_wallet` VALUES (1700107, 395, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:47', '2019-07-25 19:46:05');
INSERT INTO `t_wallet` VALUES (1700108, 395, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:47', '2019-07-25 19:46:05');
INSERT INTO `t_wallet` VALUES (1700111, 24, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:44:48', '2019-07-25 19:49:35');
INSERT INTO `t_wallet` VALUES (1700112, 24, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:44:48', '2019-07-25 19:49:35');
INSERT INTO `t_wallet` VALUES (1700113, 654, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:44:49', '2019-07-25 20:04:46');
INSERT INTO `t_wallet` VALUES (1700114, 654, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:44:49', '2019-07-25 20:04:47');
INSERT INTO `t_wallet` VALUES (1700115, 432, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:44:49', '2019-07-25 19:43:45');
INSERT INTO `t_wallet` VALUES (1700116, 432, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:44:49', '2019-07-25 19:43:45');
INSERT INTO `t_wallet` VALUES (1700117, 839, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:44:51', '2019-07-25 19:42:13');
INSERT INTO `t_wallet` VALUES (1700118, 839, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:44:51', '2019-07-25 19:42:13');
INSERT INTO `t_wallet` VALUES (1700121, 948, 2, 0.00000000, 0.00000001, 0.00000000, 37, '2019-07-25 17:44:56', '2019-07-25 19:57:21');
INSERT INTO `t_wallet` VALUES (1700122, 948, 3, -0.00000098, 0.00000000, 0.00000000, 37, '2019-07-25 17:44:56', '2019-07-25 19:57:21');
INSERT INTO `t_wallet` VALUES (1700123, 12, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:57', '2019-07-25 20:00:23');
INSERT INTO `t_wallet` VALUES (1700124, 12, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:44:57', '2019-07-25 20:00:23');
INSERT INTO `t_wallet` VALUES (1700125, 7, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:44:58', '2019-07-25 19:58:14');
INSERT INTO `t_wallet` VALUES (1700126, 7, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:44:58', '2019-07-25 19:58:14');
INSERT INTO `t_wallet` VALUES (1700127, 579, 2, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:45:00', '2019-07-25 19:57:53');
INSERT INTO `t_wallet` VALUES (1700128, 579, 3, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:45:00', '2019-07-25 19:57:53');
INSERT INTO `t_wallet` VALUES (1700129, 329, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:45:02', '2019-07-25 19:22:09');
INSERT INTO `t_wallet` VALUES (1700130, 329, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:45:02', '2019-07-25 19:22:09');
INSERT INTO `t_wallet` VALUES (1700131, 652, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:02', '2019-07-25 19:44:35');
INSERT INTO `t_wallet` VALUES (1700132, 652, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:02', '2019-07-25 19:44:35');
INSERT INTO `t_wallet` VALUES (1700133, 725, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:45:04', '2019-07-25 19:35:40');
INSERT INTO `t_wallet` VALUES (1700134, 725, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:45:04', '2019-07-25 19:35:40');
INSERT INTO `t_wallet` VALUES (1700135, 492, 2, 0.00000000, 0.00000000, 0.00000000, 31, '2019-07-25 17:45:06', '2019-07-25 20:00:36');
INSERT INTO `t_wallet` VALUES (1700136, 492, 3, 0.00000000, 0.00000000, 0.00000000, 31, '2019-07-25 17:45:06', '2019-07-25 20:00:36');
INSERT INTO `t_wallet` VALUES (1700137, 25, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:08', '2019-07-25 20:07:07');
INSERT INTO `t_wallet` VALUES (1700138, 25, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:08', '2019-07-25 20:07:07');
INSERT INTO `t_wallet` VALUES (1700145, 912, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:45:10', '2019-07-25 20:03:37');
INSERT INTO `t_wallet` VALUES (1700146, 912, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:45:10', '2019-07-25 20:03:37');
INSERT INTO `t_wallet` VALUES (1700149, 185, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:45:14', '2019-07-25 20:01:22');
INSERT INTO `t_wallet` VALUES (1700150, 185, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:45:14', '2019-07-25 20:01:22');
INSERT INTO `t_wallet` VALUES (1700165, 356, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:18', '2019-07-25 19:31:17');
INSERT INTO `t_wallet` VALUES (1700166, 356, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:18', '2019-07-25 19:31:17');
INSERT INTO `t_wallet` VALUES (1700171, 680, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:45:21', '2019-07-25 20:06:00');
INSERT INTO `t_wallet` VALUES (1700172, 680, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:45:21', '2019-07-25 20:06:00');
INSERT INTO `t_wallet` VALUES (1700175, 405, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:45:23', '2019-07-25 20:04:24');
INSERT INTO `t_wallet` VALUES (1700176, 405, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:45:23', '2019-07-25 20:04:24');
INSERT INTO `t_wallet` VALUES (1700177, 869, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:45:24', '2019-07-25 19:24:47');
INSERT INTO `t_wallet` VALUES (1700178, 869, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:45:24', '2019-07-25 19:24:47');
INSERT INTO `t_wallet` VALUES (1700187, 897, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:45:26', '2019-07-25 20:04:18');
INSERT INTO `t_wallet` VALUES (1700188, 897, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:45:26', '2019-07-25 20:04:18');
INSERT INTO `t_wallet` VALUES (1700195, 731, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:45:29', '2019-07-25 19:57:11');
INSERT INTO `t_wallet` VALUES (1700196, 731, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:45:29', '2019-07-25 19:57:11');
INSERT INTO `t_wallet` VALUES (1700201, 758, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:45:29', '2019-07-25 19:39:27');
INSERT INTO `t_wallet` VALUES (1700202, 758, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:45:29', '2019-07-25 19:39:27');
INSERT INTO `t_wallet` VALUES (1700207, 965, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:45:30', '2019-07-25 20:03:00');
INSERT INTO `t_wallet` VALUES (1700208, 965, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:45:30', '2019-07-25 20:03:00');
INSERT INTO `t_wallet` VALUES (1700209, 571, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:45:31', '2019-07-25 19:51:33');
INSERT INTO `t_wallet` VALUES (1700210, 571, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:45:31', '2019-07-25 19:51:33');
INSERT INTO `t_wallet` VALUES (1700211, 895, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:45:32', '2019-07-25 20:01:20');
INSERT INTO `t_wallet` VALUES (1700212, 895, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:45:32', '2019-07-25 20:01:20');
INSERT INTO `t_wallet` VALUES (1700233, 753, 2, 0.00000000, 0.00000000, 0.00000000, 250, '2019-07-25 17:45:46', '2019-07-25 20:06:32');
INSERT INTO `t_wallet` VALUES (1700234, 753, 3, 0.00000000, 0.00000000, 0.00000000, 250, '2019-07-25 17:45:46', '2019-07-25 20:06:32');
INSERT INTO `t_wallet` VALUES (1700235, 997, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:45:47', '2019-07-25 19:33:04');
INSERT INTO `t_wallet` VALUES (1700236, 997, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:45:47', '2019-07-25 19:33:04');
INSERT INTO `t_wallet` VALUES (1700241, 963, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:48', '2019-07-25 20:02:02');
INSERT INTO `t_wallet` VALUES (1700242, 963, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:48', '2019-07-25 20:02:02');
INSERT INTO `t_wallet` VALUES (1700243, 97, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:45:49', '2019-07-25 19:58:07');
INSERT INTO `t_wallet` VALUES (1700244, 97, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:45:49', '2019-07-25 19:58:07');
INSERT INTO `t_wallet` VALUES (1700247, 828, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:45:52', '2019-08-02 10:56:06');
INSERT INTO `t_wallet` VALUES (1700248, 828, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:45:52', '2019-08-02 10:56:06');
INSERT INTO `t_wallet` VALUES (1700251, 311, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:45:53', '2019-07-25 19:57:10');
INSERT INTO `t_wallet` VALUES (1700252, 311, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:45:53', '2019-07-25 19:57:10');
INSERT INTO `t_wallet` VALUES (1700253, 887, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:45:54', '2019-07-25 19:37:32');
INSERT INTO `t_wallet` VALUES (1700254, 887, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:45:54', '2019-07-25 19:37:32');
INSERT INTO `t_wallet` VALUES (1700259, 449, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:45:55', '2019-07-25 19:53:22');
INSERT INTO `t_wallet` VALUES (1700260, 449, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:45:55', '2019-07-25 19:53:23');
INSERT INTO `t_wallet` VALUES (1700263, 213, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:45:56', '2019-07-25 19:48:23');
INSERT INTO `t_wallet` VALUES (1700264, 213, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:45:56', '2019-07-25 19:48:23');
INSERT INTO `t_wallet` VALUES (1700267, 704, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:45:56', '2019-07-25 19:36:31');
INSERT INTO `t_wallet` VALUES (1700268, 704, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:45:57', '2019-07-25 19:36:31');
INSERT INTO `t_wallet` VALUES (1700269, 535, 2, 0.00000000, 0.00000000, 0.00000000, 38, '2019-07-25 17:45:57', '2019-07-25 19:40:36');
INSERT INTO `t_wallet` VALUES (1700270, 535, 3, 0.00000000, 0.00000000, 0.00000000, 38, '2019-07-25 17:45:57', '2019-07-25 19:40:36');
INSERT INTO `t_wallet` VALUES (1700271, 527, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:45:58', '2019-07-25 19:57:12');
INSERT INTO `t_wallet` VALUES (1700272, 527, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:45:58', '2019-07-25 19:57:12');
INSERT INTO `t_wallet` VALUES (1700277, 38, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:59', '2019-07-25 20:00:56');
INSERT INTO `t_wallet` VALUES (1700278, 38, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:45:59', '2019-07-25 20:00:56');
INSERT INTO `t_wallet` VALUES (1700283, 197, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:46:00', '2019-07-25 20:05:51');
INSERT INTO `t_wallet` VALUES (1700284, 197, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:46:01', '2019-07-25 20:05:51');
INSERT INTO `t_wallet` VALUES (1700285, 823, 2, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:46:01', '2019-07-25 19:40:12');
INSERT INTO `t_wallet` VALUES (1700286, 823, 3, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:46:01', '2019-07-25 19:40:12');
INSERT INTO `t_wallet` VALUES (1700287, 564, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:46:02', '2019-07-25 19:59:45');
INSERT INTO `t_wallet` VALUES (1700288, 564, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:46:02', '2019-07-25 19:59:45');
INSERT INTO `t_wallet` VALUES (1700291, 703, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:04', '2019-07-25 19:59:39');
INSERT INTO `t_wallet` VALUES (1700292, 703, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:04', '2019-07-25 19:59:39');
INSERT INTO `t_wallet` VALUES (1700297, 560, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:06', '2019-07-25 20:01:34');
INSERT INTO `t_wallet` VALUES (1700298, 560, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:06', '2019-07-25 20:01:34');
INSERT INTO `t_wallet` VALUES (1700315, 42, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:46:12', '2019-07-25 20:01:50');
INSERT INTO `t_wallet` VALUES (1700316, 42, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:46:12', '2019-07-25 20:01:50');
INSERT INTO `t_wallet` VALUES (1700325, 915, 2, 0.00000000, 0.00000000, 0.00000000, 31, '2019-07-25 17:46:13', '2019-07-25 20:04:19');
INSERT INTO `t_wallet` VALUES (1700326, 915, 3, 0.00000000, 0.00000000, 0.00000000, 31, '2019-07-25 17:46:13', '2019-07-25 20:04:19');
INSERT INTO `t_wallet` VALUES (1700327, 216, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:46:14', '2019-07-25 19:54:19');
INSERT INTO `t_wallet` VALUES (1700328, 216, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:46:14', '2019-07-25 19:54:19');
INSERT INTO `t_wallet` VALUES (1700331, 399, 2, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:46:15', '2019-07-25 19:57:50');
INSERT INTO `t_wallet` VALUES (1700332, 399, 3, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:46:15', '2019-07-25 19:57:50');
INSERT INTO `t_wallet` VALUES (1700339, 289, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:17', '2019-07-25 19:56:48');
INSERT INTO `t_wallet` VALUES (1700340, 289, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:17', '2019-07-25 19:56:48');
INSERT INTO `t_wallet` VALUES (1700341, 735, 2, 1000.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:18', '2019-08-02 10:47:28');
INSERT INTO `t_wallet` VALUES (1700342, 735, 3, 1000.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:18', '2019-08-02 10:47:28');
INSERT INTO `t_wallet` VALUES (1700343, 900, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:19', '2019-07-25 20:06:42');
INSERT INTO `t_wallet` VALUES (1700344, 900, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:19', '2019-07-25 20:06:42');
INSERT INTO `t_wallet` VALUES (1700345, 183, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:46:19', '2019-07-25 19:36:03');
INSERT INTO `t_wallet` VALUES (1700346, 183, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:46:19', '2019-07-25 19:36:03');
INSERT INTO `t_wallet` VALUES (1700347, 893, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:20', '2019-07-25 19:40:59');
INSERT INTO `t_wallet` VALUES (1700348, 893, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:20', '2019-07-25 19:40:59');
INSERT INTO `t_wallet` VALUES (1700351, 875, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:21', '2019-07-25 19:53:52');
INSERT INTO `t_wallet` VALUES (1700352, 875, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:21', '2019-07-25 19:53:52');
INSERT INTO `t_wallet` VALUES (1700355, 534, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:22', '2019-07-25 19:49:09');
INSERT INTO `t_wallet` VALUES (1700356, 534, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:22', '2019-07-25 19:49:09');
INSERT INTO `t_wallet` VALUES (1700357, 484, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:23', '2019-07-25 20:02:09');
INSERT INTO `t_wallet` VALUES (1700358, 484, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:23', '2019-07-25 20:02:09');
INSERT INTO `t_wallet` VALUES (1700359, 994, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:46:24', '2019-07-25 19:50:28');
INSERT INTO `t_wallet` VALUES (1700360, 994, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:46:24', '2019-07-25 19:50:28');
INSERT INTO `t_wallet` VALUES (1700363, 134, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:46:24', '2019-07-25 19:53:35');
INSERT INTO `t_wallet` VALUES (1700364, 134, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:46:24', '2019-07-25 19:53:35');
INSERT INTO `t_wallet` VALUES (1700375, 686, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:27', '2019-07-25 19:32:15');
INSERT INTO `t_wallet` VALUES (1700376, 686, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:27', '2019-07-25 19:32:15');
INSERT INTO `t_wallet` VALUES (1700383, 158, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:46:30', '2019-07-25 19:17:20');
INSERT INTO `t_wallet` VALUES (1700384, 158, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:46:30', '2019-07-25 19:17:20');
INSERT INTO `t_wallet` VALUES (1700387, 885, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:30', '2019-07-25 19:59:08');
INSERT INTO `t_wallet` VALUES (1700388, 885, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:30', '2019-07-25 19:59:08');
INSERT INTO `t_wallet` VALUES (1700409, 682, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:34', '2019-07-25 19:53:29');
INSERT INTO `t_wallet` VALUES (1700410, 682, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:34', '2019-07-25 19:53:29');
INSERT INTO `t_wallet` VALUES (1700413, 610, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:46:36', '2019-07-25 19:50:28');
INSERT INTO `t_wallet` VALUES (1700414, 610, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:46:36', '2019-07-25 19:50:28');
INSERT INTO `t_wallet` VALUES (1700417, 961, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:37', '2019-07-25 19:51:15');
INSERT INTO `t_wallet` VALUES (1700418, 961, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:37', '2019-07-25 19:51:16');
INSERT INTO `t_wallet` VALUES (1700419, 538, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:46:39', '2019-07-25 19:34:57');
INSERT INTO `t_wallet` VALUES (1700420, 538, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:46:39', '2019-07-25 19:34:57');
INSERT INTO `t_wallet` VALUES (1700431, 349, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:43', '2019-07-25 20:01:31');
INSERT INTO `t_wallet` VALUES (1700432, 349, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:46:43', '2019-07-25 20:01:31');
INSERT INTO `t_wallet` VALUES (1700445, 744, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:46:46', '2019-07-25 20:04:29');
INSERT INTO `t_wallet` VALUES (1700446, 744, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:46:46', '2019-07-25 20:04:29');
INSERT INTO `t_wallet` VALUES (1700457, 695, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:46:50', '2019-07-25 20:04:20');
INSERT INTO `t_wallet` VALUES (1700458, 695, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:46:50', '2019-07-25 20:04:20');
INSERT INTO `t_wallet` VALUES (1700467, 287, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:46:52', '2019-07-25 19:46:53');
INSERT INTO `t_wallet` VALUES (1700468, 287, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:46:52', '2019-07-25 19:46:53');
INSERT INTO `t_wallet` VALUES (1700471, 635, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:54', '2019-07-25 19:59:05');
INSERT INTO `t_wallet` VALUES (1700472, 635, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:46:54', '2019-07-25 19:59:05');
INSERT INTO `t_wallet` VALUES (1700473, 247, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:46:55', '2019-07-25 20:03:16');
INSERT INTO `t_wallet` VALUES (1700474, 247, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:46:55', '2019-07-25 20:03:16');
INSERT INTO `t_wallet` VALUES (1700475, 189, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:46:56', '2019-07-25 19:45:49');
INSERT INTO `t_wallet` VALUES (1700476, 189, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:46:56', '2019-07-25 19:45:49');
INSERT INTO `t_wallet` VALUES (1700479, 273, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:46:56', '2019-07-25 19:21:10');
INSERT INTO `t_wallet` VALUES (1700480, 273, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:46:56', '2019-07-25 19:21:10');
INSERT INTO `t_wallet` VALUES (1700481, 170, 2, 0.00000000, 0.00000000, 0.00000000, 48, '2019-07-25 17:46:58', '2019-08-02 10:46:58');
INSERT INTO `t_wallet` VALUES (1700482, 170, 3, 0.00000000, 0.00000000, 0.00000000, 48, '2019-07-25 17:46:58', '2019-08-02 10:46:58');
INSERT INTO `t_wallet` VALUES (1700485, 909, 2, 0.00000000, 0.00000000, 0.00000000, 29, '2019-07-25 17:46:59', '2019-07-25 19:40:54');
INSERT INTO `t_wallet` VALUES (1700486, 909, 3, 0.00000000, 0.00000000, 0.00000000, 29, '2019-07-25 17:46:59', '2019-07-25 19:40:54');
INSERT INTO `t_wallet` VALUES (1700487, 315, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:46:59', '2019-07-25 19:44:59');
INSERT INTO `t_wallet` VALUES (1700488, 315, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:46:59', '2019-07-25 19:44:59');
INSERT INTO `t_wallet` VALUES (1700493, 946, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:47:03', '2019-07-25 19:58:27');
INSERT INTO `t_wallet` VALUES (1700494, 946, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:47:03', '2019-07-25 19:58:27');
INSERT INTO `t_wallet` VALUES (1700501, 176, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:47:04', '2019-07-25 20:00:34');
INSERT INTO `t_wallet` VALUES (1700502, 176, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:47:04', '2019-07-25 20:00:34');
INSERT INTO `t_wallet` VALUES (1700503, 482, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:47:05', '2019-08-02 11:48:52');
INSERT INTO `t_wallet` VALUES (1700504, 482, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:47:05', '2019-08-02 11:48:52');
INSERT INTO `t_wallet` VALUES (1700505, 384, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:47:06', '2019-07-25 19:49:41');
INSERT INTO `t_wallet` VALUES (1700506, 384, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:47:06', '2019-07-25 19:49:41');
INSERT INTO `t_wallet` VALUES (1700513, 496, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:47:09', '2019-07-25 19:54:43');
INSERT INTO `t_wallet` VALUES (1700514, 496, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:47:09', '2019-07-25 19:54:43');
INSERT INTO `t_wallet` VALUES (1700519, 318, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:47:10', '2019-07-25 20:00:36');
INSERT INTO `t_wallet` VALUES (1700520, 318, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:47:10', '2019-07-25 20:00:36');
INSERT INTO `t_wallet` VALUES (1700521, 415, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:47:11', '2019-07-25 20:01:15');
INSERT INTO `t_wallet` VALUES (1700522, 415, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:47:11', '2019-07-25 20:01:16');
INSERT INTO `t_wallet` VALUES (1700523, 537, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:47:13', '2019-07-25 20:02:21');
INSERT INTO `t_wallet` VALUES (1700524, 537, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:47:13', '2019-07-25 20:02:21');
INSERT INTO `t_wallet` VALUES (1700525, 947, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:47:13', '2019-07-25 19:32:32');
INSERT INTO `t_wallet` VALUES (1700526, 947, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:47:13', '2019-07-25 19:32:32');
INSERT INTO `t_wallet` VALUES (1700531, 730, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:47:14', '2019-07-25 20:01:41');
INSERT INTO `t_wallet` VALUES (1700532, 730, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:47:14', '2019-07-25 20:01:42');
INSERT INTO `t_wallet` VALUES (1700533, 787, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:47:15', '2019-07-25 19:59:08');
INSERT INTO `t_wallet` VALUES (1700534, 787, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:47:15', '2019-07-25 19:59:08');
INSERT INTO `t_wallet` VALUES (1700539, 35, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:47:16', '2019-07-25 19:28:42');
INSERT INTO `t_wallet` VALUES (1700540, 35, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:47:16', '2019-07-25 19:28:42');
INSERT INTO `t_wallet` VALUES (1700541, 99, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:47:18', '2019-07-25 19:58:16');
INSERT INTO `t_wallet` VALUES (1700542, 99, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:47:18', '2019-07-25 19:58:16');
INSERT INTO `t_wallet` VALUES (1700545, 733, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 17:47:19', '2019-07-25 19:41:30');
INSERT INTO `t_wallet` VALUES (1700546, 733, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 17:47:19', '2019-07-25 19:41:30');
INSERT INTO `t_wallet` VALUES (1700557, 943, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:47:24', '2019-07-25 19:59:25');
INSERT INTO `t_wallet` VALUES (1700558, 943, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:47:24', '2019-07-25 19:59:25');
INSERT INTO `t_wallet` VALUES (1700567, 319, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:47:28', '2019-07-25 20:04:48');
INSERT INTO `t_wallet` VALUES (1700568, 319, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:47:28', '2019-07-25 20:04:48');
INSERT INTO `t_wallet` VALUES (1700589, 235, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:47:33', '2019-07-25 19:32:26');
INSERT INTO `t_wallet` VALUES (1700590, 235, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:47:33', '2019-07-25 19:32:26');
INSERT INTO `t_wallet` VALUES (1700599, 993, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:47:37', '2019-07-25 19:59:51');
INSERT INTO `t_wallet` VALUES (1700600, 993, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:47:37', '2019-07-25 19:59:51');
INSERT INTO `t_wallet` VALUES (1700603, 968, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:47:44', '2019-07-25 20:00:20');
INSERT INTO `t_wallet` VALUES (1700604, 968, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:47:44', '2019-07-25 20:00:20');
INSERT INTO `t_wallet` VALUES (1700605, 953, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:47:45', '2019-07-25 19:47:35');
INSERT INTO `t_wallet` VALUES (1700606, 953, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 17:47:45', '2019-07-25 19:47:35');
INSERT INTO `t_wallet` VALUES (1700609, 516, 2, 0.00000000, 0.00000000, 0.00000000, 34, '2019-07-25 17:47:46', '2019-07-25 19:41:13');
INSERT INTO `t_wallet` VALUES (1700610, 516, 3, 0.00000000, 0.00000000, 0.00000000, 34, '2019-07-25 17:47:46', '2019-07-25 19:41:13');
INSERT INTO `t_wallet` VALUES (1700621, 910, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:47:51', '2019-07-25 19:35:48');
INSERT INTO `t_wallet` VALUES (1700622, 910, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:47:51', '2019-07-25 19:35:48');
INSERT INTO `t_wallet` VALUES (1700633, 937, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:47:56', '2019-07-25 20:01:34');
INSERT INTO `t_wallet` VALUES (1700634, 937, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:47:57', '2019-07-25 20:01:35');
INSERT INTO `t_wallet` VALUES (1700635, 512, 2, 0.00000000, 0.00000000, 0.00000000, 97, '2019-07-25 17:47:57', '2019-07-25 19:48:55');
INSERT INTO `t_wallet` VALUES (1700636, 512, 3, 0.00000000, 0.00000000, 0.00000000, 97, '2019-07-25 17:47:57', '2019-07-25 19:48:55');
INSERT INTO `t_wallet` VALUES (1700647, 203, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:48:05', '2019-07-25 19:59:33');
INSERT INTO `t_wallet` VALUES (1700648, 203, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:48:05', '2019-07-25 19:59:33');
INSERT INTO `t_wallet` VALUES (1700651, 797, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:48:09', '2019-07-25 19:55:47');
INSERT INTO `t_wallet` VALUES (1700652, 797, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:48:09', '2019-07-25 19:55:47');
INSERT INTO `t_wallet` VALUES (1700655, 850, 2, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:48:10', '2019-07-25 19:52:53');
INSERT INTO `t_wallet` VALUES (1700656, 850, 3, 0.00000000, 0.00000000, 0.00000000, 22, '2019-07-25 17:48:10', '2019-07-25 19:52:53');
INSERT INTO `t_wallet` VALUES (1700679, 862, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:18', '2019-07-25 19:53:39');
INSERT INTO `t_wallet` VALUES (1700680, 862, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:18', '2019-07-25 19:53:39');
INSERT INTO `t_wallet` VALUES (1700683, 351, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:48:19', '2019-07-25 19:38:07');
INSERT INTO `t_wallet` VALUES (1700684, 351, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:48:19', '2019-07-25 19:38:07');
INSERT INTO `t_wallet` VALUES (1700691, 759, 2, 0.00000000, 0.00000000, 0.00000000, 40, '2019-07-25 17:48:20', '2019-07-25 20:03:01');
INSERT INTO `t_wallet` VALUES (1700692, 759, 3, 0.00000000, 0.00000000, 0.00000000, 40, '2019-07-25 17:48:20', '2019-07-25 20:03:01');
INSERT INTO `t_wallet` VALUES (1700695, 596, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:22', '2019-07-25 19:55:31');
INSERT INTO `t_wallet` VALUES (1700696, 596, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:22', '2019-07-25 19:55:31');
INSERT INTO `t_wallet` VALUES (1700703, 132, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:48:23', '2019-07-25 19:57:48');
INSERT INTO `t_wallet` VALUES (1700704, 132, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:48:23', '2019-07-25 19:57:48');
INSERT INTO `t_wallet` VALUES (1700707, 809, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:24', '2019-07-25 20:03:27');
INSERT INTO `t_wallet` VALUES (1700708, 809, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:24', '2019-07-25 20:03:28');
INSERT INTO `t_wallet` VALUES (1700713, 632, 2, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:48:27', '2019-07-25 19:57:03');
INSERT INTO `t_wallet` VALUES (1700714, 632, 3, 0.00000000, 0.00000000, 0.00000000, 36, '2019-07-25 17:48:27', '2019-07-25 19:57:03');
INSERT INTO `t_wallet` VALUES (1700715, 967, 2, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:48:27', '2019-07-25 19:43:14');
INSERT INTO `t_wallet` VALUES (1700716, 967, 3, 0.00000000, 0.00000000, 0.00000000, 21, '2019-07-25 17:48:27', '2019-07-25 19:43:15');
INSERT INTO `t_wallet` VALUES (1700719, 924, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:28', '2019-07-25 19:50:37');
INSERT INTO `t_wallet` VALUES (1700720, 924, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:28', '2019-07-25 19:50:37');
INSERT INTO `t_wallet` VALUES (1700723, 586, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:48:29', '2019-07-25 19:58:48');
INSERT INTO `t_wallet` VALUES (1700724, 586, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:48:29', '2019-07-25 19:58:48');
INSERT INTO `t_wallet` VALUES (1700729, 250, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:48:30', '2019-07-25 19:32:13');
INSERT INTO `t_wallet` VALUES (1700730, 250, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:48:30', '2019-07-25 19:32:14');
INSERT INTO `t_wallet` VALUES (1700737, 240, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:48:34', '2019-07-25 20:03:34');
INSERT INTO `t_wallet` VALUES (1700738, 240, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:48:34', '2019-07-25 20:03:35');
INSERT INTO `t_wallet` VALUES (1700741, 125, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:48:36', '2019-07-25 19:59:37');
INSERT INTO `t_wallet` VALUES (1700742, 125, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:48:36', '2019-07-25 19:59:37');
INSERT INTO `t_wallet` VALUES (1700743, 983, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:48:36', '2019-07-25 20:04:55');
INSERT INTO `t_wallet` VALUES (1700744, 983, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:48:36', '2019-07-25 20:04:55');
INSERT INTO `t_wallet` VALUES (1700755, 326, 2, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 17:48:39', '2019-07-25 19:50:32');
INSERT INTO `t_wallet` VALUES (1700756, 326, 3, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 17:48:39', '2019-07-25 19:50:32');
INSERT INTO `t_wallet` VALUES (1700757, 702, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:48:40', '2019-07-25 19:30:07');
INSERT INTO `t_wallet` VALUES (1700758, 702, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:48:40', '2019-07-25 19:30:08');
INSERT INTO `t_wallet` VALUES (1700759, 644, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:48:41', '2019-07-25 19:33:05');
INSERT INTO `t_wallet` VALUES (1700760, 644, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:48:41', '2019-07-25 19:33:05');
INSERT INTO `t_wallet` VALUES (1700761, 465, 2, 0.00000000, 0.00000000, 0.00000000, 37, '2019-07-25 17:48:41', '2019-07-25 19:49:41');
INSERT INTO `t_wallet` VALUES (1700762, 465, 3, 0.00000000, 0.00000000, 0.00000000, 37, '2019-07-25 17:48:41', '2019-07-25 19:49:41');
INSERT INTO `t_wallet` VALUES (1700765, 324, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:48:43', '2019-07-25 19:57:39');
INSERT INTO `t_wallet` VALUES (1700766, 324, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:48:43', '2019-07-25 19:57:39');
INSERT INTO `t_wallet` VALUES (1700775, 163, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:48:45', '2019-07-25 19:29:28');
INSERT INTO `t_wallet` VALUES (1700776, 163, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:48:45', '2019-07-25 19:29:28');
INSERT INTO `t_wallet` VALUES (1700783, 622, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:48:47', '2019-07-25 19:39:50');
INSERT INTO `t_wallet` VALUES (1700784, 622, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:48:47', '2019-07-25 19:39:50');
INSERT INTO `t_wallet` VALUES (1700785, 124, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:48:48', '2019-07-25 19:56:46');
INSERT INTO `t_wallet` VALUES (1700786, 124, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:48:48', '2019-07-25 19:56:46');
INSERT INTO `t_wallet` VALUES (1700787, 822, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:48', '2019-07-25 19:55:30');
INSERT INTO `t_wallet` VALUES (1700788, 822, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:48:48', '2019-07-25 19:55:30');
INSERT INTO `t_wallet` VALUES (1700797, 716, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:48:49', '2019-07-25 20:00:11');
INSERT INTO `t_wallet` VALUES (1700798, 716, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:48:49', '2019-07-25 20:00:11');
INSERT INTO `t_wallet` VALUES (1700819, 726, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:48:54', '2019-07-25 19:50:12');
INSERT INTO `t_wallet` VALUES (1700820, 726, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:48:54', '2019-07-25 19:50:12');
INSERT INTO `t_wallet` VALUES (1700823, 195, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:48:55', '2019-07-25 19:36:24');
INSERT INTO `t_wallet` VALUES (1700824, 195, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:48:55', '2019-07-25 19:36:24');
INSERT INTO `t_wallet` VALUES (1700863, 667, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:49:02', '2019-07-25 20:00:10');
INSERT INTO `t_wallet` VALUES (1700864, 667, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:49:02', '2019-07-25 20:00:11');
INSERT INTO `t_wallet` VALUES (1700865, 761, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:49:02', '2019-07-25 19:54:47');
INSERT INTO `t_wallet` VALUES (1700866, 761, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:49:02', '2019-07-25 19:54:47');
INSERT INTO `t_wallet` VALUES (1700869, 805, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:04', '2019-07-25 19:56:22');
INSERT INTO `t_wallet` VALUES (1700870, 805, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:04', '2019-07-25 19:56:22');
INSERT INTO `t_wallet` VALUES (1700871, 434, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:05', '2019-07-25 20:04:34');
INSERT INTO `t_wallet` VALUES (1700872, 434, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:05', '2019-07-25 20:04:34');
INSERT INTO `t_wallet` VALUES (1700877, 578, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:49:06', '2019-07-25 19:57:14');
INSERT INTO `t_wallet` VALUES (1700878, 578, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:49:06', '2019-07-25 19:57:14');
INSERT INTO `t_wallet` VALUES (1700879, 930, 2, 0.00000000, 0.00000000, 0.00000000, 34, '2019-07-25 17:49:08', '2019-07-25 19:57:58');
INSERT INTO `t_wallet` VALUES (1700880, 930, 3, 0.00000000, 0.00000000, 0.00000000, 34, '2019-07-25 17:49:08', '2019-07-25 19:57:58');
INSERT INTO `t_wallet` VALUES (1700881, 928, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:10', '2019-07-25 19:50:12');
INSERT INTO `t_wallet` VALUES (1700882, 928, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:10', '2019-07-25 19:50:13');
INSERT INTO `t_wallet` VALUES (1700883, 72, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:10', '2019-07-25 19:42:00');
INSERT INTO `t_wallet` VALUES (1700884, 72, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:11', '2019-07-25 19:42:00');
INSERT INTO `t_wallet` VALUES (1700887, 204, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:49:12', '2019-07-25 19:58:49');
INSERT INTO `t_wallet` VALUES (1700888, 204, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:49:12', '2019-07-25 19:58:49');
INSERT INTO `t_wallet` VALUES (1700891, 116, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:14', '2019-07-25 20:06:54');
INSERT INTO `t_wallet` VALUES (1700892, 116, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:14', '2019-07-25 20:06:53');
INSERT INTO `t_wallet` VALUES (1700893, 969, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:49:15', '2019-07-25 19:33:13');
INSERT INTO `t_wallet` VALUES (1700894, 969, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:49:15', '2019-07-25 19:33:13');
INSERT INTO `t_wallet` VALUES (1700897, 451, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:49:16', '2019-07-25 19:30:25');
INSERT INTO `t_wallet` VALUES (1700898, 451, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:49:16', '2019-07-25 19:30:25');
INSERT INTO `t_wallet` VALUES (1700901, 5, 2, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:49:19', '2019-07-25 19:57:48');
INSERT INTO `t_wallet` VALUES (1700902, 5, 3, 0.00000000, 0.00000000, 0.00000000, 32, '2019-07-25 17:49:19', '2019-07-25 19:57:48');
INSERT INTO `t_wallet` VALUES (1700915, 458, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:49:22', '2019-08-02 10:51:13');
INSERT INTO `t_wallet` VALUES (1700916, 458, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:49:22', '2019-08-02 10:51:13');
INSERT INTO `t_wallet` VALUES (1700919, 338, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:49:24', '2019-07-25 19:33:03');
INSERT INTO `t_wallet` VALUES (1700920, 338, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:49:24', '2019-07-25 19:33:03');
INSERT INTO `t_wallet` VALUES (1700947, 701, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:36', '2019-07-25 19:58:55');
INSERT INTO `t_wallet` VALUES (1700948, 701, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:36', '2019-07-25 19:58:55');
INSERT INTO `t_wallet` VALUES (1700951, 74, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:49:37', '2019-07-25 20:00:53');
INSERT INTO `t_wallet` VALUES (1700952, 74, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:49:37', '2019-07-25 20:00:53');
INSERT INTO `t_wallet` VALUES (1700955, 46, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:38', '2019-07-25 20:01:47');
INSERT INTO `t_wallet` VALUES (1700956, 46, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:49:38', '2019-07-25 20:01:47');
INSERT INTO `t_wallet` VALUES (1700957, 826, 2, 0.00000000, 0.00000000, 0.00000000, 2, '2019-07-25 17:49:38', '2019-07-25 19:17:11');
INSERT INTO `t_wallet` VALUES (1700958, 826, 3, 0.00000000, 0.00000000, 0.00000000, 2, '2019-07-25 17:49:38', '2019-07-25 19:17:11');
INSERT INTO `t_wallet` VALUES (1700961, 93, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:49:39', '2019-07-25 19:45:10');
INSERT INTO `t_wallet` VALUES (1700962, 93, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:49:39', '2019-07-25 19:45:10');
INSERT INTO `t_wallet` VALUES (1700965, 734, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:42', '2019-08-02 10:52:14');
INSERT INTO `t_wallet` VALUES (1700966, 734, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:42', '2019-08-02 10:52:14');
INSERT INTO `t_wallet` VALUES (1700967, 155, 2, 0.00000000, 0.00000000, 0.00000000, 64, '2019-07-25 17:49:44', '2019-07-25 20:03:35');
INSERT INTO `t_wallet` VALUES (1700968, 155, 3, 0.00000000, 0.00000000, 0.00000000, 64, '2019-07-25 17:49:44', '2019-07-25 20:03:35');
INSERT INTO `t_wallet` VALUES (1700971, 933, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:49', '2019-07-25 20:00:02');
INSERT INTO `t_wallet` VALUES (1700972, 933, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:49', '2019-07-25 20:00:02');
INSERT INTO `t_wallet` VALUES (1700975, 146, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:49:50', '2019-07-25 19:41:14');
INSERT INTO `t_wallet` VALUES (1700976, 146, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:49:50', '2019-07-25 19:41:14');
INSERT INTO `t_wallet` VALUES (1700985, 441, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:52', '2019-07-25 19:54:13');
INSERT INTO `t_wallet` VALUES (1700986, 441, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:52', '2019-07-25 19:54:13');
INSERT INTO `t_wallet` VALUES (1700993, 344, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:49:53', '2019-07-25 20:04:21');
INSERT INTO `t_wallet` VALUES (1700994, 344, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:49:53', '2019-07-25 20:04:21');
INSERT INTO `t_wallet` VALUES (1700995, 788, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:49:55', '2019-07-25 20:02:19');
INSERT INTO `t_wallet` VALUES (1700996, 788, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:49:55', '2019-07-25 20:02:19');
INSERT INTO `t_wallet` VALUES (1700997, 78, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:49:58', '2019-07-25 20:01:29');
INSERT INTO `t_wallet` VALUES (1700998, 78, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 17:49:58', '2019-07-25 20:01:29');
INSERT INTO `t_wallet` VALUES (1700999, 327, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:58', '2019-07-25 19:44:52');
INSERT INTO `t_wallet` VALUES (1701000, 327, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:49:58', '2019-07-25 19:44:52');
INSERT INTO `t_wallet` VALUES (1701001, 920, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:50:02', '2019-07-25 19:54:18');
INSERT INTO `t_wallet` VALUES (1701002, 920, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:50:02', '2019-07-25 19:54:18');
INSERT INTO `t_wallet` VALUES (1701003, 625, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:03', '2019-07-25 19:47:54');
INSERT INTO `t_wallet` VALUES (1701004, 625, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:03', '2019-07-25 19:47:54');
INSERT INTO `t_wallet` VALUES (1701005, 104, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:03', '2019-07-25 19:23:56');
INSERT INTO `t_wallet` VALUES (1701006, 104, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:03', '2019-07-25 19:23:56');
INSERT INTO `t_wallet` VALUES (1701007, 905, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:08', '2019-07-25 19:59:50');
INSERT INTO `t_wallet` VALUES (1701008, 905, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:08', '2019-07-25 19:59:50');
INSERT INTO `t_wallet` VALUES (1701009, 391, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:14', '2019-07-25 20:02:20');
INSERT INTO `t_wallet` VALUES (1701010, 391, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:14', '2019-07-25 20:02:20');
INSERT INTO `t_wallet` VALUES (1701011, 54, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:14', '2019-07-25 19:59:59');
INSERT INTO `t_wallet` VALUES (1701012, 54, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:14', '2019-07-25 19:59:59');
INSERT INTO `t_wallet` VALUES (1701023, 113, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:50:19', '2019-07-25 19:56:51');
INSERT INTO `t_wallet` VALUES (1701024, 113, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:50:19', '2019-07-25 19:56:51');
INSERT INTO `t_wallet` VALUES (1701029, 774, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:50:20', '2019-07-25 20:04:35');
INSERT INTO `t_wallet` VALUES (1701030, 774, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:50:20', '2019-07-25 20:04:35');
INSERT INTO `t_wallet` VALUES (1701033, 498, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:50:21', '2019-07-25 19:41:29');
INSERT INTO `t_wallet` VALUES (1701034, 498, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:50:21', '2019-07-25 19:41:29');
INSERT INTO `t_wallet` VALUES (1701041, 923, 2, 0.00000000, 0.00000000, 0.00000000, 45, '2019-07-25 17:50:23', '2019-07-25 19:47:52');
INSERT INTO `t_wallet` VALUES (1701042, 923, 3, 0.00000000, 0.00000000, 0.00000000, 45, '2019-07-25 17:50:23', '2019-07-25 19:47:52');
INSERT INTO `t_wallet` VALUES (1701045, 604, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:25', '2019-07-25 19:39:45');
INSERT INTO `t_wallet` VALUES (1701046, 604, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:25', '2019-07-25 19:39:45');
INSERT INTO `t_wallet` VALUES (1701049, 842, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:50:26', '2019-07-25 19:59:32');
INSERT INTO `t_wallet` VALUES (1701050, 842, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:50:26', '2019-07-25 19:59:32');
INSERT INTO `t_wallet` VALUES (1701051, 530, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:50:26', '2019-07-25 19:50:25');
INSERT INTO `t_wallet` VALUES (1701052, 530, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:50:26', '2019-07-25 19:50:25');
INSERT INTO `t_wallet` VALUES (1701067, 77, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:50:32', '2019-07-25 19:56:51');
INSERT INTO `t_wallet` VALUES (1701068, 77, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:50:32', '2019-07-25 19:56:51');
INSERT INTO `t_wallet` VALUES (1701069, 715, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:33', '2019-07-25 20:08:45');
INSERT INTO `t_wallet` VALUES (1701070, 715, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:33', '2019-07-25 20:08:45');
INSERT INTO `t_wallet` VALUES (1701073, 473, 2, 0.00000000, 0.00000000, 0.00000000, 44, '2019-07-25 17:50:35', '2019-07-25 19:54:01');
INSERT INTO `t_wallet` VALUES (1701074, 473, 3, 0.00000000, 0.00000000, 0.00000000, 44, '2019-07-25 17:50:35', '2019-07-25 19:54:01');
INSERT INTO `t_wallet` VALUES (1701075, 685, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:36', '2019-07-25 19:40:11');
INSERT INTO `t_wallet` VALUES (1701076, 685, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:36', '2019-07-25 19:40:11');
INSERT INTO `t_wallet` VALUES (1701077, 519, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:50:38', '2019-07-25 19:50:36');
INSERT INTO `t_wallet` VALUES (1701078, 519, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:50:38', '2019-07-25 19:50:36');
INSERT INTO `t_wallet` VALUES (1701081, 824, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:38', '2019-07-25 20:01:42');
INSERT INTO `t_wallet` VALUES (1701082, 824, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:50:38', '2019-07-25 20:01:42');
INSERT INTO `t_wallet` VALUES (1701093, 982, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:50:44', '2019-07-31 15:41:09');
INSERT INTO `t_wallet` VALUES (1701094, 982, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:50:44', '2019-07-31 15:41:08');
INSERT INTO `t_wallet` VALUES (1701095, 665, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:45', '2019-07-25 20:04:25');
INSERT INTO `t_wallet` VALUES (1701096, 665, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:45', '2019-07-25 20:04:25');
INSERT INTO `t_wallet` VALUES (1701103, 321, 2, 0.00000000, 0.00000000, 0.00000000, 26, '2019-07-25 17:50:48', '2019-07-25 19:53:53');
INSERT INTO `t_wallet` VALUES (1701104, 321, 3, 0.00000000, 0.00000000, 0.00000000, 26, '2019-07-25 17:50:48', '2019-07-25 19:53:53');
INSERT INTO `t_wallet` VALUES (1701105, 843, 2, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:50:50', '2019-07-25 19:50:52');
INSERT INTO `t_wallet` VALUES (1701106, 843, 3, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:50:50', '2019-07-25 19:50:52');
INSERT INTO `t_wallet` VALUES (1701107, 377, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:50:50', '2019-07-25 19:57:18');
INSERT INTO `t_wallet` VALUES (1701108, 377, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:50:50', '2019-07-25 19:57:18');
INSERT INTO `t_wallet` VALUES (1701115, 790, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:50:53', '2019-07-25 19:51:20');
INSERT INTO `t_wallet` VALUES (1701116, 790, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:50:53', '2019-07-25 19:51:20');
INSERT INTO `t_wallet` VALUES (1701117, 442, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:54', '2019-07-25 19:33:28');
INSERT INTO `t_wallet` VALUES (1701118, 442, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:50:54', '2019-07-25 19:33:28');
INSERT INTO `t_wallet` VALUES (1701127, 284, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:50:59', '2019-07-25 20:07:08');
INSERT INTO `t_wallet` VALUES (1701128, 284, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:50:59', '2019-07-25 20:07:08');
INSERT INTO `t_wallet` VALUES (1701129, 849, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:50:59', '2019-07-25 19:40:40');
INSERT INTO `t_wallet` VALUES (1701130, 849, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:50:59', '2019-07-25 19:40:40');
INSERT INTO `t_wallet` VALUES (1701133, 137, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:51:01', '2019-07-25 19:50:56');
INSERT INTO `t_wallet` VALUES (1701134, 137, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:51:01', '2019-07-25 19:50:56');
INSERT INTO `t_wallet` VALUES (1701141, 135, 2, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:51:03', '2019-07-25 20:03:57');
INSERT INTO `t_wallet` VALUES (1701142, 135, 3, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:51:03', '2019-07-25 20:03:57');
INSERT INTO `t_wallet` VALUES (1701143, 518, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:05', '2019-07-25 19:54:19');
INSERT INTO `t_wallet` VALUES (1701144, 518, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:05', '2019-07-25 19:54:19');
INSERT INTO `t_wallet` VALUES (1701147, 437, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:51:09', '2019-07-25 20:01:41');
INSERT INTO `t_wallet` VALUES (1701148, 437, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:51:09', '2019-07-25 20:01:41');
INSERT INTO `t_wallet` VALUES (1701153, 929, 2, 0.00000000, 0.00000000, 0.00000000, 260, '2019-07-25 17:51:10', '2019-07-25 19:57:16');
INSERT INTO `t_wallet` VALUES (1701154, 929, 3, 0.00000000, 0.00000000, 0.00000000, 260, '2019-07-25 17:51:10', '2019-07-25 19:57:16');
INSERT INTO `t_wallet` VALUES (1701167, 467, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:51:14', '2019-07-25 19:23:28');
INSERT INTO `t_wallet` VALUES (1701168, 467, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:51:14', '2019-07-25 19:23:28');
INSERT INTO `t_wallet` VALUES (1701171, 16, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:51:15', '2019-07-25 19:58:44');
INSERT INTO `t_wallet` VALUES (1701172, 16, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:51:15', '2019-07-25 19:58:44');
INSERT INTO `t_wallet` VALUES (1701181, 645, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:19', '2019-07-25 19:59:31');
INSERT INTO `t_wallet` VALUES (1701182, 645, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:19', '2019-07-25 19:59:31');
INSERT INTO `t_wallet` VALUES (1701191, 172, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:51:22', '2019-07-25 19:59:11');
INSERT INTO `t_wallet` VALUES (1701192, 172, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:51:22', '2019-07-25 19:59:11');
INSERT INTO `t_wallet` VALUES (1701193, 825, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:51:24', '2019-07-25 19:37:11');
INSERT INTO `t_wallet` VALUES (1701194, 825, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:51:24', '2019-07-25 19:37:11');
INSERT INTO `t_wallet` VALUES (1701195, 939, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:51:24', '2019-07-25 20:04:47');
INSERT INTO `t_wallet` VALUES (1701196, 939, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:51:25', '2019-07-25 20:04:47');
INSERT INTO `t_wallet` VALUES (1701199, 977, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:51:27', '2019-08-02 10:47:09');
INSERT INTO `t_wallet` VALUES (1701200, 977, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:51:27', '2019-08-02 10:47:09');
INSERT INTO `t_wallet` VALUES (1701201, 854, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:28', '2019-07-25 19:54:18');
INSERT INTO `t_wallet` VALUES (1701202, 854, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:28', '2019-07-25 19:54:18');
INSERT INTO `t_wallet` VALUES (1701205, 668, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:29', '2019-07-25 20:00:54');
INSERT INTO `t_wallet` VALUES (1701206, 668, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:29', '2019-07-25 20:00:54');
INSERT INTO `t_wallet` VALUES (1701213, 217, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:51:36', '2019-07-25 19:55:49');
INSERT INTO `t_wallet` VALUES (1701214, 217, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:51:36', '2019-07-25 19:55:49');
INSERT INTO `t_wallet` VALUES (1701229, 403, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:45', '2019-07-25 19:38:12');
INSERT INTO `t_wallet` VALUES (1701230, 403, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:51:45', '2019-07-25 19:38:12');
INSERT INTO `t_wallet` VALUES (1701233, 532, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:51:49', '2019-07-25 19:43:01');
INSERT INTO `t_wallet` VALUES (1701234, 532, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:51:49', '2019-07-25 19:43:01');
INSERT INTO `t_wallet` VALUES (1701237, 678, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:51:51', '2019-07-25 19:45:05');
INSERT INTO `t_wallet` VALUES (1701238, 678, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:51:51', '2019-07-25 19:45:05');
INSERT INTO `t_wallet` VALUES (1701239, 30, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:51:52', '2019-07-25 19:40:23');
INSERT INTO `t_wallet` VALUES (1701240, 30, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:51:53', '2019-07-25 19:40:23');
INSERT INTO `t_wallet` VALUES (1701241, 270, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:51:54', '2019-07-25 20:01:32');
INSERT INTO `t_wallet` VALUES (1701242, 270, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 17:51:55', '2019-07-25 20:01:33');
INSERT INTO `t_wallet` VALUES (1701243, 32, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:51:55', '2019-07-25 20:00:43');
INSERT INTO `t_wallet` VALUES (1701244, 32, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:51:55', '2019-07-25 20:00:43');
INSERT INTO `t_wallet` VALUES (1701245, 439, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:51:56', '2019-07-25 20:00:32');
INSERT INTO `t_wallet` VALUES (1701246, 439, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:51:56', '2019-07-25 20:00:32');
INSERT INTO `t_wallet` VALUES (1701247, 863, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:51:57', '2019-08-02 10:52:02');
INSERT INTO `t_wallet` VALUES (1701248, 863, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:51:57', '2019-08-02 10:52:02');
INSERT INTO `t_wallet` VALUES (1701259, 938, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 17:52:03', '2019-07-25 19:48:11');
INSERT INTO `t_wallet` VALUES (1701260, 938, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 17:52:03', '2019-07-25 19:48:11');
INSERT INTO `t_wallet` VALUES (1701273, 574, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:52:07', '2019-08-02 10:50:33');
INSERT INTO `t_wallet` VALUES (1701274, 574, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:52:07', '2019-08-02 10:50:33');
INSERT INTO `t_wallet` VALUES (1701287, 316, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:52:12', '2019-07-25 19:51:06');
INSERT INTO `t_wallet` VALUES (1701288, 316, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:52:12', '2019-07-25 19:51:06');
INSERT INTO `t_wallet` VALUES (1701293, 984, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:52:14', '2019-07-25 19:37:49');
INSERT INTO `t_wallet` VALUES (1701294, 984, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:52:14', '2019-07-25 19:37:49');
INSERT INTO `t_wallet` VALUES (1701299, 553, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:52:20', '2019-07-25 19:58:08');
INSERT INTO `t_wallet` VALUES (1701300, 553, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:52:20', '2019-07-25 19:58:08');
INSERT INTO `t_wallet` VALUES (1701303, 565, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:52:27', '2019-07-25 19:56:54');
INSERT INTO `t_wallet` VALUES (1701304, 565, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:52:27', '2019-07-25 19:56:55');
INSERT INTO `t_wallet` VALUES (1701305, 184, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:52:33', '2019-07-25 19:58:58');
INSERT INTO `t_wallet` VALUES (1701306, 184, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:52:34', '2019-07-25 19:58:58');
INSERT INTO `t_wallet` VALUES (1701307, 360, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:52:38', '2019-08-02 10:47:24');
INSERT INTO `t_wallet` VALUES (1701308, 360, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:52:38', '2019-08-02 10:47:24');
INSERT INTO `t_wallet` VALUES (1701315, 985, 2, 0.00000000, 0.00000000, 0.00000000, 82, '2019-07-25 17:52:43', '2019-07-25 19:59:10');
INSERT INTO `t_wallet` VALUES (1701316, 985, 3, 0.00000000, 0.00000000, 0.00000000, 82, '2019-07-25 17:52:43', '2019-07-25 19:59:10');
INSERT INTO `t_wallet` VALUES (1701317, 592, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:52:44', '2019-07-25 20:03:57');
INSERT INTO `t_wallet` VALUES (1701318, 592, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:52:44', '2019-07-25 20:03:57');
INSERT INTO `t_wallet` VALUES (1701321, 844, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:52:45', '2019-07-25 19:54:12');
INSERT INTO `t_wallet` VALUES (1701322, 844, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:52:45', '2019-07-25 19:54:12');
INSERT INTO `t_wallet` VALUES (1701323, 890, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:52:47', '2019-07-25 19:41:18');
INSERT INTO `t_wallet` VALUES (1701324, 890, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:52:47', '2019-07-25 19:41:18');
INSERT INTO `t_wallet` VALUES (1701327, 386, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:52:48', '2019-07-25 20:01:37');
INSERT INTO `t_wallet` VALUES (1701328, 386, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 17:52:48', '2019-07-25 20:01:37');
INSERT INTO `t_wallet` VALUES (1701335, 872, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:52:50', '2019-07-25 19:59:04');
INSERT INTO `t_wallet` VALUES (1701336, 872, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:52:50', '2019-07-25 19:59:04');
INSERT INTO `t_wallet` VALUES (1701343, 827, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:52:55', '2019-07-25 19:48:24');
INSERT INTO `t_wallet` VALUES (1701344, 827, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:52:55', '2019-07-25 19:48:24');
INSERT INTO `t_wallet` VALUES (1701347, 117, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:52:56', '2019-07-25 19:41:29');
INSERT INTO `t_wallet` VALUES (1701348, 117, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:52:56', '2019-07-25 19:41:29');
INSERT INTO `t_wallet` VALUES (1701351, 67, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:53:03', '2019-07-25 19:45:00');
INSERT INTO `t_wallet` VALUES (1701352, 67, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:53:03', '2019-07-25 19:45:01');
INSERT INTO `t_wallet` VALUES (1701355, 450, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:53:06', '2019-07-25 19:58:28');
INSERT INTO `t_wallet` VALUES (1701356, 450, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:53:06', '2019-07-25 19:58:28');
INSERT INTO `t_wallet` VALUES (1701359, 510, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:53:08', '2019-07-25 19:42:39');
INSERT INTO `t_wallet` VALUES (1701360, 510, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:53:08', '2019-07-25 19:42:39');
INSERT INTO `t_wallet` VALUES (1701365, 470, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:53:13', '2019-07-25 20:00:46');
INSERT INTO `t_wallet` VALUES (1701366, 470, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:53:13', '2019-07-25 20:00:46');
INSERT INTO `t_wallet` VALUES (1701367, 931, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:53:14', '2019-07-25 19:45:52');
INSERT INTO `t_wallet` VALUES (1701368, 931, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:53:14', '2019-07-25 19:45:52');
INSERT INTO `t_wallet` VALUES (1701371, 748, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:16', '2019-07-25 20:04:57');
INSERT INTO `t_wallet` VALUES (1701372, 748, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:16', '2019-07-25 20:04:57');
INSERT INTO `t_wallet` VALUES (1701381, 520, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:21', '2019-07-25 19:51:06');
INSERT INTO `t_wallet` VALUES (1701382, 520, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:21', '2019-07-25 19:51:06');
INSERT INTO `t_wallet` VALUES (1701385, 425, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:53:24', '2019-07-25 19:37:45');
INSERT INTO `t_wallet` VALUES (1701386, 425, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:53:24', '2019-07-25 19:37:45');
INSERT INTO `t_wallet` VALUES (1701401, 443, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:53:32', '2019-07-25 19:49:47');
INSERT INTO `t_wallet` VALUES (1701402, 443, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:53:32', '2019-07-25 19:49:47');
INSERT INTO `t_wallet` VALUES (1701405, 799, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:33', '2019-07-25 20:01:33');
INSERT INTO `t_wallet` VALUES (1701406, 799, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:33', '2019-07-25 20:01:33');
INSERT INTO `t_wallet` VALUES (1701407, 502, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:33', '2019-07-25 19:51:08');
INSERT INTO `t_wallet` VALUES (1701408, 502, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:33', '2019-07-25 19:51:08');
INSERT INTO `t_wallet` VALUES (1701409, 241, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:36', '2019-07-25 19:35:08');
INSERT INTO `t_wallet` VALUES (1701410, 241, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:36', '2019-07-25 19:35:08');
INSERT INTO `t_wallet` VALUES (1701411, 445, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:53:37', '2019-07-25 19:54:53');
INSERT INTO `t_wallet` VALUES (1701412, 445, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:53:37', '2019-07-25 19:54:53');
INSERT INTO `t_wallet` VALUES (1701417, 514, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:42', '2019-07-25 19:51:19');
INSERT INTO `t_wallet` VALUES (1701418, 514, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:53:42', '2019-07-25 19:51:19');
INSERT INTO `t_wallet` VALUES (1701421, 583, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:53:43', '2019-07-25 19:57:10');
INSERT INTO `t_wallet` VALUES (1701422, 583, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:53:43', '2019-07-25 19:57:10');
INSERT INTO `t_wallet` VALUES (1701423, 448, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:53:44', '2019-07-25 19:51:32');
INSERT INTO `t_wallet` VALUES (1701424, 448, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:53:44', '2019-07-25 19:51:32');
INSERT INTO `t_wallet` VALUES (1701427, 406, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:53:45', '2019-07-25 20:01:23');
INSERT INTO `t_wallet` VALUES (1701428, 406, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:53:45', '2019-07-25 20:01:23');
INSERT INTO `t_wallet` VALUES (1701431, 207, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:53:47', '2019-07-25 19:46:54');
INSERT INTO `t_wallet` VALUES (1701432, 207, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:53:47', '2019-07-25 19:46:54');
INSERT INTO `t_wallet` VALUES (1701463, 513, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:54:02', '2019-07-25 20:03:58');
INSERT INTO `t_wallet` VALUES (1701464, 513, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:54:02', '2019-07-25 20:03:58');
INSERT INTO `t_wallet` VALUES (1701465, 37, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:54:03', '2019-07-25 20:00:19');
INSERT INTO `t_wallet` VALUES (1701466, 37, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:54:03', '2019-07-25 20:00:19');
INSERT INTO `t_wallet` VALUES (1701477, 81, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:54:09', '2019-07-25 19:32:14');
INSERT INTO `t_wallet` VALUES (1701478, 81, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:54:09', '2019-07-25 19:32:14');
INSERT INTO `t_wallet` VALUES (1701479, 782, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:54:10', '2019-08-02 10:58:42');
INSERT INTO `t_wallet` VALUES (1701480, 782, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:54:10', '2019-08-02 10:58:42');
INSERT INTO `t_wallet` VALUES (1701481, 47, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:54:10', '2019-07-25 19:57:49');
INSERT INTO `t_wallet` VALUES (1701482, 47, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:54:10', '2019-07-25 19:57:49');
INSERT INTO `t_wallet` VALUES (1701491, 370, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:54:20', '2019-07-25 20:00:53');
INSERT INTO `t_wallet` VALUES (1701492, 370, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:54:20', '2019-07-25 20:00:53');
INSERT INTO `t_wallet` VALUES (1701503, 555, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:54:36', '2019-07-25 20:08:48');
INSERT INTO `t_wallet` VALUES (1701504, 555, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:54:36', '2019-07-25 20:08:48');
INSERT INTO `t_wallet` VALUES (1701505, 62, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:54:37', '2019-07-25 19:37:40');
INSERT INTO `t_wallet` VALUES (1701506, 62, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:54:37', '2019-07-25 19:37:40');
INSERT INTO `t_wallet` VALUES (1701519, 10, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:54:44', '2019-07-25 20:03:34');
INSERT INTO `t_wallet` VALUES (1701520, 10, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:54:44', '2019-07-25 20:03:34');
INSERT INTO `t_wallet` VALUES (1701523, 15, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:54:46', '2019-07-25 19:32:58');
INSERT INTO `t_wallet` VALUES (1701524, 15, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:54:46', '2019-07-25 19:32:58');
INSERT INTO `t_wallet` VALUES (1701531, 601, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:54:55', '2019-07-25 19:55:37');
INSERT INTO `t_wallet` VALUES (1701532, 601, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:54:55', '2019-07-25 19:55:37');
INSERT INTO `t_wallet` VALUES (1701539, 706, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:54:58', '2019-07-25 20:01:52');
INSERT INTO `t_wallet` VALUES (1701540, 706, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:54:58', '2019-07-25 20:01:52');
INSERT INTO `t_wallet` VALUES (1701545, 107, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:55:02', '2019-07-25 19:31:27');
INSERT INTO `t_wallet` VALUES (1701546, 107, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:55:02', '2019-07-25 19:31:27');
INSERT INTO `t_wallet` VALUES (1701549, 577, 2, 0.00000000, 0.00000000, 0.00000000, 58, '2019-07-25 17:55:09', '2019-08-02 10:45:35');
INSERT INTO `t_wallet` VALUES (1701550, 577, 3, 0.00000000, 0.00000000, 0.00000000, 58, '2019-07-25 17:55:09', '2019-08-02 10:45:35');
INSERT INTO `t_wallet` VALUES (1701555, 446, 2, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:55:14', '2019-07-25 20:01:56');
INSERT INTO `t_wallet` VALUES (1701556, 446, 3, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:55:14', '2019-07-25 20:01:56');
INSERT INTO `t_wallet` VALUES (1701557, 309, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:16', '2019-07-25 19:36:46');
INSERT INTO `t_wallet` VALUES (1701558, 309, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:16', '2019-07-25 19:36:46');
INSERT INTO `t_wallet` VALUES (1701561, 80, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:55:21', '2019-07-25 19:50:46');
INSERT INTO `t_wallet` VALUES (1701562, 80, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:55:21', '2019-07-25 19:50:46');
INSERT INTO `t_wallet` VALUES (1701567, 479, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:27', '2019-07-25 19:38:54');
INSERT INTO `t_wallet` VALUES (1701568, 479, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:27', '2019-07-25 19:38:54');
INSERT INTO `t_wallet` VALUES (1701579, 401, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:32', '2019-07-25 19:58:20');
INSERT INTO `t_wallet` VALUES (1701580, 401, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:32', '2019-07-25 19:58:20');
INSERT INTO `t_wallet` VALUES (1701585, 724, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:34', '2019-07-25 19:54:32');
INSERT INTO `t_wallet` VALUES (1701586, 724, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:34', '2019-07-25 19:54:32');
INSERT INTO `t_wallet` VALUES (1701591, 407, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:55:40', '2019-07-25 20:00:08');
INSERT INTO `t_wallet` VALUES (1701592, 407, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 17:55:40', '2019-07-25 20:00:08');
INSERT INTO `t_wallet` VALUES (1701595, 487, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:55:43', '2019-07-25 19:30:22');
INSERT INTO `t_wallet` VALUES (1701596, 487, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:55:43', '2019-07-25 19:30:22');
INSERT INTO `t_wallet` VALUES (1701597, 551, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:55:44', '2019-07-25 19:50:35');
INSERT INTO `t_wallet` VALUES (1701598, 551, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:55:44', '2019-07-25 19:50:35');
INSERT INTO `t_wallet` VALUES (1701609, 832, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:48', '2019-07-25 19:51:29');
INSERT INTO `t_wallet` VALUES (1701610, 832, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:55:48', '2019-07-25 19:51:29');
INSERT INTO `t_wallet` VALUES (1701623, 376, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:55:54', '2019-07-25 19:31:19');
INSERT INTO `t_wallet` VALUES (1701624, 376, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:55:54', '2019-07-25 19:31:19');
INSERT INTO `t_wallet` VALUES (1701625, 90, 2, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:55:56', '2019-07-25 19:43:53');
INSERT INTO `t_wallet` VALUES (1701626, 90, 3, 0.00000000, 0.00000000, 0.00000000, 23, '2019-07-25 17:55:56', '2019-07-25 19:43:54');
INSERT INTO `t_wallet` VALUES (1701633, 431, 2, 0.00000000, 0.00000000, 0.00000000, 46, '2019-07-25 17:56:01', '2019-07-25 19:47:39');
INSERT INTO `t_wallet` VALUES (1701634, 431, 3, 0.00000000, 0.00000000, 0.00000000, 46, '2019-07-25 17:56:01', '2019-07-25 19:47:39');
INSERT INTO `t_wallet` VALUES (1701639, 664, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:56:06', '2019-07-25 20:03:38');
INSERT INTO `t_wallet` VALUES (1701640, 664, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:56:06', '2019-07-25 20:03:38');
INSERT INTO `t_wallet` VALUES (1701655, 649, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:56:13', '2019-07-25 19:30:54');
INSERT INTO `t_wallet` VALUES (1701656, 649, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:56:13', '2019-07-25 19:30:54');
INSERT INTO `t_wallet` VALUES (1701657, 888, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:56:14', '2019-07-25 19:33:59');
INSERT INTO `t_wallet` VALUES (1701658, 888, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:56:14', '2019-07-25 19:33:59');
INSERT INTO `t_wallet` VALUES (1701671, 919, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:56:25', '2019-07-25 19:42:56');
INSERT INTO `t_wallet` VALUES (1701672, 919, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:56:25', '2019-07-25 19:42:56');
INSERT INTO `t_wallet` VALUES (1701673, 87, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:56:28', '2019-08-02 10:47:28');
INSERT INTO `t_wallet` VALUES (1701674, 87, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 17:56:28', '2019-08-02 10:47:28');
INSERT INTO `t_wallet` VALUES (1701681, 864, 2, 0.00000000, 0.00000000, 0.00000000, 29, '2019-07-25 17:56:34', '2019-07-25 19:31:21');
INSERT INTO `t_wallet` VALUES (1701682, 864, 3, 0.00000000, 0.00000000, 0.00000000, 29, '2019-07-25 17:56:34', '2019-07-25 19:31:21');
INSERT INTO `t_wallet` VALUES (1701683, 775, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:56:38', '2019-07-25 20:04:51');
INSERT INTO `t_wallet` VALUES (1701684, 775, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 17:56:38', '2019-07-25 20:04:51');
INSERT INTO `t_wallet` VALUES (1701693, 438, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:56:45', '2019-07-25 19:53:39');
INSERT INTO `t_wallet` VALUES (1701694, 438, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:56:45', '2019-07-25 19:53:39');
INSERT INTO `t_wallet` VALUES (1701707, 393, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:56:52', '2019-07-25 20:00:54');
INSERT INTO `t_wallet` VALUES (1701708, 393, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:56:52', '2019-07-25 20:00:54');
INSERT INTO `t_wallet` VALUES (1701709, 866, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:56:56', '2019-07-25 20:08:38');
INSERT INTO `t_wallet` VALUES (1701710, 866, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:56:56', '2019-07-25 20:08:38');
INSERT INTO `t_wallet` VALUES (1701711, 192, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:56:57', '2019-07-25 19:36:49');
INSERT INTO `t_wallet` VALUES (1701712, 192, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:56:57', '2019-07-25 19:36:50');
INSERT INTO `t_wallet` VALUES (1701715, 374, 2, 0.00000000, 0.00000000, 0.00000000, 52, '2019-07-25 17:57:03', '2019-07-25 19:47:50');
INSERT INTO `t_wallet` VALUES (1701716, 374, 3, 0.00000000, 0.00000000, 0.00000000, 52, '2019-07-25 17:57:03', '2019-07-25 19:47:50');
INSERT INTO `t_wallet` VALUES (1701723, 781, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:57:11', '2019-07-25 19:58:42');
INSERT INTO `t_wallet` VALUES (1701724, 781, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:57:11', '2019-07-25 19:58:42');
INSERT INTO `t_wallet` VALUES (1701737, 927, 2, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:57:17', '2019-07-25 19:49:51');
INSERT INTO `t_wallet` VALUES (1701738, 927, 3, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:57:17', '2019-07-25 19:49:51');
INSERT INTO `t_wallet` VALUES (1701747, 746, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:57:19', '2019-07-25 20:01:10');
INSERT INTO `t_wallet` VALUES (1701748, 746, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 17:57:19', '2019-07-25 20:01:10');
INSERT INTO `t_wallet` VALUES (1701753, 838, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:57:25', '2019-07-25 19:59:02');
INSERT INTO `t_wallet` VALUES (1701754, 838, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:57:25', '2019-07-25 19:59:02');
INSERT INTO `t_wallet` VALUES (1701757, 453, 2, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:57:28', '2019-07-25 19:33:39');
INSERT INTO `t_wallet` VALUES (1701758, 453, 3, 0.00000000, 0.00000000, 0.00000000, 24, '2019-07-25 17:57:28', '2019-07-25 19:33:39');
INSERT INTO `t_wallet` VALUES (1701763, 76, 2, 0.00000000, 0.00000000, 0.00000000, 66, '2019-07-25 17:57:40', '2019-07-25 19:21:12');
INSERT INTO `t_wallet` VALUES (1701764, 76, 3, 0.00000000, 0.00000000, 0.00000000, 66, '2019-07-25 17:57:40', '2019-07-25 19:21:12');
INSERT INTO `t_wallet` VALUES (1701767, 121, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:57:45', '2019-07-25 19:54:34');
INSERT INTO `t_wallet` VALUES (1701768, 121, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 17:57:45', '2019-07-25 19:54:34');
INSERT INTO `t_wallet` VALUES (1701775, 556, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:57:52', '2019-07-25 19:56:47');
INSERT INTO `t_wallet` VALUES (1701776, 556, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 17:57:52', '2019-07-25 19:56:47');
INSERT INTO `t_wallet` VALUES (1701777, 749, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:57:54', '2019-07-25 19:52:17');
INSERT INTO `t_wallet` VALUES (1701778, 749, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:57:54', '2019-07-25 19:52:17');
INSERT INTO `t_wallet` VALUES (1701779, 899, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:57:57', '2019-07-25 19:46:27');
INSERT INTO `t_wallet` VALUES (1701780, 899, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 17:57:57', '2019-07-25 19:46:27');
INSERT INTO `t_wallet` VALUES (1701783, 830, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:58:03', '2019-07-25 19:34:55');
INSERT INTO `t_wallet` VALUES (1701784, 830, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 17:58:03', '2019-07-25 19:34:55');
INSERT INTO `t_wallet` VALUES (1701801, 522, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:58:22', '2019-08-02 11:00:31');
INSERT INTO `t_wallet` VALUES (1701802, 522, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 17:58:22', '2019-08-02 11:00:31');
INSERT INTO `t_wallet` VALUES (1701803, 750, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:58:25', '2019-07-25 19:54:35');
INSERT INTO `t_wallet` VALUES (1701804, 750, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 17:58:25', '2019-07-25 19:54:34');
INSERT INTO `t_wallet` VALUES (1701805, 478, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:58:27', '2019-07-25 19:54:04');
INSERT INTO `t_wallet` VALUES (1701806, 478, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 17:58:27', '2019-07-25 19:54:04');
INSERT INTO `t_wallet` VALUES (1701849, 861, 2, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:58:59', '2019-07-25 19:56:17');
INSERT INTO `t_wallet` VALUES (1701850, 861, 3, 0.00000000, 0.00000000, 0.00000000, 30, '2019-07-25 17:58:59', '2019-07-25 19:56:17');
INSERT INTO `t_wallet` VALUES (1701865, 39, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:59:23', '2019-07-25 19:58:51');
INSERT INTO `t_wallet` VALUES (1701866, 39, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:59:23', '2019-07-25 19:58:51');
INSERT INTO `t_wallet` VALUES (1701901, 959, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:59:44', '2019-07-25 19:29:22');
INSERT INTO `t_wallet` VALUES (1701902, 959, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 17:59:44', '2019-07-25 19:29:22');
INSERT INTO `t_wallet` VALUES (1701903, 747, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:59:47', '2019-07-25 19:39:38');
INSERT INTO `t_wallet` VALUES (1701904, 747, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 17:59:47', '2019-07-25 19:39:38');
INSERT INTO `t_wallet` VALUES (1701915, 681, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:00:04', '2019-07-25 19:59:48');
INSERT INTO `t_wallet` VALUES (1701916, 681, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:00:04', '2019-07-25 19:59:48');
INSERT INTO `t_wallet` VALUES (1701923, 606, 2, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 18:00:14', '2019-08-02 10:52:02');
INSERT INTO `t_wallet` VALUES (1701924, 606, 3, 0.00000000, 0.00000000, 0.00000000, 12, '2019-07-25 18:00:14', '2019-08-02 10:52:02');
INSERT INTO `t_wallet` VALUES (1701925, 845, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 18:00:16', '2019-07-25 19:50:59');
INSERT INTO `t_wallet` VALUES (1701926, 845, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 18:00:16', '2019-07-25 19:50:59');
INSERT INTO `t_wallet` VALUES (1701929, 751, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 18:00:25', '2019-07-25 19:54:13');
INSERT INTO `t_wallet` VALUES (1701930, 751, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 18:00:25', '2019-07-25 19:54:13');
INSERT INTO `t_wallet` VALUES (1701931, 88, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:00:26', '2019-07-25 20:00:48');
INSERT INTO `t_wallet` VALUES (1701932, 88, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:00:26', '2019-07-25 20:00:49');
INSERT INTO `t_wallet` VALUES (1701933, 853, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 18:00:29', '2019-07-25 19:54:57');
INSERT INTO `t_wallet` VALUES (1701934, 853, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 18:00:29', '2019-07-25 19:54:57');
INSERT INTO `t_wallet` VALUES (1701935, 159, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:00:33', '2019-07-25 19:22:31');
INSERT INTO `t_wallet` VALUES (1701936, 159, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:00:33', '2019-07-25 19:22:31');
INSERT INTO `t_wallet` VALUES (1701937, 902, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 18:00:34', '2019-07-25 20:04:46');
INSERT INTO `t_wallet` VALUES (1701938, 902, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 18:00:34', '2019-07-25 20:04:46');
INSERT INTO `t_wallet` VALUES (1701941, 164, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 18:00:37', '2019-07-25 20:06:32');
INSERT INTO `t_wallet` VALUES (1701942, 164, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 18:00:37', '2019-07-25 20:06:32');
INSERT INTO `t_wallet` VALUES (1701943, 696, 2, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 18:00:39', '2019-07-25 19:44:50');
INSERT INTO `t_wallet` VALUES (1701944, 696, 3, 0.00000000, 0.00000000, 0.00000000, 17, '2019-07-25 18:00:39', '2019-07-25 19:44:50');
INSERT INTO `t_wallet` VALUES (1701945, 976, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:00:41', '2019-07-25 19:42:19');
INSERT INTO `t_wallet` VALUES (1701946, 976, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:00:41', '2019-07-25 19:42:19');
INSERT INTO `t_wallet` VALUES (1701947, 285, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:00:45', '2019-07-25 19:51:02');
INSERT INTO `t_wallet` VALUES (1701948, 285, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:00:45', '2019-07-25 19:51:02');
INSERT INTO `t_wallet` VALUES (1701949, 286, 2, 0.00000000, 0.00000000, 0.00000000, 90, '2019-07-25 18:00:46', '2019-07-25 20:03:40');
INSERT INTO `t_wallet` VALUES (1701950, 286, 3, 0.00000000, 0.00000000, 0.00000000, 90, '2019-07-25 18:00:46', '2019-07-25 20:03:40');
INSERT INTO `t_wallet` VALUES (1701951, 727, 2, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 18:00:56', '2019-07-25 19:59:12');
INSERT INTO `t_wallet` VALUES (1701952, 727, 3, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 18:00:56', '2019-07-25 19:59:12');
INSERT INTO `t_wallet` VALUES (1701955, 883, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 18:01:02', '2019-07-25 19:57:31');
INSERT INTO `t_wallet` VALUES (1701956, 883, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 18:01:02', '2019-07-25 19:57:32');
INSERT INTO `t_wallet` VALUES (1701963, 149, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:01:06', '2019-07-25 19:53:14');
INSERT INTO `t_wallet` VALUES (1701964, 149, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:01:06', '2019-07-25 19:53:14');
INSERT INTO `t_wallet` VALUES (1701965, 230, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 18:01:07', '2019-07-25 19:35:24');
INSERT INTO `t_wallet` VALUES (1701966, 230, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 18:01:07', '2019-07-25 19:35:24');
INSERT INTO `t_wallet` VALUES (1701967, 179, 2, 0.00000000, 0.00000000, 0.00000000, 1, '2019-07-25 18:01:11', '2019-07-25 18:01:11');
INSERT INTO `t_wallet` VALUES (1701968, 179, 3, 0.00000000, 0.00000000, 0.00000000, 1, '2019-07-25 18:01:11', '2019-07-25 18:01:11');
INSERT INTO `t_wallet` VALUES (1701969, 460, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 18:01:12', '2019-07-25 20:04:48');
INSERT INTO `t_wallet` VALUES (1701970, 460, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 18:01:12', '2019-07-25 20:04:48');
INSERT INTO `t_wallet` VALUES (1701971, 688, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:01:15', '2019-07-25 20:01:47');
INSERT INTO `t_wallet` VALUES (1701972, 688, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:01:15', '2019-07-25 20:01:48');
INSERT INTO `t_wallet` VALUES (1701979, 182, 2, 0.00000000, 0.00000000, 0.00000000, 2, '2019-07-25 18:01:19', '2019-07-25 19:32:04');
INSERT INTO `t_wallet` VALUES (1701980, 182, 3, 0.00000000, 0.00000000, 0.00000000, 2, '2019-07-25 18:01:19', '2019-07-25 19:32:04');
INSERT INTO `t_wallet` VALUES (1701983, 817, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 18:01:21', '2019-07-25 19:20:37');
INSERT INTO `t_wallet` VALUES (1701984, 817, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 18:01:22', '2019-07-25 19:20:38');
INSERT INTO `t_wallet` VALUES (1701991, 191, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:01:40', '2019-07-25 19:22:54');
INSERT INTO `t_wallet` VALUES (1701992, 191, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:01:40', '2019-07-25 19:22:54');
INSERT INTO `t_wallet` VALUES (1701993, 82, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:01:47', '2019-08-02 10:58:29');
INSERT INTO `t_wallet` VALUES (1701994, 82, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:01:47', '2019-08-02 10:58:29');
INSERT INTO `t_wallet` VALUES (1701995, 868, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:01:55', '2019-07-25 19:59:09');
INSERT INTO `t_wallet` VALUES (1701996, 868, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:01:55', '2019-07-25 19:59:09');
INSERT INTO `t_wallet` VALUES (1702005, 331, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:02:05', '2019-07-25 19:41:33');
INSERT INTO `t_wallet` VALUES (1702006, 331, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:02:05', '2019-07-25 19:41:33');
INSERT INTO `t_wallet` VALUES (1702007, 292, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 18:02:12', '2019-07-25 20:03:00');
INSERT INTO `t_wallet` VALUES (1702008, 292, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 18:02:12', '2019-07-25 20:03:01');
INSERT INTO `t_wallet` VALUES (1702009, 865, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:02:14', '2019-07-25 19:58:45');
INSERT INTO `t_wallet` VALUES (1702010, 865, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:02:14', '2019-07-25 19:58:45');
INSERT INTO `t_wallet` VALUES (1702013, 623, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:02:19', '2019-07-25 19:49:40');
INSERT INTO `t_wallet` VALUES (1702014, 623, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:02:19', '2019-07-25 19:49:40');
INSERT INTO `t_wallet` VALUES (1702015, 161, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 18:02:27', '2019-07-25 19:59:33');
INSERT INTO `t_wallet` VALUES (1702016, 161, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 18:02:27', '2019-07-25 19:59:33');
INSERT INTO `t_wallet` VALUES (1702017, 837, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:02:33', '2019-07-25 20:00:28');
INSERT INTO `t_wallet` VALUES (1702018, 837, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:02:33', '2019-07-25 20:00:28');
INSERT INTO `t_wallet` VALUES (1702019, 660, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:02:41', '2019-07-25 19:53:25');
INSERT INTO `t_wallet` VALUES (1702020, 660, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:02:41', '2019-07-25 19:53:26');
INSERT INTO `t_wallet` VALUES (1702023, 457, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:02:45', '2019-07-25 20:03:25');
INSERT INTO `t_wallet` VALUES (1702024, 457, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:02:45', '2019-07-25 20:03:25');
INSERT INTO `t_wallet` VALUES (1702025, 243, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:02:48', '2019-07-25 19:50:08');
INSERT INTO `t_wallet` VALUES (1702026, 243, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:02:48', '2019-07-25 19:50:08');
INSERT INTO `t_wallet` VALUES (1702027, 974, 2, 0.00000000, 0.00000000, 0.00000000, 27, '2019-07-25 18:02:54', '2019-07-25 19:40:41');
INSERT INTO `t_wallet` VALUES (1702028, 974, 3, 0.00000000, 0.00000000, 0.00000000, 27, '2019-07-25 18:02:54', '2019-07-25 19:40:41');
INSERT INTO `t_wallet` VALUES (1702029, 921, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:02:55', '2019-07-25 19:51:37');
INSERT INTO `t_wallet` VALUES (1702030, 921, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:02:55', '2019-07-25 19:51:37');
INSERT INTO `t_wallet` VALUES (1702033, 219, 2, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 18:03:04', '2019-07-25 20:00:16');
INSERT INTO `t_wallet` VALUES (1702034, 219, 3, 0.00000000, 0.00000000, 0.00000000, 13, '2019-07-25 18:03:04', '2019-07-25 20:00:16');
INSERT INTO `t_wallet` VALUES (1702035, 936, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:03:05', '2019-07-25 19:59:14');
INSERT INTO `t_wallet` VALUES (1702036, 936, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:03:05', '2019-07-25 19:59:14');
INSERT INTO `t_wallet` VALUES (1702037, 661, 2, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 18:03:05', '2019-07-25 20:01:23');
INSERT INTO `t_wallet` VALUES (1702038, 661, 3, 0.00000000, 0.00000000, 0.00000000, 20, '2019-07-25 18:03:05', '2019-07-25 20:01:23');
INSERT INTO `t_wallet` VALUES (1702041, 786, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 18:03:16', '2019-07-25 20:11:07');
INSERT INTO `t_wallet` VALUES (1702042, 786, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 18:03:16', '2019-07-25 20:11:07');
INSERT INTO `t_wallet` VALUES (1702045, 363, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 18:03:20', '2019-07-25 19:55:49');
INSERT INTO `t_wallet` VALUES (1702046, 363, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 18:03:20', '2019-07-25 19:55:49');
INSERT INTO `t_wallet` VALUES (1702047, 186, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 18:03:25', '2019-07-25 19:51:27');
INSERT INTO `t_wallet` VALUES (1702048, 186, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 18:03:25', '2019-07-25 19:51:28');
INSERT INTO `t_wallet` VALUES (1702053, 722, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:03:34', '2019-07-25 19:51:18');
INSERT INTO `t_wallet` VALUES (1702054, 722, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:03:34', '2019-07-25 19:51:19');
INSERT INTO `t_wallet` VALUES (1702055, 757, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:03:36', '2019-07-25 20:03:21');
INSERT INTO `t_wallet` VALUES (1702056, 757, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:03:36', '2019-07-25 20:03:21');
INSERT INTO `t_wallet` VALUES (1702059, 597, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:03:40', '2019-07-25 19:40:42');
INSERT INTO `t_wallet` VALUES (1702060, 597, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:03:40', '2019-07-25 19:40:43');
INSERT INTO `t_wallet` VALUES (1702063, 901, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:03:51', '2019-07-25 19:59:26');
INSERT INTO `t_wallet` VALUES (1702064, 901, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:03:51', '2019-07-25 19:59:26');
INSERT INTO `t_wallet` VALUES (1702067, 536, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:03:53', '2019-07-25 19:57:22');
INSERT INTO `t_wallet` VALUES (1702068, 536, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:03:53', '2019-07-25 19:57:22');
INSERT INTO `t_wallet` VALUES (1702071, 755, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:04:02', '2019-07-25 19:38:51');
INSERT INTO `t_wallet` VALUES (1702072, 755, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:04:02', '2019-07-25 19:38:51');
INSERT INTO `t_wallet` VALUES (1702073, 208, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:04:04', '2019-07-25 19:52:50');
INSERT INTO `t_wallet` VALUES (1702074, 208, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:04:04', '2019-07-25 19:52:50');
INSERT INTO `t_wallet` VALUES (1702075, 162, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:04:13', '2019-07-25 19:47:45');
INSERT INTO `t_wallet` VALUES (1702076, 162, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:04:13', '2019-07-25 19:47:45');
INSERT INTO `t_wallet` VALUES (1702077, 305, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:04:13', '2019-07-25 19:58:16');
INSERT INTO `t_wallet` VALUES (1702078, 305, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 18:04:13', '2019-07-25 19:58:16');
INSERT INTO `t_wallet` VALUES (1702079, 880, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:04:15', '2019-07-25 19:33:52');
INSERT INTO `t_wallet` VALUES (1702080, 880, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:04:15', '2019-07-25 19:33:52');
INSERT INTO `t_wallet` VALUES (1702081, 366, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:04:18', '2019-07-25 19:48:34');
INSERT INTO `t_wallet` VALUES (1702082, 366, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 18:04:18', '2019-07-25 19:48:34');
INSERT INTO `t_wallet` VALUES (1702085, 357, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:04:22', '2019-07-25 19:39:00');
INSERT INTO `t_wallet` VALUES (1702086, 357, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:04:22', '2019-07-25 19:39:00');
INSERT INTO `t_wallet` VALUES (1702147, 350, 2, 0.00000000, 0.00000000, 0.00000000, 28, '2019-07-25 18:06:07', '2019-07-25 19:59:15');
INSERT INTO `t_wallet` VALUES (1702148, 350, 3, 0.00000000, 0.00000000, 0.00000000, 28, '2019-07-25 18:06:07', '2019-07-25 19:59:15');
INSERT INTO `t_wallet` VALUES (1702223, 237, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:09:12', '2019-07-25 19:57:38');
INSERT INTO `t_wallet` VALUES (1702224, 237, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:09:12', '2019-07-25 19:57:38');
INSERT INTO `t_wallet` VALUES (1702261, 858, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 18:10:17', '2019-07-25 20:00:24');
INSERT INTO `t_wallet` VALUES (1702262, 858, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 18:10:17', '2019-07-25 20:00:24');
INSERT INTO `t_wallet` VALUES (1702301, 334, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:11:05', '2019-07-25 19:34:20');
INSERT INTO `t_wallet` VALUES (1702302, 334, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 18:11:05', '2019-07-25 19:34:20');
INSERT INTO `t_wallet` VALUES (1702305, 728, 2, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 18:11:16', '2019-07-25 20:04:17');
INSERT INTO `t_wallet` VALUES (1702306, 728, 3, 0.00000000, 0.00000000, 0.00000000, 33, '2019-07-25 18:11:16', '2019-07-25 20:04:17');
INSERT INTO `t_wallet` VALUES (1702307, 990, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 18:11:20', '2019-07-25 19:54:09');
INSERT INTO `t_wallet` VALUES (1702308, 990, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 18:11:20', '2019-07-25 19:54:09');
INSERT INTO `t_wallet` VALUES (1702323, 894, 2, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 18:11:37', '2019-07-25 19:57:14');
INSERT INTO `t_wallet` VALUES (1702324, 894, 3, 0.00000000, 0.00000000, 0.00000000, 18, '2019-07-25 18:11:37', '2019-07-25 19:57:14');
INSERT INTO `t_wallet` VALUES (1702339, 472, 3, 0.00000000, 0.00000000, 0.00000000, 26, '2019-07-25 18:12:04', '2019-07-25 20:04:45');
INSERT INTO `t_wallet` VALUES (1702340, 472, 2, 0.00000000, 0.00000000, 0.00000000, 26, '2019-07-25 18:12:04', '2019-07-25 20:04:45');
INSERT INTO `t_wallet` VALUES (1702341, 829, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:12:10', '2019-07-25 19:58:06');
INSERT INTO `t_wallet` VALUES (1702342, 829, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 18:12:10', '2019-07-25 19:58:06');
INSERT INTO `t_wallet` VALUES (1704567, 145, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:03:04', '2019-07-25 20:01:55');
INSERT INTO `t_wallet` VALUES (1704568, 145, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:03:04', '2019-07-25 20:01:55');
INSERT INTO `t_wallet` VALUES (1704579, 343, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 19:03:10', '2019-07-25 19:59:09');
INSERT INTO `t_wallet` VALUES (1704580, 343, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 19:03:10', '2019-07-25 19:59:09');
INSERT INTO `t_wallet` VALUES (1704623, 650, 2, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 19:04:10', '2019-07-25 19:59:05');
INSERT INTO `t_wallet` VALUES (1704624, 650, 3, 0.00000000, 0.00000000, 0.00000000, 14, '2019-07-25 19:04:10', '2019-07-25 19:59:05');
INSERT INTO `t_wallet` VALUES (1704635, 61, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:04:35', '2019-07-25 20:05:58');
INSERT INTO `t_wallet` VALUES (1704636, 61, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:04:35', '2019-07-25 20:05:58');
INSERT INTO `t_wallet` VALUES (1704637, 236, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 19:04:51', '2019-07-25 19:37:45');
INSERT INTO `t_wallet` VALUES (1704638, 236, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 19:04:51', '2019-07-25 19:37:45');
INSERT INTO `t_wallet` VALUES (1704639, 770, 2, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 19:05:17', '2019-07-25 19:40:36');
INSERT INTO `t_wallet` VALUES (1704640, 770, 3, 0.00000000, 0.00000000, 0.00000000, 19, '2019-07-25 19:05:17', '2019-07-25 19:40:36');
INSERT INTO `t_wallet` VALUES (1704641, 720, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 19:05:21', '2019-07-25 19:58:47');
INSERT INTO `t_wallet` VALUES (1704642, 720, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 19:05:21', '2019-07-25 19:58:47');
INSERT INTO `t_wallet` VALUES (1704643, 687, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 19:05:27', '2019-07-25 19:53:49');
INSERT INTO `t_wallet` VALUES (1704644, 687, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 19:05:27', '2019-07-25 19:53:49');
INSERT INTO `t_wallet` VALUES (1704645, 651, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 19:05:35', '2019-07-25 19:55:21');
INSERT INTO `t_wallet` VALUES (1704646, 651, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 19:05:35', '2019-07-25 19:55:21');
INSERT INTO `t_wallet` VALUES (1704661, 643, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 19:06:05', '2019-07-25 19:40:30');
INSERT INTO `t_wallet` VALUES (1704662, 643, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 19:06:05', '2019-07-25 19:40:30');
INSERT INTO `t_wallet` VALUES (1704669, 945, 2, 0.00000000, 0.00000000, 0.00000000, 2, '2019-07-25 19:06:28', '2019-07-25 19:18:11');
INSERT INTO `t_wallet` VALUES (1704670, 945, 3, 0.00000000, 0.00000000, 0.00000000, 2, '2019-07-25 19:06:28', '2019-07-25 19:18:11');
INSERT INTO `t_wallet` VALUES (1704671, 743, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 19:06:39', '2019-07-31 15:41:08');
INSERT INTO `t_wallet` VALUES (1704672, 743, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 19:06:40', '2019-07-31 15:41:08');
INSERT INTO `t_wallet` VALUES (1704701, 954, 3, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 19:07:01', '2019-07-25 19:45:44');
INSERT INTO `t_wallet` VALUES (1704702, 954, 2, 0.00000000, 0.00000000, 0.00000000, 7, '2019-07-25 19:07:01', '2019-07-25 19:45:44');
INSERT INTO `t_wallet` VALUES (1704707, 690, 2, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 19:07:59', '2019-07-25 20:03:55');
INSERT INTO `t_wallet` VALUES (1704708, 690, 3, 0.00000000, 0.00000000, 0.00000000, 8, '2019-07-25 19:07:59', '2019-07-25 20:03:55');
INSERT INTO `t_wallet` VALUES (1704733, 266, 2, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 19:08:48', '2019-07-25 19:56:02');
INSERT INTO `t_wallet` VALUES (1704734, 266, 3, 0.00000000, 0.00000000, 0.00000000, 9, '2019-07-25 19:08:48', '2019-07-25 19:56:02');
INSERT INTO `t_wallet` VALUES (1704735, 941, 3, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 19:08:58', '2019-07-25 19:49:16');
INSERT INTO `t_wallet` VALUES (1704736, 941, 2, 0.00000000, 0.00000000, 0.00000000, 15, '2019-07-25 19:08:58', '2019-07-25 19:49:16');
INSERT INTO `t_wallet` VALUES (1704745, 772, 2, 0.00000000, 0.00000000, 0.00000000, 35, '2019-07-25 19:09:22', '2019-07-25 19:54:16');
INSERT INTO `t_wallet` VALUES (1704746, 772, 3, 0.00000000, 0.00000000, 0.00000000, 35, '2019-07-25 19:09:22', '2019-07-25 19:54:16');
INSERT INTO `t_wallet` VALUES (1704751, 359, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 19:09:32', '2019-07-25 19:47:34');
INSERT INTO `t_wallet` VALUES (1704752, 359, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 19:09:32', '2019-07-25 19:47:34');
INSERT INTO `t_wallet` VALUES (1704755, 239, 3, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 19:09:43', '2019-07-25 19:40:57');
INSERT INTO `t_wallet` VALUES (1704756, 239, 2, 0.00000000, 0.00000000, 0.00000000, 10, '2019-07-25 19:09:43', '2019-07-25 19:40:56');
INSERT INTO `t_wallet` VALUES (1704761, 142, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 19:10:05', '2019-07-25 19:59:44');
INSERT INTO `t_wallet` VALUES (1704762, 142, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 19:10:05', '2019-07-25 19:59:44');
INSERT INTO `t_wallet` VALUES (1704773, 421, 3, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 19:10:52', '2019-07-25 19:25:10');
INSERT INTO `t_wallet` VALUES (1704774, 421, 2, 0.00000000, 0.00000000, 0.00000000, 16, '2019-07-25 19:10:52', '2019-07-25 19:25:10');
INSERT INTO `t_wallet` VALUES (1704775, 740, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:10:55', '2019-07-25 19:27:06');
INSERT INTO `t_wallet` VALUES (1704776, 740, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:10:55', '2019-07-25 19:27:06');
INSERT INTO `t_wallet` VALUES (1704781, 995, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:12:19', '2019-07-25 20:04:58');
INSERT INTO `t_wallet` VALUES (1704782, 995, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:12:19', '2019-07-25 20:04:58');
INSERT INTO `t_wallet` VALUES (1704801, 576, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 19:14:21', '2019-07-25 19:39:43');
INSERT INTO `t_wallet` VALUES (1704802, 576, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 19:14:21', '2019-07-25 19:39:43');
INSERT INTO `t_wallet` VALUES (1704813, 871, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 19:14:43', '2019-07-25 19:48:36');
INSERT INTO `t_wallet` VALUES (1704814, 871, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 19:14:43', '2019-07-25 19:48:36');
INSERT INTO `t_wallet` VALUES (1704827, 485, 2, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 19:15:49', '2019-07-25 19:51:29');
INSERT INTO `t_wallet` VALUES (1704828, 485, 3, 0.00000000, 0.00000000, 0.00000000, 5, '2019-07-25 19:15:49', '2019-07-25 19:51:29');
INSERT INTO `t_wallet` VALUES (1704829, 418, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 19:16:14', '2019-07-25 19:37:53');
INSERT INTO `t_wallet` VALUES (1704830, 418, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 19:16:14', '2019-07-25 19:37:53');
INSERT INTO `t_wallet` VALUES (1704831, 436, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 19:16:51', '2019-07-25 19:38:04');
INSERT INTO `t_wallet` VALUES (1704832, 436, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 19:16:51', '2019-07-25 19:38:05');
INSERT INTO `t_wallet` VALUES (1704833, 233, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:18:03', '2019-07-25 19:59:34');
INSERT INTO `t_wallet` VALUES (1704834, 233, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:18:03', '2019-07-25 19:59:34');
INSERT INTO `t_wallet` VALUES (1704835, 569, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:18:15', '2019-07-25 19:32:10');
INSERT INTO `t_wallet` VALUES (1704836, 569, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:18:15', '2019-07-25 19:32:10');
INSERT INTO `t_wallet` VALUES (1704837, 471, 2, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 19:18:34', '2019-07-25 19:55:39');
INSERT INTO `t_wallet` VALUES (1704838, 471, 3, 0.00000000, 0.00000000, 0.00000000, 11, '2019-07-25 19:18:34', '2019-07-25 19:55:39');
INSERT INTO `t_wallet` VALUES (1704839, 290, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 19:20:48', '2019-07-25 19:43:08');
INSERT INTO `t_wallet` VALUES (1704840, 290, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-07-25 19:20:48', '2019-07-25 19:43:08');
INSERT INTO `t_wallet` VALUES (1704841, 958, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:21:05', '2019-07-25 19:42:00');
INSERT INTO `t_wallet` VALUES (1704842, 958, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:21:05', '2019-07-25 19:42:00');
INSERT INTO `t_wallet` VALUES (1704843, 694, 2, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:21:18', '2019-07-25 20:03:39');
INSERT INTO `t_wallet` VALUES (1704844, 694, 3, 0.00000000, 0.00000000, 0.00000000, 6, '2019-07-25 19:21:18', '2019-07-25 20:03:39');
INSERT INTO `t_wallet` VALUES (1704845, 257, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 19:23:52', '2019-07-25 20:01:18');
INSERT INTO `t_wallet` VALUES (1704846, 257, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 19:23:52', '2019-07-25 20:01:18');
INSERT INTO `t_wallet` VALUES (1704851, 526, 2, 0.00000000, 0.00000000, 0.00000000, 1, '2019-07-25 19:25:48', '2019-07-25 19:25:48');
INSERT INTO `t_wallet` VALUES (1704852, 526, 3, 0.00000000, 0.00000000, 0.00000000, 1, '2019-07-25 19:25:48', '2019-07-25 19:25:48');
INSERT INTO `t_wallet` VALUES (1704857, 879, 2, 0.00000000, 0.00000000, 0.00000000, 1, '2019-07-25 19:29:44', '2019-07-25 19:29:44');
INSERT INTO `t_wallet` VALUES (1704858, 879, 3, 0.00000000, 0.00000000, 0.00000000, 1, '2019-07-25 19:29:44', '2019-07-25 19:29:44');
INSERT INTO `t_wallet` VALUES (1704859, 763, 2, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 19:38:58', '2019-07-25 19:56:50');
INSERT INTO `t_wallet` VALUES (1704860, 763, 3, 0.00000000, 0.00000000, 0.00000000, 4, '2019-07-25 19:38:58', '2019-07-25 19:56:50');
INSERT INTO `t_wallet` VALUES (1704873, 106, 3, 0.00000000, 0.00000000, 0.00000000, 1, '2019-07-25 19:57:58', '2019-07-25 19:57:58');
INSERT INTO `t_wallet` VALUES (1704874, 106, 2, 0.00000000, 0.00000000, 0.00000000, 1, '2019-07-25 19:57:58', '2019-07-25 19:57:58');
INSERT INTO `t_wallet` VALUES (6169345, 111, 2, 0.00000000, 0.00000000, 0.00000000, 3, '2019-08-02 10:25:49', '2019-08-02 14:58:27');
INSERT INTO `t_wallet` VALUES (6169618, 222, 2, 1.00000000, 0.00000000, 0.00000000, 3, '2019-08-02 10:25:49', '2019-08-02 14:58:30');
INSERT INTO `t_wallet` VALUES (6170721, 111, 3, 220.00000000, 0.00000000, 0.00000000, 3, '2019-08-02 10:25:49', '2019-08-02 14:58:27');
INSERT INTO `t_wallet` VALUES (6170723, 222, 3, 0.00000000, 0.00000000, 0.00000000, 3, '2019-08-02 10:25:49', '2019-08-02 14:58:29');
INSERT INTO `t_wallet` VALUES (6170724, 9820903632, 1, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:31:24', '2019-08-14 15:31:24');
INSERT INTO `t_wallet` VALUES (6170725, 9820903632, 2, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:31:24', '2019-08-14 15:31:24');
INSERT INTO `t_wallet` VALUES (6170726, 9820903632, 3, 99.00000000, 0.00000000, 1.00000000, 3, '2019-08-14 15:31:25', '2019-08-14 15:31:25');
INSERT INTO `t_wallet` VALUES (6170727, 9820903632, 4, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:31:25', '2019-08-14 15:31:25');
INSERT INTO `t_wallet` VALUES (6170728, 9820903632, 5, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:31:25', '2019-08-14 15:31:25');
INSERT INTO `t_wallet` VALUES (6170729, 9820903632, 6, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:31:25', '2019-08-14 15:31:25');
INSERT INTO `t_wallet` VALUES (6170730, 9820903632, 7, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:31:25', '2019-08-14 15:31:25');
INSERT INTO `t_wallet` VALUES (6170731, 9820903632, 8, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:31:25', '2019-08-14 15:31:25');
INSERT INTO `t_wallet` VALUES (6170732, 9820903632, 9, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:31:25', '2019-08-14 15:31:25');
INSERT INTO `t_wallet` VALUES (6170733, 9820903632, 10, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:31:25', '2019-08-14 15:31:25');
INSERT INTO `t_wallet` VALUES (6170738, 9820903632, 13, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-14 15:41:02', '2019-08-14 15:41:02');
INSERT INTO `t_wallet` VALUES (6170807, 1, 1, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:36', '2019-08-22 10:28:36');
INSERT INTO `t_wallet` VALUES (6170808, 1, 2, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:36', '2019-08-22 10:28:36');
INSERT INTO `t_wallet` VALUES (6170809, 1, 3, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:36', '2019-08-22 10:28:36');
INSERT INTO `t_wallet` VALUES (6170810, 1, 4, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:36', '2019-08-22 10:28:36');
INSERT INTO `t_wallet` VALUES (6170811, 1, 5, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:36', '2019-08-22 10:28:36');
INSERT INTO `t_wallet` VALUES (6170812, 1, 6, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:36', '2019-08-22 10:28:36');
INSERT INTO `t_wallet` VALUES (6170813, 1, 7, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:37', '2019-08-22 10:28:37');
INSERT INTO `t_wallet` VALUES (6170814, 1, 8, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:37', '2019-08-22 10:28:37');
INSERT INTO `t_wallet` VALUES (6170815, 1, 9, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:37', '2019-08-22 10:28:37');
INSERT INTO `t_wallet` VALUES (6170816, 1, 10, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:28:37', '2019-08-22 10:28:37');
INSERT INTO `t_wallet` VALUES (6170817, 2, 1, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');
INSERT INTO `t_wallet` VALUES (6170818, 2, 2, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');
INSERT INTO `t_wallet` VALUES (6170819, 2, 3, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');
INSERT INTO `t_wallet` VALUES (6170820, 2, 4, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');
INSERT INTO `t_wallet` VALUES (6170821, 2, 5, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');
INSERT INTO `t_wallet` VALUES (6170822, 2, 6, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');
INSERT INTO `t_wallet` VALUES (6170823, 2, 7, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');
INSERT INTO `t_wallet` VALUES (6170824, 2, 8, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');
INSERT INTO `t_wallet` VALUES (6170825, 2, 9, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');
INSERT INTO `t_wallet` VALUES (6170826, 2, 10, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 10:48:38', '2019-08-22 10:48:38');

-- ----------------------------
-- Table structure for t_wallet_record
-- ----------------------------
DROP TABLE IF EXISTS `t_wallet_record`;
CREATE TABLE `t_wallet_record`  (
  `id` bigint(64) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) NULL DEFAULT NULL COMMENT '货币id(t_currency.id)',
  `before_amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '成交前金额',
  `after_amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '成交后金额',
  `change_amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '变动金额',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '成交类型,1-划转：币币钱包-->法币钱包 2-划转：法币钱包-->币币钱包 3-买入 4-卖出 5-划转：币币钱包-->资金钱包 6-划转：资金钱包-->币币钱包',
  `entrust_record_id` bigint(64) NULL DEFAULT NULL COMMENT '关联的id,当前主要指成交id,t_entrust_record.id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE,
  INDEX `index_currency_id`(`currency_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '虚拟币钱包表日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_wallet_record
-- ----------------------------
INSERT INTO `t_wallet_record` VALUES (1, 9820903632, 3, 10.00000000, 9.00000000, -1.00000000, 1, NULL, '2019-08-16 18:38:20');
INSERT INTO `t_wallet_record` VALUES (2, 9820903632, 3, 9.00000000, 7.00000000, -2.00000000, 5, NULL, '2019-08-16 18:39:05');
INSERT INTO `t_wallet_record` VALUES (361228616358699008, 1, 3, 1.00000000, 0.00000000, -1.00000000, 1, NULL, '2019-08-20 16:34:39');
INSERT INTO `t_wallet_record` VALUES (361230242154815488, 1, 3, 0.00000000, 1.00000000, 1.00000000, 2, NULL, '2019-08-20 16:41:07');
INSERT INTO `t_wallet_record` VALUES (361603526566354944, 1, 3, 1.00000000, 0.00000000, -1.00000000, 5, NULL, '2019-08-21 17:24:25');

-- ----------------------------
-- Table structure for t_wallet_transfer
-- ----------------------------
DROP TABLE IF EXISTS `t_wallet_transfer`;
CREATE TABLE `t_wallet_transfer`  (
  `id` bigint(64) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `currency` int(11) NULL DEFAULT NULL COMMENT '货币id(t_currency的id)',
  `count` decimal(30, 10) NULL DEFAULT NULL COMMENT '数量',
  `business_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务流水号',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态，1.待处理，2.成功，3.失败，4.状态未明',
  `type_channel` tinyint(1) NULL DEFAULT NULL COMMENT '操作渠道：1.C2C钱包划转到币币钱包，2.币币钱包划转到C2C钱包 3.资金钱包划转币币钱包 4.币币钱包划转资金钱包',
  `source_channel` tinyint(1) NULL DEFAULT NULL COMMENT '来源渠道：1.app，2.后台，3.PC',
  `des` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE,
  INDEX `index_currency`(`currency`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '钱包划转记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wallet_transfer
-- ----------------------------
INSERT INTO `t_wallet_transfer` VALUES (1, 1, 1, 1.0000000000, '1', 1, 1, 1, '1', '2019-08-08 17:51:23', '2019-08-08 17:51:23');
INSERT INTO `t_wallet_transfer` VALUES (359428431513133056, 9820903632, 3, 1.0000000000, '359428431513133057', 3, 2, 1, '', '2019-08-15 17:21:22', '2019-08-15 17:21:22');
INSERT INTO `t_wallet_transfer` VALUES (359428662757695488, 9820903632, 3, 1.0000000000, '359428662757695489', 4, 2, 1, '', '2019-08-15 17:22:17', '2019-08-15 17:22:22');

-- ----------------------------
-- Table structure for w_coin
-- ----------------------------
DROP TABLE IF EXISTS `w_coin`;
CREATE TABLE `w_coin`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `coin_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN',
  `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '币种  BTC、USDT、EOS、ETH等',
  `contract` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '合约地址USDT=31',
  `is_recharge` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '是否允许充值 D：禁止 E：允许',
  `is_withdraw` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '是否允许提现 D：禁止 E：允许',
  `status` tinyint(255) NOT NULL DEFAULT 1 COMMENT '状态:0禁用,1正常',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '币种类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_coin
-- ----------------------------
INSERT INTO `w_coin` VALUES (1, 'BTC', 'BTC', '', 'E', 'E', 1, '', '2019-08-23 12:43:26.000', '2019-08-28 16:40:46.692');
INSERT INTO `w_coin` VALUES (2, 'ETH', 'ETH', '', 'D', 'D', 1, '', '2019-08-23 12:44:15.000', '2019-08-28 16:40:56.347');
INSERT INTO `w_coin` VALUES (3, 'BTC_TOKEN', 'USDT', '31', 'E', 'E', 1, '', '2019-08-23 17:24:36.000', '2019-08-29 13:33:03.835');

-- ----------------------------
-- Table structure for w_coin_config
-- ----------------------------
DROP TABLE IF EXISTS `w_coin_config`;
CREATE TABLE `w_coin_config`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `coin_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN',
  `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '币种  BTC、USDT、EOS、ETH等',
  `contract` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '合约地址USDT=31',
  `scan_block` bigint(20) NULL DEFAULT 0 COMMENT '扫描区块的高度（主要查充值数据）',
  `base_multiple` int(11) NULL DEFAULT NULL COMMENT '基础倍数8【基础单位换成聪】ETH是18，有些ETHtoken不是18.需要注意',
  `min_confirm` int(6) NULL DEFAULT NULL COMMENT '最低确认数',
  `boss_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'boss钱包',
  `min_collection_amount` decimal(18, 8) NULL DEFAULT 0.00000000 COMMENT '最低归集数',
  `valid` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '禁用D  启用E',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_coin_config
-- ----------------------------
INSERT INTO `w_coin_config` VALUES (1, 'BTC', 'BTC', '', 591119, 8, 6, '1A968Gxk1G2t5TawiSRd3XACdoqcHtEqZP', 0.00000000, 'E', '1', '2019-08-28 18:52:54.000', '2019-08-28 18:52:54.000');
INSERT INTO `w_coin_config` VALUES (2, 'BTC_TOKEN', 'USDT', '31', 591085, 8, 6, '1A968Gxk1G2t5TawiSRd3XACdoqcHtEqZP', 0.00000000, 'E', '2', '2019-08-28 18:52:54.000', '2019-08-28 18:52:54.000');
INSERT INTO `w_coin_config` VALUES (19, NULL, 'BTC', '', NULL, NULL, NULL, '1', NULL, 'E', NULL, '2019-08-29 17:56:58.041', '2019-08-29 17:56:58.041');
INSERT INTO `w_coin_config` VALUES (20, NULL, 'BTC', '', NULL, NULL, NULL, '1', NULL, 'E', '1', '2019-08-29 17:58:23.909', '2019-08-29 17:58:23.909');
INSERT INTO `w_coin_config` VALUES (21, NULL, 'BTC', '', NULL, NULL, NULL, '1', NULL, 'E', '123123123', '2019-08-29 18:00:44.380', '2019-08-29 18:00:44.380');

-- ----------------------------
-- Table structure for w_config_wallet
-- ----------------------------
DROP TABLE IF EXISTS `w_config_wallet`;
CREATE TABLE `w_config_wallet`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `coin_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种大类',
  `wallet_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `keystore` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'keystore',
  `valid` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'E' COMMENT '是否有效：D无效E有效',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置钱包' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_config_wallet
-- ----------------------------
INSERT INTO `w_config_wallet` VALUES (1, 'BTC', 'BOSS', '1A968Gxk1G2t5TawiSRd3XACdoqcHtEqZP', 'a/WDLFoK8zigZUcmH/A4EAFVyR2KqImHI5Ux8ey3OGDL8gDOGyyorIE0Gexk+QfqmC8D73CUKUr+Ke1eCp5MyA==', 'D', 'BTCBOSS', '2019-08-20 15:58:52.000', '2019-08-20 15:58:56.000');
INSERT INTO `w_config_wallet` VALUES (9, 'BTC_TOKEN', 'withdraw', '1P4AR1FWhVtDznuDs5Pa4oMCrSxkefBQGD', 'bgMg98qOQ/I4A0KA0lvlQK+6GYLXcpeJV6s/G+iqSc1mx1ny4nLWEDRoRMphnHlQJgWoRB8yxnstSCowwPIPTw==', 'E', '', '2019-08-21 16:05:16.591', '2019-08-21 16:05:16.591');
INSERT INTO `w_config_wallet` VALUES (10, 'BTC_TOKEN', 'fee', '1HCJHezn7QicWMjaNTGzehAZshq3XwXCrN', 'dQ1FLz6CUxUdRviRz/0VbdZUtVXg4ej3Q9Ab4iUkrNKMMuGS/RFK2NWmu3yf9uVniGKfBRLpqo/4T7bd0LdxzQ==', 'E', '', '2019-08-21 16:05:16.744', '2019-08-21 16:05:16.744');
INSERT INTO `w_config_wallet` VALUES (11, 'BTC', 'withdraw', '19UcA5tzBVm6Xt6wC2QhpVkRCSvvdHpWWG', '2p2B+XJGGRjuzZyJI9f88jxG7UHS2CCBUWfnVPrp4POk0l1mhVFdtFqCjKwHmk25a57ZSLBPGjWjrxaFTCeMSQ==', 'E', '', '2019-08-21 16:10:48.258', '2019-08-21 16:10:48.258');
INSERT INTO `w_config_wallet` VALUES (12, 'BTC', 'fee', '1AEwtMxmvKjWBBe3J17jo3R1uiV3honWD1', '4oImDUvDaELeW+uD9wWJXV3FO/4WbTUPdBba4DjGqhijtBKhR2C8Cyn55Nr1QFp5pv7JNVj4ZpSAOIdZtypi0A==', 'E', '', '2019-08-21 16:10:48.404', '2019-08-21 16:10:48.404');

-- ----------------------------
-- Table structure for w_order
-- ----------------------------
DROP TABLE IF EXISTS `w_order`;
CREATE TABLE `w_order`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `order_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单号',
  `order_type` int(11) NOT NULL DEFAULT 1 COMMENT '订单类型：1充币，-1提币',
  `fee` decimal(20, 8) NULL DEFAULT NULL COMMENT '手续费',
  `coin_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN',
  `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '币种  BTC、USDT、EOS、ETH等',
  `contract` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '合约地址USDT=31',
  `amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '数量',
  `receiver_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接收地址',
  `type` int(8) NULL DEFAULT NULL COMMENT '订单状态：1未审核，2客服已审核，3财务已审核，4财务拒绝，5成功，6等待确认，-1客服已拒绝，-2用户取消',
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 336 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_order
-- ----------------------------
INSERT INTO `w_order` VALUES (322, 10086, '362287302136958976', -1, 0.00050000, 'BTC', 'BTC', '', -0.10000000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 5, NULL, '2019-08-23 14:41:19', '2019-08-28 15:27:01');
INSERT INTO `w_order` VALUES (323, 10086, '362289402011062272', -1, 0.00050000, 'BTC', 'BTC', '', -0.10000000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 5, NULL, '2019-08-23 14:49:39', '2019-08-28 15:28:50');
INSERT INTO `w_order` VALUES (324, 10086, '362322582646689792', -1, 0.00050000, 'BTC', 'BTC', '', -0.10000000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 5, NULL, '2019-08-23 17:01:29', '2019-08-28 15:29:58');
INSERT INTO `w_order` VALUES (325, 10086, '364104990106718208', -1, 0.00050000, 'BTC', 'BTC', '', -0.15000000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 5, NULL, '2019-08-28 15:04:07', '2019-08-28 15:40:30');
INSERT INTO `w_order` VALUES (326, 10086, '364110603914186752', -1, 0.00050000, 'BTC', 'BTC', '', -0.15000000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-28 15:26:25', '2019-08-28 15:26:25');
INSERT INTO `w_order` VALUES (327, 10086, '364469218403553280', -1, 0.00050000, 'BTC', 'BTC', '', -0.15000000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-29 15:11:26', '2019-08-29 15:11:26');
INSERT INTO `w_order` VALUES (328, 10086, '364481250578272256', -1, 0.00050000, 'BTC', 'BTC', '', -0.00500000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-29 15:59:15', '2019-08-29 15:59:15');
INSERT INTO `w_order` VALUES (329, 10086, '364481281679036416', -1, 0.00050000, 'BTC', 'BTC', '', -0.00500000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-29 15:59:22', '2019-08-29 15:59:22');
INSERT INTO `w_order` VALUES (330, 10086, '364481313438306304', -1, 0.00050000, 'BTC', 'BTC', '', -0.00500000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-29 15:59:30', '2019-08-29 15:59:30');
INSERT INTO `w_order` VALUES (331, 10086, '364481337240981504', -1, 0.00050000, 'BTC', 'BTC', '', -0.00500000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-29 15:59:36', '2019-08-29 15:59:36');
INSERT INTO `w_order` VALUES (332, 10086, '364481356404756480', -1, 0.00050000, 'BTC', 'BTC', '', -0.00500000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-29 15:59:40', '2019-08-29 15:59:40');
INSERT INTO `w_order` VALUES (333, 10086, '364481595534610432', -1, 0.00050000, 'BTC', 'BTC', '', -0.00500000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-29 16:00:37', '2019-08-29 16:00:37');
INSERT INTO `w_order` VALUES (334, 10086, '364512878293815296', -1, 5.00000000, 'BTC_TOKEN', 'USDT', '', -11.00000000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-29 18:04:55', '2019-08-29 18:04:55');
INSERT INTO `w_order` VALUES (335, 10086, '364513272260595712', -1, 4.00000000, 'BTC_TOKEN', 'USDT', '', -12.00000000, '1234578edewsfWE', 1, NULL, '2019-08-29 18:06:29', '2019-08-29 18:06:29');

-- ----------------------------
-- Table structure for w_user_wallet
-- ----------------------------
DROP TABLE IF EXISTS `w_user_wallet`;
CREATE TABLE `w_user_wallet`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `platform` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '10' COMMENT '默认角色',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `coin_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '币种大类',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `private_key` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '私钥',
  `code_qr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否需要处理  0:否   1是',
  `valid` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'E' COMMENT '是否有效：D无效E有效',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `udix_address`(`address`) USING BTREE,
  UNIQUE INDEX `uidx_orderId_coinType_token`(`user_id`, `platform`, `coin_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7673 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '钱包' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_user_wallet
-- ----------------------------
INSERT INTO `w_user_wallet` VALUES (7671, '10', 10086, 'BTC', '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', '8C3nnnLXOgC90nf96hmLjQuaVE517JSBAhP1Pc78uRU4He1Y2JXPBPHfar66CdzX/v8BQMqupwVea0hYwsMN4A==', NULL, 0, 'E', '2019-08-22 22:52:06.180', '2019-08-23 14:14:04.683');
INSERT INTO `w_user_wallet` VALUES (7672, '10', 10086, 'BTC_TOKEN', '15Q12Sj9r1FZxEJnFrQzxgkAtmYqKZL7pX', 'k4sAtuyElDbW5ada8uew2ZWrz63LAQsARtLrPfiIOZ4djI6YUdANqrkRiIpRfq2vh5BQ/R5/UOfY8cRTBPUOJw==', NULL, 0, 'E', '2019-08-22 22:55:04.228', '2019-08-23 14:14:04.749');

-- ----------------------------
-- Table structure for w_wallet_account
-- ----------------------------
DROP TABLE IF EXISTS `w_wallet_account`;
CREATE TABLE `w_wallet_account`  (
  `id` bigint(64) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) NULL DEFAULT NULL COMMENT '货币id(t_currency.id)',
  `total` decimal(20, 8) NULL DEFAULT NULL COMMENT '可用总数量',
  `trade_frozen` decimal(20, 8) NULL DEFAULT NULL COMMENT '交易冻结数量',
  `transfer_frozen` decimal(20, 8) NULL DEFAULT NULL COMMENT '划转冻结数量',
  `version` int(11) NULL DEFAULT 1 COMMENT '版本号，每更新一次数据加1',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_user_currency_id`(`user_id`, `currency_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资金钱包表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_wallet_account
-- ----------------------------
INSERT INTO `w_wallet_account` VALUES (361882792222793728, 1, 1, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 11:54:07', '2019-08-22 11:54:07');
INSERT INTO `w_wallet_account` VALUES (361882792659001344, 1, 3, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 11:54:07', '2019-08-22 11:54:07');
INSERT INTO `w_wallet_account` VALUES (361882892982558720, 2, 3, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 11:54:31', '2019-08-22 11:54:31');
INSERT INTO `w_wallet_account` VALUES (361882944543137792, 2, 1, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-22 11:54:43', '2019-08-22 11:54:43');
INSERT INTO `w_wallet_account` VALUES (363680200913068032, 10010, 3, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-27 10:56:22', '2019-08-27 10:56:22');
INSERT INTO `w_wallet_account` VALUES (364416016048201728, 1, 2, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-29 11:40:14', '2019-08-29 11:40:14');
INSERT INTO `w_wallet_account` VALUES (1164562087853088769, 10086, 1, 0.21400000, 0.00000000, 0.33400000, 0, '2019-08-22 23:36:53', '2019-08-22 23:36:53');
INSERT INTO `w_wallet_account` VALUES (1164712441513218049, 10086, 3, 68.00000000, 0.00000000, 32.00000000, 0, '2019-08-23 09:34:20', '2019-08-23 09:34:20');

-- ----------------------------
-- Table structure for w_wallet_account_record
-- ----------------------------
DROP TABLE IF EXISTS `w_wallet_account_record`;
CREATE TABLE `w_wallet_account_record`  (
  `id` bigint(64) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) NULL DEFAULT NULL COMMENT '币种id',
  `before_amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '成交前金额',
  `after_amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '成交后金额',
  `change_amount` decimal(20, 8) NULL DEFAULT NULL COMMENT '变动金额 转入+  转出-',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '成交类型,1充币 2提币 3-划转：币币钱包-->资金钱包 4-划转：资金钱包-->币币钱包 5-划转:资金钱包-->c2c钱包  6-划转:c2c钱包-->资金钱包',
  `order_id` bigint(64) NULL DEFAULT NULL COMMENT '关联的id,订单id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE,
  INDEX `index_currency_id`(`currency_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资金钱包流水表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of w_wallet_account_record
-- ----------------------------
INSERT INTO `w_wallet_account_record` VALUES (361603527120003072, 1, 3, 0.00000000, 1.00000000, 1.00000000, 3, NULL, '2019-08-21 17:24:25');
INSERT INTO `w_wallet_account_record` VALUES (364110950137339904, 10086, 1, 1.00000000, 0.89950000, -0.10050000, 2, NULL, '2019-08-28 15:28:01');
INSERT INTO `w_wallet_account_record` VALUES (364111366317154304, 10086, 1, 0.89950000, 0.79900000, -0.10050000, 2, NULL, '2019-08-28 15:29:40');
INSERT INTO `w_wallet_account_record` VALUES (364111869713326080, 10086, 1, 0.79900000, 0.69850000, -0.10050000, 2, NULL, '2019-08-28 15:31:40');
INSERT INTO `w_wallet_account_record` VALUES (364114260068147200, 10086, 1, 0.69850000, 0.54800000, -0.15050000, 2, NULL, '2019-08-28 15:41:10');

-- ----------------------------
-- Table structure for w_wallet_address
-- ----------------------------
DROP TABLE IF EXISTS `w_wallet_address`;
CREATE TABLE `w_wallet_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(255) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `token_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `currency_id` int(11) NULL DEFAULT NULL COMMENT '货币id(t_currency.id)',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态:0禁用,1正常',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_wallet_address
-- ----------------------------
INSERT INTO `w_wallet_address` VALUES (75, 10086, 'test', 'BTC', 1, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, '2019-08-26 22:57:51', '2019-08-26 22:57:51');
INSERT INTO `w_wallet_address` VALUES (76, 10086, 'test2', 'USDT', 3, '15Q12Sj9r1FZxEJnFrQzxgkAtmYqKZL7pX', 1, '2019-08-26 23:30:28', '2019-08-26 23:30:28');
INSERT INTO `w_wallet_address` VALUES (77, 10086, 'testf', 'BTC', 1, 'BTC', 1, '2019-08-28 14:42:21', '2019-08-28 14:42:21');
INSERT INTO `w_wallet_address` VALUES (78, 10086, 'test', 'USDT', 3, '1234578edewsfWE', 1, '2019-08-29 16:27:44', '2019-08-29 16:27:44');

-- ----------------------------
-- Table structure for w_wallet_bill
-- ----------------------------
DROP TABLE IF EXISTS `w_wallet_bill`;
CREATE TABLE `w_wallet_bill`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `platform` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '10' COMMENT '默认角色',
  `order_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `direction` int(11) NOT NULL DEFAULT 1 COMMENT '1:进账，-1出账',
  `coin_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN',
  `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '币种  BTC、USDT、EOS、ETH等',
  `contract` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '合约地址USDT=31',
  `sender_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '发送地址',
  `receiver_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收者地址',
  `amount` decimal(18, 8) NOT NULL COMMENT '对应coin转账数量',
  `block` bigint(20) NOT NULL DEFAULT 0 COMMENT '打包区块',
  `tx` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易hash',
  `trade_step` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '20' COMMENT '交易步骤： 10待审核 20待转账 25在转账 30已发布 40确认中 50确认完成',
  `operation_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '10' COMMENT '操作类型',
  `transfer_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '转移状态：0：初始化，1：待归集，2已转移，3不用处理',
  `flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uidx_tx`(`tx`) USING BTREE,
  UNIQUE INDEX `uidx_orderId`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户钱包账单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_wallet_bill
-- ----------------------------
INSERT INTO `w_wallet_bill` VALUES (9, 10086, '10', '362287302136958976', -1, 'BTC', 'BTC', '', '', '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 0.10000000, 0, '362287302136958976', '50', '10', 3, 2, '', '2019-08-28 15:27:07.684', '2019-08-28 15:27:48.672');
INSERT INTO `w_wallet_bill` VALUES (10, 10086, '10', '362289402011062272', -1, 'BTC', 'BTC', '', '', '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 0.10000000, 0, '362289402011062272', '50', '10', 3, 2, '', '2019-08-28 15:28:57.600', '2019-08-28 15:29:27.798');
INSERT INTO `w_wallet_bill` VALUES (11, 10086, '10', '362322582646689792', -1, 'BTC', 'BTC', '', '', '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 0.10000000, 0, '362322582646689792', '50', '10', 3, 2, '', '2019-08-28 15:30:07.555', '2019-08-28 15:31:27.892');
INSERT INTO `w_wallet_bill` VALUES (12, 10086, '10', '364104990106718208', -1, 'BTC', 'BTC', '', '', '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 0.15000000, 0, '364104990106718208', '50', '10', 3, 2, '', '2019-08-28 15:40:37.525', '2019-08-28 15:40:57.717');

SET FOREIGN_KEY_CHECKS = 1;
