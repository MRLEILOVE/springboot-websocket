/**  
 * Project Name:test-  
 * File Name:Test.java  
 * Package Name:com.td.test.threadpool  
 * DateTime: Aug 15, 2019 12:46:08 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/  
  
package com.td.test.threadpool;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**  
 * ClassName:Test <br/>  
 * Description: TODO 添加描述. <br/>  
 * DateTime: Aug 15, 2019 12:46:08 PM <br />
 * @author   Administrator  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public class Test {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

	private static void t() {
		System.out.println( "CAPACITY=" + CAPACITY );
		System.out.println( RUNNING );
		System.out.println( SHUTDOWN );
		System.out.println( STOP );
		System.out.println( TIDYING );
		System.out.println( TERMINATED );
		
		System.out.println(  );
		System.out.println( "Integer.MAX_VALUE=" + Integer.MAX_VALUE ); // 2147483647
		byte b = -128;
//		int i = 2147483648;
		System.out.println( "==" + (2147483648L*2) );
		System.out.println( "==" + (4294967296L/2) );
		
		System.out.println(  );
		System.out.println( workerCountOf(-536870912) );
		System.out.println( workerCountOf(-536870911) );
		System.out.println( workerCountOf(-536870910) );
		
		Runtime.getRuntime().exit( 0 );
	}

	private static void testThread() {
		ExecutorService es = Executors.newFixedThreadPool( 2 );
		int iArr_cnt[] = { 10, 5 };
		for (int i = 0; i < 4; i++) {
			final int i_idx = i;
			
			es.submit( () -> {
				Thread thread = Thread.currentThread();
				int i_cnt = iArr_cnt[i_idx % 2];
				System.out.println( "i=" + i_idx + ", i_cnt=" + i_cnt );
				while (i_cnt-- > 0) {
					System.out.println( LocalDateTime.now() + ", i=" + i_idx + ", " + thread.getId() + "," + thread.getName() );
					
					try {
						Thread.sleep( 1000 );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} );
		}
	}
	
	@SuppressWarnings("unused")
	private static void testArrayBlockingQueue() {
		try {
			BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
			
			new Thread(()->{
				try {
					if (false) { // false true
						System.out.println( "--" );
						String str_take = queue.take();
						System.out.println( "str_take=" + str_take );
					}
					
					Thread.sleep( 3000 );
					
					String str_take_2 = queue.take();
					System.out.println( "str_take_2=" + str_take_2 );
				} catch (InterruptedException e) {}
			}).start();
			
			Thread.sleep( 1500 );
			
			queue.add( "a1" );
			System.out.println( "1 queue.size()=" + queue.size() );
			queue.put( "a2" );
			System.out.println( "2 queue.size()=" + queue.size() );
			queue.offer( "a3" ); // add put offer
			System.out.println( "3 queue.size()=" + queue.size() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void testLinkedBlockingQueue() {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);
		
		new Thread(()->{
			try {
				if (true) { // false true
					System.out.println( "--" );
					String str_take = queue.take();
					System.out.println( "str_take=" + str_take + ", size=" + queue.size() );
				}
				
				Thread.sleep( 1000 );
				
				String str_take_2 = queue.take(); // any problem ?  why the count is 0 ?
				System.out.println( "str_take_2=" + str_take_2 + ", size=" + queue.size() );
			} catch (InterruptedException e) {}
		}).start();
		
		try {
//			Thread.sleep( 1500 );
			
			System.out.println( queue.add( "a1" ) );
			System.out.println( "1 queue.size()=" + queue.size() );
			queue.put( "a2" );
			System.out.println( "2 queue.size()=" + queue.size() );
			queue.put( "a3" );
			System.out.println( "3 queue.size()=" + queue.size() );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		queue.clear();
	}
	
	public static void main(String[] args) {
//		t();
//		testThread();
//		testArrayBlockingQueue();
		testLinkedBlockingQueue();
	}

}
  
