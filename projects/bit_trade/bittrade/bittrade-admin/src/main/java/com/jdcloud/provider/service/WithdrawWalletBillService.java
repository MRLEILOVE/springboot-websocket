package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.WithdrawWalletBillDto;
import com.jdcloud.provider.pojo.WithdrawWalletBill;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.MentionMoney;
import com.jdcloud.provider.pojo.vo.WithdrawWalletBillVo;

/**
 * <p>
 * 提币钱包账单 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-31
 */
public interface WithdrawWalletBillService extends IService<WithdrawWalletBill> {

    Page<WithdrawWalletBillVo> selectWithdrawWalletBill(Page<WithdrawWalletBillVo> page, WithdrawWalletBillDto dto);

    WithdrawWalletBillVo selectWithdrawWalletBillDetail(Long id);

    MentionMoney getMentionMoneyCount();

    MentionMoney getCoinCount();
}
