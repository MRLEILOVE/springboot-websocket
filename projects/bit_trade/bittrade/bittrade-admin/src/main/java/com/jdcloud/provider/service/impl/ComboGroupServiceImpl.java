package com.jdcloud.provider.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.base.enums.ParameterConfigEnum;
import com.jdcloud.core.conf.AliyunConfiguration;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.ComboGroupDto;
import com.jdcloud.provider.mapper.ComboGroupMapper;
import com.jdcloud.provider.model.pojo.vo.ComboCountVo;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.vo.StatisticMeetVo;
import com.jdcloud.provider.service.ComboGroupService;
import com.jdcloud.provider.service.ParameterConfigService;
import com.jdcloud.provider.utils.RandomUtils;
import com.jdcloud.util.BigDecimalUtil;
import com.jdcloud.util.Convert;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-07
 */
@Service
@Slf4j
public class ComboGroupServiceImpl extends ServiceImpl<ComboGroupMapper, ComboGroup> implements ComboGroupService {

    @Resource
    private ComboGroupMapper comboGroupMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ParameterConfigService parameterConfigService;

    @Value("${jdcloud.aliyun.uploadPath}")
    private String uploadPath;

    @Autowired
    private AliyunConfiguration aliyunConfiguration;

    @Override
    public Page<ComboGroup> selectComboGroupListPage(Page<ComboGroup> page) {
        List<ComboGroup> comboGroupList = comboGroupMapper.selectComboGroupListPage(page);
        for (int i = 0; i < comboGroupList.size(); i++) {
            ComboGroup comboGroup = comboGroupList.get(i);
            comboGroup.setEarningsRatio(NumberUtil.round(NumberUtil.mul(comboGroup.getEarningsRatio(), 100), 2, RoundingMode.HALF_UP));

            Integer maleRemain = (Integer) redisTemplate.get(RedisKeyUtil.MALE_REMAIN + comboGroupList.get(i).getId());
            if (maleRemain == null || maleRemain == 0) {
                comboGroup.setType(0);
            }else {
                comboGroup.setType(1);
            }
            comboGroupList.set(i, comboGroup);
//            log.info("处理第几个套餐："+String.valueOf(comboGroupList.get(i).getId()));
        }
        page.setRecords(comboGroupList);
        return page;
    }




/*    @Override
    public Wrapper<String> comboGroupdelete(Integer id) {
        comboGroupMapper.deleteById(id);
        return  WrapMapper.ok("删除成功");
    }*/

    @Override
    public int deleteComboGroupdeleteByIds(String ids) {
        Integer[] Ids = ConvertUtil.toIntArray(ids);
        return baseMapper.deleteComboGroupdeleteByIds(Ids);
    }

