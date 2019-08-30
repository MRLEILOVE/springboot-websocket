package com.bittrade.uac.model.pojo;

import com.bittrade.uac.model.enums.TimeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: xzc
 * @create: 2019-08-13 11:03
 * @description: 手势密码实体
 **/
@Getter
@Setter
@NoArgsConstructor
public class UserGesture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手势ID
     */
    private Long id;

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
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 获取用户手势密码实体
     *
     * @return
     */
    public static UserGesture getInstance() {
        return new UserGesture();
    }
}
