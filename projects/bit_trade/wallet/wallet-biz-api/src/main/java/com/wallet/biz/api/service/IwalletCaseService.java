package com.wallet.biz.api.service;

import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.pojo.vo.AddressParamDto;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;


public interface IwalletCaseService {
    ReturnDTO showfee();

    ReturnDTO showmaxMin();

    ReturnDTO checkparam();

    ReturnDTO auditStatus();

    ReturnDTO confirmTibi(WithdrawBillParamVo withdrawBillParamVo,Long userID);

    ReturnDTO chongbi(Long userId, AddressParamDto addressParamDto);
}
