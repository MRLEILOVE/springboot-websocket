package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.api.__default.DAO.IDefaultWCoinConfigDAO;
import com.bittrade.api.service.IWCoinConfigService;
import com.bittrade.pojo.dto.WCoinConfigDTO;
import com.bittrade.pojo.model.WCoinConfig;
import com.bittrade.pojo.vo.WCoinConfigVO;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wCoinConfig" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WCoinConfigController extends BaseController<WCoinConfig, WCoinConfigDTO, WCoinConfigVO, IDefaultWCoinConfigDAO, IWCoinConfigService> {
	
}
