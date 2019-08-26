package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.C2cEnum;
import com.jdcloud.provider.dto.OrderUserDto;
import com.jdcloud.provider.dto.OrderUserListDto;
import com.jdcloud.provider.mapper.OrderEntrustMapper;
import com.jdcloud.provider.mapper.OrderUserMapper;
import com.jdcloud.provider.mapper.PersonalAccountMapper;
import com.jdcloud.provider.pojo.OrderEntrust;
import com.jdcloud.provider.pojo.OrderUser;
import com.jdcloud.provider.pojo.PersonalAccount;
import com.jdcloud.provider.pojo.vo.OrderUserDetailsVo;
import com.jdcloud.provider.service.OrderUserService;
import com.jdcloud.provider.service.PersonalAccountService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import groovy.util.logging.Slf4j;
import jline.internal.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *orderUserdetails
 * @author helen
 * @since 2019-03-03
 */
@Service
@Slf4j
public class OrderUserServiceImpl extends ServiceImpl<OrderUserMapper, OrderUser> implements OrderUserService {

    @Resource
    private  OrderUserMapper orderUserMapper;
    @Resource
    private OrderEntrustMapper orderEntrustMapper;
    @Resource
    private PersonalAccountService personalAccountService;
    @Resource
    private PersonalAccountMapper personalAccountMapper;
    @Override
    public Page<OrderUser> selectOrderUserPage(Page<OrderUser> page, OrderUserListDto orderUserListDto) {
        page.setRecords( orderUserMapper.selectOrderUserPage(page, orderUserListDto ) );
        return page;
    }

    @Override
    public List<OrderUserDetailsVo> OrderUserExcelList(OrderUserListDto orderUserListDto) {
        return orderUserMapper.OrderUserExcelList(orderUserListDto);
    }

    @Override
    public OrderUserDetailsVo selectOrderUserDetails(Integer id){
       return orderUserMapper.selectOrderUserDetails(id);
    }

