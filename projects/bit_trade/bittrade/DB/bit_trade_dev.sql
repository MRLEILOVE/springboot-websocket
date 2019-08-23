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

 Date: 23/08/2019 14:51:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `payment_method_id` bigint(64) UNSIGNED NOT NULL COMMENT '收款方式id，出售单为收款方式, 购买单为付款方式',
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
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_advert_info
-- ----------------------------
INSERT INTO `t_advert_info` VALUES (23, 1, 1, 1, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 196.00000000, 12.00000000, 1, 1, 1, 1, '2018-11-11 12:12:12', 10, '请买家尽快付款，谢谢', 4, '2019-08-22 15:03:06', '2019-08-22 19:52:29');
INSERT INTO `t_advert_info` VALUES (24, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 6.00000000, 4.00000000, 1, 1, 1, 1, '2018-11-11 12:12:12', NULL, '这个是购买广告', 2, '2019-08-22 21:56:18', '2019-08-22 21:56:18');
INSERT INTO `t_advert_info` VALUES (25, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, 1, 1, 1, 1, '2018-11-11 12:12:12', NULL, '这个是购买广告', 1, '2019-08-23 00:46:15', '2019-08-23 00:46:15');
INSERT INTO `t_advert_info` VALUES (26, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, 1, 1, 1, 1, '2018-11-11 12:12:12', NULL, '这个是购买广告', 1, '2019-08-23 00:46:26', '2019-08-23 00:46:26');
INSERT INTO `t_advert_info` VALUES (27, 1, 1, 2, 1, NULL, 6.00, NULL, 20.00, 1000.00, 0.00000000, 10.00000000, 0.00000000, 1, 1, 1, 1, '2018-11-11 12:12:12', NULL, '这个是购买广告', 1, '2019-08-23 00:46:37', '2019-08-23 00:46:37');

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
  `status` tinyint(1) NOT NULL COMMENT '状态（1，已拍下；2，已付款；3，已收款；5，已完成；6，已取消，7，超时关闭）',
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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_advert_order
-- ----------------------------
INSERT INTO `t_advert_order` VALUES (1, 23, 1, 'C2C361933910411710464', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-22 15:20:24', 0, NULL, NULL, NULL, '2019-08-22 15:27:27', NULL, '2019-08-22 15:18:29', '2019-08-22 15:18:29');
INSERT INTO `t_advert_order` VALUES (3, 23, 1, 'C2C362002942800302080', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-22 19:54:33', 0, NULL, NULL, NULL, '2019-08-22 20:01:33', NULL, '2019-08-22 19:51:19', '2019-08-22 19:51:19');
INSERT INTO `t_advert_order` VALUES (4, 23, 1, 'C2C362003285416218624', 'CNY', 1, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-22 19:55:55', 0, NULL, NULL, NULL, '2019-08-22 20:02:55', NULL, '2019-08-22 19:52:41', '2019-08-22 19:52:41');
INSERT INTO `t_advert_order` VALUES (5, 24, 1, 'C2C362035928547594240', 'CNY', 2, NULL, 1, 1, 1, NULL, 24.00, 4.00000000, 6.00, 0.00, 0.00000000, 1, '2019-08-22 22:05:37', 0, NULL, NULL, NULL, NULL, NULL, '2019-08-22 22:02:23', '2019-08-22 22:02:23');

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
  `is_recharge` tinyint(1) NULL DEFAULT 0 COMMENT '是否允许充值 0：禁止 1：允许',
  `is_withdraw` tinyint(1) NULL DEFAULT 0 COMMENT '是否允许提现 0：禁止 1：允许',
  `desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '虚拟货币类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_currency
-- ----------------------------
INSERT INTO `t_currency` VALUES (1, 'BTC', '比特幣', 1, 1, 1, 1, '比特币（Bitcoin，简称BTC）是目前使用最为广泛的一种数字货币，它诞生于2009年1月3日，是一种点对点（P2P）传输的数字加密货币，总量2100万枚。比特币网络每10分钟释放出一定数量币，预计在2140年达到极限。比特币被投资者称为“数字黄金”。比特币依据特定算法，通过大量的计算产生，不依靠特定货币机构发行，其使用整个P2P网络中众多节点构成的分布式数据库来确认并记录所有的交易行为，并使用密码学设计确保货币流通各个环节安全性，可确保无法通过大量制造比特币来人为操控币值。基于密码学的设计可以使比特币只能被真实拥有者转移、支付及兑现。同样确保了货币所有权与流通交易的匿名性。', '2019-06-26 15:43:19');
INSERT INTO `t_currency` VALUES (2, 'ETH', '以太坊', 1, 1, 0, 0, '以太坊（Ethereum）是下一代密码学账本，可以支持众多的高级功能，包括用户发行货币，智能协议，去中心化的交易和设立去中心化自治组织(DAOs)或去中心化自治公司（DACs）。以太坊并不是把每一单个类型的功能作为特性来特别支持，相反，以太坊包括一个内置的图灵完备的脚本语言，允许通过被称为“合同”的机制来为自己想实现的特性写代码。一个合同就像一个自动的代理，每当接收到一笔交易，合同就会运行特定的一段代码，这段代码能修改合同内部的数据存储或者发送交易。', '2019-06-26 15:44:40');
INSERT INTO `t_currency` VALUES (3, 'USDT', '泰達幣', 1, 1, 1, 1, 'USDT是由Tether公司发行的一种虚拟货币，实际上和其他虚拟货币并没有什么本质的区别。但他牛就牛在宣布严格遵守1：1的准备金保证，即每发行1枚USDT代币，其银行帐户都会有1美元的资金保障。这样一来，USDT就和美元等价了，俨然成为了虚拟货币和现实货币的桥梁。特别是随着全球监管越来越严格，更多的人都通过USDT来进行虚拟货币的投资，地位自然也就水涨船高', '2019-07-05 16:21:38');
INSERT INTO `t_currency` VALUES (4, 'LTC', '萊特幣', 1, 0, 0, 0, '莱特币诞生于2011年11月9日，被称为是“数字白银”。莱特币在技术上和比特币具有相同的实现原理。它是第一个基于Scrypt算法的网络数字货币，与比特币相比，莱特币拥有更快的交易确认时间，更高的网络交易容量和效率。莱特币现在拥有完整的产业链，充分的流动性，足以证明其是成熟、安全、稳定的商用金融系统。', '2019-07-19 11:32:27');
INSERT INTO `t_currency` VALUES (5, 'EOS', '柚子幣', 1, 0, 0, 0, 'EOS (Enterprise Operation System)是由 Block.one公司主导开发的一种全新的基于区块链智能合约平台，旨在为高性能分布式应用提供底层区块链平台服务。EOS 项目的目标是实现一个类似操作系统的支撑分布式应用程序的区块链架构。该架构可以提供账户，身份认证，数据库，异步通信以及可在数以万计的 CPU/GPU群集上进行程序调度和并行运算。EOS最终可以支持每秒执行数百万个交易，同时普通用户执行智能合约无需支付使用费用。', '2019-07-19 11:32:30');
INSERT INTO `t_currency` VALUES (6, 'XRP', '瑞波幣', 1, 0, 0, 0, 'Ripple 是一个去中心化的资产传输网络,用于解决金融机构以及用户间的资产转换和信任问题。XRP 是这个网络上面的基础资产,目前已经成为市值排名前几位的区块链资产。', '2019-07-19 11:32:32');
INSERT INTO `t_currency` VALUES (7, 'ETC', '以太經典', 1, 0, 0, 0, '以太经典（Ethereum Classic，简称ETC）是原始以太坊Ethereum区块链的延续， 是以太坊在1,920,000个区块后硬分叉出的分叉币种，功能和以太坊极为类似。应用程序运行完全按照程序设定，没有任何停机、审查、欺诈或第三方干扰的可能性，免受外部干扰和主观篡改交易。', '2019-08-03 17:24:12');
INSERT INTO `t_currency` VALUES (8, 'BCH', '比特幣現金', 1, 0, 0, 0, '比特币现金（Bitcoin Cash (BCH)）是比特币的硬分叉币，通过升级协议修复链上容量，不包含SegWit功能。比特币现金（BCH）于2017年8月1日发布，作为原始比特币核心软件的升级版本，支持大区块（将区块大小提升至8M），有效提高支付处理速度，实现更快、更便宜的交易以及更流畅的用户体验', '2019-08-03 17:26:36');
INSERT INTO `t_currency` VALUES (9, 'TRX', 'TRX', 1, 0, 0, 0, 'TRON的流通市值为5,965,500,000美元（Coinmarketcap.com）,每日成交量为257,473,000美元。 现在,TRX在全球排名第10,全球用户近200万。 TRON-TRX的官方代币已在近70个交易所上市,包括BitMEX,Binance,Bittrex,Bitfinex,Upbit,Bithumb,Huobi,OKEx等。', '2019-08-03 17:30:23');
INSERT INTO `t_currency` VALUES (10, 'BSV', '比特幣SV', 1, 0, 0, 0, '应比特币现金（BCH）矿业巨头CoinGeek及其他矿工的要求，我们创建了比特币SV（Bitcoin Cash SV [IOU]），旨在为矿工提供明确的比特币现金（BCH）实现选择，并允许企业在其稳固可靠的基础上构建应用程序和网站。 比特币SV创建全球区块链的路线图以四大基本支柱为基础：安全性、稳定性、可扩容性、安全即时交易（亦称为零确认）。', '2019-08-03 17:32:45');

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
INSERT INTO `t_legal_currency_account` VALUES (1, 1, 1, 960.00000000, 40.00000000, 0, 0, 0, '2019-08-22 10:08:53', '2019-08-22 10:08:53');
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
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

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
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '币种类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_coin
-- ----------------------------
INSERT INTO `w_coin` VALUES (1, 'BTC', 'BTC', '', 'E', 'E', '', '2019-08-23 12:43:26.000', '2019-08-23 12:43:30.000');
INSERT INTO `w_coin` VALUES (2, 'ETH', 'ETH', '', 'D', 'D', '', '2019-08-23 12:44:15.000', '2019-08-23 12:44:29.173');

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
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_coin_config
-- ----------------------------
INSERT INTO `w_coin_config` VALUES (1, 'BTC', 'BTC', '', 591119, 8, 6, '1A968Gxk1G2t5TawiSRd3XACdoqcHtEqZP', 0.00000000, 'E', '', '2019-06-28 16:32:06.734', '2019-08-23 14:17:31.143');
INSERT INTO `w_coin_config` VALUES (2, 'BTC_TOKEN', 'USDT', '31', 591085, 8, 6, '1A968Gxk1G2t5TawiSRd3XACdoqcHtEqZP', 0.00000000, 'E', '', '2019-06-28 16:32:34.410', '2019-08-21 18:16:45.433');

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
) ENGINE = InnoDB AUTO_INCREMENT = 324 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_order
-- ----------------------------
INSERT INTO `w_order` VALUES (322, 10086, '362287302136958976', -1, 0.00050000, 'BTC', 'BTC', '', 0.10000000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-23 14:41:19', '2019-08-23 14:41:19');
INSERT INTO `w_order` VALUES (323, 10086, '362289402011062272', -1, 0.00050000, 'BTC', 'BTC', '', 0.10000000, '1M9P5jzB7jGejX5MQBYS2Ew9YTmpwTpT9d', 1, NULL, '2019-08-23 14:49:39', '2019-08-23 14:49:39');

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
INSERT INTO `w_wallet_account` VALUES (1164562087853088769, 10086, 1, 0.79900000, 0.00000000, 0.20100000, 0, '2019-08-22 23:36:53', '2019-08-22 23:36:53');
INSERT INTO `w_wallet_account` VALUES (1164712441513218049, 10086, 3, 0.00000000, 0.00000000, 0.00000000, 0, '2019-08-23 09:34:20', '2019-08-23 09:34:20');

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
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户钱包账单' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
