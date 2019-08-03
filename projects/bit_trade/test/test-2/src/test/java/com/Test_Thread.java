/**  
 * Project Name:test-2  
 * File Name:T.java  
 * Package Name:com  
 * DateTime: Aug 2, 2019 11:54:17 AM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com;

/**
 * ClassName:T <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Aug 2, 2019 11:54:17 AM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
public class Test_Thread {

	public static void main(String[] args) {
		Thread t_run = new Thread( () -> {
			while (true) {
				System.out.println( "SelfThreadID=" + Thread.currentThread().getId() + ", Thread.currentThread().getState()="
						+ Thread.currentThread().getState() );
				for (int i = 0; i < 1000000; i++) {
					System.out.print( "" );
				}

				try {
					Thread.sleep( 2000 );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} );
		t_run.start();
		new Thread( () -> {
			while (true) {
				System.out.println( "\tSelfThreadID=" + Thread.currentThread().getId() + ", t_run.getId()=" + t_run.getId() + ", t_run.getState()="
						+ t_run.getState() );

				try {
					Thread.sleep( 500 );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} ).start();
	}

}
