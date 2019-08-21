package com.wallet.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wallet.biz.pojo.model.WWalletAccount;
import com.wallet.biz.pojo.vo.AssetsVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface IWWalletAccountDAO extends BaseMapper<WWalletAccount> {

    /**
     * 资金账户入账
     * @param id 资金账户id
     * @param num 数量
     * @Param version 版本号
     * @return
     */
    Integer fundAccountIn(@Param("id") Long id, @Param("num") BigDecimal num, @Param("version") Integer version);

    /**
     * 资金账户出账
     * @param id 资金账户id
     * @param num 数量
     * @Param version 版本号
     * @return
     */
    Integer fundAccountOut(@Param("id") Long id, @Param("num") BigDecimal num, @Param("version") Integer version);

    /**
     * 查询用户钱包列表
     * @param userId 用户id
     * @return
     */
    List<AssetsVO> getAssets(Long userId);
}
