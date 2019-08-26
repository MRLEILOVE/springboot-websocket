package com.jdcloud.provider.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.ErrorCodeEnum;
import com.jdcloud.base.enums.ParameterConfigEnum;
import com.jdcloud.base.exception.BusinessException;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.BetaMaleOrderDto;
import com.jdcloud.provider.dto.BetaOrderDto;
import com.jdcloud.provider.mapper.BetaMaleMapper;
import com.jdcloud.provider.mapper.BetaOrderMapper;
import com.jdcloud.provider.mapper.ComboGroupMapper;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.pojo.vo.BetaMaleOrderVo;
import com.jdcloud.provider.pojo.vo.BetaOrderVo;
import com.jdcloud.provider.service.*;
import com.jdcloud.util.BigDecimalUtil;
import com.jdcloud.util.ListUtil;
import com.jdcloud.util.RandomUtil;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import com.jdcloud.util.zookeeper.SnowflakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 贝塔狗---订单表（每笔装让记录都会生成一条订单数据作为记录） 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-15
 */
@Slf4j
@Service
public class BetaOrderServiceImpl extends ServiceImpl<BetaOrderMapper, BetaOrder> implements BetaOrderService {

    @Resource
    private BetaOrderMapper betaOrderMapper;
    @Resource
    private ComboGroupMapper comboGroupMapper;
    @Autowired
    private PersonalCardService personalCardService;
    @Resource
    private BetaMaleMapper betaMaleMapper;
    @Autowired
    private BetaMaleService betaMaleService;
    @Autowired
    private ComboGroupService comboGroupService;
    @Autowired
    private BetaProcessedaOrderService betaProcessedaOrderService;
    @Autowired
    private UserStatisticService userStatisticService;
    @Resource
    private TaskExecutor taskExecutor;

    @Autowired
    private BetaAccountService betaAccountService;
    @Autowired
    private BetaFreezeAccountService betaFreezeAccountService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BetaFreezeAccountRecordService betaFreezeAccountRecordService;
    @Autowired
    private BetaLockedAccountService betaLockedAccountService;

    @Override
    public Page<BetaOrder> betaOrderListPage(Page<BetaOrder> page, Integer id) {
        page.setRecords(betaOrderMapper.betaOrderListPage(page, id));
        return page;
    }

    @Override
    public Page<BetaOrder> selectPageList(Page<BetaOrder> page, BetaOrder dto) {
        page.setRecords(betaOrderMapper.selectPageList(page, dto));
        return page;
    }


    @Override
    public Page<BetaMaleOrderVo> betaOrderListPageAppeal(Page<BetaMaleOrderVo> page, BetaMaleOrderDto betaMaleOrderDto) {
        Map<String, Object> map = betaMaleOrderDto.getParams();
        String fieldName = "create_time";
        if (map.get("status") != null) {
            String status = (String) map.get("status");
            switch (status) {
                case "1":
                    fieldName = "create_time";
                    break;
                case "2":
                    fieldName = "pay_time";
                    break;
                case "3":
                    fieldName = "succeed_time";
                    break;
                case "4":
                    fieldName = "register_time";
                    break;
            }
        }
        page.setRecords(baseMapper.betaOrderListPageAppeal(page, fieldName, betaMaleOrderDto));
        return page;
    }


