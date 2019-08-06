package com.bittrade.currency.api.service;

import com.bittrade.__default.service.IDefaultTWalletService;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.TWalletVO;
import com.core.common.DTO.ReturnDTO;

public interface ITransferService extends IDefaultTWalletService<TWallet, TWalletDTO, TWalletVO>{
    /**
     * 资金划转
     */
    ReturnDTO transferOfFundsB2C(TransferDto transferDto);
}
