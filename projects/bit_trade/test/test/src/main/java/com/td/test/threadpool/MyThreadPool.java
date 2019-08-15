/**  
 * Project Name:test-  
 * File Name:MyThreadPool.java  
 * Package Name:com.td.test.threadpool  
 * DateTime: Aug 15, 2019 4:02:51 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/  
  
package com.td.test.threadpool;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**  
 * ClassName:MyThreadPool <br/>  
 * Description: TODO 添加描述. <br/>  
 * DateTime: Aug 15, 2019 4:02:51 PM <br />
 * @author   Administrator  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public class MyThreadPool {

	private int capacity;
	private int runSize;
	private int i_backup = 0;
	private Thread objArr_execute[];
	private Runnable objArr_backup[];
	private ReentrantLock lock = new ReentrantLock();
	
	public MyThreadPool(int i) {
		this.capacity = i;
		this.objArr_execute = new Thread[capacity];
		this.objArr_backup = new Runnable[3]; // backup .
		this.runSize = 0;
	}
	
	private void incRunSize() {
		try {
			lock.lock();
			runSize++;
		} finally {
			lock.unlock();
		}
	}
	
	private void decRunSize() {
		try {
			lock.lock();
			runSize--;
		} finally {
			lock.unlock();
		}
	}
	
	private int getRunSize() {
		try {
			lock.lock();
			return runSize;
		} finally {
			lock.unlock();
		}
	}

	private static final void sleep(long millis) {
		try {
			Thread.sleep( millis );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 *   此步也可以由内存中的线程数组来运行。
	 * </pre>
	 * createThread:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param r
	 * @param i_idx
	 * @return  
	 * @since JDK 1.8
	 */
	private Thread createThread(Runnable r, int i_idx) {
		return new Thread(() -> {
			System.out.println( "----------- i_idx=" + i_idx + ", before ID=" + Thread.currentThread().getId() );
			r.run();
			System.out.println( "----------- i_idx=" + i_idx + ", after ID=" + Thread.currentThread().getId() );
			
			decRunSize();
			callback(i_idx);
		});
	}
	
	/**
	 * use async ?
	 * callback:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator    
	 * @param i_idx 
	 * @since JDK 1.8
	 */
	private void callback(int i_idx) {
		System.out.println( "callback()  ID=" + Thread.currentThread().getId() + ", runSize=" + getRunSize() );
		System.out.println(  );
		
		if (objArr_backup != null && objArr_backup.length > 0) {
			for (int i = 0; i < objArr_backup.length; i++) {
				Runnable obj_backup = objArr_backup[i];
				
				if (obj_backup != null) {
					createThread(obj_backup, i_idx).start();
					objArr_backup[i] = null;
					
					break;
				}
			}
		}
	}
	
	private void start(Runnable r) {
		int i_runSize = getRunSize();
		if (i_runSize < capacity) {
			try {
				lock.lock();
				
				final int i_idx = i_runSize;
				
				createThread(r, i_idx).start();
				
				incRunSize();
			} finally {
				lock.unlock();
			}
		} else {
			objArr_backup[i_backup++] = r;
		}
	}

	private static final Random R = new Random();
	
	private static void test_1() {
		MyThreadPool myThreadPool = new MyThreadPool(2); // 2
		for (int i = 0; i < 3; i++) { // 4
			final int i_idx = i;
			myThreadPool.start( () -> {
				Thread thread = Thread.currentThread();
				int i_random = R.nextInt( 5 ) + 1;
				System.out.println( "i_idx=" + i_idx + ", ID=" + thread.getId() + ", i_random=" + i_random );
				while (i_random-- > 0) {
					System.out.println( "i_idx=" + i_idx + ", ID=" + thread.getId() + ", LocalDateTime.now()=" + LocalDateTime.now() );
					
					sleep( 1000 );
				}
			} );
		}
	}

	private static void test_2() {
		MyThreadPool myThreadPool = new MyThreadPool(2); // 2
		
		final int i_idx = 0;
		Runnable r = () -> {
			
			Thread thread = Thread.currentThread();
			int i_random = R.nextInt( 5 ) + 1;
			System.out.println( "i_idx=" + i_idx + ", ID=" + thread.getId() + ", i_random=" + i_random );
			
			while (i_random-- > 0) {
				System.out.println( "i_idx=" + i_idx + ", ID=" + thread.getId() + ", LocalDateTime.now()=" + LocalDateTime.now() );
				
				sleep( 1000 );
			}
			
		};
		
		myThreadPool.start( r );
		myThreadPool.start( r );
	}
	
	public static void main(String[] args) {
//		test_1();
//		test_2();
		
		ExecutorService es = Executors.newFixedThreadPool( 2 );
		final int i_idx = 0;
		Runnable r = () -> {
			
			Thread thread = Thread.currentThread();
			int i_random = R.nextInt( 5 ) + 1;
			System.out.println( "i_idx=" + i_idx + ", ID=" + thread.getId() + ", i_random=" + i_random );
			
			while (i_random-- > 0) {
				System.out.println( "i_idx=" + i_idx + ", ID=" + thread.getId() + ", LocalDateTime.now()=" + LocalDateTime.now() );
				
				sleep( 1000 );
			}
			
		};
		es.execute( r );
		es.execute( r );
	}

}
  
