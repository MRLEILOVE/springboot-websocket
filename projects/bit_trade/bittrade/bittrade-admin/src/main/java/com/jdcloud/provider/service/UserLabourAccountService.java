package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.LabourDto;
import com.jdcloud.provider.pojo.UserLabourAccount;
import com.jdcloud.provider.pojo.vo.LabourUserVo;
import com.jdcloud.provider.pojo.vo.LabourVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helen
 * @since 2019-01-07
 */
public interface UserLabourAccountService extends IService<UserLabourAccount> {

    /**
     * 查询个人邀请列表
     * @param page
     * @param labourDto
     * @return
     */
    Page<LabourVo> selectUserLabourList(Page<LabourVo> page, LabourDto labourDto);

    /**
     * 导出
     * @param labourDto
     * @return
     */
    List<LabourVo> selectUserLabourExportList(LabourDto labourDto);

    /**
     * 总推广奖励
     * @return
     */
    BigDecimal selectUserLabourSum();

    int updateLabourAccount(UserLabourAccount account);

    /**
     *查询用户是否注册奖励
     * @param userId
     * @return
     */
    LabourUserVo selectLabourUserTWO(Long userId);
}
