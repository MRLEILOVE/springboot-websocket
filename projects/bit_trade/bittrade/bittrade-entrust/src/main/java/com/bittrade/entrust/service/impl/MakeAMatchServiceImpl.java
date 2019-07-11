package com.bittrade.entrust.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

import com.bittrade.common.constant.IConstant;
import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.common.enums.EntrustStatusEnumer;
import com.bittrade.common.enums.EntrustTypeEnumer;
import com.bittrade.common.enums.IsActiveEnumer;
import com.bittrade.currency.api.service.ITEntrustRecordService;
import com.bittrade.currency.api.service.ITEntrustService;
import com.bittrade.entrust.service.IMakeAMatchService;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.model.TEntrustRecord;
import com.core.common.constant.ICompareResultConstant;
import com.core.tool.BigDecimalUtil;
import com.core.tool.SnowFlake;

@Service
public class MakeAMatchServiceImpl implements IMakeAMatchService {
	
	/**
	 * 市价买
	 */
	private static final ArrayList<TEntrust> LIST_BUY_MARKET = new ArrayList<>();
	
	/**
	 * 限价买
	 */
	private static final ArrayList<TEntrust> LIST_BUY_LIMIT = new ArrayList<>();
	
	/**
	 * 市价卖
	 */
	private static final ArrayList<TEntrust> LIST_SELL_MARKET = new ArrayList<>();
	
	/**
	 * 限价卖
	 */
	private static final ArrayList<TEntrust> LIST_SELL_LIMIT = new ArrayList<>();
	
	private static final SnowFlake SNOW_FLAKE__ENTRUST = new SnowFlake(1, 1);
	private static final SnowFlake SNOW_FLAKE__ENTRUST_RECORD = new SnowFlake(1, 1);
	
	@com.alibaba.dubbo.config.annotation.Reference
	private ITEntrustService entrustService;
	@com.alibaba.dubbo.config.annotation.Reference
	private ITEntrustRecordService entrustRecordService;
	
	/**
	 * 行情价
	 */
	private static /* final */BigDecimal LINE_PRICE = new BigDecimal(0);
	
	private static final ReentrantLock LOCK_BUY = new ReentrantLock();
	private static final ReentrantLock LOCK_SELL = new ReentrantLock();
	
	/**
	 * <pre>
	 * 买： 时间最早  排序
	 * 卖： 时间最早  排序
	 * </pre>
	 * 市价直接排在最后。
	 * <p>
	 *   0 => end, 新 => 旧。
	 * </p>
	 * @param entrust
	 * @param list
	 * @return
	 */
	private int findIndexFromMarket(TEntrust entrust, List<TEntrust> list) {
		return 0;
	}
	
	/**
	 * 买： 价格最高、时间最早  排序
	 * 卖： 价格最低、时间最早  排序
	 * <p>
	 *   买： 0 => end, 低 => 高。
	 *   卖： 0 => end, 高 => 低。
	 * </p>
	 * <p>
	 *   1、市价， 2、限价5， 3、市价， 4、限价4 => 顺序： 1、2、3、4 。
	 *   1、市价， 2、限价5， 3、市价， 4、限价6 => 顺序： 1、4、2、3 ？
	 * </p>
	 * @param entrust
	 * @param list
	 * @param compareVal
	 * @return
	 */
	private int findIndexFromLimit(TEntrust entrust, List<TEntrust> list, int compareVal) {
		int i_idx = -1;
		
		if (list.size() > 0) {
			for (i_idx = list.size() - 1; i_idx > -1; i_idx--) {
				TEntrust _entrust = list.get(i_idx);
				
				int i_compareTo = _entrust.getPrice().compareTo(entrust.getPrice()); // this : val, -1 = "<", 0 = "==", 1 = ">" .
				if (i_compareTo == compareVal) {
					break;
//				} else if (i_compareTo == 0) { // Price: this == val
//					i_compareTo = _entrust.getCreateTime().compareTo(entrust.getCreateTime());
//					if (i_compareTo == 1) { // CreateTime: this > val
//						
//					}
				}
			}
		}
		return i_idx + 1;
	}
	
	/**
	 * 获取成交价。
	 * @param price
	 * @return
	 */
	private BigDecimal getPrice(BigDecimal price) {
		return BigDecimalUtil.isNullOrZero(price) ? LINE_PRICE : price;
	}
	
