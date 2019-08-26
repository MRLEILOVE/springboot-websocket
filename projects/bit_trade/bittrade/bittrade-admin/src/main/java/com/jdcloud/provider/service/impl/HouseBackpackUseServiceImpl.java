package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HouseBackpackUseDto;
import com.jdcloud.provider.pojo.HouseBackpackUse;
import com.jdcloud.provider.mapper.HouseBackpackUseMapper;
import com.jdcloud.provider.pojo.vo.HouseBackpackUseVo;
import com.jdcloud.provider.service.HouseBackpackUseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 背包表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-21
 */
@Service
public class HouseBackpackUseServiceImpl extends ServiceImpl<HouseBackpackUseMapper, HouseBackpackUse> implements HouseBackpackUseService {

    /**
     * 商品列表
     * @param page
     * @param
     * @return
     */
    @Override
    public Page<HouseBackpackUseVo> getBackpackUseList(Page<HouseBackpackUseVo> page, HouseBackpackUseDto houseBackpackUseDto) {
        page.setRecords(baseMapper.getBackpackUseList(page,houseBackpackUseDto));
        return page;
    }


    @Override
    public List<HouseBackpackUseVo> selectBackpackUseList(Long houseId, String type, Integer beSuitable, Long puppyId) {
        HouseBackpackUseDto dto=new HouseBackpackUseDto();
        dto.setHouseId(houseId);
        dto.setType(type);//类型
        dto.setBeSuitable(beSuitable);//适用
        if(puppyId!=null){
            dto.setPuppyId(puppyId);
        }
        return  baseMapper.selectBackpackUseList(dto);
    }

}
