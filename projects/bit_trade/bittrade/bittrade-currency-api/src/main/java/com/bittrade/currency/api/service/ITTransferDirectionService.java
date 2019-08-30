package com.bittrade.currency.api.service;

import com.bittrade.__default.service.IDefaultTTransferDirectionService;
import com.bittrade.pojo.dto.TTransferDirectionDTO;
import com.bittrade.pojo.vo.DirectionVO;
import com.bittrade.pojo.vo.TTransferDirectionVO;
import com.bittrade.pojo.model.TTransferDirection;
import com.core.common.DTO.ReturnDTO;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITTransferDirectionService extends IDefaultTTransferDirectionService<TTransferDirection, TTransferDirectionDTO, TTransferDirectionVO> {

    /**
     * 资金划转方向
     */
    List<DirectionVO> direction();
}
