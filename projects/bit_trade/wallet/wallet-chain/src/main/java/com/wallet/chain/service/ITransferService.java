package com.wallet.chain.service;

import com.wallet.chain.dto.TransferParamDto;
import com.wallet.chain.dto.TransferReultDto;
import com.wallet.chain.dto.WithDrawParamDto;
import com.wallet.chain.dto.WithDrawResultDto;

public interface ITransferService {

    /**
     * 转移【归集钱包到冷钱包及其他钱包】
     *
     * @param paramDto
     * @return
     */
    TransferReultDto transfer(TransferParamDto paramDto);
}
