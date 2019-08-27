package com.bittrade.admin.service.impl.sys;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.admin.dao.sys.ISysUserOnlineDAO;
import com.bittrade.admin.model.domain.SysUserOnline;
import com.bittrade.admin.util.DateUtils;

/**
 * <p>
 * 在线用户记录 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Component
public class SysUserOnlineServiceImpl {

	@Autowired
	private ISysUserOnlineDAO userOnlineDao;

	/**
	 * 通过会话序号查询信息
	 * 
	 * @param sessionId
	 * @return 在线用户信息
	 */
	public SysUserOnline selectOnlineById(String sessionId) {
		return userOnlineDao.selectOnlineById( sessionId );
	}

	/**
	 * 通过会话序号删除信息
	 * 
	 * @param sessionId
	 * @return 在线用户信息
	 */
	public void deleteOnlineById(String sessionId) {
		SysUserOnline userOnline = selectOnlineById( sessionId );
		if (null != userOnline) {
			userOnlineDao.deleteOnlineById( sessionId );
		}
	}

	/**
	 * 通过会话序号删除信息
	 * 
	 * @param sessions
	 * @return 在线用户信息
	 */
	public void batchDeleteOnline(List<String> sessions) {
		for (String sessionId : sessions) {
			SysUserOnline userOnline = selectOnlineById( sessionId );
			if (null != userOnline) {
				userOnlineDao.deleteOnlineById( sessionId );
			}
		}
	}

	/**
	 * 保存会话信息
	 * 
	 * @param online
	 */
	public void saveOnline(SysUserOnline online) {
		userOnlineDao.saveOnline( online );
	}

	/**
	 * 查询会话集合
	 * 
	 * @param pageUtilEntity
	 */
	public Page<SysUserOnline> selectUserOnlineList(Page<SysUserOnline> page, SysUserOnline userOnline) {
		page.setRecords( userOnlineDao.selectUserOnlineList( page, userOnline ) );
		return page;
	}

	/**
	 * 强退用户
	 * 
	 * @param sessionId
	 */
	public void forceLogout(String sessionId) {
		userOnlineDao.deleteOnlineById( sessionId );
	}

	/**
	 * 查询会话集合
	 * 
	 * @param online
	 */
	public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
		String lastAccessTime = DateUtils.parseDateToStr( DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate );
		return userOnlineDao.selectOnlineByExpired( lastAccessTime );
	}

}