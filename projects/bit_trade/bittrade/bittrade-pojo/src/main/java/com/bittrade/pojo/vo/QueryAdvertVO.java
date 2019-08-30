package com.bittrade.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.core.common.annotation.CheckEnumValue;

import lombok.Data;

/**
 * 查询购买广告列表条件
 * <br/>
 *
 * @author ：leigq
 * @date ：2019/8/20 19:40
 */
@Data
public class QueryAdvertVO implements Serializable {

	private static final long serialVersionUID = 9129284783172886611L;

	/**
	 * 法币虚拟币id
	 */
	@NotNull(message = "coinId cannot be null")
	private Long coinId;

	/**
	 * 仅与认证商家交易
	 * <br/>
	 * 弃用，项目暂时没有商家认证
	 */
	@Deprecated
	private Boolean onlyTransactionWithAuthBusiness = false;

	/**
	 * 广告类型 类型 1:出售 2:购买
	 */
	@NotNull(message = "AdvertType cannot be null")
	@CheckEnumValue(enumClass = TAdvertInfoDTO.AdvertTypeEnum.class, enumMethod = "isValidAdvertType")
	private Integer AdvertType;

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

	/**
	 * 广告类型是否为购买
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isBuyType() {
		return Objects.equals(TAdvertInfoDTO.AdvertTypeEnum.BUY.getCode(), this.AdvertType);
	}

	/**
	 * 广告类型是否为出售
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isSellType() {
		return Objects.equals(TAdvertInfoDTO.AdvertTypeEnum.SELL.getCode(), this.AdvertType);
	}

}
