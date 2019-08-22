package com.bittrade.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 登录用户广告 VO
 * <br/>
 *
 * @author ：leigq
 * @date ：2019/8/21 16:27
 */
@Data
@Accessors(chain = true)
public class AdvertUserVO implements Serializable {

	private static final long serialVersionUID = -4268000935381470040L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 法币名称
	 */
	private String coinName;

	/**
	 * 类型(1:出售 2:购买)
	 */
	private Integer type;

	/**
	 * 定价方式 1：固定价格 2：浮动价格
	 */
	private Integer pricingMode;

	/**
	 * 浮动比例 (小数 0.01 = 1%)
	 */
	private java.math.BigDecimal floatingRatio;

	/**
	 * 单价（CNY）
	 */
	private java.math.BigDecimal price;

	/**
	 * 隐藏价格
	 */
	private java.math.BigDecimal hidePrice;

	/**
	 * 最小限额（CNY）
	 */
	private java.math.BigDecimal minLimit;

	/**
	 * 最大限额（CNY）
	 */
	private java.math.BigDecimal maxLimit;

	/**
	 * 广告已交易数量
	 */
	private java.math.BigDecimal alreadyTransactionAmount;

	/**
	 * 广告剩余数量
	 */
	private java.math.BigDecimal balanceAmount;

	/**
	 * 广告进行中冻结数量
	 */
	private java.math.BigDecimal freezeAmount;

	/**
	 * 收款方式id，出售单为收款方式, 购买单为付款方式
	 */
	private Long paymentMethodId;

	/**
	 * 状态：1，进行中；2，已下架(暂停)；3，已撤销；
	 */
	private Integer status;


}
