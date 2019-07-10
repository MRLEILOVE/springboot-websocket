package test.number;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Test_Number {

	public static void main(String[] args) {
		double d = 13.123456789012345; // double 的问题？
//		d = 543210312345.1;
		BigDecimal bd = new BigDecimal(/*d*/"543210312345.123456789012345");
		bd = bd.setScale(14, BigDecimal.ROUND_HALF_UP);
		System.out.println(String.format("(1)=%s, (2)=%.6f", d, /*d*/bd));
		System.out.println("d=" + d);
		System.out.println("bd=" + bd);
		System.out.println();
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		System.out.println("nf.format(d)=" + nf.format(d));
		
		DecimalFormat df = new DecimalFormat("#.00000");
		System.out.println("df.format(d)=" + df.format(d));
		
		long l = Double.doubleToRawLongBits(d);
		System.out.println("l=" + l);
	}

}
