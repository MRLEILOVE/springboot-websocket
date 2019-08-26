package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.UserLabour;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helen
 * @since 2019-01-07
 */
public interface UserLabourService extends IService<UserLabour> {

    /**
     * 统计三级邀请(弃用)
     * @param userId
     * @return
     */
    @Deprecated
    long selectUserLabourThreeCount(Long userId);

    /**
     * 统计三级邀请(新增)
     * @param userId
     * @return
     */
    long selectUserLabourThreeCountNow(Long userId);

    /**
     * 统计邀请总实名认证(弃用)
     * @param userId
     * @return
     */
    @Deprecated
    long selectUserLabourAuthCount(Long userId);

    /**
     * 统计邀请总实名认证(新增)
     * @param userId
     * @return
     */
    long selectUserLabourAuthCountNow(Long userId);

    /**
     * 统计邀请总活跃---抢了狗的人
     * @param userId
     * @return
     */
    long selectUserLabourActiveCount(Long userId);
    /**
     * 统计邀请一级认证---抢了狗的人
     * @param userId
     * @return
     */
    long selectUserLabourOneAuthCount(Long userId);
    /**
     * 统计邀请一级活跃---抢了狗的人
     * @param userId
     * @return
     */
    long selectUserLabourOneActiveCount(Long userId);

    UserLabour selectlistLimit(Long parentId);

    /** 
     * @Description: 查询userId的一级和二级推荐人
     * @Author: senghor
     * @Date: 2019/5/23 0023 9:33
     */
    Map<String, UserLabour> selectAncestors(Long userId);

    /**
     * @Description: 根据userId查询
     * @Author: senghor
     * @Date: 2019/5/23 0023 9:33
     */
    UserLabour selectByUserId(Long userId);

    /**
     * @Description: 根据parentId查询
     * @Author: senghor
     * @Date: 2019/5/23 0023 9:33
     */
    List<UserLabour> selectByParentId(Long parentId);

    /**
     * @Description: 查询所有的推荐关系的用户ID和父级ID
     * @Author: senghor
     * @Date: 2019/5/28 0028 9:20
     */
    List<UserLabour> selectAllUserId();

    /** 
     * @Description: 查询所有有下级的父级ID
     * @Author: senghor
     * @Date: 2019/5/28 0028 9:24
     */
    List<Long> selectAllParentId();

    /**
     * @Description: 查询所有的推荐关系实名认证的用户ID和父级ID
     * @Author: senghor
     * @Date: 2019/7/5 15:56
     */
    List<UserLabour> selectByAuth();
}
