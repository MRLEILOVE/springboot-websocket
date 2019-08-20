package com.wallet.service;


import org.bitcoinj.core.UTXO;

import com.wallet.dto.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface IJsonRpcService {

    BigInteger getLastBlock();

    /**
     * 获取token余额
     *
     * @return
     */
    BigDecimal getTokenBalance(String address, String contract);

    /**
     * 查询区块的交易数量
     *
     * @param block
     * @return
     */
    List<String> listTokenBlockTransactions(BigInteger block);

    /**
     * 查询订单详情
     */
    OmniTransactionResultDto getTokenTransaction(String txId);

    /**
     * 获取手续费率
     *
     * @return
     */
    FeeRateDto getFeeRate();

    /**
     * 广播交易
     *
     * @param sign
     * @return
     */
    String sendRawTransaction(String sign);


    /**
     * 查询交易信息：从块[BuffHASH]止，获取最近的全部交易【不包括blockHash的交易】
     *
     * @param blockHash
     * @return
     */
    ListSinceBlockDto listSinceBlock(String blockHash);

    /**
     * 导入钱包
     *
     * @param address
     */
    void importAddress(String address);

    /**
     * 获取未花费
     *
     * @param address
     * @return
     */
    List<UTXO> getPointUnspent(String address);

    /**
     * 获取BTC【必须是节点维护的钱包】
     *
     * @param address
     * @return
     */
    BigDecimal getBalance(String address);

    /**
     * 解析签名
     *
     * @param sign
     * @return
     */
    DecoderawtransactionDto decodeRawTransaction(String sign);

    /**
     * 校验地址
     *
     * @param address
     * @return
     */
    boolean validateAddress(String address);

    /**
     * 根据区块获取hash
     *
     * @param block
     * @return
     */
    String getBlockHash(BigInteger block);

    /**
     * 获取对应区块信息
     *
     * @param hash
     * @return
     */
    BlockInfoDto getBlockInfo(String hash);

    /**
     * 获取对应交易信息
     *
     * @param txHash
     * @return
     */
    RawtransactionDto getRawTransaction(String txHash);
}

