package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaFreezeAccountDto;
import com.jdcloud.provider.mapper.BetaOrderMapper;
import com.jdcloud.provider.pojo.BetaFreezeAccount;
import com.jdcloud.provider.mapper.BetaFreezeAccountMapper;
import com.jdcloud.provider.pojo.vo.BetaAccountVo;
import com.jdcloud.provider.pojo.vo.BetaFreezeAccountSumVo;
import com.jdcloud.provider.pojo.vo.BetaFreezeAccountVo;
import com.jdcloud.provider.service.BetaFreezeAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-08
 */
@Service
public class BetaFreezeAccountServiceImpl extends ServiceImpl<BetaFreezeAccountMapper, BetaFreezeAccount> implements BetaFreezeAccountService {

    @Resource
    private BetaOrderMapper betaOrderMapper;

    @Override
    public Page<BetaFreezeAccountVo> betaFreezeAccountList(Page<BetaFreezeAccountVo> page, BetaFreezeAccountDto dto) {
        page.setRecords(baseMapper.betaFreezeAccountList(page, dto));
       /* for (BetaFreezeAccountVo betaFreezeAccountVo:page.getRecords()) {
            betaFreezeAccountVo.setFreezeBanlence(betaOrderMapper.getSumFreezeBanlence(betaFreezeAccountVo.getUserId()));
        }*/
        return page;
    }

    /**
     * @Description: 冻结狗资金总额，每天释放总额，可出售总额
     * @param startTime :
     * @Author: zjun
     * @Date: 2019/8/10 22:44
     */
    @Override
    public BetaFreezeAccountSumVo getBetaFreezeAccountSum(String startTime) {
        return baseMapper.getBetaFreezeAccountSum(startTime);
    }

    @Override
    public List<BetaFreezeAccountVo> betaFreezeAccountExcelList(BetaFreezeAccountDto dto) {
        return baseMapper.betaFreezeAccountExcelList(dto);
    }
}
