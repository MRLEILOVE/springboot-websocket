package com.wallet.chain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithDrawParamDto {
    @NotNull(message = "用户ID不能为空")
    private Long userId;//提币用户
    @NotBlank(message = "订单流水不能为空")
    private String orderId; //订单流水。唯一
    @NotBlank(message = "币种大类不能为空")
    private String coinType; //币种大类
    @NotBlank(message = "具体币种不能为空")
    private String token;   //具体币种
    @NotNull(message = "提币数量不能为空")
    private BigDecimal amount; //提币数量
    @NotBlank(message = "接收地址不能为空")
    private String receiverAddress;//接收地址
}
