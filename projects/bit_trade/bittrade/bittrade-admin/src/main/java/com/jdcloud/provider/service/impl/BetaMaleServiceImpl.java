package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.ContractEnum;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.BetaMaleAddDto;
import com.jdcloud.provider.dto.BetaMaleDto;
import com.jdcloud.provider.mapper.BetaMaleImportRecordMapper;
import com.jdcloud.provider.mapper.BetaMaleMapper;
import com.jdcloud.provider.mapper.BetaOrderMapper;
import com.jdcloud.provider.mapper.ComboGroupMapper;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.pojo.vo.BetaMaleOrderVo;
import com.jdcloud.provider.pojo.vo.BetaMaleVo;
import com.jdcloud.provider.service.BetaMaleService;
import com.jdcloud.provider.service.BetaOrderService;
import com.jdcloud.provider.service.PersonalCardService;
import com.jdcloud.provider.service.UserAccountService;
import com.jdcloud.provider.utils.ImportExcelUtil;
import com.jdcloud.util.Convert;
import com.jdcloud.util.ListUtil;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * <p>
 * 贝塔狗---公狗表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-10
 */
@Service
@Slf4j
public class BetaMaleServiceImpl extends ServiceImpl<BetaMaleMapper, BetaMale> implements BetaMaleService {

    @Resource
    private BetaMaleMapper betaMaleMapper;
    @Resource
    private ComboGroupMapper comboGroupMapper;
    @Autowired
    private PersonalCardService personalCardService;
    @Resource
    private BetaOrderMapper betaOrderMapper;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BetaOrderService betaOrderService;
    @Resource
    private BetaMaleImportRecordMapper betaMaleImportRecordMapper;

    /**
     * 查询列表
     *
     * @param page
     * @param betaMaleDto
     * @return
     */
    @Override
    public Page<BetaMaleVo> selectBetaMaleListPage(Page<BetaMaleVo> page, BetaMaleDto betaMaleDto) {
        page.setRecords(betaMaleMapper.selectBetaMaleListPage(page, betaMaleDto));
        return page;
    }

