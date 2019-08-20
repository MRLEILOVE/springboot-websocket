package com.wallet.chain.service;

import com.wallet.chain.dto.WithDrawParamDto;

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
