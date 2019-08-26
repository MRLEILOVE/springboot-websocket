package com.jdcloud.provider.web.audit;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.Recharge;
import com.jdcloud.provider.pojo.RechargeOrder;
import com.jdcloud.provider.pojo.vo.RechargeConfigVo;
import com.jdcloud.provider.service.RechargeOrderService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 充值审核
 * <p>
 *
 * @author yongheng
 * @since 2018/11/23
 */
@Controller
@RequestMapping(value = "/audit/recharge")
public class RechargeController extends BaseController {
	private String					prefix	= "audit/recharge";

	@Autowired
	private RechargeOrderService	rechargeOrderService;

	@RequiresPermissions("audit:recharge:view")
	@GetMapping()
	public String recharge() {
		return prefix + "/recharge";
	}

	/**
	 * 审核列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 11:01
	 */
	@RequiresPermissions("audit:recharge:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Recharge recharge) {
		this.paramsHandle( recharge );
		Page<Recharge> page = rechargeOrderService.selectRechargeOrderList( recharge );
		return new TableDataInfo( page.getRecords(), page.getTotal() );
	}

	@RequiresPermissions("audit:recharge:auditRefuse")
	@GetMapping("/auditEdit/{orderNo}")
	public String audit(@PathVariable("orderNo") String orderNo, ModelMap mmap) {
		RechargeOrder rechargeOrder = new RechargeOrder();
		rechargeOrder.setOrderNo( orderNo );
		mmap.put( "rechargeOrder", rechargeOrder );
		return prefix + "/edit";
	}

	@RequiresPermissions("audit:recharge:edit")
	@GetMapping("/corrected/{orderNo}")
	public String corrected(@PathVariable("orderNo") String orderNo, ModelMap mmap) {
		RechargeOrder rechargeOrder = new RechargeOrder();
		rechargeOrder.setOrderNo( orderNo );
		mmap.put( "rechargeOrder", rechargeOrder );
		return prefix + "/corrected";
	}

	/**
	 * 修正金额<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@RequiresPermissions("audit:recharge:edit")
	@PostMapping(value = "/correctedAmount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> correctedAmount(RechargeOrder rechargeOrder) {
		return rechargeOrderService.correctedAmount( rechargeOrder, this.getUser() );
	}

	/**
	 * 充值审核通过<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@RequiresPermissions("audit:recharge:auditPass")
	@PostMapping(value = "/auditPass", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> auditPass(@RequestParam("orderNos[]") String[] orderNos) {
		return rechargeOrderService.auditPass( orderNos, this.getUser() );
	}

	/**
	 * 充值审核拒绝<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@RequiresPermissions("audit:recharge:auditRefuse")
	@PostMapping(value = "/auditRefuse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> auditRefuse(RechargeOrder rechargeOrder) {
		return rechargeOrderService.auditRefuse( rechargeOrder, this.getUser() );
	}

	/**
	 * 跳转自动充值界面<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 16:45
	 */
	@RequiresPermissions("audit:recharge:rechargeConfig")
	@GetMapping("/goRechargeConfig")
	public String goRechargeConfig(ModelMap mmap) {
		mmap.put( "rechargeConfig", rechargeOrderService.goRechargeConfig() );
		return prefix + "/rechargeConfig";
	}

	/**
	 * 修改自动充值设置<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@RequiresPermissions("audit:recharge:rechargeConfig")
	@PostMapping(value = "/rechargeConfig", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> rechargeConfig(RechargeConfigVo rechargeConfigVo) {
		return rechargeOrderService.rechargeConfig( rechargeConfigVo, this.getUser() );
	}
}