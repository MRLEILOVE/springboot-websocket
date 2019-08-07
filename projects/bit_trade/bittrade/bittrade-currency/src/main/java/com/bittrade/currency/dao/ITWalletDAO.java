package com.bittrade.currency.dao;

import java.math.BigDecimal;
import java.util.List;

import com.bittrade.pojo.dto.TransferDto;
import com.bittrade.pojo.model.TWallet;
import org.apache.ibatis.annotations.Param;

import com.bittrade.__default.DAO.IDefaultTWalletDAO;
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
     * 划转冻结
     */
    Integer transferFrozen(TWallet wallet);
}
