package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.provider.dto.BetaProcessedaOrderDto;
import com.jdcloud.provider.mapper.BetaMaleMapper;
import com.jdcloud.provider.mapper.BetaOrderMapper;
import com.jdcloud.provider.mapper.BetaProcessedaOrderMapper;
import com.jdcloud.provider.mapper.ComboGroupMapper;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.pojo.vo.BetaProcessedaOrderVo;
import com.jdcloud.provider.service.BetaOrderService;
import com.jdcloud.provider.service.BetaProcessedaOrderService;
import com.jdcloud.provider.service.PersonalCardService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * beta订单处理表  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-24
 */
@Service
public class BetaProcessedaOrderServiceImpl extends ServiceImpl<BetaProcessedaOrderMapper, BetaProcessedaOrder> implements BetaProcessedaOrderService {

    @Resource
    private BetaOrderMapper betaOrderMapper;
    @Resource
    private ComboGroupMapper comboGroupMapper;
    @Autowired
    private PersonalCardService personalCardService;
    @Resource
    private BetaMaleMapper betaMaleMapper;
    @Resource
    private BetaOrderService betaOrderService;

    @Override
    public Page<BetaProcessedaOrderVo> betaProcessedaOrderListPage(Page<BetaProcessedaOrderVo> page, BetaProcessedaOrderDto betaProcessedaOrderDto) {
        page.setRecords(baseMapper.betaProcessedaOrderListPage(page, betaProcessedaOrderDto));
        return page;
    }


    @Override
    public BetaProcessedaOrderVo betaProcessedaOrderDetails(Integer id) {
        // 我要通过这个ID 去查询 处理订单表的状态
        BetaProcessedaOrder orderPam = baseMapper.selectById(id);

        BetaOrder betaOrder = betaOrderMapper.selectById(orderPam.getOrderId());
        BetaProcessedaOrderVo betaProcessedaOrderVo = new BetaProcessedaOrderVo();
        BeanUtils.copyProperties(betaOrder, betaProcessedaOrderVo);
        PersonalCard cardPam = new PersonalCard();
        // 判断如果传递过来的用户ＩＤ　＝　所属订单ＩＤ　则查询　
        cardPam.setUserId(betaOrder.getSellUserId());
        // 查询出转让方的用户信息
        PersonalCard cardSellUserId = personalCardService.selectOne(new EntityWrapper<>(cardPam));

        // 获取到公狗的参数 然后取出套餐信息
        BetaMale betaMale = betaMaleMapper.selectById(betaOrder.getFeminaId());
        if (betaMale != null) {
            ComboGroup comboGroup = comboGroupMapper.selectById(betaMale.getComboId());
            if (comboGroup != null) {
                BeanUtils.copyProperties(comboGroup, betaProcessedaOrderVo);
            }
        }
        // 查询领养方的用户信息
        if (betaOrder.getUserId() != null) {
            PersonalCard cardUserId = new PersonalCard();
            cardUserId.setUserId(betaOrder.getUserId());
            cardUserId = personalCardService.selectOne(new EntityWrapper<>(cardUserId));
            if (cardUserId != null) {
                betaProcessedaOrderVo.setName(cardUserId.getName());
                betaProcessedaOrderVo.setPhone(cardUserId.getPhone());
                BeanUtils.copyProperties(cardUserId, betaProcessedaOrderVo);
            }
        }
        betaProcessedaOrderVo.setCreateTime(orderPam.getCreateTime());
        betaProcessedaOrderVo.setId(orderPam.getId());
        betaProcessedaOrderVo.setProcessedaOrderStauts(orderPam.getProcessedaOrderStauts());
        betaProcessedaOrderVo.setRemarks(orderPam.getRemarks());

        betaProcessedaOrderVo.setSellUserName(cardSellUserId.getName());
        betaProcessedaOrderVo.setSellUserPhone(cardSellUserId.getPhone());
        betaProcessedaOrderVo.setBankName(cardSellUserId.getBankName());
        betaProcessedaOrderVo.setBankNumber(cardSellUserId.getBankNumber());
        betaProcessedaOrderVo.setAlipay(cardSellUserId.getAlipay());
        betaProcessedaOrderVo.setAlipayNumber(cardSellUserId.getAlipayNumber());
        betaProcessedaOrderVo.setWechat(cardSellUserId.getWechat());
        betaProcessedaOrderVo.setWechatNumber(cardSellUserId.getWechatNumber());
        return betaProcessedaOrderVo;
    }

