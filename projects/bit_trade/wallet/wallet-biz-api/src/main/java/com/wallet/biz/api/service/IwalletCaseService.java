package com.wallet.biz.api.service;

import com.bittrade.pojo.vo.CoinTypeVO;
import com.bittrade.pojo.vo.WalletAddressVO;
import com.bittrade.pojo.vo.WithdrawBillParamVo;
import com.core.common.DTO.ReturnDTO;


public interface IwalletCaseService {

    ReturnDTO checkparam(CoinTypeVO coinTypeVO, Long userID);

    ReturnDTO auditStatus();

    ReturnDTO confirmTibi(WithdrawBillParamVo withdrawBillParamVo,Long userID);

    ReturnDTO chongbi(Long userId, CoinTypeVO coinTypeVO);

    ReturnDTO rechargeRecord(Long userId/*, CoinTypeVO coinTypeVO*/);

    ReturnDTO withdrawRecord(Long userId/*, CoinTypeVO coinTypeVO*/);

    ReturnDTO qrCode(Long userId, CoinTypeVO coinTypeVO);

    ReturnDTO addaddress(Long userId, WalletAddressVO walletAddressVO);

    void BillToAccount();

    void OrderToBill();
}
