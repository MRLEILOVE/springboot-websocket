package com.wallet.chain.service.impl;

import com.alibaba.fastjson.JSON;
import com.wallet.chain.constant.DirectionConstant;
import com.wallet.chain.constant.FlagConstant;
import com.wallet.chain.constant.TradeStepConstant;
import com.wallet.chain.constant.TypeConstant;
import com.wallet.chain.dto.OmniTransactionResultDto;
import com.wallet.chain.entity.CoinConfig;
import com.wallet.chain.entity.UserWallet;
import com.wallet.chain.entity.WalletBill;
import com.wallet.chain.service.*;
import com.wallet.chain.utils.IdUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service("rechargeStrategyBTC_TOKEN")
public class RechargeStrategyBTC_TOKEN implements IRechargeStrategy {

    @Autowired
    private IJsonRpcService jsonRpcService;
    @Autowired
    private CoinConfigService coinConfigService;
    @Autowired
    private UserWalletService userWalletService;
    @Autowired
    private WalletBillService walletBillService;

    @Override
    public void execute(CoinConfig coinConfig) {

        //已经被扫过的区块高度
        BigInteger oldBlock = coinConfig.getScanBlock();

        //当前区块最新高度
        BigInteger currentBlock = jsonRpcService.getLastBlock();

        while (currentBlock.compareTo(oldBlock) > 0) {
            oldBlock = oldBlock.add(BigInteger.ONE);

            //查询本个区块下的交易hash
            List<String> txIdList = jsonRpcService.listTokenBlockTransactions(oldBlock);
            if (txIdList != null && txIdList.size() != 0) {
                txIdList.stream().forEach(txId -> handleTxId(txId, coinConfig));
            }
            coinConfigService.updateById(CoinConfig.builder()
                    .id(coinConfig.getId())
                    .scanBlock(oldBlock).build());
        }
    }

    private void handleTxId(String txId, CoinConfig coinConfig) {

        OmniTransactionResultDto transactionResultDto = jsonRpcService.getTokenTransaction(txId);
        //如果不是对应TOKEN交易直接返回
        if (transactionResultDto == null
                || !TypeConstant.SIMPLE_SEND.equalsIgnoreCase(transactionResultDto.getType())
                || !coinConfig.getContract().equalsIgnoreCase(transactionResultDto.getPropertyid().toString())
                || !transactionResultDto.getValid()) { //交易可能无效
            return;
        }

        //转入
        handleIn(transactionResultDto, coinConfig);

    }

    /**
     * 转入操作
     *
     * @param transactionResultDto
     * @param coinConfig
     * @return
     */
    private boolean handleIn(OmniTransactionResultDto transactionResultDto, CoinConfig coinConfig) {

        //判断是钱包数据
        if (!userWalletService.isMineAddressCache(coinConfig.getCoinType(), transactionResultDto.getReferenceaddress())) {
            return false;
        }

        UserWallet userWallet = userWalletService.cacheUserWallet(coinConfig.getCoinType(), transactionResultDto.getReferenceaddress());
        try {
            walletBillService.save(
                    WalletBill.builder()
                            .userId(userWallet.getUserId())
                            .orderId(IdUtils.nextId())
                            .direction(DirectionConstant.IN)
                            .coinType(coinConfig.getCoinType())
                            .token(coinConfig.getToken())
                            .contract(coinConfig.getContract())
                            .senderAddress(transactionResultDto.getSendingaddress())
                            .receiverAddress(transactionResultDto.getReferenceaddress())
                            .amount(transactionResultDto.getAmount())
                            .block(transactionResultDto.getBlock())
                            .tx(transactionResultDto.getTxid())
                            .tradeStep(TradeStepConstant.PACKAGED)
                            .flag(FlagConstant.INIT)
                            .build());
        } catch (DuplicateKeyException e) {
            log.warn("RechargeStrategyBTC_TOKEN.handleIn.转入。param={}", JSON.toJSONString(transactionResultDto), e);
        }

        return true;
    }
}
