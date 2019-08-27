package com.bittrade.admin.service.impl.sys;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.admin.dao.sys.ISysOperLogDAO;
import com.bittrade.admin.model.domain.SysOperLog;
import com.bittrade.admin.service.sys.SysOperLogService;
import com.bittrade.admin.util.ConvertUtil;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<ISysOperLogDAO, SysOperLog> implements SysOperLogService {

	@Override
	public Page<SysOperLog> selectOperLogList(Page<SysOperLog> page, SysOperLog operLog) {
		page.setRecords( baseMapper.selectOperLogList( page, operLog ) );
		return page;
	}

	@Override
	public int deleteOperLogByIds(String ids) {
		return baseMapper.deleteOperLogByIds( ConvertUtil.toStrArray( ids ) );
	}

	@Override
	public SysOperLog selectOperLogById(Integer operId) {
		return baseMapper.selectOperLogById( operId );
	}

	@Override
	public void cleanOperLog() {
		baseMapper.cleanOperLog();
	}
}