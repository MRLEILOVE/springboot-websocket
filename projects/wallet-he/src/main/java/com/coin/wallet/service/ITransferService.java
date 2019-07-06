package com.coin.wallet.service;

import com.coin.wallet.dto.TransferParamDto;
import com.coin.wallet.dto.TransferReultDto;
import com.coin.wallet.dto.WithDrawParamDto;
import com.coin.wallet.dto.WithDrawResultDto;

public interface ITransferService {

    /**
     * 转移【归集钱包到冷钱包及其他钱包】
     *
     * @param paramDto
     * @return
     */
    TransferReultDto transfer(TransferParamDto paramDto);
}
