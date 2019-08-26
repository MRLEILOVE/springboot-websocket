package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.AppVersionDto;
import com.jdcloud.provider.pojo.AppVersion;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yongheng
 * @since 2018-12-21
 */
public interface AppVersionService extends IService<AppVersion> {

	/**
	 * 版本管理列表<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/21 16:51
	 */
	Page<AppVersion> selectAppVersionList(Page<AppVersion> page, AppVersionDto appVersionDto);

	/**
	 * 保存或更新<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/21 17:23
	 */
	Wrapper<String> saveOrUpdate(AppVersionDto appVersionDto, SysUser user);
}
