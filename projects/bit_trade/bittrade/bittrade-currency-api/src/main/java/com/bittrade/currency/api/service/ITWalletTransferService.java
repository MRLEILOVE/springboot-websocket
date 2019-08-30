package com.bittrade.currency.api.service;

import com.bittrade.__default.service.IDefaultTWalletTransferService;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.model.TWalletTransfer;
import com.bittrade.pojo.vo.CoinVo;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.core.common.DTO.ReturnDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
     * @param currencyName 币种名称
     * @return
     */
    BigDecimal availableBalance(Long userId, Long accountId, String currencyName);

    /**
     * 两个账户共同币种
     * @param accountId1 账户1id
     * @param accountId2 账户2id
     * @return 币种列表
     */
    List<CoinVo> togetherCoin(Long accountId1, Long accountId2);
}
