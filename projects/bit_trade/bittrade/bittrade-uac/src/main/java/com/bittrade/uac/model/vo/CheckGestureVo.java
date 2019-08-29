package com.bittrade.uac.model.vo;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author: xzc
 * @create: 2019-08-13 16:25
 * @description: 验证手势密码实体
 **/
@Getter
@Setter
@ToString
public class CheckGestureVo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 手势密码
     */
    private String gestures;

    /**
     * 初始化验证手势密码实体
     *
     * @param userId   用户id
     * @param gestures 手势密码
     * @return
     */
    public CheckGestureVo(Long userId, String gestures) {
        //错误信息
        Preconditions.checkArgument(Objects.nonNull(userId) && 0 < userId.intValue(), "无效用户ID！");
        Preconditions.checkArgument(StringUtils.isNotBlank(gestures), "无效手势密码！");
        this.userId = userId;
        this.gestures = gestures;
    }
}
