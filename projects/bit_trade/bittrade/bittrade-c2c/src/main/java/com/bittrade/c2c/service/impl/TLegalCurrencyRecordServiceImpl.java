package com.bittrade.c2c.service.impl;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyRecordServiceImpl;
import com.bittrade.c2c.dao.ITLegalCurrencyRecordDAO;
import com.bittrade.c2c.service.ITLegalCurrencyRecordService;
import com.bittrade.pojo.dto.TLegalCurrencyRecordDTO;
import com.bittrade.pojo.model.TLegalCurrencyRecord;
import com.bittrade.pojo.vo.TLegalCurrencyRecordVO;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLegalCurrencyRecordServiceImpl extends DefaultTLegalCurrencyRecordServiceImpl<ITLegalCurrencyRecordDAO, TLegalCurrencyRecord, TLegalCurrencyRecordDTO, TLegalCurrencyRecordVO> implements ITLegalCurrencyRecordService {
	
}
