package com.bittrade.currency.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bittrade.api.__default.DAO.IDefaultTWalletDAO;
import com.bittrade.pojo.vo.QueryWalletVO;

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
	
}
