/**  
 * Project Name:bittrade-entrust  
 * File Name:KLineDTO.java  
 * Package Name:com.bittrade.entrust.dto  
 * DateTime: Jul 18, 2019 5:57:32 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.bittrade.entrust.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * ClassName:KLineDTO <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 5:57:32 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
@Data
public class KLineDTO {

	// [symbol,time,open,high,low,close,volume]
	// ["BTC-USDT","2019-07-18
	// 17:00:00.00","9783.2","9787.9","9778.3","9782","3.40246089"]
	private int			symbol;
	private Date		time;
	private BigDecimal	open;
	private BigDecimal	high;
	private BigDecimal	low;
	private BigDecimal	close;
	private int			volume;

}
