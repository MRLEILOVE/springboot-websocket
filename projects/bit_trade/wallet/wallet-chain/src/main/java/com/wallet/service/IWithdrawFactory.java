package com.wallet.service;

import com.wallet.dto.WithDrawParamDto;

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
