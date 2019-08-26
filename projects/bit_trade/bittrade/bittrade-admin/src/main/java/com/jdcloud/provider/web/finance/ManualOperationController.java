package com.jdcloud.provider.web.finance;

import com.jdcloud.provider.pojo.RechargeOrder;
import com.jdcloud.provider.pojo.WithdrawOrder;
import com.jdcloud.provider.service.FinanceSettingService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 设置控制器
 * <p>
 *
 * @author yongheng
 * @since 2018/11/29
 */
@Controller
@RequestMapping(value = "/finance/manualOperation")
public class ManualOperationController extends BaseController {

	private String					prefix	= "finance/manualOperation";

	@Autowired
	private FinanceSettingService	financeSettingService;

	@RequiresPermissions("finance:rechargeApply:view")
	@GetMapping("/rechargeApply")
	public String rechargeApplyPage() {
		return prefix + "/rechargeApply";
	}

	/**
	 * 人工充值申请<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@RequiresPermissions("finance:rechargeApply:apply")
	@PostMapping(value = "/rechargeApply/apply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> rechargeApply(RechargeOrder rechargeOrder) {
		return financeSettingService.rechargeApply( rechargeOrder, this.getUser() );
	}

	@RequiresPermissions("finance:withdrawApply:view")
	@GetMapping("/withdrawApply")
	public String withdrawApplyPage() {
		return prefix + "/withdrawApply";
	}

	/**
	 * 人工提现申请<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@RequiresPermissions("finance:withdrawApply:apply")
	@PostMapping(value = "/withdrawApply/apply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> withdrawApply(WithdrawOrder withdrawOrder) {
		return financeSettingService.withdrawApply( withdrawOrder, this.getUser() );
	}
}