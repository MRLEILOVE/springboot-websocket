package com.bittrade.pojo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.core.framework.base.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@TableName(value="t_advert_info")
public class TAdvertInfo extends BaseModel<TAdvertInfo> {

	private static final long serialVersionUID = 2252880516378768941L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 法币id
	 */
	private Long coinId;

	/**
	 * 类型 1:出售 2:购买
	 */
	private Integer type;

	/**
	 * 定价方式 1：固定价格 2：浮动价格
	 */
	private Integer pricingMode;

	/**
	 * 浮动比例
	 */
	private BigDecimal floatingRatio;

	/**
	 * 交易价格
	 */
	private BigDecimal price;

	/**
	 * 隐藏价格
	 */
	private BigDecimal hidePrice;

	/**
	 * 单笔最小限额
	 */
	private BigDecimal minLimit;

	/**
	 * 单笔最大限额
	 */
	private BigDecimal maxLimit;

	/**
	 * 广告已交易数量
	 */
	private BigDecimal alreadyTransactionAmount;

	/**
	 * 广告剩余数量
	 */
	private BigDecimal balanceAmount;

	/**
	 * 广告进行中冻结数量
	 */
	private BigDecimal freezeAmount;

	/**
	 * 收款方式id，出售单为收款方式, 购买单为付款方式,多个以逗号分隔
	 * <br/>
	 */
	private String paymentMethodId;

	/**
	 * 状态：1，进行中；2，已下架(暂停)；3，已撤销；
	 */
	private Integer status;

	/**
	 * 是否开启对手限制 (0 禁用 1 启用)
	 */
	private Integer openOpponentLimit;

	/**
	 * 对手限制-认证等级
	 */
	private Integer certificationLevel;

	/**
	 * 对手限制-注册时间
	 */
	private LocalDateTime registeredTime;

	/**
	 * 对手限制-付款时间
	 */
	private Integer paymentTime;

	/**
	 * 交易说明
	 */
	private String message;

	/**
	 * 版本号
	 */
	@Version
	private Long version;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;

	/*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓额外字段，不属于数据库↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/

	/**
	 * 币名称
	 */
	@TableField(exist = false)
	private String coinName;

	/**
	 * c2c已成交数量
	 */
	@TableField(exist = false)
	private Integer c2cAlreadyDealCount;

	/**
	 * c2c总成交数量
	 */
	@TableField(exist = false)
	private Integer c2cTotalCount;

	/**
	 * c2c成交率
	 */
	@TableField(exist = false)
	private BigDecimal c2cTurnoverRate;

	/**
	 * 付款失效或放币时效
	 */
	@TableField(exist = false)
	private Long paymentOrPutCoinAging;

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
	 * 定价方式
	 * <br/>
	 * @author     ：leigq
	 * @date       ：2019/8/19 20:24
	 */
	@AllArgsConstructor
	public enum PricingModeEnum {
		FIXED(1, "固定价格"),
		FLOAT(2, "浮动价格"),
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
		public static boolean isValidPricingMode(Integer pricingMode) {
			for (PricingModeEnum pricingModeEnum : PricingModeEnum.values()) {
				if (pricingModeEnum.code.equals(pricingMode)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 状态
	 * <br/>
	 * @author     ：leigq
	 * @date       ：2019/8/19 20:24
	 */
	@AllArgsConstructor
	public enum StatusEnum {
		PROCESSING(1, "进行中"),
		PAUSE(2, "已下架(暂停)"),
		revoked( 3, "已撤销"),
		;

		@Getter
		private Integer code;
		@Getter
		private String describe;

		public static boolean isValidStatus(Integer status) {
			for (StatusEnum statusEnum : StatusEnum.values()) {
				if (statusEnum.code.equals(status)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 认证等级
	 * <br/>
	 * @author     ：leigq
	 * @date       ：2019/8/19 20:24
	 */
	@AllArgsConstructor
	public enum CertificationLevel {
		FIRST_LEVEL(1, "一级"),
		SECONDARY(2, "二级"),
		THIRD_LEVEL(3, "三级"),
		;

		@Getter
		private Integer code;
		@Getter
		private String describe;

		public static boolean isValidCertificationLevel(Integer level) {
			for (CertificationLevel certificationLevel : CertificationLevel.values()) {
				if (certificationLevel.code.equals(level)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 付款时间
	 * <br/>
	 * @author     ：leigq
	 * @date       ：2019/8/19 20:24
	 */
	@AllArgsConstructor
	public enum PaymentTime {
		TEN_MINUTES(10, "十分钟"),
		FIFTEEN_MINUTES(15, "十五分钟"),
		TWENTY_MINUTES(20, "二十分钟"),
		;

		@Getter
		private Integer code;
		@Getter
		private String describe;

		public static boolean isValidPaymentTime(Integer time) {
			for (PaymentTime paymentTime : PaymentTime.values()) {
				if (paymentTime.code.equals(time)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 定价方式是否为浮动价格
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isFloatPricing() {
		return Objects.equals(PricingModeEnum.FLOAT.getCode(), this.pricingMode);
	}

	/**
	 * 广告类型是否为购买
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isBuyType() {
		return Objects.equals(AdvertTypeEnum.BUY.getCode(), this.type);
	}

	/**
	 * 广告类型是否为出售
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isSellType() {
		return Objects.equals(AdvertTypeEnum.SELL.getCode(), this.type);
	}

	/**
	 * 定价方式是否为浮动价格
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isFloatingPrice() {
		return Objects.equals(PricingModeEnum.FLOAT.getCode(), this.pricingMode);
	}
	
}