    /**
     * 查询订单详情
     *
     * @param id
     * @return
     */
    @Override
    public BetaMaleOrderVo orderListDetails(Integer id) {
        BetaOrder betaOrder = betaOrderMapper.selectById(id);

        BetaMaleOrderVo orderVo = new BetaMaleOrderVo();
        BeanUtils.copyProperties(betaOrder, orderVo);
        PersonalCard cardPam = new PersonalCard();
        cardPam.setUserId(betaOrder.getSellUserId());
        // 查询出转让方的用户信息
        PersonalCard cardSellUserId = personalCardService.selectOne(new EntityWrapper<>(cardPam));
        if (cardSellUserId != null) {
        }
        // 获取到公狗的参数 然后取出套餐信息
        BetaMale betaMale = betaMaleMapper.selectById(betaOrder.getFeminaId());
        if (betaMale != null) {
            ComboGroup comboGroup = comboGroupMapper.selectById(betaMale.getComboId());
            if (comboGroup != null) {
                BeanUtils.copyProperties(comboGroup, orderVo);
            }
        }
        // 查询领养方的用户信息
        if (betaOrder.getUserId() != null) {
            PersonalCard cardUserId = new PersonalCard();
            cardUserId.setUserId(betaOrder.getUserId());
            cardUserId = personalCardService.selectOne(new EntityWrapper<>(cardUserId));
            if (cardUserId != null) {
                orderVo.setName(cardUserId.getName());
                orderVo.setPhone(cardUserId.getPhone());
                BeanUtils.copyProperties(cardUserId, orderVo);
            }
        }
        orderVo.setSellUserName(cardSellUserId.getName());
        orderVo.setSellUserPhone(cardSellUserId.getPhone());
        orderVo.setBankName(cardSellUserId.getBankName());
        orderVo.setBankNumber(cardSellUserId.getBankNumber());
        orderVo.setAlipay(cardSellUserId.getAlipay());
        orderVo.setAlipayNumber(cardSellUserId.getAlipayNumber());
        orderVo.setWechat(cardSellUserId.getWechat());
        orderVo.setWechatNumber(cardSellUserId.getWechatNumber());
        orderVo.setCreateTime(betaOrder.getCreateTime());
        return orderVo;
    }

    /**
     * 导出订单
     *
     * @param betaMaleOrderDto
     * @return
     */
    @Override
    public List<BetaMaleOrderVo> exportBetaOrderList(BetaMaleOrderDto betaMaleOrderDto) {
        Map<String, Object> map = betaMaleOrderDto.getParams();
        String fieldName = "create_time";
        if (map.get("status") != null) {
            String status = (String) map.get("status");
            switch (status) {
                case "1":
                    fieldName = "create_time";
                    break;
                case "2":
                    fieldName = "pay_time";
                    break;
                case "3":
                    fieldName = "succeed_time";
                    break;
                case "4":
                    fieldName = "register_time";
                    break;
            }
        }
        List<BetaMaleOrderVo> list = baseMapper.exportBetaOrderList(betaMaleOrderDto, fieldName);
        return list;
    }

