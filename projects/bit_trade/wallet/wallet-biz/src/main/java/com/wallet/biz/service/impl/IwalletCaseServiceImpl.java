package com.wallet.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.api.service.*;
import com.wallet.biz.pojo.model.WCoin;
import com.wallet.biz.pojo.model.WOrder;
import com.wallet.biz.pojo.model.WUserWallet;
import com.wallet.biz.pojo.model.WWalletAddress;
import com.wallet.biz.pojo.vo.CoinTypeVO;
import com.wallet.biz.pojo.vo.AddressResultDto;
import com.wallet.biz.pojo.vo.WalletAddressVO;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;
import com.wallet.biz.utils.AesUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
    IWWalletAddressService wWalletAddressService;
    @Autowired
    IWCoinService wCoinService;
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
//        SnowFlake snowFlake = new SnowFlake(1, 1);
        WOrder daworder = WOrder.builder()
                .userId(userID)
//                .orderId(String .valueOf(snowFlake.nextId()))
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
    public ReturnDTO chongbi(Long userId, CoinTypeVO coinTypeVO) {
        WUserWallet userWallet = wUserWalletService.getOne(new QueryWrapper<>(WUserWallet.builder()
                .userId(userId)
                .coinType(coinTypeVO.getCoinType())
                .build()));

        if (userWallet == null) {
            //创建钱包
            try {
                ECKey key = new ECKey();
                userWallet = WUserWallet.builder()
                        .userId(userId)
                        .coinType(coinTypeVO.getCoinType())
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
                .token(coinTypeVO.getToken())
                .address(userWallet.getAddress())
                .build());
    }

    @Override
    public ReturnDTO rechargeRecord(Long userId, CoinTypeVO coinTypeVO) {
        List<WOrder> list = orderService.list(new QueryWrapper<>(WOrder.builder()
                .userId(userId)
                .coinType(coinTypeVO.getCoinType())
                .token(coinTypeVO.getToken())
                .orderType(1).build()));
        return ReturnDTO.ok(list);
    }

    @Override
    public ReturnDTO withdrawRecord(Long userId, CoinTypeVO coinTypeVO) {
        List<WOrder> list = orderService.list(new QueryWrapper<>(WOrder.builder()
                .userId(userId)
                .coinType(coinTypeVO.getCoinType())
                .token(coinTypeVO.getToken())
                .orderType(-1).build()));
        return ReturnDTO.ok(list);
    }

    @Override
    public ReturnDTO qrCode(Long userId, CoinTypeVO coinTypeVO) {
        WUserWallet userWallet = wUserWalletService.getOne(new QueryWrapper<>(WUserWallet.builder()
                .userId(userId)
                .coinType(coinTypeVO.getCoinType())
                .build()));

        if (userWallet == null || userWallet.getCodeQr()==null) {
            return ReturnDTO.error("该用户还没有二维码");
        }
        return ReturnDTO.ok(userWallet.getCodeQr());
    }

    @Override
    public ReturnDTO addaddress(Long userId, WalletAddressVO walletAddressVO) {
        WCoin wCoin = wCoinService.getOne(new QueryWrapper<>(WCoin.builder()
                .token(walletAddressVO.getTokenType()).build()));
        if (wCoin == null) {
            return ReturnDTO.error("尚不支持该币种");
        }

        WWalletAddress wWalletAddress = wWalletAddressService.getOne(new QueryWrapper<>(WWalletAddress.builder()
                .userId(userId)
                .tokenType(walletAddressVO.getTokenType())
                .address(walletAddressVO.getAddress()).build()));
        if (null == wWalletAddress) {
            wWalletAddress = WWalletAddress.builder()
                    .status((byte) 0)
                    .tokenType(walletAddressVO.getTokenType())
                    .userId(userId)
                    .address(walletAddressVO.getAddress())
                    .name(walletAddressVO.getName())
                    .currencyId(wCoin.getId().intValue())
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now()).build();
        }
        if (wWalletAddress.getStatus() == (byte) 1) {
            return ReturnDTO.error("提币地址已存在，请勿重复添加");
        }
        try {
            wWalletAddress.setStatus((byte) 1);
            boolean insert = wWalletAddressService.saveOrUpdate(wWalletAddress);
            if (insert) {
                return ReturnDTO.ok("提币地址已经成功添加");
            }
        } catch (Exception e) {
            return ReturnDTO.error("提币地址添加出错");
        }
        return ReturnDTO.error("提币地址已存在，请勿重复添加");
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
