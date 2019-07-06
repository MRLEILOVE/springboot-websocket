package com.bittrade.api.dao;

import com.bittrade.api.__default.DAO.IDefaultTWalletDAO;
import com.bittrade.pojo.vo.QueryWalletVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface ITWalletDAO extends IDefaultTWalletDAO {

    /**
     * 查询用户钱包
     */
    List<QueryWalletVO> queryByUserId(@Param("userId") Integer userId);
}
