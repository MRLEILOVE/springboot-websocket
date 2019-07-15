package com.bittrade.entrust;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.core.framework.BaseApplication;

@SpringBootApplication
@EnableDubbo
public class EntrustApplication extends BaseApplication {

	public static void main(String[] args) {
		run(EntrustApplication.class, args, "entrust");
	}

//	@Autowired
//	ITEntrustService es;
////	@PostConstruct
//	public void test() {
////		QueryWrapper<TEntrust> qw = new QueryWrapper<TEntrust>();
////		qw.eq("ab", "bc");
//		MergeSegments ms = new MergeSegments();
//		while (true) {
//			try {
//				String str_ret = 
////						es.testPrm(qw)
//						es.testPrm_2(ms)
//						;
//				System.out.println("str_ret=" + str_ret);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			try {
//				System.out.println("input>>");
//				System.out.println("System.in.read()=" + System.in.read());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
