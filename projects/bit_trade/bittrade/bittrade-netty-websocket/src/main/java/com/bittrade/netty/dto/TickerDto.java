package com.bittrade.netty.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class TickerDto implements Serializable {

	private static final long	serialVersionUID	= 5690922898676119845L;

	private BigDecimal			high;
	private BigDecimal			close;
	private BigDecimal			open;
	private BigDecimal			low;

}
