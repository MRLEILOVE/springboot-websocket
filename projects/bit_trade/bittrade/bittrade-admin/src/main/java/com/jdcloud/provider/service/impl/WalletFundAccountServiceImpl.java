package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.WalletFundAccountDto;
import com.jdcloud.provider.pojo.WalletFundAccount;
import com.jdcloud.provider.mapper.WalletFundAccountMapper;
import com.jdcloud.provider.pojo.vo.WalletFundAccountVo;
import com.jdcloud.provider.service.WalletFundAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资金账户表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-23
 */
@Service
public class WalletFundAccountServiceImpl extends ServiceImpl<WalletFundAccountMapper, WalletFundAccount> implements WalletFundAccountService {

    /**
     * 用户资产
     * @param page
     * @param dto
     * @return
     */
    @Override
    public Page<WalletFundAccountVo> getWithdrawWalletBill(Page<WalletFundAccountVo> page, WalletFundAccountDto dto) {
        return page.setRecords(baseMapper.getWithdrawWalletBill(page,dto));
    }
}
