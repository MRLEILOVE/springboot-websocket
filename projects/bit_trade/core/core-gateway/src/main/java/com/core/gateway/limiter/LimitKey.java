package com.core.gateway.limiter;

import lombok.Data;

@Data
public class LimitKey {

	private String api;
	private String biz;

}
