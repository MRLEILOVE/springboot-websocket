package com.jdcloud.provider.service;

import com.jdcloud.provider.dto.AmountSetting;
import com.jdcloud.provider.dto.RateSetting;
import com.jdcloud.provider.dto.TokenSetting;
import com.jdcloud.provider.pojo.RechargeOrder;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.pojo.UserAccount;
import com.jdcloud.provider.pojo.WithdrawOrder;
import com.jdcloud.util.wrapper.Wrapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 财务管理设置
 * <p>
 *
 * @author yongheng
 * @since 2018/11/29
 */
public interface FinanceSettingService {

	/**
	 * 汇率设置<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 10:30
	 */
	RateSetting queryRateSetting();

	/**
	 * 修改调整汇率<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 14:25
	 */
	Wrapper<String> rateSettingEdit(BigDecimal exchangeRateAdjust, SysUser user);

	/**
	 * 出入金限制设置<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 15:22
	 */
	AmountSetting queryAmountSetting();

	/**
	 * 出入金限制修改 <br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 16:11
	 */
	Wrapper<String> amountSettingEdit(AmountSetting amountSetting, SysUser user);

	/**
	 * 人工充值申请 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 20:05
	 */
	Wrapper<String> rechargeApply(RechargeOrder rechargeOrder, SysUser user);

	/**
	 * 人工提现申请<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/30 10:00
	 */
	Wrapper<String> withdrawApply(WithdrawOrder withdrawOrder, SysUser user);

	/**
	 * 用户查询<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/30 11:24
	 */
	Wrapper<List<UserAccount>> queryUser(String keyword);

	/**
	 * 体验金设置<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/18 19:55
	 */
	TokenSetting queryTokenSetting();

	/**
	 * 体验金参数修改<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/18 20:12
	 */
	Wrapper<String> tokenSettingEdit(TokenSetting tokenSetting, SysUser user);
}