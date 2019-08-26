package com.jdcloud.provider.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.LabourEnum;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.PersonalCardAuthDto;
import com.jdcloud.provider.dto.PersonalCardDto;
import com.jdcloud.provider.mapper.PersonalCardMapper;
import com.jdcloud.provider.pojo.PersonalCard;
import com.jdcloud.provider.pojo.UserLabour;
import com.jdcloud.provider.pojo.UserLabourAccount;
import com.jdcloud.provider.pojo.UserLabourAccountRecord;
import com.jdcloud.provider.pojo.vo.LabourUserVo;
import com.jdcloud.provider.pojo.vo.PersonalCardVo;
import com.jdcloud.provider.service.*;
import com.jdcloud.util.ListUtil;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 用户收款信息表 服务实现类
 * </p>
 *
 * @author helne
 * @since 2019-03-22
 */
@Service
@Slf4j
public class PersonalCardServiceImpl extends ServiceImpl<PersonalCardMapper, PersonalCard> implements PersonalCardService {

    @Autowired
    private UserLabourService userLabourService;
    @Autowired
    private UserLabourAccountService userLabourAccountService;
    @Autowired
    private ParameterConfigService parameterConfigService;
    @Autowired
    private UserLabourAccountRecordService userLabourAccountRecordService;
    @Autowired
    private PersonalCardService personalCardService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private TaskExecutor taskExecutor;

 /*   @Override
    public Page<PersonalCardVo> personalCardListPageList(Page<PersonalCardVo> page, PersonalCardDto personalCardDto) {
        page.setRecords( personalCardMapper.personalCardListPageList(page, personalCardDto ) );
        return page;
    }*/

    @Override
    public Page<PersonalCardVo> personalCardListPageList(Page<PersonalCardVo> page, PersonalCardDto personalCardDto) {
        //列表数据
        List<PersonalCard> list = baseMapper.personalCardListPageList(page, personalCardDto);
        List<PersonalCardVo> volist = new ArrayList<>();
        for (PersonalCard pc : list) {
            PersonalCardVo vo = new PersonalCardVo();
            BeanUtils.copyProperties(pc, vo);

            // 查询推广人数据
            UserLabour labourPam = new UserLabour();
            labourPam.setUserId(pc.getUserId());
            UserLabour labourVo = userLabourService.selectOne(new EntityWrapper<UserLabour>(labourPam));
            if (labourVo != null) {
                PersonalCard pcQuery = new PersonalCard();
                pcQuery.setUserId(labourVo.getParentId());
                PersonalCard personalCard = baseMapper.selectOne(pcQuery);
                if (personalCard != null) {
                    vo.setDirectPushName(personalCard.getName());
                    vo.setDirectPushPhone(personalCard.getPhone());
                }

                String[] parents = labourVo.getAncestors().split(",");
                int num = parents.length;
                if (num == 2) {
                    PersonalCard pcQueryTwo = new PersonalCard();
                    pcQueryTwo.setUserId(Long.parseLong(parents[0]));
                    PersonalCard personalCardTwo = baseMapper.selectOne(pcQueryTwo);
                    if (personalCardTwo != null) {
                        vo.setSecondLevelRecommendName(personalCardTwo.getName());
                        vo.setSecondLevelRecommendPhone(personalCardTwo.getPhone());
                    }
                }
            }
            volist.add(vo);
        }
        page.setRecords(volist);
        return page;
    }

    /**
     * 已处理实名，最新修改的排前面
     *
     * @param page
     * @param personalCardDto
     * @return
     */
    @Override
    public Page<PersonalCardVo> personalCardListPageListNew(Page<PersonalCardVo> page, PersonalCardDto personalCardDto) {
        //列表数据
        List<PersonalCard> list = baseMapper.personalCardListPageListNew(page, personalCardDto);
        List<PersonalCardVo> volist = new ArrayList<>();
        for (PersonalCard pc : list) {
            PersonalCardVo vo = new PersonalCardVo();
            BeanUtils.copyProperties(pc, vo);

            // 查询推广人数据
            UserLabour labourPam = new UserLabour();
            labourPam.setUserId(pc.getUserId());
            UserLabour labourVo = userLabourService.selectOne(new EntityWrapper<UserLabour>(labourPam));
            if (labourVo != null) {
                PersonalCard pcQuery = new PersonalCard();
                pcQuery.setUserId(labourVo.getParentId());
                PersonalCard personalCard = baseMapper.selectOne(pcQuery);
                if (personalCard != null) {
                    vo.setDirectPushName(personalCard.getName());
                    vo.setDirectPushPhone(personalCard.getPhone());
                }

                String[] parents = labourVo.getAncestors().split(",");
                int num = parents.length;
                if (num == 2) {
                    PersonalCard pcQueryTwo = new PersonalCard();
                    pcQueryTwo.setUserId(Long.parseLong(parents[0]));
                    PersonalCard personalCardTwo = baseMapper.selectOne(pcQueryTwo);
                    if (personalCardTwo != null) {
                        vo.setSecondLevelRecommendName(personalCardTwo.getName());
                        vo.setSecondLevelRecommendPhone(personalCardTwo.getPhone());
                    }
                }
            }
            volist.add(vo);
        }
        page.setRecords(volist);
        return page;
    }


