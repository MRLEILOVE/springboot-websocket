package com.walletbiz.test;

import com.bittrade.pojo.vo.CoinTypeVO;
import com.bittrade.pojo.vo.WalletAddressVO;
import com.bittrade.pojo.vo.WithdrawBillParamVo;
import com.common.bittrade.controller.WCoinController;
import com.common.bittrade.service.IWCoinService;
import com.core.web.constant.entity.LoginUser;
import com.wallet.biz.controller.WOrderController;
import com.wallet.biz.controller.WWalletAddressController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.bittrade.service.impl.WWalletAccountServiceImpl;
import com.walletbiz.test.base.BaseTester;

public class WalletTester extends BaseTester {

	@Autowired
	WWalletAccountServiceImpl walletAccountServiceImpl;

	@Autowired
	IWCoinService wCoinService;
	@Autowired
	WCoinController coinController;
	@Autowired
	WWalletAddressController wWalletAddressController;
	@Autowired
	WOrderController wOrderController;

	@Test
	public void test() {

		System.out.println("test start ...");
//		List<WCoin> Rechargecoinlist = wCoinService.list(new QueryWrapper<>(WCoin.builder()
//				.isRecharge("E").build()));
//		System.out.println(Rechargecoinlist);
//
//		ReturnDTO x = coinController.rechargecoinlist();
//		System.out.println(x);
//		System.out.println(coinController.withdrawcoinlist());
		CoinTypeVO coinTypeVO = CoinTypeVO.builder().coinType("BTC").token("BTC").build();
		WithdrawBillParamVo withdrawBillParamVo = WithdrawBillParamVo.builder()
				.CoinType("BTC_TOKEN")
				.token("USDT")
				.amount("10")
				.free("4")
				.password("123456")
				.receiverAddress("").build();
		Long userId = 10010L;
		LoginUser user =new LoginUser();
		user.setUser_id(userId);
		user.setPayPassWord("123456");
		WalletAddressVO walletAddressVO = WalletAddressVO.builder()
				.address("15Q12Sj9r1FZxEJnFrQzxgkAtmYqKZL7pX")
				.name("test2")
				.tokenType("USDT").build();
//		System.out.println(wWalletAddressController.addaddress(walletAddressVO,user));
//		System.out.println(wWalletAddressController.firstaddress(coinTypeVO,user));
//		System.out.println(wWalletAddressController.addresslist(coinTypeVO,user));
//		System.out.println(wOrderController.confirmTibi(withdrawBillParamVo,user));
		System.out.println(wOrderController.feeMaxMin(coinTypeVO));
		System.out.println(wOrderController.quotaMaxMin(coinTypeVO));
//		System.out.println(user.getPayPassWord());
//		System.out.println(withdrawBillParamVo.getPassword());
	}

}
