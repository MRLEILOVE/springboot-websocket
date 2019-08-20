package com.walletbiz.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class CoinRecordVo {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 默认角色
     */
    @ApiModelProperty("用户ID")
    private String platform;

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String orderId;

    /**
     * 1:进账，-1出账
     */
    @ApiParam("1:进账，-1出账")
    private Integer direction;

    /**
     * 币种大类:BTC系列、BTC_TOKEN、EOS系列、ETH、ETH_TOKEN
     */

    private String coinType;

    /**
     * 币种  BTC、USDT、EOS、ETH等
     */
    @ApiParam("币种  BTC、USDT、EOS、ETH等")
    private String token;

    /**
     * 合约地址USDT=31
     */
    private String contract;

    /**
     * 发送地址
     */

    private String senderAddress;

    /**
     * 接收者地址
     */
    @ApiParam("接收者地址")
    private String receiverAddress;

    /**
     * 对应coin转账数量
     */
    @ApiParam("对应coin转账数量,改成String类型，避免前端拿到科学记数法")
    private String amount;

    /**
     * 打包区块
     */
    private Long block;

    /**
     * 交易hash
     */
    private String tx;

    /**
     * 交易步骤： 10待审核 20待转账 25在转账 30已发布 40确认中 50确认完成
     */

    private String tradeStep;

    /**
     * 操作类型
     */

    private String operationType;

    /**
     * 转移状态：0：初始化，1：待归集，2已转移，3不用处理
     */

    private Boolean transferFlag;

    /**
     * 0：初始化，1：未同步到资金账户，2已同步到资金账户，3不用处理
     */
    private Integer flag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
