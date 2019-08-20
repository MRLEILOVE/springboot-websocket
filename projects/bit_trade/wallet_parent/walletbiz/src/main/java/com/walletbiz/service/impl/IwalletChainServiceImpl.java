package com.walletbiz.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.common.DTO.ReturnDTO;
import com.walletbiz.dto.JudgmentDto;
import com.walletbiz.dto.PageDto;
import com.walletbiz.enumer.CoinType;
import com.walletbiz.pojo.ParameterConfig;
import com.walletbiz.service.IJsonRpcService;
import com.walletbiz.service.IwalletChainService;
import com.walletbiz.service.ParameterConfigService;
import com.walletbiz.vo.MaxMinFeeVo;



@Service
public class IwalletChainServiceImpl implements IwalletChainService {
    @Autowired
    private ParameterConfigService parameterConfigService;
    @Autowired
    private IJsonRpcService jsonRpcService;

    @Override
    public ReturnDTO showfee(){
        Map<String, Object> map = new HashMap<>();
        MaxMinFeeVo maxMinFeeVo1 = new MaxMinFeeVo();
        MaxMinFeeVo maxMinFeeVo3 = new MaxMinFeeVo();
        QueryWrapper<ParameterConfig> btcMaxWrapper = new QueryWrapper<>();
        QueryWrapper<ParameterConfig> btcMinWrapper = new QueryWrapper<>();
        QueryWrapper<ParameterConfig> usdtMaxWrapper = new QueryWrapper<>();
        QueryWrapper<ParameterConfig> usdtMinWrapper = new QueryWrapper<>();

        btcMaxWrapper.eq("dictionary_key", "btcMaxFee");
        btcMinWrapper.eq("dictionary_key", "btcMinFee");
        usdtMaxWrapper.eq("dictionary_key", "usdtMaxFee");
        usdtMinWrapper.eq("dictionary_key", "usdtMinFee");

        ParameterConfig btcMax = parameterConfigService.getOne(btcMaxWrapper);
        ParameterConfig btcMin = parameterConfigService.getOne(btcMinWrapper);
        ParameterConfig usdtMax = parameterConfigService.getOne(usdtMaxWrapper);
        ParameterConfig usdtMinFee = parameterConfigService.getOne(usdtMinWrapper);

        maxMinFeeVo1.setMax(btcMax.getDictionaryValue());
        maxMinFeeVo1.setMin(btcMin.getDictionaryValue());
        maxMinFeeVo3.setMax(usdtMax.getDictionaryValue());
        maxMinFeeVo3.setMin(usdtMinFee.getDictionaryValue());

        map.put(CoinType.BTC.toString(), maxMinFeeVo1);
        map.put(CoinType.USDT.toString(), maxMinFeeVo3);

        return ReturnDTO.ok(map);
    }

    @Override
    public ReturnDTO<Map<String, Object>> showmaxMin() {
        Map<String, Object> map = new HashMap<>();
        MaxMinFeeVo maxMin = new MaxMinFeeVo();
        MaxMinFeeVo maxMinFeeVo3 = new MaxMinFeeVo();
        QueryWrapper<ParameterConfig> btcMinWrapper = new QueryWrapper<>();
        QueryWrapper<ParameterConfig> btcMaxWrapper = new QueryWrapper<>();
        QueryWrapper<ParameterConfig> usdtMinWrapper = new QueryWrapper<>();
        QueryWrapper<ParameterConfig> usdtMaxWrapper = new QueryWrapper<>();

        btcMinWrapper.eq("dictionary_key", "btcMin");
        btcMaxWrapper.eq("dictionary_key", "btcMax");
        usdtMinWrapper.eq("dictionary_key", "usdtMin");
        usdtMaxWrapper.eq("dictionary_key", "usdtMax");

        ParameterConfig btcMin = parameterConfigService.getOne(btcMinWrapper);
        ParameterConfig btcMax = parameterConfigService.getOne(btcMaxWrapper);
        ParameterConfig usdtMin = parameterConfigService.getOne(usdtMinWrapper);
        ParameterConfig usdtMax = parameterConfigService.getOne(usdtMaxWrapper);

        maxMin.setMin(btcMin.getDictionaryValue());
        maxMin.setMax(btcMax.getDictionaryValue());
        maxMinFeeVo3.setMin(usdtMin.getDictionaryValue());
        maxMinFeeVo3.setMax(usdtMax.getDictionaryValue());

        map.put(CoinType.BTC.toString(), maxMin);
        map.put(CoinType.USDT.toString(), maxMinFeeVo3);
        return ReturnDTO.ok(map);
    }

