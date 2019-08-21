package com.wallet.biz.service.impl;

import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;
import com.wallet.biz.service.IwalletCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
public class IwalletCaseServiceImpl implements IwalletCaseService {
/*    @Autowired
    private ParameterConfigService parameterConfigService;
    @Autowired
    private IJsonRpcService jsonRpcService;
*/
    @Override
    public ReturnDTO showfee(){
        Map<String, Object> map = new HashMap<>();
      /*  MaxMinFeeVo maxMinFeeVo1 = new MaxMinFeeVo();
        MaxMinFeeVo maxMinFeeVo3 = new MaxMinFeeVo();
        EntityWrapper<ParameterConfig> btcMaxWrapper = new EntityWrapper<>();
        EntityWrapper<ParameterConfig> btcMinWrapper = new EntityWrapper<>();
        EntityWrapper<ParameterConfig> usdtMaxWrapper = new EntityWrapper<>();
        EntityWrapper<ParameterConfig> usdtMinWrapper = new EntityWrapper<>();

        btcMaxWrapper.eq("dictionary_key", "btcMaxFee");
        btcMinWrapper.eq("dictionary_key", "btcMinFee");
        usdtMaxWrapper.eq("dictionary_key", "usdtMaxFee");
        usdtMinWrapper.eq("dictionary_key", "usdtMinFee");

        ParameterConfig btcMax = parameterConfigService.selectOne(btcMaxWrapper);
        ParameterConfig btcMin = parameterConfigService.selectOne(btcMinWrapper);
        ParameterConfig usdtMax = parameterConfigService.selectOne(usdtMaxWrapper);
        ParameterConfig usdtMinFee = parameterConfigService.selectOne(usdtMinWrapper);

        maxMinFeeVo1.setMax(btcMax.getDictionaryValue());
        maxMinFeeVo1.setMin(btcMin.getDictionaryValue());
        maxMinFeeVo3.setMax(usdtMax.getDictionaryValue());
        maxMinFeeVo3.setMin(usdtMinFee.getDictionaryValue());

        map.put(CoinType.BTC.toString(), maxMinFeeVo1);
        map.put(CoinType.USDT.toString(), maxMinFeeVo3);
*/
        return ReturnDTO.ok(map);
    }

    @Override
    public ReturnDTO showmaxMin() {
        Map<String, Object> map = new HashMap<>();
      /*  MaxMinFeeVo maxMin = new MaxMinFeeVo();
        MaxMinFeeVo maxMinFeeVo3 = new MaxMinFeeVo();
        EntityWrapper<ParameterConfig> btcMinWrapper = new EntityWrapper<>();
        EntityWrapper<ParameterConfig> btcMaxWrapper = new EntityWrapper<>();
        EntityWrapper<ParameterConfig> usdtMinWrapper = new EntityWrapper<>();
        EntityWrapper<ParameterConfig> usdtMaxWrapper = new EntityWrapper<>();

        btcMinWrapper.eq("dictionary_key", "btcMin");
        btcMaxWrapper.eq("dictionary_key", "btcMax");
        usdtMinWrapper.eq("dictionary_key", "usdtMin");
        usdtMaxWrapper.eq("dictionary_key", "usdtMax");

        ParameterConfig btcMin = parameterConfigService.selectOne(btcMinWrapper);
        ParameterConfig btcMax = parameterConfigService.selectOne(btcMaxWrapper);
        ParameterConfig usdtMin = parameterConfigService.selectOne(usdtMinWrapper);
        ParameterConfig usdtMax = parameterConfigService.selectOne(usdtMaxWrapper);

        maxMin.setMin(btcMin.getDictionaryValue());
        maxMin.setMax(btcMax.getDictionaryValue());
        maxMinFeeVo3.setMin(usdtMin.getDictionaryValue());
        maxMinFeeVo3.setMax(usdtMax.getDictionaryValue());

        map.put(CoinType.BTC.toString(), maxMin);
        map.put(CoinType.USDT.toString(), maxMinFeeVo3);*/
        return ReturnDTO.ok(map);
    }

