package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BannerDto;
import com.jdcloud.provider.pojo.Banner;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yongheng
 * @since 2018-12-07
 */
public interface BannerService extends IService<Banner> {

	/**
	 * banner列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/7 18:53
	 */
	Page<BannerDto> selectBannerList(BannerDto bannerDto);

	/**
	 * banner保存或更新<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/7 20:16
	 */
	Wrapper<String> saveOrUpdate(BannerDto bannerDto, SysUser user);
}
