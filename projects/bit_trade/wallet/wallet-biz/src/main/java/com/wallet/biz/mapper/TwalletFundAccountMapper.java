package com.wallet.biz.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wallet.biz.pojo.TWalletFundAccount;
import com.wallet.biz.vo.AssetsVO;


public interface TwalletFundAccountMapper extends BaseMapper<TWalletFundAccount> {
    /**
     * 资金账户改变金额
     */
    Integer changeMoney(TWalletFundAccount walletFundAccount);

    /**
     * 获取用户资金账户资产
     * @param userId 用户id
     * @return
     */
    List<AssetsVO> getAssetsVO(@Param("userId") Long userId);

    /**
     * 冻结划转金额
     * @param id 钱包id
     * @param num 数量
     * @param version 版本号
     * @return 修改成功数据条数
     */
    Integer modifyTransferFrozen(@Param("id") Integer id, @Param("num") BigDecimal num, @Param("version") Integer version);

    /**
     * 释放划转解冻
     * @param id 钱包id
     * @param num 数量
     * @param version 版本号
     * @return
     */
    Integer decreaseFreeze(@Param("id") Integer id,@Param("num") BigDecimal num,@Param("version") Integer version);
}
