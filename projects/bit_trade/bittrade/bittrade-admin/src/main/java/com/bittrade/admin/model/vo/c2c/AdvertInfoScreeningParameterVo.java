package com.bittrade.admin.model.vo.c2c;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author xzc
 * @date 2019-08-22 11:12
 * @description 广告信息筛选参数
 */
@Getter
@Setter
@ToString
public class AdvertInfoScreeningParameterVo {

    /**
     * 筛选发布信息开始时间
     */
    private LocalDate releaseTimeStart;

    /**
     * 筛选发布信息结束时间
     */
    private LocalDate releaseTimeEnd;

    /**
     * 搜索（接收:用户loginName）
     */
    private String searchStr;

    /**
     * 币种
     */
    private String coinName;

    /**
     * 方向：买入、卖出
     */
    private Integer advertInfoType;
}
