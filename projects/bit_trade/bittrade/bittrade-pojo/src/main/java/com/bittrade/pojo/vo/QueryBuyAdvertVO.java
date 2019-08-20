package com.bittrade.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 查询购买广告列表条件
 * <br/>
 *
 * @author ：leigq
 * @date ：2019/8/20 19:40
 */
@Data
public class QueryBuyAdvertVO implements Serializable {

	private static final long serialVersionUID = 9129284783172886611L;

	/**
	 * 法币虚拟币id
	 */
	private Long coinId;

	/**
	 * 仅与认证商家交易
	 * <br/>
	 * 弃用，项目暂时没有商家认证
	 */
	@Deprecated
	private Boolean onlyTransactionWithAuthBusiness = false;

	/**
	 * 仅展示可交易
	 */
	private Boolean onlyShowCanTransaction = false;

	/**
	 * 最小目标金额
	 */
	private BigDecimal minTargetAmount;

	/**
	 * 收款方式id
	 */
	private Long receiptWay;

}
