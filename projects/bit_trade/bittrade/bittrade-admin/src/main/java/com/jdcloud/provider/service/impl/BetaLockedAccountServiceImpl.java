package com.jdcloud.provider.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.ActionDto;
import com.jdcloud.provider.pojo.BetaLockedAccount;
import com.jdcloud.provider.mapper.BetaLockedAccountMapper;
import com.jdcloud.provider.pojo.vo.BetaLockedAccountVo;
import com.jdcloud.provider.pojo.BetaLockedAccount;
import com.jdcloud.provider.mapper.BetaLockedAccountMapper;
import com.jdcloud.provider.service.BetaLockedAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *getBetaLockedAccount
 *
 * @author ourblue
 * @since 2019-08-16
 */
@Service
public class BetaLockedAccountServiceImpl extends ServiceImpl<BetaLockedAccountMapper, BetaLockedAccount> implements BetaLockedAccountService {

    /**
     * 获取我的资产列表
     * @param page
     * @param dto
     * @return
     */
    @Override
    public Page<BetaLockedAccountVo> getBetaLockedAccount(Page<BetaLockedAccountVo> page, ActionDto dto) {
        return page.setRecords(baseMapper.getBetaLockedAccount(page,dto));
    }

    @Override
    public List<BetaLockedAccountVo> betaLockedAccountExcelList(ActionDto dto) {
        return baseMapper.betaLockedAccountExcelList(dto);
    }
}