    /**
     * 购买出售订单的详细页
     * @param page
     * @param orderUserListDto
     * @return
     */
    @Override
    public Page<OrderUserDetailsVo> selectOrderUserDetailsPage(Page<OrderUserDetailsVo> page, OrderUserListDto orderUserListDto) {
        page.setRecords( orderUserMapper.selectOrderUserDetailsPage(page, orderUserListDto ) );
        return page;
    }



//    /**
//     * 放币服务类
//     */
//    @Override
//    @Transactional
//    public Wrapper updateOrderUserCoinRelease(OrderUserDto addContractDto) {
//        //判断订单是否存在
//        OrderUser orderUser = new OrderUser();
//        orderUser.setId(addContractDto.getId());
//        orderUser = orderUserMapper.selectOne(orderUser);
//        if (orderUser == null) {
//            return WrapMapper.error("订单不存在");
//        }
//        if (1 == orderUser.getOrderStauts() || 0 == orderUser.getOrderStauts()) {
//            return WrapMapper.error("放币失败！");
//        }
//        OrderEntrust oe = new OrderEntrust();
//        //获取到委托单的参数
//        OrderEntrust orderEntrust = orderEntrustMapper.selectById(orderUser.getEntrustId());
//        //1 是购买单GlobalConstant.Number.TWO_2
//        if (C2cEnum.orderUserType.ORDERUSEEER_SELL.getCode().equals(orderUser.getType())) {
//            if (!addContractDto.getUserId().equals(orderUser.getUserId())) {
//                return WrapMapper.error("用户无权限操作订单！");
//            }
//            //用户的资产信息
//            PersonalAccount personalAccountA = new PersonalAccount();
//            personalAccountA.setUserId(addContractDto.getUserId());
//            personalAccountA.setProductType(orderUser.getProductType());
//            personalAccountA = personalAccountService.selectOne(new EntityWrapper<PersonalAccount>(personalAccountA));
//            //订单交易类型
//            if (orderUser.getQuantity().compareTo(personalAccountA.getUsedMargin()) > GlobalConstant.Number.ZERO_0) {
//                return WrapMapper.error("冻结资金异常无法放币！");
//            }
//            //修改冻结资金：冻结资金-输入的金额
//            BigDecimal countMargin = personalAccountA.getUsedMargin().subtract(orderUser.getQuantity());
//            personalAccountA.setUsedMargin(countMargin);
//            //修改的总出金：当前总出金+当前金额
//            BigDecimal countTotalExitA = personalAccountA.getTotalExit().add(orderUser.getQuantity());
//            personalAccountA.setTotalExit(countTotalExitA);
//            personalAccountService.updatePersonalAccount(personalAccountA);
//            // 委托单为出售单  只增加流水记录        //用户出售给委托方   用户收到委托方的付款  用户减少冻结资金，增加总出金
//            personalAccountService.personalAccountAdd(addContractDto.getUserId(), orderUser.getProductType(),
//                    orderUser.getQuantity(), BetaEnum.serialType.SERIAL_REDUCE.getCode(),
//                    "放币成功,剩余冻结资产:" + countMargin);
//
//            //B方此交易单，已经完成，
//            //获取购买方到用户的资产信息
//            PersonalAccount personalAccountB = new PersonalAccount();
//            personalAccountB.setUserId(orderUser.getOrderEntrustUserId());
//            personalAccountB.setProductType(orderUser.getProductType());//根据订单上面的交易类型
//            personalAccountB = personalAccountService.selectOne(new EntityWrapper<PersonalAccount>(personalAccountB));
//            //修改B的总入金
//            BigDecimal countTotalEnterB = personalAccountA.getTotalEnter().add(orderUser.getQuantity());
//            personalAccountB.setTotalEnter(countTotalEnterB);
//
//            //修改B的资产余额
//            BigDecimal countBalance = personalAccountB.getBalance().add(orderUser.getQuantity());
//            personalAccountB.setBalance(countBalance);
//            personalAccountService.updatePersonalAccount(personalAccountB);
//            // 只增加流水记录
//            personalAccountService.personalAccountAdd(orderUser.getOrderEntrustUserId(), orderUser.getProductType(),
//                    orderUser.getQuantity(), BetaEnum.serialType.SERIAL_ADD.getCode(), "收币成功，增加余额");
//            // 成交量
//            int quantity = orderUser.getQuantity().intValue();
//            int turnover = orderEntrust.getTurnover();
//            oe.setTurnover(quantity + turnover);
//            oe.setId(addContractDto.getEntrustId());
//            Integer row= orderEntrustMapper.updateById(oe);
//            if(row!=1){
//                return WrapMapper.error("更新成交量失败");
//            }
//        }
//        //1 是购买GlobalConstant.Number.ONE_1
//        if (C2cEnum.orderUserType.ORDERUSEEER_BUY.getCode().equals(orderUser.getType())) {
//            if (!addContractDto.getUserId().equals(orderUser.getOrderEntrustUserId())) {
//                return WrapMapper.error("用户无权限操作订单！");
//            }
//            //获取到用户的资产信息
//            PersonalAccount personalAccountA = new PersonalAccount();
//            personalAccountA.setUserId(orderUser.getOrderEntrustUserId());
//            personalAccountA.setProductType(orderUser.getProductType());
//            personalAccountA = personalAccountService.selectOne(new EntityWrapper<PersonalAccount>(personalAccountA));
//            //订单交易类型
//            //修改B冻结资金
//            BigDecimal countMargin = personalAccountA.getUsedMargin().subtract(orderUser.getQuantity());
//            if (orderUser.getQuantity().compareTo(personalAccountA.getUsedMargin()) > GlobalConstant.Number.ZERO_0) {
//                return WrapMapper.error("冻结账户异常无法放币！");
//            }
//            // 增加总出金
//            personalAccountA.setUsedMargin(countMargin);
//            BigDecimal countTotalExitA = personalAccountA.getTotalExit().add(orderUser.getQuantity());
//            personalAccountA.setTotalExit(countTotalExitA);
//            personalAccountService.updatePersonalAccount(personalAccountA);
//            //  委托单为购买单    只生成流水
//            personalAccountService.personalAccountAdd(orderUser.getOrderEntrustUserId(), orderUser.getProductType(),
//                    orderUser.getQuantity(), BetaEnum.serialType.SERIAL_REDUCE.getCode(),
//                    "放币成功,剩余冻结资产:" + countMargin);
//            //B方此交易单，已经完成，
//            //获取购买方用户的资产信息
//            PersonalAccount personalAccountB = new PersonalAccount();
//            personalAccountB.setUserId(orderUser.getUserId());
//            personalAccountB.setProductType(orderUser.getProductType());//根据订单上面的交易类型
//            personalAccountB = personalAccountService.selectOne(new EntityWrapper<PersonalAccount>(personalAccountB));
//            //修改A的总入金
//            BigDecimal countTotalEnterB = personalAccountA.getTotalEnter().add(orderUser.getQuantity());
//            personalAccountB.setTotalEnter(countTotalEnterB);
//            //修改A的资产余额
//            BigDecimal countBalance = personalAccountB.getBalance().add(orderUser.getQuantity());
//            personalAccountB.setBalance(countBalance);
//            personalAccountService.updatePersonalAccount(personalAccountB);
//
//            // 购买人 付款完成  增加流水  流水
//            personalAccountService.personalAccountAdd(orderUser.getUserId(), orderUser.getProductType(),
//                    orderUser.getQuantity(), BetaEnum.serialType.SERIAL_ADD.getCode(),
//                    "收币成功，余额增加:"+orderUser.getQuantity());
//
//            // 增加委托单上的成交量
//            int quantity = orderUser.getQuantity().intValue();
//            int turnover = orderEntrust.getTurnover();
//            oe.setTurnover(quantity + turnover);
//            oe.setId(addContractDto.getEntrustId());
//            Integer row =  orderEntrustMapper.updateById(oe);
//            if(row!=1){
//                return WrapMapper.ok("成交量更新失败！");
//            }
//        }
//        orderUser.setUpdateTime(new Date());
//        orderUser.setId(addContractDto.getId());
//        orderUser.setOrderStauts(GlobalConstant.Number.ONE_1);
//        Integer row = orderUserMapper.updateById(orderUser);
//        if(row!=1){
//            return WrapMapper.ok("更新订单状态失败！");
//        }
//        return WrapMapper.ok("订单修改成功！");
//    }

