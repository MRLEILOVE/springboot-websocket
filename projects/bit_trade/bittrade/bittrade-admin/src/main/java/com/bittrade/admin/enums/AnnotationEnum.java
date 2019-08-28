package com.bittrade.admin.enums;

/**
 * .注解bean
 * 
 * @author who ?
 *
 */
public class AnnotationEnum {

	/**
	 * .业务操作类型
	 * 
	 * @author who ?
	 */
	public enum BusinessType {

		OTHER, // 其它

		INSERT, // 新增

		UPDATE, // 修改

		DELETE, // 删除

		GRANT, // 授权

		EXPORT, // 导出

		IMPORT, // 导入

		FORCE, // 强退

		GENCODE, // 生成代码

		CLEAN,// 清空

		RECHARGE,//充值

		PUTMONEY,//放币

		CANCELPUTMONEY,//取消放币
	}

}
