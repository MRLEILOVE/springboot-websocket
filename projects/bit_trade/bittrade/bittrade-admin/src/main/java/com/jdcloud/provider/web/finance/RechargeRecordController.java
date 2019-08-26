package com.jdcloud.provider.web.finance;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.Recharge;
import com.jdcloud.provider.dto.RechargeStatistics;
import com.jdcloud.provider.service.RechargeOrderService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.*;

/**
 * <p>
 * 财务管理--充值记录
 * <p>
 *
 * @author yongheng
 * @since 2018/11/23
 */
@Controller
@RequestMapping(value = "/finance/rechargeRecord")
public class RechargeRecordController extends BaseController {

	private String					prefix	= "finance/rechargeRecord";

	@Autowired
	private RechargeOrderService	rechargeOrderService;

	@RequiresPermissions("finance:rechargeRecord:view")
	@GetMapping()
	public String recharge(ModelMap mmap) {
		RechargeStatistics rechargeStatistics = rechargeOrderService.rechargeStatistics();
		mmap.put( "rechargeStatistics", rechargeStatistics );
		return prefix + "/rechargeRecord";
	}

	/**
	 * 充值记录列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 11:01
	 */
	@RequiresPermissions("finance:rechargeRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Recharge recharge) {
		this.paramsHandle( recharge );
		Page<Recharge> page = rechargeOrderService.selectRechargeRecordList( recharge );
		return new TableDataInfo( page.getRecords(), page.getTotal() );
	}

	@RequiresPermissions("finance:rechargeRecord:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(Recharge recharge) {
		String beginTime = recharge.getParams().get( "beginTime" );
		String endTime = recharge.getParams().get( "endTime" );
		recharge.setBeginTime( StringUtils.isBlank( beginTime ) ? null : DateTimeUtils.stringToDate( beginTime, "yyyy-MM-dd" ) );
		if (StringUtils.isNotBlank( endTime )) {
			Calendar c = Calendar.getInstance();
			c.setTime( DateTimeUtils.stringToDate( endTime, "yyyy-MM-dd" ) );
			c.set( HOUR_OF_DAY, 23 );
			c.set( MINUTE, 59 );
			c.set( SECOND, 59 );
			recharge.setCloseTime( c.getTime() );
		}
		List<Recharge> rechargeRecord = rechargeOrderService.selectRechargeRecordExportList( recharge );
		ExcelUtil<Recharge> util = new ExcelUtil<>( Recharge.class );
		return util.exportExcel( rechargeRecord, "rechargeRecord" );
	}
}