    /**
     * 增加(放狗)
     */
    @Override
    @Transactional
    public Wrapper<String> saveOrUpdate(BetaMaleAddDto betaMaleAddDto) {
        if (DateTimeUtils.daysBetween(betaMaleAddDto.getPresellTime(), DateTimeUtils.getDays()) > 0) {
            return WrapMapper.error("预售时间只能为今天以后的日期");
        }

        PersonalCard cardPam = new PersonalCard();
        cardPam.setPhone(betaMaleAddDto.getPhone());
        PersonalCard card = personalCardService.selectOne(new EntityWrapper<>(cardPam));
        if (card == null) {
            return WrapMapper.error("请先实名认证");
        }
        if (card.getBankNumber() == null &&
                card.getAlipay() == null &&
                card.getAlipayNumber() == null &&
                card.getWechat() == null &&
                card.getWechatNumber() == null) {
            return WrapMapper.error("至少需要创建一种收款方式！");
        }

        //获取到套餐的id 去查询然后计算出收益价格
        ComboGroup group = comboGroupMapper.selectById(betaMaleAddDto.getComboId());
        if (group == null) {
            return WrapMapper.error("获取套餐失败！");
        }
        if (betaMaleAddDto.getPrice() == null) {
            return WrapMapper.error("公狗价格不能为空！");
        }
        if (betaMaleAddDto.getPrice().compareTo(group.getMinPrice()) < GlobalConstant.Number.ZERO_0) {
            return WrapMapper.error("低于套餐的最低价格");
        }
        if (betaMaleAddDto.getPrice().compareTo(group.getMaxPrice()) > GlobalConstant.Number.ZERO_0) {
            return WrapMapper.error("大于套餐的最高价格");
        }
        // 判断不在套餐领养时间内 才能放狗（判断不在周末时间 才能上班）
        if (DateTimeUtils.getMinuteSecondInt() >= group.getStartTime()
                && DateTimeUtils.getMinuteSecondInt() <= group.getEndTime()) {
            return WrapMapper.error("套餐已经开始领养,无法放狗");
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(card.getUserId());
        userAccount = userAccountService.selectOne(new EntityWrapper<>(userAccount));
        if (userAccount == null) {
            return WrapMapper.error("用户不存在");
        }
        if (userAccount.getInternalAccount() != 2) {
            return WrapMapper.error("该用户不是内部账户，不能放狗");
        }

        //生成公狗
        BetaMale betaMale = new BetaMale();
        betaMale.setComboId(betaMaleAddDto.getComboId());
        betaMale.setCreateTime(new Date());
        betaMale.setPremiumStauts(BetaEnum.premiumStauts.YESPREMIUMSTAUTS.getCode());
        betaMale.setBetaActivate(BetaEnum.betaActivate.ACTIVATE.getCode());
        betaMale.setPresellTime(betaMaleAddDto.getPresellTime());
        betaMale.setPrice(betaMaleAddDto.getPrice());

        //获取到当前的收益后的价格
        BigDecimal profit = betaMaleAddDto.getPrice().multiply(group.getEarningsRatio());
        //最终收益
        BigDecimal earningsPriceFinal = profit.add(betaMaleAddDto.getPrice());
        betaMale.setEarningsPrice(earningsPriceFinal);
        betaMale.setUserId(card.getUserId());
        betaMale.setPlatformAndUser(BetaEnum.platformSndUser.sourcePlatform.getCode());
        betaMaleMapper.insert(betaMale);
        // 创建订单信息
        BetaOrder betaOrder = new BetaOrder();
        betaOrder.setCreateTime(new Date());
        Random ra = new Random();
        int sleep = ra.nextInt(ContractEnum.ENTRUST_NUMBER_MAX) + ContractEnum.ENTRUST_NUMBER_MIN;
        betaOrder.setOrderNumber("BR" + DateTimeUtils.getCurrentFullDateTime() + sleep);
        betaOrder.setPrice(betaMaleAddDto.getPrice());
        betaOrder.setEarningsPrice(earningsPriceFinal);
        betaOrder.setOrderStauts(BetaEnum.orderStauts.TRANSFER.getCode());
        betaOrder.setOrderStautsType(BetaEnum.sellAtatus.NOTSELL.getCode());
        /*  betaOrder.setSucceedTime(new Date());*/
        betaOrder.setFeminaId(betaMale.getId());
        betaOrder.setSellUserId(card.getUserId());
        betaOrder.setComboId(group.getId());
        betaOrder.setDifferential(group.getDifferential());
        betaOrderMapper.insert(betaOrder);
        //修改最新的订单号
        betaMale.setId(betaMale.getId());
        betaMale.setOrderId(betaOrder.getId());
        betaMaleMapper.updateById(betaMale);

//        taskExecutor.execute(() -> {
//            // 给买方增加持有资产
//            userStatisticService.updateParentUserStatisticAccount(card.getUserId(), betaOrder.getPrice(), BetaEnum.serialType.SERIAL_ADD.getCode());
//        });
        BetaMaleVo betaMaleVo = betaMaleMapper.selectByTodayRemainOne(DateTimeUtils.getDays(), betaMale.getComboId());
        redisTemplate.set(RedisKeyUtil.MALE_REMAIN + betaMaleVo.getComboId(), betaMaleVo.getMaleRemainSum(), GlobalConstant.Number.DAY_TIME_LONG);
        return WrapMapper.ok("创建公狗成功");
    }


    @Override
    public BetaMaleVo selectBetaMale(Integer id) {
        return betaMaleMapper.selectBetaMale(id);
    }


    /**
     * 激活接口
     */
    @Override
    @Transactional
    public Wrapper<String> updateEditRecovery(BetaMaleAddDto betaMaleAddDto) {
        // 1.验证用户 是否认证，
        PersonalCard cardPam = new PersonalCard();
        cardPam.setUserId(betaMaleAddDto.getUserId());
        PersonalCard card = personalCardService.selectOne(new EntityWrapper<>(cardPam));
        if (card == null) {
            return WrapMapper.error("请先实名认证");
        }
        // 2.验证用户是否绑定 一种收款方式
        if (card.getBankNumber() == null &&
                card.getAlipay() == null &&
                card.getAlipayNumber() == null &&
                card.getWechat() == null &&
                card.getWechatNumber() == null) {
            return WrapMapper.error("至少需要创建一种收款方式！");
        }

        BetaMale betaMale = new BetaMale();
        //3.判断套餐套餐选择有误
        ComboGroup group = comboGroupMapper.selectById(betaMaleAddDto.getComboId());
        if (group == null) {
            return WrapMapper.error("获取套餐失败！");
        }
        if (betaMaleAddDto.getPrice() == null) {
            return WrapMapper.error("公狗价格不能为空！");
        }
        if (betaMaleAddDto.getPrice().compareTo(group.getMinPrice()) < GlobalConstant.Number.ZERO_0) {
            return WrapMapper.error("低于套餐的最低价格");
        }
        if (betaMaleAddDto.getPrice().compareTo(group.getMaxPrice()) > GlobalConstant.Number.ZERO_0) {
            return WrapMapper.error("大于套餐的最高价格");
        }

        //获取到当前的收益后的价格
        BigDecimal Profit = betaMaleAddDto.getPrice().multiply(group.getEarningsRatio());
        //最终收益
        BigDecimal earningsPriceFinal = Profit.add(betaMaleAddDto.getPrice());
        //  是否激活（0=未激活，1=已激活）0 未激活的状态
        if (betaMaleAddDto.getBetaActivate() == BetaEnum.betaActivate.NOTACTIVATE.getCode()) {
            betaMale.setId(betaMaleAddDto.getId());
            betaMale.setBetaActivate(BetaEnum.betaActivate.NOTACTIVATE.getCode());
            betaMale.setComboId(betaMaleAddDto.getComboId());
            betaMale.setUpdateTime(new Date());
            betaMale.setPremiumStauts(1);
            betaMale.setEarningsPrice(earningsPriceFinal);
            betaMale.setPresellTime(betaMaleAddDto.getPresellTime());
            betaMale.setPrice(betaMaleAddDto.getPrice());
            betaMale.setPlatformAndUser(1);
            betaMale.setUserId(card.getUserId());
        } else {
            BetaMale malePar = baseMapper.selectById(betaMaleAddDto.getId());
            if (malePar == null) {
                return WrapMapper.error("公狗不存在！");
            }
            BetaOrder betaOrder = new BetaOrder();
            // 已激活 订单id   就直接生成订单
            if (betaMaleAddDto.getOrderId() == null) {
                betaOrder.setCreateTime(new Date());
                betaOrder.setOrderNumber("E" + System.currentTimeMillis());
                betaOrder.setPrice(betaMaleAddDto.getPrice());
                betaOrder.setEarningsPrice(earningsPriceFinal);
                betaOrder.setOrderStauts(BetaEnum.orderStauts.TRANSFER.getCode());
                betaOrder.setOrderStautsType(0);

                betaOrder.setFeminaId(malePar.getId());
                betaOrder.setSellUserId(betaMaleAddDto.getUserId());
                betaOrder.setComboId(group.getId());
                betaOrder.setDifferential(group.getDifferential());

                betaOrderMapper.insert(betaOrder);
                // 获取到订单Id
                betaMale.setId(malePar.getId());
                betaMale.setBetaActivate(BetaEnum.betaActivate.ACTIVATE.getCode());
                betaMale.setPrice(betaMaleAddDto.getPrice());
                betaMale.setEarningsPrice(earningsPriceFinal);
                betaMale.setOrderId(betaOrder.getId());
                betaMale.setPlatformAndUser(1);


                //修改最新的订单号
                betaMale.setOrderId(betaOrder.getId());
                betaMaleMapper.updateById(betaMale);
            } else {
                // 判断 orederId 不为空
                betaOrder.setId(betaMaleAddDto.getOrderId());
                betaOrder.setUpdateTime(new Date());
                betaOrder.setPrice(betaMaleAddDto.getPrice());
                betaOrder.setEarningsPrice(earningsPriceFinal);
                betaOrder.setOrderStauts(BetaEnum.orderStauts.TRANSFER.getCode());
                betaOrder.setOrderStautsType(0);

                betaOrder.setSellUserId(card.getUserId());
                betaOrder.setFeminaId(malePar.getId());
                betaOrder.setComboId(group.getId());
                betaOrder.setDifferential(group.getDifferential());
                betaOrderMapper.updateById(betaOrder);

                betaMale.setId(betaMaleAddDto.getId());
                betaMale.setBetaActivate(betaMaleAddDto.getBetaActivate());
                betaMale.setComboId(betaMaleAddDto.getComboId());
                betaMale.setUpdateTime(new Date());
                betaMale.setPremiumStauts(betaMaleAddDto.getPremiumStauts());
                betaMale.setEarningsPrice(earningsPriceFinal);
                betaMale.setPresellTime(betaMaleAddDto.getPresellTime());
                betaMale.setPrice(betaMaleAddDto.getPrice());
                betaMale.setUserId(card.getUserId());
                betaMale.setPlatformAndUser(1);

                //修改最新的订单号
                betaMale.setOrderId(betaOrder.getId());
            }
        }
        betaMaleMapper.updateEditRecovery(betaMale);
        return WrapMapper.ok();
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
        // 查询出转让方的用户信息
        PersonalCard cardPam = new PersonalCard();
        cardPam.setUserId(betaOrder.getSellUserId());
        PersonalCard cardSellUserId = personalCardService.selectOne(new EntityWrapper<>(cardPam));
        if (cardSellUserId != null) {
            /*orderVo.setSellUserName(cardSellUserId.getName());
            BeanUtils.copyProperties(cardSellUserId, orderVo);*/
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
        // 冲突的字段set进去
        orderVo.setSellUserName(cardSellUserId.getName());
        orderVo.setSellUserPhone(cardSellUserId.getPhone());
        orderVo.setBankName(cardSellUserId.getBankName());
        orderVo.setBankNumber(cardSellUserId.getBankNumber());
        orderVo.setAlipay(cardSellUserId.getAlipay());
        orderVo.setAlipayNumber(cardSellUserId.getAlipayNumber());
        orderVo.setWechat(cardSellUserId.getWechat());
        orderVo.setWechatNumber(cardSellUserId.getWechatNumber());
        return orderVo;
    }


    /**
     * 删除公狗
     * 关联删除 对应的订单
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public int deleteByIds(String ids) {
        Integer i = Integer.parseInt(ids);
        BetaMale betaMale = baseMapper.selectById(i);
        Integer row = 0;
        if (betaMale != null) {
            //查询订单数量
            BetaOrder betaOrder = new BetaOrder();
            betaOrder.setOrderStauts(BetaEnum.orderStauts.COMPLAIN.getCode());
            betaOrder.setFeminaId(betaMale.getId());
            List<BetaOrder> list = betaOrderMapper.selectList(new EntityWrapper<>(betaOrder));
            if (list.size() == 1) {
                // 判断内部账号
                UserAccount userAccount = new UserAccount();
                userAccount.setUserId(list.get(0).getSellUserId());
                userAccount.setInternalAccount(2);
                userAccount = userAccountService.selectOne(new EntityWrapper<>(userAccount));
                if (userAccount == null) {
                    return 0;
                }
                if (list.get(0).getUserId() == null) {
                    // 删除订单
                    log.info("删除成功！" + list.get(0).getUserId());
                    betaOrderMapper.deleteById(betaMale.getOrderId());
                    Integer[] Ids = ConvertUtil.toIntArray(ids);
                    row = baseMapper.deleteByIds(Ids);

                }
                if (list.get(0).getUserId() != null) {
                    log.info("删除成功！" + list.get(0).getUserId());
                    UserAccount userAccountPam = new UserAccount();
                    userAccountPam.setUserId(list.get(0).getUserId());
                    userAccountPam.setInternalAccount(2);
                    userAccountPam = userAccountService.selectOne(new EntityWrapper<>(userAccountPam));
                    if (userAccountPam == null) {
                        return 0;
                    } else {
                        // 删除订单
                        log.info("删除成功！");
                        betaOrderMapper.deleteById(betaMale.getOrderId());
                        Integer[] Ids = ConvertUtil.toIntArray(ids);
                        row = baseMapper.deleteByIds(Ids);
                    }
                }
            } else {
                // 第二种情况 直接删除
                BetaOrder betaOrderPam = new BetaOrder();
                //查询公狗的订单
                betaOrderPam.setOrderStauts(BetaEnum.orderStauts.TRANSFER.getCode());
                betaOrderPam.setFeminaId(betaMale.getId());

                List<BetaOrder> listPam = betaOrderMapper.selectList(new EntityWrapper<>(betaOrderPam));
                if (listPam.size() == 1) {
                    UserAccount userAccountPam = new UserAccount();
                    userAccountPam.setUserId(listPam.get(0).getSellUserId());
                    userAccountPam.setInternalAccount(2);
                    userAccountPam = userAccountService.selectOne(new EntityWrapper<>(userAccountPam));
                    if (userAccountPam == null) {
                        return 0;
                    }
                    // 删除订单
                    betaOrderMapper.deleteById(betaMale.getOrderId());
                    Integer[] Ids = ConvertUtil.toIntArray(ids);
                    row = baseMapper.deleteByIds(Ids);
                } else if (betaMale.getBetaActivate() == BetaEnum.betaActivate.NOTACTIVATE.getCode()) {
                    // 考虑这种情况 --- 未激活的状态
                    Integer[] Ids = ConvertUtil.toIntArray(ids);
                    row = baseMapper.deleteByIds(Ids);
                }
            }
            BetaMaleVo betaMaleVo = betaMaleMapper.selectByTodayRemainOne(DateTimeUtils.getDays(), betaMale.getComboId());
            redisTemplate.set(RedisKeyUtil.MALE_REMAIN + betaMaleVo.getComboId(), betaMaleVo.getMaleRemainSum(), GlobalConstant.Number.DAY_TIME_LONG);
        }
        return row;
    }

    /**
     * 删除公狗
     * 订单
     * 2019-7-1
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<String> deleteNew(Integer id) {
        BetaMale betaMale = baseMapper.selectById(id);
        if (null != betaMale) {
            // 查询客诉的订单
            BetaOrder betaOrder = new BetaOrder();
            betaOrder.setOrderStauts(BetaEnum.orderStauts.COMPLAIN.getCode());
            betaOrder.setFeminaId(betaMale.getId());
            betaOrder = betaOrderMapper.selectOne(betaOrder);
            if (null != betaOrder) {
                // 出售人 = 内部账号
                Integer count = userAccountService.eqinternalAccount(betaOrder.getSellUserId());
                if (count.equals(0)) {
                    return WrapMapper.error("出售人不是内部账号,无法删除!");
                }
                // 领养人为空的客诉单可直接删除
                if (null == betaOrder.getUserId()) {
                    int row = betaOrderMapper.deleteById(betaMale.getOrderId());
                    int column = baseMapper.deleteById(betaMale.getId());
                    if (row != 0 && column != 0) {
                        BetaMaleVo betaMaleVo = betaMaleMapper.selectByTodayRemainOne(DateTimeUtils.getDays(), betaMale.getComboId());
                        redisTemplate.set(RedisKeyUtil.MALE_REMAIN + betaMaleVo.getComboId(), betaMaleVo.getMaleRemainSum(), GlobalConstant.Number.DAY_TIME_LONG);
                        return WrapMapper.ok("删除成功!");
                    }
                }   // 领养人不为空，且是内部的账号的可以直接删除
                else {
                    Integer acCount = userAccountService.eqinternalAccount(betaOrder.getSellUserId());
                    if (acCount.equals(0)) {
                        return WrapMapper.error("领养人不是内部账号,无法删除!");
                    } else {
                        int row = betaOrderMapper.deleteById(betaMale.getOrderId());
                        int column = baseMapper.deleteById(betaMale.getId());
                        if (row != 0 && column != 0) {
                            BetaMaleVo betaMaleVo = betaMaleMapper.selectByTodayRemainOne(DateTimeUtils.getDays(), betaMale.getComboId());
                            redisTemplate.set(RedisKeyUtil.MALE_REMAIN + betaMaleVo.getComboId(), betaMaleVo.getMaleRemainSum(), GlobalConstant.Number.DAY_TIME_LONG);
                            return WrapMapper.ok("删除成功!");
                        }
                    }
                }
            } else {
                //  查询待转让的订单
                BetaOrder order = new BetaOrder();
                order.setOrderStauts(BetaEnum.orderStauts.TRANSFER.getCode());
                order.setFeminaId(betaMale.getId());
                order = betaOrderMapper.selectOne(order);
                if (null != order) {
                    // 出售人 = 内部账号
                    Integer count = userAccountService.eqinternalAccount(betaOrder.getSellUserId());
                    if (count.equals(0)) {
                        return WrapMapper.error("出售人不是内部账号,无法删除!");
                    }
                    int row = betaOrderMapper.deleteById(betaMale.getOrderId());
                    int column = baseMapper.deleteById(betaMale.getId());
                    if (row != 0 && column != 0) {
                        BetaMaleVo betaMaleVo = betaMaleMapper.selectByTodayRemainOne(DateTimeUtils.getDays(), betaMale.getComboId());
                        redisTemplate.set(RedisKeyUtil.MALE_REMAIN + betaMaleVo.getComboId(), betaMaleVo.getMaleRemainSum(), GlobalConstant.Number.DAY_TIME_LONG);
                        return WrapMapper.ok("删除成功!");
                    }
                }
            }
        }
        return WrapMapper.error("无法删除,请联系管理员!");
    }

    /**
     * @param fileWaitsum
     * @Description: 设置公狗优先级
     * @Author: senghor
     * @Date: 2019/8/17 23:34
     */
    @Override
    public Wrapper<String> importExcelWaitSum(MultipartFile fileWaitsum) {
        if (fileWaitsum.isEmpty()) {
            return WrapMapper.ok("导入失败,文件不存在!");
        }
        InputStream in = null;
        List<List<Object>> listob = null;
        try {
            in = fileWaitsum.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            listob = new ImportExcelUtil().getBankListByExcel(in, fileWaitsum.getOriginalFilename());
        } catch (Exception e) {
            return WrapMapper.ok("请上传execl文件");
        }

        List<List<Object>> nowListob = listob;
        List<String> errorlist = new ArrayList<String>();
        List<String> succeedlist = new ArrayList<String>();
        if (listob != null && listob.size() > 0) {
            int row = 1;
//            List<List<List<Object>>> threadLists = ListUtil.averageAssign(nowListob, 20);
            for (List<Object> excelList : nowListob) {
                row++;
                if ("无".equals(excelList.get(0))) {
                    errorlist.add("第" + row + "行订单编号不能为空");
                    continue;
                }
                if ("无".equals(excelList.get(1))) {
                    errorlist.add("第" + row + "行优先级不能为空");
                    continue;
                }
                try {
                    String orderNumber = excelList.get(0).toString();// 订单编号
                    String priorityStr = excelList.get(1).toString();
                    Integer priority = 0;
                    if (priorityStr.indexOf(".") >= 0){
                        priority = Integer.valueOf(priorityStr.substring(0,priorityStr.indexOf(".")));// 优先级
                    }else{
                        priority = Integer.valueOf(priorityStr);// 优先级
                    }
                    Integer waitSum = (10 - priority) * 100;
                    EntityWrapper entityWrapper = new EntityWrapper();
                    entityWrapper.eq("order_number", orderNumber);
                    entityWrapper.setSqlSelect("id");
                    BetaOrder betaOrder = betaOrderService.selectOne(entityWrapper);
                    if (betaOrder == null) {
                        errorlist.add("第" + row + "行导入失败，订单不存在");
                        continue;
                    }
                    Integer flag = baseMapper.updateByOrderNumber(orderNumber, waitSum);
                    if (flag > 0) {
                        succeedlist.add("第" + row + "行导入成功");
                        log.info("第" + row + "行导入成功");
                    } else {
                        errorlist.add("第" + row + "行导入失败");
                        log.info("第" + row + "行导入失败");
                    }

                } catch (Exception e) {
                    log.error("第" + row + "行未知错误" + e);
                    errorlist.add("第" + row + "行导入失败,未知错误" + e);
                }
            }
        }
        return WrapMapper.ok("导入成功</br>异常信息:" + errorlist.toString());
    }

    /**
     * @Description: 清空之前所有数据的优先级
     * @Author: senghor
     * @Date: 2019/8/18 0:28
     */
    @Override
    public void clearWaitSum() {
        baseMapper.clearWaitSum();
    }


    /**
     * @Description: 导入公狗模板
     * @Author: senghor
     * @Date: 2019/5/10 0010 14:40
     */
    @Override
    public Wrapper<String> importExcel(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        if (file.isEmpty()) {
            return WrapMapper.ok("导入失败,文件不存在!");
        }
        InputStream in = null;
        List<List<Object>> listob = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
        } catch (Exception e) {
            return WrapMapper.ok("请上传execl文件");
        }

        List<List<Object>> nowListob = listob;
        List<String> errorlist = new ArrayList<String>();
        List<String> succeedlist = new ArrayList<String>();
        if (listob != null && listob.size() > 0) {
            Map<String, Object> redismap = new HashMap<>();
            int countLine = 0;
            int row = 1;
            String fristVenueName = "";

            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);

            countLine = nowListob.size();
            for (List<Object> excelList : nowListob) {
                row++;
                //默认插入失败记录
                BetaMaleImportRecord betaMaleImportRecord = new BetaMaleImportRecord();
                betaMaleImportRecord.setComboId(excelList.get(0).toString());
                betaMaleImportRecord.setPrice(excelList.get(1).toString());
                betaMaleImportRecord.setPhone(excelList.get(2).toString());
                betaMaleImportRecord.setPresellTime(excelList.get(3).toString());
                betaMaleImportRecord.setCreateTime(new Date());
                betaMaleImportRecord.setImportStauts(BetaEnum.importStauts.defeated.getCode());
                betaMaleImportRecordMapper.insert(betaMaleImportRecord);
                int id = betaMaleImportRecord.getId();
                if ("无".equals(excelList.get(0))) {
                    errorlist.add("第" + row + "行套餐ID不能为空");
                    continue;
                }
                if ("无".equals(excelList.get(1))) {
                    errorlist.add("第" + row + "行价格不能为空");
                    continue;
                }
                if ("无".equals(excelList.get(2))) {
                    errorlist.add("第" + row + "行用户手机不能为空");
                    continue;
                }
                if ("无".equals(excelList.get(3))) {
                    errorlist.add("第" + row + "行预售时间不能为空");
                    continue;
                }
                try {
                    Integer comboId = Integer.valueOf(excelList.get(0).toString());// 套餐ID
                    BigDecimal price = new BigDecimal(excelList.get(1).toString());// 价格
                    String phone = excelList.get(2).toString().replace(".00", "");// 用户手机号
                    Date presellTime = null;// 预售时间
                    try {
                        presellTime = DateTimeUtils.stringToDate(excelList.get(3).toString().substring(0, 10), "yyyy-MM-dd");// 预售时间
                    } catch (Exception e) {
                        log.error("第" + row + "行未知错误" + e);
                        errorlist.add("第" + row + "行导入失败,时间格式不正确");
                        continue;
                    }
                    BetaMaleAddDto betaMaleAddDto = new BetaMaleAddDto();

                    betaMaleAddDto.setComboId(comboId);
                    betaMaleAddDto.setPrice(price);
                    betaMaleAddDto.setPresellTime(presellTime);
                    betaMaleAddDto.setPhone(phone);
                    Wrapper result = saveOrUpdate(betaMaleAddDto);
                    if (result.getCode() == 200) {
                        succeedlist.add("第" + row + "行导入成功");
                        BetaMaleImportRecord updateImportRecord = new BetaMaleImportRecord();
                        updateImportRecord.setId(id);
                        updateImportRecord.setImportStauts(BetaEnum.importStauts.succeed.getCode());
                        updateImportRecord.setUpdateTime(new Date());
                        betaMaleImportRecordMapper.updateById(updateImportRecord);
                    } else {
                        errorlist.add("第" + row + "行导入失败," + result.getMessage());
                    }

                } catch (Exception e) {
                    log.error("第" + row + "行未知错误" + e);
                    errorlist.add("第" + row + "行导入失败,未知错误" + e);
                }

//                String result = numberFormat.format((float) (row - 2) / (float) countLine * 100);
//                redismap.put("current", result);
//                redismap.put("page", (row - 2) + "/" + countLine);
//
//                redismap.put("count", countLine);// 总条数
//                redismap.put("succeed", succeedlist.size());// 成功条数
//                redismap.put("error", errorlist.size());// 失败条数
//
//                redismap.put("succeedlist", succeedlist);// 成功内容
//                redismap.put("errorlist", errorlist);// 失败内容
            }
        }
//            });
        // 清空之前的缓存
        redisTemplate.delete(redisTemplate.keys(RedisKeyUtil.MALE_REMAIN + "*"));
        // 获取剩余公狗数量
        List<BetaMaleVo> betaMaleVos = betaMaleMapper.selectByTodayRemain(DateTimeUtils.getDays());
        for (BetaMaleVo betaMaleVo : betaMaleVos) {
            redisTemplate.set(RedisKeyUtil.MALE_REMAIN + betaMaleVo.getComboId(), betaMaleVo.getMaleRemainSum(), GlobalConstant.Number.DAY_TIME_LONG);
        }
        return WrapMapper.ok("导入成功</br>异常信息:" + errorlist.toString());
    }

