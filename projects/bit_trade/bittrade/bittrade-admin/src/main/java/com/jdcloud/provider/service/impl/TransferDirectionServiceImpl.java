package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AccountManageConfigDto;
import com.jdcloud.provider.pojo.TransferDirection;
import com.jdcloud.provider.mapper.TransferDirectionMapper;
import com.jdcloud.provider.pojo.vo.TransferDirectionVo;
import com.jdcloud.provider.service.TransferDirectionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 划转配置表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-25
 */
@Service
public class TransferDirectionServiceImpl extends ServiceImpl<TransferDirectionMapper, TransferDirection> implements TransferDirectionService {

    /**
     * 账户划转
     *
     * @param page
     * @return
     */
    @Override
    public Page<TransferDirectionVo> getTransferDirection(Page<TransferDirectionVo> page, AccountManageConfigDto dto) {
        return page.setRecords(baseMapper.getTransferDirection(page, dto));
    }


    /**
     * 保存划转配置
     *
     * @param transferDirection
     * @return
     */
    @Override
    public Wrapper<String> addTransferDirection(TransferDirection transferDirection) {
        if(transferDirection.getMainAccountId()==transferDirection.getTargetAccountId()){
            return WrapMapper.error("源账户不能配置划转源账户！");
        }
        EntityWrapper entity = new EntityWrapper();
        entity.eq("main_account_id", transferDirection.getMainAccountId());
        entity.eq("target_account_id", transferDirection.getTargetAccountId());
        int count = baseMapper.selectCount(entity);
        if (count > 0) {
            return WrapMapper.error("已经配置过当前划转账户！");
        }
        int row = baseMapper.insert(transferDirection);
        if(row!=1){
            return WrapMapper.error("保存法币划转配置失败！");
        }
         return WrapMapper.ok("保存法币划转配置成功！");
    }
}
