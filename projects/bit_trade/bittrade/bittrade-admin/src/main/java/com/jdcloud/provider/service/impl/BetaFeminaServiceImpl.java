package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.HouseEnum;
import com.jdcloud.provider.dto.BetaFeminaDto;
import com.jdcloud.provider.dto.BetaFeminaStatusDto;
import com.jdcloud.provider.mapper.BetaFeminaMapper;
import com.jdcloud.provider.pojo.BetaFemina;
import com.jdcloud.provider.pojo.vo.BetaFeminaVo;
import com.jdcloud.provider.pojo.vo.HouseBackpackUseVo;
import com.jdcloud.provider.pojo.vo.HouseVo;
import com.jdcloud.provider.service.BetaFeminaService;
import com.jdcloud.provider.service.HouseBackpackUseService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 贝塔狗---母狗表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-10
 */
@Service
public class BetaFeminaServiceImpl extends ServiceImpl<BetaFeminaMapper, BetaFemina> implements BetaFeminaService {

    @Resource
    private BetaFeminaMapper betaFeminaMapper;
    @Autowired
    private HouseBackpackUseService houseBackpackUseService;
    //查找的类型
    String type=HouseEnum.productType.CLOTHES.getCode().toString()+","+HouseEnum.productType.BACKGROUND.
            getCode().toString()+","+HouseEnum.productType.KENNEL.getCode().toString()+","+HouseEnum.productType.
            DOGBASIN.getCode().toString()+","+HouseEnum.productType.FEMINACARD.getCode().toString();
    @Override
    public Page<BetaFeminaVo> selectBetaFeminaPage(Page<BetaFeminaVo> page, BetaFeminaDto betaFeminaDto){
        page.setRecords(betaFeminaMapper.selectBetaFeminaPage(page,betaFeminaDto));
        return page;
    }

    @Override
    public Page<BetaFeminaVo> selectBetaFeminaPageNew(Page<BetaFeminaVo> page, BetaFeminaDto betaFeminaDto) {

        List<BetaFeminaVo> list= betaFeminaMapper.selectBetaFeminaPageNew(page,betaFeminaDto);
        List<BetaFeminaVo> array = new ArrayList<>();
        for (BetaFeminaVo vo :list){
            int charm=vo.getCharm();
            int power=vo.getPower();
            int agility = vo.getAgility();
            int stamina = vo.getStamina();
            int intelligence = vo.getIntelligence();
            // 查询正在装备的数据
            //获取背包中装饰类的道具
            List<HouseBackpackUseVo>  propDataList=houseBackpackUseService.selectBackpackUseList(vo.getHouseId(),type,
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
//
//        page.setRecords(betaFeminaMapper.selectBetaFeminaPageNew(page,betaFeminaDto));
//        return page;
    }

    @Override
    public BetaFeminaVo selectbetaFeminaById(Integer id) {
        return betaFeminaMapper.selectbetaFeminaById(id);
    }

    @Override
    public Wrapper<String> saveOrUpdate(BetaFemina betafemina) {
        if(betafemina.getId()!=null){
            betafemina.setUpdateTime(new Date());
            betaFeminaMapper.updateById(betafemina);
        }else{
            betaFeminaMapper.insert(betafemina);
        }
        return WrapMapper.ok();
    }

    /**
     *设置不孕
     */
    @Override
    public Wrapper<String> updateStatus(BetaFeminaStatusDto betaFeminaStatusDto){
        BetaFemina betaFemina  = new BetaFemina();
        betaFemina.setId(betaFeminaStatusDto.getId());
        betaFemina.setStatus(betaFeminaStatusDto.getStatus());
        betaFeminaMapper.updateById(betaFemina);
        return WrapMapper.ok("设置成功");
    }
}
