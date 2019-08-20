package com.wallet.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.mapper.GBetaAccountMapper;
import com.wallet.biz.mapper.TAccountConfigMapper;
import com.wallet.biz.pojo.GBetaAccount;
import com.wallet.biz.pojo.TAccountConfig;
import com.wallet.biz.service.GBetaAccountService;
import com.wallet.biz.vo.BetaTotal;

@Service
public class GBetaAccountServiceImpl extends ServiceImpl<GBetaAccountMapper, GBetaAccount> implements GBetaAccountService {
    @Autowired
    private GBetaAccountMapper betaAccountMapper;
    @Autowired
    private TAccountConfigMapper accountConfigMapper;

    /**
     * 用户币种列表及总余额
     */
    @Override
    public List<BetaTotal> selectTotal(Long userId) {
        List<BetaTotal> list = new ArrayList<>();
        /*EntityWrapper<GBetaAccount> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id",userId);
        List<GBetaAccount> gBetaAccounts = betaAccountMapper.selectList(wrapper);*/
        List<GBetaAccount> gBetaAccounts = betaAccountMapper.selectUserBetaAccount(userId);
        gBetaAccounts.forEach(x ->{
            TAccountConfig accountConfig = new TAccountConfig();
            accountConfig.setAccountType(x.getProductType());
            QueryWrapper<TAccountConfig> qw = new QueryWrapper<>(accountConfig);
            TAccountConfig config = accountConfigMapper.selectOne(qw);
            BetaTotal accountTotal = new BetaTotal();
            if(config != null){
                accountTotal.setCurrency(config.getValue());
            }
            accountTotal.setBalance(x.getBalance());
            accountTotal.setUsedMargin(x.getUsedMargin());
            list.add(accountTotal);
        });
        return list;
    }

    /**
     * 查询用户beta账户列表（关联账户-币种 中间表 做成可配置）
     */
    @Override
    public List<GBetaAccount> selectUserBetaAccount(Long userId) {
        return betaAccountMapper.selectUserBetaAccount(userId);
    }
}
