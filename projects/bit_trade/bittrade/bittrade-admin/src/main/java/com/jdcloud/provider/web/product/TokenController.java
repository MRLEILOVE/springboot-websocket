package com.jdcloud.provider.web.product;

import com.jdcloud.provider.dto.TokenSetting;
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
 * 体验金设置
 * <p>
 *
 * @author yongheng
 * @since 2018/12/18
 */
@Controller
@RequestMapping(value = "/product/token")
public class TokenController extends BaseController {
	private String					prefix	= "product/token";

	@Autowired
	private FinanceSettingService	financeSettingService;

	@RequiresPermissions("product:tokenSetting:view")
	@GetMapping("")
	public String tokenSetting(ModelMap mmap) {
		TokenSetting tokenSetting = financeSettingService.queryTokenSetting();
		mmap.put( "tokenSetting", tokenSetting );
		return prefix + "/tokenSetting";
	}

	/**
	 * 体验金参数修改<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 16:10
	 */
	@RequiresPermissions("product:tokenSetting:edit")
	@PostMapping(value = "/tokenSetting/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> tokenSettingEdit(TokenSetting tokenSetting) {
		return financeSettingService.tokenSettingEdit( tokenSetting, this.getUser() );
	}
}