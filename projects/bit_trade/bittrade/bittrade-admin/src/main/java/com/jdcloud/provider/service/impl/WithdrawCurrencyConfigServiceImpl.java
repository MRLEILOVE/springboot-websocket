package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.WithdrawCurrencyConfig;
import com.jdcloud.provider.mapper.WithdrawCurrencyConfigMapper;
import com.jdcloud.provider.pojo.vo.WithdrawCurrencyConfigVo;
import com.jdcloud.provider.service.WithdrawCurrencyConfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.util.Convert;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-25
 */
@Service
public class WithdrawCurrencyConfigServiceImpl extends ServiceImpl<WithdrawCurrencyConfigMapper, WithdrawCurrencyConfig> implements WithdrawCurrencyConfigService {

    @Override
    public Wrapper<String> saveOrUpdate(WithdrawCurrencyConfig withdrawCurrencyConfig) {
        if(null==withdrawCurrencyConfig.getId()){
            WithdrawCurrencyConfig existAccount=new WithdrawCurrencyConfig();
            existAccount.setAccountConfigId(withdrawCurrencyConfig.getAccountConfigId());
            WithdrawCurrencyConfig existAccountData=baseMapper.selectOne(existAccount);
            if(existAccountData!=null){
                return WrapMapper.error("已存在该币种");
            }
            baseMapper.insert(withdrawCurrencyConfig);
            return WrapMapper.ok("增加成功");
        }else{
            baseMapper.updateById(withdrawCurrencyConfig);
            return WrapMapper.ok("修改成功");
        }
    }

    @Override
    public Page<WithdrawCurrencyConfigVo> selectWithdrawCurrencyConfigPage(Page<WithdrawCurrencyConfigVo> page) {
        page.setRecords(baseMapper.selectWithdrawCurrencyConfigPage(page));
        return page;
    }

    @Override
    public int deleteByIds(String idStr) {
        Integer[] ids = ConvertUtil.toIntArray(idStr);
        return baseMapper.deleteByIds(ids);
    }
}
