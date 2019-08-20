package com.wallet.chain.service;

import com.wallet.chain.dto.RechargeParamDto;
import com.wallet.chain.dto.RechargeReultDto;

public interface IRechargeService {

    /**
     * 充币   【从区块链中扫数据到系统中】
     *
     * @param paramDto
     * @return
     */
    RechargeReultDto recharge(RechargeParamDto paramDto);
}
