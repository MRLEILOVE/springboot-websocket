package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.mapper.SysOperLogMapper;
import com.jdcloud.provider.pojo.SysOperLog;
import com.jdcloud.provider.service.SysOperLogService;
import com.jdcloud.util.Convert;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

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