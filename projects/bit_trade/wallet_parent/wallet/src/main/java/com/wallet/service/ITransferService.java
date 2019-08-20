package com.wallet.service;

import com.wallet.dto.TransferParamDto;
import com.wallet.dto.TransferReultDto;
import com.wallet.dto.WithDrawParamDto;
import com.wallet.dto.WithDrawResultDto;

public interface ITransferService {

    /**
     * 转移【归集钱包到冷钱包及其他钱包】
     *
     * @param paramDto
     * @return
     */
    TransferReultDto transfer(TransferParamDto paramDto);
}
