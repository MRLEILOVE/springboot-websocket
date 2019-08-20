package com.walletbiz.dto;

import com.core.framework.base.DTO.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("账户类型分页dto")
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountTypeDto  extends BaseDTO<AccountTypeDto> {
/**  
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).  
	 * @since JDK 1.8  
	 */
	private static final long serialVersionUID = 1L;

	//    @ApiModelProperty("1:资金账户，2：法币账户，3：beta账户")
//    @ApiModelProperty("1:提币 2：充值 3：转入资金账户 4：转出资金账户  5：转入法币账户  6：转出法币账户  7：转入beta账户  8：转出beta账户")
    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("币种id")
    private Integer currencyId;
}
