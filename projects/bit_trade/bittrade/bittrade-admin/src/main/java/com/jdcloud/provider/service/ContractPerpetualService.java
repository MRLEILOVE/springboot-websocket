package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.ContractPerpetual;

import java.util.List;

/**
 * <p>
 * 永续合约交易表 服务类
 * </p>
 *
 * @author helen
 * @since 2018-11-22
 */
public interface ContractPerpetualService extends IService<ContractPerpetual> {

    /**
     * 查询永续合约持仓集合
     *
     * @param  perpetual
     * @return 永续合约持仓集合
     */
    Page<ContractPerpetual> selectContractPerpetualList(Page<ContractPerpetual> page, ContractPerpetual perpetual);
    /**
     * 查询永续合约历史集合
     *
     * @param  perpetual
     * @return 永续合约持仓集合
     */
    Page<ContractPerpetual> selectContractPerpetualHistoryList(Page<ContractPerpetual> page, ContractPerpetual perpetual);
    /**
     * 查询永续合约历史集合----Excel导出
     *
     * @param  perpetual
     * @return 永续合约持仓集合
     */
    List<ContractPerpetual> selectContractPerpetualExportList(ContractPerpetual perpetual);

    /**
     * 查询订单ID
     * @param orderId
     * @return
     */
    ContractPerpetual selectContractPerpetualById(String orderId);

    /**
     * 查询永续合约持仓数量<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/12/27 21:54
     */
    Integer selectContractPerpetualCount(Long userId);
}
