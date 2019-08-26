package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaMaleOrderDto;
import com.jdcloud.provider.dto.BetaOrderDto;
import com.jdcloud.provider.pojo.BetaOrder;
import com.jdcloud.provider.pojo.vo.BetaMaleOrderVo;
import com.jdcloud.provider.pojo.vo.BetaOrderVo;
import com.jdcloud.util.wrapper.Wrapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 贝塔狗---订单表（每笔装让记录都会生成一条订单数据作为记录） 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-15
 */
public interface BetaOrderService extends IService<BetaOrder> {

    Page<BetaOrder> betaOrderListPage(Page<BetaOrder> page,Integer id);

    Page<BetaOrder> selectPageList(Page<BetaOrder> page,BetaOrder dto);

    Page<BetaMaleOrderVo> betaOrderListPageAppeal(Page<BetaMaleOrderVo> page, BetaMaleOrderDto betaMaleOrderDto);

    BetaMaleOrderVo orderListDetails(Integer id);

    List<BetaMaleOrderVo> exportBetaOrderList(BetaMaleOrderDto betaMaleOrderDto);

    Wrapper<String> auditRelease(Integer id,String remarks);

    /**
     * @Description: 拒绝放狗
     * @Author: senghor
     * @Date: 2019/6/18 20:18
     */
    Wrapper auditRefuseDogo(Integer id,String remarks);

    /**
     * @Description: 持仓数据监控列表
     * @Author: senghor
     * @Date: 2019/5/14 0014 16:27
     */
    Page<BetaOrderVo> selectCountUserBeta(Page<BetaOrderVo> page, BetaOrderDto betaOrderDto);

    List<BetaOrder> selectAllUserId();

    BigDecimal getSumDifferential();

    /**
     * @Description: 一键客诉
     * @Author: zjun
     * @Date: 2019/8/6 17:28
     */
    Wrapper<String> customerComplaint();


    /**
     * @Description: 一键拒绝放狗
     * @Author: zjun
     * @Date: 2019/8/6 17:28
     */
    void auditRefuseDogoAll();

    /**
     * @Description: 打包出售审核
     * @param id : 订单ID
     * @param type : 1=通过，2=拒绝
     * @Author: zjun
     * @Date: 2019/8/13 21:10
     */
    Wrapper<String> packAudit(Integer id, Integer type);

    /**
     * @Description: 获取打包审核列表
     * @param page :
     * @param betaMaleOrderDto :
     * @Author: zjun
     * @Date: 2019/8/14 10:17
     */
    Page<BetaMaleOrderVo> betaOrderAuditListPage(Page<BetaMaleOrderVo> page, BetaMaleOrderDto betaMaleOrderDto);


}
