package com.jdcloud.provider.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.FurureTask.RefreshTeamCountTask;
import com.jdcloud.provider.dto.TaskDto;
import com.jdcloud.provider.dto.TeamCountResultsVo;
import com.jdcloud.provider.dto.UserStatisticDto;
import com.jdcloud.provider.mapper.UserStatisticMapper;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.pojo.vo.UserStatisticVo;
import com.jdcloud.provider.service.*;
import com.jdcloud.util.ListUtil;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-24
 */
@Slf4j
@Service
public class UserStatisticServiceImpl extends ServiceImpl<UserStatisticMapper, UserStatistic> implements UserStatisticService {

    @Autowired
    public UserLabourService userLabourService;
    @Autowired
    public BetaAccountService betaAccountService;
    @Autowired
    public ComboGroupService comboGroupService;
    @Autowired
    public BetaOrderService betaOrderService;
    @Autowired
    public RedisTemplate redisTemplate;
    @Resource
    public TaskExecutor taskExecutor;

    @Override
    public UserStatistic selectByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        // 查询该用户统计数据是否已经存在
        UserStatistic userStatistic = new UserStatistic();
        userStatistic.setUserId(userId);
        userStatistic = baseMapper.selectOne(userStatistic);
        // 如果不存在则创建初始统计数据
        if (userStatistic == null) {
            userStatistic = new UserStatistic();
            userStatistic.setCreateTime(new Date());
            userStatistic.setUserId(userId);
            baseMapper.insert(userStatistic);
        }
        return userStatistic;
    }

    public UserStatistic selectByUserId(Long userId, List<UserStatistic> userStatisticList) {
        // 查询该用户统计数据是否已经存在
        UserStatistic returnStatistic = new UserStatistic();
        for (UserStatistic userStatistic : userStatisticList) {
            if (userId.equals(userStatistic.getUserId())) {
                returnStatistic.setUserId(userStatistic.getUserId());
            }
        }
        return returnStatistic;
    }

    /**
     * @Description: 修改父级的团队人数统计数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 14:15
     */
    @Override
    public boolean updateParentUserStatisticPeople(Long userId) {
        boolean flag = false;
        // 查询是否有父级ID
        Long parentId = selectByParentId(userId);
        if (parentId == null) {
            return flag;
        }
        log.info("userId:" + userId + "给父级团队人数增量处理");
        UserStatistic parentUserStatistic = selectByUserId(parentId);
        if (parentUserStatistic != null) {
            // 给父级的团队人数增加
            parentUserStatistic.setTeamCount(parentUserStatistic.getTeamCount() + 1);
            parentUserStatistic.setTeamCountOne(parentUserStatistic.getTeamCountOne() + 1);
            baseMapper.updateById(parentUserStatistic);

            // 查询爷级统计数据
            Long grandpatId = selectByParentId(parentUserStatistic.getUserId());
            if (grandpatId == null) {
                return flag;
            }
            UserStatistic grandpaUserStatistic = selectByUserId(grandpatId);
            if (grandpaUserStatistic != null) {
                // 给爷级的团队人数增加
                grandpaUserStatistic.setTeamCount(grandpaUserStatistic.getTeamCount() + 1);
                grandpaUserStatistic.setTeamCountTwo(grandpaUserStatistic.getTeamCountTwo() + 1);
                baseMapper.updateById(grandpaUserStatistic);

                // 查询是否还有祖级统计数据递归调用
                setParentStatisticPeople(grandpaUserStatistic.getUserId());
            }
        }
        return flag;
    }

    /**
     * @Description: 递归查询给父级的团队增加总人数
     * @Author: senghor
     * @Date: 2019/5/24 0024 15:51
     */
    public void setParentStatisticPeople(Long userId) {
        // 查询是否有父级ID
        Long parentId = selectByParentId(userId);
        if (parentId != null) {
            UserStatistic parentUserStatistic = selectByUserId(parentId);
            if (parentUserStatistic != null) {
                // 给父级的团队人数
                parentUserStatistic.setTeamCount(parentUserStatistic.getTeamCount() + 1);
                baseMapper.updateById(parentUserStatistic);
                setParentStatisticPeople(parentUserStatistic.getUserId());
            }
        }
    }

    /**
     * @param amount     本次变更的金额
     * @param serialType 交易类型
     * @Description: 修改父级的微分统计数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 14:15
     */
    @Override
    public boolean updateParentUserStatisticScore(Long userId, BigDecimal amount, Integer serialType) {
        // 取绝对值
        amount = amount.abs();
        boolean flag = false;
        // 查询是否有父级ID
        Long parentId = selectByParentId(userId);
        if (parentId == null) {
            return flag;
        }
        log.info("userId:" + userId + "给父级微分统计增量处理");
        UserStatistic parentUserStatistic = selectByUserId(parentId);
        if (parentUserStatistic != null) {
            BigDecimal teamScoreSum = BigDecimal.ZERO;
            BigDecimal teamScoreSumOne = BigDecimal.ZERO;
            // 给父级的微分一级和总数增加
            if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
                teamScoreSum = parentUserStatistic.getTeamScoreSum().add(amount);
                teamScoreSumOne = parentUserStatistic.getTeamScoreSumOne().add(amount);
            } else {
                teamScoreSum = parentUserStatistic.getTeamScoreSum().subtract(amount);
                teamScoreSumOne = parentUserStatistic.getTeamScoreSumOne().subtract(amount);
            }
            parentUserStatistic.setTeamScoreSum(teamScoreSum);
            parentUserStatistic.setTeamScoreSumOne(teamScoreSumOne);
            baseMapper.updateById(parentUserStatistic);
            // 查询爷级统计数据
            Long grandpatId = selectByParentId(parentUserStatistic.getUserId());
            if (grandpatId == null) {
                return flag;
            }
            UserStatistic grandpaUserStatistic = selectByUserId(grandpatId);
            if (grandpaUserStatistic != null) {
                BigDecimal teamScoreSumTwo = new BigDecimal(0);
                // 给父级的微分二级和总数增加
                if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
                    teamScoreSum = grandpaUserStatistic.getTeamScoreSum().add(amount);
                    teamScoreSumTwo = grandpaUserStatistic.getTeamScoreSumTwo().add(amount);
                } else {
                    teamScoreSum = grandpaUserStatistic.getTeamScoreSum().subtract(amount);
                    teamScoreSumTwo = grandpaUserStatistic.getTeamScoreSumTwo().subtract(amount);
                }
                grandpaUserStatistic.setTeamScoreSum(teamScoreSum);
                grandpaUserStatistic.setTeamScoreSumTwo(teamScoreSumTwo);
                baseMapper.updateById(grandpaUserStatistic);
                // 查询是否还有祖级统计数据递归调用
                setParentStatisticScore(grandpaUserStatistic.getUserId(), amount, serialType);
            }
        }
        return flag;
    }

    /**
     * @Description: 递归查询给父级的累积收益增加总数
     * @Author: senghor
     * @Date: 2019/5/24 0024 15:51
     */
    public void setParentStatisticScore(Long userId, BigDecimal amount, Integer serialType) {
        // 查询是否有父级ID
        Long parentId = selectByParentId(userId);
        if (parentId != null) {
            UserStatistic parentUserStatistic = selectByUserId(parentId);
            if (parentUserStatistic != null) {
                BigDecimal teamScoreSum = new BigDecimal(0);
                // 给父级的累积收益总数增加
                if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
                    teamScoreSum = parentUserStatistic.getTeamScoreSum().add(amount);
                } else {
                    teamScoreSum = parentUserStatistic.getTeamScoreSum().subtract(amount);
                }
                parentUserStatistic.setTeamScoreSum(teamScoreSum);
                baseMapper.updateById(parentUserStatistic);
                setParentStatisticScore(parentUserStatistic.getUserId(), amount, serialType);
            }
        }
    }

    /**
     * @param amount     本次变更的金额
     * @param serialType 交易类型
     * @Description: 修改父级的累积统计数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 14:15
     */
    @Override
    public boolean updateParentUserStatisticTotal(Long userId, BigDecimal amount, Integer serialType) {
        // 取绝对值
        amount = amount.abs();
        boolean flag = false;
        // 查询是否有父级ID
        Long parentId = selectByParentId(userId);
        if (parentId == null) {
            return flag;
        }
        log.info("userId:" + userId + "给父级累积收益增量处理");
        UserStatistic parentUserStatistic = selectByUserId(parentId);
        if (parentUserStatistic != null) {
            BigDecimal teamTotalSum = new BigDecimal(0);
            BigDecimal teamTotalSumOne = new BigDecimal(0);
            // 给父级的累积收益一级和总数增加
            if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
                teamTotalSum = parentUserStatistic.getTeamTotalSum().add(amount);
                teamTotalSumOne = parentUserStatistic.getTeamTotalSumOne().add(amount);
            } else {
                teamTotalSum = parentUserStatistic.getTeamTotalSum().subtract(amount);
                teamTotalSumOne = parentUserStatistic.getTeamTotalSumOne().subtract(amount);
            }
            parentUserStatistic.setTeamTotalSum(teamTotalSum);
            parentUserStatistic.setTeamTotalSumOne(teamTotalSumOne);
            baseMapper.updateById(parentUserStatistic);
            // 查询爷级统计数据
            Long grandpatId = selectByParentId(parentUserStatistic.getUserId());
            if (grandpatId == null) {
                return flag;
            }
            UserStatistic grandpaUserStatistic = selectByUserId(grandpatId);
            if (grandpaUserStatistic != null) {
                BigDecimal teamTotalSumTwo = new BigDecimal(0);
                // 给父级的累积收益二级和总数增加
                if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
                    teamTotalSum = grandpaUserStatistic.getTeamTotalSum().add(amount);
                    teamTotalSumTwo = grandpaUserStatistic.getTeamScoreSumTwo().add(amount);
                } else {
                    teamTotalSum = grandpaUserStatistic.getTeamTotalSum().subtract(amount);
                    teamTotalSumTwo = grandpaUserStatistic.getTeamScoreSumTwo().subtract(amount);
                }
                grandpaUserStatistic.setTeamTotalSum(teamTotalSum);
                grandpaUserStatistic.setTeamTotalSumTwo(teamTotalSumTwo);
                baseMapper.updateById(grandpaUserStatistic);
                // 查询是否还有祖级统计数据递归调用
                setParentStatisticTotal(grandpaUserStatistic.getUserId(), amount, serialType);
            }
        }
        return flag;
    }

    /**
     * @Description: 递归查询给父级的累积收益增加总数
     * @Author: senghor
     * @Date: 2019/5/24 0024 15:51
     */
    public void setParentStatisticTotal(Long userId, BigDecimal amount, Integer serialType) {
        // 查询是否有父级ID
        Long parentId = selectByParentId(userId);
        if (parentId != null) {
            UserStatistic parentUserStatistic = selectByUserId(parentId);
            if (parentUserStatistic != null) {
                BigDecimal teamTotalSum = new BigDecimal(0);
                // 给父级的累积收益总数增加
                if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
                    teamTotalSum = parentUserStatistic.getTeamTotalSum().add(amount);
                } else {
                    teamTotalSum = parentUserStatistic.getTeamTotalSum().subtract(amount);
                }
                parentUserStatistic.setTeamTotalSum(teamTotalSum);
                baseMapper.updateById(parentUserStatistic);
                setParentStatisticTotal(parentUserStatistic.getUserId(), amount, serialType);
            }
        }
    }

    /**
     * @param amount     本次变更的金额
     * @param serialType 交易类型
     * @Description: 修改父级的持有资产数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 14:15
     */
    @Override
    public boolean updateParentUserStatisticAccount(Long userId, BigDecimal amount, Integer serialType) {
        // 取绝对值
        amount = amount.abs();
        boolean flag = false;
        // 查询是否有父级ID
        Long parentId = selectByParentId(userId);
        if (parentId == null) {
            return flag;
        }
        log.info("userId:" + userId + "给父级持有资产增量处理");
        UserStatistic parentUserStatistic = selectByUserId(parentId);
        if (parentUserStatistic != null) {
            BigDecimal teamAccountSum = new BigDecimal(0);
            BigDecimal teamAccountSumOne = new BigDecimal(0);
            // 给父级的微分一级和总数增加
            if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
                teamAccountSum = parentUserStatistic.getTeamAccountSum().add(amount);
                teamAccountSumOne = parentUserStatistic.getTeamAccountSumOne().add(amount);
            } else {
                teamAccountSum = parentUserStatistic.getTeamAccountSum().subtract(amount);
                teamAccountSumOne = parentUserStatistic.getTeamAccountSumOne().subtract(amount);
            }
            parentUserStatistic.setTeamAccountSum(teamAccountSum);
            parentUserStatistic.setTeamAccountSumOne(teamAccountSumOne);
            baseMapper.updateById(parentUserStatistic);
            // 查询爷级统计数据
            Long grandpatId = selectByParentId(parentUserStatistic.getUserId());
            if (grandpatId == null) {
                return flag;
            }
            UserStatistic grandpaUserStatistic = selectByUserId(grandpatId);
            if (grandpaUserStatistic != null) {
                BigDecimal teamAccountSumTwo = new BigDecimal(0);
                // 给父级的微分二级和总数增加
                if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
                    teamAccountSum = grandpaUserStatistic.getTeamAccountSum().add(amount);
                    teamAccountSumTwo = grandpaUserStatistic.getTeamAccountSumTwo().add(amount);
                } else {
                    teamAccountSum = grandpaUserStatistic.getTeamAccountSum().subtract(amount);
                    teamAccountSumTwo = grandpaUserStatistic.getTeamAccountSumTwo().subtract(amount);
                }
                grandpaUserStatistic.setTeamAccountSum(teamAccountSum);
                grandpaUserStatistic.setTeamAccountSumTwo(teamAccountSumTwo);
                baseMapper.updateById(grandpaUserStatistic);
                // 查询是否还有祖级统计数据递归调用
                setParentStatisticAccount(grandpaUserStatistic.getUserId(), amount, serialType);
            }
        }
        return flag;
    }

    /**
     * @Description: 递归查询给父级的累积收益增加总数
     * @Author: senghor
     * @Date: 2019/5/24 0024 15:51
     */
    public void setParentStatisticAccount(Long userId, BigDecimal amount, Integer serialType) {
        // 查询是否有父级ID
        Long parentId = selectByParentId(userId);
        if (parentId != null) {
            UserStatistic parentUserStatistic = selectByUserId(parentId);
            if (parentUserStatistic != null) {
                BigDecimal teamAccountSum = new BigDecimal(0);
                // 给父级的累积收益总数增加
                if (serialType == BetaEnum.serialType.SERIAL_ADD.getCode()) {
                    teamAccountSum = parentUserStatistic.getTeamAccountSum().add(amount);
                } else {
                    teamAccountSum = parentUserStatistic.getTeamAccountSum().subtract(amount);
                }
                parentUserStatistic.setTeamAccountSum(teamAccountSum);
                baseMapper.updateById(parentUserStatistic);
                setParentStatisticAccount(parentUserStatistic.getUserId(), amount, serialType);
            }
        }
    }

    /**
     * @Description: 根据统计的用户ID查询父级的统计数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 11:45
     */
    public Long selectByParentId(Long userId) {
        return baseMapper.selectByParentId(userId);
    }

    /**
     * @Description: 手动更新团队人数
     * @Author: senghor
     * @Date: 2019/5/24 0024 17:07
     */
    @Override
    public Wrapper refreshTeamCount() {
        long startTime = System.currentTimeMillis();

        // 所有已实名的推荐关系
        List<UserLabour> userLabourAuthList = userLabourService.selectByAuth();
        // 所有的推荐关系
        List<UserLabour> userLabourList = userLabourService.selectAllUserId();
        // 查询所有有下级的父级ID
        List<Long> parentIdList = userLabourService.selectAllParentId();
        // 查询所有人的微分和累积收益的钱包
        List<BetaAccount> betaAccountsList = betaAccountService.selectAllUserId();
        // 查询所有人的统计数据
        List<UserStatistic> userStatisticList = baseMapper.selectAllUserId();
        // 查询所有套餐
        List<ComboGroup> comboGroupList = comboGroupService.selectListSection();

        //查询出当前用户的所有领养中的订单
        List<BetaOrder> betaOrderList = betaOrderService.selectAllUserId();
        // 成功数量
        Integer successSum = 0;
        // 失败数量
        Integer errorSum = 0;
        // 线程数量
        Integer threadSum = 20;
        // 按用户量分组
        List<List<Long>> threadIds = ListUtil.averageAssign(parentIdList, threadSum);
        //开启多线程
        ExecutorService exs = Executors.newFixedThreadPool(threadSum);
        try {
            List<FutureTask<TaskDto>> futureList = new ArrayList<>();
            for (int i = 0; i < threadIds.size(); i++) {
                List<Long> listId = threadIds.get(i);
                FutureTask<TaskDto> futureTask = new FutureTask<>(new RefreshTeamCountTask(this, redisTemplate, i
                        , userLabourAuthList, userLabourList, betaAccountsList
                        , userStatisticList, comboGroupList, betaOrderList
                        , listId));
                //提交任务，添加返回，Runnable特性
                exs.submit(futureTask);
                //Future特性
                futureList.add(futureTask);
            }
            //结果归集
            while (futureList.size() > 0) {
                Iterator<FutureTask<TaskDto>> iterable = futureList.iterator();
                //遍历一遍
                while (iterable.hasNext()) {
                    Future<TaskDto> future = iterable.next();
                    if (future.isDone() && !future.isCancelled()) {
                        //Future特性
                        TaskDto taskDto = future.get();
                        successSum += taskDto.getSuccessSum();
                        errorSum += taskDto.getErrorSum();
                        System.out.println("任务i=" + taskDto.getI() + "获取完成，移出任务队列！" + DateUtils.now());
                        //任务完成移除任务
                        iterable.remove();
                    } else {
                        //避免CPU高速轮循，可以休息一下。
                        Thread.sleep(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();
        }

//        for (int i = 0; i < threadIds.size(); i++) {
//            List<Long> listId = threadIds.get(i);
//            taskExecutor.execute(() -> {
//                int taskSuccessSum = 0;
//                int taskErrorSum = 0;
//                for (Long userId : listId) {
//                    try {
//                        // 获取统计对象，无则创建数据保存到数据库
//                        UserStatistic userStatistic = selectByUserId(userId, userStatisticList);
//                        TeamCountResultsVo teamCountResultsVo = countTeamPeopleSum(Arrays.asList(userId),
//                                new TeamCountResultsVo(),
//                                betaAccountsList, userLabourList,
//                                comboGroupList, betaOrderList);
//                        // 统计团队人数
//                        if (teamCountResultsVo.getTeamPeopleSumList().size() > 0) {
//                            userStatistic.setTeamCountOne(teamCountResultsVo.getTeamPeopleSumList().get(0));
//                        }
//                        if (teamCountResultsVo.getTeamPeopleSumList().size() > 1) {
//                            userStatistic.setTeamCountTwo(teamCountResultsVo.getTeamPeopleSumList().get(1));
//                        }
//                        userStatistic.setTeamCount(teamCountResultsVo.getTeamPeopleSumList().stream().mapToInt(Integer::intValue).sum());
//                        // 统计团队微分总数
//                        if (teamCountResultsVo.getTeamScoreSumList().size() > 0) {
//                            userStatistic.setTeamScoreSumOne(teamCountResultsVo.getTeamScoreSumList().get(0));
//                        }
//                        if (teamCountResultsVo.getTeamScoreSumList().size() > 1) {
//                            userStatistic.setTeamScoreSumTwo(teamCountResultsVo.getTeamScoreSumList().get(1));
//                        }
//                        BigDecimal teamScoreSum = BigDecimal.ZERO;
//                        for (BigDecimal sum : teamCountResultsVo.getTeamScoreSumList()) {
//                            teamScoreSum = teamScoreSum.add(sum);
//                        }
//                        userStatistic.setTeamScoreSum(teamScoreSum);
//
//                        // 统计累积收益
//                        if (teamCountResultsVo.getTeamTotalSumList().size() > 0) {
//                            userStatistic.setTeamTotalSumOne(teamCountResultsVo.getTeamTotalSumList().get(0));
//                        }
//                        if (teamCountResultsVo.getTeamTotalSumList().size() > 1) {
//                            userStatistic.setTeamTotalSumTwo(teamCountResultsVo.getTeamTotalSumList().get(1));
//                        }
//                        BigDecimal teamTotalSum = BigDecimal.ZERO;
//                        for (BigDecimal sum : teamCountResultsVo.getTeamTotalSumList()) {
//                            teamTotalSum = teamTotalSum.add(sum);
//                        }
//                        userStatistic.setTeamTotalSum(teamTotalSum);
//
//                        // 统计总资产
//                        if (teamCountResultsVo.getTeamAccountSumList().size() > 0) {
//                            userStatistic.setTeamAccountSumOne(teamCountResultsVo.getTeamAccountSumList().get(0));
//                        }
//                        if (teamCountResultsVo.getTeamAccountSumList().size() > 1) {
//                            userStatistic.setTeamAccountSumTwo(teamCountResultsVo.getTeamAccountSumList().get(1));
//                        }
//                        BigDecimal teamAccountSum = BigDecimal.ZERO;
//                        for (BigDecimal sum : teamCountResultsVo.getTeamAccountSumList()) {
//                            teamAccountSum = teamAccountSum.add(sum);
//                        }
//                        userStatistic.setTeamAccountSum(teamAccountSum);
//                        // 如果不存在则创建初始统计数据
//                        if (userStatistic.getUserId() == null) {
//                            userStatistic.setCreateTime(new Date());
//                            userStatistic.setUpdateTime(new Date());
//                            userStatistic.setUserId(userId);
//                            this.insert(userStatistic);
//                        } else {
//                            EntityWrapper userParm = new EntityWrapper();
//                            userParm.eq("user_id", userId);
//                            userStatistic.setUpdateTime(new Date());
//                            this.update(userStatistic, userParm);
//                        }
//
//                        Integer countSum = countTeamAuthSum(Arrays.asList(userId), 0, userLabourAuthList);
//                        redisTemplate.set(RedisKeyUtil.AUTH_TEAM_COUNT + userId, countSum);
//                        log.info("更新下级团队实名人数" + countSum);
//                        taskSuccessSum++;
//                        log.info("更新下级团队业绩" + userStatistic.toString());
//                    } catch (Exception e) {
//                        taskErrorSum++;
//                        e.printStackTrace();
//                    }
//                }
//                Map<String, Integer> map = new HashMap<>();
//                map.put("taskSuccessSum", taskSuccessSum);
////                redisTemplate.set(RedisKeyUtil.);
//            });
//        }
//        try {
//            Thread.sleep(70000);
//            Set<byte[]> set;
//            do {
//                set = redisTemplate.keys("*");
//            } while (set.size() == threadIds.size());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        double endTime = System.currentTimeMillis();
        double countTime = NumberUtil.div(endTime - startTime,1000, 2);
        return WrapMapper.ok("统计耗时:" + countTime + "s,成功人数:" + successSum + "失败人数:" + errorSum);
    }


    /**
     * @Description: 统计团队各级统计数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 17:08
     */
    // 统计团队各级业绩
    public TeamCountResultsVo countTeamPeopleSum(List<Long> userIds, TeamCountResultsVo teamCountResultsVo
            , List<BetaAccount> betaAccountsList, List<UserLabour> userLabourList, List<ComboGroup> comboGroupList
            , List<BetaOrder> betaOrderList) {
        if (userIds.size() > 0) {
            // 本次的团队人数
            Integer teamPeopleSum = 0;
            // 本次的团队微分总数
            BigDecimal teamScoreSum = new BigDecimal(0);
            // 本次的团队资产总额
            BigDecimal teamAccountSum = new BigDecimal(0);
            // 本次的团队累计收益总额
            BigDecimal teamTotalSum = new BigDecimal(0);
            // 保存本次的下级团队ID
            List<Long> sonUserIds = new ArrayList<>();

            for (Long userId : userIds) {
                // 查询当前用户下级团队
                List<UserLabour> sonUserLabourList = selectByParentId(userId, userLabourList);
                // 增加本次团队人数
                teamPeopleSum += sonUserLabourList.size();
                for (UserLabour userLabour : sonUserLabourList) {
                    sonUserIds.add(userLabour.getUserId());
                    teamAccountSum = teamAccountSum.add(selectOrderCount(userLabour.getUserId(), betaOrderList));
                    for (BetaAccount betaAccount : betaAccountsList) {
                        if (userLabour.getUserId().equals(betaAccount.getUserId())) {
                            if (betaAccount.getProductType() == BetaEnum.accountType.BETA.getCode()) {
                                teamScoreSum = teamScoreSum.add(betaAccount.getBalance());
                            }
                            if (betaAccount.getProductType() == BetaEnum.accountType.TOTAL_EARNINGS.getCode()) {
                                teamTotalSum = teamTotalSum.add(betaAccount.getBalance());
                            }
                        }
                    }
                }
            }
            // 有下级团队才进行下次统计
            if (sonUserIds.size() != 0) {
                teamCountResultsVo.getTeamPeopleSumList().add(teamPeopleSum);
                teamCountResultsVo.getTeamScoreSumList().add(teamScoreSum);
                teamCountResultsVo.getTeamAccountSumList().add(teamAccountSum);
                teamCountResultsVo.getTeamTotalSumList().add(teamTotalSum);
                countTeamPeopleSum(sonUserIds, teamCountResultsVo, betaAccountsList, userLabourList, comboGroupList, betaOrderList);
            }
        }
        return teamCountResultsVo;
    }

    /**
     * @Description: 返回父级ID是当前用户的数据
     * @Author: senghor
     * @Date: 2019/5/24 0024 17:07
     */
    public List<UserLabour> selectByParentId(Long parentId, List<UserLabour> userLabourList) {
        List<UserLabour> userLabours = new ArrayList<>();
        for (UserLabour userLabour : userLabourList) {
            if (parentId.equals(userLabour.getParentId())) {
                userLabours.add(userLabour);
            }
        }
        return userLabours;
    }


    /**
     * 计算用户总资产(只计算收益价格总数)
     *
     * @param userId
     * @return
     */
    public BigDecimal selectOrderCount(Long userId, List<BetaOrder> betaOrderList) {
        BigDecimal total = BigDecimal.ZERO;
        for (BetaOrder betaOrder : betaOrderList) {
            if (userId.equals(betaOrder.getUserId())) {
                total = total.add(betaOrder.getEarningsPrice());
            }
        }
        return total;
    }


    /**
     * 计算用户总资产
     *
     * @param userId
     * @return
     */
    public BigDecimal selectOrderCount(Long userId, List<ComboGroup> comboGroupList, List<BetaOrder> betaOrderList) {
        //查询出当前用户的所有领养中的订单
        List<BetaOrder> orderVos = new ArrayList<>();
        for (BetaOrder betaOrder : betaOrderList) {
            if (userId.equals(betaOrder.getUserId())) {
                orderVos.add(betaOrder);
            }
        }
        BigDecimal total = BigDecimal.ZERO;
        if (orderVos.size() == 0) {
            return total;
        }
        //迭代计算出总资产
        for (BetaOrder vo : orderVos) {
            total = total.add(vo.getPrice());
            BigDecimal deff = vo.getEarningsPrice().subtract(vo.getPrice());
            ComboGroup comboVo = new ComboGroup();
            for (ComboGroup comboGroup : comboGroupList) {
                if (vo.getComboId().equals(comboGroup.getId())) {
                    comboVo = comboGroup;
                }
            }
            int day = DateTimeUtils.daysBetween(vo.getSucceedTime(), new Date()) + 1;//加一天，不足一天按一天计算。
            BigDecimal dayDeff = deff.divide(new BigDecimal(comboVo.getDays()), 4, BigDecimal.ROUND_DOWN);
            if (day < comboVo.getDays()) {
                dayDeff = dayDeff.multiply(new BigDecimal(day));
            } else {
                dayDeff = deff;//如果因为订单问题导致当天没有放出，那么收益不能重复计算。
            }
            total = total.add(dayDeff);
        }
        return total;
    }


    /**
     * 用户统计列表
     *
     * @param page
     * @param userStatisticDto
     * @return
     */
    @Override
    public Page<UserStatisticVo> selectUserList(Page<UserStatisticVo> page, UserStatisticDto userStatisticDto) {
        page.setRecords(baseMapper.selectUserList(page, userStatisticDto));
        return page;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @Override
    public UserStatisticVo selectAil(Integer id) {
        return baseMapper.selectAil(id);
    }

    /**
     * @Description: 根据推荐集合查询下级有效实名用户人数
     * @Author: senghor
     * @Date: 2019/7/5 15:17
     */
    public Integer countTeamAuthSum(List<Long> userIds, Integer teamCountSum, List<UserLabour> userLabourList) {
        if (userIds.size() > 0) {
            // 本次的团队人数
            Integer teamPeopleSum = 0;
            // 保存本次的下级团队ID
            List<Long> sonUserIds = new ArrayList<>();

            for (Long userId : userIds) {
                // 查询当前用户下级团队
                List<UserLabour> sonUserLabourList = selectByParentId(userId, userLabourList);
                if (sonUserLabourList.size() > 0) {
                    // 增加本次团队人数
                    teamPeopleSum += sonUserLabourList.size();
                    for (UserLabour userLabour : sonUserLabourList) {
                        sonUserIds.add(userLabour.getUserId());
                    }
                }
            }
            // 有下级团队才进行下次统计
            if (sonUserIds.size() != 0) {
                teamCountSum += teamPeopleSum;
                teamCountSum = countTeamAuthSum(sonUserIds, teamCountSum, userLabourList);
            }
        }
        return teamCountSum;
    }
}
