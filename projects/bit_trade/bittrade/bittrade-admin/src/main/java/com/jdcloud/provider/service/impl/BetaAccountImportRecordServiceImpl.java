package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaAccountImportRecordDto;
import com.jdcloud.provider.pojo.BetaAccountImportRecord;
import com.jdcloud.provider.mapper.BetaAccountImportRecordMapper;
import com.jdcloud.provider.service.BetaAccountImportRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-08-15
 */
@Service
public class BetaAccountImportRecordServiceImpl extends ServiceImpl<BetaAccountImportRecordMapper, BetaAccountImportRecord> implements BetaAccountImportRecordService {

    /**
     * @param page
     * @param betaAccountImportRecordDto
     * @Description: 资金导入记录
     * @Author: senghor
     * @Date: 2019/8/16 9:55
     */
    @Override
    public Page<BetaAccountImportRecord> selectBetaAccountImportRecordListPage(Page<Object> page, BetaAccountImportRecordDto betaAccountImportRecordDto) {
        return baseMapper.selectBetaAccountImportRecordListPage(page, betaAccountImportRecordDto);
    }
}
