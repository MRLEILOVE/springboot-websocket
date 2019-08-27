package com.bittrade.currency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTTransferDirectionServiceImpl;
import com.bittrade.currency.api.service.ITTransferDirectionService;
import com.bittrade.currency.dao.ITTransferDirectionDAO;
import com.bittrade.pojo.dto.TTransferDirectionDTO;
import com.bittrade.pojo.model.TTransferDirection;
import com.bittrade.pojo.vo.DirectionVO;
import com.bittrade.pojo.vo.TTransferDirectionVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TTransferDirectionServiceImpl extends DefaultTTransferDirectionServiceImpl<ITTransferDirectionDAO, TTransferDirection, TTransferDirectionDTO, TTransferDirectionVO> implements ITTransferDirectionService {
    @Autowired
    private ITTransferDirectionDAO transferDirectionDAO;

    /**
     * 资金划转方向
     * @return
     */
    @Override
    public List<DirectionVO> direction() {
        return transferDirectionDAO.direction();
    }
}
