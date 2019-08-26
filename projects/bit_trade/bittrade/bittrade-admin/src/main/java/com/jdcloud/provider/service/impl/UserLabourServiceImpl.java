package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.UserEnum;
import com.jdcloud.provider.mapper.UserLabourMapper;
import com.jdcloud.provider.pojo.UserLabour;
import com.jdcloud.provider.service.UserLabourService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helen
 * @since 2019-01-07
 */
@Service
public class UserLabourServiceImpl extends ServiceImpl<UserLabourMapper, UserLabour> implements UserLabourService {

    @Override
    public long selectUserLabourThreeCount(Long userId) {
        long labourThree = baseMapper.selectUserLabourThreeCount(userId);
        return labourThree;
    }

    @Override
    public long selectUserLabourThreeCountNow(Long userId) {
        long labourThree = baseMapper.selectUserLabourThreeCountNow(userId);
        return labourThree;
    }

    @Override
    public long selectUserLabourAuthCount(Long userId) {
        long labourAuth = baseMapper.selectUserLabourAuthCount(userId);
        return labourAuth;
    }

    @Override
    public long selectUserLabourAuthCountNow(Long userId) {
        long labourAuth = baseMapper.selectUserLabourAuthCountNow(userId);
        return labourAuth;
    }

    @Override
    public long selectUserLabourActiveCount(Long userId) {
        long labourActive = baseMapper.selectUserLabourActiveCount(userId);
        return labourActive;
    }

    @Override
    public long selectUserLabourOneAuthCount(Long userId) {
        long oneAuth = baseMapper.selectUserLabourOneAuthCount(userId);
        return oneAuth;
    }

    @Override
    public long selectUserLabourOneActiveCount(Long userId) {
        long oneActive = baseMapper.selectUserLabourOneActiveCount(userId);
        return oneActive;
    }

    // 查询父节点下面的某一个用户
    @Override
    public UserLabour selectlistLimit(Long parentId) {
        return baseMapper.selectlistLimit(parentId);
    }

    /**
     * @Description: 查询userId的一级和二级推荐人
     * @Author: senghor
     * @Date: 2019/5/23 0023 9:32
     */
    @Override
    public Map<String, UserLabour> selectAncestors(Long userId) {
        Map<String, UserLabour> map = new HashMap<>();
        if (userId != null){
            UserLabour userLabour = selectByUserId(userId);
            if (userLabour != null && userLabour.getParentId() != null){
                UserLabour parentUserLabour = selectByUserId(userLabour.getParentId());
                if (parentUserLabour != null) {
                    // 保存一级推荐人
                    map.put(UserEnum.userLabour.parentUserLabour.getKey(), parentUserLabour);
                    if (parentUserLabour.getParentId() != null) {
                        UserLabour grandpaUserLabour = selectByUserId(parentUserLabour.getParentId());
                        if (grandpaUserLabour != null) {
                            // 保存二级推荐人
                            map.put(UserEnum.userLabour.grandpaUserLabour.getKey(), grandpaUserLabour);
                        }
                    }
                }
            }
        }
        return map;
    }

    @Override
    public UserLabour selectByUserId(Long userId) {
        if (userId != null){
            UserLabour userParm = new UserLabour();
            userParm.setUserId(userId);
            UserLabour userLabour = baseMapper.selectOne(userParm);
            if (userLabour != null){
                return userLabour;
            }
        }
        return null;
    }

    /**
     *
     * 查询父节点
     * @param parentId
     * @return
     */
    @Override
    public List<UserLabour> selectByParentId(Long parentId) {
        List<UserLabour> userLabours = new ArrayList<>();
        if (parentId != null) {
            UserLabour userParm = new UserLabour();
            userParm.setParentId(parentId);
            userLabours = baseMapper.selectList(new EntityWrapper<>(userParm));
        }
        return userLabours;
    }

    @Override
    public List<UserLabour> selectAllUserId() {
        return baseMapper.selectAllUserId();
    }

    @Override
    public List<Long> selectAllParentId() {
        return baseMapper.selectAllParentId();
    }

    /**
     * @Description: 查询所有的推荐关系实名认证的用户ID和父级ID
     * @Author: senghor
     * @Date: 2019/7/5 15:56
     */
    @Override
    public List<UserLabour> selectByAuth() {
        return baseMapper.selectByAuth();
    }

}
