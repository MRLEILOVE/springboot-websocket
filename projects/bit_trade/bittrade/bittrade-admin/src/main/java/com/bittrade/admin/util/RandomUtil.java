package com.bittrade.admin.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bittrade.admin.model.vo.ComboCountVo;

import cn.hutool.core.util.NumberUtil;

/**
 * @Description: 随机工具类
 * @Author: senghor
 * @Date: 2019/6/22 14:27
 */
public class RandomUtil {

    public static List<ComboCountVo> getSplitCombo(List<ComboCountVo> comboCountVos, BigDecimal orderAmount) {
        ComboCountVo comboGroupVo;

        // 价格基数总和
        BigDecimal splitPriceSum = BigDecimal.ZERO;
        // 计算价格基数
        for (int i = 0; i < comboCountVos.size(); i++) {
            comboGroupVo = comboCountVos.get(i);
            if (comboGroupVo.getSplitSum() > 0) {
                BigDecimal priceBase = NumberUtil.mul(comboGroupVo.getSplitSum(), NumberUtil.sub(comboGroupVo.getMaxPrice(), comboGroupVo.getMinPrice()));
                splitPriceSum = NumberUtil.add(splitPriceSum, priceBase);
                comboGroupVo.setPriceBase(priceBase);
            } else {
                comboGroupVo.setPriceBase(BigDecimal.ZERO);
            }
            comboCountVos.set(i, comboGroupVo);
        }
        // 计算价格权重
        for (int i = 0; i < comboCountVos.size(); i++) {
            comboGroupVo = comboCountVos.get(i);
            if (comboGroupVo.getSplitSum() > 0) {
                BigDecimal priceWeight = NumberUtil.div(comboGroupVo.getPriceBase(), splitPriceSum, 10);
                comboGroupVo.setSplitPrice(NumberUtil.round(NumberUtil.mul(orderAmount, priceWeight), 2));
                comboGroupVo.setSellPrice(NumberUtil.div(comboGroupVo.getSplitPrice(), comboGroupVo.getSplitSum(), 2));
                comboGroupVo.setPriceWeight(priceWeight);
                if (comboGroupVo.getSellPrice().compareTo(comboGroupVo.getMinPrice()) == 1 &&
                        comboGroupVo.getSellPrice().compareTo(comboGroupVo.getMaxPrice()) == -1) {
                    comboGroupVo.setPriceRisk(0);
                } else {
                    comboGroupVo.setPriceRisk(1);
                }
                if (comboGroupVo.getBetaSum() != 0 && comboGroupVo.getUserSum() != 0 && comboGroupVo.getMeet().compareTo(BigDecimal.ZERO) == 1) {
                    // 计算饱和度
                    BigDecimal saturated = NumberUtil.div(comboGroupVo.getBetaSum(), comboGroupVo.getUserSum(), 10);
                    // 计算饱和风险系数
                    BigDecimal risk = NumberUtil.div(saturated, comboGroupVo.getMeet(), 10);
                    // 超出饱和度
                    if (risk.compareTo(new BigDecimal(0.95)) >= 0) {
                        comboGroupVo.setSaturatedRisk(1);
                    } else {
                        comboGroupVo.setSaturatedRisk(0);
                    }
                }
                comboCountVos.set(i, comboGroupVo);
            }
        }

        List<ComboCountVo> comboCountVoList = new ArrayList<>();
        comboCountVoList.addAll(comboCountVos);
        return comboCountVoList;
    }

}
