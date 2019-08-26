package com.bittrade.admin.util;

import org.apache.commons.lang3.StringUtils;

/**
 * permission 工具类
 * 
 * @author ruoyi
 */
public class PermissionUtil {
	/**
	 * 权限错误消息提醒
	 * 
	 * @param errorMsg
	 *            错误信息
	 * @return
	 */
	public static String getMsg(String permissionsStr) {
		String permission = StringUtils.substringBetween( permissionsStr, "[", "]" );
		String msg = MessageUtil.message( "no.view.permission", permission );
		if (StringUtils.endsWithIgnoreCase( permission, PermissionConstants.ADD_PERMISSION )) {
			msg = MessageUtil.message( "no.create.permission", permission );
		} else if (StringUtils.endsWithIgnoreCase( permission, PermissionConstants.EDIT_PERMISSION )) {
			msg = MessageUtil.message( "no.update.permission", permission );
		} else if (StringUtils.endsWithIgnoreCase( permission, PermissionConstants.REMOVE_PERMISSION )) {
			msg = MessageUtil.message( "no.delete.permission", permission );
		} else if (StringUtils.endsWithIgnoreCase( permission, PermissionConstants.EXPORT_PERMISSION )) {
			msg = MessageUtil.message( "no.export.permission", permission );
		} else if (StringUtils.endsWithAny( permission, new String[] { PermissionConstants.VIEW_PERMISSION, PermissionConstants.LIST_PERMISSION } )) {
			msg = MessageUtil.message( "no.view.permission", permission );
		}
		return msg;
	}
	
	public static class PermissionConstants {
		/** 新增权限 */
		public static final String	ADD_PERMISSION		= "add";

		/** 修改权限 */
		public static final String	EDIT_PERMISSION		= "edit";

		/** 删除权限 */
		public static final String	REMOVE_PERMISSION	= "remove";

		/** 导出权限 */
		public static final String	EXPORT_PERMISSION	= "export";

		/** 显示权限 */
		public static final String	VIEW_PERMISSION		= "view";

		/** 查询权限 */
		public static final String	LIST_PERMISSION		= "list";
	}
}
