package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.UserStatisticDto;
import com.jdcloud.provider.pojo.UserStatistic;
import com.jdcloud.provider.pojo.vo.UserStatisticVo;
import com.jdcloud.util.wrapper.Wrapper;

import java.math.BigDecimal;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-24
 */
public interface UserStatisticService extends IService<UserStatistic> {

    /**
     * @Description: 根据userId查询用户统计数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 11:38
     */
    UserStatistic selectByUserId(Long userId);

    /**
     * @Description: 修改父级的团队人数统计数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 11:40
     */
    boolean updateParentUserStatisticPeople(Long userId);

    /**
     * @param amount     本次变更的金额
     * @param serialType 交易类型
     * @Description: 修改父级的微分统计数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 11:40
     */
    boolean updateParentUserStatisticScore(Long userId, BigDecimal amount, Integer serialType);

    /**
     * @param amount     本次变更的金额
     * @param serialType 交易类型
     * @Description: 修改父级的累积统计数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 11:40
     */
    boolean updateParentUserStatisticTotal(Long userId, BigDecimal amount, Integer serialType);

    /**
     * @param amount     本次变更的金额
     * @param serialType 交易类型
     * @Description: 修改父级的持有资产数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 11:40
     */
    boolean updateParentUserStatisticAccount(Long userId, BigDecimal amount, Integer serialType);

    /**
     * @Description: 手动更新团队人数
     * @Author: senghor
     * @Date: 2019/5/24 0024 17:08
     */
    Wrapper refreshTeamCount();

    Page<UserStatisticVo> selectUserList(Page<UserStatisticVo> page, UserStatisticDto userStatisticDto);

    UserStatisticVo selectAil(Integer id);
}
