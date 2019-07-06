package com.bittrade.entrust.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.common.enums.EntrustTypeEnumer;
import com.bittrade.entrust.service.IMakeAMatchService;
import com.bittrade.pojo.model.TEntrust;
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
	private ITEntrustService entrustService;
	@Autowired
	private ITEntrustRecordService entrustRecordService;
	
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
	 * @param entrust
	 * @param list_market
	 * @param list_limit
	 */
	private void matchSell(TEntrust entrust, List<TEntrust> list_market, List<TEntrust> list_limit) {
		
	}
	
	private void addToBuy(int idx, TEntrust entrust, List<TEntrust> list) {
		if (idx == list.size()) { // isFirst
			if (LIST_SELL_MARKET.size() > 0 || LIST_SELL_LIMIT.size() > 0) { // 和对手盘（卖）进行撮合。
				matchSell(entrust, LIST_SELL_MARKET, LIST_SELL_LIMIT);
			} else {
				list.add(entrust);
			}
		} else {
			list.add(idx, entrust);
		}
	}
	
	public void makeAMatch(TEntrust entrust) {
		entrust.setId(SNOW_FLAKE.nextId());
		entrust.setCreateTime(new Date());
		entrustService.add(entrust);
		if (entrust.getEntrustDirection() == EntrustDirectionEnumer.BUY.getCode()) {
			int i_idx;
			LOCK_BUY.lock();
			if (entrust.getEntrustType() == EntrustTypeEnumer.MARKET.getCode()) { // 市价
				i_idx = findIndexFromMarket(entrust, LIST_BUY_MARKET);
				addToBuy(i_idx, entrust, LIST_BUY_MARKET);
			} else { // 限价
				i_idx = findIndexFromLimit(entrust, LIST_BUY_LIMIT, -1);
				addToBuy(i_idx, entrust, LIST_BUY_LIMIT);
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
			System.out.println(e.getPrice() + ", " + e.getCount() + ", " + e.getEntrustDirection() + ", " + e.getEntrustType());
		}
		for (int i = 0, len = LIST_BUY_LIMIT.size(); i < len; i++) {
			TEntrust e = LIST_BUY_LIMIT.get(i);
			System.out.println(e.getPrice() + ", " + e.getCount() + ", " + e.getEntrustDirection() + ", " + e.getEntrustType());
		}
		System.out.println("sell ---------------------------------------------");
		for (int i = 0, len = LIST_SELL_MARKET.size(); i < len; i++) {
			TEntrust e = LIST_SELL_MARKET.get(i);
			System.out.println(e.getPrice() + ", " + e.getCount() + ", " + e.getEntrustDirection() + ", " + e.getEntrustType());
		}
		for (int i = 0, len = LIST_SELL_LIMIT.size(); i < len; i++) {
			TEntrust e = LIST_SELL_LIMIT.get(i);
			System.out.println(e.getPrice() + ", " + e.getCount() + ", " + e.getEntrustDirection() + ", " + e.getEntrustType());
		}
	}
	
	private static final class Tester {
		
		private static final Random R = new Random(System.currentTimeMillis());
		
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
			final int CNT = 50;
			
			ExecutorService es = Executors.newFixedThreadPool(CNT);
			CountDownLatch cdl = new CountDownLatch(CNT);
			MakeAMatchServiceImpl makeAMatch = new MakeAMatchServiceImpl();
			
			for (int i = 0; i < CNT; i++) {
				/* Future<String> future = */es.submit(new Callable<String>() {
					@Override
					public String call() throws Exception {
						TEntrust entrust = new TEntrust();
						entrust.setEntrustDirection(getEntrustDirection());
						entrust.setEntrustType(getEntrustType());
						entrust.setPrice(getPrice());
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
