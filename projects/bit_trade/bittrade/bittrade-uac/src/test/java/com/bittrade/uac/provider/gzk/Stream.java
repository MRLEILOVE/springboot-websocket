package com.bittrade.uac.provider.gzk;


public class Stream {
	
	public static int i = 1;
	
	public static void main(String[] args) throws InterruptedException {
		
		new Thread(() -> {
			Stream.i = 2;
		}).start();;
		
		Thread.sleep( 2000 );
		System.out.println( i );
	}
}
