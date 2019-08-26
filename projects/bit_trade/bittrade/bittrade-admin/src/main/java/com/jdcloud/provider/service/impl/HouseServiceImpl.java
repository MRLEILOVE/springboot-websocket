package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.HouseEnum;
import com.jdcloud.provider.dto.HouseDto;
import com.jdcloud.provider.mapper.HouseMapper;
import com.jdcloud.provider.pojo.House;
import com.jdcloud.provider.pojo.vo.HouseBackpackUseVo;
import com.jdcloud.provider.pojo.vo.HouseVo;
import com.jdcloud.provider.service.HouseBackpackUseService;
import com.jdcloud.provider.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 狗窝表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-13
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Autowired
    private HouseBackpackUseService houseBackpackUseService;

    //查找的类型
    String type=HouseEnum.productType.CLOTHES.getCode().toString()+","+HouseEnum.productType.BACKGROUND.
            getCode().toString()+","+HouseEnum.productType.KENNEL.getCode().toString()+","+HouseEnum.productType.
            DOGBASIN.getCode().toString()+","+HouseEnum.productType.FEMINACARD.getCode().toString();


    /**
     * 狗窝列表
     * @param page
     * @param houseDto
     * @return
     */
//    @Override
//    public Page<HouseVo> getHouse(Page<HouseVo> page, HouseDto houseDto) {
//        page.setRecords(baseMapper.getHouse(page,houseDto));
//        return page;
//    }


    /**
     * 狗窝列表
     * @param page
     * @param houseDto
     * @return
     */
    public Page<HouseVo> getHouse(Page<HouseVo> page, HouseDto houseDto) {
        List<HouseVo> list=  baseMapper.getHouse(page,houseDto);
        List<HouseVo> array = new ArrayList<>();
        for (HouseVo vo :list){
            int charm=vo.getCharm();
            int power=vo.getPower();
            int agility = vo.getAgility();
            int stamina = vo.getStamina();
            int intelligence = vo.getIntelligence();
            // 查询正在装备的数据
            //获取背包中装饰类的道具
            List<HouseBackpackUseVo>  propDataList=houseBackpackUseService.selectBackpackUseList(vo.getId(),type,
                    HouseEnum.beSuitable.BITCH.getCode(),null);
            for (HouseBackpackUseVo houseBackpackUseVo:propDataList ) {
                boolean flag=false;
                if(houseBackpackUseVo.getProductType().equals(HouseEnum.productType.FEMINACARD.getCode())){
                    long expireTime=houseBackpackUseVo.getSpeedupTime()*3600+houseBackpackUseVo.getCreateTime().getTime()/1000; //过期时间
                    long nowTime=System.currentTimeMillis()/1000;
                    if(nowTime<=expireTime){ flag=true;}
                }else{
                    flag=true;
                }
                if(flag){
                    charm=charm+houseBackpackUseVo.getCharm();
                    power = power+houseBackpackUseVo.getPower();
                    agility = agility+houseBackpackUseVo.getAgility();
                    stamina = stamina+houseBackpackUseVo.getStamina();
                    intelligence = intelligence+houseBackpackUseVo.getIntelligence();
                }
            }
            vo.setCharm(charm);
            vo.setPower(power);
            vo.setAgility(agility);
            vo.setStamina(stamina);
            vo.setIntelligence(intelligence);
           array.add(vo);
        }
        page.setRecords(array);
        return page;
    }



    /**
     * 狗窝详情
     * @param id
     * @return
     */
    @Override
    public HouseVo getDetail(Integer id) {
        HouseVo vo=    baseMapper.getDetail(id);
        return vo;
    }
}
