package com.bittrade.currency.dao;

import com.bittrade.__default.DAO.IDefaultTTransferDirectionDAO;
import com.bittrade.pojo.vo.DirectionVO;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITTransferDirectionDAO extends IDefaultTTransferDirectionDAO {

    /**
     * 资金划转方向
     */
    List<DirectionVO> direction();
}
