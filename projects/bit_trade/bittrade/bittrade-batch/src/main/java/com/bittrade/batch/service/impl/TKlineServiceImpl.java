package com.bittrade.batch.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.service.ITKlineService;
import com.bittrade.api.service.abs.AbstractTKlineServiceImpl;
import com.bittrade.batch.dao.ITKlineDAO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TKlineServiceImpl extends AbstractTKlineServiceImpl<ITKlineDAO> implements ITKlineService<ITKlineDAO> {

}
