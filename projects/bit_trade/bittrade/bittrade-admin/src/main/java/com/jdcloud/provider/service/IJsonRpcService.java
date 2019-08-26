package com.jdcloud.provider.service;


import org.bitcoinj.core.UTXO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface IJsonRpcService {


    /**
     * 导入钱包
     *
     * @param address
     */
    void importAddress(String address);

    boolean validateAddress(String address);

    BigDecimal getTokenBalance(String address, String contract);

   BigDecimal getBalance(String address);
}

