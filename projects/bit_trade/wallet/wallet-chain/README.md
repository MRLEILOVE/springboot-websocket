## 钱包系统

####        流程介绍
###### 0、脚本
```
/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.16 : Database - wallet
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wallet` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wallet`;

/*Table structure for table `w_coin_config` */

DROP TABLE IF EXISTS `w_coin_config`;

CREATE TABLE `w_coin_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `coin_type` varchar(100) NOT NULL COMMENT '币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN',
  `token` varchar(100) NOT NULL COMMENT '币种  BTC、USDT、EOS、ETH等',
  `contract` varchar(100) NOT NULL COMMENT '合约地址USDT=31',
  `scan_block` bigint(20) NOT NULL COMMENT '扫描区块的高度（主要查充值数据）',
  `base_multiple` int(11) NOT NULL COMMENT '基础倍数8【基础单位换成聪】ETH是18，有些ETHtoken不是18.需要注意',
  `min_confirm` int(6) NOT NULL COMMENT '最低确认数',
  `boss_address` varchar(100) NOT NULL DEFAULT '' COMMENT 'boss钱包',
  `min_collection_amount` decimal(18,8) NOT NULL DEFAULT '0.00000000' COMMENT '最低归集数',
  `valid` char(1) NOT NULL DEFAULT 'E' COMMENT '禁用D  启用E',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `w_coin_config` */

insert  into `w_coin_config`(`id`,`coin_type`,`token`,`contract`,`scan_block`,`base_multiple`,`min_confirm`,`boss_address`,`min_collection_amount`,`valid`,`remark`,`create_time`,`update_time`) values (3,'BTC','BTC','',1566930,8,6,'msMCJ6QWsEyPbvouUUoPxZhyBQuccCVd4v','0.00000000','E','','2019-06-28 16:32:06.734','2019-07-01 18:17:15.541'),(4,'BTC_TOKEN','USDT','1',1566930,8,6,'msMCJ6QWsEyPbvouUUoPxZhyBQuccCVd4v','0.00000000','E','','2019-06-28 16:32:34.410','2019-07-01 18:17:15.584');

/*Table structure for table `w_config_wallet` */

DROP TABLE IF EXISTS `w_config_wallet`;

