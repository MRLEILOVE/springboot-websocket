package com.bittrade.uac.service.impl;

import com.bittrade.uac.service.UserGestureService;
import com.bittrade.uac.mapper.UserGestureMapper;
import com.bittrade.uac.model.pojo.UserGesture;
import com.bittrade.uac.model.vo.CheckGestureVo;
import com.bittrade.uac.model.vo.GestureVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author: xzc
 * @create: 2019-08-13 13:54
 * @description: 手势密码服务
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserGestureServiceImpl implements UserGestureService {

    @Autowired
    private UserGestureMapper gestureMapper;

    @Override
    public UserGesture findByUserId(Long userId) {
        UserGesture instance = UserGesture.getInstance();
        instance.setUserId(userId);
        return gestureMapper.select(instance);
    }

    @Override
    public boolean create(GestureVo gestureVo) {
        Long userId = gestureVo.getUserId();
        UserGesture gesture = findByUserId(userId);
        if (Objects.isNull(gesture)) {
            gesture = UserGesture.getInstance();
        }
        gesture.setUserId(userId);
        gesture.setGestures(gestureVo.getGestures());
        gesture.setPeriod(gestureVo.getPeriod());
        gesture.setPeriodUnit(gestureVo.getPeriodUnit());
        gesture.setEnabled(gestureVo.getEnabled());

        int status;
        Long id = gesture.getId();
        if (Objects.nonNull(id)) {
            status = gestureMapper.update(gesture);
        } else {
            status = gestureMapper.insert(gesture);
        }
        return 0 != status;
    }

    @Override
    public boolean checkGesture(CheckGestureVo checkGestureVo) {
        Long userId = checkGestureVo.getUserId();
        UserGesture userGesture = this.findByUserId(userId);
        //该用户没有设置手势密码
        if (Objects.isNull(userGesture)) {
            return true;
        }
        //未启用手势密码
        Boolean enabled = userGesture.getEnabled();
        if (!enabled) {
            return true;
        }
        //验证手势密码是否一致
        String gesturesConfig = userGesture.getGestures();
        String inputGesture = checkGestureVo.getGestures();
        return StringUtils.equals(gesturesConfig, inputGesture);
    }

}
