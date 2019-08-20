package com.wallet.biz.mapper;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wallet.biz.dto.WalletDto;
import com.wallet.biz.pojo.WWithdrawWalletBill;
import com.wallet.biz.vo.WUserWalletBillVo;


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
