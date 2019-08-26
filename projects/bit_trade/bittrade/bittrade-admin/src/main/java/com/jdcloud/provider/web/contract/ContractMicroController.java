package com.jdcloud.provider.web.contract;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.ContractMicro;
import com.jdcloud.provider.service.ContractMicroService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 微合约持仓表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/contract/micro")
public class ContractMicroController extends BaseController {

	private String			prefix	= "contract/micro";

	@Autowired
	private ContractMicroService contractMicroService;

	@RequiresPermissions("contract:micro:view")
	@GetMapping()
	public String user() {
		return prefix + "/micro";
	}

	@RequiresPermissions("contract:micro:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ContractMicro micro) {
		micro.setStatus(1);
		Page<ContractMicro> micros = contractMicroService.selectContractMicroList( getPage(), micro );
		return new TableDataInfo( micros.getRecords(), micros.getTotal() );
	}

	@RequiresPermissions("contract:micro:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(ContractMicro micro) {
		micro.setStatus(1);
		List<ContractMicro> micros = contractMicroService.selectContractMicroExportList(micro);
		ExcelUtil<ContractMicro> util = new ExcelUtil<ContractMicro>( ContractMicro.class );
		return util.exportExcel( micros, "micro" );
	}

}