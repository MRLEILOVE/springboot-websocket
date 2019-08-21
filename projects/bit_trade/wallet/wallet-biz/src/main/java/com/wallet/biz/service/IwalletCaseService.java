package com.wallet.biz.service;

import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;


public interface IwalletCaseService {
    ReturnDTO showfee();

    ReturnDTO showmaxMin();

    ReturnDTO checkparam();

    ReturnDTO auditStatus();

    ReturnDTO confirmTibi(WithdrawBillParamVo withdrawBillParamVo);
}
