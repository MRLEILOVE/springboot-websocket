package com.jdcloud.provider.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.WUserWalletBillDto;
import com.jdcloud.provider.mapper.WWithdrawWalletBillMapper;
import com.jdcloud.provider.pojo.WWithdrawWalletBill;
import com.jdcloud.provider.pojo.vo.WWithdrawWalletBillVo;
import com.jdcloud.provider.service.IWWithdrawWalletBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 提币钱包账单 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */
@Service
public class WWithdrawWalletBillServiceImpl extends ServiceImpl<WWithdrawWalletBillMapper, WWithdrawWalletBill> implements IWWithdrawWalletBillService {

    @Override
    public Page<WWithdrawWalletBillVo> getWithdrawWalletBill(Page<WWithdrawWalletBillVo> page, WUserWalletBillDto wUserWalletBillDto) {
        return page.setRecords(baseMapper.getWithdrawWalletBill(page,wUserWalletBillDto));
    }
}
