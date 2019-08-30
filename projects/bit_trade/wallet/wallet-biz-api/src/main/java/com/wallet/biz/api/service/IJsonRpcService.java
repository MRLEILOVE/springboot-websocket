package com.wallet.biz.api.service;


import java.math.BigDecimal;

public interface IJsonRpcService {


    /**
     * 导入钱包
     *
     * @param address
     */
    void importAddress(String address);

    boolean validateAddress(String address);

    Object getomni_getbalance(String address, Integer id);



    BigDecimal getBalance(String address);


}

