package com.bittrade.admin.controller.sys;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.sys.ITParamConfigService;
import com.bittrade.pojo.dto.TParamConfigDTO;
import com.bittrade.pojo.vo.TParamConfigVO;
import com.bittrade.pojo.model.TParamConfig;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tParamConfig" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TParamConfigController extends BaseController<TParamConfig, TParamConfigDTO, TParamConfigVO, ITParamConfigService> {
	
}
