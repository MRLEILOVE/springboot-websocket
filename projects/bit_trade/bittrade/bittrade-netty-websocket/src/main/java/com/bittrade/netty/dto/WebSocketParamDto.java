package com.bittrade.netty.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * ClassName:WebSocketParamDto <br/>
 * Description: WebSocketParamDto参数. <br/>
 * DateTime: 2019年7月16日 上午10:31:26 <br />
 * 
 * @author zale
 * @version
 * @since JDK 1.8
 * @see
 */
@Data
public class WebSocketParamDto implements Serializable {

	private static final long	serialVersionUID	= 1828350058826113092L;

	private String				op;
	private String				args;

}