    @Override
    public Wrapper<String> updatePresellTime(BetaMaleAddDto betaMaleAddDto) {
        BetaMale betaMale = selectById(betaMaleAddDto.getId());
        if (betaMaleAddDto.getPresellTime().getTime() > betaMale.getPresellTime().getTime()) {
            return WrapMapper.error("发售时间只能往前修改");
        }
        betaMale.setPresellTime(betaMaleAddDto.getPresellTime());
        BetaMale betaMalePam = new BetaMale();
        betaMalePam.setId(betaMaleAddDto.getId());
        boolean flag = update(betaMale, new EntityWrapper<>(betaMalePam));
        if (flag) {
            // 获取剩余公狗数量
            BetaMaleVo betaMaleVo = betaMaleMapper.selectByTodayRemainOne(DateTimeUtils.getDays(), betaMale.getComboId());
            redisTemplate.set(RedisKeyUtil.MALE_REMAIN + betaMaleVo.getComboId(), betaMaleVo.getMaleRemainSum(), GlobalConstant.Number.DAY_TIME_LONG);
            return WrapMapper.ok("修改成功");
        }
        return WrapMapper.error("修改异常");
    }

    /**
     * @Description: 刷新缓存中的剩余公狗数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    @Override
    public Wrapper<String> refreshRedis() {
        // 清空之前的缓存
        redisTemplate.delete(redisTemplate.keys(RedisKeyUtil.MALE_REMAIN + "*"));
        // 获取剩余公狗数量
        List<BetaMaleVo> betaMaleVos = betaMaleMapper.selectByTodayRemain(DateTimeUtils.getDays());
        for (BetaMaleVo betaMaleVo : betaMaleVos) {
            redisTemplate.set(RedisKeyUtil.MALE_REMAIN + betaMaleVo.getComboId(), betaMaleVo.getMaleRemainSum(), GlobalConstant.Number.DAY_TIME_LONG);
        }
        return WrapMapper.ok("刷新成功");
    }

}
