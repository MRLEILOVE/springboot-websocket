package com.bittrade.uac.provider.gzk;

import java.util.AbstractList;
import java.util.ArrayList;

public class Mains extends AbstractList<String>{

	private String			t1	= "t1";

	private static String	t2	= "t2";

	public static void main(String[] args) {
		
		new InnerClass2().main();
		InnerClass1 mi = new Mains().new InnerClass1();
		mi.main();
		
	}

	class InnerClass1 extends ArrayList<String> {
		
		public InnerClass1 InnerClass1(){
			return new InnerClass1();
		}

		public void main() {

			System.out.println( t1 );
			System.out.println( t2 );
		}

	}

	static class InnerClass2 {

		public void main() {
			System.out.println( t2 );
		}
	}

	@Override
	public String get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
