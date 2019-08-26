package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.HouseComboSetDto;
import com.jdcloud.provider.pojo.HouseComboSet;
import com.jdcloud.provider.pojo.vo.HouseComboSetVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 套餐设置表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-18
 */
public interface HouseComboSetService extends IService<HouseComboSet> {

    Page<HouseComboSetVo> getComboSet(Page<HouseComboSetVo> page);

    Wrapper<String> saveHouseComboSet(HouseComboSetDto houseComboSetDto);

    Wrapper<String> updateHouseProduct(HouseComboSetDto houseComboSetDto);

    Wrapper<String> deleteByIds(Integer ids);
}
