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

import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.api.service.ITEntrustRecordService;
import com.bittrade.api.service.ITEntrustService;
import com.bittrade.common.constant.ICompareResultConstant;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.common.enums.EntrustStatusEnumer;
import com.bittrade.common.enums.EntrustTypeEnumer;
import com.bittrade.common.enums.IsActiveEnumer;
import com.bittrade.entrust.dao.ITEntrustDAO;
import com.bittrade.entrust.dao.ITEntrustRecordDAO;
import com.bittrade.entrust.service.IMakeAMatchService;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.model.TEntrustRecord;
import com.core.tool.SnowFlake;

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
	
	private static final SnowFlake SNOW_FLAKE = new SnowFlake(1, 1);
	
	@Autowired
	private ITEntrustService<ITEntrustDAO> entrustService;
	@Autowired
	private ITEntrustRecordService<ITEntrustRecordDAO> entrustRecordService;
	
	/**
	 * 行情价
	 */
	private static final BigDecimal LINE_PRICE = new BigDecimal(0);
	
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
	 * 撮合
	 * @param entrust 市价类型
	 * @param list_market
	 * @param list_limit
	 */
	private void matchSellWithBuyMarket(TEntrust entrust, List<TEntrust> list_market, List<TEntrust> list_limit) {
		if (list_market.size() > 0) {
			for (int i = list_market.size() - 1; i > -1; i--) { // 撮合市价
				TEntrust entrust_market = list_market.get(i);
				
				int i_compareTo = entrust.getCount().compareTo(entrust_market.getCount());
				if (i_compareTo == 1) {
					BigDecimal amount = LINE_PRICE.multiply(entrust_market.getCount());
					
					entrustService.updateOnMatch(
							entrust.getSuccessAmount().add(amount), 
							entrust.getCount().subtract(entrust_market.getCount()), 
							EntrustStatusEnumer.PART_FINISH.getCode(), 
							entrust.getId()
							);
					entrustService.updateOnMatch(
							amount, 
							new BigDecimal(0), 
							EntrustStatusEnumer.FINISH.getCode(), 
							entrust_market.getId()
							);
					
					TEntrustRecord entrustRecord_buy = new TEntrustRecord();
					entrustRecord_buy.setId(SNOW_FLAKE.nextId());
					entrustRecord_buy.setUserId(entrust.getUserId());
					entrustRecord_buy.setRivalUserId(entrust_market.getUserId());
					entrustRecord_buy.setEntrustId(entrust.getId());
					entrustRecord_buy.setRivalEntrustId(entrust_market.getId());
					entrustRecord_buy.setPrice(LINE_PRICE);
					entrustRecord_buy.setCount(entrust_market.getCount());
					entrustRecord_buy.setAmount(amount);
					entrustRecord_buy.setCurrencyTradeId(entrust.getCurrencyTradeId());
					entrustRecord_buy.setIsActive(IsActiveEnumer.UNACTIVE.getCode());
					entrustRecord_buy.setEntrustDirection(EntrustDirectionEnumer.BUY.getCode());
					entrustRecord_buy.setVersion(0);
					entrustRecordService.add(entrustRecord_buy);
					
					TEntrustRecord entrustRecord_sell = new TEntrustRecord();
					entrustRecord_sell.setId(SNOW_FLAKE.nextId());
					entrustRecord_sell.setUserId(entrust_market.getUserId());
					entrustRecord_sell.setRivalUserId(entrust.getUserId());
					entrustRecord_sell.setEntrustId(entrust_market.getId());
					entrustRecord_sell.setRivalEntrustId(entrust.getId());
					entrustRecord_sell.setPrice(LINE_PRICE);
					entrustRecord_sell.setCount(entrust_market.getCount());
					entrustRecord_sell.setAmount(amount);
					entrustRecord_sell.setCurrencyTradeId(entrust_market.getCurrencyTradeId());
					entrustRecord_sell.setIsActive(IsActiveEnumer.ACTIVE.getCode());
					entrustRecord_sell.setEntrustDirection(EntrustDirectionEnumer.SELL.getCode());
					entrustRecord_sell.setVersion(0);
					entrustRecordService.add(entrustRecord_sell);
				} else if (i_compareTo == 0) {
					
				}
			}
		}
		if (
				entrust.getCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.GREATER_THAN
				&& 
				list_limit.size() > 0
				) {
			for (int i = list_limit.size() - 1; i > -1; i--) { // 撮合限价
				TEntrust entrust_limit = list_limit.get(i);
				
			}
		}
	}
	
	private void addToBuyMarket(int idx, TEntrust entrust, List<TEntrust> list) {
		if (idx == list.size()) { // isFirst
			matchSellWithBuyMarket(entrust, LIST_SELL_MARKET, LIST_SELL_LIMIT); // 和对手盘（卖）进行撮合。
			if (entrust.getCount().compareTo(BigDecimal.ZERO) == ICompareResultConstant.GREATER_THAN) { // 有剩余的则加入列表。
				list.add(entrust);
			}
		} else {
			list.add(idx, entrust);
		}
	}
	
	private void addToBuyLimit(int idx, TEntrust entrust, List<TEntrust> list) {
	}
	
	public void makeAMatch(TEntrust entrust) {
		entrust.setId(SNOW_FLAKE.nextId());
		entrust.setStatus(EntrustStatusEnumer.UNFINISH.getCode());
		entrustService.add(entrust);
		if (entrust.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()) {
			int i_idx;
			LOCK_BUY.lock();
			if (entrust.getEntrustType() == EntrustTypeEnumer.MARKET.getCode()) { // 市价
				i_idx = findIndexFromMarket(entrust, LIST_BUY_MARKET);
				addToBuyMarket(i_idx, entrust, LIST_BUY_MARKET);
			} else { // 限价
				i_idx = findIndexFromLimit(entrust, LIST_BUY_LIMIT, ICompareResultConstant.LESS_THAN);
				addToBuyLimit(i_idx, entrust, LIST_BUY_LIMIT);
			}
			LOCK_BUY.unlock();
		} else if (entrust.getEntrustDirection() == EntrustDirectionEnumer.SELL.getCode()) {
//			int i_idx = findIndexFromSell(entrust);
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
		
		private static void test() {
			final int CNT = 5; // 50
			
			ExecutorService es = Executors.newFixedThreadPool(CNT);
			CountDownLatch cdl = new CountDownLatch(CNT);
			MakeAMatchServiceImpl makeAMatch = new MakeAMatchServiceImpl();
			
			for (int i = 0; i < CNT; i++) {
				/* Future<String> future = */es.submit(new Callable<String>() {
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
						makeAMatch.makeAMatch(entrust);
						
						cdl.countDown();
						
						return null;
					}
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
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			es.shutdown();
			
			// print .
			print();
		}
		
	}
	
	public static void main(String[] args) {
		Tester.test();
	}
	
}