    /**
     * 审核是否放狗
     *
     * @param id
     */
    @Override
    @Transactional
    public synchronized Wrapper<String> auditRelease(Integer id, String remarks) {
        //验证订单
        BetaOrder betaOrder = baseMapper.selectById(id);
        if (betaOrder == null) {
            return WrapMapper.error("订单不存在！");
        }
        if (betaOrder.getOrderStauts() != BetaEnum.orderStauts.PASS.getCode()
                && betaOrder.getOrderStauts() != BetaEnum.orderStauts.COMPLAIN.getCode()) {
            return WrapMapper.error("订单状态有误");
        }
        Long buyUserId = 0l;
        Long sellUserId = betaOrder.getUserId();

        //修改订单数据
        betaOrder.setUpdateTime(new Date());
        betaOrder.setOrderStauts(BetaEnum.orderStauts.SUCCEED.getCode());
        betaOrder.setSettlingTime(DateTimeUtils.getDays(0));
        betaOrder.setLawsuitContent(betaOrder.getLawsuitContent());
        betaOrder.setSucceedTime(new Date());
        betaOrder.setGeneralizeTime(DateTimeUtils.getDays());
        baseMapper.updateBetaOrder(betaOrder);
        BetaMale maleVo = betaMaleService.selectById(betaOrder.getFeminaId());
        if (maleVo == null) {
            throw new RuntimeException("订单放狗异常！");
        }

        //查询公狗套餐
        ComboGroup comboVo = comboGroupService.selectById(maleVo.getComboId());
        if (comboVo == null) {
            return WrapMapper.error("获取套餐失败！");
        }
        maleVo.setSellStatus(BetaEnum.sellAtatus.SELL.getCode());
        maleVo.setUserId(betaOrder.getUserId());
        maleVo.setPresellTime(DateTimeUtils.getDays(comboVo.getDays()));
        betaMaleService.updateById(maleVo);


        BetaProcessedaOrder betaProcessedaOrder = new BetaProcessedaOrder();
        betaProcessedaOrder.setCreateTime(new Date());
        betaProcessedaOrder.setOrderId(betaOrder.getId());
        betaProcessedaOrder.setProcessedaOrderStauts(BetaEnum.processedaOrderStauts.YES.getCode());
        betaProcessedaOrder.setRemarks(remarks);
        betaProcessedaOrder.setOriginalNumbering(betaOrder.getOrderNumber());
        betaProcessedaOrderService.insert(betaProcessedaOrder);


        BigDecimal amount = betaOrder.getPrice();
        BigDecimal earningsPrice = betaOrder.getEarningsPrice();
        Long staticbuyUserId = buyUserId;
        Long staticTellUserId = sellUserId;
        if (betaOrder.getComboId() != GlobalConstant.Number.THREE_3) {
            //增加冻结账户可售额度
            updateBetaFreezeAccount(betaOrder.getUserId(), betaOrder.getPrice());
        }
        //给领养人减少锁仓余额，增加可出售
        addSalabilityPrice(betaOrder.getUserId(), betaOrder.getPrice());
//        taskExecutor.execute(() -> {
//            // 给买方增加持有资产
//            userStatisticService.updateParentUserStatisticAccount(staticbuyUserId, earningsPrice, BetaEnum.serialType.SERIAL_ADD.getCode());
//            // 给卖方减少持有资产
//            userStatisticService.updateParentUserStatisticAccount(staticTellUserId, amount, BetaEnum.serialType.SERIAL_REDUCE.getCode());
//        });
        return WrapMapper.ok("放狗成功！");
    }

