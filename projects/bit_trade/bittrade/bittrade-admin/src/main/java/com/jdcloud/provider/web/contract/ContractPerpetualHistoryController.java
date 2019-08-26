package com.jdcloud.provider.web.contract;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.ContractPerpetual;
import com.jdcloud.provider.service.ContractPerpetualService;
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
@RequestMapping("/contract/perpetualhistory")
public class ContractPerpetualHistoryController extends BaseController {

	private String			prefix	= "contract/perpetualhistory";

	@Autowired
	private ContractPerpetualService contractPerpetualService;

	@RequiresPermissions("contract:perpetualhistory:view")
	@GetMapping()
	public String user() {
		return prefix + "/perpetualhistory";
	}

	@RequiresPermissions("contract:perpetualhistory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ContractPerpetual perpetual) {
		//历史订单如果没有指定状态则使用-1进行判断
        if (perpetual.getStatus() == null) {
            perpetual.setStatus(-1);
        }
		Page<ContractPerpetual> perpetuals = contractPerpetualService.selectContractPerpetualHistoryList( getPage(), perpetual );
		return new TableDataInfo( perpetuals.getRecords(), perpetuals.getTotal() );
	}

	@RequiresPermissions("contract:perpetualhistory:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(ContractPerpetual perpetual) {
		//历史订单如果没有指定状态则使用-1进行判断
		if (perpetual.getStatus() == null) {
			perpetual.setStatus(-1);
		}
		List<ContractPerpetual> perpetuals = contractPerpetualService.selectContractPerpetualExportList(perpetual);
		ExcelUtil<ContractPerpetual> util = new ExcelUtil<ContractPerpetual>( ContractPerpetual.class );
		return util.exportExcel( perpetuals, "perpetual" );
	}

}