    /**
     * 修改 // 增加
     *
     * @param comboGroupDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<String> saveOrUpdate(ComboGroupDto comboGroupDto) {
        ComboGroup comboGroup = new ComboGroup();
        comboGroup.setDays(comboGroupDto.getDays());
        comboGroup.setEarningsRatio(comboGroupDto.getEarningsRatio());
        comboGroup.setMaxPrice(comboGroupDto.getMaxPrice());
        comboGroup.setMinPrice(comboGroupDto.getMinPrice());
        comboGroup.setLevel(comboGroupDto.getLevel());
        comboGroup.setDifferential(comboGroupDto.getDifferential());
        comboGroup.setDogName(comboGroupDto.getDogName());

        // 图片处理
        if (StringUtils.isNotBlank(comboGroupDto.getPictureBase64())) {
            String path = uploadPath + ConstantEnum.AliyunConstant.BANNER_PATH.getCode();
            String pictureUrl = aliyunConfiguration.upload(comboGroupDto.getPictureBase64(),
                    path + "combogroup_" + comboGroup.getDays() + "_" + comboGroup.getEarningsRatio() + "." + comboGroupDto.getPictureSuffix());
            comboGroup.setDogImg(pictureUrl);
        }
        if (comboGroupDto.getStartTime() != null) {
            boolean gtartTime = comboGroupDto.getStartTime().contains(":");
            if (gtartTime) {//包含
                String startTim = comboGroupDto.getStartTime().replace(":", "");
                Integer startTimInt = Integer.parseInt(startTim);
                comboGroup.setStartTime(startTimInt);
            } else {
                comboGroup.setStartTime(Integer.parseInt(comboGroupDto.getStartTime()));
            }
        }
        if (comboGroupDto.getEndTime() != null) {
            boolean gendTime = comboGroupDto.getEndTime().contains(":");
            if (gendTime) {
                String endTime = comboGroupDto.getEndTime().replace(":", "");
                Integer endTimeInt = Integer.parseInt(endTime);
                comboGroup.setEndTime(endTimeInt);
            } else {
                comboGroup.setEndTime(Integer.parseInt(comboGroupDto.getEndTime()));
            }
        }
        if (comboGroupDto.getId() == null) {
            comboGroup.setCreateTime(new Date());
            baseMapper.insert(comboGroup);
        } else {
            comboGroup.setId(comboGroupDto.getId());
            comboGroup.setUpdateTime(new Date());
            baseMapper.updateById(comboGroup);
        }

        List<ComboGroup> comboGroupList = comboGroupMapper.selectList(null);
        JSONArray jsonArray = new JSONArray();
        for (ComboGroup comboGroupVo : comboGroupList) {
            JSONObject json = new JSONObject(comboGroupVo);
            jsonArray.add(json);
            // 保存单个套餐
            redisTemplate.set(RedisKeyUtil.COMBO_GROUP_ID + comboGroupVo.getId(), json);
        }
        // 保存所有套餐
        redisTemplate.set(RedisKeyUtil.COMBO_GROUP_ID + "all", jsonArray);
        return WrapMapper.ok();
    }

    @Override
    public List<ComboGroup> selectListSection() {
        return baseMapper.selectListSection();
    }

    /**
     * @Description: 刷新缓存数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    @Override
    public Wrapper<String> refreshRedis() {
        List<ComboGroup> comboGroupList = comboGroupMapper.selectList(null);
        JSONArray jsonArray = new JSONArray();
        for (ComboGroup comboGroup : comboGroupList) {
            JSONObject json = new JSONObject(comboGroup);
            jsonArray.add(json);
            // 保存单个套餐
            redisTemplate.set(RedisKeyUtil.COMBO_GROUP_ID + comboGroup.getId(), json);
        }
        // 保存所有套餐
        redisTemplate.set(RedisKeyUtil.COMBO_GROUP_ID + "all", jsonArray);
        return WrapMapper.ok("刷新成功");
    }

    /**
     * @Description: 查询拆狗设置数据
     * @Author: senghor
     * @Date: 2019/6/22 15:39
     */
    @Override
    public List<ComboCountVo> selectListComboVo() {
        List<ComboGroup> comboGroupList = baseMapper.selectList(null);
        List<ComboCountVo> returnComboCountVos = new ArrayList<>();
        BigDecimal betaSplitLimit = parameterConfigService.getBigDecimalValue(ParameterConfigEnum.BETA_SPLIT_LIMIT.getKey());
        // 获取明天之前需要拆狗的最高收益价格和最低收益价格
        ComboCountVo comboCountStatusVo = baseMapper.selectTomorrowMaxMin(DateTimeUtils.getDays(1), betaSplitLimit);

        List<ComboCountVo> comboCountVos = (List<ComboCountVo>) redisTemplate.get(RedisKeyUtil.COMBO_GROUP_SATURATION);

        ComboCountVo comboCountVo;
        ComboCountVo comboCount;
        for (int i = 0; i < comboGroupList.size(); i++) {
            comboCountVo = new ComboCountVo();
            BeanUtil.copyProperties(comboGroupList.get(i), comboCountVo);
            for (int j = 0; j < comboCountVos.size(); j++) {
                if (comboCountVos.get(j).getId().equals(comboCountVo.getId())) {
                    comboCount = comboCountVos.get(j);
                    if (comboCount.getBetaSum() != null && comboCount.getBetaSum() > 0
                            && comboCount.getUserSum() != null && comboCount.getUserSum() > 0) {
                        // 计算饱和度
                        BigDecimal saturated = NumberUtil.div(comboCount.getBetaSum(), comboCount.getUserSum(), 4);
                        saturated = BigDecimalUtil.turnDown(NumberUtil.mul(saturated, 100), 2);
                        comboCountVo.setPriceWeight(saturated);
                    } else {
                        comboCountVo.setPriceWeight(BigDecimal.ZERO);
                    }
                    comboCountVo.setUserSum(comboCount.getUserSum());
                    comboCountVo.setBetaSum(comboCount.getBetaSum());
                    comboCount.setSplitSum(comboCountVo.getSplitSum());
                    comboCountVos.set(j, comboCount);
                }
            }
            comboCountVo.setMeet(BigDecimalUtil.turnDown(NumberUtil.mul(comboCountVo.getMeet(), 100), 2));
            comboCountVo.setEarningsRatio(BigDecimalUtil.turnDown(NumberUtil.mul(comboCountVo.getEarningsRatio(), 100), 2));
            returnComboCountVos.add(comboCountVo);
        }

        // 最低价格和最高价格都为0，则表示没有需要拆分的狗，则不需要计算警告值
        if ((comboCountStatusVo.getMaxPrice().compareTo(BigDecimal.ZERO) == 1 &&
                comboCountStatusVo.getMinPrice().compareTo(BigDecimal.ZERO) == 1) ||
                (comboCountStatusVo.getMaxPrice2().compareTo(BigDecimal.ZERO) == 1 &&
                        comboCountStatusVo.getMinPrice2().compareTo(BigDecimal.ZERO) == 1)) {
            if (comboCountStatusVo.getMaxPrice2().compareTo(BigDecimal.ZERO) == 1 &&
                    comboCountStatusVo.getMinPrice2().compareTo(BigDecimal.ZERO) == 1) {
                // 判断客诉订单中是否有价值超过15000的订单，有则比较正常订单的最低拆分价格是否是0，是0则使用客诉拆分最低价格
                if (comboCountStatusVo.getMinPrice().compareTo(BigDecimal.ZERO) == 0 ||
                        comboCountStatusVo.getMinPrice().compareTo(comboCountStatusVo.getMinPrice2()) == 1) {
                    comboCountStatusVo.setMinPrice(comboCountStatusVo.getMinPrice2());
                }
                if (comboCountStatusVo.getMaxPrice().compareTo(comboCountStatusVo.getMaxPrice2()) == -1) {
                    comboCountStatusVo.setMaxPrice(comboCountStatusVo.getMinPrice2());
                }
            }
            List<ComboCountVo> comboCountVoMinList = RandomUtils.getSplitCombo(comboCountVos, comboCountStatusVo.getMinPrice());

            for (int i = 0; i < returnComboCountVos.size(); i++) {
                comboCountVo = returnComboCountVos.get(i);
                for (int j = 0; j < comboCountVoMinList.size(); j++) {
                    if (comboCountVoMinList.get(j).getId().equals(comboCountVo.getId())) {
                        comboCountVo.setSaturatedRisk(comboCountVoMinList.get(j).getSaturatedRisk());
                        comboCountVo.setPriceRisk(comboCountVoMinList.get(j).getPriceRisk());
                    }
                }
                returnComboCountVos.set(i, comboCountVo);
            }

            List<ComboCountVo> comboCountVoMaxList = RandomUtils.getSplitCombo(comboCountVos, comboCountStatusVo.getMaxPrice());
            for (int i = 0; i < returnComboCountVos.size(); i++) {
                comboCountVo = returnComboCountVos.get(i);
                for (int j = 0; j < comboCountVoMaxList.size(); j++) {
                    if (comboCountVoMaxList.get(j).getId().equals(comboCountVo.getId())) {
                        if (comboCountVoMaxList.get(j).getSaturatedRisk() == 1) {
                            comboCountVo.setSaturatedRisk(1);
                        }
                        if (comboCountVoMaxList.get(j).getPriceRisk() == 1) {
                            comboCountVo.setPriceRisk(1);
                        }
                    }
                }
                returnComboCountVos.set(i, comboCountVo);
            }
        }
        return returnComboCountVos;
    }

