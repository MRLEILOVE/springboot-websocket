package com.jdcloud.provider.web.agnt;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AgntCountDto;
import com.jdcloud.provider.pojo.AgntUserConsumer;
import com.jdcloud.provider.pojo.vo.ConsumerVo;
import com.jdcloud.provider.service.AgntUserConsumerService;
import com.jdcloud.provider.service.AgntUserService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 代理商客户
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/agnt/customer")
public class AgntCustomerController extends BaseController {

	@Autowired
	private AgntUserService			agntUserService;

	@Autowired
	private AgntUserConsumerService	agntUserConsumerService;

	private String					prefix	= "agnt/customer";

	@RequiresPermissions("agnt:customer:view")
	@GetMapping()
	public String user(ModelMap mmap) {
		AgntCountDto agntCountDto = agntUserService.agntStatistics();
		mmap.put( "agntCountDto", agntCountDto );
		return prefix + "/list";
	}

	/**
	 * 代理商客户list<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 10:43
	 */
	@RequiresPermissions("agnt:customer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ConsumerVo cv) {
		Page<ConsumerVo> ConsumerVoPage = agntUserConsumerService.selectConsumerList( this.getPage(), cv );
		return new TableDataInfo( ConsumerVoPage.getRecords(), ConsumerVoPage.getTotal() );
	}

	/**
	 * 代理客户list导出<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/10 10:43
	 */
	@RequiresPermissions("agnt:customer:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(ConsumerVo cv) {
		List<ConsumerVo> consumerVos = agntUserConsumerService.selectConsumerListExport( cv );
		ExcelUtil<ConsumerVo> util = new ExcelUtil<>( ConsumerVo.class );
		return util.exportExcel( consumerVos, "AgntConsumer" );
	}

	@RequiresPermissions("agnt:customer:edit")
	@GetMapping("/editInviter/{userId}")
	public String goEditInviter(@PathVariable("userId") Long userId, ModelMap mmap) {
		mmap.put( "userId", userId );
		return prefix + "/editInviter";
	}

	/**
	 * 修改邀请人<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 18:01
	 */
	@PostMapping("/editInviter")
	@RequiresPermissions("agnt:customer:edit")
	@ResponseBody
	public Wrapper<String> editInviter(AgntUserConsumer agntConsumer) {
		return agntUserConsumerService.editInviter( agntConsumer );
	}

	/**
	 * 删除邀请人<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/10 18:01
	 */
	@PostMapping("/delInviter")
	@RequiresPermissions("agnt:customer:edit")
	@ResponseBody
	public Wrapper<String> delInviter(AgntUserConsumer agntConsumer) {
		return agntUserConsumerService.delInviter( agntConsumer );
	}
}