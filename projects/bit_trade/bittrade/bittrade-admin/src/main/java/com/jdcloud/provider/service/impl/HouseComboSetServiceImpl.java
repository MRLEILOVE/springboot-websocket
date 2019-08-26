package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.HouseComboSetDto;
import com.jdcloud.provider.mapper.HouseComboSetMapper;
import com.jdcloud.provider.pojo.HouseComboSet;
import com.jdcloud.provider.pojo.HouseComboSetDetail;
import com.jdcloud.provider.pojo.vo.HouseComboSetVo;
import com.jdcloud.provider.service.HouseComboSetDetailService;
import com.jdcloud.provider.service.HouseComboSetService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 套餐设置表 服务实现类
 * </p>
 * @author cc
 * @since 2019-06-28
 */
@Service
public class HouseComboSetServiceImpl extends ServiceImpl<HouseComboSetMapper, HouseComboSet> implements HouseComboSetService {

    @Autowired
    private HouseComboSetDetailService houseComboSetDetailService;

    /**
     * 查询套餐设置
     * @param page
     * @return
     */
    @Override
    public Page<HouseComboSetVo> getComboSet(Page<HouseComboSetVo> page) {
        return page.setRecords(baseMapper.getComboSet(page));
    }

    /**
     * 增加狗窝套餐
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<String> saveHouseComboSet(HouseComboSetDto houseComboSetDto) {
        EntityWrapper entity = new EntityWrapper();
        entity.eq("combo_id", houseComboSetDto.getComboId());
        Integer count = baseMapper.selectCount(entity);
        if (count > 0) {
            return WrapMapper.error("已设置该套餐，请勿重新设置！");
        }
        HouseComboSet houseComboSet = new HouseComboSet();
        houseComboSet.setComboId(houseComboSetDto.getComboId());
        houseComboSet.setFetationMax(houseComboSetDto.getFetationMax());
        houseComboSet.setMinPrice(houseComboSetDto.getMinPrice());
        houseComboSet.setMaxPrice(houseComboSetDto.getMaxPrice());
        houseComboSet.setFetationProbability(houseComboSetDto.getFetationProbability());
        int i = baseMapper.insert(houseComboSet);
        if (houseComboSetDto.getHouseComboSetDetail() != null) {
            HouseComboSetDetail detail = new HouseComboSetDetail();
            for (HouseComboSetDetail ail : houseComboSetDto.getHouseComboSetDetail()) {
                detail.setComboSetId(houseComboSet.getId());
                detail.setFetationCount(ail.getFetationCount());
                detail.setFetationProbability(ail.getFetationProbability());
                boolean bo = houseComboSetDetailService.insert(detail);
            }
        }
        return WrapMapper.ok("增加成功");
    }

    /**
     * 修改狗窝套餐设置
     * @param houseComboSetDto
     * @return
     */
    @Override
    public Wrapper<String> updateHouseProduct(HouseComboSetDto houseComboSetDto) {
        HouseComboSet houseComboSet = new HouseComboSet();
        houseComboSet.setId(houseComboSetDto.getId());
        houseComboSet.setComboId(houseComboSetDto.getComboId());
        houseComboSet.setFetationMax(houseComboSetDto.getFetationMax());
        houseComboSet.setMinPrice(houseComboSetDto.getMinPrice());
        houseComboSet.setMaxPrice(houseComboSetDto.getMaxPrice());
        houseComboSet.setFetationProbability(houseComboSetDto.getFetationProbability());
        int i = baseMapper.updateById(houseComboSet);
        if (houseComboSetDto.getHouseComboSetDetail() != null) {
            HouseComboSetDetail detail = new HouseComboSetDetail();
            for (HouseComboSetDetail ail : houseComboSetDto.getHouseComboSetDetail()) {
                detail.setId(ail.getId());
                detail.setComboSetId(houseComboSet.getId());
                detail.setFetationCount(ail.getFetationCount());
                detail.setFetationProbability(ail.getFetationProbability());
                boolean bo = houseComboSetDetailService.updateById(detail);
            }
        }
        return WrapMapper.ok("修改成功");
    }

    /**
     * 删除狗窝套餐
     * @param ids
     * @return
     */
    @Override
    public Wrapper<String> deleteByIds(Integer ids) {
        HouseComboSet houseComboSet = baseMapper.selectById(ids);
        if (houseComboSet == null) {
            return WrapMapper.error("删除狗窝套餐为空！");
        }
        EntityWrapper entity = new EntityWrapper();
        entity.eq("combo_set_id", houseComboSet.getId());
        Integer count = houseComboSetDetailService.selectCount(entity);
        if (count >= 1) {
            return WrapMapper.error("套餐下有狗出生几率，无法删除！");
        }
        baseMapper.deleteById(houseComboSet.getId());
        return WrapMapper.error("删除成功");
    }


}
