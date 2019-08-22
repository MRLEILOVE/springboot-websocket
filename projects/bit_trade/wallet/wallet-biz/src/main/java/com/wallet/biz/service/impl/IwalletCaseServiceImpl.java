package com.wallet.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.api.service.IWWalletAccountService;
import com.wallet.biz.pojo.model.WCoinConfig;
import com.wallet.biz.pojo.model.WWalletAccount;
import com.wallet.biz.pojo.vo.WithdrawBillParamVo;
import com.wallet.biz.service.IwalletCaseService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class IwalletCaseServiceImpl implements IwalletCaseService {
/*    @Autowired
    private ParameterConfigService parameterConfigService;
    @Autowired
    private IJsonRpcService jsonRpcService;
*/
    @Autowired
    IWWalletAccountService wWalletAccountService;

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
 //       Long user_id = RequestUtil.getCurrentUser().getUser_id();
/*
        EntityWrapper<PersonalCard>entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("user_id",user_id);
        PersonalCard personalCard = personalCardService.selectOne(entityWrapper);
        if (null==personalCard){
            return WrapMapper.error("用户未实名认证");
        }
        Integer authStauts = personalCard.getAuthStauts();
        if (!AuthStatusEnum.authType.SUCCESS.getCode().equals(authStauts)){
            return WrapMapper.error("用户实名认证审核还未通过");
        }
*//*
        EntityWrapper<TWalletFundAccount> entityWrapper1=new EntityWrapper<>();
        entityWrapper1.eq("user_id",user_id).eq("currency",withDrawParamVo.getToken());
        TWalletFundAccount tWalletFundAccount = iTwalletFundAccountService.selectOne(entityWrapper1);
        int i = tWalletFundAccount.getTotal().compareTo(new BigDecimal(withDrawParamVo.getAmount()).add(new BigDecimal(withDrawParamVo.getFree())));
        if (i<0){
            return WrapMapper.error("提币超过用户当前余额");
        }*/
        WWalletAccount walletAccount = wWalletAccountService.getOne(new QueryWrapper<>(WWalletAccount.builder()
  /*              .userId(user_id).currencyId(withdrawBillParamVo.getToken())
               .coinType(withdrawBillParamVo.getCoinType())
                .token(withdrawBillParamVo.getToken())
                .valid("E")*/
                .build()), true);
        if(null == walletAccount){
            return ReturnDTO.error("该币种暂时不支持提币");
        }
 /*       UserPayPassword userPayPassword=userPayPasswordService.selectByUserId(user_id);
        if (null==userPayPassword){
            return WrapMapper.error("支付密码未设置");
        }
        String idStr = Long.toString(user_id, 36);//userId
        String md5Password = DigestUtils.md5Hex(withDrawParamVo.getPassword() + idStr);
        if (!userPayPassword.getPassword().equals(md5Password)){
            return WrapMapper.error("密码不正确");
        }

        Long id = (Long)redisTemplate.opsForValue().get("user" + user_id);
        if (null!=id&&id.equals(user_id)){
            return WrapMapper.error("3秒内不可重复提交订单");
        }

        redisTemplate.opsForValue().set("user"+user_id,user_id);
        redisTemplate.expire("user"+user_id,3, TimeUnit.SECONDS);
        SnowFlake snowFlake = new SnowFlake(1, 1);

        BigDecimal add = (new BigDecimal(withDrawParamVo.getAmount()).add(new BigDecimal(withDrawParamVo.getFree())));
        BigDecimal subtract = (new BigDecimal(withDrawParamVo.getAmount()).add(new BigDecimal(withDrawParamVo.getFree())));

        tWalletFundAccount.setTransferFrozen(tWalletFundAccount.getTransferFrozen().add(add));
        tWalletFundAccount.setTotal(tWalletFundAccount.getTotal().subtract(subtract));
        TOrder order=new TOrder();
        order.setAmount(new BigDecimal(withDrawParamVo.getAmount()));
        order.setOrderId(String .valueOf(snowFlake.nextId()));
        order.setReceiverAddress(withDrawParamVo.getReceiverAddress());
        order.setToken(withDrawParamVo.getToken());
        order.setUserId(user_id);
        order.setType(AuditEnum.orderType.NOTAUDITED.getCode());
        order.setFee(new BigDecimal(withDrawParamVo.getFree()));

        boolean insert1 = iTwalletFundAccountService.updateById(tWalletFundAccount);
        boolean insert = this.insert(order);
        if (insert&&insert1){
            return WrapMapper.ok("您的提币申请已经成功提交，请等待人工审核!");
        }
//        TOrder order2= TOrder;
        return WrapMapper.error("充值出错");*/
        return null;
    }
}
