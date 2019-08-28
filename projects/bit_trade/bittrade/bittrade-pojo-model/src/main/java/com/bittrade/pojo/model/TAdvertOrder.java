package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_advert_order")
public class TAdvertOrder extends BaseModel<TAdvertOrder> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 取消订单时间限制，默认下单 3 分钟内可取消订单
	 */
	public static final long CANCEL_ORDER_DURATION = 3;
	
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
		 * 取消者ID（仅取消状态需填）
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
		 * 状态（1，已拍下；2，已付款；3，已收款；5，已完成；6，已取消，7，超时关闭）
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
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	
	/**
	 * 广告信息id
	 */
	private Long advertId;
	
	/**
	 * 法币币种id
	 */
	private Long coinId;
	
	/**
	 * 订单号
	 */
	private String orderNumber;
	
	/**
	 * 付款法币(CNY)
	 */
	private String paymentLegalCurrency;
	
	/**
	 * 广告类别 1出售 2购买
	 */
	private Integer advertType;
	
	/**
	 * 广告留言
	 */
	private String advertMessage;

	/**
	 * 购买者ID
	 */
	private Long buyerId;
	
	/**
	 * 出售者ID
	 */
	private Long sellerId;
	
	/**
	 * 发布者ID
	 */
	private Long publisherId;
	
	/**
	 * 取消者ID（仅取消状态需填）
	 */
	private Long cancellerId;
	
	/**
	 * 交易金额(CNY)
	 */
	private java.math.BigDecimal transactionAmout;
	
	/**
	 * 交易数量
	 */
	private java.math.BigDecimal transactionNum;
	
	/**
	 * 交易价格(CNY)
	 */
	private java.math.BigDecimal transactionPrice;
	
	/**
	 * 续费率(小数 0.01 = 1%)
	 */
	private java.math.BigDecimal rate;
	
	/**
	 * 手续费
	 */
	private java.math.BigDecimal charge;
	
	/**
	 * 状态（1，已拍下；2，已付款；3，已收款；5，已完成；6，已取消，7，超时关闭）
	 */
	private Integer status;
	
	/**
	 * 取消订单截止时间（默认为 点击 购买/出售 后 3 分钟）
	 */
	private java.time.LocalDateTime cancelOrderDeadline;
	
	/**
	 * 仲裁状态：0，未仲裁；1，已仲裁；
	 */
	private Integer arbitStatus;
	
	/**
	 * 仲裁结果
	 */
	private String arbitResult;
	
	/**
	 * 付款时间
	 */
	private java.time.LocalDateTime paymentTime;
	
	/**
	 * 放币时间
	 */
	private java.time.LocalDateTime grantCoinTime;
	
	/**
	 * 过期时间
	 */
	private java.time.LocalDateTime overdueTime;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 修改时间
	 */
	private java.time.LocalDateTime updateTime;


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
			for (TAdvertOrder.AdvertTypeEnum advertTypeEnum : TAdvertOrder.AdvertTypeEnum.values()) {
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
			for (TAdvertOrder.ArbitStatusEnum arbitStatusEnum : TAdvertOrder.ArbitStatusEnum.values()) {
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
			for (TAdvertOrder.StatusEnum statusEnum : TAdvertOrder.StatusEnum.values()) {
				if (statusEnum.code.equals(status)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 驗證是否已付款
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/19 17:24
	 */
	public boolean isAlreadyPaid(Integer status) {
		return Objects.equals(StatusEnum.ALREADY_PAID.getCode(), status);
	}

	/**
	 * 驗證是否已完成
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/19 17:24
	 */
	public boolean isAlreadyComplete(Integer status) {
		return Objects.equals(StatusEnum.ALREADY_COMPLETE.getCode(), status);
	}

	/**
	 * 驗證是否已取消
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/19 17:24
	 */
	public boolean isAlreadyCancel(Integer status) {
		return Objects.equals(StatusEnum.ALREADY_CANCEL.getCode(), status);
	}
}
