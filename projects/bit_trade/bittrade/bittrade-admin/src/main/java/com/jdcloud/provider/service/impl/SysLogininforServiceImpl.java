package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.mapper.SysLogininforMapper;
import com.jdcloud.provider.pojo.SysLogininfor;
import com.jdcloud.provider.service.SysLogininforService;
import com.jdcloud.util.Convert;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements SysLogininforService {

	@Override
	public void insertLogininfor(SysLogininfor logininfor) {
		baseMapper.insertLogininfor( logininfor );
	}

	@Override
	public Page<SysLogininfor> selectLogininforList(Page<SysLogininfor> page, SysLogininfor logininfor) {
		page.setRecords( baseMapper.selectLogininforList(page, logininfor ) );
		return page;
	}

	@Override
	public int deleteLogininforByIds(String ids) {
		return baseMapper.deleteLogininforByIds( ConvertUtil.toStrArray( ids ) );
	}

	@Override
	public void cleanLogininfor() {
		baseMapper.cleanLogininfor();
	}

}