    /**
     * @Description: 增加冻结账户可售额度
     * @Author: zjun
     * @Date: 2019/8/8 2:31
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateBetaFreezeAccount(Long userId, BigDecimal price) {
        //判断领养人是否老会员
        JSONObject cardJson = (JSONObject) redisTemplate.get(RedisKeyUtil.PERSONAL_CARD_USERID + userId);
        if (null == cardJson) {
            log.error("获取实名认证失败[{}]", userId);
            throw new RuntimeException("获取实名认证失败");
        }
        if (BetaEnum.personOldFlag.OLD.getCode().equals(cardJson.getInt("oldFlag"))) {
            //增加领养人可售额度
            //获取可出售额度比例
            BigDecimal saleable_quota = new BigDecimal(redisTemplate.get(RedisKeyUtil.PARAMETER_CONFIG_PREF + ParameterConfigEnum.SALEABLE_QUOTA.getKey()).toString());
            //获取领养人冻结账户
            EntityWrapper<BetaFreezeAccount> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("user_id", userId);
            BetaFreezeAccount betaFreezeAccount = betaFreezeAccountService.selectOne(entityWrapper);
            if (null == betaFreezeAccount) {
                betaFreezeAccount = new BetaFreezeAccount();
                betaFreezeAccount.setUserId(userId);
                betaFreezeAccount.setSellBanlence(BigDecimal.ZERO);
                betaFreezeAccount.setVersion(0);
                boolean flag = betaFreezeAccountService.insert(betaFreezeAccount);
                if (flag) {
                    log.info("新增冻结账户成功[{}]", betaFreezeAccount.toString());
                } else {
                    log.error("新增冻结账户失败[{}]", betaFreezeAccount.toString());
                    throw new RuntimeException("新增冻结账户失败");
                }
            }
            //需要增加的可售额度
            BigDecimal sellQuotaPrice = NumberUtil.mul(price, saleable_quota).setScale(2, BigDecimal.ROUND_DOWN);
            BigDecimal beforeAmount = betaFreezeAccount.getSellBanlence();//变更之前金额
            BigDecimal afterAmount = NumberUtil.add(betaFreezeAccount.getSellBanlence(), sellQuotaPrice);//变更之后金额
            betaFreezeAccount.setSellBanlence(NumberUtil.add(betaFreezeAccount.getSellBanlence(), sellQuotaPrice));
            boolean flag = betaFreezeAccountService.updateById(betaFreezeAccount);
            if (flag) {
                log.info("修改可出售额度成功[{}]", betaFreezeAccount.toString());
            } else {
                log.error("修改可出售额度失败[{}]", betaFreezeAccount.toString());
                throw new RuntimeException("修改可出售额度失败");
            }
            //增加流水
            BetaFreezeAccountRecord record = new BetaFreezeAccountRecord();
            record.setUserId(userId);
            record.setRecordNumber(SnowflakeIdGenerator.instance.nextId().toString());
            record.setFreezeAccountId(betaFreezeAccount.getId());
            record.setRecordType(BetaEnum.freezeRecordType.SELL.getCode());
            record.setBeforeAmount(beforeAmount);
            record.setAmount(sellQuotaPrice);
            record.setAfterAmount(afterAmount);
            record.setRemark("增加可售额度");
            flag = betaFreezeAccountRecordService.insert(record);
            if (flag) {
                log.info("增加流水成功[{}]", record);
            } else {
                log.error("增加流水失败[{}]", record);
                throw new RuntimeException("增加流水失败");
            }
        } else {
            log.info("该领养人为新用户[{}]", userId);
        }
    }

    /**
     * @param userId : 用户ID
     * @Description: 增加可出售额
     * @Author: zjun
     * @Date: 2019/8/16 16:50
     */
    @Transactional(rollbackFor = Exception.class)
    public void addSalabilityPrice(Long userId, BigDecimal pirce) {
        BetaLockedAccount betaLockedAccount = new BetaLockedAccount();
        betaLockedAccount.setUserId(userId);
        BetaLockedAccount betaLockedAccountData = betaLockedAccountService.selectOne(new EntityWrapper<>(betaLockedAccount));
        if (null != betaLockedAccountData) {
            BigDecimal balance = betaLockedAccountData.getBalance();
            log.info("[{}]数字资产为[{}]", userId, balance);
            //判断余额是否足够
            if (BigDecimal.ZERO.compareTo(balance) >= 0) {
                return;
            }
            BigDecimal salability_ratio = new BigDecimal(redisTemplate.get(RedisKeyUtil.PARAMETER_CONFIG_PREF + ParameterConfigEnum.SALABILITY_RATIO.getKey()).toString());
            //可出售额
            BigDecimal salability_price = pirce.multiply(salability_ratio).setScale(2, BigDecimal.ROUND_DOWN);

            if (balance.compareTo(salability_price) < 0) {
                salability_price = balance;
            }
            //获取可出售钱包
            BetaAccount betaAccount = new BetaAccount();
            betaAccount.setUserId(userId);
            betaAccount.setProductType(BetaEnum.accountType.SALABILITY.getCode());
            BetaAccount betaAccountData = betaAccountService.selectOne(new EntityWrapper<>(betaAccount));
            int num = betaAccountService.updateBetaAccountById(betaAccountData, BetaEnum.accountType.SALABILITY.getCode(),
                    salability_price, BetaEnum.serialType.SERIAL_ADD.getCode(), "增加可出售");
            if (num == 0) {
                log.error("变更资产失败[{}]", betaAccountData);
                throw new RuntimeException("变更资产失败");
            }
            betaLockedAccountData.setBalance(betaLockedAccountData.getBalance().subtract(salability_price));
            boolean flag = betaLockedAccountService.updateById(betaLockedAccountData);
            if (flag) {
                log.info("修改锁仓账户资产成功[{}]", betaLockedAccountData);
            } else {
                log.error("修改锁仓账户资产失败[{}]", betaLockedAccountData);
                throw new RuntimeException("修改锁仓账户资产失败");
            }
        } else {
            log.info("[{}]没有锁仓账户", userId);
        }
    }

