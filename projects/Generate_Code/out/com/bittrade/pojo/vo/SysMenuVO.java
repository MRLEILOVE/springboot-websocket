package com.bittrade.pojo.vo;

import com.core.framework.base.VO.BaseVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenuVO extends BaseVO<SysMenuVO> {

	private static final long serialVersionUID = 1L;

	private Integer menuId;
	private String menuName;
	private Integer parentId;
	private Integer orderNum;
	private String url;
	private String menuType;
	private String visible;
	private String perms;
	private String icon;
	private String createBy;
	private java.time.LocalDateTime createTime;
	private String updateBy;
	private java.time.LocalDateTime updateTime;
	private String remark;

}
