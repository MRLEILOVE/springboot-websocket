package com.wallet.service.impl;

import com.alibaba.fastjson.JSON;
import com.wallet.constant.DirectionConstant;
import com.wallet.constant.FlagConstant;
import com.wallet.constant.TradeStepConstant;
import com.wallet.constant.TypeConstant;
import com.wallet.dto.BlockInfoDto;
import com.wallet.dto.RawtransactionDto;
import com.wallet.dto.VoutDto;
import com.wallet.entity.CoinConfig;
import com.wallet.entity.UserWallet;
import com.wallet.entity.UserWalletBill;
import com.wallet.service.*;
import com.wallet.utils.IdUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service("rechargeStrategyBTC")
public class RechargeStrategyBTC implements IRechargeStrategy {

    @Autowired
    private IJsonRpcService jsonRpcService;
    @Autowired
    private CoinConfigService coinConfigService;
    @Autowired
    private UserWalletService userWalletService;
    @Autowired
    private UserWalletBillService userWalletBillService;

    @Override
    public void execute(CoinConfig coinConfig) {
        //已经被扫过的区块高度
        BigInteger oldBlock = coinConfig.getScanBlock();

        //当前区块最新高度
        BigInteger currentBlock = jsonRpcService.getLastBlock();

        String oldBlockHash = "";

        while (currentBlock.compareTo(oldBlock) > 0) {
            oldBlock = oldBlock.add(BigInteger.ONE);
            oldBlockHash = jsonRpcService.getBlockHash(oldBlock);
            BlockInfoDto blockInfoDto = jsonRpcService.getBlockInfo(oldBlockHash);
            List<String> txList = blockInfoDto.getTx();
            for (String tx : txList) {
                handleTxId(coinConfig, tx, blockInfoDto.getHeight());
            }

            coinConfigService.updateById(CoinConfig.builder()
                    .id(coinConfig.getId())
                    .scanBlock(oldBlock).build());
        }
    }


    private void handleTxId(CoinConfig coinConfig, String txId, BigInteger block) {
        //查询交易详情
        RawtransactionDto rawtransactionDto = jsonRpcService.getRawTransaction(txId);

        //判断是否为BTC交易,如果不是跳过
        if (!isBTCTrade(rawtransactionDto)) {
            return;
        }

        handle(coinConfig, rawtransactionDto, block);
    }

    private boolean isBTCTrade(RawtransactionDto rawtransactionDto) {
        List<VoutDto> voutDtoList = rawtransactionDto.getVout();
        for (VoutDto voutDto : voutDtoList) {
            if (TypeConstant.TOKEN_TYPE.equals(voutDto.getScriptPubKey().getType())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 转入操作
     *
     * @return
     */
    private void handle(CoinConfig coinConfig, RawtransactionDto rawtransactionDto, BigInteger block) {

        List<VoutDto> voutDtoList = rawtransactionDto.getVout();
        for (VoutDto voutDto : voutDtoList) {
            if (voutDto.getScriptPubKey() != null
                    && voutDto.getScriptPubKey().getAddresses() != null
                    && voutDto.getScriptPubKey().getAddresses().get(0) != null) {

                UserWallet userWallet = userWalletService.cacheUserWallet(coinConfig.getCoinType(), voutDto.getScriptPubKey().getAddresses().get(0));

                if (userWallet == null) {
                    continue;
                }
                try {
                    userWalletBillService.save(
                            UserWalletBill.builder()
                                    .userId(userWallet.getUserId())
                                    .orderId(IdUtils.nextId())
                                    .direction(DirectionConstant.IN)
                                    .coinType(coinConfig.getCoinType())
                                    .token(coinConfig.getToken())
                                    .contract(coinConfig.getContract())
                                    .senderAddress("")
                                    .receiverAddress(userWallet.getAddress())
                                    .amount(voutDto.getValue())
                                    .block(block)
                                    .tx(rawtransactionDto.getTxid())
                                    .tradeStep(TradeStepConstant.PACKAGED)
                                    .flag(FlagConstant.INIT)
                                    .build());
                } catch (DuplicateKeyException e) {
                    log.warn("RechargeStrategyBTC.handleIn.转入。param={}", JSON.toJSONString(rawtransactionDto), e);
                }

            }
        }
    }
}