    /**
     * @Description: 拒绝放狗
     * @Author: senghor
     * @Date: update2019/6/18 20:11
     * <p>
     * 新增加 两个字段    隐藏的订单id   和   新的订单id   做撤回狗的功能
     * C
     * update  2019-7-21
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized Wrapper auditRefuseDogo(Integer id, String remarks) {
        //验证原订单
        BetaOrder betaOrder = baseMapper.selectById(id);
        if (betaOrder == null) {
            return WrapMapper.error("订单不存在！");
        }
        // 取消和成功 就提醒订单有误
        if (betaOrder.getOrderStauts() != BetaEnum.orderStauts.COMPLAIN.getCode()) {
            return WrapMapper.error("订单已被审核，请勿重复审核");
        }

        //获取要修改的公狗数据
        BetaMale oldBetaMale = betaMaleService.selectById(betaOrder.getFeminaId());
        if (oldBetaMale == null) {
            throw new RuntimeException("订单放狗异常！");
        }

        // 获取到公狗对应的套餐
        ComboGroup comboGroup = comboGroupService.selectById(betaOrder.getComboId());
        if (comboGroup == null) {
            throw new RuntimeException("获取套餐异常！");
        }

        // 修改原订单数据为隐藏状态
        betaOrder.setUpdateTime(new Date());
        betaOrder.setOrderStauts(BetaEnum.orderStauts.HIDDEN.getCode());
        betaOrder.setOrderStautsType(BetaEnum.orderstautstype.KEFUCLOSE.getCode());
        int betaOrderSum = baseMapper.updateById(betaOrder);
        if (betaOrderSum == 0) {
            log.error("修改订单隐藏状态失败，betaOrder：" + betaOrder.toString());
            throw new RuntimeException("审核订单失败！请联系管理员！");
        }

        // 获取到天数
        BigDecimal days = new BigDecimal(comboGroup.getDays());
        // 计算一天的收益率
        BigDecimal dayPutRate = comboGroup.getEarningsRatio().divide(days, 10, BigDecimal.ROUND_DOWN);
        // 计算一天的收益金额
        BigDecimal dayPutPrice = betaOrder.getPrice().multiply(dayPutRate).setScale(2, BigDecimal.ROUND_DOWN);
        // 计算最新公狗价格(当前价格 + 一天的收益率)
        BigDecimal nowPrice = betaOrder.getPrice().add(dayPutPrice).setScale(2, BigDecimal.ROUND_DOWN);
        // 计算最新公狗收益金额(最新公狗价格 + (最新公狗价格 * 收益率))
        BigDecimal nowEarningsPrice = nowPrice.multiply(comboGroup.getEarningsRatio()).setScale(2, BigDecimal.ROUND_DOWN);
        nowEarningsPrice = nowEarningsPrice.add(nowPrice);

        // 比较最后是否升档需要修改套餐ID
        Integer comboId = betaOrder.getComboId();
        // 比较结算一天收益后价格是否会溢出，溢出则跳档
        if (nowPrice.compareTo(comboGroup.getMaxPrice()) == 1) {
            // 判断最新价格是否超过15000,超过15000则直接正常放狗
            if (nowPrice.compareTo(new BigDecimal(15000)) < 1) {
                log.info("查询出需要跳档的订单:" + betaOrder.toString());
                // 获取可升级的套餐返回
                EntityWrapper entity = new EntityWrapper();
                entity.le("min_price", nowPrice);
                entity.ge("max_price", nowPrice);
                List<ComboGroup> comboVos = comboGroupService.selectList(entity);
                // 比较出可以放狗的套餐
                int sum = 0;
                for (ComboGroup vo : comboVos) {
                    BetaMale countPam = new BetaMale();
                    countPam.setComboId(vo.getId());
                    countPam.setPresellTime(DateTimeUtils.getDays());
                    int count = betaMaleService.selectCount(new EntityWrapper<>(countPam));
                    if (sum <= count) {
                        comboId = vo.getId();
                        sum = count;
                    }
                }
                // 由于跳档需要更新公狗的套餐ID
                oldBetaMale.setComboId(comboId);
                ComboGroup comboVo = comboGroupService.selectById(comboId);
                log.info("最后选择跳档套餐是:" + comboVo.toString());
                // 计算最新收益价格 = （最新价格 + （最新价格 * 升档套餐收益率））
                nowEarningsPrice = nowPrice.multiply(comboVo.getEarningsRatio());//计算出转让单的预计收益。
                nowEarningsPrice = BigDecimalUtil.turnDown(nowEarningsPrice.add(nowPrice), 4);//加上本金
            }
        }
        //重新生成一条订单
        BetaOrder betaOrderPamr = new BetaOrder();
        betaOrderPamr.setCreateTime(new Date());
        betaOrderPamr.setOrderNumber(RandomUtil.randomOrderNum("BR"));
        betaOrderPamr.setPrice(nowPrice);
        betaOrderPamr.setEarningsPrice(nowEarningsPrice);
        betaOrderPamr.setOrderStauts(BetaEnum.orderStauts.TRANSFER.getCode());
        betaOrderPamr.setOrderStautsType(BetaEnum.sellAtatus.NOTSELL.getCode());
        betaOrderPamr.setFeminaId(betaOrder.getFeminaId());
        betaOrderPamr.setSellUserId(betaOrder.getSellUserId());
        betaOrderPamr.setComboId(comboId);
        betaOrderPamr.setDifferential(betaOrder.getDifferential());
        int betaOrderPamrSum = betaOrderMapper.insert(betaOrderPamr);
        if (betaOrderPamrSum == 0) {
            log.error("客诉订单" + id + ",生成最新订单失败");
            throw new RuntimeException("审核订单失败！请联系管理员！");
        }

        // 订单状态 修改成-未出售
        oldBetaMale.setSellStatus(BetaEnum.sellAtatus.NOTSELL.getCode());

        // 把这条狗的 退还给订单绑定出售人
        oldBetaMale.setOrderId(betaOrderPamr.getId());
        oldBetaMale.setUserId(betaOrder.getSellUserId());
        // 预售时间加一天
        oldBetaMale.setPresellTime(DateTimeUtils.getDays(1));
        // 公狗的最新价格
        oldBetaMale.setPrice(nowPrice);
        // 公狗的最新收益价
        oldBetaMale.setEarningsPrice(nowEarningsPrice);
        //拒绝放狗加上滞留天数加1
        oldBetaMale.setWaitSum(oldBetaMale.getWaitSum() + 1);
        Boolean oldBetaMaleFlag = betaMaleService.updateById(oldBetaMale);
        if (!oldBetaMaleFlag) {
            log.error("客诉订单" + id + ",修改公狗数据失败");
            throw new RuntimeException("审核订单失败！请联系管理员！");
        }

        BetaProcessedaOrder betaProcessedaOrder = new BetaProcessedaOrder();
        betaProcessedaOrder.setCreateTime(new Date());
        betaProcessedaOrder.setOrderId(betaOrder.getId());
        betaProcessedaOrder.setProcessedaOrderStauts(BetaEnum.processedaOrderStauts.NO.getCode());
        betaProcessedaOrder.setRemarks(remarks);

        // 增加隐藏的订单id
        betaProcessedaOrder.setHideOrderId(betaOrder.getId());
        // 增加新的订单id
        betaProcessedaOrder.setNewOrderId(betaOrderPamr.getId());
        // 新订单编号
        betaProcessedaOrder.setNewNumbering(betaOrderPamr.getOrderNumber());
        // 原订单编号
        betaProcessedaOrder.setOriginalNumbering(betaOrder.getOrderNumber());

        Boolean betaProcessedaOrderFlag = betaProcessedaOrderService.insert(betaProcessedaOrder);
        if (!betaProcessedaOrderFlag) {
            log.error("新增已处理订单数据失败，betaProcessedaOrder：" + betaProcessedaOrder.toString());
            throw new RuntimeException("审核订单失败！请联系管理员！");
        }

        // 加一天的 累计收益
        betaAccountService.cumulativeProfit(betaOrder.getSellUserId(), dayPutPrice);
        return WrapMapper.ok("拒绝放狗成功！");
    }

    @Override
    public Page<BetaOrderVo> selectCountUserBeta(Page<BetaOrderVo> page, BetaOrderDto betaOrderDto) {
        page.setRecords(betaOrderMapper.selectCountUserBeta(page, betaOrderDto));
        return page;
    }

    /**
     * @Description: 查询所有有效订单
     * @Author: senghor
     * @Date: 2019/5/25 0025 18:16
     */
    @Override
    public List<BetaOrder> selectAllUserId() {
        return baseMapper.selectAllUserId();
    }

