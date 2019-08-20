package com.walletbiz.mapper;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walletbiz.dto.WalletDto;
import com.walletbiz.pojo.WWithdrawWalletBill;
import com.walletbiz.vo.WUserWalletBillVo;


/**
 * <p>
 * 提币钱包账单 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */

public interface WWithdrawWalletBillMapper extends BaseMapper<WWithdrawWalletBill> {



    List<WUserWalletBillVo> selectWithPage(Page<WUserWalletBillVo> page, WalletDto walletDto);
}
