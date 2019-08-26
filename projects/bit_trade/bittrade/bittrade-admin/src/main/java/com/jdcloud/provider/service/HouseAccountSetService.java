package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.HouseAccountSet;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.BetaAccountVo;
import com.jdcloud.provider.pojo.vo.HouseAccountSetVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 挖矿配置 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-03
 */
public interface HouseAccountSetService extends IService<HouseAccountSet> {

    Wrapper<String> saveHouseProduct(HouseAccountSet houseAccountSet);

    Wrapper<String> updateHouseAccountSet(HouseAccountSet houseAccountSet);

    /**
     * @Description: 分页获取数据
     * @Author: zjun
     * @Date: 2019/7/22 14:35
     */
    Page<HouseAccountSetVo> selectHouseAccountSetPage(Page<HouseAccountSetVo> page);
}