	/**
	 * 获取成交价根据行情价。
	 * @param price_buy
	 * @param price_sell
	 * @return
	 */
	private BigDecimal getPriceWithLinePrice(BigDecimal price_buy, BigDecimal price_sell) {
		return price_buy.compareTo(price_sell) >= ICompareResultConstant.EQUAL ?
				price_buy.compareTo(LINE_PRICE) >= ICompareResultConstant.EQUAL && LINE_PRICE.compareTo(price_sell) >= ICompareResultConstant.EQUAL ?
						LINE_PRICE : 
							LINE_PRICE.compareTo(price_buy) == ICompareResultConstant.GREATER_THAN ? price_buy : price_sell
				:
				null
				;
	}
	
	/**
	 * （市市、限限、市限、限市）四种类型  获取成交价。
	 * @param entrust_before
	 * @param entrust_after
	 * @return
	 */
	private BigDecimal getDealPrice(TEntrust entrust_before, TEntrust entrust_after) {
		BigDecimal bd_dealPrice = null;
		
		BigDecimal bd_beforePrice = getPrice(entrust_before.getPrice()), bd_afterPrice = getPrice(entrust_after.getPrice());
		if (
				entrust_before.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()
				&& 
				entrust_after.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()
				) {
			bd_dealPrice = getPriceWithLinePrice(bd_beforePrice, bd_afterPrice);
		}
		if (
				entrust_before.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()
				&& 
				entrust_after.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()
				) {
			bd_dealPrice = getPriceWithLinePrice(bd_afterPrice, bd_beforePrice);
		}
		
		return bd_dealPrice;
	}
	
	private void matchWith(TEntrust entrust_before, TEntrust entrust_after, BigDecimal dealPrice) {
		BigDecimal count;
		BigDecimal amount;
		if (entrust_before.getLeftCount().compareTo(entrust_after.getLeftCount()) == ICompareResultConstant.LESS_THAN) {
			count = entrust_before.getLeftCount();
		} else {
			count = entrust_after.getLeftCount();
		}
		amount = dealPrice.multiply(count);
		
		// 修改委托
		{
			entrust_before.setLeftCount(entrust_before.getLeftCount().subtract(count));
			entrust_before.setSuccessAmount(entrust_before.getSuccessAmount().add(amount));
			entrustService.updateOnMatch(
					entrust_before.getSuccessAmount(), 
					entrust_before.getLeftCount(), 
					BigDecimalUtil.isZero(entrust_before.getLeftCount()) ? EntrustStatusEnumer.FINISH.getCode() : EntrustStatusEnumer.PART_FINISH.getCode(), 
					entrust_before.getId()
					);
//			System.out.println(
//					"entrust_before.getSuccessAmount()=" + entrust_before.getSuccessAmount() + ", " + 
//					"entrust_before.getLeftCount()=" + entrust_before.getLeftCount() + ", " + 
//					(BigDecimalUtil.isZero(entrust_before.getLeftCount()) ? EntrustStatusEnumer.FINISH.getCode() : EntrustStatusEnumer.PART_FINISH.getCode()) + ", " + 
//					entrust_before.getId()
//					);
		}
		{
			entrust_after.setLeftCount(entrust_after.getLeftCount().subtract(count));
			entrust_after.setSuccessAmount(entrust_after.getSuccessAmount().add(amount));
			entrustService.updateOnMatch(
					entrust_after.getSuccessAmount(), 
					entrust_after.getLeftCount(), 
					BigDecimalUtil.isZero(entrust_after.getLeftCount()) ? EntrustStatusEnumer.FINISH.getCode() : EntrustStatusEnumer.PART_FINISH.getCode(), 
					entrust_after.getId()
					);
//			System.out.println(
//					"entrust_after.getSuccessAmount()=" + entrust_after.getSuccessAmount() + ", " + 
//					"entrust_after.getLeftCount()=" + entrust_after.getLeftCount() + ", " + 
//					(BigDecimalUtil.isZero(entrust_after.getLeftCount()) ? EntrustStatusEnumer.FINISH.getCode() : EntrustStatusEnumer.PART_FINISH.getCode()) + ", " + 
//					entrust_after.getId()
//					);
		}
		
		// 新增撮合
		// 主动
		TEntrustRecord entrustRecord_before = new TEntrustRecord();
		entrustRecord_before.setId(SNOW_FLAKE__ENTRUST_RECORD.nextId());
		entrustRecord_before.setUserId(entrust_before.getUserId());
		entrustRecord_before.setRivalUserId(entrust_after.getUserId());
		entrustRecord_before.setEntrustId(entrust_before.getId());
		entrustRecord_before.setRivalEntrustId(entrust_after.getId());
		entrustRecord_before.setPrice(dealPrice);
		entrustRecord_before.setCount(count);
		entrustRecord_before.setAmount(amount);
		entrustRecord_before.setCurrencyTradeId(entrust_before.getCurrencyTradeId());
		entrustRecord_before.setIsActive(IsActiveEnumer.ACTIVE.getCode());
		entrustRecord_before.setEntrustDirection(entrust_before.getEntrustDirection());
		entrustRecordService.add(entrustRecord_before);
//		System.out.println(entrustRecord_before);
		// 被动
		TEntrustRecord entrustRecord_after = new TEntrustRecord();
		entrustRecord_after.setId(SNOW_FLAKE__ENTRUST_RECORD.nextId());
		entrustRecord_after.setUserId(entrust_after.getUserId());
		entrustRecord_after.setRivalUserId(entrust_before.getUserId());
		entrustRecord_after.setEntrustId(entrust_after.getId());
		entrustRecord_after.setRivalEntrustId(entrust_before.getId());
		entrustRecord_after.setPrice(dealPrice);
		entrustRecord_after.setCount(count);
		entrustRecord_after.setAmount(amount);
		entrustRecord_after.setCurrencyTradeId(entrust_after.getCurrencyTradeId());
		entrustRecord_after.setIsActive(IsActiveEnumer.UNACTIVE.getCode());
		entrustRecord_after.setEntrustDirection(entrust_after.getEntrustDirection());
		entrustRecordService.add(entrustRecord_after);
//		System.out.println(entrustRecord_after);
	}
	
