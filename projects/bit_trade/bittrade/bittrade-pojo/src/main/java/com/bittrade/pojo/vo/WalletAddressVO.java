package com.bittrade.pojo.vo;

import com.core.framework.base.VO.BaseVO;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("提币地址")
public class WalletAddressVO{

	private static final long serialVersionUID = 1L;

	private String name;
	private String tokenType;
	private String address;
}
