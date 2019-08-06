package com.bittrade.currency.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.bittrade.pojo.dto.TransferDto;
import com.core.common.DTO.ReturnDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTWalletServiceImpl;
import com.bittrade.currency.api.service.ITWalletService;
import com.bittrade.currency.dao.ITCurrencyTradeDAO;
import com.bittrade.currency.dao.ITWalletDAO;
import com.bittrade.currency.dao.ITWalletRecordDAO;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.model.TWalletRecord;
import com.bittrade.pojo.vo.CoinAccountVO;
import com.bittrade.pojo.vo.QueryWalletVO;
import com.bittrade.pojo.vo.TWalletVO;
import com.bittrade.pojo.vo.UserWalletVO;
import com.core.tool.SnowFlake;

import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;

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

	@Autowired
	private ITWalletDAO				walletDAO;

	@Autowired
	private JedisCluster			jedisCluster;

	@Autowired
	private ITCurrencyTradeDAO		currencyTradeDAO;

	@Autowired
	private ITWalletRecordDAO		walletRecordDAO;

	private static final SnowFlake	SNOW_FLAKE	= new SnowFlake( 1, 1 );

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


}