	/**
	 * 修改行情价根据成交价
	 * @param dealPrice
	 */
	private void changeLinePrice(BigDecimal dealPrice) {
		if (LINE_PRICE.compareTo(dealPrice) != ICompareResultConstant.EQUAL) {
			LINE_PRICE = dealPrice;
			// 异步通知。
			
		}
	}
	
	/**
	 * 撮合
	 * <p>
	 *   当然程序也可以写成专门针对某种类型的操作， 比如：市市、市限、限限、限市。 <br />
	 *   这样程序会多写一份类似的， 少部分一样， 但是准确从效率来讲会高一些。 不过现在应该这个可以忽略不计。
	 * </p>
	 * @param entrust 市价或者限价类型
	 * @param list
	 */
	private void matchWith(TEntrust entrust, List<TEntrust> list) {
		if (
				entrust.getLeftCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.GREATER_THAN // 还有未成交数
				&& 
				list.size() > 0
				) {
			for (int i = list.size() - 1; i > -1; i--) {
				TEntrust entrust_ = list.get(i);
				
				BigDecimal bd_dealPrice = getDealPrice(entrust_, entrust);
				if (bd_dealPrice == null) {
					break;
				} else {
					matchWith(entrust_, entrust, bd_dealPrice);
					changeLinePrice(bd_dealPrice);
					
					if (entrust_.getLeftCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.EQUAL) {
						list.remove(i);
					}
					if (entrust.getLeftCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.EQUAL) {
						break;
					}
				}
			}
		}
	}
	
	private void addTo(int idx, TEntrust entrust, List<TEntrust> list_after, List<TEntrust> list_beforeMarket, List<TEntrust> list_beforeLimit) {
		if (idx == list_after.size()) { // isFirst
			matchWith(entrust, list_beforeMarket); // 市价和对手盘（卖）进行撮合。
			matchWith(entrust, list_beforeLimit); // 市价和对手盘（卖）进行撮合。
			if (entrust.getLeftCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.GREATER_THAN) { // 有剩余的则加入列表。
				list_after.add(entrust);
			}
		} else {
			list_after.add(idx, entrust);
		}
	}
	
	public void makeAMatch(TEntrust entrust) {
		// 入库。
		{
			entrust.setId(SNOW_FLAKE__ENTRUST.nextId());
			entrust.setSuccessAmount(BigDecimal.ZERO);
			entrust.setLeftCount(entrust.getCount());
			entrust.setStatus(EntrustStatusEnumer.UNFINISH.getCode());
			entrustService.add(entrust);
//			System.out.println(entrust);
		}
		if (entrust.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()) {
			int i_idx;
			LOCK_BUY.lock();
			if (entrust.getEntrustType() == EntrustTypeEnumer.MARKET.getCode()) { // 市价
				i_idx = findIndexFromMarket(entrust, LIST_BUY_MARKET);
				addTo(i_idx, entrust, LIST_BUY_MARKET, LIST_SELL_MARKET, LIST_SELL_LIMIT);
			} else { // 限价
				i_idx = findIndexFromLimit(entrust, LIST_BUY_LIMIT, ICompareResultConstant.LESS_THAN);
				addTo(i_idx, entrust, LIST_BUY_LIMIT, LIST_SELL_MARKET, LIST_SELL_LIMIT);
			}
			LOCK_BUY.unlock();
		} else if (entrust.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()) {
			int i_idx;
			LOCK_BUY.lock();
			if (entrust.getEntrustType() == EntrustTypeEnumer.MARKET.getCode()) { // 市价
				i_idx = findIndexFromMarket(entrust, LIST_SELL_MARKET);
				addTo(i_idx, entrust, LIST_SELL_MARKET, LIST_BUY_MARKET, LIST_BUY_LIMIT);
			} else { // 限价
				i_idx = findIndexFromLimit(entrust, LIST_SELL_LIMIT, ICompareResultConstant.GREATER_THAN);
				addTo(i_idx, entrust, LIST_SELL_LIMIT, LIST_BUY_MARKET, LIST_BUY_LIMIT);
			}
			LOCK_BUY.unlock();
		}
	}
	
