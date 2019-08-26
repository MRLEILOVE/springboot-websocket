package com.jdcloud.provider.web.finance;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.Withdraw;
import com.jdcloud.provider.dto.WithdrawStatistics;
import com.jdcloud.provider.service.WithdrawOrderService;
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
@RequestMapping(value = "/finance/withdrawRecord")
public class WithdrawRecordController extends BaseController {

	private String					prefix	= "finance/withdrawRecord";

	@Autowired
	private WithdrawOrderService	withdrawOrderService;

	@RequiresPermissions("finance:withdrawRecord:view")
	@GetMapping()
	public String withdraw(ModelMap mmap) {
		WithdrawStatistics withdrawStatistics = withdrawOrderService.withdrawStatistics();
		mmap.put( "withdrawStatistics", withdrawStatistics );
		return prefix + "/withdrawRecord";
	}

	/**
	 * 提现记录列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 11:01
	 */
	@RequiresPermissions("finance:withdrawRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Withdraw withdraw) {
		this.paramsHandle( withdraw );
		Page<Withdraw> page = withdrawOrderService.selectWithdrawRecordList( withdraw );
		return new TableDataInfo( page.getRecords(), page.getTotal() );
	}

	/**
	 * 提现记录导出<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 18:52
	 */
	@RequiresPermissions("finance:withdrawRecord:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(Withdraw withdraw) {
		String beginTime = withdraw.getParams().get( "beginTime" );
		String endTime = withdraw.getParams().get( "endTime" );
		withdraw.setBeginTime( StringUtils.isBlank( beginTime ) ? null : DateTimeUtils.stringToDate( beginTime, "yyyy-MM-dd" ) );
		if (StringUtils.isNotBlank( endTime )) {
			Calendar c = Calendar.getInstance();
			c.setTime( DateTimeUtils.stringToDate( endTime, "yyyy-MM-dd" ) );
			c.set( HOUR_OF_DAY, 23 );
			c.set( MINUTE, 59 );
			c.set( SECOND, 59 );
			withdraw.setCloseTime( c.getTime() );
		}
		List<Withdraw> withdrawRecord = withdrawOrderService.selectWithdrawRecordExportList( withdraw );
		ExcelUtil<Withdraw> util = new ExcelUtil<>( Withdraw.class );
		return util.exportExcel( withdrawRecord, "withdrawRecord" );
	}
}