    /**
     * 统计消耗的微分
     *
     * @return
     */
    @Override
    public BigDecimal getSumDifferential() {
        Date te = DateTimeUtils.getDays();
        BigDecimal mal = baseMapper.getSumDifferential(te);
        return mal;
    }

    /**
     * @Description: 一键客诉
     * @Author: zjun
     * @Date: 2019/8/6 16:10
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized Wrapper<String> customerComplaint() {
        String today = DateUtils.today() + " 00:00:00";
        int num = baseMapper.customerComplaint(today);
        log.info("成功处理{}条 ", num);
        return WrapMapper.ok("成功处理" + num + "条");
    }

    /**
     * @Description: 一键拒绝放狗
     * @Author: zjun
     * @Date: 2019/8/6 16:10
     */
    @Override
    public void auditRefuseDogoAll() {
        List<BetaMaleOrderVo> betaMaleOrderVos = baseMapper.betaOrderListByStatus(BetaEnum.orderStauts.COMPLAIN.getCode());
        int sumNum = betaMaleOrderVos.size();
        log.info("一共需要处理拒绝放狗的数量为:" + sumNum);
        List<List<BetaMaleOrderVo>> lists = ListUtil.averageAssign(betaMaleOrderVos, 10);
        for (int i = 0; i < lists.size(); i++) {
            Integer taskSum = i + 1;
            List<BetaMaleOrderVo> betaMaleOrderVoList = lists.get(i);
            taskExecutor.execute(() -> {
                Integer taskMaleSum = 0;
                for (BetaMaleOrderVo betaMaleOrderVo : betaMaleOrderVoList) {
                    taskMaleSum++;
                    try {
                        Wrapper<String> wrapper = auditRefuseDogo(betaMaleOrderVo.getId(), "一键处理");
                        if (wrapper == null || wrapper.getCode() != 200) {
                            log.error("拒绝放狗失败betaMaleOrderVo=" + betaMaleOrderVo.toString());
                        } else {
                            log.info("第" + taskSum + "个线程,需要处理共" + betaMaleOrderVoList.size() + "条,成功处理完拒绝放狗序号：" + taskMaleSum);
                        }
                    } catch (Exception e) {
                        log.error("拒绝放狗失败betaMaleOrderVo=" + betaMaleOrderVo.toString(), e);
                    }
                }
            });
        }
    }

