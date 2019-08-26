package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.Config;
import com.jdcloud.provider.pojo.AcceptDealer;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author helen
 * @since 2019-01-04
 */
public interface AcceptDealerService extends IService<AcceptDealer> {

	/**
	 * 承兑商列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 18:27
	 */
	Page<AcceptDealer> selectAcceptDealerList(Page<AcceptDealer> page, Config config);

	/**
	 * 保存或更新
	 * 
	 * @param acceptDealerDto
	 * @param user
	 * @return
	 */
	Wrapper<String> saveOrUpdate(AcceptDealer acceptDealerDto, SysUser user);

	/**
	 * 删除<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 19:36
	 */
	Wrapper<String> deleteByIds(String ids);
}
