package com.bittrade.admin.shiro.web.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bittrade.admin.service.impl.sys.SysUserOnlineServiceImpl;
import com.bittrade.admin.shiro.constant.ShiroConstant;
import com.bittrade.admin.shiro.session.OnlineSession;
import com.bittrade.admin.util.SpringUtil;
import com.bittrade.pojo.model.SysUserOnline;

/**
 * 主要是在此如果会话的属性修改了 就标识下其修改了 然后方便 OnlineSessionDao同步
 * 
 * @author ruoyi
 */
public class OnlineWebSessionManager extends DefaultWebSessionManager {
	private static final Logger log = LoggerFactory.getLogger( OnlineWebSessionManager.class );

	@Override
	public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) throws InvalidSessionException {
		super.setAttribute( sessionKey, attributeKey, value );
		if (value != null && needMarkAttributeChanged( attributeKey )) {
			OnlineSession s = (OnlineSession) doGetSession( sessionKey );
			s.markAttributeChanged();
		}
	}

	private boolean needMarkAttributeChanged(Object attributeKey) {
		if (attributeKey == null) {
			return false;
		}
		String attributeKeyStr = attributeKey.toString();
		// 优化 flash属性没必要持久化
		if (attributeKeyStr.startsWith( "org.springframework" )) {
			return false;
		}
		if (attributeKeyStr.startsWith( "javax.servlet" )) {
			return false;
		}
		if (attributeKeyStr.equals( ShiroConstant.CURRENT_USERNAME )) {
			return false;
		}
		return true;
	}

	@Override
	public Object removeAttribute(SessionKey sessionKey, Object attributeKey) throws InvalidSessionException {
		Object removed = super.removeAttribute( sessionKey, attributeKey );
		if (removed != null) {
			OnlineSession s = (OnlineSession) doGetSession( sessionKey );
			s.markAttributeChanged();
		}

		return removed;
	}

	/**
	 * 验证session是否有效 用于删除过期session
	 */
	@Override
	public void validateSessions() {
		if (log.isInfoEnabled()) {
			log.info( "invalidation sessions..." );
		}

		int invalidCount = 0;

		int timeout = (int) this.getGlobalSessionTimeout();
		Date expiredDate = DateUtils.addMilliseconds( new Date(), 0 - timeout );
		SysUserOnlineServiceImpl userOnlineService = SpringUtil.getBean( SysUserOnlineServiceImpl.class );
		List<SysUserOnline> userOnlineList = userOnlineService.selectOnlineByExpired( expiredDate );
		// 批量过期删除
		List<String> needOfflineIdList = new ArrayList<String>();
		for (SysUserOnline userOnline : userOnlineList) {
			try {
				SessionKey key = new DefaultSessionKey( userOnline.getSessionId() );
				Session session = retrieveSession( key );
				if (session != null) {
					throw new InvalidSessionException();
				}
			} catch (InvalidSessionException e) {
				if (log.isDebugEnabled()) {
					boolean expired = (e instanceof ExpiredSessionException);
					String msg = "Invalidated session with id [" + userOnline.getSessionId() + "]" + (expired ? " (expired)" : " (stopped)");
					log.debug( msg );
				}
				invalidCount++;
				needOfflineIdList.add( userOnline.getSessionId() );
			}

		}
		if (needOfflineIdList.size() > 0) {
			try {
				userOnlineService.batchDeleteOnline( needOfflineIdList );
			} catch (Exception e) {
				log.error( "batch delete db session error.", e );
			}
		}

		if (log.isInfoEnabled()) {
			String msg = "Finished invalidation session.";
			if (invalidCount > 0) {
				msg += " [" + invalidCount + "] sessions were stopped.";
			} else {
				msg += " No sessions were stopped.";
			}
			log.info( msg );
		}

	}

	@Override
	protected Collection<Session> getActiveSessions() {
		throw new UnsupportedOperationException( "getActiveSessions method not supported" );
	}
}
