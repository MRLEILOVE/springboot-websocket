package com.bittrade.admin.service.impl.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTTransferDirectionServiceImpl;
import com.bittrade.admin.dao.wallet.ITTransferDirectionDAO;
import com.bittrade.admin.model.vo.wallet.DirectionVo;
import com.bittrade.admin.service.wallet.ITTransferDirectionService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TTransferDirectionServiceImpl extends DefaultTTransferDirectionServiceImpl<ITTransferDirectionDAO> implements ITTransferDirectionService {

    @Autowired
    private ITTransferDirectionDAO directionDAO;

    /**
     * 页面列表
     */
    @Override
    public List<DirectionVo> findList() {
        return directionDAO.findList();
    }
}