    @Override
    public ReturnDTO checkparam(/*JudgmentDto withDrawParamVo*/) {
 /*       if (null != withDrawParamVo.getAmount() && (withDrawParamVo.getAmount().compareTo(new BigDecimal(0)) > 0)) {
            EntityWrapper<ParameterConfig> maxWrapper = new EntityWrapper<>();
            EntityWrapper<ParameterConfig> minWrapper = new EntityWrapper<>();
            if (CoinType.BTC.toString().equals(withDrawParamVo.getToken())) {
                maxWrapper.eq("dictionary_key", "btcMax");
                ParameterConfig parameterMax = parameterConfigService.selectOne(maxWrapper);
                minWrapper.eq("dictionary_key", "btcMin");
                ParameterConfig parameterMin = parameterConfigService.selectOne(minWrapper);
                BigDecimal min = new BigDecimal(parameterMin.getDictionaryValue());
                BigDecimal max = new BigDecimal(parameterMax.getDictionaryValue());
                int i1 = min.compareTo(withDrawParamVo.getAmount());
                int i2 = max.compareTo(withDrawParamVo.getAmount());
                //min>fee||max<fee
                if (i1 > 0 || i2 < 0) {
                    return WrapMapper.error("btc超出提币限额");
                }
            } else if (CoinType.USDT.toString().equals(withDrawParamVo.getToken())) {
                maxWrapper.eq("dictionary_key", "usdtMax");
                ParameterConfig parameterMax = parameterConfigService.selectOne(maxWrapper);
                minWrapper.eq("dictionary_key", "usdtMin");
                ParameterConfig parameterMin = parameterConfigService.selectOne(minWrapper);
                BigDecimal min = new BigDecimal(parameterMin.getDictionaryValue());
                BigDecimal max = new BigDecimal(parameterMax.getDictionaryValue());
                int i1 = min.compareTo(withDrawParamVo.getAmount());
                int i2 = max.compareTo(withDrawParamVo.getAmount());
                if (i1 > 0 || i2 < 0) {
                    return WrapMapper.error("usdt超出提币限额");
                }
            }
        }


        if (null != withDrawParamVo.getFree() && (withDrawParamVo.getFree().compareTo(new BigDecimal(0)) > 0)) {
            EntityWrapper<ParameterConfig> maxWrapper = new EntityWrapper<>();
            EntityWrapper<ParameterConfig> minWrapper = new EntityWrapper<>();
            if (CoinType.BTC.toString().equals(withDrawParamVo.getToken())) {
                maxWrapper.eq("dictionary_key", "btcMaxFee");
                ParameterConfig parameterMax = parameterConfigService.selectOne(maxWrapper);
                minWrapper.eq("dictionary_key", "btcMinFee");
                ParameterConfig parameterMin = parameterConfigService.selectOne(minWrapper);
                BigDecimal min = new BigDecimal(parameterMin.getDictionaryValue());
                BigDecimal max = new BigDecimal(parameterMax.getDictionaryValue());
                System.out.println(min);//0.0005
                System.out.println(withDrawParamVo.getFree());//0.000015
                int i1 = min.compareTo(withDrawParamVo.getFree());
                int i2 = max.compareTo(withDrawParamVo.getFree());
                //min>fee||max<fee
                if (i1 > 0 || i2 < 0) {
                    return WrapMapper.error("手续费超出限额");
                }
            } else if (CoinType.USDT.toString().equals(withDrawParamVo.getToken())) {
                maxWrapper.eq("dictionary_key", "usdtMaxFee");
                ParameterConfig parameterMax = parameterConfigService.selectOne(maxWrapper);
                System.out.println(parameterMax);
                minWrapper.eq("dictionary_key", "usdtMinFee");
                ParameterConfig parameterMin = parameterConfigService.selectOne(minWrapper);
                System.out.println(parameterMin);
                BigDecimal min = new BigDecimal(parameterMin.getDictionaryValue());
                BigDecimal max = new BigDecimal(parameterMax.getDictionaryValue());
                int i1 = min.compareTo(withDrawParamVo.getFree());
                int i2 = max.compareTo(withDrawParamVo.getFree());
                if (i1 > 0 || i2 < 0) {
                    return WrapMapper.error("手续费超出限额");
                }
            }
        }

        if (null != withDrawParamVo.getReceiverAddress() && !"".equals(withDrawParamVo.getReceiverAddress())) {
            boolean b = jsonRpcService.validateAddress(withDrawParamVo.getReceiverAddress());
            if (!b) {
                return WrapMapper.error("收币地址不正确");
            }
        }

*/
        return ReturnDTO.ok("success");
    }

    @Override
    public ReturnDTO auditStatus(/*PageDto pageDto*/) {
        return null;
    }

    @Override
    public ReturnDTO confirmTibi(WithdrawBillParamVo withdrawBillParamVo) {
        return null;
    }
}
