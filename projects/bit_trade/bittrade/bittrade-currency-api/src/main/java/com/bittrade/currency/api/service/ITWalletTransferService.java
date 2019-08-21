package com.bittrade.currency.api.service;

import com.bittrade.__default.service.IDefaultTWalletTransferService;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.TWalletTransfer;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.core.common.DTO.ReturnDTO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITWalletTransferService extends IDefaultTWalletTransferService<TWalletTransfer, TWalletTransferDTO, TWalletTransferVO> {
    /**
     * 资金划转
     */
    ReturnDTO transferOfFunds(TransferDto transferDto) throws Exception;

    /**
     * 查询用户钱包可用余额
     * @param userId 用户id
     * @param accountId 账户id
     * @param currencyId 币种id
     * @return
     */
    String availableBalance(Long userId, Long accountId, Long currencyId);
}
