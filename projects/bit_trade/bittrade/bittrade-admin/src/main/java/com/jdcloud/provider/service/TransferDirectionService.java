package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AccountManageConfigDto;
import com.jdcloud.provider.pojo.TransferDirection;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.vo.TransferDirectionVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 划转配置表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-25
 */
public interface TransferDirectionService extends IService<TransferDirection> {

    Page<TransferDirectionVo> getTransferDirection(Page<TransferDirectionVo> page, AccountManageConfigDto dto);

    Wrapper<String> addTransferDirection(TransferDirection transferDirection);
}
