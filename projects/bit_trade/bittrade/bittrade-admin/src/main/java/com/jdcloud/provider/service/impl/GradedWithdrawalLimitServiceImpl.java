package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.GradedWithdrawalLimit;
import com.jdcloud.provider.mapper.GradedWithdrawalLimitMapper;
import com.jdcloud.provider.pojo.WithdrawCurrencyConfig;
import com.jdcloud.provider.pojo.vo.GradedWithdrawalLimitVo;
import com.jdcloud.provider.service.GradedWithdrawalLimitService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.util.Convert;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-25
 */
@Service
public class GradedWithdrawalLimitServiceImpl extends ServiceImpl<GradedWithdrawalLimitMapper, GradedWithdrawalLimit> implements GradedWithdrawalLimitService {

    @Override
    public Page<GradedWithdrawalLimitVo> selectGradedWithdrawalLimitPage(Page<GradedWithdrawalLimitVo> page) {
        page.setRecords(baseMapper.selectGradedWithdrawalLimitPage(page));
        return page;
    }

    @Override
    public Wrapper<String> saveOrUpdate(GradedWithdrawalLimit gradedWithdrawalLimit) {
        if(null==gradedWithdrawalLimit.getId()){
            GradedWithdrawalLimit existAccount=new GradedWithdrawalLimit();
            existAccount.setAccountConfigId(gradedWithdrawalLimit.getAccountConfigId());
            existAccount.setGraded(gradedWithdrawalLimit.getGraded());
            GradedWithdrawalLimit existAccountData=baseMapper.selectOne(existAccount);
            if(existAccountData!=null){
                return WrapMapper.error("已存在该币种");
            }
            baseMapper.insert(gradedWithdrawalLimit);
            return WrapMapper.ok("增加成功");
        }else{
            baseMapper.updateById(gradedWithdrawalLimit);
            return WrapMapper.ok("修改成功");
        }
    }

    @Override
    public int deleteByIds(String idStr) {
        Integer[] ids = ConvertUtil.toIntArray(idStr);
        return baseMapper.deleteByIds(ids);
    }
}
