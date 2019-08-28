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
@TableName(value="t_user_authentication")
public class TUserAuthentication extends BaseModel<TUserAuthentication> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 用户认证表主键
		 */
		public static final String ID = "id";
		
		/**
		 * 用户ID
		 */
		public static final String USER_ID = "user_id";
		
		/**
		 * 用户真实姓名
		 */
		public static final String REAL_NAME = "real_name";
		
		/**
		 * 证件号码
		 */
		public static final String IDENTITY_NO = "identity_no";
		
		/**
		 * 证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证
		 */
		public static final String IDENTITY_TYPE = "identity_type";
		
		/**
		 * 证件正面照片
		 */
		public static final String IDENTITY_FRONT_URL = "identity_front_url";
		
		/**
		 * 证件正面照片七牛key
		 */
		public static final String IDENTITY_FRONT_KEY = "identity_front_key";
		
		/**
		 * 证件正面照片七牛hash
		 */
		public static final String IDENTITY_FRONT_HASH = "identity_front_hash";
		
		/**
		 * 证件背面照片
		 */
		public static final String IDENTITY_BACK_URL = "identity_back_url";
		
		/**
		 * 证件背面照片七牛key
		 */
		public static final String IDENTITY_BACK_KEY = "identity_back_key";
		
		/**
		 * 证件背面照片七牛hash
		 */
		public static final String IDENTITY_BACK_HASH = "identity_back_hash";
		
		/**
		 * 证件是否提交:1提交，0无效
		 */
		public static final String POST_REAL_VALIDATE = "post_real_validate";
		
		/**
		 * 证件审核：0未审核，1通过，2审核中
		 */
		public static final String FHAS_REAL_VALIDATE = "fhas_real_Validate";
		
		/**
		 * 银行卡号
		 */
		public static final String BANK_CARD_NO = "bank_card_no";
		
		/**
		 * 开户行
		 */
		public static final String BANK_ADDRESS = "bank_address";
		
		/**
		 * 银行卡正面
		 */
		public static final String BANK_CARD_URL = "bank_card_url";
		
		/**
		 * 
		 */
		public static final String BANK_CARD_KEY = "bank_card_key";
		
		/**
		 * 
		 */
		public static final String BANK_CARD_HASH = "bank_card_hash";
		
		/**
		 * 支付宝账号
		 */
		public static final String ALIPAY_NO = "alipay_no";
		
		/**
		 * 缺省状态
		 */
		public static final String STATUS = "status";
		
		/**
		 * 提交时间
		 */
		public static final String SUBMIT_TIME = "submit_time";
		
		/**
		 * 审核人
		 */
		public static final String AUDITOR = "auditor";
		
		/**
		 * 审核时间
		 */
		public static final String AUDIT_TIME = "audit_time";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 更新时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
	};
	
	/**
	 * 用户认证表主键
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 用户真实姓名
	 */
	private String realName;
	
	/**
	 * 证件号码
	 */
	private String identityNo;
	
	/**
	 * 证件类型:证件类型:1.身份证 2.军官证，3.护照 4.台湾居民通行证 5.港澳居民通行证
	 */
	private Byte identityType;
	
	/**
	 * 证件正面照片
	 */
	private String identityFrontUrl;
	
	/**
	 * 证件正面照片七牛key
	 */
	private String identityFrontKey;
	
	/**
	 * 证件正面照片七牛hash
	 */
	private String identityFrontHash;
	
	/**
	 * 证件背面照片
	 */
	private String identityBackUrl;
	
	/**
	 * 证件背面照片七牛key
	 */
	private String identityBackKey;
	
	/**
	 * 证件背面照片七牛hash
	 */
	private String identityBackHash;
	
	/**
	 * 证件是否提交:1提交，0无效
	 */
	private Byte postRealValidate;
	
	/**
	 * 证件审核：0未审核，1通过，2审核中
	 */
	private Byte fhasRealValidate;
	
	/**
	 * 银行卡号
	 */
	private String bankCardNo;
	
	/**
	 * 开户行
	 */
	private String bankAddress;
	
	/**
	 * 银行卡正面
	 */
	private String bankCardUrl;
	
	/**
	 * 
	 */
	private String bankCardKey;
	
	/**
	 * 
	 */
	private String bankCardHash;
	
	/**
	 * 支付宝账号
	 */
	private String alipayNo;
	
	/**
	 * 缺省状态
	 */
	private Byte status;
	
	/**
	 * 提交时间
	 */
	private java.time.LocalDateTime submitTime;
	
	/**
	 * 审核人
	 */
	private String auditor;
	
	/**
	 * 审核时间
	 */
	private java.time.LocalDateTime auditTime;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
}