    /**
     * 放币服务类
     *
     * @param addContractDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Wrapper updateOrderUserCoinRelease(OrderUserDto addContractDto) {
        Log.info("进入到放币服务类"+addContractDto);
        //判断订单是否存在
        OrderUser orderUser = new OrderUser();
        orderUser.setId(addContractDto.getId());
        orderUser = orderUserMapper.selectOne(orderUser);
        if (orderUser == null) {
            return WrapMapper.error("订单不存在");
        }
        if (1 == orderUser.getOrderStauts() || 0 == orderUser.getOrderStauts()) {
            return WrapMapper.error("放币失败！");
        }
        OrderEntrust oe = new OrderEntrust();
        //获取到委托单的参数
        OrderEntrust orderEntrust = orderEntrustMapper.selectById(orderUser.getEntrustId());
        //1 是购买单GlobalConstant.Number.TWO_2
        if (C2cEnum.orderUserType.ORDERUSEEER_SELL.getCode().equals(orderUser.getType())) {

            Log.info("是购买单GlobalConstant.Number.TWO_2"+orderUser.getType());

            //用户的资产信息
            PersonalAccount personalAccountA = new PersonalAccount();
            personalAccountA.setUserId(addContractDto.getUserId());
            personalAccountA.setProductType(orderUser.getProductType());
            personalAccountA = personalAccountService.selectOne(new EntityWrapper<PersonalAccount>(personalAccountA));
            //订单交易类型
            if (orderUser.getQuantity().compareTo(personalAccountA.getUsedMargin()) > GlobalConstant.Number.ZERO_0) {
                return WrapMapper.error("冻结资金异常无法放币！");
            }
            //修改冻结资金：冻结资金-输入的金额
            BigDecimal countMargin = personalAccountA.getUsedMargin().subtract(orderUser.getQuantity());

            // 委托单为出售单  只增加流水记录        //用户出售给委托方   用户收到委托方的付款  用户减少冻结资金，增加总出金
            personalAccountService.personalAccountAddNew(addContractDto.getUserId(), orderUser.getProductType(),
                    orderUser.getQuantity(), BetaEnum.serialType.SERIAL_REDUCE.getCode(),
                    "放币成功:" , orderUser.getId());
            // 只增加流水记录
            personalAccountService.personalAccountAddNew(orderUser.getOrderEntrustUserId(), orderUser.getProductType(),
                    orderUser.getQuantity(), BetaEnum.serialType.SERIAL_ADD.getCode(), "收币成功，增加余额"
                            + orderUser.getQuantity(),
                    orderUser.getId());
            // 成交量
            int quantity = orderUser.getQuantity().intValue();
            int turnover = orderEntrust.getTurnover();
            oe.setTurnover(quantity + turnover);
            oe.setId(addContractDto.getEntrustId());
            orderEntrustMapper.updateById(oe);
        }
        //1 是购买GlobalConstant.Number.ONE_1
        if (C2cEnum.orderUserType.ORDERUSEEER_BUY.getCode().equals(orderUser.getType())) {
//            if (!addContractDto.getUserId().equals(orderUser.getOrderEntrustUserId())) {
//                return WrapMapper.error("用户无权限操作订单！");
//            }
            Log.info("是购买GlobalConstant.Number.ONE_1"+orderUser.getType());
            //获取到用户的资产信息
            PersonalAccount personalAccountA = new PersonalAccount();
            personalAccountA.setUserId(orderUser.getOrderEntrustUserId());
            personalAccountA.setProductType(orderUser.getProductType());
            personalAccountA = personalAccountService.selectOne(new EntityWrapper<PersonalAccount>(personalAccountA));
            //订单交易类型
            //修改B冻结资金
            BigDecimal countMargin = personalAccountA.getUsedMargin().subtract(orderUser.getQuantity());
            if (orderUser.getQuantity().compareTo(personalAccountA.getUsedMargin()) > GlobalConstant.Number.ZERO_0) {
                return WrapMapper.error("冻结账户异常无法放币！");
            }
            //  委托单为购买单    只生成流水
            personalAccountService.personalAccountAddNew(orderUser.getOrderEntrustUserId(), orderUser.getProductType(),
                    orderUser.getQuantity(), BetaEnum.serialType.SERIAL_REDUCE.getCode(),
                    "放币成功:" , orderUser.getId());

            //B方此交易单，已经完成，
            //获取购买方用户的资产信息
            PersonalAccount personalAccountB = new PersonalAccount();
            personalAccountB.setUserId(orderUser.getUserId());
            personalAccountB.setProductType(orderUser.getProductType());//根据订单上面的交易类型
            personalAccountB = personalAccountService.selectOne(new EntityWrapper<PersonalAccount>(personalAccountB));

            // 购买人 付款完成  增加流水  流水
            personalAccountService.personalAccountAddNew(orderUser.getUserId(), orderUser.getProductType(),
                    orderUser.getQuantity(), BetaEnum.serialType.SERIAL_ADD.getCode(),
                    "收币成功，余额增加:" + orderUser.getQuantity(), orderUser.getId());

            // 增加委托单上的成交量
            int quantity = orderUser.getQuantity().intValue();
            int turnover = orderEntrust.getTurnover();
            oe.setTurnover(quantity + turnover);
            oe.setId(addContractDto.getEntrustId());
            Integer row =  orderEntrustMapper.updateById(oe);
            if(row!=1){
                return WrapMapper.error("订单修改失败！");
            }
        }
        orderUser.setUpdateTime(new Date());
        orderUser.setId(addContractDto.getId());
        orderUser.setOrderStauts(GlobalConstant.Number.ONE_1);
        Integer row =  orderUserMapper.updateById(orderUser);
        if(row!=1){
            return WrapMapper.error("订单修改失败！");
        }
        Log.info("row 订单修改成功");
        return WrapMapper.ok("订单修改成功！");
    }


//    @Override
//    @Transactional
//    public Wrapper updateOrderUserCoinCancel(OrderUserDto addContractDto) {
//        //通过订单id去订单是否存在
//        OrderUser orderUser = new OrderUser();
//        orderUser.setId(addContractDto.getId());
//        orderUser = orderUserMapper.selectOne(orderUser);
//        if (orderUser == null) {
//            return WrapMapper.error("订单不存在");
//        }
//        //成功和取消不能取消
//        if (orderUser.getOrderStauts() == 0) {
//            return WrapMapper.error("订单状态异常");
//        }
//        if (orderUser.getOrderStauts() == 1) {
//            return WrapMapper.error("订单状态异常");
//        }
//
//        orderUser.setId(addContractDto.getId());
//        orderUser.setOrderStauts(GlobalConstant.Number.ZERO_0);
//        orderUser.setOrderStautsType(GlobalConstant.Number.TWO_2);
//        orderUserMapper.updateById(orderUser);
//
//        OrderEntrust oe = new OrderEntrust();
//        //通过关联订单id，获取到委托单的参数
//        OrderEntrust orderEntrust = orderEntrustMapper.selectById(orderUser.getEntrustId());
//        if (orderEntrust == null) {
//            return WrapMapper.error("委托单异常");
//        }
//        //取消后订单的数量增加
//        BigDecimal countQuantity = orderEntrust.getQuantity().add(orderUser.getQuantity());
//        oe.setQuantity(countQuantity);
//        oe.setId(orderEntrust.getId());
//        orderEntrustMapper.updateById(oe);
//        return WrapMapper.ok("修改成功！");
//    }


    /**
     * 取消
     *
     * @param addContractDto
     * @return
     */
    @Override
    @Transactional
    public Wrapper updateOrderUserCoinCancel(OrderUserDto addContractDto) {
        //通过订单id去订单是否存在
        OrderUser orderUser = new OrderUser();
        orderUser.setId(addContractDto.getId());
        orderUser = orderUserMapper.selectOne(orderUser);
        if (orderUser == null) {
            return WrapMapper.error("订单不存在");
        }
        //成功和取消不能取消
        if (orderUser.getOrderStauts() == GlobalConstant.Number.ZERO_0) {
            return WrapMapper.error("订单状态异常");
        }
        if (orderUser.getOrderStauts() == GlobalConstant.Number.ONE_1) {
            return WrapMapper.error("订单状态异常");
        }

        orderUser.setId(addContractDto.getId());
        orderUser.setOrderStauts(GlobalConstant.Number.ZERO_0);
        orderUser.setOrderStautsType(GlobalConstant.Number.ONE_1);
        orderUserMapper.updateById(orderUser);

        OrderEntrust oe = new OrderEntrust();
        //通过关联订单id，获取到委托单的参数
        OrderEntrust orderEntrust = orderEntrustMapper.selectById(orderUser.getEntrustId());
        if (orderEntrust == null) {
            return WrapMapper.error("委托单异常");
        }
        //取消后订单的数量增加
        BigDecimal countQuantity = orderEntrust.getQuantity().add(orderUser.getQuantity());
        oe.setQuantity(countQuantity);
        oe.setId(orderEntrust.getId());
        orderEntrustMapper.updateById(oe);

        // 取消后 减少 卖用户的
        if (orderUser.getType() == C2cEnum.orderUserType.ORDERUSEEER_SELL.getCode()) { // 如果这是一条出售单
            //获取到用户的资产信息
            PersonalAccount personalAccount = new PersonalAccount();
            personalAccount.setUserId(orderUser.getUserId());
            personalAccount.setProductType(orderUser.getProductType());//根据订单上面的交易类型
            personalAccount = personalAccountService.selectOne(new EntityWrapper<PersonalAccount>(personalAccount));
            //修改冻结资金
            BigDecimal countMargin = personalAccount.getUsedMargin().subtract(orderUser.getQuantity());
            personalAccount.setUsedMargin(countMargin);

            //增加余额
           /* BigDecimal countBlean = orderUser.getQuantity().add(personalAccount.getBalance());
            personalAccount.setBalance(countBlean);*/

            //修改的总出金
            BigDecimal countTotalExitA = personalAccount.getTotalExit().add(orderUser.getQuantity());
            personalAccount.setTotalEnter(countTotalExitA);
            // 增加金额
            personalAccountService.updatePersonalAccount(personalAccount);
            // 增加流水
            personalAccountService.updatePersonalAccountNew(orderUser.getUserId(),
                    orderUser.getProductType(),
                    orderUser.getQuantity(), BetaEnum.serialType.SERIAL_ADD.getCode(),
                    "订单被取消,冻结资金转入余额" + orderUser.getQuantity(), orderUser.getId());
        }
        return WrapMapper.ok("订单修改成功！");
    }

}
