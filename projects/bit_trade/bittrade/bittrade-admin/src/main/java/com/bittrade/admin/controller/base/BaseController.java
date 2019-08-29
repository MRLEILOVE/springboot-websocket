package com.bittrade.admin.controller.base;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.admin.enums.UserEnum.UserSex;
import com.bittrade.admin.model.dto.BaseQuery;
import com.bittrade.admin.util.ServletUtil;
import com.bittrade.admin.util.ShiroUtil;
import com.bittrade.admin.wrapper.WrapMapper;
import com.bittrade.admin.wrapper.Wrapper;
import com.bittrade.pojo.dto.SysUserDTO;
import com.core.common.constant.GlobalConstant.PageAug;
import com.core.tool.DateTimeUtils;

public class BaseController {

	/**
	 * 传递的日期格式的字符串，转化为Date类型
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		dateFormat.setLenient( false );
		binder.registerCustomEditor( Date.class, new CustomDateEditor( dateFormat, true ) );
	}

	/**
	 * 性别
	 * 
	 * @param sex
	 * @return
	 */
	public String getSex(String sex) {
		if (StringUtils.isEmpty( sex )) {
			return UserSex.WOMAN.getValue();
		}
		return sex .equals(UserSex.WOMAN.getCode() + "") ? UserSex.WOMAN.getValue() : UserSex.MAN.getValue();
	}

	/**
	 * .获取page对象
	 * 
	 * @return
	 */
	public <T> Page<T> getPage() {
		ServletUtil.getParameter( PageAug.IS_ASC );
		Page<T> page = new Page<>( ServletUtil.getParameterToInt( PageAug.PAGE_NUM ), ServletUtil.getParameterToInt( PageAug.PAGE_SIZE ) );
//		String orderBy = ServletUtil.getParameter( PageAug.ORDER_BY_COLUMN );
//		page.setOrderByField( getOrderByField( orderBy ) );
//		page.setAsc( ServletUtil.getParameter( PageAug.IS_ASC ).equals( "desc" ) ? false : true );
		return page;
	}

	/**
	 * .转换排序字段
	 * 
	 * @param oriSortField
	 * @return
	 */
	private String getOrderByField(String oriSortField) {

		if ("createtime".equalsIgnoreCase( oriSortField )) {
			return "create_time";
		}
		if ("rolesort".equalsIgnoreCase( oriSortField )) {
			return "role_sort";
		}
		if ("ordernum".equalsIgnoreCase( oriSortField )) {
			return "order_num";
		}
		if ("dictsort".equalsIgnoreCase( oriSortField )) {
			return "dict_sort";
		}
		if ("opertime".equalsIgnoreCase( oriSortField )) {
			return "oper_time";
		}
		if ("logintime".equalsIgnoreCase( oriSortField )) {
			return "login_time";
		}
		if ("lastAccessTime".equalsIgnoreCase( oriSortField )) {
			return "last_access_time";
		}
		if ("startTimestamp".equalsIgnoreCase( oriSortField )) {
			return "start_timestamp";
		}
		return oriSortField;
	}

	public <T extends BaseQuery> void paramsHandle(T t) {
		String beginTime = t.getParams().get( "beginTime" );
		String endTime = t.getParams().get( "endTime" );
		t.setBeginTime( StringUtils.isBlank( beginTime ) ? null : DateTimeUtils.stringToDate( beginTime, "yyyy-MM-dd" ) );
		if (StringUtils.isNotBlank( endTime )) {
			Calendar c = Calendar.getInstance();
			c.setTime( DateTimeUtils.stringToDate( endTime, "yyyy-MM-dd" ) );
			c.set( HOUR_OF_DAY, 23 );
			c.set( MINUTE, 59 );
			c.set( SECOND, 59 );
			t.setCloseTime( c.getTime() );
		}
		t.setSize( this.getPage().getSize() );
		t.setCurrent( this.getPage().getCurrent() );
	}

	/**
	 * 响应返回结果
	 * 
	 * @param rows
	 * @return 操作结果
	 */
	protected Wrapper<String> toAjax(int rows) {
		return rows > 0 ? success() : error();
	}

	public Wrapper<String> success() {
		return WrapMapper.ok( "操作成功" );
	}

	public Wrapper<String> error() {
		return WrapMapper.error( "操作失败" );
	}

	public Wrapper<String> success(String message) {
		return WrapMapper.ok( message );
	}

	public Wrapper<String> error(String message) {
		return WrapMapper.error( message );
	}

	public Wrapper<String> error(int code, String message) {
		return WrapMapper.wrap( code, message );
	}

	public SysUserDTO getUser() {
		return ShiroUtil.getUser();
	}

	public void setUser(SysUserDTO userDTO) {
		ShiroUtil.setUser( userDTO );
	}

	public Integer getUserId() {
		return getUser().getUserId();
	}

	public String getLoginName() {
		return getUser().getLoginName();
	}

}
