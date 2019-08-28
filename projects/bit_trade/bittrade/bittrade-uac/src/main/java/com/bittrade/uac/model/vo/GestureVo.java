package com.bittrade.uac.model.vo;

import com.bittrade.uac.model.enums.TimeType;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author: xzc
 * @create: 2019-08-13 14:11
 * @description: 前端传入参数
 **/
@Getter
@Setter
@ToString
public class GestureVo {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 手势密码
     */
    private String gestures;

    /**
     * 周期
     */
    private Integer period;

    /**
     * 周期单位（0、秒，1、分，2、时，3、天）
     */
    private TimeType periodUnit;

    /**
     * 是否启用（0、不启用，1、启用）
     */
    private Boolean enabled;

    /**
     * 参数验证
     *
     * @param userId     用户ID
     * @param gestures   手势密码
     * @param period     验证密码的周期
     * @param periodUnit 周期单位
     * @param enabled    是否启用
     */
    public GestureVo(Long userId, String gestures, Integer period, TimeType periodUnit, Boolean enabled) {
        //错误信息
        Preconditions.checkArgument(Objects.nonNull(userId) && 0 < userId.intValue(), "无效用户ID！");
        Preconditions.checkArgument(StringUtils.isNotBlank(gestures), "无效手势密码！");
        Preconditions.checkArgument(Objects.nonNull(period), "无效手势密码！");
        Preconditions.checkArgument(Objects.nonNull(periodUnit), "无效手势密码！");
        Preconditions.checkArgument(Objects.nonNull(enabled), "无效手势密码！");
        this.userId = userId;
        this.gestures = gestures;
        this.period = period;
        this.periodUnit = periodUnit;
        this.enabled = enabled;
    }
}
