package com.bittrade.pojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class OkexTickerDto implements Serializable {

	private static final long	serialVersionUID	= -8728882160989813708L;

	private String				instrument_id;
	private BigDecimal			last;
	private BigDecimal			best_bid;
	private BigDecimal			best_ask;
	private BigDecimal			open_24h;
	private BigDecimal			high_24h;
	private BigDecimal			low_24h;
	private BigDecimal			base_volume_24h;
	private BigDecimal			quote_volume_24h;
	private String				timestamp;

}
