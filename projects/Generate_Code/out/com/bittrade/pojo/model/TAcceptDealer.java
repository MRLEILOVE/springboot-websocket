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
@TableName(value="t_accept_dealer")
public class TAcceptDealer extends BaseModel<TAcceptDealer> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
	 */
	public static final class FieldNames {
		
		/**
		 * 承兑商表id
		 */
		public static final String ID = "id";
		
		/**
		 * 承兑商名称
		 */
		public static final String ACCEPT_NAME = "accept_name";
		
		/**
		 * 承兑商业务id
		 */
		public static final String ACCEPT_BUSINESSID = "accept_businessId";
		
		/**
		 * 承兑商key（蓝鲸MD5key）
		 */
		public static final String ACCEPT_KEY = "accept_key";
		
		/**
		 * 承兑商充值url
		 */
		public static final String ACCEPT_RECHARGE_URL = "accept_recharge_url";
		
		/**
		 * 图标url
		 */
		public static final String ICON_URL = "icon_url";
		
		/**
		 * 排序
		 */
		public static final String SORT = "sort";
		
		/**
		 * 状态：0禁用，1启用
		 */
		public static final String STATUS = "status";
		
		/**
		 * （备用字段）
		 */
		public static final String TYPE = "type";
		
		/**
		 * 备注
		 */
		public static final String REMARK = "remark";
		
		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";
		
		/**
		 * 创建人
		 */
		public static final String CREATER = "creater";
		
		/**
		 * 更新时间
		 */
		public static final String UPDATE_TIME = "update_time";
		
		/**
		 * 修改时间
		 */
		public static final String UPDATER = "updater";
		
	};
	
	/**
	 * 承兑商表id
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;
	
	/**
	 * 承兑商名称
	 */
	private String acceptName;
	
	/**
	 * 承兑商业务id
	 */
	private String acceptBusinessId;
	
	/**
	 * 承兑商key（蓝鲸MD5key）
	 */
	private String acceptKey;
	
	/**
	 * 承兑商充值url
	 */
	private String acceptRechargeUrl;
	
	/**
	 * 图标url
	 */
	private String iconUrl;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 状态：0禁用，1启用
	 */
	private Byte status;
	
	/**
	 * （备用字段）
	 */
	private Byte type;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 创建人
	 */
	private String creater;
	
	/**
	 * 更新时间
	 */
	private java.time.LocalDateTime updateTime;
	
	/**
	 * 修改时间
	 */
	private String updater;
	
}
