package test.compile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Compile {
	
	private void compile() {
		String str_name = "core";
		
		InputStream is = null;
		try {
			Process proc = Runtime.getRuntime().exec("ping g.cn");
			is = proc.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
			String line = null;  

			while((line=br.readLine())!=null){
				System.out.println(line);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is !=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				is = null;
			}
		}
	}
	
	public static void main(String[] args) {
		Compile c= new Compile();
		c.compile();
	}
	
}
