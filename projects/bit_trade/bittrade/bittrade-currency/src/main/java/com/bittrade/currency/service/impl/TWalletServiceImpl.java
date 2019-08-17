package com.bittrade.currency.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bittrade.__default.service.impl.DefaultTWalletServiceImpl;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.currency.dao.ITCurrencyDAO;
import com.bittrade.currency.dao.ITCurrencyTradeDAO;
import com.bittrade.currency.dao.ITWalletDAO;
import com.bittrade.currency.dao.ITWalletRecordDAO;
import com.bittrade.currency.feign.AssetsService;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.model.*;
import com.bittrade.pojo.vo.*;
import com.core.common.DTO.ReturnDTO;
import com.core.tool.SnowFlake;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 虚拟币钱包表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TWalletServiceImpl extends DefaultTWalletServiceImpl<ITWalletDAO, TWallet, TWalletDTO, TWalletVO> implements ITWalletService {

	private static final Logger		LOG			= LoggerFactory.getLogger( TWalletServiceImpl.class );
	private static final SnowFlake	SNOW_FLAKE	= new SnowFlake( 1, 1 );
	@Autowired
	private ITWalletDAO				walletDAO;
	@Autowired
	private JedisCluster			jedisCluster;
	@Autowired
	private ITCurrencyTradeDAO		currencyTradeDAO;
	@Autowired
	private ITWalletRecordDAO		walletRecordDAO;
	@Autowired
	private AssetsService 			assetsService;
	@Autowired
	private ITCurrencyDAO 			currencyDAO;


	/**
	 * 查询用户的币币账户
	 */
	@Override
	public CoinAccountVO queryCoinAccountByUserId(Integer userId) {
		CoinAccountVO resultVO = new CoinAccountVO();
		Double usdtTotal = 0.00;// usdt总数量
		Double CNY = 0.00;// 人民币总数量

		// 查询用户钱包
		List<QueryWalletVO> walletVOS = walletDAO.queryByUserId( userId );
		if (walletVOS != null && walletVOS.size() > 0) {
			// 计算转换成usdt的总数量
			for (QueryWalletVO vo : walletVOS) {
				if ("USDT".equals( vo.getCurrencyName() )) {
					usdtTotal = usdtTotal + vo.getTotal();// 本身就是usdt的话，直接累加就可以了
				} else {
					// #TODO 查找该币与usdt的兑换率 ，现在暂时先写个假的汇率
					Double rate = 0.5;
					usdtTotal = usdtTotal + (rate * vo.getTotal());
				}
			}
			// 计算折合人民币的数量
			String rateKey = jedisCluster.get( "USD_TO_CNY_RATE_KEY" );
			if (rateKey != null) {
				Integer cnyRateKey = Integer.parseInt( rateKey );
				CNY = cnyRateKey * usdtTotal;
			}

			resultVO.setTotal( usdtTotal );
			resultVO.setCNY( CNY );
			resultVO.setWalletVOS( walletVOS );
		} else {
			resultVO.setCNY( 0.00 );
			resultVO.setTotal( 0.00 );
		}
		return resultVO;
	}

	/**
	 * 查询用户钱包
	 * 
	 * @param userId
	 *            用户id
	 * @param currencyTradeId
	 *            交易对id
	 */
	@Override
	public UserWalletVO queryUserWallet(Integer userId, Integer currencyTradeId) {
		return walletDAO.queryUserWallet( userId, currencyTradeId );
	}

	@Override
	public int modifyTradeFrozen(BigDecimal tradeFrozen, Integer version, Long id) {
		return walletDAO.modifyTradeFrozen( tradeFrozen, version, id );
	}

	@Override
	public void modifyWalletSellte(TEntrustRecord entrustRecords) throws Exception {
		long currentUserId = entrustRecords.getUserId();// 用户id
		long rivalUserId = entrustRecords.getRivalUserId();// 对手方用户id
		long entrustRecordId = entrustRecords.getId();
		int currencyTradeId = entrustRecords.getCurrencyTradeId();

		BigDecimal count = entrustRecords.getCount();
		BigDecimal amount = entrustRecords.getAmount();

		// 2、根据交易对Id获去币种id
		TCurrencyTrade currencyTrade = new TCurrencyTrade();
		currencyTrade.setId( currencyTradeId );
		currencyTrade = currencyTradeDAO.getBy( currencyTrade );
		int currencyId = currencyTrade.getCurrencyId1();// 货比id
		int marketId = currencyTrade.getCurrencyId2();// 法币id

		// 3、获取卖家币币钱包信息
		// currentUserId-->卖currencyId，currencyId(count)↓，marketId(amout)↑
		TWallet sellCurrencyIdWallet = qryUserWallet( currentUserId, currencyId );
		TWallet sellMarketIdWallet = qryUserWallet( currentUserId, marketId );
		if (sellCurrencyIdWallet.getTradeFrozen().compareTo( count ) < 0) {
			throw new Exception( "TEntrustRecord.id=" + entrustRecords.getId() + "sellte.faile,reason.userId=" + currentUserId + "currencyId="
					+ currencyId + "tradeFrozen=" + sellCurrencyIdWallet.getTradeFrozen() + "count=" + count );
		}

		// 4、更新卖家币币钱包
		updateUserWallet( sellCurrencyIdWallet, count, entrustRecordId, true );// 减count
		updateUserWallet( sellMarketIdWallet, amount, entrustRecordId, false );// 加amount

		// 5、获取对手的币币钱包信息
		// rivalUserId-->买currencyId，currencyId(count)↑，marketId(amout)↓
		TWallet buyMarketIdWallet = qryUserWallet( rivalUserId, marketId );
		TWallet buyCurrencyIdWallet = qryUserWallet( rivalUserId, currencyId );
		if (buyMarketIdWallet.getTradeFrozen().compareTo( amount ) < 0) {
			throw new Exception( "TEntrustRecord.id=" + entrustRecords.getId() + "sellte.faile,reason.userId=" + rivalUserId + "marketId=" + marketId
					+ "tradeFrozen=" + buyMarketIdWallet.getTradeFrozen() + "amount=" + amount );
		}

		// 6、更新对手钱包信息
		updateUserWallet( buyMarketIdWallet, amount, entrustRecordId, true );// 减amount
		updateUserWallet( buyCurrencyIdWallet, count, entrustRecordId, false );// 加count
	}

	/**
	 * updateUserWallet:(操作用户钱包). <br/>
	 * 
	 * @author Administrator
	 * @param wallet
	 * @param val
	 * @param entrustRecordId
	 * @param bool
	 * @since JDK 1.8
	 */
	private void updateUserWallet(TWallet wallet, BigDecimal val, long entrustRecordId, boolean bool) throws Exception {
		TWallet updateWallet = new TWallet();// 修改的对象
		if (bool) {
			updateWallet.setTradeFrozen( wallet.getTradeFrozen().subtract( val ) );
		} else {
			updateWallet.setTotal( wallet.getTotal().add( val ) );
		}
		updateWallet.setVersion( wallet.getVersion() + 1 );
		updateWallet.setUpdateTime( LocalDateTime.now() );

		TWallet updateSellMarketIdWallet = new TWallet(); // 条件
		updateSellMarketIdWallet.setId( wallet.getId() );
		updateSellMarketIdWallet.setUserId( wallet.getUserId() );
		updateSellMarketIdWallet.setCurrencyId( wallet.getCurrencyId() );
		updateSellMarketIdWallet.setVersion( wallet.getVersion() );

		// 更新钱包
		int row = walletDAO.modifyWithSelectiveBy( updateWallet, updateSellMarketIdWallet );
		LOG.info( "row=" + row );

		// 记录钱包流水
		TWalletRecord walletRecord = new TWalletRecord();
		walletRecord.setId( SNOW_FLAKE.nextId() );
		walletRecord.setUserId( wallet.getUserId() );
		walletRecord.setCurrencyId( wallet.getCurrencyId() );
		walletRecord.setType( (byte) 3 );
		walletRecord.setEntrustRecordId( entrustRecordId );
		walletRecord.setCreateTime( LocalDateTime.now() );

		BigDecimal beforeAmount = wallet.getTotal().add( wallet.getTradeFrozen() ).add( wallet.getTransferFrozen() );
		walletRecord.setBeforeAmount( beforeAmount );
		if (bool) {
			walletRecord.setChangeAmount( BigDecimal.ZERO.subtract( val ) );
		} else {
			walletRecord.setChangeAmount( val );
		}
		walletRecord.setAfterAmount( beforeAmount.add( val ) );
		// 插入流水
		walletRecordDAO.add( walletRecord );
	}

	@Override
	public TWallet qryUserWallet(long userId, int currencyId) throws Exception {
		TWallet qryWallet = new TWallet();
		qryWallet.setUserId( userId );
		qryWallet.setCurrencyId( currencyId );
		TWallet result = walletDAO.getBy( qryWallet );
		if (null == result) { // 用户币币钱包不存在，则给其生成一个钱包
			TWallet wallet = new TWallet();
			wallet.setUserId( userId );
			wallet.setCurrencyId( currencyId );
			wallet.setTotal( BigDecimal.ZERO );
			wallet.setTradeFrozen( BigDecimal.ZERO );
			wallet.setTransferFrozen( BigDecimal.ZERO );
			wallet.setCreateTime( LocalDateTime.now() );
			wallet.setVersion( 0 );// 版本号默认从0开始
			int row = walletDAO.add( wallet );
			if (row > 0) {
				return wallet;
			} else {
				throw new Exception( "用户Id=" + userId + ",currencyId = " + currencyId + "生成用户钱包异常" );
			}
		}
		return result;
	}

	/**
	 * 总净资产
	 * @param userId 用户id
	 * @return
	 */
    @Override
    public ReturnDTO<ConversionVo> totalNetAssets(Long userId) {
		BigDecimal totalUSDT = BigDecimal.ZERO;
		//获取人民币-usdt汇率
		String value = jedisCluster.get(RedisKeyUtil.USD_TO_CNY_RATE_KEY);
		BigDecimal USD_TO_CNY_RATE_RATE = new BigDecimal(JSONObject.parseObject(value).get("rate").toString());

		/**获取币币账户总资产*/
		ConversionVo bibiVO = totalConversion(userId);
		totalUSDT = bibiVO.getUSDT();

		//远程调用
		try {
			String assets = assetsService.getAssets(userId);
			totalUSDT = totalUSDT.add(new BigDecimal(assets));
		} catch (RetryableException e){
			e.printStackTrace();
			return ReturnDTO.error("网络繁忙，请稍后再试");
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnDTO.error("服务繁忙，请稍后再试");
		}
		BigDecimal totalCNY = totalUSDT.multiply(USD_TO_CNY_RATE_RATE);
		return ReturnDTO.ok(ConversionVo.builder().CNY(totalCNY).USDT(totalUSDT).build());
    }

	/**
	 * 用户的币币账户总资金折合
	 * @param userId 用户id
	 * @return 资金折合对象
	 */
	@Override
	public ConversionVo totalConversion(Long userId) {
		ConversionVo vo = ConversionVo.builder().CNY(BigDecimal.ZERO).USDT(BigDecimal.ZERO).build();
		BigDecimal totalUSDT = BigDecimal.ZERO;
		//获取人民币-usdt汇率
		String value = jedisCluster.get(RedisKeyUtil.USD_TO_CNY_RATE_KEY);
		BigDecimal USD_TO_CNY_RATE_RATE = new BigDecimal(JSONObject.parseObject(value).get("rate").toString());
		//获取用户币币账户下所有的钱包
		List<AssetsVO> AssetsVOs = walletDAO.getAssets(userId);
		for(AssetsVO x : AssetsVOs){
			//获取最新价
			String s = jedisCluster.get(IConstant.REDIS_PREFIX__LINE_PRICE + x.getCurrencyName());
			if(s == null){
				s = "2";
			}
			BigDecimal price = new BigDecimal(s);
			//USDT累计
			BigDecimal all = x.getTotal().add(x.getTradeFrozen().add(x.getTransferFrozen()));
			totalUSDT = totalUSDT.add(price.multiply(all));
		}
		BigDecimal totalCNY = totalUSDT.multiply(USD_TO_CNY_RATE_RATE);
		return ConversionVo.builder().CNY(totalCNY).USDT(totalUSDT).build();
	}

	/**
	 * 查询当前用户的币币账户币种余额列表
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public List<AccountVO> detail(Long userId) {
		//查询币种列表
		List<TCurrency> currencies = currencyDAO.gets();
		//查询用户钱包列表
		List<AccountVO> userAccountVOs = walletDAO.qryByUser(userId);
		if(userAccountVOs == null || userAccountVOs.size() <=0){
			//为用户创建全部钱包
			createAllWallet(currencies,userId);
			//封装好数据后返回给前端
			List<AccountVO> accountVOs = new ArrayList<>();
			currencies.stream().forEach(x -> {
				AccountVO vo = AccountVO.builder().balance(BigDecimal.ZERO).usedMargin(BigDecimal.ZERO).currency(x.getName()).build();
				accountVOs.add(vo);
			});
			return accountVOs;
		}else if(currencies.size() > userAccountVOs.size()){
			//为用户创建缺失的钱包
			List<TCurrency> lockCurrency = createLockWallet(currencies,userAccountVOs,userId);
			lockCurrency.stream().forEach(x ->{
				//将缺失的钱包添加到用户钱包列表，然后返回数据给前端
				userAccountVOs.add(AccountVO.builder().currency(x.getName()).balance(BigDecimal.ZERO).usedMargin(BigDecimal.ZERO).build());
			});
			return userAccountVOs;
		}
		return userAccountVOs;
	}

	/**
	 * 为用户创建缺失的钱包
	 * @param currencies 币种列表
	 * @param userAccountVOs 用户已有的钱包列表
	 * @param userId 用户id
	 * @return 缺失钱包的币种列表
	 */
	private List<TCurrency> createLockWallet(List<TCurrency> currencies, List<AccountVO> userAccountVOs, Long userId) {
		List<TCurrency> lockList = new ArrayList<>();//需要返回的缺失钱包的币种列表
		Map<String,String> existAccountMap = new HashMap<>();//已经存在的钱包Map

		//将已经存在的钱包封装进existAccountMap
		userAccountVOs.stream().forEach(x ->{
			existAccountMap.put(x.getCurrency(),x.getCurrency());
		});

		//遍历币种列表，寻找以及创建缺失的钱包列表
		currencies.stream().forEach(x -> {
			if(existAccountMap.get(x.getName()) == null){
				//将对象加入lockList
				lockList.add(x);
				//创建钱包
				createWallet(x.getId(),userId);
			}
		});
		return lockList;
	}

	/**
	 * 为用户创建全部钱包
	 * @param currencies 币种列表
	 * @param userId 用户id
	 */
	private void createAllWallet(List<TCurrency> currencies, Long userId) {
		currencies.stream().forEach(x -> {
			createWallet(x.getId(),userId);
		});
	}

	private TWallet createWallet(Integer currencyId, Long userId) {
		TWallet wallet = TWallet.builder()
				.userId(userId)
				.currencyId(currencyId)
				.total(BigDecimal.ZERO)
				.tradeFrozen(BigDecimal.ZERO)
				.transferFrozen(BigDecimal.ZERO)
				.version(0)
				.createTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build();
		walletDAO.add(wallet);
		return wallet;
	}

	/**
	 * 资产总览
	 * @param userId 用户id
	 * @return
	 */
	/*@Override
	public ReturnDTO<List<ConversionVo>> overview(Long userId) {
		List<ConversionVo> list = new ArrayList<>();

		*//** 获取币币账户总资产折合 *//*
		ConversionVo bibi = totalConversion(userId);
		bibi.setAccount("币币账户");
		list.add(bibi);

		*//** 远程调用法币账户总资产折合 *//*
		ConversionVo personal = assetsService.personalTotalConversion(userId);
		personal.setAccount("法币账户");
		list.add(personal);

		*//** 远程调用资金账户总资产折合 *//*
		ConversionVo fund = assetsService.personalTotalConversion(userId);
		fund.setAccount("资金账户");
		list.add(fund);

		*//** 交易账户 = 法币账户 + 币币账户 *//*
		ConversionVo deal = ConversionVo.builder().account("交易账户").build();
		deal.setUSDT(personal.getUSDT().add(bibi.getUSDT()));
		deal.setCNY(personal.getCNY().add(bibi.getCNY()));
		list.add(deal);

		return ReturnDTO.ok(list);
	}*/
}
