package com.jdcloud.provider.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.WUserWalletBillDto;
import com.jdcloud.provider.dto.WithdrawWalletBillDto;
import com.jdcloud.provider.mapper.WUserWalletBillMapper;
import com.jdcloud.provider.pojo.WUserWalletBill;
import com.jdcloud.provider.pojo.vo.WUserWalletBillVo;
import com.jdcloud.provider.pojo.vo.WithdrawWalletBillVo;
import com.jdcloud.provider.service.IWUserWalletBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户钱包账单 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */
@Service
public class WUserWalletBillServiceImpl extends ServiceImpl<WUserWalletBillMapper, WUserWalletBill> implements IWUserWalletBillService {

    /**
     * 获取提币记录
     *
     * @param page
     * @param wUserWalletBillDto
     * @return
     */
    @Override
    public Page<WUserWalletBillVo> getNewWithDraw(Page<WUserWalletBillVo> page, WUserWalletBillDto wUserWalletBillDto) {
        return page.setRecords(baseMapper.getNewWithDraw(page, wUserWalletBillDto));
    }

    /**
     * 平台充值记录
     *
     * @param page
     * @param dto
     * @return C
     */
    @Override
    public Page<WithdrawWalletBillVo> getwithdrawRechargeListNew(Page<WithdrawWalletBillVo> page, WithdrawWalletBillDto dto) {
        return page.setRecords(baseMapper.getwithdrawRechargeListNew(page, dto));
    }

    /**
     * 平台充值详情
     * @param id
     * @return
     */
    @Override
    public WithdrawWalletBillVo getWithdrawRechargeDetail(Long id) {
        return baseMapper.getWithdrawRechargeDetail(id);
    }


}