    /**
     * @Description: 获取明天之前需要拆分的狗最高和最低价格
     * @Author: senghor
     * @Date: 2019/6/25 17:24
     */
    @Override
    public Wrapper<ComboGroup> selectTomorrowMaxMin() {
        BigDecimal betaSplitLimit = parameterConfigService.getBigDecimalValue(ParameterConfigEnum.BETA_SPLIT_LIMIT.getKey());
        // 获取明天之前需要拆狗的最高收益价格和最低收益价格
        ComboCountVo comboCountVo = baseMapper.selectTomorrowMaxMin(DateTimeUtils.getDays(1), betaSplitLimit);
        if (comboCountVo != null) {
            if ((comboCountVo.getMaxPrice().compareTo(BigDecimal.ZERO) == 1 &&
                    comboCountVo.getMinPrice().compareTo(BigDecimal.ZERO) == 1) ||
                    (comboCountVo.getMaxPrice2().compareTo(BigDecimal.ZERO) == 1 &&
                            comboCountVo.getMinPrice2().compareTo(BigDecimal.ZERO) == 1)) {
                if (comboCountVo.getMaxPrice2().compareTo(BigDecimal.ZERO) == 1 &&
                        comboCountVo.getMinPrice2().compareTo(BigDecimal.ZERO) == 1) {
                    // 判断客诉订单中是否有价值超过15000的订单，有则比较正常订单的最低拆分价格是否是0，是0则使用客诉拆分最低价格
                    if (comboCountVo.getMinPrice().compareTo(BigDecimal.ZERO) == 0 ||
                            comboCountVo.getMinPrice().compareTo(comboCountVo.getMinPrice2()) == 1) {
                        comboCountVo.setMinPrice(comboCountVo.getMinPrice2());
                    }
                    if (comboCountVo.getMaxPrice().compareTo(comboCountVo.getMaxPrice2()) == -1) {
                        comboCountVo.setMaxPrice(comboCountVo.getMinPrice2());
                    }
                }
            }
            return WrapMapper.ok(comboCountVo);
        } else {
            comboCountVo = new ComboCountVo();
            comboCountVo.setMinPrice(BigDecimal.ZERO);
            comboCountVo.setMaxPrice(BigDecimal.ZERO);
            return WrapMapper.ok(comboCountVo);
        }
    }

    /**
     * @param startDate
     * @param endDate
     * @Description: 查询当前的预约饱和度
     * @Author: senghor
     * @Date: 2019/7/31 11:23
     */
    @Override
    public List<StatisticMeetVo> selectStatisticReserve(Date startDate, Date endDate) {
        return baseMapper.selectStatisticReserve(startDate, endDate);
    }

    /**
     * @param startDate
     * @param endDate
     * @Description: 查询当前的领养饱和度
     * @Author: senghor
     * @Date: 2019/7/31 11:23
     */
    @Override
    public List<StatisticMeetVo> selectStatisticClick(Date startDate, Date endDate) {
        return baseMapper.selectStatisticClick(startDate, endDate);
    }
}