	private static void print() {
		System.out.println("LIST_BUY_MARKET.size()=" + LIST_BUY_MARKET.size());
		System.out.println("LIST_BUY_LIMIT.size()=" + LIST_BUY_LIMIT.size());
		System.out.println("LIST_SELL_MARKET.size()=" + LIST_SELL_MARKET.size());
		System.out.println("LIST_SELL_LIMIT.size()=" + LIST_SELL_LIMIT.size());
		System.out.println("buy ---------------------------------------------");
		for (int i = 0, len = LIST_BUY_MARKET.size(); i < len; i++) {
			TEntrust e = LIST_BUY_MARKET.get(i);
			System.out.println(e.getUserId() + ", " + e.getCurrencyTradeId() + ", " + e.getEntrustDirection() + ", " + e.getEntrustType() + ", " + e.getPrice() + ", " + e.getCount());
		}
		for (int i = 0, len = LIST_BUY_LIMIT.size(); i < len; i++) {
			TEntrust e = LIST_BUY_LIMIT.get(i);
			System.out.println(e.getUserId() + ", " + e.getCurrencyTradeId() + ", " + e.getEntrustDirection() + ", " + e.getEntrustType() + ", " + e.getPrice() + ", " + e.getCount());
		}
		System.out.println("sell ---------------------------------------------");
		for (int i = 0, len = LIST_SELL_MARKET.size(); i < len; i++) {
			TEntrust e = LIST_SELL_MARKET.get(i);
			System.out.println(e.getUserId() + ", " + e.getCurrencyTradeId() + ", " + e.getEntrustDirection() + ", " + e.getEntrustType() + ", " + e.getPrice() + ", " + e.getCount());
		}
		for (int i = 0, len = LIST_SELL_LIMIT.size(); i < len; i++) {
			TEntrust e = LIST_SELL_LIMIT.get(i);
			System.out.println(e.getUserId() + ", " + e.getCurrencyTradeId() + ", " + e.getEntrustDirection() + ", " + e.getEntrustType() + ", " + e.getPrice() + ", " + e.getCount());
		}
	}
	
	private static final class Tester {
		
		private static final Random R = new Random(System.currentTimeMillis());
		
		private static long getUserID() {
			Random r = new Random(R.nextLong());
			return r.nextInt(1000);
		}
		
		private static int getCurrencyTradeID() {
			Random r = new Random(R.nextLong());
			return r.nextInt(100);
		}
		
		private static int getEntrustDirection() {
			Random r = new Random(R.nextLong());
			return r.nextInt(2);
		}
		
		private static int getEntrustType() {
			Random r = new Random(R.nextLong());
			return r.nextInt(2);
		}
		
		private static BigDecimal getPrice() {
			Random r = new Random(R.nextLong());
			return new BigDecimal(r.nextDouble() * 100).setScale(IConstant.PRICE_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN);
		}
		
		private static BigDecimal getCount() {
			Random r = new Random(R.nextLong());
			return new BigDecimal(r.nextDouble() * 100).setScale(IConstant.COUNT_DECIMAL_LENGTH, BigDecimal.ROUND_HALF_DOWN);
		}
		
		private static final class MyCallable implements Callable<String> {
			
			private ITEntrustService entrustService;
			private ITEntrustRecordService entrustRecordService;
			private MakeAMatchServiceImpl makeAMatch;
			private CountDownLatch cdl;
			
			private MyCallable(MakeAMatchServiceImpl makeAMatch, ITEntrustService entrustService, ITEntrustRecordService entrustRecordService, CountDownLatch cdl) {
				this.makeAMatch = makeAMatch;
				this.entrustService = entrustService;
				this.entrustRecordService = entrustRecordService;
				this.cdl = cdl;
			}

