package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaAccountDto;
import com.jdcloud.provider.dto.BetaFreezeAccountDto;
import com.jdcloud.provider.pojo.BetaFreezeAccount;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.BetaAccountVo;
import com.jdcloud.provider.pojo.vo.BetaFreezeAccountSumVo;
import com.jdcloud.provider.pojo.vo.BetaFreezeAccountVo;

import java.util.List;

/**
 * <p>
 *  冻结账户服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-08
 */
public interface BetaFreezeAccountService extends IService<BetaFreezeAccount> {

    /**
     * @Description: 获取冻结账户列表
     * @param page :
     * @param dto :
     * @Author: zjun
     * @Date: 2019/8/8 4:27
     */
    Page<BetaFreezeAccountVo> betaFreezeAccountList(Page<BetaFreezeAccountVo> page, BetaFreezeAccountDto dto);


    /**
     * @Description: 冻结狗资金总额，每天释放总额，可出售总额
     * @param startTime : 
     * @Author: zjun
     * @Date: 2019/8/10 22:44
     */
    BetaFreezeAccountSumVo getBetaFreezeAccountSum(String startTime);

    /**
     * @Description: 获取导出列表
     * @param dto :
     * @Author: zjun
     * @Date: 2019/8/13 16:42
     */
    List<BetaFreezeAccountVo> betaFreezeAccountExcelList(BetaFreezeAccountDto dto);
}
