package com.bittrade.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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

	private static final long serialVersionUID = -565572835743587398L;

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
	 * 取消订单时间限制，默认下单 3 分钟内可取消订单
	 */
	public static final long CANCEL_ORDER_DURATION = 3;

	/**
	 * 币名称
	 */
	@TableField(exist = false)
	private String coinName;

	/**
	 * 收款方式id，出售单为收款方式, 购买单为付款方式
	 */
	@TableField(exist = false)
	private Long paymentMethodId;

	/**
	 *
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {

		/**
		 * 主键
		 */
		public static final String ID = "id";

		/**
		 * 广告信息id
		 */
		public static final String ADVERT_ID = "advert_id";

		/**
		 * 法币币种id
		 */
		public static final String COIN_ID = "coin_id";

		/**
		 * 订单号
		 */
		public static final String ORDER_NUMBER = "order_number";

		/**
		 * 付款法币(CNY)
		 */
		public static final String PAYMENT_LEGAL_CURRENCY = "payment_legal_currency";

		/**
		 * 广告类别 1出售 2购买
		 */
		public static final String ADVERT_TYPE = "advert_type";

		/**
		 * 广告留言
		 */
		public static final String ADVERT_MESSAGE = "advert_message";

		/**
		 * 购买者ID
		 */
		public static final String BUYER_ID = "buyer_id";

		/**
		 * 出售者ID
		 */
		public static final String SELLER_ID = "seller_id";

		/**
		 * 发布者ID
		 */
		public static final String PUBLISHER_ID = "publisher_id";

		/**
		 * 取消者ID,系统超时取消填0（仅取消状态需填）
		 */
		public static final String CANCELLER_ID = "canceller_id";

		/**
		 * 交易金额(CNY)
		 */
		public static final String TRANSACTION_AMOUT = "transaction_amout";

		/**
		 * 交易数量
		 */
		public static final String TRANSACTION_NUM = "transaction_num";

		/**
		 * 交易价格(CNY)
		 */
		public static final String TRANSACTION_PRICE = "transaction_price";

		/**
		 * 续费率(小数 0.01 = 1%)
		 */
		public static final String RATE = "rate";

		/**
		 * 手续费
		 */
		public static final String CHARGE = "charge";

		/**
		 * 状态（0,未操作; 1，已拍下；2，已付款；3，已收款；5，已完成；6，已取消，7，超时关闭）
		 */
		public static final String STATUS = "status";

		/**
		 * 取消订单截止时间（默认为 点击 购买/出售 后 3 分钟）
		 */
		public static final String CANCEL_ORDER_DEADLINE = "cancel_order_deadline";

		/**
		 * 仲裁状态：0，未仲裁；1，已仲裁；
		 */
		public static final String ARBIT_STATUS = "arbit_status";

		/**
		 * 仲裁结果
		 */
		public static final String ARBIT_RESULT = "arbit_result";

		/**
		 * 付款时间
		 */
		public static final String PAYMENT_TIME = "payment_time";

		/**
		 * 放币时间
		 */
		public static final String GRANT_COIN_TIME = "grant_coin_time";

		/**
		 * 过期时间
		 */
		public static final String OVERDUE_TIME = "overdue_time";

		/**
		 * 备注
		 */
		public static final String REMARK = "remark";

		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";

		/**
		 * 修改时间
		 */
		public static final String UPDATE_TIME = "update_time";

	};

	/**
	 * 广告类型 <br/>
	 *
	 * @author ：leigq
	 * @date ：2019/8/19 17:58
	 */
	@AllArgsConstructor
	public enum AdvertTypeEnum {
		SELL(1, "出售"), BUY(2, "购买"),;

		@Getter
		private Integer code;

		@Getter
		private String describe;

		/**
		 * 验证广告类型 <br/>
		 * create by: leigq <br/>
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
	 * 仲裁状态 <br/>
	 *
	 * @author ：leigq
	 * @date ：2019/8/19 17:58
	 */
	@AllArgsConstructor
	public enum ArbitStatusEnum {
		NO_ARBITRATION(0, "未仲裁"), ARBITRATED(1, "已仲裁"),;

		@Getter
		private Integer code;

		@Getter
		private String describe;

		/**
		 * 验证仲裁状态 <br/>
		 * create by: leigq <br/>
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
	 * 订单状态 <br/>
	 *
	 * @author ：leigq
	 * @date ：2019/8/19 17:58
	 */
	@AllArgsConstructor
	public enum StatusEnum {
		ALREADY_AUCTION(1, "已拍下"), ALREADY_PAID(2, "已付款"), ALREADY_RECEIPT(3, "已收款"), ALREADY_COMPLETE(5, "已完成"),
		ALREADY_CANCEL(6, "已取消"), TIMEOUT_OFF(7, "超时关闭"),;

		@Getter
		private Integer code;

		@Getter
		private String describe;

		/**
		 * 验证订单状态 <br/>
		 * create by: leigq <br/>
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

}
