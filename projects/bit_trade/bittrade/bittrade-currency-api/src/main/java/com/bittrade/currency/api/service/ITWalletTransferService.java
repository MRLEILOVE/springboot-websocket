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
    ReturnDTO transferOfFundsB2C(TransferDto transferDto) throws Exception;

    /**
     * 币币账户充值
     * @param transferDto
     * @return 成功：succ
     */
    String biBiAccountEntry(TransferDto transferDto);

    /**
     * 币币账户出账
     * @param transferDto
     * @return 成功：succ
     */
    String biBiAccountOut(TransferDto transferDto);

    /**
     * 查询用户钱包可用余额
     * @param userId 用户id
     * @param currencyName 币种名称
     * @return 钱包余额
     */
    String availableBalance(Long userId, String currencyName);
}
