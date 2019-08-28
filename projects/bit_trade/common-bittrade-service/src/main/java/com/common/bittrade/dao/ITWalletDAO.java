package com.common.bittrade.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.__default.DAO.IDefaultTWalletDAO;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.QueryWalletVO;
import com.bittrade.pojo.vo.UserWalletVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITWalletDAO extends IDefaultTWalletDAO {

    /**
     * 查询用户钱包
     */
    List<QueryWalletVO> queryByUserId(@Param("userId") Integer userId);

    /**
     * 查询用户钱包
     */
    UserWalletVO queryUserWallet(@Param("userId") Integer userId, @Param("currencyTradeId") Integer currencyTradeId);
    
    /**
     * <p>
     * 修改 交易冻结数量
     * </p>
     * modifyTradeFrozen:(这里用一句话描述这个方法的作用). <br/>  
     * TODO(这里描述这个方法适用条件 – 可选).<br/>  
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
     *  
     * @author Administrator  
     * @param tradeFrozen
     * @param version
     * @param id
     * @return  
     * @since JDK 1.8
     */
    int modifyTradeFrozen(BigDecimal tradeFrozen, Integer version, Long id);

    /**
     * 修改划转冻结
     * @param id id
     * @param transferFrozen 划转冻结
     * @param version 版本号
     * @return
     */
    Integer modifyTransferFrozen(@Param("id") Long id, @Param("transferFrozen") BigDecimal transferFrozen,@Param("version") Integer version);

    /**
     * 释放划转解冻
     * @param id id
     * @param transferFrozen 划转冻结金额
     * @param version 版本号
     * @return
     */
    Integer decreaseTransferFreeze(@Param("id")Long id, @Param("transferFrozen")BigDecimal transferFrozen, @Param("version")Integer version);

    /**
     * 币币账户入账
     * @param id id
     * @param num 划转数量
     * @param version 版本号
     * @return
     */
    Integer biBiAccountOut(@Param("id")Long id, @Param("num")BigDecimal num, @Param("version")Integer version);

    /**
     * 币币账户出账
     * @param id id
     * @param num 划转数量
     * @param version 版本号
     * @return
     */
    Integer biBiAccountIn(@Param("id")Long id, @Param("num")BigDecimal num, @Param("version")Integer version);

    /**
     * 获取用户币币账户资产
     * @param userId 用户id
     * @return 资产vo
     */
    List<AssetsVO> getAssets(@Param("userId") Long userId);

}
