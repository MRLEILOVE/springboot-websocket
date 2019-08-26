package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.HouseAccountSet;
import com.jdcloud.provider.mapper.HouseAccountSetMapper;
import com.jdcloud.provider.pojo.vo.HouseAccountSetVo;
import com.jdcloud.provider.service.HouseAccountSetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 挖矿配置 服务实现类
 * </p>
 *
 * @author c
 * @since 2019-07-03
 */
@Service
public class HouseAccountSetServiceImpl extends ServiceImpl<HouseAccountSetMapper, HouseAccountSet> implements HouseAccountSetService {

    /**
     * 增加配置
     * @param houseAccountSet
     * @return
     */
    @Override
    public Wrapper<String> saveHouseProduct(HouseAccountSet houseAccountSet) {
        HouseAccountSet existAccount=new HouseAccountSet();
        existAccount.setAccountConfigId(houseAccountSet.getAccountConfigId());
        HouseAccountSet existAccountData=baseMapper.selectOne(existAccount);
        if(existAccountData!=null){
            return WrapMapper.error("已存在该币种");
        }
        houseAccountSet.setFixedRatio(houseAccountSet.getFixedRatio().divide(new BigDecimal(100)));
        houseAccountSet.setFloatRatioDown(houseAccountSet.getFloatRatioDown().divide(new BigDecimal(100)));
        houseAccountSet.setFloatRatioUp(houseAccountSet.getFloatRatioUp().divide(new BigDecimal(100)));
//        houseAccountSet.setFoodCoefficient(houseAccountSet.getFoodCoefficient().divide(new BigDecimal(100)));
//        houseAccountSet.setPowerCoefficient(houseAccountSet.getPowerCoefficient().divide(new BigDecimal(100)));
//        houseAccountSet.setAgilityCoefficient(houseAccountSet.getAgilityCoefficient().divide(new BigDecimal(100)));
//        houseAccountSet.setStaminaCoefficient(houseAccountSet.getStaminaCoefficient().divide(new BigDecimal(100)));
//        houseAccountSet.setCorrectCoefficient(houseAccountSet.getCorrectCoefficient().divide(new BigDecimal(100)));
//        houseAccountSet.setProbability(houseAccountSet.getProbability().divide(new BigDecimal(100)));
        Integer  teg = baseMapper.insert(houseAccountSet);
        if(teg!=1){
            return WrapMapper.error("增加配置失败！");
        }
        return WrapMapper.ok("增加配置成功");
    }

    /**
     * 修改配置
     * @param houseAccountSet
     * @return
     */
    @Override
    public Wrapper<String> updateHouseAccountSet(HouseAccountSet houseAccountSet) {
        houseAccountSet.setFixedRatio(houseAccountSet.getFixedRatio().divide(new BigDecimal(100)));
        houseAccountSet.setFloatRatioDown(houseAccountSet.getFloatRatioDown().divide(new BigDecimal(100)));
        houseAccountSet.setFloatRatioUp(houseAccountSet.getFloatRatioUp().divide(new BigDecimal(100)));
//        houseAccountSet.setProbability(houseAccountSet.getProbability().divide(new BigDecimal(100)));
        Integer  teg = baseMapper.updateById(houseAccountSet);
        if(teg!=1){
            return WrapMapper.error("修改配置失败！");
        }
        return WrapMapper.ok("修改配置成功");
    }

    @Override
    public Page<HouseAccountSetVo> selectHouseAccountSetPage(Page<HouseAccountSetVo> page) {
        page.setRecords(baseMapper.selectHouseAccountSetPage(page));
        return page;
    }
}
