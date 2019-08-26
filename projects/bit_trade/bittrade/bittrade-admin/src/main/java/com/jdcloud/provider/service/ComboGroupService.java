package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.ComboGroupDto;
import com.jdcloud.provider.model.pojo.vo.ComboCountVo;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.vo.StatisticMeetVo;
import com.jdcloud.util.wrapper.Wrapper;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-07
 */
public interface ComboGroupService extends IService<ComboGroup> {

    Page<ComboGroup> selectComboGroupListPage(Page<ComboGroup> page);

    int deleteComboGroupdeleteByIds(String ids);

    Wrapper<String> saveOrUpdate(ComboGroupDto comboGroupDto);

    List<ComboGroup> selectListSection();

    /**
     * @Description: 刷新缓存数据
     * @Author: senghor
     * @Date: 2019/6/8 13:31
     */
    Wrapper<String> refreshRedis();

    /**
     * @Description: 查询拆狗设置数据
     * @Author: senghor
     * @Date: 2019/6/22 15:39
     */
    List<ComboCountVo> selectListComboVo();

    /**
     * @Description: 获取明天之前需要拆分的狗最高和最低价格
     * @Author: senghor
     * @Date: 2019/6/25 17:24
     */
    Wrapper<ComboGroup> selectTomorrowMaxMin();

    /**
     * @Description: 查询当前的预约饱和度
     * @Author: senghor
     * @Date: 2019/7/31 11:23
     */
    List<StatisticMeetVo> selectStatisticReserve(Date startDate, Date endDate);

    /**
     * @Description: 查询当前的领养饱和度
     * @Author: senghor
     * @Date: 2019/7/31 11:23
     */
    List<StatisticMeetVo> selectStatisticClick(Date startDate, Date endDate);
}
