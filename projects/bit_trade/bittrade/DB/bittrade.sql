/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26 : Database - bit_trade
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bit_trade` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bit_trade`;

/*Table structure for table `t_currency` */

DROP TABLE IF EXISTS `t_currency`;

CREATE TABLE `t_currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `short_name` varchar(10) DEFAULT NULL COMMENT '简称',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态:0禁用,1正常',
  `type` tinyint(1) DEFAULT NULL COMMENT '法币:0否,1是',
  `desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='虚拟货币类型表';

/*Data for the table `t_currency` */

insert  into `t_currency`(`id`,`name`,`short_name`,`status`,`type`,`desc`,`create_time`) values (1,'BTC','比特币',1,1,'比特币（Bitcoin，简称BTC）是目前使用最为广泛的一种数字货币，它诞生于2009年1月3日，是一种点对点（P2P）传输的数字加密货币，总量2100万枚。比特币网络每10分钟释放出一定数量币，预计在2140年达到极限。比特币被投资者称为“数字黄金”。比特币依据特定算法，通过大量的计算产生，不依靠特定货币机构发行，其使用整个P2P网络中众多节点构成的分布式数据库来确认并记录所有的交易行为，并使用密码学设计确保货币流通各个环节安全性，可确保无法通过大量制造比特币来人为操控币值。基于密码学的设计可以使比特币只能被真实拥有者转移、支付及兑现。同样确保了货币所有权与流通交易的匿名性。','2019-06-26 15:43:19'),(2,'ETH','以太坊',1,1,'以太坊（Ethereum）是下一代密码学账本，可以支持众多的高级功能，包括用户发行货币，智能协议，去中心化的交易和设立去中心化自治组织(DAOs)或去中心化自治公司（DACs）。以太坊并不是把每一单个类型的功能作为特性来特别支持，相反，以太坊包括一个内置的图灵完备的脚本语言，允许通过被称为“合同”的机制来为自己想实现的特性写代码。一个合同就像一个自动的代理，每当接收到一笔交易，合同就会运行特定的一段代码，这段代码能修改合同内部的数据存储或者发送交易。','2019-06-26 15:44:40'),(3,'USDT','比特币代币',1,1,NULL,'2019-07-05 16:21:38'),(4,'LTC','莱特币',1,0,NULL,'2019-07-19 11:32:27'),(5,'EOS','柚子币',1,0,NULL,'2019-07-19 11:32:30'),(6,'XRP','瑞波币',1,0,NULL,'2019-07-19 11:32:32'),(7,'ESV','明星代币',1,0,NULL,'2019-07-19 11:32:35');

/*Table structure for table `t_currency_address` */

DROP TABLE IF EXISTS `t_currency_address`;

