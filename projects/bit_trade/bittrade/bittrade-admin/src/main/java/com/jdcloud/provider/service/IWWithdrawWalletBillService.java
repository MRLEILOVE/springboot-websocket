package com.jdcloud.provider.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.WUserWalletBillDto;
import com.jdcloud.provider.pojo.WWithdrawWalletBill;
import com.jdcloud.provider.pojo.vo.WWithdrawWalletBillVo;


/**
 * <p>
 * 提币钱包账单 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */
public interface IWWithdrawWalletBillService extends IService<WWithdrawWalletBill> {


    Page<WWithdrawWalletBillVo> getWithdrawWalletBill(Page<WWithdrawWalletBillVo> page, WUserWalletBillDto wUserWalletBillDto);
}
