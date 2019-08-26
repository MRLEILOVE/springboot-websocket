package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.GradedWithdrawalLimit;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.WithdrawCurrencyConfig;
import com.jdcloud.provider.pojo.vo.GradedWithdrawalLimitVo;
import com.jdcloud.util.wrapper.Wrapper;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-25
 */
public interface GradedWithdrawalLimitService extends IService<GradedWithdrawalLimit> {

    /**
     * @Description: 分页获取数据
     * @param page :
     * @Author: zjun
     * @Date: 2019/7/25 11:44
     */
    Page<GradedWithdrawalLimitVo> selectGradedWithdrawalLimitPage(Page<GradedWithdrawalLimitVo> page);

    /**
     * @Description: 添加修改
     * @param gradedWithdrawalLimit :
     * @Author: zjun
     * @Date: 2019/7/25 11:34
     */
    Wrapper<String> saveOrUpdate(GradedWithdrawalLimit gradedWithdrawalLimit);

    /**
     * @Description: 批量删除
     * @param ids :
     * @Author: zjun
     * @Date: 2019/7/25 14:40
     */
    int deleteByIds(String ids);

}
