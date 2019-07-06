package com.bittrade.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.api.__default.service.IDefaultTWalletService;
import com.bittrade.api.dao.ITWalletDAO;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.CoinAccountVO;
import com.bittrade.pojo.vo.TWalletVO;


/**
 * <p>
 * 虚拟币钱包表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-07-05
 */
public interface ITWalletService extends IDefaultTWalletService<TWallet, TWalletDTO, TWalletVO, ITWalletDAO> {

    /**
     * 查询用户的币币账户
     */
    CoinAccountVO queryCoinAccountByUserId(Integer userId);
}
