package com.bittrade.admin.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bittrade.admin.model.domain.SysUserOnline;

/**
 * <p>
 * 在线用户记录 Mapper 接口
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
public interface SysUserOnlineMapper extends BaseMapper<SysUserOnline> {

	/**
	 * 通过会话序号查询信息
	 * 
	 * @param sessionId
	 * @return 在线用户信息
	 */
	public SysUserOnline selectOnlineById(String sessionId);

	/**
	 * 通过会话序号删除信息
	 * 
	 * @param sessionId
	 * @return 在线用户信息
	 */
	public int deleteOnlineById(String sessionId);

	/**
	 * 保存会话信息
	 * 
	 * @param online
	 * @return 结果
	 */
	public int saveOnline(SysUserOnline online);

	/**
	 * 查询会话集合
	 * 
	 * @param userOnline
	 * @return 会话集合
	 */
	public List<SysUserOnline> selectUserOnlineList(IPage<SysUserOnline> page, @Param("userOnline") SysUserOnline userOnline);

	/**
	 * 查询过期会话集合
	 * 
	 * @param lastAccessTime
	 *            过期时间
	 * @return 会话集合
	 */
	public List<SysUserOnline> selectOnlineByExpired(String lastAccessTime);

}
