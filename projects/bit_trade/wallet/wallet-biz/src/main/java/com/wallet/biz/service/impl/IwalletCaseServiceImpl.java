package com.wallet.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.api.service.*;
import com.wallet.biz.pojo.model.WOrder;
import com.wallet.biz.pojo.model.WUserWallet;
import com.wallet.biz.pojo.vo.AddressParamDto;
import com.wallet.biz.pojo.vo.AddressResultDto;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;
import com.wallet.biz.tool.SnowFlake;
import com.wallet.biz.utils.AesUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    IWWalletAccountService wWalletAccountService;
    @Autowired
    IWWalletBillService walletBillService;
    @Autowired
    IWOrderService orderService;
    @Autowired
    IWUserWalletService wUserWalletService;
    @Autowired
    private NetworkParameters networkParameters;
    @Value("${btc.user-wallet.encrypt-key}")
    private String encryptKey;


    @Override
    public ReturnDTO auditStatus(/*PageDto pageDto*/) {
        return null;
    }

    @Override
    public ReturnDTO confirmTibi(WithdrawBillParamVo withdrawBillParamVo,Long userID) {
        SnowFlake snowFlake = new SnowFlake(1, 1);
        WOrder daworder = WOrder.builder()
                .userId(userID)
                .orderId(String .valueOf(snowFlake.nextId()))
                .orderType(-1)
                .coinType(withdrawBillParamVo.getCoinType())
                .token(withdrawBillParamVo.getToken())
                .fee(new BigDecimal(withdrawBillParamVo.getFree()))
                .amount(new BigDecimal(withdrawBillParamVo.getAmount()))
                .receiverAddress(withdrawBillParamVo.getReceiverAddress())
                .type(1)
                .build();
        return orderService.addOrder(daworder);
    }

    @Override
    public ReturnDTO chongbi(Long userId, AddressParamDto addressParamDto) {
        WUserWallet userWallet = wUserWalletService.getOne(new QueryWrapper<>(WUserWallet.builder()
                .userId(userId)
                .coinType(addressParamDto.getCoinType())
                .build()), true);

        if (userWallet == null) {
            //创建钱包
            try {
                ECKey key = new ECKey();
                userWallet = WUserWallet.builder()
                        .userId(userId)
                        .coinType(addressParamDto.getCoinType())
                        .address(key.toAddress(networkParameters).toBase58())
                        .privateKey(AesUtils.aesEncrypt(key.getPrivateKeyAsWiF(networkParameters), encryptKey))
                        .flag((byte)1)
                        .build();
                wUserWalletService.save(userWallet);
            } catch (Exception e) {
//                log.error("CreateAddressStrategyBTC.execute={}", JSON.toJSONString(AddressParamDto), e);
                return ReturnDTO.error("生成钱包地址失败，请重试");
            }
        }

        return ReturnDTO.ok(AddressResultDto.builder()
                .userId(userWallet.getUserId())
                .token(addressParamDto.getToken())
                .address(userWallet.getAddress())
                .build());
    }
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

}
