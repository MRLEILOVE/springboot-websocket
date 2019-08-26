package com.jdcloud.provider.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.MessageConstant;
import com.jdcloud.base.dto.SmsMessageDto;
import com.jdcloud.base.enums.AuditEnum;
import com.jdcloud.core.message.SmsService;
import com.jdcloud.provider.dto.TOrderDto;
import com.jdcloud.provider.dto.TotalDto;
import com.jdcloud.provider.dto.WithDrawParamDto;
import com.jdcloud.provider.mapper.TOrderMapper;
import com.jdcloud.provider.model.dto.UserDto;
import com.jdcloud.provider.model.service.UacUserFeignApi;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.pojo.vo.TOrderVo;
import com.jdcloud.provider.pojo.vo.TotalVo;
import com.jdcloud.provider.service.*;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-08
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

    @Autowired
    private ITwalletFundAccountService iTwalletFundAccountService;

    @Autowired
    private WConfigWalletService wConfigWalletService;

    @Autowired
    private IJsonRpcService jsonRpcService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<String> refuse(Integer id) {
        TOrder tOrder = this.selectById(id);
        if (tOrder.getType() != AuditEnum.orderType.NOTAUDITED.getCode()) {
            WrapMapper.error("审核异常");
        }
        tOrder.setType(AuditEnum.orderType.SERVICEREFUSE.getCode());
        this.updateById(tOrder);
        EntityWrapper<TWalletFundAccount> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_id", tOrder.getUserId()).eq("currency", tOrder.getToken());
        TWalletFundAccount tWalletFundAccount = iTwalletFundAccountService.selectOne(entityWrapper);
        tWalletFundAccount.setTotal(tWalletFundAccount.getTotal().add(tOrder.getAmount()).add(tOrder.getFee()));
        tWalletFundAccount.setTransferFrozen(tWalletFundAccount.getTransferFrozen().subtract(tOrder.getAmount()).subtract(tOrder.getFee()));
        iTwalletFundAccountService.updateById(tWalletFundAccount);
        return WrapMapper.ok("拒绝成功");
    }

    /**
     * 提现审核列表
     *
     * @param page
     * @param tOrderDto
     * @return
     */
    @Override
    public Page<TOrderVo> getCustomerList(Page<TOrderVo> page, TOrderDto tOrderDto) {
        List<TOrderVo> tOrderVos = baseMapper.getCustomerList(page, tOrderDto);
        if (CollectionUtils.isNotEmpty(tOrderVos)) {
            tOrderVos.forEach(tOrderVo -> {
                BigDecimal fee = tOrderVo.getFee();
                fee = Objects.isNull(fee) ? BigDecimal.ZERO : fee;
                tOrderVo.setFeeStr(fee.stripTrailingZeros().toPlainString());
            });
        }
        return page.setRecords(tOrderVos);
    }

    /**
     * 财务审核列表
     *
     * @param page
     * @param tOrderDto
     * @return
     */
    @Override
    public Page<TOrderVo> getFinanceList(Page<TOrderVo> page, TOrderDto tOrderDto) {
        List<TOrderVo> tOrderVos = baseMapper.getFinanceList(page, tOrderDto);
        if (CollectionUtils.isNotEmpty(tOrderVos)) {
            tOrderVos.forEach(tOrderVo -> {
                BigDecimal fee = tOrderVo.getFee();
                fee = Objects.isNull(fee) ? BigDecimal.ZERO : fee;
                tOrderVo.setFeeStr(fee.stripTrailingZeros().toPlainString());
            });
        }
        return page.setRecords(tOrderVos);
    }

    /**
     * 新的客服审核列表
     *
     * @param page
     * @param tOrderDto
     * @return
     */
    @Override
    public Page<TOrderVo> getCustomerPage(Page<TOrderVo> page, TOrderDto tOrderDto) {
        List<TOrderVo> tOrderVos = baseMapper.getCustomerPage(page, tOrderDto);
        if (CollectionUtils.isNotEmpty(tOrderVos)) {
            tOrderVos.forEach(tOrderVo -> {
                BigDecimal fee = tOrderVo.getFee();
                fee = Objects.isNull(fee) ? BigDecimal.ZERO : fee;
                tOrderVo.setFeeStr(fee.stripTrailingZeros().toPlainString());
            });
        }
        return page.setRecords(tOrderVos);
    }

    /**
     * 财务拒绝
     *
     * @param id
     * @return C
     */
    @Override
    public Wrapper financialRejection(Integer id) {
        TOrder tOrder = this.selectById(id);
        if (tOrder.getType() != AuditEnum.orderType.CSAUDITED.getCode()) {
            WrapMapper.error("审核异常");
        }
        tOrder.setType(AuditEnum.orderType.REFUSE.getCode());
        this.updateById(tOrder);
        EntityWrapper<TWalletFundAccount> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_id", tOrder.getUserId()).eq("currency", tOrder.getToken());
        TWalletFundAccount tWalletFundAccount = iTwalletFundAccountService.selectOne(entityWrapper);
        tWalletFundAccount.setTotal(tWalletFundAccount.getTotal().add(tOrder.getAmount()).add(tOrder.getFee()));
        tWalletFundAccount.setTransferFrozen(tWalletFundAccount.getTransferFrozen().subtract(tOrder.getAmount()).subtract(tOrder.getFee()));
        iTwalletFundAccountService.updateById(tWalletFundAccount);
        return WrapMapper.ok("拒绝成功");
    }


    /**
     * 币种 资金总额
     *
     * @param page
     * @return
     */
    @Override
    public Page<TotalVo> getTotal(Page<TotalVo> page,TotalDto dto) {
        EntityWrapper<WConfigWallet> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("valid", "E");
        if(StringUtils.isNotBlank(dto.getCoinType())){
            entityWrapper.eq("coin_type",dto.getCoinType());
        }
        if(StringUtils.isNotBlank(dto.getWalletType())){
            entityWrapper.eq("wallet_type",dto.getWalletType());
        }

        List<WConfigWallet> list = wConfigWalletService.selectList(entityWrapper);
        List<TotalVo> list1 = new ArrayList<>();
        for (WConfigWallet wConfigWallet : list) {
            TotalVo totalVo = new TotalVo();
            totalVo.setCoinType(wConfigWallet.getCoinType());
            totalVo.setWalletType(wConfigWallet.getWalletType());
            BigDecimal btcBalance = jsonRpcService.getBalance(wConfigWallet.getAddress());
            BigDecimal usdtBalance = jsonRpcService.getTokenBalance(wConfigWallet.getAddress(), "31");
            totalVo.setBtcBalance(btcBalance);
            totalVo.setUsdtBalance(usdtBalance);
            totalVo.setAddress(wConfigWallet.getAddress());
            list1.add(totalVo);
        }
        return page.setRecords(list1);
    }


    /**
     * 客服订单详情
     * @param id
     * @return
     */
    @Override
    public TOrderVo getCustomerDetail(Integer id) {
        TOrderVo tOrderVos = baseMapper.getCustomerDetail(id);
        return tOrderVos;
    }


}