			@Override
			public String call() throws Exception {
				TEntrust entrust = new TEntrust();
				entrust.setUserId(getUserID());
				entrust.setCurrencyTradeId(getCurrencyTradeID());
				entrust.setEntrustDirection(getEntrustDirection());
				entrust.setEntrustType(getEntrustType());
				if (entrust.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) {
					entrust.setPrice(getPrice());
				}
				entrust.setCount(getCount());
				System.out.println("11 com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.makeAMatch(TEntrust)");
				makeAMatch.makeAMatch(entrust);
				System.out.println("22 com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.makeAMatch(TEntrust)");
				
				System.out.println("countDown before");
				cdl.countDown();
				System.out.println("countDown after");
				
				return null;
			}
			
		}
		
		private static void test(IMakeAMatchService makeAMatch) {
			final int CNT = 5; // 50 5
			
			ExecutorService es = Executors.newFixedThreadPool(CNT);
			CountDownLatch cdl = new CountDownLatch(CNT);
//			MyCallable MyCallable = new MyCallable(makeAMatch, entrustService, entrustRecordService, cdl);
			
			for (int i = 0; i < CNT; i++) {
//				/* Future<String> future = */es.submit(MyCallable);
				/* Future<String> future = */es.submit(() -> {
					TEntrust entrust = new TEntrust();
					entrust.setUserId(getUserID());
					entrust.setCurrencyTradeId(getCurrencyTradeID());
					entrust.setEntrustDirection(getEntrustDirection());
					entrust.setEntrustType(getEntrustType());
					if (entrust.getEntrustType() == EntrustTypeEnumer.LIMIT.getCode()) {
						entrust.setPrice(getPrice());
					}
					entrust.setCount(getCount());
					System.out.println("11 com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.makeAMatch(TEntrust)");
					makeAMatch.makeAMatch(entrust);
					System.out.println("22 com.bittrade.entrust.service.impl.MakeAMatchServiceImpl.makeAMatch(TEntrust)");
					
					System.out.println("countDown before");
					cdl.countDown();
					System.out.println("countDown after");
					
					return null;
				});
//				try {
//					System.out.println(future.get());
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				} catch (ExecutionException e) {
//					e.printStackTrace();
//				}
			}
			
			try {
				System.out.println("await before");
				cdl.await();
				System.out.println("await after");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			es.shutdown();
			
			// print .
			print();
		}
		
	}
	
	public void test() {
//		Tester.test(this);
		TEntrust entrust1 = new TEntrust();
		entrust1.setUserId(101);
		entrust1.setCurrencyTradeId(102);
		entrust1.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
		entrust1.setEntrustType(EntrustTypeEnumer.MARKET.getCode());
		entrust1.setCount(new BigDecimal(50));
		makeAMatch(entrust1);
		TEntrust entrust2 = new TEntrust();
		entrust2.setUserId(102);
		entrust2.setCurrencyTradeId(102);
		entrust2.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
		entrust2.setEntrustType(EntrustTypeEnumer.LIMIT.getCode());
		entrust2.setPrice(new BigDecimal(157));
		entrust2.setCount(new BigDecimal(28));
		makeAMatch(entrust2);
		TEntrust entrust3 = new TEntrust();
		entrust3.setUserId(103);
		entrust3.setCurrencyTradeId(102);
		entrust3.setEntrustDirection(EntrustDirectionEnumer.SELL.getCode());
		entrust3.setEntrustType(EntrustTypeEnumer.LIMIT.getCode());
		entrust3.setPrice(new BigDecimal(101));
		entrust3.setCount(new BigDecimal(37));
		makeAMatch(entrust3);
		TEntrust entrust4 = new TEntrust();
		entrust4.setUserId(104);
		entrust4.setCurrencyTradeId(102);
		entrust4.setEntrustDirection(EntrustDirectionEnumer.SELL.getCode());
		entrust4.setEntrustType(EntrustTypeEnumer.MARKET.getCode());
		entrust4.setCount(new BigDecimal(80));
		makeAMatch(entrust4);
		TEntrust entrust5 = new TEntrust();
		entrust5.setUserId(105);
		entrust5.setCurrencyTradeId(102);
		entrust5.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
		entrust5.setEntrustType(EntrustTypeEnumer.MARKET.getCode());
		entrust5.setCount(new BigDecimal(83));
		makeAMatch(entrust5);
	}
	
	public static void _main(String[] args) {
//		Tester.test(null);
	}
	
}
