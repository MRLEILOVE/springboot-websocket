package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultTWalletService;
import com.bittrade.pojo.vo.CoinAccountVO;
import com.bittrade.pojo.vo.UserWalletVO;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.DTO.BaseDTO;
import com.core.framework.base.VO.BaseVO;
import com.core.framework.base.model.BaseModel;


/**
 * <p>
 * 虚拟币钱包表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
public interface ITWalletService<Model extends BaseModel<Model>, DTO extends BaseDTO<DTO>, VO extends BaseVO<VO>, DAO extends IBaseDAO<Model, DTO, VO>> extends IDefaultTWalletService<Model, DTO, VO, DAO> {

    /**
     * 查询用户的币币账户
     * @param userId 用户id
     */
    CoinAccountVO queryCoinAccountByUserId(Integer userId);

    /**
     * 查询用户钱包
     * @param userId 用户id
     * @param currencyTradeId 交易对id
     */
    UserWalletVO queryUserWallet(Integer userId, Integer currencyTradeId);
}
