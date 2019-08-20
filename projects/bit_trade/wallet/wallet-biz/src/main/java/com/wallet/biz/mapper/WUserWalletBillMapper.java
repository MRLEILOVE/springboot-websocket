package com.wallet.biz.mapper;



import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wallet.biz.dto.WalletDto;
import com.wallet.biz.pojo.WUserWalletBill;
import com.wallet.biz.vo.WUserWalletBillVo;


/**
 * <p>
 * 用户钱包账单 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */

public interface WUserWalletBillMapper extends BaseMapper<WUserWalletBill> {


    List<WUserWalletBillVo> selectRechargePage(Page<WUserWalletBillVo> page, WalletDto walletDto);
}
