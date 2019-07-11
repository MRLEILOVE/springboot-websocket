package com.bittrade.entrust;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.bittrade.currency.api.service.ITEntrustService;
import com.bittrade.pojo.model.TEntrust;
import com.core.framework.BaseApplication;

@SpringBootApplication
@EnableDubbo
public class EntrustApplication extends BaseApplication {

	public static void main(String[] args) {
		run(EntrustApplication.class, args, "entrust");
	}

	@Reference
	ITEntrustService es;
//	@PostConstruct
	public void test() {
//		QueryWrapper<TEntrust> qw = new QueryWrapper<TEntrust>();
//		qw.eq("ab", "bc");
		MergeSegments ms = new MergeSegments();
		while (true) {
			try {
				String str_ret = 
//						es.testPrm(qw)
						es.testPrm_2(ms)
						;
				System.out.println("str_ret=" + str_ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("input>>");
				System.out.println("System.in.read()=" + System.in.read());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
