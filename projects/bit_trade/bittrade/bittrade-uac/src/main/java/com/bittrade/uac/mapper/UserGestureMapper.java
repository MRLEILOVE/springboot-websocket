package com.bittrade.uac.mapper;

import com.bittrade.uac.model.pojo.UserGesture;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: xzc
 * @create: 2019-08-13 11:46
 * @description: 手势密码mapper
 **/
@Repository
@Transactional(rollbackFor = Exception.class)
public interface UserGestureMapper {
    /**
     * 插入数据
     *
     * @param gesture
     * @return 返回受影响的行数
     */
    int insert(UserGesture gesture);

    /**
     * 更新数据
     *
     * @param gesture
     * @return 返回受影响的行数
     */
    int update(UserGesture gesture);

    /**
     * 查询数据
     *
     * @param gesture
     * @return 返回满足条件的实体
     */
    UserGesture select(UserGesture gesture);
}
