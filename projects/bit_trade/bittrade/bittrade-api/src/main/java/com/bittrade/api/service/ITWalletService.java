package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultTWalletService;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.CoinAccountVO;
import com.bittrade.pojo.vo.TWalletVO;
import com.core.framework.base.DAO.IBaseDAO;


/**
 * <p>
 * 虚拟币钱包表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
public interface ITWalletService<DAO extends IBaseDAO<TWallet, TWalletDTO, TWalletVO>> extends IDefaultTWalletService<TWallet, TWalletDTO, TWalletVO, DAO> {

    /**
     * 查询用户的币币账户
     */
    CoinAccountVO queryCoinAccountByUserId(Integer userId);
}
