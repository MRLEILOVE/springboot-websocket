package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.AppVersionDto;
import com.jdcloud.provider.mapper.AppVersionMapper;
import com.jdcloud.provider.pojo.AppVersion;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.service.AppVersionService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yongheng
 * @since 2018-12-21
 */
@Service
public class AppVersionServiceImpl extends ServiceImpl<AppVersionMapper, AppVersion> implements AppVersionService {

	/**
	 * 版本管理列表<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/21 16:51
	 */
	@Override
	public Page<AppVersion> selectAppVersionList(Page<AppVersion> page, AppVersionDto appVersionDto) {
		page.setRecords( baseMapper.selectAppVersionListById( page, appVersionDto ) );
		return page;
	}

	/**
	 * 保存或更新<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/21 17:23
	 */
	@Override
	public Wrapper<String> saveOrUpdate(AppVersionDto appVersionDto, SysUser user) {
		AppVersion appVersion = new AppVersion();
		BeanUtils.copyProperties( appVersionDto, appVersion );
		appVersion.setUpdator( user.getUserName() );
		appVersion.setUpdateTime( new Date() );
		if (appVersion.getId() == null) {
			appVersion.setCreater( user.getUserName() );
			appVersion.setCreateTime( new Date() );
		}

		boolean flag = this.insertOrUpdate( appVersion );
		if (!flag) {
			return WrapMapper.error( "保存失败！" );
		}
		return WrapMapper.ok();
	}
}
