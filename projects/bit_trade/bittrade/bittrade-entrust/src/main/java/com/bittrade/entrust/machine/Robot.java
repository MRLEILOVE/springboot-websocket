package com.bittrade.entrust.machine;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.common.enums.EntrustTypeEnumer;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.entrust.api.service.ITEntrustRecordService;
import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.entrust.service.impl.MakeAMatchServiceImpl;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TEntrust;
import com.core.common.constant.ICompareResultConstant;

import redis.clients.jedis.JedisCluster;

/**
 * <p>
 * 机器人下委托单
 * </p>
 * ClassName: Robot <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * DateTime: Jul 16, 2019 10:54:50 AM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 */
@Component
public /* static */final class Robot {

	@Reference
	private ITCurrencyTradeService	currencyTradeService;
	@Autowired
	private JedisCluster			jedisCluster;
	@Autowired
	private ITEntrustService		entrustService;
	@Autowired
	private MakeAMatchServiceImpl	makeAMatchService;

	private static final BigDecimal bd_priceRangePercent = new BigDecimal("0.05"); // 5%
	private static final Random		R	= new Random( System.currentTimeMillis() );

	private static long getUserID() {
		Random r = new Random( R.nextLong() );
		return r.nextInt( 1000 ) + 1;
	}

	private static int getCurrencyTradeID() {
		Random r = new Random( R.nextLong() );
		return 1 + r.nextInt( 1 ); // 2
	}

	private static int getEntrustDirection() {
		Random r = new Random( R.nextLong() );
		return r.nextInt( 2 );
	}

	private static int getEntrustType() {
		Random r = new Random( R.nextLong() );
		return r.nextInt( 2 );
	}

	private static BigDecimal getPrice(double min, double max) {
		Random r = new Random( R.nextLong() );
		return new BigDecimal( r.nextDouble() * (max - min)
				+ min )/*
						 * .setScale(IConstant.PRICE_DECIMAL_LENGTH,
						 * BigDecimal.ROUND_HALF_DOWN)
						 */;
	}

	/**
	 * <p>
	 *   根据交易对获取其他站的行情价然后和自己的行情价进行融合。
	 * </p>
	 * getPrice:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param currencyTradeID
	 * @return
	 * @since JDK 1.8
	 */
	private BigDecimal getPrice(int currencyTradeID) {
		BigDecimal bd_price;
		
		BigDecimal bd_min, bd_max;
		double d_min, d_max;
		
		TCurrencyTrade currencyTrade = currencyTradeService.getByPK( currencyTradeID );
//		String str_linePrice = jedisCluster.get( String.format( "OKEX_%s_LAST_KEY", currencyTrade.getSymbol() ) );
		BigDecimal bd_selfPrice = makeAMatchService.getLinePrice( currencyTradeID );
		String str_otherLinePrice = jedisCluster.get( String.format( "OKEX_%s_LAST_KEY", currencyTrade.getSymbol() ) );
		if (str_otherLinePrice != null && str_otherLinePrice.length() > 0) {
			BigDecimal bd_otherPrice = new BigDecimal(str_otherLinePrice);
			if (bd_selfPrice.compareTo(bd_otherPrice) == ICompareResultConstant.LESS_THAN) {
				bd_min = bd_selfPrice;
				bd_max = bd_otherPrice;
			} else {
				bd_min = bd_otherPrice;
				bd_max = bd_selfPrice;
			}
		} else {
			bd_min = bd_max = bd_selfPrice;
		}
		
		d_min = bd_min.subtract( bd_min.multiply( bd_priceRangePercent ) ).doubleValue();
		d_max = bd_max.add( bd_max.multiply( bd_priceRangePercent ) ).doubleValue();
		bd_price = getPrice(d_min, d_max);
		
		return bd_price;
	}

	private static BigDecimal getCount() {
		Random r = new Random( R.nextLong() );
		return new BigDecimal( r.nextDouble()
				* 100 )/*
						 * .setScale(IConstant.COUNT_DECIMAL_LENGTH,
						 * BigDecimal.ROUND_HALF_DOWN)
						 */;
	}

	private static long getSleepMillis() {
		Random r = new Random( R.nextLong() );
		return r.nextInt( 1000 );
	}

	private static final class MyCallable implements Callable<String> {

		private ITEntrustService		entrustService;
		private ITEntrustRecordService	entrustRecordService;
		private MakeAMatchServiceImpl	makeAMatch;
		private CountDownLatch			cdl;
		private Robot					robot;

		private MyCallable(MakeAMatchServiceImpl makeAMatch, ITEntrustService entrustService, ITEntrustRecordService entrustRecordService,
				CountDownLatch cdl) {
			this.makeAMatch = makeAMatch;
			this.entrustService = entrustService;
			this.entrustRecordService = entrustRecordService;
			this.cdl = cdl;
		}

		@Override
		public String call() throws Exception {
			TEntrust entrust = new TEntrust();
			entrust.setUserId( getUserID() );
			entrust.setCurrencyTradeId( getCurrencyTradeID() );
			entrust.setEntrustDirection( getEntrustDirection() );
			entrust.setEntrustType( getEntrustType() );
			if (entrust.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) {
				entrust.setPrice( robot.getPrice( getCurrencyTradeID() ) );
			}
			entrust.setCount( getCount() );
			System.out.println( "11 com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.makeAMatch(TEntrust)" );
			makeAMatch.makeAMatch( entrust );
			System.out.println( "22 com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.makeAMatch(TEntrust)" );

			System.out.println( "countDown before" );
			cdl.countDown();
			System.out.println( "countDown after" );

			return null;
		}

	}

	public void test() {
		final int CNT = 50; // 500 50 5

		ExecutorService es = Executors.newFixedThreadPool( CNT );
		// CountDownLatch cdl = new CountDownLatch( CNT );
		// MyCallable MyCallable = new MyCallable(makeAMatch, entrustService,
		// entrustRecordService, cdl);

		for (int i = 0; i < CNT; i++) {
			// /* Future<String> future = */es.submit(MyCallable);
			/* Future<String> future = */es.submit( () -> {
				while (true) {
					TEntrust entrust = new TEntrust();
					entrust.setUserId( getUserID() );
					entrust.setCurrencyTradeId( getCurrencyTradeID() );
					entrust.setEntrustDirection( getEntrustDirection() );
					entrust.setEntrustType( getEntrustType() );
					if (entrust.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) {
						entrust.setPrice( getPrice( getCurrencyTradeID() ) );
					}
					entrust.setCount( getCount() );
					entrustService.add(entrust);

					makeAMatchService.makeAMatch( entrust );

					Thread.sleep( getSleepMillis() );
				}

				// System.out.println( "countDown before" );
				// cdl.countDown();
				// System.out.println( "countDown after" );

				// return null;
			} );
			// try {
			// System.out.println(future.get());
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// } catch (ExecutionException e) {
			// e.printStackTrace();
			// }
		}

		// try {
		// System.out.println( "await before" );
		// cdl.await();
		// System.out.println( "await after" );
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		synchronized (Robot.class) {
			try {
				Robot.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();  
			}
		}

		es.shutdown();
	}

	public static void _main(String[] args) {
	}

}