    /**
     * @param id   : 订单ID
     * @param type : 1=通过，2=拒绝
     * @Description: 打包出售审核
     * @Author: zjun
     * @Date: 2019/8/13 21:10
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized Wrapper<String> packAudit(Integer id, Integer type) {
        //获取订单
        BetaOrder betaOrderData = baseMapper.selectById(id);
        if (betaOrderData == null) {
            return WrapMapper.error("订单不存在！");
        }
        // 是否审核状态
        if (!BetaEnum.orderStauts.AUDIT.getCode().equals(betaOrderData.getOrderStauts())) {
            return WrapMapper.error("订单已被审核，请勿重复操作");
        }
        //通过处理
        if (BetaEnum.auditType.PASS.getCode().equals(type)) {
            //修改公狗状态为未出售，并修改预售时间
            BetaMale betaMale = new BetaMale();
            betaMale.setId(betaOrderData.getFeminaId());
            betaMale.setPresellTime(DateTimeUtils.getDays(1));
            betaMale.setSellStatus(BetaEnum.sellAtatus.NOTSELL.getCode());
            betaMale.setUpdateTime(new Date());
            boolean flag = betaMaleService.updateById(betaMale);
            if (flag) {
                log.info("打包出售审核修改公狗状态成功[{}]", betaMale);
            } else {
                log.error("打包出售审核修改公狗状态失败[{}]", betaMale);
                throw new RuntimeException("打包出售公狗修改失败,请联系管理员！");
            }
            //修改订单状态待转让
            BetaOrder betaOrder = new BetaOrder();
            betaOrder.setId(id);
            betaOrder.setOrderStauts(BetaEnum.orderStauts.TRANSFER.getCode());
            betaOrder.setUpdateTime(new Date());
            flag = this.updateById(betaOrder);
            if (flag) {
                log.info("打包出售审核修改订单状态成功[{}]", betaOrder);
            } else {
                log.error("打包出售审核修改订单状态失败[{}]", betaOrder);
                throw new RuntimeException("打包出售订单修改失败,请联系管理员！");
            }
        } else if (BetaEnum.auditType.REFUSE.getCode().equals(type)) {
            //修改订单状态审核失败
            BetaOrder betaOrder = new BetaOrder();
            betaOrder.setId(id);
            betaOrder.setOrderStauts(BetaEnum.orderStauts.AUDIT_FAILURE.getCode());
            betaOrder.setUpdateTime(new Date());
            boolean flag = this.updateById(betaOrder);
            if (flag) {
                log.info("打包出售审核修改订单状态成功[{}]", betaOrder);
            } else {
                log.error("打包出售审核修改订单状态失败[{}]", betaOrder);
                throw new RuntimeException("打包出售订单修改失败,请联系管理员！");
            }
            //获取公狗数据
            BetaMale betaMaleData = betaMaleService.selectById(betaOrderData.getFeminaId());
            betaMaleData.setSellStatus(BetaEnum.sellAtatus.AUDIT_FAILURE.getCode());
            betaMaleData.setUpdateTime(new Date());
            flag = betaMaleService.updateById(betaMaleData);
            if (flag) {
                log.info("打包出售审核修改公狗状态成功[{}]", betaMaleData);
            } else {
                log.error("打包出售审核修改公狗状态失败[{}]", betaMaleData);
                throw new RuntimeException("打包出售公狗修改失败,请联系管理员！");
            }
            Integer productType = null;
            if (BetaEnum.platformSndUser.userPushProfit.getCode().equals(betaMaleData.getPlatformAndUser())) {
                productType = BetaEnum.accountType.GENER.getCode();
            } else if (BetaEnum.platformSndUser.sourceUserTeam.getCode().equals(betaMaleData.getPlatformAndUser())) {
                productType = BetaEnum.accountType.TEAMPROFIT.getCode();
            } else {
                throw new RuntimeException("找不到账户");
            }
            //获取资产账户
            BetaAccount betaAccount = new BetaAccount();
            betaAccount.setUserId(betaOrderData.getSellUserId());
            betaAccount.setProductType(productType);
            betaAccount = betaAccountService.selectOne(new EntityWrapper<>(betaAccount));
            if (null == betaAccount) {
                log.error("账户不存在user_id={}，product_type={}", betaOrderData.getSellUserId(), betaMaleData.getPlatformAndUser());
                throw new RuntimeException("打包出售获取账户失败,请联系管理员！");
            }
            //返还额度
            int num = betaAccountService.updateBetaAccountByPack(betaAccount, betaOrderData.getPrice(), "出售收益审核拒绝，退回账户");
            if (0 == num) {
                log.error("返还额度失败{}", betaAccount);
                throw new RuntimeException("返还额度失败,请联系管理员！");
            }
        } else {
            return WrapMapper.error("操作失败");
        }
        return WrapMapper.ok("操作成功");
    }

    /**
     * @param page             :
     * @param betaMaleOrderDto :
     * @Description: 获取打包审核列表
     * @Author: zjun
     * @Date: 2019/8/14 10:17
     */
    @Override
    public Page<BetaMaleOrderVo> betaOrderAuditListPage(Page<BetaMaleOrderVo> page, BetaMaleOrderDto betaMaleOrderDto) {
        page.setRecords(baseMapper.betaOrderAuditListPage(page, betaMaleOrderDto));
        return page;
    }
}
