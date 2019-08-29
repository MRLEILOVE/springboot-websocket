package com.common.bittrade.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.impl.DefaultTWalletRecordServiceImpl;
import com.bittrade.common.enums.WalletRecordTypeEnumer;
import com.bittrade.pojo.dto.AccountTypeDto;
import com.bittrade.pojo.dto.TWalletRecordDTO;
import com.bittrade.pojo.model.TWalletRecord;
import com.bittrade.pojo.vo.RecordVO;
import com.bittrade.pojo.vo.TWalletRecordVO;
import com.common.bittrade.dao.ITWalletRecordDAO;
import com.common.bittrade.service.ITWalletRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TWalletRecordServiceImpl extends DefaultTWalletRecordServiceImpl<ITWalletRecordDAO, TWalletRecord, TWalletRecordDTO, TWalletRecordVO> implements ITWalletRecordService {
    @Autowired
    private ITWalletRecordDAO walletRecordDAO;

    /**
     * 币币账户资产记录
     * @param userId 用户id
     * @param dto 请求对象
     * @return 币币账户资产记录列表
     */
    @Override
    public Page<RecordVO> queryBiBiAccountRecord(Long userId, AccountTypeDto dto) {
        Page<RecordVO> page = new Page<>(dto.getCurrent(),dto.getSize());
        //1-转入 2-转出 3-买入 4-卖出
        List<Integer> types = new ArrayList<>();
        if(dto.getType() != null){
            switch (dto.getType()){
                case 1 :
                    types = Arrays.asList(WalletRecordTypeEnumer.FUNDS_TO_BIBI.getCode().intValue(),WalletRecordTypeEnumer.PERSONAL_TO_BIBI.getCode().intValue());
                    break;
                case 2 :
                    types = Arrays.asList(WalletRecordTypeEnumer.BIBI_TO_FUNDS.getCode().intValue(),WalletRecordTypeEnumer.BIBI_TO_PERSONAL.getCode().intValue());
                    break;
                case 3 :
                    types = Arrays.asList(WalletRecordTypeEnumer.BUYING.getCode().intValue());
                    break;
                case 4 :
                    types = Arrays.asList(WalletRecordTypeEnumer.SELLING.getCode().intValue());
                    break;
                default:
                    types = Arrays.asList(WalletRecordTypeEnumer.BIBI_TO_PERSONAL.getCode().intValue(),
                                          WalletRecordTypeEnumer.PERSONAL_TO_BIBI.getCode().intValue(),
                                          WalletRecordTypeEnumer.BUYING.getCode().intValue(),
                                          WalletRecordTypeEnumer.SELLING.getCode().intValue(),
                                          WalletRecordTypeEnumer.BIBI_TO_FUNDS.getCode().intValue(),
                                          WalletRecordTypeEnumer.FUNDS_TO_BIBI.getCode().intValue());
                    break;
            }
        }
        List<RecordVO> list = walletRecordDAO.queryBiBiAccountRecord(page, userId, dto.getCurrencyId(), types, dto.getBeginTime(), dto.getEndTime());
        return page.setRecords(list);
    }
}