    @Override
    public ReturnDTO<String> checkparam(JudgmentDto withDrawParamVo) {
        if (null != withDrawParamVo.getAmount() && (withDrawParamVo.getAmount().compareTo(new BigDecimal(0)) > 0)) {
            QueryWrapper<ParameterConfig> maxWrapper = new QueryWrapper<>();
            QueryWrapper<ParameterConfig> minWrapper = new QueryWrapper<>();
            if (CoinType.BTC.toString().equals(withDrawParamVo.getToken())) {
                maxWrapper.eq("dictionary_key", "btcMax");
                ParameterConfig parameterMax = parameterConfigService.getOne(maxWrapper);
                minWrapper.eq("dictionary_key", "btcMin");
                ParameterConfig parameterMin = parameterConfigService.getOne(minWrapper);
                BigDecimal min = new BigDecimal(parameterMin.getDictionaryValue());
                BigDecimal max = new BigDecimal(parameterMax.getDictionaryValue());
                int i1 = min.compareTo(withDrawParamVo.getAmount());
                int i2 = max.compareTo(withDrawParamVo.getAmount());
                //min>fee||max<fee
                if (i1 > 0 || i2 < 0) {
                    return ReturnDTO.error("btc超出提币限额");
                }
            } else if (CoinType.USDT.toString().equals(withDrawParamVo.getToken())) {
                maxWrapper.eq("dictionary_key", "usdtMax");
                ParameterConfig parameterMax = parameterConfigService.getOne(maxWrapper);
                minWrapper.eq("dictionary_key", "usdtMin");
                ParameterConfig parameterMin = parameterConfigService.getOne(minWrapper);
                BigDecimal min = new BigDecimal(parameterMin.getDictionaryValue());
                BigDecimal max = new BigDecimal(parameterMax.getDictionaryValue());
                int i1 = min.compareTo(withDrawParamVo.getAmount());
                int i2 = max.compareTo(withDrawParamVo.getAmount());
                if (i1 > 0 || i2 < 0) {
                    return ReturnDTO.error("usdt超出提币限额");
                }
            }
        }


        if (null != withDrawParamVo.getFree() && (withDrawParamVo.getFree().compareTo(new BigDecimal(0)) > 0)) {
            QueryWrapper<ParameterConfig> maxWrapper = new QueryWrapper<>();
            QueryWrapper<ParameterConfig> minWrapper = new QueryWrapper<>();
            if (CoinType.BTC.toString().equals(withDrawParamVo.getToken())) {
                maxWrapper.eq("dictionary_key", "btcMaxFee");
                ParameterConfig parameterMax = parameterConfigService.getOne(maxWrapper);
                minWrapper.eq("dictionary_key", "btcMinFee");
                ParameterConfig parameterMin = parameterConfigService.getOne(minWrapper);
                BigDecimal min = new BigDecimal(parameterMin.getDictionaryValue());
                BigDecimal max = new BigDecimal(parameterMax.getDictionaryValue());
                System.out.println(min);//0.0005
                System.out.println(withDrawParamVo.getFree());//0.000015
                int i1 = min.compareTo(withDrawParamVo.getFree());
                int i2 = max.compareTo(withDrawParamVo.getFree());
                //min>fee||max<fee
                if (i1 > 0 || i2 < 0) {
                    return ReturnDTO.error("手续费超出限额");
                }
            } else if (CoinType.USDT.toString().equals(withDrawParamVo.getToken())) {
                maxWrapper.eq("dictionary_key", "usdtMaxFee");
                ParameterConfig parameterMax = parameterConfigService.getOne(maxWrapper);
                System.out.println(parameterMax);
                minWrapper.eq("dictionary_key", "usdtMinFee");
                ParameterConfig parameterMin = parameterConfigService.getOne(minWrapper);
                System.out.println(parameterMin);
                BigDecimal min = new BigDecimal(parameterMin.getDictionaryValue());
                BigDecimal max = new BigDecimal(parameterMax.getDictionaryValue());
                int i1 = min.compareTo(withDrawParamVo.getFree());
                int i2 = max.compareTo(withDrawParamVo.getFree());
                if (i1 > 0 || i2 < 0) {
                    return ReturnDTO.error("手续费超出限额");
                }
            }
        }

        if (null != withDrawParamVo.getReceiverAddress() && !"".equals(withDrawParamVo.getReceiverAddress())) {
            boolean b = jsonRpcService.validateAddress(withDrawParamVo.getReceiverAddress());
            if (!b) {
                return ReturnDTO.error("收币地址不正确");
            }
        }


        return ReturnDTO.ok("success");
    }

    @Override
    public ReturnDTO<String> auditStatus(PageDto pageDto) {
        return null;
    }
}
