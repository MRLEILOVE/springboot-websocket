package com.bittrade.uac.service;

import com.bittrade.uac.model.pojo.UserGesture;
import com.bittrade.uac.model.vo.CheckGestureVo;
import com.bittrade.uac.model.vo.GestureVo;

/**
 * @author: xzc
 * @create: 2019-08-13 13:54
 * @description: 手势密码服务
 **/
public interface UserGestureService {

    /**
     * 通过用户ID获取手势密码
     *
     * @param userId
     * @return 返回该用户的手势密码实体
     */
    UserGesture findByUserId(Long userId);

    /**
     * 创建或更新手势密码
     *
     * @param gestureVo
     * @return true 创建或更新成功 false 创建或更新失败
     */
    boolean create(GestureVo gestureVo);

    /**
     * 验证手势密码
     *
     * @param checkGestureVo
     * @return true 验证成功 false 验证失败
     */
    boolean checkGesture(CheckGestureVo checkGestureVo);
}
