package com.common.bittrade.service;

import java.math.BigDecimal;
import java.util.List;

import com.bittrade.__default.service.IDefaultTWalletService;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.*;
import com.core.common.DTO.ReturnDTO;

/**
 * <p>
 * 虚拟币钱包表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
public interface ITWalletService extends IDefaultTWalletService<TWallet, TWalletDTO, TWalletVO> {

	/**
	 * 查询用户的币币账户
	 * 
	 * @param userId
	 *            用户id
	 */
	CoinAccountVO queryCoinAccountByUserId(Integer userId);

	/**
	 * 查询用户钱包
	 * 
	 * @param userId
	 *            用户id
	 * @param currencyTradeId
	 *            交易对id
	 */
	UserWalletVO queryUserWallet(Integer userId, Integer currencyTradeId);

	/**
	 * <p>
	 * 修改 交易冻结数量
	 * </p>
	 * modifyTradeFrozen:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param tradeFrozen
	 * @param version
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	int modifyTradeFrozen(BigDecimal tradeFrozen, Integer version, Long id);

	/**
	 * modifyWalletSellte:(结算). <br/>
	 * 
	 * @author Administrator
	 * @param entrustRecords
	 * @return
	 * @since JDK 1.8
	 */
	void modifyWalletSellte(TEntrustRecord entrustRecords) throws Exception;

	/**
	 * 
	 * qryUserWallet:(查询用户钱包). <br/>
	 * 
	 * @author Administrator
	 * @param userId
	 * @param currencyId
	 * @return
	 * @since JDK 1.8
	 */
	TWallet qryUserWallet(long userId, int currencyId) throws Exception;

	/**
	 * 总净资产
	 * @param userId 用户id
 	 * @return
	 */
	ReturnDTO<ConversionVo> totalNetAssets(Long userId);

	/**
	 * 用户的币币账户总资金折合
	 * @param userId 用户id
	 * @return 资金折合对象
	 */
    ConversionVo conversionTotal(Long userId);

	/**
	 * 查询当前用户的币币账户币种余额列表
	 * @param userId 用户id
	 * @return 钱包列表
	 */
	List<AssetsVO> detail(Long userId);

}
