package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.ActionDto;
import com.jdcloud.provider.pojo.BetaLockedAccount;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.BetaLockedAccountVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-16
 */
public interface BetaLockedAccountService extends IService<BetaLockedAccount> {

    Page<BetaLockedAccountVo> getBetaLockedAccount(Page<BetaLockedAccountVo> page, ActionDto dto);

    /**
     * @Description: 导出列表
     * @param dto :
     * @Author: zjun
     * @Date: 2019/8/20 11:08
     */
    List<BetaLockedAccountVo> betaLockedAccountExcelList(ActionDto dto);
}
