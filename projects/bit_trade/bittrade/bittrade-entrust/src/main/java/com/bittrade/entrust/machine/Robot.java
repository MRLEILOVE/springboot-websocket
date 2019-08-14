package com.bittrade.entrust.machine;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.common.enums.EntrustTypeEnumer;
import com.bittrade.common.utils.RedisKeyUtil;
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
public /* static */final class Robot implements InitializingBean, DisposableBean {

	private static final Logger		LOG						= LoggerFactory.getLogger( Robot.class );

	@Autowired
	private JedisCluster			jedisCluster;
	@Autowired
	private ITEntrustService		entrustService;
	@Autowired
	private MakeAMatchServiceImpl	makeAMatchService;

	/**
	 * 机器人下的单
	 */
	private static final long		MACHINE_USER_ID			= -1;
	private static final int		MAX_CURRENCY_TRADE_ID	= 1;										// 2
	/**
	 * 价格浮动范围
	 */
	private static final BigDecimal	BD__PRICE_RANGE_PERCENT	= new BigDecimal( "0.05" );					// 5%
	/*
	 * 买卖单区分价格高低。 使其更容易被撮合。
	 */
	private static final BigDecimal	BD__PRICE_ADD_RATE		= new BigDecimal( +1 );
	private static final BigDecimal	BD__PRICE_SUB_RATE		= new BigDecimal( -1 );
	/*
	 * 均衡器使用。 也可以更智能的根据有多少的偏差来决定使用均衡器的均衡程度。
	 */
	private static final BigDecimal	BD__LESS				= new BigDecimal( "0.9" );
	private static final BigDecimal	BD__ONE					= new BigDecimal( 1 );
	private static final BigDecimal	BD__MORE				= new BigDecimal( "1.1" );

	private static BigDecimal		bd__count_buy_rate		= BD__ONE;
	private static BigDecimal		bd__count_sell_rate		= BD__ONE;
	private static BigDecimal		bd__amount_buy_rate		= BD__ONE;
	// 1000 的 下10倍或者10分之一 （乘除方向不同而已）。
	private static final BigDecimal	BD__CNT_MIN_RATE		= new BigDecimal( 100 );
	// 1000 的 上10倍或者10分之一 （乘除方向不同而已）。
	private static final BigDecimal	BD__CNT_MAX_RATE		= new BigDecimal( 10000 );
	private static final Random		R						= new Random( System.currentTimeMillis() );

	/**
	 * equalizer 均衡器<br />
	 * 买卖单的数量差别
	 */
	private static final int		EQUALIZER_DIFFERENCE	= 50;

	private BigDecimal[] getMinAndMaxByLinePrice(TCurrencyTrade currencyTrade) {
		BigDecimal bd_min, bd_max;

		BigDecimal bd_selfPrice = makeAMatchService.getLinePrice( currencyTrade.getId() );
		String str_otherLinePrice = jedisCluster.get( String.format( RedisKeyUtil.OKEX_SYMBOL_LAST_KEY, currencyTrade.getSymbol() ) );
		if (str_otherLinePrice != null && str_otherLinePrice.length() > 0) {
			BigDecimal bd_otherPrice = new BigDecimal( str_otherLinePrice );
			if (bd_selfPrice.compareTo( bd_otherPrice ) == ICompareResultConstant.LESS_THAN) {
				bd_min = bd_selfPrice;
				bd_max = bd_otherPrice;
			} else {
				bd_min = bd_otherPrice;
				bd_max = bd_selfPrice;
			}
		} else {
			bd_min = bd_max = bd_selfPrice;
		}

		return new BigDecimal[] { bd_min, bd_max };
	}

	private static long getUserID() {
		// Random r = new Random( R.nextLong() );
		// return r.nextInt( 1000 ) + 1;
		return MACHINE_USER_ID;
	}

	private static int getCurrencyTradeID() {
		Random r = new Random( R.nextLong() );
		return 1 + r.nextInt( MAX_CURRENCY_TRADE_ID );
	}

	private static int getEntrustDirection() {
		Random r = new Random( R.nextLong() );
		return r.nextInt( 2 );
	}

	private static int getEntrustType() {
		Random r = new Random( R.nextLong() );
		return r.nextInt( 2 );
	}

	private static BigDecimal getRandom(double min, double max) {
		Random r = new Random( R.nextLong() );
		return new BigDecimal( r.nextDouble() * (max - min) + min );
	}

	/**
	 * <p>
	 * 根据交易对获取其他站的行情价然后和自己的行情价进行融合。
	 * </p>
	 * getPrice:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param min
	 * @param max
	 * @param rate
	 * @param currencyTrade
	 * @return
	 * @since JDK 1.8
	 */
	private BigDecimal getPrice(BigDecimal min, BigDecimal max, BigDecimal rate, TCurrencyTrade currencyTrade) {
		BigDecimal bd_price;

		double d_min = min.subtract( min.multiply( BD__PRICE_RANGE_PERCENT ) ).doubleValue(),
				d_max = max.add( max.multiply( BD__PRICE_RANGE_PERCENT ) ).doubleValue();

		bd_price = getRandom( d_min, d_max );

		// 均衡器。 根据买卖方向平衡买卖单价（买价加5%， 卖价减5%）。
		bd_price = bd_price.add( bd_price.multiply( BD__PRICE_RANGE_PERCENT ).multiply( rate ) );

		// 也可以通过原数运算来取精度。
		bd_price = bd_price.setScale( currencyTrade.getPriceDecimalDigits(), BigDecimal.ROUND_HALF_DOWN );

		return bd_price;
	}

	/**
	 * 
	 * getCount:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param min
	 * @param max
	 * @param rate
	 * @param currencyTrade
	 * @return
	 * @since JDK 1.8
	 */
	private static BigDecimal getCount(BigDecimal min, BigDecimal max, BigDecimal rate, TCurrencyTrade currencyTrade) {
		BigDecimal bd_count;

		double d_min = BD__CNT_MIN_RATE.divide( min, currencyTrade.getCountDecimalDigits(), BigDecimal.ROUND_HALF_DOWN ).doubleValue(),
				d_max = BD__CNT_MAX_RATE.divide( max, currencyTrade.getCountDecimalDigits(), BigDecimal.ROUND_HALF_DOWN ).doubleValue();

		bd_count = getRandom( d_min, d_max );

		// 均衡器。
		bd_count = bd_count.multiply( rate );

		// 也可以通过原数运算来取精度。
		bd_count = bd_count.setScale( currencyTrade.getCountDecimalDigits(), BigDecimal.ROUND_HALF_DOWN );

		return bd_count;
	}

	/**
	 * 
	 * getAmount:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param rate
	 * @param currencyTrade
	 * @return
	 * @since JDK 1.8
	 */
	private static BigDecimal getAmount(BigDecimal rate, TCurrencyTrade currencyTrade) {
		Random r = new Random( R.nextLong() );
		return new BigDecimal( r.nextDouble() * 10000 ) // 应该这个地方会更多可能性影响均衡器的使用上。
				// 均衡器。
				.multiply( rate )
				// 也可以通过原数运算来取精度。
				.setScale( currencyTrade.getPriceDecimalDigits(), BigDecimal.ROUND_HALF_DOWN );
	}

	private static long getSleepMillis() {
		Random r = new Random( R.nextLong() );
		// return r.nextInt( 1000 );
		return r.nextInt( 10000 );
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
				entrust.setPrice( robot.getPrice( null, null, null, null ) );
			}
			entrust.setCount( getCount( null, null, null, null ) );
			System.out.println( "11 com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.makeAMatch(TEntrust)" );
			makeAMatch.makeAMatch( entrust );
			System.out.println( "22 com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.makeAMatch(TEntrust)" );

			System.out.println( "countDown before" );
			cdl.countDown();
			System.out.println( "countDown after" );

			return null;
		}

	}

	private void checkLinePrice() {
		for (int i = 1; i <= MAX_CURRENCY_TRADE_ID; i++) {
			BigDecimal bd_selfPrice = makeAMatchService.getLinePrice( i );
			if (bd_selfPrice == null) {
				LOG.warn( "currencyTradeID:" + i + ", LinePrice Is NullOrEmpty !" );
				throw new RuntimeException( "currencyTradeID:" + i + ", LinePrice Is NullOrEmpty !" );
			}
		}
	}

	private static final int					CNT		= 10;	// 500 50 10 5 2

	private static boolean						isRun	= true;

	private static /* final */ExecutorService	es_robot;
	private static /* final */ExecutorService	es_equalizer;

	private void startRobot() {
		checkLinePrice();

		es_robot = Executors.newFixedThreadPool( CNT ); // 懒加载模式。
														// 为了节约点内存（存储、空间）和CPU（性能、时间）的开销？
		// CountDownLatch cdl = new CountDownLatch( CNT );
		// MyCallable MyCallable = new MyCallable(makeAMatch, entrustService,
		// entrustRecordService, cdl);
		for (int i = 0; i < CNT; i++) {
			// /* Future<String> future = */es.submit(MyCallable);
			/* Future<String> future = */es_robot.submit( () -> {
				while (isRun) {
					int i_currencyTradeID = getCurrencyTradeID();
					TCurrencyTrade currencyTrade = makeAMatchService.getCurrencyTrade( i_currencyTradeID );
					BigDecimal bdArr_MinAndMax[] = getMinAndMaxByLinePrice( currencyTrade );

					TEntrust entrust = new TEntrust();
					entrust.setUserId( getUserID() );
					entrust.setCurrencyTradeId( i_currencyTradeID );
					entrust.setEntrustDirection( getEntrustDirection() );
					entrust.setEntrustType( getEntrustType() );
					{
						if (entrust.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) {
							if (entrust.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()) {

								entrust.setPrice( getPrice( bdArr_MinAndMax[ 0 ], bdArr_MinAndMax[ 1 ], BD__PRICE_SUB_RATE, currencyTrade ) );
								entrust.setCount( getCount( bdArr_MinAndMax[ 0 ], bdArr_MinAndMax[ 1 ], bd__count_buy_rate, currencyTrade ) );

							} else /*
									 * if (entrust.getEntrustDirection() ==
									 * EntrustDirectionEnumer.SELL.getCode())
									 */ {

								entrust.setPrice( getPrice( bdArr_MinAndMax[ 0 ], bdArr_MinAndMax[ 1 ], BD__PRICE_ADD_RATE, currencyTrade ) );
								entrust.setCount( getCount( bdArr_MinAndMax[ 0 ], bdArr_MinAndMax[ 1 ], bd__count_sell_rate, currencyTrade ) );

							}
						} else /*
								 * if (entrust.getEntrustType() ==
								 * EntrustTypeEnumer.MARKET.getCode())
								 */ {
							if (entrust.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()) {

								entrust.setAmount( getAmount( bd__amount_buy_rate, currencyTrade ) );

							} else /*
									 * if (entrust.getEntrustDirection() ==
									 * EntrustDirectionEnumer.SELL.getCode())
									 */ {

								entrust.setCount( getCount( bdArr_MinAndMax[ 0 ], bdArr_MinAndMax[ 1 ], bd__count_sell_rate, currencyTrade ) );

							}
						}
					}
					// entrustService.add(entrust);

					makeAMatchService.makeAMatch( entrust );

					try {
						Thread.sleep( getSleepMillis() );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
		// synchronized (Robot.class) {
		// try {
		// Robot.class.wait();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
	}

	/**
	 * 均衡器， 均衡买卖单的平衡。 startEqualizer:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @since JDK 1.8
	 */
	private void startEqualizer() {
		es_equalizer = Executors.newSingleThreadExecutor();

		es_equalizer.submit( () -> {
			while (isRun) {
				for (int i = 1; i <= MAX_CURRENCY_TRADE_ID; i++) {
					int iArr_subCount[] = makeAMatchService.getSubCount( i );

					// if (
					// iArr_subCount[0] >= EQUALIZER_DIFFERENCE
					// &&
					// iArr_subCount[1] >= EQUALIZER_DIFFERENCE
					// ) { // 买和卖单堆积数量超过一定数， 需要增大买单单价和减小卖单单价， 好让其更容易被撮合。
					// // unimplements
					// }
					if (iArr_subCount[ 0 ] - iArr_subCount[ 1 ] > EQUALIZER_DIFFERENCE) { // 买单大于卖单一定数量。
																							// 需要减小买单的数量、增大卖单的数量
						bd__count_buy_rate = BD__LESS;
						bd__amount_buy_rate = BD__LESS;
						bd__count_sell_rate = BD__MORE;

						LOG.info( "使用了均衡器 \"买单大于卖单\", BuySize:" + iArr_subCount[ 0 ] + ", SellSize:" + iArr_subCount[ 1 ] );
					} else if (iArr_subCount[ 1 ] - iArr_subCount[ 0 ] > EQUALIZER_DIFFERENCE) { // 卖单大于买单一定数量。
																									// 需要增大买单的数量、减小卖单的数量
						bd__count_buy_rate = BD__MORE;
						bd__amount_buy_rate = BD__MORE;
						bd__count_sell_rate = BD__LESS;

						LOG.info( "使用了均衡器 \"卖单大于买单\", BuySize:" + iArr_subCount[ 0 ] + ", SellSize:" + iArr_subCount[ 1 ] );
					} else {
						bd__count_buy_rate = BD__ONE;
						bd__amount_buy_rate = BD__ONE;
						bd__count_sell_rate = BD__ONE;
					}
				}

				// 执行完任务，休息一段时间。 程序也要喘口气、喝喝茶嘛
				try {
					Thread.sleep( 60000 );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} );
	}

	// @javax.annotation.PostConstruct
	public void startUp() {
		try {
			startRobot();
			startEqualizer();
		} catch (Exception e) {
			LOG.error( e.toString() );
		}
	}

	// @javax.annotation.PreDestroy
	private void shutDown() {
		isRun = false;

		try {
			if (es_equalizer != null) {
				es_equalizer.shutdown();
				es_equalizer = null;
			}
			if (es_robot != null) {
				es_robot.shutdown();
				es_robot = null;
			}
		} catch (Exception e) {
			LOG.error( e.toString() );
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// startUp();
	}

	@Override
	public void destroy() throws Exception {
		shutDown();
	}

	public static void _main(String[] args) {
	}

}