package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.model.dto.UserAuthenticationDto;
import com.jdcloud.provider.pojo.AuditRecord;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yongheng
 * @since 2018-12-03
 */
public interface AuditRecordService extends IService<AuditRecord> {

	/**
	 * 保存记录<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/3 20:39
	 */
	void saveRecord(UserAuthenticationDto result, String remark);

	/**
	 * 根据userId查询审核记录<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/9 20:23
	 */
	AuditRecord selectByUserId(Long userId);
}
