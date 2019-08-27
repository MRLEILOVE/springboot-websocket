package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyRecordServiceImpl;
import com.bittrade.currency.dao.ITLegalCurrencyRecordDAO;
import com.bittrade.pojo.dto.TLegalCurrencyRecordDTO;
import com.bittrade.pojo.vo.TLegalCurrencyRecordVO;
import com.bittrade.pojo.model.TLegalCurrencyRecord;
import com.bittrade.currency.api.service.ITLegalCurrencyRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLegalCurrencyRecordServiceImpl extends DefaultTLegalCurrencyRecordServiceImpl<ITLegalCurrencyRecordDAO, TLegalCurrencyRecord, TLegalCurrencyRecordDTO, TLegalCurrencyRecordVO> implements ITLegalCurrencyRecordService {
	
}
