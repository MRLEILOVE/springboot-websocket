package com.bittrade.pojo.dto;

import com.core.framework.base.DTO.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TAdvertOrderDTO extends BaseDTO<TAdvertOrderDTO> {

	/**
	 * 取消订单时间限制，默认下单 3 分钟内可取消订单
	 */
	public static final long CANCEL_ORDER_DURATION = 3;

	/**
	 * 广告类型
	 * <br/>
	 *
	 * @author ：leigq
	 * @date ：2019/8/19 17:58
	 */
	@AllArgsConstructor
	public enum AdvertTypeEnum {
		SELL(1, "出售"),
		BUY(2, "购买"),
		;

		@Getter
		private Integer code;

		@Getter
		private String describe;

		/**
		 * 验证广告类型
		 * <br/>
		 * create by: leigq
		 * <br/>
		 * create time: 2019/8/19 17:24
		 */
		public static boolean isValidAdvertType(Integer advertType) {
			for (AdvertTypeEnum advertTypeEnum : AdvertTypeEnum.values()) {
				if (advertTypeEnum.code.equals(advertType)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 仲裁状态
	 * <br/>
	 *
	 * @author ：leigq
	 * @date ：2019/8/19 17:58
	 */
	@AllArgsConstructor
	public enum ArbitStatusEnum {
		NO_ARBITRATION(0, "未仲裁"),
		ARBITRATED(1, "已仲裁"),
		;

		@Getter
		private Integer code;

		@Getter
		private String describe;

		/**
		 * 验证仲裁状态
		 * <br/>
		 * create by: leigq
		 * <br/>
		 * create time: 2019/8/19 17:24
		 */
		public static boolean isValidArbitStatus(Integer arbitStatus) {
			for (ArbitStatusEnum arbitStatusEnum : ArbitStatusEnum.values()) {
				if (arbitStatusEnum.code.equals(arbitStatus)) {
					return true;
				}
			}
			return false;
		}
	}


	/**
	 * 订单状态
	 * <br/>
	 *
	 * @author ：leigq
	 * @date ：2019/8/19 17:58
	 */
	@AllArgsConstructor
	public enum StatusEnum {
		ALREADY_AUCTION(1, "已拍下"),
		ALREADY_PAID(2, "已付款"),
		ALREADY_RECEIPT(3, "已收款"),
		ALREADY_COMPLETE(5, "已完成"),
		ALREADY_CANCEL(6, "已取消"),
		TIMEOUT_OFF(7, "超时关闭"),
		;

		@Getter
		private Integer code;

		@Getter
		private String describe;

		/**
		 * 验证订单状态
		 * <br/>
		 * create by: leigq
		 * <br/>
		 * create time: 2019/8/19 17:24
		 */
		public static boolean isValidStatus(Integer status) {
			for (StatusEnum statusEnum : StatusEnum.values()) {
				if (statusEnum.code.equals(status)) {
					return true;
				}
			}
			return false;
		}
	}

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long advertId;
	private Long coinId;
	private String orderNumber;
	private String paymentLegalCurrency;
	private Byte advertType;
	private String advertMessage;
	private Long buyerId;
	private Long sellerId;
	private Long publisherId;
	private Long cancellerId;
	private java.math.BigDecimal transactionAmout;
	private java.math.BigDecimal transactionNum;
	private java.math.BigDecimal transactionPrice;
	private java.math.BigDecimal rate;
	private java.math.BigDecimal charge;
	private Byte status;
	private java.time.LocalDateTime cancelOrderDeadline;
	private Byte arbitStatus;
	private String arbitResult;
	private java.time.LocalDateTime paymentTime;
	private java.time.LocalDateTime grantCoinTime;
	private java.time.LocalDateTime overdueTime;
	private String remark;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;

	/**
	 * 币名称
	 */
	private String coinName;

	/**
	 * 收款方式id，出售单为收款方式, 购买单为付款方式
	 */
	private Long paymentMethodId;

}
