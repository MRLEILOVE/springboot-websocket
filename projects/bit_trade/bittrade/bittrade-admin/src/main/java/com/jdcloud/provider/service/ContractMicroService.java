package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.ContractMicro;

import java.util.List;

/**
 * <p>
 * 微盘合约订单 服务类
 * </p>
 *
 * @author helen
 * @since 2018-10-29
 */
public interface ContractMicroService extends IService<ContractMicro> {

    /**
     * 查询微合约持仓集合
     *
     * @param  micro
     * @return 微合约持仓集合
     */
    Page<ContractMicro> selectContractMicroList(Page<ContractMicro> page, ContractMicro micro);

    /**
     * 查询微合约历史集合----Excel导出
     *
     * @param  micro
     * @return 微合约持仓集合
     */
    List<ContractMicro> selectContractMicroExportList(ContractMicro micro);
}
