package com.bittrade.admin.service.impl.sys;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.admin.dao.sys.ISysLogininforDAO;
import com.bittrade.admin.model.domain.SysLogininfor;
import com.bittrade.admin.service.sys.SysLogininforService;
import com.bittrade.admin.util.ConvertUtil;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<ISysLogininforDAO, SysLogininfor> implements SysLogininforService {

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
