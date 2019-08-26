package com.jdcloud.provider.web.finance;

import com.jdcloud.provider.dto.AmountSetting;
import com.jdcloud.provider.dto.RateSetting;
import com.jdcloud.provider.service.FinanceSettingService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping(value = "/finance/setting")
public class SettingController extends BaseController {

	private String					prefix	= "finance/setting";

	@Autowired
	private FinanceSettingService	financeSettingService;

	@RequiresPermissions("finance:rateSetting:view")
	@GetMapping("/rateSetting")
	public String rateSetting(ModelMap mmap) {
		RateSetting rateSetting = financeSettingService.queryRateSetting();
		mmap.put( "rateSetting", rateSetting );
		return prefix + "/rateSetting";
	}

	/**
	 * 修改调整汇率<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@RequiresPermissions("finance:rateSetting:edit")
	@PostMapping(value = "/rateSetting/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> rateSettingEdit(RateSetting rateSetting) {
		return financeSettingService.rateSettingEdit( rateSetting.getExchangeRateAdjust(), this.getUser() );
	}

	@RequiresPermissions("finance:amountSetting:view")
	@GetMapping("/amountSetting")
	public String amountSetting(ModelMap mmap) {
		AmountSetting amountSetting = financeSettingService.queryAmountSetting();
		mmap.put( "amountSetting", amountSetting );
		return prefix + "/amountSetting";
	}

	/**
	 * 出入金限制修改<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 16:10
	 */
	@RequiresPermissions("finance:amountSetting:edit")
	@PostMapping(value = "/amountSetting/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> amountSettingEdit(AmountSetting amountSetting) {
		return financeSettingService.amountSettingEdit( amountSetting, this.getUser() );
	}



}