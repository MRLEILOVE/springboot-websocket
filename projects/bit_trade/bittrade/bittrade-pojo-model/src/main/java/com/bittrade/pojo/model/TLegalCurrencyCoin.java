package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.framework.base.model.BaseModel;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * 
 * 法币币种表
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_legal_currency_coin")
public class TLegalCurrencyCoin extends BaseModel<TLegalCurrencyCoin> {
	
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
		 * 名称（英文）
		 */
		public static final String NAME = "name";
		
		/**
		 * 标题（中文）
		 */
		public static final String TITLE = "title";
		
		/**
		 * 图标
		 */
		public static final String IMG = "img";
		
		/**
		 * 状态(0 禁用 1 启用)
		 */
		public static final String STATUS = "status";
		
		/**
		 * 发布广告最小数量
		 */
		public static final String MIN_QUOTA = "min_quota";
		
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
	 * 名称（英文）
	 */
	private String name;
	
	/**
	 * 标题（中文）
	 */
	private String title;
	
	/**
	 * 图标
	 */
	private String img;
	
	/**
	 * 状态(0 禁用 1 启用)
	 */
	private Byte status;
	
	/**
	 * 发布广告最小数量
	 */
	private java.math.BigDecimal minQuota;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 修改时间
	 */
	private java.time.LocalDateTime updateTime;

	@AllArgsConstructor
	public enum Status{

		DISABLE(0, "禁用"),
		ENABLE(1, "启用"),
		;

		@Getter
		private Integer code;
		@Getter
		private String describe;

	}

	public boolean isDisable(Integer status) {
		return Objects.equals(Status.DISABLE.getCode(), status);
	}
	
}
