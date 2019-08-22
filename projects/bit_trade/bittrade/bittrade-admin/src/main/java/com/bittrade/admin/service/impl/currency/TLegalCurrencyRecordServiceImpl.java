package com.bittrade.admin.service.impl.currency;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyRecordServiceImpl;
import com.bittrade.pojo.dto.TLegalCurrencyRecordDTO;
import com.bittrade.pojo.vo.TLegalCurrencyRecordVO;
import com.bittrade.pojo.model.TLegalCurrencyRecord;
import com.bittrade.admin.dao.currency.ITLegalCurrencyRecordDAO;
import com.bittrade.admin.service.currency.ITLegalCurrencyRecordService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TLegalCurrencyRecordServiceImpl extends DefaultTLegalCurrencyRecordServiceImpl<ITLegalCurrencyRecordDAO, TLegalCurrencyRecord, TLegalCurrencyRecordDTO, TLegalCurrencyRecordVO> implements ITLegalCurrencyRecordService {
	
}
