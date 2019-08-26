package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.Config;
import com.jdcloud.provider.mapper.AcceptDealerMapper;
import com.jdcloud.provider.pojo.AcceptDealer;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.service.AcceptDealerService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author helen
 * @since 2019-01-04
 */
@Service
public class AcceptDealerServiceImpl extends ServiceImpl<AcceptDealerMapper, AcceptDealer> implements AcceptDealerService {

	/**
	 * 承兑商列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 18:27
	 */
	@Override
	public Page<AcceptDealer> selectAcceptDealerList(Page<AcceptDealer> page, Config config) {
		page.setRecords( baseMapper.selectAcceptDealerList( page, config ) );
		return page;
	}

	/**
	 * 保存或更新<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 19:38
	 */
	@Override
	public Wrapper<String> saveOrUpdate(AcceptDealer acceptDealerDto, SysUser user) {
		AcceptDealer acceptDealer = new AcceptDealer();
		BeanUtils.copyProperties( acceptDealerDto, acceptDealer );
		acceptDealer.setUpdater( user.getUserName() );
		acceptDealer.setUpdateTime( new Date() );
		if (acceptDealer.getId() == null) {
			acceptDealer.setCreater( user.getUserName() );
			acceptDealer.setCreateTime( new Date() );
		}

		boolean flag = this.insertOrUpdate( acceptDealer );
		if (!flag) {
			return WrapMapper.error( "保存或更新失败！" );
		}
		return WrapMapper.ok();
	}

	/**
	 * 删除<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 19:36
	 */
	@Override
	public Wrapper<String> deleteByIds(String ids) {
		Long[] idArray = ConvertUtil.toLongArray( ids );
		// 删除数据
		Integer count = baseMapper.deleteBatchIds( Arrays.asList( idArray ) );
		if (count == 0) {
			return WrapMapper.error( "删除失败" );
		}
		return WrapMapper.ok( "删除成功" );
	}
}
