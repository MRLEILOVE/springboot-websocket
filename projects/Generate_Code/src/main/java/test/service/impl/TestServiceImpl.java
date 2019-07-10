package test.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import test.service.ITestService;

@Service(value="testService")
public class TestServiceImpl implements ITestService {
	
	@Value(value="${address.a}")
	private String addr;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
