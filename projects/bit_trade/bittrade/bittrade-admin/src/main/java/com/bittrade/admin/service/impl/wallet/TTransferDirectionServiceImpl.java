package com.bittrade.admin.service.impl.wallet;

import com.bittrade.admin.model.vo.wallet.DirectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTTransferDirectionServiceImpl;
import com.bittrade.pojo.dto.TTransferDirectionDTO;
import com.bittrade.pojo.vo.TTransferDirectionVO;
import com.bittrade.pojo.model.TTransferDirection;
import com.bittrade.admin.dao.wallet.ITTransferDirectionDAO;
import com.bittrade.admin.service.wallet.ITTransferDirectionService;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TTransferDirectionServiceImpl extends DefaultTTransferDirectionServiceImpl<ITTransferDirectionDAO, TTransferDirection, TTransferDirectionDTO, TTransferDirectionVO> implements ITTransferDirectionService {

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