    @Override
    public List<PersonalCardVo> personalCardListExcelList(PersonalCardDto personalCardDto) {
        List<PersonalCardVo> list = baseMapper.personalCardListExcelList(personalCardDto);
        for (PersonalCardVo personalCardVo : list) {
            if (personalCardVo.getDirectPushPhone() != null && personalCardVo.getDirectPushPhone().equals(personalCardVo.getSecondLevelRecommendPhone())) {
                personalCardVo.setSecondLevelRecommendPhone(null);
                personalCardVo.setSecondLevelRecommendName(null);
            }
        }
        return list;
    }

    @Override
    public PersonalCard selectDetail(Long userId) {
        PersonalCard card = new PersonalCard();
        card.setUserId(userId);
        card = baseMapper.selectOne(card);
        return card;
    }

    /**
     * @Description: 实名认证审核
     * @Author: senghor
     * @Date: 2019/6/9 20:15
     */
    @Override
    @Transactional
    public Wrapper updateByIdPersonalCard(PersonalCardAuthDto personalCardAuthDto) {
        PersonalCard personalCard = baseMapper.selectById(personalCardAuthDto.getId());
        if (personalCard == null) {
            return WrapMapper.error("审核失败，请联系管理员！");
        }
        if (personalCard.getAuthStauts() == personalCardAuthDto.getAuthStauts()) {
            return WrapMapper.error("当前状态与选择状态一致无需修改!");
        }
        Integer initCheck = personalCard.getInitCheck();
        // 只有通过的请求才添加是否初次审核通过1=是2=否
        if (personalCardAuthDto.getAuthStauts().equals(BetaEnum.authStauts.success.getCode())) {
            personalCard.setInitCheck(BetaEnum.initCheck.NO.getCode());
        }
        personalCard.setUpdateTime(new Date());
        personalCard.setAuthStauts(personalCardAuthDto.getAuthStauts());
        Integer row = baseMapper.updateById(personalCard);
        if (row != 1) {
            return WrapMapper.error("增加失败");
        }
        // 将审核的数据放入缓存
        JSONObject jsonObject = new JSONObject(personalCard);
        redisTemplate.set(RedisKeyUtil.PERSONAL_CARD_USERID + personalCard.getUserId(), jsonObject);

        if (personalCardAuthDto.getAuthStauts().equals(BetaEnum.authStauts.success.getCode())) {
            try {
                UserLabour labourPam = new UserLabour();
                labourPam.setUserId(personalCard.getUserId());
                UserLabour labourVo = userLabourService.selectOne(new EntityWrapper<UserLabour>(labourPam));
                // 不加赠送币的
                if (initCheck.equals(BetaEnum.initCheck.YES.getCode())) {
                    //无推广绑定关系直接返回
                    if (labourVo == null) {
                        log.info("无推广绑定关系直接返回");
                        return WrapMapper.ok("审核成功");
                    }

                    // 判断这个用户获取到注册奖励
                    LabourUserVo vo = userLabourAccountService.selectLabourUserTWO(labourPam.getUserId());
                    if (null == vo || null == vo.getLabourMoney()) {
                        // 查询到这个用户是否有流水
                        String[] parents = labourVo.getAncestors().split(",");
                        int num = parents.length;
                        int hierarchy = 1;
                        while (num > 0) {
                            String parent = parents[num - 1];//取出对应父级ID
                            UserLabourAccount labourAccount = new UserLabourAccount();
                            labourAccount.setUserId(Long.parseLong(parent));
                            UserLabourAccount accountVo = userLabourAccountService.selectOne(new EntityWrapper<UserLabourAccount>(labourAccount));
                            if (accountVo == null) {
                                log.info("取出对应父级ID 为空");
                                return WrapMapper.ok("审核成功");
                            }
                            BigDecimal percent = BigDecimal.ZERO;
                            if (hierarchy == 1) {
                                percent = parameterConfigService.getBigDecimalValue("secondBitt");
                            } else {
                                percent = parameterConfigService.getBigDecimalValue("threeBitt");
                            }
                            percent = percent == null ? BigDecimal.ZERO : percent;
                            BigDecimal afterAmount = accountVo.getBalance().add(percent);
                            //生成推荐奖励流水
                            UserLabourAccountRecord record = new UserLabourAccountRecord();
                            record.setBeforeAmount(accountVo.getBalance());
                            record.setAmount(percent);
                            record.setAfterAmount(afterAmount);
                            record.setOrderUserId(personalCard.getUserId());
                            record.setHierarchy(hierarchy++);
                            record.setUserId(accountVo.getUserId());
                            record.setType(LabourEnum.LabourOrderType.REGISTER.getCode());
                            record.setCreateTime(new Date());

                            //新增流水记录
                            userLabourAccountRecordService.insert(record);
                            //更新用户推广奖励资产
                            accountVo.setBalance(afterAmount);
                            accountVo.setTotalBalance(accountVo.getTotalBalance().add(percent));
                            accountVo.setUpdateTime(new Date());
                            userLabourAccountService.updateLabourAccount(accountVo);
                            num--;
                        }
                    }
                }

                // 团队关系
                // 判断父级当前身份是否大于普通用户

                EntityWrapper<PersonalCard> entity = new EntityWrapper<>();
                entity.eq("user_id", labourVo.getParentId());
                entity.gt("current_identity", BetaEnum.currentIdentity.USER.getCode());
                PersonalCard person = personalCardService.selectOne(entity);
                if (person != null) {
                    // 去获取推荐人下面的第一个人
                    UserLabour list = userLabourService.selectlistLimit(person.getUserId());
                    if (list != null) {
                        UserLabour labour = new UserLabour();
                        labour.setGeneralizeId(list.getGeneralizeId());
                        labour.setServiceId(list.getServiceId());
                        labour.setPeersServiceId(list.getPeersServiceId());
                        labour.setPartnerId(list.getPartnerId());
                        labour.setPeersPartnerId(list.getPeersPartnerId());
                        labour.setPartnerv1Id(list.getPartnerv1Id());
                        labour.setPartnerv2Id(list.getPartnerv2Id());
                        labour.setId(labourVo.getId());
                        labour.setUpdateTime(new Date());
                        userLabourService.updateById(labour);
                    }
                } else {
                    UserLabour labour = new UserLabour();
                    UserLabour list = new UserLabour();
                    list.setUserId(labourVo.getParentId());
                    list = userLabourService.selectOne(new EntityWrapper<>(list));
                    if (list != null) {
                        labour.setGeneralizeId(list.getGeneralizeId());
                        labour.setServiceId(list.getServiceId());
                        labour.setPeersServiceId(list.getPeersServiceId());
                        labour.setPartnerId(list.getPartnerId());
                        labour.setPeersPartnerId(list.getPeersPartnerId());
                        labour.setPartnerv1Id(list.getPartnerv1Id());
                        labour.setPartnerv2Id(list.getPartnerv2Id());
                        labour.setId(labourVo.getId());
                        labour.setUpdateTime(new Date());
                        userLabourService.updateById(labour);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return WrapMapper.ok("审核成功");
    }

    /**
     * @Description: 升级用户身份
     * @Author: senghor
     * @Date: 2019/5/14 0014 23:32
     */
    @Override
    public Wrapper updateCurrentIdentity(PersonalCardAuthDto personalCardAuthDto) {
        PersonalCard personalCard = new PersonalCard();
        personalCard.setUserId(personalCardAuthDto.getUserId());
        personalCard = baseMapper.selectOne(personalCard);
        if (personalCard == null) {
            return WrapMapper.error("获取不到用户");
        }
        if (!personalCard.getCurrentIdentity().equals(personalCardAuthDto.getCurrentIdentity().toString())) {
            personalCard.setCurrentIdentity(personalCardAuthDto.getCurrentIdentity().toString());
            baseMapper.updateById(personalCard);
            // 查询所有的直推
            return WrapMapper.ok("升级成功");
        } else {
            return WrapMapper.error("只允许升级身份");
        }
    }


    /**
     * 校正推广收益
     *
     * @return
     */
    @Override
    public Wrapper checkPushEarnings() {
        // 查询所有需要校正的人数
        Integer type = BetaEnum.authStauts.success.getCode();
        List<PersonalCard> cardPam = personalCardService.selectCheckPush(type);
        for (PersonalCard card : cardPam) {
            savePushEarnings(card.getUserId());
        }
        return WrapMapper.ok("校正成功！");
    }


    /**
     * 查询推广收益校正人数
     *
     * @param
     * @param
     * @return
     */
    @Override
    public List<PersonalCard> selectCheckPush(Integer type) {
        return baseMapper.selectCheckPush(type);
    }

    /**
     * 统计需要校正人数
     *
     * @param type
     * @return
     */
    @Override
    public Integer recommendEarningsSum(Integer type) {
        return baseMapper.recommendEarningsSum(type);
    }


    /**
     * 增加推广收益抽成
     */
    public void savePushEarnings(Long userId) {
        UserLabour labourPam = new UserLabour();
        labourPam.setUserId(userId);
        UserLabour labourVo = userLabourService.selectOne(new EntityWrapper<UserLabour>(labourPam));
        //无推广绑定关系直接返回
        if (labourVo == null) {
            log.info("无推广绑定关系直接返回");
            return;
        }
        String[] parents = labourVo.getAncestors().split(",");
        int num = parents.length;
        int hierarchy = 1;
        while (num > 0) {
            String parent = parents[num - 1];//取出对应父级ID
            UserLabourAccount labourAccount = new UserLabourAccount();
            labourAccount.setUserId(Long.parseLong(parent));
            UserLabourAccount accountVo = userLabourAccountService.selectOne(new EntityWrapper<UserLabourAccount>(labourAccount));
            if (accountVo == null) {
                log.info("取出对应父级ID 为空");
                return;
            }
            BigDecimal percent = BigDecimal.ZERO;
            if (hierarchy == 1) {
                percent = parameterConfigService.getBigDecimalValue("secondBitt");
            } else {
                percent = parameterConfigService.getBigDecimalValue("threeBitt");
            }
            percent = percent == null ? BigDecimal.ZERO : percent;
            BigDecimal afterAmount = accountVo.getBalance().add(percent);
            //生成推荐奖励流水
            UserLabourAccountRecord record = new UserLabourAccountRecord();
            record.setBeforeAmount(accountVo.getBalance());
            record.setAmount(percent);
            record.setAfterAmount(afterAmount);
            record.setOrderUserId(userId);
            record.setHierarchy(hierarchy++);
            record.setUserId(accountVo.getUserId());
            record.setType(LabourEnum.LabourOrderType.REGISTER.getCode());
            record.setCreateTime(new Date());

            //新增流水记录
            userLabourAccountRecordService.insert(record);
            //更新用户推广奖励资产
            accountVo.setBalance(afterAmount);
            accountVo.setTotalBalance(accountVo.getTotalBalance().add(percent));
            accountVo.setUpdateTime(new Date());
            userLabourAccountService.updateLabourAccount(accountVo);
            num--;
        }
    }


    /**
     * @Description: 刷新缓存数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    @Override
    public Wrapper<String> refreshRedis() {
        // 清空之前的缓存
//        redisTemplate.delete(redisTemplate.keys(RedisKeyUtil.PERSONAL_CARD_USERID + "*"));
        // 获取所有实名用户
        List<PersonalCard> personalCards = baseMapper.selectList(null);
        AtomicInteger sum = new AtomicInteger(0);
        List<List<PersonalCard>> threadPersonalCards = ListUtil.averageAssign(personalCards, 20);
        for (List<PersonalCard> personalCardList : threadPersonalCards) {
            List<PersonalCard> list = personalCardList;
            taskExecutor.execute(() -> {
                for (PersonalCard pc : list) {
                    sum.incrementAndGet();
                    log.info("当前第" + sum);
                    redisTemplate.set(RedisKeyUtil.PERSONAL_CARD_USERID + pc.getUserId(), new JSONObject(pc));
                }
            });
        }
        return WrapMapper.ok("刷新成功");
    }

    /**
     * @Description: 修改用户概率
     * @Author: senghor
     * @Date: 2019/8/13 16:52
     */
    @Override
    public Boolean updateMPNnum(PersonalCardAuthDto personalCardAuthDto) {
        PersonalCard personalCard = new PersonalCard();
        personalCard.setUserId(personalCardAuthDto.getUserId());
        personalCard = baseMapper.selectOne(personalCard);
        if (personalCard == null) {
            return false;
        }
        personalCard.setMpnNum(personalCardAuthDto.getMpnNum());
        return this.updateById(personalCard);
    }

    @Override
    public Integer updateMPNnumList(PersonalCardDto personalCardDto) {
        Integer updateSum = baseMapper.updateMPNnumList(personalCardDto);
        // 获取所有实名用户
        List<PersonalCard> personalCards = baseMapper.selectList(null);
        AtomicInteger sum = new AtomicInteger(0);
        List<List<PersonalCard>> threadPersonalCards = ListUtil.averageAssign(personalCards, 20);
        for (List<PersonalCard> personalCardList : threadPersonalCards) {
            List<PersonalCard> list = personalCardList;
            taskExecutor.execute(() -> {
                for (PersonalCard pc : list) {
                    sum.incrementAndGet();
                    log.info("当前第" + sum);
                    redisTemplate.set(RedisKeyUtil.PERSONAL_CARD_USERID + pc.getUserId(), new JSONObject(pc));
                }
            });
        }
        return updateSum;
    }

}
