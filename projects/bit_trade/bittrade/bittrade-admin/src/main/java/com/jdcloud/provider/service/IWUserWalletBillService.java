package com.jdcloud.provider.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.WUserWalletBillDto;
import com.jdcloud.provider.dto.WithdrawWalletBillDto;
import com.jdcloud.provider.pojo.WUserWalletBill;
import com.jdcloud.provider.pojo.vo.WUserWalletBillVo;
import com.jdcloud.provider.pojo.vo.WithdrawWalletBillVo;


/**
 * <p>
 * 用户钱包账单 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */
public interface IWUserWalletBillService extends IService<WUserWalletBill> {


    Page<WUserWalletBillVo> getNewWithDraw(Page<WUserWalletBillVo> page, WUserWalletBillDto wUserWalletBillDto);

    Page<WithdrawWalletBillVo> getwithdrawRechargeListNew(Page<WithdrawWalletBillVo> page, WithdrawWalletBillDto dto);

    WithdrawWalletBillVo getWithdrawRechargeDetail(Long id);
}
