package com.coin.wallet.service;

import com.coin.wallet.dto.RechargeParamDto;
import com.coin.wallet.dto.RechargeReultDto;

public interface IRechargeService {

    /**
     * 充币   【从区块链中扫数据到系统中】
     *
     * @param paramDto
     * @return
     */
    RechargeReultDto recharge(RechargeParamDto paramDto);
}
