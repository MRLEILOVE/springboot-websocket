package com.bittrade.currency.api.service;

import java.util.List;

import com.bittrade.__default.service.IDefaultTTransferDirectionService;
import com.bittrade.pojo.vo.DirectionVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITTransferDirectionService extends IDefaultTTransferDirectionService {

    /**
     * 资金划转方向
     */
    List<DirectionVO> direction();
}
