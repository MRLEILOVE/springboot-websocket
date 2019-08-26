package com.jdcloud.provider.web.contract;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.model.service.BizContractPerpetualApi;
import com.jdcloud.provider.pojo.ContractPerpetual;
import com.jdcloud.provider.service.ContractPerpetualService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
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
@RequestMapping("/contract/perpetual")
public class ContractPerpetualController extends BaseController {

	private String			prefix	= "contract/perpetual";

	@Autowired
	private ContractPerpetualService contractPerpetualService;

	@Autowired
	private BizContractPerpetualApi bizContractPerpetualApi;

	@RequiresPermissions("contract:perpetual:view")
	@GetMapping()
	public String user() {
		return prefix + "/perpetual";
	}

	@RequiresPermissions("contract:perpetual:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ContractPerpetual perpetual) {
		perpetual.setStatus(1);
		Page<ContractPerpetual> perpetuals = contractPerpetualService.selectContractPerpetualList( getPage(), perpetual );
		return new TableDataInfo( perpetuals.getRecords(), perpetuals.getTotal() );
	}

	@RequiresPermissions("contract:perpetual:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(ContractPerpetual perpetual) {
		perpetual.setStatus(1);
		List<ContractPerpetual> perpetuals = contractPerpetualService.selectContractPerpetualExportList(perpetual);
		ExcelUtil<ContractPerpetual> util = new ExcelUtil<ContractPerpetual>( ContractPerpetual.class );
		return util.exportExcel( perpetuals, "perpetual" );
	}

	@RequiresPermissions("contract:perpetual:sell")
	@PostMapping("/sell")
	@ResponseBody
	public Wrapper sell(ContractPerpetual perpetual) {
		try {
			ContractPerpetual p = contractPerpetualService.selectContractPerpetualById(perpetual.getOrderId());
			if (p == null){
				return WrapMapper.error("订单不存在！");
			}
			Wrapper wrapper = bizContractPerpetualApi.sell(p.getOrderId().toString(),p.getUserId());
			return wrapper;
		} catch (Exception e) {
			return WrapMapper.error("查询订单失败！");
		}
	}
}