CREATE TABLE `t_currency_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) DEFAULT NULL COMMENT '货币id，t_currency表中的id',
  `adderess` varchar(180) DEFAULT NULL COMMENT '钱包地址',
  `address_remark` varchar(200) DEFAULT NULL COMMENT '地址备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `adderess` (`adderess`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户钱包地址表';

/*Data for the table `t_currency_address` */

/*Table structure for table `t_currency_optional` */

DROP TABLE IF EXISTS `t_currency_optional`;

CREATE TABLE `t_currency_optional` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `currency_trade_id` int(11) NOT NULL COMMENT '交易对id,t_currency_trade中的id',
  `status` tinyint(1) NOT NULL COMMENT '状态：0删除，1启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户的交易对自选表';

/*Data for the table `t_currency_optional` */

insert  into `t_currency_optional`(`id`,`user_id`,`currency_trade_id`,`status`,`create_time`,`update_time`) values (1,735,1,1,'2019-07-06 16:48:56','2019-07-19 17:16:05'),(2,735,2,0,'2019-07-06 17:46:39','2019-07-19 17:15:52'),(3,735,4,0,'2019-07-12 11:00:01','2019-07-19 17:06:00'),(4,735,3,0,'2019-07-19 11:11:24','2019-07-19 17:15:52'),(5,735,5,0,'2019-07-19 11:11:40','2019-07-19 16:58:00'),(6,735,7,1,'2019-07-19 17:00:46','2019-07-19 17:12:04'),(7,735,8,0,'2019-07-19 17:00:47','2019-07-19 17:15:55'),(8,735,9,1,'2019-07-19 17:12:05','2019-07-19 17:12:42');

/*Table structure for table `t_currency_trade` */

DROP TABLE IF EXISTS `t_currency_trade`;

CREATE TABLE `t_currency_trade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `symbol` varchar(20) DEFAULT NULL COMMENT '交易对,如BTC/USDT',
  `currency_id1` int(11) DEFAULT NULL COMMENT '货币id，t_currency表中的id',
  `currency_id2` int(11) DEFAULT NULL COMMENT '法币id，t_currency表中的id',
  `price_decimal_digits` int(11) DEFAULT NULL COMMENT '单价小数位',
  `count_decimal_digits` int(11) DEFAULT NULL COMMENT '数量小数位',
  `min_price` decimal(20,8) DEFAULT '0.00000000' COMMENT '最小挂单单价',
  `min_count` decimal(20,8) DEFAULT '0.00000000' COMMENT '最小挂单数量',
  `min_amount` decimal(20,8) DEFAULT '0.00000000' COMMENT '最小挂单金额',
  `max_price` decimal(20,8) DEFAULT '0.00000000' COMMENT '最大可买单价',
  `max_count` decimal(20,8) DEFAULT '0.00000000' COMMENT '最大可买数量',
  `max_amount` decimal(20,8) DEFAULT '0.00000000' COMMENT '最大可买金额',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：0禁用，1启用',
  `sort` int(4) DEFAULT '1' COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `symbol` (`symbol`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='法币类型匹配表';

/*Data for the table `t_currency_trade` */

insert  into `t_currency_trade`(`id`,`symbol`,`currency_id1`,`currency_id2`,`price_decimal_digits`,`count_decimal_digits`,`min_price`,`min_count`,`min_amount`,`max_price`,`max_count`,`max_amount`,`status`,`sort`,`create_time`,`update_time`) values (1,'ETH-USDT',2,3,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-06-26 15:48:49','2019-06-26 15:48:51'),(2,'ETH-BTC',2,1,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-06-26 15:48:49','2019-06-26 15:48:51'),(3,'LTC-USDT',4,3,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-07-19 11:02:26','2019-07-19 11:02:29'),(4,'EOS-USDT',5,3,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-07-19 11:04:08','2019-07-19 11:04:10'),(5,'XRP-USDT',6,3,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-07-19 11:04:43','2019-07-19 11:04:47'),(6,'ESV-USDT',7,3,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-07-19 11:05:19','2019-07-19 11:05:23'),(7,'LTC-BTC',4,1,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-07-19 11:07:57','2019-07-19 11:07:59'),(8,'EOS-BTC',5,1,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-07-19 11:08:35','2019-07-19 11:08:37'),(9,'XRP-BTC',6,1,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-07-19 11:09:09','2019-07-19 11:09:10'),(10,'ESV-BTC',7,1,2,2,'0.00000000','0.00000000','0.00000000','0.00000000','0.00000000','0.00000000',1,1,'2019-07-19 11:09:43','2019-07-19 11:09:45');

/*Table structure for table `t_entrust` */

DROP TABLE IF EXISTS `t_entrust`;

CREATE TABLE `t_entrust` (
  `id` bigint(64) NOT NULL COMMENT '委托表ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `currency_trade_id` int(11) DEFAULT NULL COMMENT '交易对id,t_currency_trade中的id',
  `price` decimal(20,10) DEFAULT NULL COMMENT '价格',
  `amount` decimal(20,8) DEFAULT NULL COMMENT '金额',
  `success_amount` decimal(20,8) DEFAULT NULL COMMENT '成功撮合金额',
  `count` decimal(20,8) DEFAULT NULL COMMENT '委托数量',
  `left_count` decimal(20,8) DEFAULT NULL COMMENT '未成交数量',
  `fees` decimal(20,8) DEFAULT NULL COMMENT '总手续费',
  `left_fees` decimal(20,8) DEFAULT NULL COMMENT '剩余的手续费(未成交完全时)',
  `status` int(11) DEFAULT NULL COMMENT '状态:1未完成,2部分成交,3完全成交,4用户撤销',
  `entrust_type` int(11) DEFAULT NULL COMMENT '委托类型:0市价交易,1限价交易',
  `entrust_direction` int(11) DEFAULT NULL COMMENT '委托方向:0买入,1卖出',
  `version` int(11) DEFAULT '1' COMMENT '版本号，每更新一次数据加1',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_user_id_status` (`user_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='委托交易表';

/*Data for the table `t_entrust` */

/*Table structure for table `t_entrust_record` */

DROP TABLE IF EXISTS `t_entrust_record`;

CREATE TABLE `t_entrust_record` (
  `id` bigint(64) NOT NULL COMMENT '委托明细表id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `rival_user_id` bigint(20) DEFAULT NULL COMMENT '对手方用户id',
  `entrust_id` bigint(64) DEFAULT NULL COMMENT '委托单id,t_fentrust.id',
  `rival_entrust_id` bigint(64) DEFAULT NULL COMMENT '对手委托单id,t_fentrust.id',
  `amount` decimal(20,8) DEFAULT NULL COMMENT '总金额',
  `price` decimal(20,10) DEFAULT NULL COMMENT '价格',
  `count` decimal(20,8) DEFAULT NULL COMMENT '数量',
  `rate` decimal(20,6) DEFAULT NULL COMMENT '费率',
  `fees` decimal(20,8) DEFAULT NULL COMMENT '费用',
  `currency_trade_id` int(11) DEFAULT NULL COMMENT '交易对id,t_currency_trade中的id',
  `is_active` tinyint(1) DEFAULT NULL COMMENT '是否主动:1-主买主卖，0-被买被卖',
  `entrust_direction` int(11) DEFAULT NULL COMMENT '交易类型:0-买，1-卖',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_create_time` (`create_time`),
  KEY `index_user_id` (`user_id`),
  KEY `index_currency_trade_id` (`currency_trade_id`),
  KEY `index_entrust_direction` (`entrust_direction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='委托交易日志';

/*Data for the table `t_entrust_record` */

/*Table structure for table `t_kline` */

DROP TABLE IF EXISTS `t_kline`;

CREATE TABLE `t_kline` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `symbol` varchar(20) DEFAULT NULL COMMENT '如:btc_usdt',
  `low` decimal(20,10) DEFAULT NULL COMMENT '最低价格',
  `high` decimal(20,10) DEFAULT NULL COMMENT '最高价格',
  `open` decimal(20,10) DEFAULT NULL COMMENT '开盘价格',
  `close` decimal(20,10) DEFAULT NULL COMMENT '收盘价格',
  `volume` decimal(30,10) DEFAULT NULL COMMENT '交易量',
  `granularity` int(1) DEFAULT NULL COMMENT '时间粒度，以秒为单位，如[60/180/300/900/1800/3600/7200/14400/21600/43200/86400/604800]',
  `time` datetime DEFAULT NULL COMMENT '开始时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_symbol_granularity_time` (`symbol`,`granularity`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='k线数据';

/*Data for the table `t_kline` */

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL COMMENT '提币用户',
  `order_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单id',
  `fee` decimal(10,0) DEFAULT NULL COMMENT '手续费',
  `token` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '具体币种',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '提币数量',
  `receiver_address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '接收地址',
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '审核状态',
  `operator` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`userId`,`order_id`,`fee`,`token`,`amount`,`receiver_address`,`type`,`operator`,`create_time`,`update_time`) values (1,NULL,'345987706037342208',NULL,NULL,NULL,NULL,'未审核',NULL,'2019-07-09 15:12:44','2019-07-09 15:12:44');

/*Table structure for table `t_param_config` */

DROP TABLE IF EXISTS `t_param_config`;

CREATE TABLE `t_param_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `param_key` varchar(50) NOT NULL COMMENT '参数键',
  `param_value` varchar(200) DEFAULT NULL COMMENT '参数值',
  `param_status` int(1) DEFAULT NULL COMMENT '启动状态,1：开启, 0:不开启',
  `param_remark` varchar(100) DEFAULT NULL COMMENT '参数备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(20) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `updater` varchar(20) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

/*Data for the table `t_param_config` */

insert  into `t_param_config`(`id`,`param_key`,`param_value`,`param_status`,`param_remark`,`create_time`,`creater`,`update_time`,`updater`) values (1,'okexUsdToCnyRateKey','https://www.okex.me/api/swap/v3/rate',1,'获取okex法币汇率URL','2019-07-04 15:00:29','sys',NULL,NULL),(2,'okexKlineHistorySwitchKey','on',1,'获取K线历史数据开关','2019-07-05 10:10:13','sys',NULL,NULL),(3,'okexKlineUrlKey','https://www.okex.me/api/spot/v3/instruments/{0}/candles?granularity={1}',1,'okex-K线地址','2019-07-05 10:23:33','sys',NULL,NULL),(4,'okexSymbolKlineHistoryDataKey','BTC-USDT,ETH-USDT,LTC-USDT,EOS-USDT',1,'拉取指定okex交易对历史数据','2019-07-05 10:35:07','sys',NULL,NULL),(5,'okexGranularitysKey','60,180,300,900,1800,3600,7200,14400,21600,43200,86400,604800',1,'okex-获取K线数据的时间粒度','2019-07-05 10:46:31','sys',NULL,NULL),(6,'okexWebsocketUrlKey','wss://real.okex.com:10442/ws/v3',1,'okex-WebSocket地址','2019-07-06 16:10:33','sys',NULL,NULL),(7,'okexTickerHttpUrlKey','https://www.okex.me/api/spot/v3/instruments/{0}/ticker',1,'okex-Ticker地址','2019-07-08 11:00:14','sys',NULL,NULL);

/*Table structure for table `t_ticker` */

DROP TABLE IF EXISTS `t_ticker`;

CREATE TABLE `t_ticker` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `symbol` varchar(30) DEFAULT NULL COMMENT '币对名称',
  `last` varchar(30) DEFAULT NULL COMMENT '最新成交价[收盘价]',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行情表';

/*Data for the table `t_ticker` */

/*Table structure for table `t_user_capital_account_record` */

DROP TABLE IF EXISTS `t_user_capital_account_record`;

CREATE TABLE `t_user_capital_account_record` (
  `id` bigint(64) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) DEFAULT NULL COMMENT '货币id，t_currency表中的id',
  `count` decimal(20,8) DEFAULT NULL COMMENT '数量',
  `adderess` varchar(180) DEFAULT NULL COMMENT '地址',
  `fees` decimal(20,8) DEFAULT NULL COMMENT '手续费',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型:1:充币,2提币,3:撤销提现',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态:0:失败,1:成功',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户资金账户记录';

/*Data for the table `t_user_capital_account_record` */

/*Table structure for table `t_wallet` */

DROP TABLE IF EXISTS `t_wallet`;

CREATE TABLE `t_wallet` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) DEFAULT NULL COMMENT '货币id(t_currency.id)',
  `total` decimal(20,8) DEFAULT NULL COMMENT '可用总数量',
  `trade_frozen` decimal(20,8) DEFAULT NULL COMMENT '交易冻结数量',
  `transfer_frozen` decimal(20,8) DEFAULT NULL COMMENT '划转冻结数量',
  `version` int(11) DEFAULT '1' COMMENT '版本号，每更新一次数据加1',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_currency_id` (`currency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=198951 DEFAULT CHARSET=utf8mb4 COMMENT='虚拟币钱包表';

/*Data for the table `t_wallet` */

insert  into `t_wallet`(`id`,`user_id`,`currency_id`,`total`,`trade_frozen`,`transfer_frozen`,`version`,`create_time`,`update_time`) values (36239,841,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:05:48','2019-07-24 10:05:58'),(36240,841,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:05:48','2019-07-24 10:05:58'),(36247,105,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:05:58','2019-07-24 10:05:58'),(36248,105,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:05:58','2019-07-24 10:05:58'),(74963,259,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74964,259,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74965,126,3,'0.00000000','0.00000000','0.00000000',8,'2019-07-24 10:29:35','2019-07-24 10:29:36'),(74966,126,2,'0.00000000','0.00000000','0.00000000',8,'2019-07-24 10:29:35','2019-07-24 10:29:36'),(74967,713,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74968,713,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74969,523,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74970,523,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74971,332,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74972,332,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74973,348,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74974,348,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74975,998,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:35'),(74976,998,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:35','2019-07-24 10:29:36'),(74977,68,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:36','2019-07-24 10:29:36'),(74978,68,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:36','2019-07-24 10:29:36'),(74979,181,2,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:36','2019-07-24 10:29:36'),(74980,181,3,'0.00000000','0.00000000','0.00000000',1,'2019-07-24 10:29:36','2019-07-24 10:29:36');

/*Table structure for table `t_wallet_record` */

DROP TABLE IF EXISTS `t_wallet_record`;

CREATE TABLE `t_wallet_record` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `currency_id` int(11) DEFAULT NULL COMMENT '货币id(t_currency.id)',
  `before_amount` decimal(20,8) DEFAULT NULL COMMENT '成交前金额',
  `after_amount` decimal(20,8) DEFAULT NULL COMMENT '成交后金额',
  `change_amount` decimal(20,8) DEFAULT NULL COMMENT '变动金额',
  `type` tinyint(1) DEFAULT NULL COMMENT '成交类型,1-充值 2-提现 3-币币交易 4-划转：币币钱包-->法币钱包 5-划转：法币钱包-->币币钱包',
  `entrust_record_id` bigint(64) DEFAULT NULL COMMENT '关联的id,当前主要指成交id,t_entrust_record.id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_user_id` (`user_id`),
  KEY `index_currency_id` (`currency_id`)
) ENGINE=InnoDB AUTO_INCREMENT=346270420988727421 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='虚拟币钱包表日志表';

/*Data for the table `t_wallet_record` */

insert  into `t_wallet_record`(`id`,`user_id`,`currency_id`,`before_amount`,`after_amount`,`change_amount`,`type`,`entrust_record_id`,`create_time`) values (346270420988727385,841,2,'0.00000000','0.00000000','0.00000000',3,351119600546615296,'2019-07-24 10:05:58'),(346270420988727386,841,3,'0.00000000','0.00000000','0.00000000',3,351119600546615296,'2019-07-24 10:05:58'),(346270420988727387,105,3,'0.00000000','0.00000000','0.00000000',3,351119600546615296,'2019-07-24 10:05:58'),(346270420988727388,105,2,'0.00000000','0.00000000','0.00000000',3,351119600546615296,'2019-07-24 10:05:58'),(346270420988727389,259,2,'0.00000000','0.00000000','0.00000000',3,351206075023888384,'2019-07-24 10:29:35'),(346270420988727390,259,3,'0.00000000','0.00000000','0.00000000',3,351206075023888384,'2019-07-24 10:29:35'),(346270420988727391,126,3,'0.00000000','0.00000000','0.00000000',3,351206075023888384,'2019-07-24 10:29:35'),(346270420988727392,126,2,'0.00000000','0.00000000','0.00000000',3,351206075023888384,'2019-07-24 10:29:35'),(346270420988727393,713,2,'0.00000000','0.00000000','0.00000000',3,351206075942440960,'2019-07-24 10:29:35'),(346270420988727394,713,3,'0.00000000','0.00000000','0.00000000',3,351206075942440960,'2019-07-24 10:29:35'),(346270420988727395,126,3,'0.00000000','0.00000000','0.00000000',3,351206075942440960,'2019-07-24 10:29:35'),(346270420988727396,126,2,'0.00000000','0.00000000','0.00000000',3,351206075942440960,'2019-07-24 10:29:35'),(346270420988727397,523,2,'0.00000000','0.00000000','0.00000000',3,351206076865187840,'2019-07-24 10:29:35'),(346270420988727398,523,3,'0.00000000','0.00000000','0.00000000',3,351206076865187840,'2019-07-24 10:29:35'),(346270420988727399,126,3,'0.00000000','0.00000000','0.00000000',3,351206076865187840,'2019-07-24 10:29:35'),(346270420988727400,126,2,'0.00000000','0.00000000','0.00000000',3,351206076865187840,'2019-07-24 10:29:35'),(346270420988727401,332,2,'0.00000000','0.00000000','0.00000000',3,351206077783740416,'2019-07-24 10:29:35'),(346270420988727402,332,3,'0.00000000','0.00000000','0.00000000',3,351206077783740416,'2019-07-24 10:29:35'),(346270420988727403,126,3,'0.00000000','0.00000000','0.00000000',3,351206077783740416,'2019-07-24 10:29:35'),(346270420988727404,126,2,'0.00000000','0.00000000','0.00000000',3,351206077783740416,'2019-07-24 10:29:35'),(346270420988727405,348,2,'0.00000000','0.00000000','0.00000000',3,351206078832316416,'2019-07-24 10:29:35'),(346270420988727406,348,3,'0.00000000','0.00000000','0.00000000',3,351206078832316416,'2019-07-24 10:29:35'),(346270420988727407,126,3,'0.00000000','0.00000000','0.00000000',3,351206078832316416,'2019-07-24 10:29:35'),(346270420988727408,126,2,'0.00000000','0.00000000','0.00000000',3,351206078832316416,'2019-07-24 10:29:35'),(346270420988727409,998,2,'0.00000000','0.00000000','0.00000000',3,351206079692148736,'2019-07-24 10:29:36'),(346270420988727410,998,3,'0.00000000','0.00000000','0.00000000',3,351206079692148736,'2019-07-24 10:29:36'),(346270420988727411,126,3,'0.00000000','0.00000000','0.00000000',3,351206079692148736,'2019-07-24 10:29:36'),(346270420988727412,126,2,'0.00000000','0.00000000','0.00000000',3,351206079692148736,'2019-07-24 10:29:36'),(346270420988727413,68,2,'0.00000000','0.00000000','0.00000000',3,351206080543592448,'2019-07-24 10:29:36'),(346270420988727414,68,3,'0.00000000','0.00000000','0.00000000',3,351206080543592448,'2019-07-24 10:29:36'),(346270420988727415,126,3,'0.00000000','0.00000000','0.00000000',3,351206080543592448,'2019-07-24 10:29:36'),(346270420988727416,126,2,'0.00000000','0.00000000','0.00000000',3,351206080543592448,'2019-07-24 10:29:36'),(346270420988727417,181,2,'0.00000000','0.00000000','0.00000000',3,351206081428590592,'2019-07-24 10:29:36'),(346270420988727418,181,3,'0.00000000','0.00000000','0.00000000',3,351206081428590592,'2019-07-24 10:29:36'),(346270420988727419,126,3,'0.00000000','0.00000000','0.00000000',3,351206081428590592,'2019-07-24 10:29:36'),(346270420988727420,126,2,'0.00000000','0.00000000','0.00000000',3,351206081428590592,'2019-07-24 10:29:36');

/*Table structure for table `t_wallet_transfer` */

DROP TABLE IF EXISTS `t_wallet_transfer`;

CREATE TABLE `t_wallet_transfer` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `currency` int(11) DEFAULT NULL COMMENT '货币id(t_currency的id)',
  `count` decimal(30,10) DEFAULT NULL COMMENT '数量',
  `business_number` varchar(100) DEFAULT NULL COMMENT '业务流水号',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态，1.待处理，2.成功，3.失败，4.状态未明',
  `typeChannel` tinyint(1) DEFAULT NULL COMMENT '操作渠道：1.C2C钱包划转到币币钱包，2.币币钱包划转到C2C钱包',
  `sourceChannel` tinyint(1) DEFAULT NULL COMMENT '来源渠道：1.app，2.后台，3.PC',
  `desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_currency` (`currency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='钱包划转记录表';

/*Data for the table `t_wallet_transfer` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