    @Override
    public int betaProcessedaOrderdeletes(String ids) {
        Integer[] Ids = ConvertUtil.toIntArray(ids);
        return baseMapper.betaProcessedaOrderdeletes(Ids);
    }


    /**
     * 拒绝放狗（撤销）
     *
     * @param id
     * @return C
     * 2019-7-21
     */
    @Override
    public Wrapper updateRefuseDogoRecall(Integer id) {
        BetaProcessedaOrder prorder = baseMapper.selectById(id);
        if (null == prorder || !BetaEnum.processedaOrderStauts.NO.getCode().equals(prorder.getProcessedaOrderStauts())) {
            return WrapMapper.error("处理订单中无数据，刷新重试！");
        }

        BetaOrder hideOrder = betaOrderService.selectById(prorder.getHideOrderId());
        if (null == hideOrder || !BetaEnum.orderStauts.HIDDEN.getCode().equals(hideOrder.getOrderStauts())) {
            return WrapMapper.error("隐藏的订单异常！");
        }

        BetaOrder newOrder = betaOrderService.selectById(prorder.getNewOrderId());
        if (null == newOrder || !BetaEnum.orderStauts.TRANSFER.getCode().equals(newOrder.getOrderStauts())) {
            return WrapMapper.error("待转让的订单异常！");
        }
        // 隐藏的订单处理成客诉
        hideOrder.setOrderStauts(BetaEnum.orderStauts.COMPLAIN.getCode());
        Boolean bo = betaOrderService.updateById(hideOrder);
        if (!bo) {
            return WrapMapper.error("隐藏的订单处理成客诉失败！");
        }

        // 待转让的订单变为隐藏
        newOrder.setOrderStauts(BetaEnum.orderStauts.HIDDEN.getCode());
        Boolean boo = betaOrderService.updateById(newOrder);
        if (!boo) {
            return WrapMapper.error("待转让的订单变为隐藏失败！");
        }

        // 处理过的已处理订单无法进行再处理    3 拒绝放狗已处理
        prorder.setProcessedaOrderStauts(BetaEnum.processedaOrderStauts.HAVEHANDLED.getCode());
        prorder.setUpdateTime(new Date());
        int row  =   baseMapper.updateById(prorder);
        if (row!=1) {
            return WrapMapper.error("拒绝放狗已处理,状态修改失败！");
        }

        return WrapMapper.ok("撤销成功,订单变为客诉快去订单中处理吧！");
    }

    /**
     * 放狗操作（撤回）
     * @param id
     * @return
     */
    @Override
    public Wrapper updateputTheDog(Integer id) {
        BetaProcessedaOrder prorder = baseMapper.selectById(id);
        if (null == prorder || !BetaEnum.processedaOrderStauts.YES.getCode().equals(prorder.getProcessedaOrderStauts())) {
            return WrapMapper.error("处理订单中无放狗的数据，刷新重试！");
        }
        BetaOrder order  = betaOrderService.selectById(prorder.getOrderId());
        if(null==order || !BetaEnum.orderStauts.SUCCEED.getCode().equals(order.getOrderStauts())){
            return WrapMapper.error("订单不存在！");
        }

        // 隐藏的订单处理成客诉
        order.setOrderStauts(BetaEnum.orderStauts.COMPLAIN.getCode());
        Boolean bo = betaOrderService.updateById(order);
        if (!bo) {
            return WrapMapper.error("订单处理成客诉失败！");
        }

        // 处理过的已处理订单无法进行再处理    4放狗已经处理
        prorder.setProcessedaOrderStauts(BetaEnum.processedaOrderStauts.YESHAVEHANDLED.getCode());
        prorder.setUpdateTime(new Date());
        int row  =   baseMapper.updateById(prorder);
        if (row!=1) {
            return WrapMapper.error("放狗已处理,状态修改失败！");
        }
        return WrapMapper.ok("撤销成功,订单变为客诉快去订单中处理！");
    }



}
