package com.wallet.biz.service;

import java.util.Map;

import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.dto.JudgmentDto;
import com.wallet.biz.dto.PageDto;

public interface IwalletChainService {
    ReturnDTO<String> showfee();

    ReturnDTO<Map<String, Object>> showmaxMin();

    ReturnDTO<String> checkparam(JudgmentDto withDrawParamVo);

    ReturnDTO<String> auditStatus(PageDto pageDto);
}
