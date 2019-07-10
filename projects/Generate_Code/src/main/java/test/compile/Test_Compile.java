package test.compile;

import java.io.File;

public class Test_Compile {

	public static void main(String[] args) {
    	File f = new File("D:\\work\\git\\corn-parent\\corn-adminweb-service\\target\\lib");
    	File []fArr = f.listFiles();
    	String str = ".";
    	for (int i = 0; i < fArr.length; i++) {
			str += ";../lib/" + fArr[i].getName();
		}
    	System.out.println(str);
	}

}
