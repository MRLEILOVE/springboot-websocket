package com.walletbiz.service;

import java.util.Map;

import com.core.common.DTO.ReturnDTO;
import com.walletbiz.dto.JudgmentDto;
import com.walletbiz.dto.PageDto;

public interface IwalletChainService {
    ReturnDTO<String> showfee();

    ReturnDTO<Map<String, Object>> showmaxMin();

    ReturnDTO<String> checkparam(JudgmentDto withDrawParamVo);

    ReturnDTO<String> auditStatus(PageDto pageDto);
}
