package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaMaleAddDto;
import com.jdcloud.provider.pojo.WithdrawCurrencyConfig;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.HouseAccountSetVo;
import com.jdcloud.provider.pojo.vo.WithdrawCurrencyConfigVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-25
 */
public interface WithdrawCurrencyConfigService extends IService<WithdrawCurrencyConfig> {

    /**
     * @Description: 添加修改
     * @param withdrawCurrencyConfig :
     * @Author: zjun
     * @Date: 2019/7/25 11:34
     */
    Wrapper<String> saveOrUpdate(WithdrawCurrencyConfig withdrawCurrencyConfig);


    /**
     * @Description: 分页获取数据
     * @Author: zjun
     * @Date: 2019/7/22 14:35
     */
    Page<WithdrawCurrencyConfigVo> selectWithdrawCurrencyConfigPage(Page<WithdrawCurrencyConfigVo> page);

    /**
     * @Description: 批量删除
     * @param ids :
     * @Author: zjun
     * @Date: 2019/7/25 14:38
     */
    int deleteByIds(String ids);
}
