package com.walletbiz.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.common.DTO.ReturnDTO;
import com.core.tool.BigDecimalUtil;
import com.core.web.tool.WebUtil;
import com.walletbiz.Exception.FlowException;
import com.walletbiz.enumer.CoinType;
import com.walletbiz.mapper.TAccountConfigMapper;
import com.walletbiz.pojo.GBetaAccount;
import com.walletbiz.pojo.TAccountConfig;
import com.walletbiz.service.GBetaAccountService;
import com.walletbiz.service.ParameterConfigService;
import com.walletbiz.utils.RedisKeyUtil;
import com.walletbiz.vo.BetaTotal;
import com.walletbiz.vo.ConversionVo;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/betaAcoount")
public class BetaAccountController {
    @Autowired
    private GBetaAccountService betaAccountService;
    @Autowired
    private ParameterConfigService parameterConfigService;
    @Autowired
    private TAccountConfigMapper accountConfigMapper;

    @PostMapping("/conversionTotal")
    @ApiOperation(value = "总资金折合", notes = "总资金折合")
    public ReturnDTO<ConversionVo> totalConversion() throws FlowException {
        Long userId = WebUtil.getLoginUser().getUser_id();
        if (userId == null) {
            throw new FlowException("用户未登录");
        }

        //usdt转人民币汇率
        BigDecimal USDT_TO_CNY_RATE = parameterConfigService.getRateByKey(RedisKeyUtil.USD_TO_CNY_RATE_KEY);
        //btc转cny汇率
        BigDecimal BTC_TO_CNY_RATE = parameterConfigService.getRateByKey(RedisKeyUtil.BTC_TO_CNY_RATE);
        //BITT_CNY汇率
        BigDecimal BITT_TO_CNY_RATE = parameterConfigService.getRateByKey(RedisKeyUtil.BITT_TO_CNY_RATE);

        //查询用户在该账户下的币种列表
        List<GBetaAccount> betaAccounts = betaAccountService.selectUserBetaAccount(userId);

        //先转人名币，再转usdt
        BigDecimal amount = new BigDecimal(0);//人民币总金额
        for (GBetaAccount betaAccount : betaAccounts) {
            TAccountConfig config = new TAccountConfig();
            config.setAccountType(betaAccount.getProductType());//钱包类型
            QueryWrapper<TAccountConfig> qw = new QueryWrapper<TAccountConfig>(config);
            TAccountConfig accountConfig = accountConfigMapper.selectOne(qw);
            if (CoinType.BTC.toString().equals(accountConfig.getValue())) {
                amount = amount.add((betaAccount.getBalance().add(betaAccount.getUsedMargin()).multiply(BTC_TO_CNY_RATE)));
            } else if (CoinType.USDT.toString().equals(accountConfig.getValue())) {
                amount = amount.add((betaAccount.getBalance().add(betaAccount.getUsedMargin()).multiply(USDT_TO_CNY_RATE)));
            }else if(CoinType.BITT.toString().equals(accountConfig.getValue())){
                amount = amount.add((betaAccount.getBalance().add(betaAccount.getUsedMargin()).multiply(BITT_TO_CNY_RATE)));
            }
        }

        //USDT保留四位小数精度计算
        BigDecimal usdtAmount = amount.divide(USDT_TO_CNY_RATE,4, BigDecimal.ROUND_HALF_UP);
        //CNY保留两位小数精度计算
        amount = BigDecimalUtil.turnDown(amount,2);

        ConversionVo conversionVo = new ConversionVo();
        conversionVo.setUSDT(usdtAmount);
        conversionVo.setCNY(amount);
        return ReturnDTO.ok(conversionVo);
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询当前用户的beta账户币种余额列表", notes = "查询当前用户的beta账户币种余额列表")
    public ReturnDTO<List<BetaTotal>> list() throws FlowException {
        Long userId = WebUtil.getLoginUser().getUser_id();
        if (userId == null) {
            throw new FlowException("用户未登录");
        }
        List<BetaTotal> betaAccounts = betaAccountService.selectTotal(userId);
        return ReturnDTO.ok(betaAccounts);
    }

}
