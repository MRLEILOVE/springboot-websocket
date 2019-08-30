package com.bittrade.uac.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xzc
 * @date 2019/8/23 9:40 上午
 * @description
 */
@Getter
@Setter
@ToString
public class CheckCodeVo {

    /**
     * 登入名
     */
    private String equipmentCode;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证类型
     */
    private Integer checkType;
}
