package com.coin.wallet.service;

import com.coin.wallet.dto.WithDrawParamDto;

public interface IWithdrawFactory {

    /**
     * 提币落单
     *
     * @param paramDto
     * @return
     */
    void addOrder(WithDrawParamDto paramDto);

    /**
     * 提币
     */
    void execute();
}
