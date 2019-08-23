package com.bittrade.admin.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xzc
 * @date 2019-08-22 17:07
 * @description 请求页面参数
 */
@Getter
@Setter
@ToString
public class PageParamVo {

    /**
     * 页面行数
     */
    private Long size;

    /**
     * 页数
     */
    private Long num;
}
