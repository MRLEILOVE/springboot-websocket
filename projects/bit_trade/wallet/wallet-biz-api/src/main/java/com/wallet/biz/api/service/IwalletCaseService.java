package com.wallet.biz.api.service;

import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.pojo.vo.CoinTypeVO;
import com.wallet.biz.pojo.vo.WalletAddressVO;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;


public interface IwalletCaseService {
    ReturnDTO showfee();

    ReturnDTO showmaxMin();

    ReturnDTO checkparam();

    ReturnDTO auditStatus();

    ReturnDTO confirmTibi(WithdrawBillParamVo withdrawBillParamVo,Long userID);

    ReturnDTO chongbi(Long userId, CoinTypeVO coinTypeVO);

    ReturnDTO rechargeRecord(Long userId, CoinTypeVO coinTypeVO);

    ReturnDTO withdrawRecord(Long userId, CoinTypeVO coinTypeVO);

    ReturnDTO qrCode(Long userId, CoinTypeVO coinTypeVO);

    ReturnDTO addaddress(Long userId, WalletAddressVO walletAddressVO);
}