CREATE TABLE `w_config_wallet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `coin_type` varchar(100) NOT NULL COMMENT '币种大类',
  `wallet_type` varchar(20) NOT NULL COMMENT '钱包类型:withdarw[提币地址]fee[手续费地址]collect[归集]',
  `address` varchar(100) NOT NULL COMMENT '地址',
  `keystore` varchar(5000) NOT NULL DEFAULT '' COMMENT 'keystore',
  `valid` char(1) NOT NULL DEFAULT 'E' COMMENT '是否有效：D无效E有效',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='配置钱包';

/*Data for the table `w_config_wallet` */

insert  into `w_config_wallet`(`id`,`coin_type`,`wallet_type`,`address`,`keystore`,`valid`,`remark`,`create_time`,`update_time`) values (1,'BTC','withdraw','mxxsTaEtJK6hQUUNCNxzmsAcnietiddptj','Z9TMOgJvSy/9eENnHNJgkyv7ugPHElsnt/p+d+iRZdX32zPGrgyE2TSMVHMxzA6SkToQjW8bSWKh+CDGoV0CoA==','E','','2019-06-28 15:30:44.356','2019-06-28 15:30:44.356'),(3,'BTC','fee','mkQN4Fz5uKnt9v9bzTmvwrs2qHKxQKxqZQ','Xcj3idrucljlIvEmj1jJo2SezZpMLCU3qNnghSWmxuploy8Dmw/CucOixL5oUVxc2hKZt94E4FK5oUmbUYtnHg==','E','','2019-06-28 15:30:51.502','2019-06-28 15:30:51.502'),(4,'BTC_TOKEN','withdraw','mpB8hFejmyB5CxpgW8413QvaQWyiuEc2od','jbIednYOAA4fyhunO75+z3lo7dOE92xBIGfL1LLYkSdprvhQpqvfq/M9Hm0U1wbv9rXnjTmOS5ENhzVvYAGLhw==','E','','2019-06-28 15:30:52.724','2019-06-28 15:30:52.724'),(6,'BTC_TOKEN','fee','mmzed8uVRcb9mXykGkDVBDoCHxBj2YAehJ','bPlFpILBZlmKy9aCstekuLWzLUQKEsJJgl4I6AleW3OdQwE6fkjRSszVUkRbC93gLbUGYOi8UrhM6IXkLvDYzw==','E','','2019-06-28 15:30:53.948','2019-06-28 15:30:53.948');

/*Table structure for table `w_user_wallet` */

DROP TABLE IF EXISTS `w_user_wallet`;

CREATE TABLE `w_user_wallet` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `platform` varchar(2) NOT NULL DEFAULT '10' COMMENT '默认角色',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `coin_type` varchar(100) NOT NULL COMMENT '币种大类',
  `address` varchar(100) NOT NULL COMMENT '地址',
  `private_key` varchar(500) NOT NULL DEFAULT '' COMMENT '私钥',
  `flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否需要处理  0:否   1是',
  `valid` char(1) NOT NULL DEFAULT 'E' COMMENT '是否有效：D无效E有效',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `udix_address` (`address`),
  UNIQUE KEY `uidx_orderId_coinType_token` (`user_id`,`platform`,`coin_type`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='钱包';

/*Data for the table `w_user_wallet` */

insert  into `w_user_wallet`(`id`,`platform`,`user_id`,`coin_type`,`address`,`private_key`,`flag`,`valid`,`create_time`,`update_time`) values (102,'10',1,'BTC','mp1fUf3DaSMK67drp6Fz59AgMYFYSqcbod','DTnw8HWNS3ZORlyxLiFYzA5I8p+Kcn/TWSTT8PoZC08BuHSbWXBF9Fnkuxw+a+Cz6PaG6+0ql9zzRsJ30Xm3Ew==',0,'E','2019-06-28 11:50:54.957','2019-06-28 15:30:47.850'),(103,'10',1,'BTC_TOKEN','muXDurXCfFMS3z6DoUcMeHke4dYNGPfMoW','Jei/v69y1CCACwmmj2YMSWUTn3Gk6wXTj7ucFNCUmwZnJClsFvij4SfWkh6pgMRVJiLC7c2ElAjkuNyUSX5WaA==',0,'E','2019-06-28 11:50:54.982','2019-06-28 15:30:51.200'),(104,'10',2,'BTC','mgnYcJLbRAPc163x9dWxSs3KTRZhLn9P6T','UWfmtWK7huZumVwh1T187+Ce4ZuRq+Hw2R4yL461Gj7v6EA6W8fIQBV3BR7xKkqT4CDrBsVy9RTuYmCJBQ7yUg==',0,'E','2019-06-28 13:43:22.934','2019-06-28 15:30:52.084'),(105,'10',2,'BTC_TOKEN','myt1mnQ3EaDZFj3KL18KH8ArtaRC3d6DzP','GAe/eIQSHHvVCNyiFGb9qIbaLVybkZtnva7uZnAT/bqFb+d3WMYMoHxSfVwGhdBIl+xELi6h4RDn0yUERdAEVw==',0,'E','2019-06-28 13:43:22.947','2019-06-28 15:30:52.941'),(106,'10',3,'BTC','mgUWCb3NGBMowsA8DyjFsEcfKY51vuLr6L','T2QT1+khJsKo9hggcdIcRTJOUh36Ol/VFZ4q6ivMO8H9Zl29DRuX5+gLXCcCT2wHeVormwdhxckOG4e5F1x5jA==',0,'E','2019-06-30 00:52:06.391','2019-06-30 00:52:11.613'),(107,'10',3,'BTC_TOKEN','n2j6VncisAbdAVAvfC9ZSgpzQH5UFEy5GW','8FVd2xMMAuo5dQ4uz1BO9MHG19R6bBAwOAgBLW1CRFsq4eYqmqNtlGzAAtm01qx9bUfk6EWQKSDKtkty14XnuQ==',0,'E','2019-06-30 00:52:06.426','2019-06-30 00:52:12.533'),(108,'10',4,'BTC','mtCFYgmvGfLhcHuHXwvj7QTx7hPqp1nDJh','pvUyblsbOECRtkIplVs3Sk/Er74UaqWIfm37/ihCj44+Km5sQHPrMVAZ2s1t3nWZLYWiwu++PfMgNEfCiCwQIA==',0,'E','2019-07-01 17:32:06.670','2019-07-01 17:32:10.720'),(109,'10',4,'BTC_TOKEN','mu7PBYbp58c9FT9y3WwQFyaz4MkoTLHX8n','ChW+gG5D+mMBNepX/9CfgitZrWxRFAH9huVwkfIT0QkrCHA581QEwRu5y2bqCkXcgCKeo08aOOvxEY6K1Amkdg==',0,'E','2019-07-01 17:32:06.687','2019-07-01 17:32:11.255');

/*Table structure for table `w_user_wallet_bill` */

DROP TABLE IF EXISTS `w_user_wallet_bill`;

CREATE TABLE `w_user_wallet_bill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `platform` varchar(2) NOT NULL DEFAULT '10' COMMENT '默认角色',
  `order_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `direction` int(11) NOT NULL DEFAULT '1' COMMENT '1:进账，-1出账',
  `coin_type` varchar(100) NOT NULL COMMENT '币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN',
  `token` varchar(100) NOT NULL COMMENT '币种  BTC、USDT、EOS、ETH等',
  `contract` varchar(100) NOT NULL DEFAULT '' COMMENT '合约地址USDT=31',
  `sender_address` varchar(100) NOT NULL COMMENT '发送地址',
  `receiver_address` varchar(100) NOT NULL COMMENT '接收者地址',
  `amount` decimal(18,8) NOT NULL COMMENT '对应coin转账数量',
  `block` bigint(20) NOT NULL DEFAULT '0' COMMENT '打包区块',
  `tx` varchar(200) NOT NULL COMMENT '交易hash',
  `trade_step` char(2) NOT NULL COMMENT '交易步骤： 10待审核 20待转账 25在转账 30已发布 40确认中 50确认完成',
  `operation_type` varchar(100) NOT NULL DEFAULT '10' COMMENT '操作类型',
  `transfer_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '转移状态：0：初始化，1：待归集，2已转移，3不用处理',
  `flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uidx_tx` (`tx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户钱包账单';

/*Data for the table `w_user_wallet_bill` */

insert  into `w_user_wallet_bill`(`id`,`user_id`,`platform`,`order_id`,`direction`,`coin_type`,`token`,`contract`,`sender_address`,`receiver_address`,`amount`,`block`,`tx`,`trade_step`,`operation_type`,`transfer_flag`,`flag`,`remark`,`create_time`,`update_time`) values (5,2,'10','1907010431198113349030903808',1,'BTC_TOKEN','USDT','1','mpB8hFejmyB5CxpgW8413QvaQWyiuEc2od','myt1mnQ3EaDZFj3KL18KH8ArtaRC3d6DzP','0.00000001',1566547,'7d24a765e81a111d05bfcbc8e39658b6762699a6a1287144081f5e051fe1d7bb','50','10',2,1,'','2019-07-01 16:31:43.191','2019-07-01 16:31:53.356'),(6,2,'10','1907010431198113363312508928',1,'BTC_TOKEN','USDT','1','mpB8hFejmyB5CxpgW8413QvaQWyiuEc2od','myt1mnQ3EaDZFj3KL18KH8ArtaRC3d6DzP','0.00000001',1566587,'4ef3b682e2a1ed21fe1a35b8d38e43943665d3d9d3786498757377f098fc1e3c','50','10',2,1,'','2019-07-01 16:31:46.588','2019-07-01 16:31:55.542'),(7,2,'10','1907010521198125926511280128',1,'BTC','BTC','','','mgnYcJLbRAPc163x9dWxSs3KTRZhLn9P6T','0.00001000',1566587,'12a459f4685c074720012a28baa805eed540b9113b9e3fdb01bb17ca137d44ed','50','10',2,1,'','2019-07-01 17:21:41.911','2019-07-01 17:21:53.527'),(8,4,'10','1907010547198132447924191232',1,'BTC','BTC','','','mtCFYgmvGfLhcHuHXwvj7QTx7hPqp1nDJh','0.00001200',1566922,'a4b57d72678a99faad738e711f83240e160a53396e2faff62ff2026a1ec4b7b4','50','10',2,1,'','2019-07-01 17:47:36.717','2019-07-01 17:56:12.691'),(9,4,'10','1907010547198132480576847872',1,'BTC_TOKEN','USDT','1','mpB8hFejmyB5CxpgW8413QvaQWyiuEc2od','mu7PBYbp58c9FT9y3WwQFyaz4MkoTLHX8n','0.00000012',1566922,'739cb5cac68ec1b03eacd301677edbd07e6cda342c5f4e8493bd1beb5e386135','50','10',2,1,'','2019-07-01 17:47:44.500','2019-07-01 17:56:13.177'),(10,4,'10','1907010616198139678606491648',1,'BTC','BTC','','','mtCFYgmvGfLhcHuHXwvj7QTx7hPqp1nDJh','0.00001200',1566929,'cbc8d9baab05fa8efc07e5cd2e3c0ab3c6c4f2db37271d9f00d7743c25e98a1f','40','10',0,0,'','2019-07-01 18:16:20.642','2019-07-01 18:16:20.642');

/*Table structure for table `w_withdraw_wallet_bill` */

DROP TABLE IF EXISTS `w_withdraw_wallet_bill`;

CREATE TABLE `w_withdraw_wallet_bill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `platform` varchar(2) NOT NULL DEFAULT '10' COMMENT '默认角色',
  `order_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `direction` int(11) NOT NULL DEFAULT '1' COMMENT '1:进账，-1出账',
  `coin_type` varchar(100) NOT NULL COMMENT '币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN',
  `token` varchar(100) NOT NULL COMMENT '币种  BTC、USDT、EOS、ETH等',
  `contract` varchar(100) NOT NULL DEFAULT '' COMMENT '合约地址USDT=31',
  `sender_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '发送地址',
  `receiver_address` varchar(100) NOT NULL COMMENT '接收者地址',
  `amount` decimal(18,8) NOT NULL COMMENT '对应coin转账数量',
  `block` bigint(20) NOT NULL DEFAULT '0' COMMENT '打包区块',
  `tx` varchar(200) NOT NULL COMMENT '交易hash',
  `trade_step` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '20' COMMENT '交易步骤： 10待审核 20待转账 25在转账 30已发布 40确认中 50确认完成',
  `operation_type` varchar(100) NOT NULL DEFAULT '10' COMMENT '操作类型',
  `transfer_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '转移状态：0：初始化，1：待归集，2已转移，3不用处理',
  `flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uidx_tx` (`tx`) USING BTREE,
  UNIQUE KEY `uidx_orderId` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='提币钱包账单';

/*Data for the table `w_withdraw_wallet_bill` */

insert  into `w_withdraw_wallet_bill`(`id`,`user_id`,`platform`,`order_id`,`direction`,`coin_type`,`token`,`contract`,`sender_address`,`receiver_address`,`amount`,`block`,`tx`,`trade_step`,`operation_type`,`transfer_flag`,`flag`,`remark`,`create_time`,`update_time`) values (5,1,'10','4',-1,'BTC','BTC','','mxxsTaEtJK6hQUUNCNxzmsAcnietiddptj','mtCFYgmvGfLhcHuHXwvj7QTx7hPqp1nDJh','0.00001200',1566922,'a4b57d72678a99faad738e711f83240e160a53396e2faff62ff2026a1ec4b7b4','50','10',3,0,'','2019-07-01 17:32:24.707','2019-07-01 17:56:05.146'),(6,1,'10','5',-1,'BTC_TOKEN','USDT','1','mpB8hFejmyB5CxpgW8413QvaQWyiuEc2od','mu7PBYbp58c9FT9y3WwQFyaz4MkoTLHX8n','0.00000012',1566922,'739cb5cac68ec1b03eacd301677edbd07e6cda342c5f4e8493bd1beb5e386135','50','10',3,0,'','2019-07-01 17:32:46.011','2019-07-01 17:56:05.355'),(7,1,'10','6',-1,'BTC','BTC','','mxxsTaEtJK6hQUUNCNxzmsAcnietiddptj','mtCFYgmvGfLhcHuHXwvj7QTx7hPqp1nDJh','0.00001200',1566929,'cbc8d9baab05fa8efc07e5cd2e3c0ab3c6c4f2db37271d9f00d7743c25e98a1f','40','10',3,0,'','2019-07-01 18:07:32.368','2019-07-01 18:16:20.134');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

```

###### 1、准备

```
   1.1、系统启动前配置表 w_coin_config
   1.2、启动钱包系统，项目在启动时会创建系统钱包w_config_wallet   程序入口InitConfigWalletRunner
```

######  2、创建钱包

```
   创建钱包流程：UserWalletController
   post请求：http://127.0.0.1:8080/inner/address/create

   参数： {"userId":4}
   
   响应：{
         "code": 0,
          "msg": "请求成功",
          "data": [
            {"userId": 4,
             "coinType": "BTC",
             "address": "mtCFYgmvGfLhcHuHXwvj7QTx7hPqp1nDJh"},
            {"userId": 4,
             "coinType": "BTC_TOKEN",
             "address": "mu7PBYbp58c9FT9y3WwQFyaz4MkoTLHX8n"}
           ]}
    
    示例：curl -X POST http://127.0.0.1:8080/inner/address/create -H 'Content-Type:application/json' -d '{"userId":4}'
```

###### 3、提现

```
   提现流程：下单->提币->查询打包块->确认高度->同步到用户资金表【TODO】  WithdrawWalletBillController->WithdrawJob->QueryPa ckageJob-> ConfirmJob

   post请求：http://127.0.0.1:8080/inner/order/withdraw
   参数：{
    "userId":1,//提币用户
	"orderId":5,//订单号唯一
	"coinType":"BTC",//代币大类（USDT这个值传BTC_TOKEN）
	"token":"BTC",//具体币种 （USDT这个值传USDT） 
	"amount":"0.000012",//提币数量
	"receiverAddress":"mtCFYgmvGfLhcHuHXwvj7QTx7hPqp1nDJh"//接收地址
     }
   响应：{"code": 0,"msg": "请求成功","data": null} 
   示例：curl -X POST http://127.0.0.1:8080/inner/order/withdraw -H 'Content-Type: application/json' \
     -d '{
	"userId":1,
	"orderId":6,
	"coinType":"BTC",
	"token":"BTC",
	"amount":"0.000012",
	"receiverAddress":"mtCFYgmvGfLhcHuHXwvj7QTx7hPqp1nDJh"
         }'    
```

###### 3、充币
```
   充币流程：扫数据->确认高度->归集/同步到用户资金表【TODO】    RechargeBillScanJob -> ConfirmJob -> CollectionJob
```

###### 3、备注【充提币同步到资金表需要业务模块做，钱包模块不参与】
```
   充币同步到资金表:
    1、查询可以同步的数据：select *  from w_user_wallet_bill  where direction=1 and flag=1 ;
    2、执行具体的同步操作；
    3、修改状态： update w_user_wallet_bill set flag=2 where id = xx;

   提币同步到资金表:
    1、查询可以同步的数据：select *  from w_withdraw_wallet_bill where direction=-1 and flag=1 ;
    2、执行具体的同步操作；
    3、修改状态： update w_withdraw_wallet_bill set flag=2 where id = xx;

```