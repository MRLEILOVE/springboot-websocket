package com.bittrade.admin.dao.wallet;

import com.bittrade.__default.DAO.IDefaultTTransferDirectionDAO;
import com.bittrade.admin.model.vo.wallet.DirectionVo;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITTransferDirectionDAO extends IDefaultTTransferDirectionDAO {

    /**
     * 页面列表
     */
    List<DirectionVo> findList();
}
