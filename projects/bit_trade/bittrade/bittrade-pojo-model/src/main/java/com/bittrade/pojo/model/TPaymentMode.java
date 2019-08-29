/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@TableName(value="t_payment_mode")
public class TPaymentMode extends BaseModel<TPaymentMode> {
	
	private static final long serialVersionUID = 1L;
	
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
		 * 用户id
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 类型 1；银行卡 2：支付宝 3：微信
		 */
		public static final String TYPE = "type";
		
		/**
		 * 真实姓名或昵称
		 */
		public static final String TRUE_NAME = "true_name";
		
		/**
		 * 帐号
		 */
		public static final String CARD = "card";
		
		/**
		 * 开户银行
		 */
		public static final String BANK_NAME = "bank_name";
		
		/**
		 * 开户支行
		 */
		public static final String BANK_BRANCH = "bank_branch";
		
		/**
		 * 二维码
		 */
		public static final String QR_CODE = "qr_code";
		
		/**
		 * 状态：0，禁用；1，启用；
		 */
		public static final String STATUS = "status";
		
		/**
		 * 展示设置 1：用于收款 2:用于付款 3：用于收付款
		 */
		public static final String DISPLAY_SETTING = "display_setting";
		
		/**
		 * 所属币种 如：人民币CNY
		 */
		public static final String BELONG_CURRENCY = "belong_currency";
		
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
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 类型 1；银行卡 2：支付宝 3：微信
	 */
	private Byte type;
	
	/**
	 * 真实姓名或昵称
	 */
	private String trueName;
	
	/**
	 * 帐号
	 */
	private String card;
	
	/**
	 * 开户银行
	 */
	private String bankName;
	
	/**
	 * 开户支行
	 */
	private String bankBranch;
	
	/**
	 * 二维码
	 */
	private String qrCode;
	
	/**
	 * 状态：0，禁用；1，启用；
	 */
	private Byte status;
	
	/**
	 * 展示设置 1：用于收款 2:用于付款 3：用于收付款
	 */
	private Byte displaySetting;
	
	/**
	 * 所属币种 如：人民币CNY
	 */
	private String belongCurrency;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 修改时间
	 */
	private java.time.LocalDateTime updateTime;
	
}
