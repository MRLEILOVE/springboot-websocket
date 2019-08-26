/*
package com.jdcloud.provider.web.audit;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.Withdraw;
import com.jdcloud.provider.pojo.WithdrawOrder;
import com.jdcloud.provider.service.WithdrawOrderService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

*/
/**
 * <p>
 * 提现审核
 * <p>
 *
 * @author yongheng
 * @since 2018/11/26
 *//*

@Controller
@RequestMapping(value = "/audit/withdraw")
public class WithdrawController extends BaseController {

	private String					prefix	= "audit/withdraw";

	@Autowired
	private WithdrawOrderService	withdrawOrderService;

	@RequiresPermissions("audit:withdraw:view")
	@GetMapping()
	public String withdraw() {
		return prefix + "/withdraw";
	}

	*/
/**
	 * 提现列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 19:56
	 *//*

	@RequiresPermissions("audit:withdraw:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Withdraw withdraw) {
		this.paramsHandle( withdraw );
		Page<Withdraw> page = withdrawOrderService.selectWithdrawOrderList( withdraw );
		return new TableDataInfo( page.getRecords(), page.getTotal() );
	}

	*/
/**
	 * 提现详情<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 19:56
	 *//*

	@RequiresPermissions("audit:withdraw:audit")
	@GetMapping("/detail/{orderNo}")
	public String detail(@PathVariable("orderNo") String orderNo, ModelMap mmap) {
		mmap.put( "detail", withdrawOrderService.selectWithdrawDetail( orderNo ) );
		return prefix + "/audit";
	}

	*/
/**
	 * 提现审核<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 19:56
	 *//*

	@RequiresPermissions("audit:withdraw:audit")
	@PostMapping("/audit")
	@ResponseBody
	public Wrapper<String> audit(Withdraw withdraw) {
		return withdrawOrderService.withdrawAudit( withdraw, this.getUser() );
	}

	@RequiresPermissions("audit:withdraw:edit")
	@GetMapping("/auditEdit/{orderNo}/{status}")
	public String audit(@PathVariable("orderNo") String orderNo, @PathVariable("status") String status, ModelMap mmap) {
		WithdrawOrder withdrawOrder = new WithdrawOrder();
		withdrawOrder.setWithdrawOrderId( orderNo );
		withdrawOrder.setStatus( Integer.valueOf( status ) );
		mmap.put( "withdrawOrder", withdrawOrder );
		return prefix + "/edit";
	}
}*/
